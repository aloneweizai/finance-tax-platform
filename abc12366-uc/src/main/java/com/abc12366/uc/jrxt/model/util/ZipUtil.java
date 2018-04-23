package com.abc12366.uc.jrxt.model.util;

import org.springframework.stereotype.Component;

import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.zip.*;

/**
 * @author ziben
 * @version 1.0 11-3-29 2:37
 */
@Component("zipUtil")
public class ZipUtil {
    public static final String EXT = "zip";
    private static final String BASE_DIR = "D:\\zzzzzz";
    private static final String PATH = File.separator;
    private static final int BUFFER = 1024;

    public static String zip(String inFileName) throws IOException {
        File zipFile = null;

        zipFile = File.createTempFile("wtdkdz_", EXT);
        InputStream inFile = ClassLoader.getSystemResourceAsStream(inFileName);
        ZipOutputStream zOut = new ZipOutputStream(new FileOutputStream(zipFile));
        zOut.putNextEntry(new ZipEntry(inFileName));
        int len;
        while ((len = inFile.read()) != -1) {
            zOut.write(len);
        }
        inFile.close();
        zOut.close();
        return zipFile.getAbsolutePath();
    }

    public byte[] compress(HashMap<String, String> contents) throws IOException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        ZipOutputStream zOut = new ZipOutputStream(byteArrayOutputStream);
        byte[] outBytes;
        try {
            for (Map.Entry<String, String> content : contents.entrySet()) {
                byte[] bytes = content.getValue().getBytes();
                ZipEntry zipEntry = new ZipEntry(content.getKey());
                zOut.putNextEntry(zipEntry);
                zOut.write(bytes, 0, bytes.length);
            }

            zOut.close();
            outBytes = byteArrayOutputStream.toByteArray();
            byteArrayOutputStream.close();
        } finally {
            if(byteArrayOutputStream != null)
                byteArrayOutputStream.close();
        }
        return outBytes;
    }

    public byte[] compressWithZlib(HashMap<String, String> contents) throws IOException {
        byte[] output = new byte[0];
        byte[] data = ((String)contents.values().toArray()[0]).getBytes();
        Deflater compresser = new Deflater();

        compresser.reset();
        compresser.setInput(data);
        compresser.finish();
        ByteArrayOutputStream bos = new ByteArrayOutputStream(data.length);
        try {
            byte[] buf = new byte[1024];
            while (!compresser.finished()) {
                int i = compresser.deflate(buf);
                bos.write(buf, 0, i);
            }
            output = bos.toByteArray();
        } catch (Exception e) {
            output = data;
            e.printStackTrace();
        } finally {
            try {
                bos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        compresser.end();
        return output;
    }

    public HashMap<String, String> decompress(byte[] bytes) throws IOException {
//    	FileOutputStream fos = new FileOutputStream("C:\\abc.zip");
//    	fos.write(bytes);
//    	fos.close();

        HashMap<String, String> resultMap;
        ByteArrayOutputStream bos = null;
        ZipInputStream zis = null;
        try {
            ByteArrayInputStream inputStream = new ByteArrayInputStream(bytes);
            CheckedInputStream cis = new CheckedInputStream(inputStream, new CRC32());
            zis = new ZipInputStream(cis);

            resultMap = new HashMap<String, String>();

            ZipEntry entry = null;
            while ((entry = zis.getNextEntry()) != null) {
                bos = new ByteArrayOutputStream();

                int count;
                byte data[] = new byte[BUFFER];
                while ((count = zis.read(data, 0, BUFFER)) != -1) {
                    bos.write(data, 0, count);
                }
                resultMap.put(entry.getName(), bos.toString());
                bos.close();
                zis.closeEntry();
            }
        } finally {
            if(bos != null) bos.close();
            if(zis != null) zis.closeEntry();
        }
        return resultMap;
    }

    public HashMap<String, String> decompressWithZlib(byte[] bytes) throws IOException {
        HashMap<String, String> resultMap = new HashMap<String, String>();

        ByteArrayOutputStream bos = null;
        InflaterOutputStream zos = null;
        try {
            bos = new ByteArrayOutputStream();
            zos = new InflaterOutputStream(bos);
            zos.write(bytes);
            zos.close();
            resultMap.put("1", bos.toString());
        }
        finally {
            if(bos != null) {
                try {
                    bos.close();
                }
                catch(Exception e) {}
            }
            if(zos != null) {
                try {
                    zos.close();
                }
                catch(Exception e) {}
            }
        }
        return resultMap;
    }

    public void decompress(File srcFile) throws Exception {
        String basePath = srcFile.getAbsolutePath() + ".dir";
        decompress(srcFile, basePath);
    }

    public void decompress(File srcFile, String destPath) throws Exception {
        decompress(srcFile, new File(destPath));
    }

    public void decompress(File srcFile, File destFile) throws Exception {
        CheckedInputStream cis = new CheckedInputStream(new FileInputStream(srcFile), new CRC32());
        ZipInputStream zis = new ZipInputStream(cis);
        decompress(destFile, zis);
        zis.close();
    }

    private void decompress(File destFile, ZipInputStream zis) throws Exception {

        ZipEntry entry = null;
        while ((entry = zis.getNextEntry()) != null) {

            // ļ
            String dir = destFile.getPath() + File.separator + entry.getName();

            File dirFile = new File(dir);

            // ļ
            fileProber(dirFile);

            if (entry.isDirectory()) {
                dirFile.mkdirs();
            } else {
                decompressFile(dirFile, zis);
            }

            zis.closeEntry();
        }
    }

    private void decompressFile(File destFile, ZipInputStream zis) throws Exception {

        BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(destFile));

        int count;
        byte data[] = new byte[BUFFER];
        while ((count = zis.read(data, 0, BUFFER)) != -1) {
            bos.write(data, 0, count);
        }

        bos.close();
    }

    private static void fileProber(File dirFile) {

        File parentFile = dirFile.getParentFile();
        if (!parentFile.exists()) {

            fileProber(parentFile);

            parentFile.mkdir();
        }
    }

    public static void main(String[] args) {
        String srcStr = "<?xml version=\"1.0\" encoding=\"GBK\" ?>\n" +
                "<REQUEST>\n" +
                "    <NSRSBH>430903707384722</NSRSBH>\n" +
                "</REQUEST>";
        //srcStr = new String(bytes);
        byte[] bytes = srcStr.getBytes();

        HashMap<String, String> map = new HashMap<String, String>();

        map.put("getqyxxreq.xml", srcStr);

        ZipUtil zu = new ZipUtil();
        byte[] bytes2 = new byte[0];
        try {
            bytes2 = zu.compress(map);
        } catch (IOException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
       try {
            /*     CryptUtil cu = new CryptUtil();

                bytes2 = cu.encryptContent(bytes2, "12345678");
                */
                FileOutputStream fos = null;

                    fos = new FileOutputStream("D:\\project\\FPWTDKDZ\\Ŀ̹\\ʵ\\WTDKDZ\\DKDZFW\\dkdz-service\\samples\\getqyxxreq.zip");
            fos.write(bytes2);
            fos.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        } catch (IOException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }  catch (Exception e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }

//        ZipUtil zu = new ZipUtil();
//        File file = new File("D:\\test.zip");
//        try {
//            zu.decompress(file, "D:\\");
//        } catch (Exception e) {
//            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
//        }
    }
}
