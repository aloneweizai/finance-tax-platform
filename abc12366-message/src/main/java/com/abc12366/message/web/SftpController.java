package com.abc12366.message.web;

import com.abc12366.gateway.component.SpringCtxHolder;
import com.abc12366.gateway.util.Constant;
import com.abc12366.gateway.util.Utils;
import com.abc12366.message.model.bo.FjBo;
import com.abc12366.message.model.bo.FjListBo;
import com.abc12366.message.util.SFTPUtil;
import com.jcraft.jsch.ChannelSftp;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 文件上传服务
 *
 * @author xieyanmao
 */
@RestController
@RequestMapping(path = "/sftp", headers = Constant.VERSION_HEAD + "=" + Constant.VERSION_1)
public class SftpController {

    private static final Logger LOGGER = LoggerFactory.getLogger(SftpController.class);

    /**
     * 通过字节码上传文件
     *
     * @param fjListBo FjListBo
     * @return ResponseEntity
     */
    @PostMapping
    public ResponseEntity upload(@Valid @RequestBody FjListBo fjListBo) {
        LOGGER.info("{}", fjListBo);
        SFTPUtil sf = new SFTPUtil();
        String host = SpringCtxHolder.getProperty("sftp_host");
        int port = Integer.parseInt(SpringCtxHolder.getProperty("sftp_port"));
        String username = SpringCtxHolder.getProperty("sftp_username");
        String password = SpringCtxHolder.getProperty("sftp_password");

        String directory = fjListBo.getDirectory();
        List<FjBo> fjBoList = fjListBo.getFjBo();
        List<Map<String, String>> dataList = new ArrayList<>();
        String fileName;
        List<Byte> content;
        ChannelSftp sftp = sf.connect(host, port, username, password);
        for (FjBo fjBo : fjBoList) {
            fileName = fjBo.getFileName();
            content = fjBo.getContent();
            Map<String, String> map = sf.uploadByByte(directory, content, fileName, sftp);
            dataList.add(map);
        }
        LOGGER.info("{}", dataList);
        sftp.disconnect();
        sftp.exit();
        return ResponseEntity.ok(Utils.kv("dataList", dataList));
    }

    /**
     * base64方式上传文件
     *
     * @param fjListBo FjListBo
     * @return ResponseEntity
     */
    @PostMapping(path = "/upload")
    public ResponseEntity uploadBase64(@Valid @RequestBody FjListBo fjListBo) {
        SFTPUtil sf = new SFTPUtil();
        String host = SpringCtxHolder.getProperty("sftp_host");
        int port = Integer.parseInt(SpringCtxHolder.getProperty("sftp_port"));
        String username = SpringCtxHolder.getProperty("sftp_username");
        String password = SpringCtxHolder.getProperty("sftp_password");

        String directory = fjListBo.getDirectory();
        List<FjBo> fjBoList = fjListBo.getFjBo();
        List<Map<String, String>> dataList = new ArrayList<>();
        String fileName;
        String fileContent;
        ChannelSftp sftp = sf.connect(host, port, username, password);
        for (FjBo fjBo : fjBoList) {
            fileName = fjBo.getFileName();
            fileContent = fjBo.getFileContent();
            Map<String, String> map = sf.uploadByBase64(directory, fileContent, fileName, sftp);
            dataList.add(map);
        }
        LOGGER.info("{}", dataList);
        sftp.disconnect();
        sftp.exit();
        return ResponseEntity.ok(Utils.kv("dataList", dataList));
    }
}
