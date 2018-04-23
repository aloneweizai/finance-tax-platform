package com.abc12366.uc.jrxt.model.util;

import org.apache.commons.compress.archivers.*;
import org.apache.commons.compress.archivers.zip.ZipArchiveEntry;
import org.apache.commons.compress.archivers.zip.ZipFile;
import org.springframework.stereotype.Component;

import java.io.*;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.zip.CRC32;
import java.util.zip.CheckedInputStream;

@Component
public class ZipUtilTdps {
    private static int BUFFER = 2048;

    public static byte[] compress(HashMap<String, String> contents) throws ArchiveException, IOException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        ArchiveOutputStream zipOutput = new ArchiveStreamFactory().createArchiveOutputStream(ArchiveStreamFactory.ZIP,
                                                                                             byteArrayOutputStream);
        for (Map.Entry<String, String> content : contents.entrySet()) {
            ArchiveEntry entry = new ZipArchiveEntry(content.getKey());
            zipOutput.putArchiveEntry(entry);
            zipOutput.write(content.getValue().getBytes());
            zipOutput.closeArchiveEntry();
        }

        zipOutput.close();
        byte[] outBytes = byteArrayOutputStream.toByteArray();
        byteArrayOutputStream.close();
        return outBytes;
    }

    public static HashMap<String, String> decompress(byte[] bytes) throws IOException, ArchiveException {
        ByteArrayInputStream inputStream = new ByteArrayInputStream(bytes);
        CheckedInputStream cis = new CheckedInputStream(inputStream, new CRC32());

        ArchiveInputStream zipInput = new ArchiveStreamFactory().createArchiveInputStream(cis);
        HashMap<String, String> resultMap = new HashMap<>();

        ArchiveEntry entry;
        while ((entry = zipInput.getNextEntry()) != null) {
            byte[] contents = new byte[BUFFER];
            int count;

            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            while ((count = zipInput.read(contents, 0, BUFFER)) != -1) {
                baos.write(contents, 0, count);
            }
            String convertedStr = new String(baos.toByteArray(), "GBK");
            resultMap.put(entry.getName(), convertedStr);
            //System.out.println("xxxxxxxx" +convertedStr);
        }

        zipInput.close();
        return resultMap;
    }

    public static void decompress(File srcFile, File destFile) throws Exception {
        ZipFile zipFile = new ZipFile(srcFile);

        Enumeration<ZipArchiveEntry> entries = zipFile.getEntries();
        while (entries.hasMoreElements()) {
            ZipArchiveEntry entry = entries.nextElement();
            InputStream content = zipFile.getInputStream(entry);
            FileOutputStream fos = new FileOutputStream("D://" + entry.getName());
            try {
                byte[] contents = new byte[BUFFER];
                int count;
                while ((count = content.read(contents, 0, BUFFER)) != -1) {
                    fos.write(contents, 0, count);
                }
            } finally {
                fos.close();
                content.close();
            }
        }
    }
}
