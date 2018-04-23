package com.abc12366.uc.service.impl;


import com.abc12366.gateway.component.SpringCtxHolder;
import com.abc12366.gateway.exception.ServiceException;
import com.abc12366.gateway.util.Constant;
import com.abc12366.gateway.util.Utils;
import com.abc12366.uc.mapper.db1.WxGzhMapper;
import com.abc12366.uc.mapper.db2.WxGzhRoMapper;
import com.abc12366.uc.model.weixin.bo.CmsFileUploadDto;
import com.abc12366.uc.model.weixin.bo.FileListDto;
import com.abc12366.uc.model.weixin.bo.FjDto;
import com.abc12366.uc.model.weixin.bo.gzh.GzhInfo;
import com.abc12366.uc.service.IWxGzhService;
import com.abc12366.uc.util.wx.WechatUrl;
import com.abc12366.uc.util.wx.WxConnectFactory;
import com.abc12366.uc.util.wx.WxGzhClient;
import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageHelper;
import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.SftpATTRS;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import sun.misc.BASE64Encoder;

import javax.servlet.http.HttpServletRequest;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.sql.Timestamp;
import java.util.*;

@Service("iWxGzhService")
public class WxGzhServiceimpl implements IWxGzhService {

    private static final Logger LOGGER = LoggerFactory.getLogger(WxGzhServiceimpl.class);
    @Autowired
    private WxGzhMapper wxGzhMapper;
    @Autowired
    private WxGzhRoMapper wxGzhRoMapper;
    
    @Autowired
    private RestTemplate restTemplate;
    


    public List<GzhInfo> wxgzhList(GzhInfo gzhInfo, int page, int size) {
        PageHelper.startPage(page, size, true).pageSizeZero(true).reasonable(true);
        List<GzhInfo> gzhInfos = wxGzhRoMapper.selectList(gzhInfo);
        return gzhInfos;
    }


    public GzhInfo selectOne(String id) {
        GzhInfo info = new GzhInfo();
        try {
            LOGGER.info("查询单个公众号信息:{}", id);
            info = wxGzhRoMapper.selectOne(id);
        } catch (Exception e) {
            LOGGER.error("查询单个公众号信息异常：{}", e);
            throw new ServiceException(4234);
        }
        return info;
    }

    @Transactional("db1TxManager")
    public GzhInfo insert(GzhInfo gzhInfo) {
        Timestamp now = new Timestamp(new Date().getTime());
        gzhInfo.setId(Utils.uuid());
        gzhInfo.setCreatDate(now);
        gzhInfo.setLastupdate(now);

        wxGzhMapper.insert(gzhInfo);
        return gzhInfo;
    }


    @Transactional("db1TxManager")
    public GzhInfo update(GzhInfo gzhInfo) {
        Timestamp now = new Timestamp(new Date().getTime());
        gzhInfo.setLastupdate(now);
        int update = wxGzhMapper.update(gzhInfo);
        if (update != 1) {
            LOGGER.info("{修改微信公众号失败}", update);
            throw new ServiceException(4421);
        }
        return wxGzhRoMapper.selectOne(gzhInfo.getId());
    }


    @Transactional("db1TxManager")
    public void delete(String id) {
        GzhInfo gzhInfo = selectOne(id);
        if (gzhInfo != null) {
            wxGzhMapper.delete(id);
        } else {
            throw new ServiceException(4012);
        }
    }


    @Transactional("db1TxManager")
	public void updateUserToken(GzhInfo gzhInfo) {
		wxGzhMapper.updateUserToken(gzhInfo);
	}

	
    public String getUserToken(String appid){
    	return wxGzhRoMapper.selectUserToken(appid);
    }


    @Transactional("db1TxManager")
	public void updatejsapiTicket(GzhInfo gzhInfo) {
    	wxGzhMapper.updatejsapiTicket(gzhInfo);
    }


	@SuppressWarnings("unchecked")
	@Override
	public String getWxDownFilePath(String userId,String mediaId,HttpServletRequest request) {
		try {
			Map<String, String> tks = new HashMap<String, String>();
			tks.put("access_token", WxGzhClient.getInstanceToken());
			tks.put("media_id", mediaId);
			InputStream inputStream=WxConnectFactory.getWXFile(WechatUrl.WXIMG_DOWN, tks);
			
			ByteArrayOutputStream content=new ByteArrayOutputStream();
			byte[] buffer = new byte[1024];
			int len;
			while ((len = inputStream.read(buffer)) !=-1) {
				content.write(buffer, 0, len);
			}
			inputStream.close();
			
			CmsFileUploadDto cmsFileUploadDto = new CmsFileUploadDto();
			FjDto fjDto = new FjDto();
			fjDto.setFileName(mediaId+".jpg");
			try {
				fjDto.setFileContent(new BASE64Encoder().encode(content.toByteArray()));
				cmsFileUploadDto.setDirectory(userId.replaceAll("!", "/"));
				cmsFileUploadDto.getFjBo().add(fjDto);
			} catch (Exception e) {
				e.printStackTrace();
			}
  
			String url = SpringCtxHolder.getProperty("abc12366.message.url") + "/sftp/upload";
			
			HttpHeaders httpHeaders = new HttpHeaders();
	        if (!StringUtils.isEmpty(request.getHeader(Constant.APP_TOKEN_HEAD))) {
	            httpHeaders.add(Constant.APP_TOKEN_HEAD, request.getHeader(Constant.APP_TOKEN_HEAD));
	        }
	        if (!StringUtils.isEmpty(request.getHeader(Constant.ADMIN_TOKEN_HEAD))) {
	            httpHeaders.add(Constant.ADMIN_TOKEN_HEAD, request.getHeader(Constant.ADMIN_TOKEN_HEAD));
	        }
	        if (!StringUtils.isEmpty(request.getHeader(Constant.USER_TOKEN_HEAD))) {
	            httpHeaders.add(Constant.USER_TOKEN_HEAD, request.getHeader(Constant.USER_TOKEN_HEAD));
	        }
	        if (!StringUtils.isEmpty(request.getHeader(Constant.VERSION_HEAD))) {
	            httpHeaders.add(Constant.VERSION_HEAD, request.getHeader(Constant.VERSION_HEAD));
	        }
	        HttpEntity requestEntity = new HttpEntity(cmsFileUploadDto, httpHeaders);
	        
	        ResponseEntity<String> responseEntity = null;
	        try {
	            responseEntity = restTemplate.exchange(url, HttpMethod.POST, requestEntity, String.class);
	        } catch (RestClientException e) {
	            throw new ServiceException("0000", "调用接口异常，地址：" + url);
	        }
	        LOGGER.info("Response: {}, {}", url, responseEntity);
	        String responseStr=( responseEntity != null ? responseEntity.getBody() : null);
	        
	        FileListDto fileListDto  = null;
	        if (!StringUtils.isEmpty(responseStr)) {
	        	fileListDto = JSON.parseObject(responseStr, FileListDto.class);
	        }
	        
	        if(fileListDto.getDataList().size()>0){
	        	return "/images" +fileListDto.getDataList().get(0).getFilePath();
	        }else{
	        	LOGGER.info("下载微信服务器文件失败",fileListDto.getMessage());
				throw new ServiceException(9999,"下载微信服务器文件失败:"+fileListDto.getMessage());
	        }
			
			
		} catch (Exception e) {
			LOGGER.info("下载微信服务器文件失败",e);
			throw new ServiceException(9999,"下载微信服务器文件失败");
		}
	}
	
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
	
	@SuppressWarnings("rawtypes")
	private static List fileBytesToList(byte[] bytes) throws Exception {
		Byte[] newBytes = new Byte[bytes.length];
		List list = null;
		for (int i = 0; i < bytes.length; i++) {
			newBytes[i] = bytes[i];
		}
		list = Arrays.asList(newBytes);
		return list;
	}
}
