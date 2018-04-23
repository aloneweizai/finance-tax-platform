package com.abc12366.message.util;

import com.abc12366.gateway.component.SpringCtxHolder;
import com.jcraft.jsch.*;
import org.slf4j.LoggerFactory;
import sun.misc.BASE64Decoder;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @author xieyanmao
 */
public class SFTPUtil {

    private static final org.slf4j.Logger LOGGER = LoggerFactory.getLogger(SFTPUtil.class);

    /**
     * 将上传的文件进行重命名
     *
     * @param name 文件名
     * @return 文件名
     */
    private static String rename(String name) {

        Long now = Long.parseLong(new SimpleDateFormat("yyyyMMddHHmmss")
                .format(new Date()));
        Long random = (long) (Math.random() * now);
        String fileName = now + "" + random;

        if (name.contains(".")) {
            fileName += name.substring(name.lastIndexOf("."));
        }
        return fileName;
    }

    /**
     * 数组转byte数组
     *
     * @param list 数组
     * @return byte[]
     */
    public static byte[] listToByteArray(List<Byte> list) {
        byte[] bytes = new byte[list.size()];
        for (int i = 0; i < list.size(); i++) {
            bytes[i] = list.get(i);
        }
        return bytes;
    }

    public static void main(String[] args) {
        SFTPUtil sf = new SFTPUtil();
        String host = "118.118.116.202";
        int port = 22;
        String username = "root";
        String password = "hngs_123";
        String directory = "/root";
        String uploadFile = "D:/upload/2_201083103325.png";
        String downloadFile = "123.png";
        String saveFile = "D:/upload/2_201083103325_download.png";
        String deleteFile = "123.png";
        ChannelSftp sftp = sf.connect(host, port, username, password);
        sf.upload(directory, uploadFile, sftp);
//        sf.download(directory, downloadFile, saveFile, sftp);
//        sf.delete(directory, deleteFile, sftp);
        try {
            sftp.cd(directory);
            sftp.mkdir("ss");
            System.out.println("finished");
        } catch (Exception e) {
            e.printStackTrace();
            LOGGER.error("{}", e);
        }
    }

    /**
     * 连接sftp服务器
     *
     * @param host     主机
     * @param port     端口
     * @param username 用户名
     * @param password 密码
     * @return sftp通道
     */
    public ChannelSftp connect(String host, int port, String username,
                               String password) {
        ChannelSftp sftp = null;
        try {
            JSch jsch = new JSch();
            jsch.getSession(username, host, port);
            Session sshSession = jsch.getSession(username, host, port);
            System.out.println("Session created.");
            sshSession.setPassword(password);
            java.util.Properties sshConfig = new java.util.Properties();
            sshConfig.put("StrictHostKeyChecking", "no");
            sshSession.setConfig(sshConfig);
            sshSession.connect();
            System.out.println("Session connected.");
            System.out.println("Opening Channel.");
            Channel channel = sshSession.openChannel("sftp");
            channel.connect();
            sftp = (ChannelSftp) channel;
            System.out.println("Connected to " + host + ".");
        } catch (Exception e) {
            LOGGER.error("{}", e);
        }
        return sftp;
    }

    /**
     * 上传文件
     *
     * @param directory  上传的目录
     * @param uploadFile 要上传的文件
     * @param sftp       sftp通道
     */
    public void upload(String directory, String uploadFile, ChannelSftp sftp) {
        try {
            sftp.cd(directory);
            File file = new File(uploadFile);
            sftp.put(new FileInputStream(file), file.getName());
        } catch (Exception e) {
            e.printStackTrace();
            LOGGER.error("{}", e);
        }
    }

    /**
     * 上传文件
     *
     * @param directory 上传的目录
     * @param content   要上传的文件
     * @param fileName  文件名
     * @param sftp      sftp通道
     * @return 文件信息
     */
    public Map<String, String> uploadByByte(String directory, List<Byte> content, String fileName, ChannelSftp sftp) {
        Map<String, String> map = new HashMap<>();
        OutputStream outputStream = null;
        try {
            String imagesuri = SpringCtxHolder.getProperty("sftp_imagesuri");
            sftp.cd(imagesuri);
            if (isDirExist(directory, sftp)) {
                sftp.cd(directory);
            } else {
                sftp.mkdir(directory);
                sftp.cd(directory);
            }
            String storeName = rename(fileName);
            String filePath = imagesuri + "/" + directory + "/" + storeName;
            outputStream = sftp.put(filePath);
            byte[] buffer = null;
            if (content != null) {
                buffer = SFTPUtil.listToByteArray(content);
            }
            String filePath1 = "/" + directory + "/" + storeName;
            outputStream.write(buffer != null ? buffer : new byte[0]);
            outputStream.flush();
            map.put("fileName", fileName);
            map.put("storeName", storeName);
            map.put("filePath", filePath1);
            return map;
        } catch (Exception e) {
            e.printStackTrace();
            LOGGER.error("{}", e);
        } finally {
            try {
                if (outputStream != null) {
                    outputStream.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
                LOGGER.error("{}", e);
            }
        }
        return map;
    }

    /**
     * 上传文件
     *
     * @param directory 上传的目录
     * @param content   要上传的文件
     * @param fileName  文件名
     * @param sftp      sftp通道
     * @return 文件信息
     */
    public Map<String, String> uploadByBase64(String directory, String content, String fileName, ChannelSftp sftp) {
        Map<String, String> map = new HashMap<>();
        OutputStream outputStream = null;
        try {
            String imagesuri = SpringCtxHolder.getProperty("sftp_imagesuri");
            sftp.cd(imagesuri);
            if (isDirExist(directory, sftp)) {
                sftp.cd(directory);
            } else {
                sftp.mkdir(directory);
                sftp.cd(directory);
            }
            String storeName = rename(fileName);
            String filePath = imagesuri + "/" + directory + "/" + storeName;
            outputStream = sftp.put(filePath);
            BASE64Decoder decoder = new BASE64Decoder();
            //Base64解码
            byte[] buffer = decoder.decodeBuffer(content);
            String filePath1 = "/" + directory + "/" + storeName;
            outputStream.write(buffer);
            outputStream.flush();
            map.put("fileName", fileName);
            map.put("storeName", storeName);
            map.put("filePath", filePath1);
            return map;
        } catch (Exception e) {
            e.printStackTrace();
            LOGGER.error("{}", e);
        } finally {
            try {
                if (outputStream != null) {
                    outputStream.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
                LOGGER.error("{}", e);
            }
        }
        return map;
    }

    /**
     * 下载文件
     *
     * @param directory    下载目录
     * @param downloadFile 下载的文件
     * @param saveFile     存在本地的路径
     * @param sftp         sftp通道
     */
    public void download(String directory, String downloadFile, String saveFile, ChannelSftp sftp) {
        try {
            sftp.cd(directory);
            File file = new File(saveFile);
            sftp.get(downloadFile, new FileOutputStream(file));
        } catch (Exception e) {
            e.printStackTrace();
            LOGGER.error("{}", e);
        }
    }

    /**
     * 获取下载文件流
     *
     * @param directory    下载目录
     * @param downloadFile 下载的文件
     * @param sftp         sftp通道
     */
    public InputStream getInputStream(String directory, String downloadFile, ChannelSftp sftp) {
        InputStream in = null;
        try {
            sftp.cd(directory);
            in = sftp.get(downloadFile);
        } catch (Exception e) {
            e.printStackTrace();
            LOGGER.error("{}", e);
        }
        return in;
    }

    /**
     * 删除文件
     *
     * @param directory  要删除文件所在目录
     * @param deleteFile 要删除的文件
     * @param sftp       sftp通道
     */
    public void delete(String directory, String deleteFile, ChannelSftp sftp) {
        try {
            sftp.cd(directory);
            sftp.rm(deleteFile);
        } catch (Exception e) {
            e.printStackTrace();
            LOGGER.error("{}", e);
        }
    }

    /**
     * 列出目录下的文件
     *
     * @param directory 要列出的目录
     * @param sftp      sftp通道
     * @return Vector
     * @throws com.jcraft.jsch.SftpException
     */
    public Vector listFiles(String directory, ChannelSftp sftp) throws SftpException {
        return sftp.ls(directory);
    }

    /**
     * 判断目录是否存在
     */
    public boolean isDirExist(String directory, ChannelSftp sftp) {
        boolean isDirExistFlag = false;
        try {
            SftpATTRS sftpATTRS = sftp.lstat(directory);
            isDirExistFlag = true;
            return sftpATTRS.isDir();
        } catch (Exception e) {
            if (e.getMessage().toLowerCase().equals("no such file")) {
                isDirExistFlag = false;
            }
        }
        return isDirExistFlag;
    }
}
