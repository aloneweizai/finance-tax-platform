package com.abc12366.uc.jrxt.model.util;

import com.abc12366.gateway.exception.ServiceException;
import com.abc12366.uc.jrxt.model.tiripPackage.*;
import com.alibaba.fastjson.JSONObject;
import org.exolab.castor.xml.MarshalException;
import org.exolab.castor.xml.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 15-5-15.
 */
@Component
public class PkgUtil extends BaseObject{
    @Autowired
    private CrypTdpsUtil cryptutiltdps;
    @Autowired
    private ZipUtilTdps zipUtiltdps;
    @Autowired
    private CodeUtilTdps codeUtiltdps;

    public String generatorPkgStrbyTdps(final Map<String, String> map) throws Exception {
        String result = null;
        String serviceId = map.get("serviceid").toUpperCase();
        if(serviceId.equalsIgnoreCase("TY11")){
            result = makeTiripPackageBytdpsTY11(makeTY11(map), serviceId, "CSZJ_NEW");
        }
        if(serviceId.equalsIgnoreCase("TY03")){
            result = makeTiripPackageBytdpsTY03(makeTY03(map), serviceId, "CSZJ_NEW", map.get("NSRSBH"), map.get("OLDPASS"));
        }
        if(serviceId.equalsIgnoreCase("TY12")){
            result = makeTiripPackageBytdps(makeTY12(map), serviceId, "CSZJ_NEW");
        }
        if(serviceId.equalsIgnoreCase("TY21")){
            result = makeTiripPackageBytdps(makeTY21(map), serviceId, "CSZJ_NEW");
        }
        if(serviceId.equalsIgnoreCase("GY01")){
        	map.remove("serviceid");
            result = makeTiripPackageByGY01tdps(JSONObject.toJSONString(map), serviceId, "ABC_4000");
        }
        if(serviceId.equalsIgnoreCase("TY06")){
        	map.remove("serviceid");
            result = makeTiripPackageBytdpsTY11(makeTY06(map), serviceId, "CSZJ_NEW");
        }

        return result;
    }

	/**
     * 业务消息查询专用XML组装
     * @param contentStr
     * @param serviceId
     * @param yhmc
     * @return
     */
    public String makeTiripPackageByGY01tdps(String contentStr, String serviceId, String yhmc){
        _log.info("subPackage: \n" + contentStr);
        String mm=CommonUtils.getRandom();

        String jmmm=cryptutiltdps.b64AsymmetricEncrypt(mm);
        String content = processBusinessPkgBytdps(contentStr, "abc",mm);
        TiripPackage tiripPackage = new TiripPackage();

        // make Identity
        Identity identity = new Identity();
        identity.setServiceId(serviceId);
        identity.setChannelId(yhmc);
        identity.setPassword("888888");
        tiripPackage.setIdentity(identity);

        // make ContentControl
        ContentControl contentControl = new ContentControl();
        Control control1 = new Control();
        control1.setId("1");
        control1.setType("zip");
        control1.setImpl("Zlib");
        contentControl.addControl(control1);

        Control control2 = new Control();
        control2.setId("2");
        control2.setType("crypt");
        control2.setImpl("SBMMJM");
        contentControl.addControl(control2);

        Control control3 = new Control();
        control3.setId("3");
        control3.setType("code");
        control3.setImpl("Base64");
        contentControl.addControl(control3);
        tiripPackage.setContentControl(contentControl);

        //make RouterSession
        RouterSession routerSession = new RouterSession();
        
        ParamList paramList = new ParamList();
        paramList.setName("SENDER");
        paramList.setValue("cszjnew");
        routerSession.addParamList(paramList);
        
        ParamList paramList2 = new ParamList();
        paramList2.setName("MM");
        paramList2.setValue(jmmm);
        routerSession.addParamList(paramList2);
        tiripPackage.setRouterSession(routerSession);

        // make BussinessContent
        BusinessContent businessContent =  new BusinessContent();
        SubPackage subPackage = new SubPackage();
        subPackage.setId("1");
        subPackage.setContent(content);
        businessContent.addSubPackage(subPackage);
        tiripPackage.setBusinessContent(businessContent);
        tiripPackage.setVersion("1.0");
        String xml = null;
        try {
            xml = XmlJavaParser.parseObjectToXml(tiripPackage);
        } catch (IOException e) {
            _log.error("IOException: " + e);
            e.printStackTrace();
        } catch (MarshalException e) {
            _log.error("MarshalException: " + e);
            e.printStackTrace();
        } catch (ValidationException e) {
            _log.error("ValidationException: " + e);
            e.printStackTrace();
        }
        _log.info("tiripPackage:\n" + xml);
        return xml;
    }
    


    private static String makeTY11(final Map<String, String> map){
        com.abc12366.uc.jrxt.model.TY11Request.REQUEST request = new com.abc12366.uc.jrxt.model.TY11Request.REQUEST();
        request.setNSRSBH(map.get("NSRSBH"));
        com.abc12366.uc.jrxt.model.TY11Request.NSRJBXX nsrjbxx=new  com.abc12366.uc.jrxt.model.TY11Request.NSRJBXX();
        com.abc12366.uc.jrxt.model.TY11Request.MXXX  mxxx=new com.abc12366.uc.jrxt.model.TY11Request.MXXX();
        mxxx.setCODE("NSRSBH");
        mxxx.setVALUE(map.get("NSRSBH"));

        nsrjbxx.addMXXX(mxxx);
        request.setNSRJBXX(nsrjbxx);
        try {
            String str = XmlJavaParser.parseObjectToXml(request);
            return str;
        } catch (IOException e) {
            _log.error("IOException: " + e);
            e.printStackTrace();
        } catch (MarshalException e) {
            _log.error("MarshalException: " + e);
            e.printStackTrace();
        } catch (ValidationException e) {
            _log.error("ValidationException: " + e);
            e.printStackTrace();
        }
        return null;
    }

    public static String makeTiripPackage(String contentStr, String serviceId, String yhmc){
        _log.info("subPackage: \n" + contentStr);
        String content = processBusinessPkg(contentStr, "cszj");
        TiripPackage tiripPackage = new TiripPackage();

        // make Identity
        Identity identity = new Identity();
        identity.setServiceId(serviceId);
        identity.setChannelId(yhmc);
        identity.setPassword("qqqqqq");
        tiripPackage.setIdentity(identity);

        // make ContentControl
        ContentControl contentControl = new ContentControl();
        Control control1 = new Control();
        control1.setId("1");
        control1.setType("zip");
        control1.setImpl("Zlib");
        contentControl.addControl(control1);

        Control control2 = new Control();
        control2.setId("2");
        control2.setType("crypt");
        control2.setImpl("YHMMMJM");
        contentControl.addControl(control2);

        Control control3 = new Control();
        control3.setId("3");
        control3.setType("code");
        control3.setImpl("Base64");
        contentControl.addControl(control3);
        tiripPackage.setContentControl(contentControl);

        //make RouterSession
        RouterSession routerSession = new RouterSession();
        ParamList paramList = new ParamList();
        paramList.setName("CREATETIME");
        paramList.setValue(com.abc12366.gateway.util.DateUtils.dateToStr(new Date()));
        routerSession.addParamList(paramList);
        tiripPackage.setRouterSession(routerSession);

        // make BussinessContent
        BusinessContent businessContent =  new BusinessContent();
        SubPackage subPackage = new SubPackage();
        subPackage.setId("1");
        subPackage.setContent(content);
        businessContent.addSubPackage(subPackage);
        tiripPackage.setBusinessContent(businessContent);
        tiripPackage.setVersion("1.0");
        String xml = null;
        try {
             xml = XmlJavaParser.parseObjectToXml(tiripPackage);
        } catch (IOException e) {
            _log.error("IOException: " + e);
            e.printStackTrace();
        } catch (MarshalException e) {
            _log.error("MarshalException: " + e);
            e.printStackTrace();
        } catch (ValidationException e) {
            _log.error("ValidationException: " + e);
            e.printStackTrace();
        }
        _log.info("tiripPackage:\n" + xml);
        return xml;
    }

    public String makeTiripPackageBytdps(String contentStr, String serviceId, String yhmc){
        _log.info("subPackage: \n" + contentStr);
        String mm=CommonUtils.getRandom();
        //���ܺ������
        String jmmm=cryptutiltdps.b64AsymmetricEncrypt(mm);
        String content = processBusinessPkgBytdps(contentStr, "abc",mm);
        TiripPackage tiripPackage = new TiripPackage();

        // make Identity
        Identity identity = new Identity();
        identity.setServiceId(serviceId);
        identity.setChannelId(yhmc);
        identity.setPassword("qqqqqq");
        tiripPackage.setIdentity(identity);

        // make ContentControl
        ContentControl contentControl = new ContentControl();
        Control control1 = new Control();
        control1.setId("1");
        control1.setType("zip");
        control1.setImpl("Zlib");
        contentControl.addControl(control1);

        Control control2 = new Control();
        control2.setId("2");
        control2.setType("crypt");
        control2.setImpl("SBMMJM");
        contentControl.addControl(control2);

        Control control3 = new Control();
        control3.setId("3");
        control3.setType("code");
        control3.setImpl("Base64");
        contentControl.addControl(control3);
        tiripPackage.setContentControl(contentControl);

        //make RouterSession
        RouterSession routerSession = new RouterSession();
        ParamList paramList = new ParamList();
        paramList.setName("MM");
        paramList.setValue(jmmm);
        routerSession.addParamList(paramList);
        tiripPackage.setRouterSession(routerSession);

        // make BussinessContent
        BusinessContent businessContent =  new BusinessContent();
        SubPackage subPackage = new SubPackage();
        subPackage.setId("1");
        subPackage.setContent(content);
        businessContent.addSubPackage(subPackage);
        tiripPackage.setBusinessContent(businessContent);
        tiripPackage.setVersion("1.0");
        String xml = null;
        try {
            xml = XmlJavaParser.parseObjectToXml(tiripPackage);
        } catch (IOException e) {
            _log.error("IOException: " + e);
            e.printStackTrace();
        } catch (MarshalException e) {
            _log.error("MarshalException: " + e);
            e.printStackTrace();
        } catch (ValidationException e) {
            _log.error("ValidationException: " + e);
            e.printStackTrace();
        }
        _log.info("tiripPackage:\n" + xml);
        return xml;
    }

    public String makeTiripPackageBytdpsTY03(String contentStr, String serviceId, String yhmc, String nsrsbh, String oldpwd) throws Exception {
        _log.info("subPackage: \n" + contentStr);
        String mm=CommonUtils.getRandom();
        //���ܺ������
        String jmmm=cryptutiltdps.b64AsymmetricEncrypt(mm);
        String content = processBusinessPkgBytdps(contentStr, "abc",mm);
        TiripPackage tiripPackage = new TiripPackage();

        // make Identity
        Identity identity = new Identity();
        identity.setServiceId(serviceId);
        identity.setChannelId(yhmc);
        identity.setPassword(oldpwd);
        tiripPackage.setIdentity(identity);

        // make ContentControl
        ContentControl contentControl = new ContentControl();
        Control control1 = new Control();
        control1.setId("1");
        control1.setType("zip");
        control1.setImpl("Zlib");
        contentControl.addControl(control1);

        Control control2 = new Control();
        control2.setId("2");
        control2.setType("crypt");
        control2.setImpl("SBMMJM");
        contentControl.addControl(control2);

        Control control3 = new Control();
        control3.setId("3");
        control3.setType("code");
        control3.setImpl("Base64");
        contentControl.addControl(control3);
        tiripPackage.setContentControl(contentControl);

        //make RouterSession
        RouterSession routerSession = new RouterSession();
        ParamList paramList = new ParamList();
        ParamList paramList2 = new ParamList();
        paramList.setName("MM");
        paramList.setValue(jmmm);
        paramList2.setName("SENDER");
        paramList2.setValue(nsrsbh);
        routerSession.addParamList(paramList2);
        routerSession.addParamList(paramList);
        tiripPackage.setRouterSession(routerSession);

        // make BussinessContent
        BusinessContent businessContent =  new BusinessContent();
        SubPackage subPackage = new SubPackage();
        subPackage.setId("1");
        subPackage.setContent(content);
        businessContent.addSubPackage(subPackage);
        tiripPackage.setBusinessContent(businessContent);
        tiripPackage.setVersion("1.0");
        String xml = null;
        try {
            xml = XmlJavaParser.parseObjectToXml(tiripPackage);
        } catch (IOException e) {
            _log.error("IOException: " + e);
            e.printStackTrace();
        } catch (MarshalException e) {
            _log.error("MarshalException: " + e);
            e.printStackTrace();
        } catch (ValidationException e) {
            _log.error("ValidationException: " + e);
            e.printStackTrace();
        }
        _log.info("tiripPackage:\n" + xml);
        return xml;
    }

    public String makeTiripPackageBytdpsTY11(String contentStr, String serviceId, String yhmc){
        _log.info("subPackage: \n" + contentStr);
        String mm=CommonUtils.getRandom();
        //���ܺ������
        String jmmm=cryptutiltdps.b64AsymmetricEncrypt(mm);
        String content = processBusinessPkgBytdps(contentStr, "abc",mm);
        TiripPackage tiripPackage = new TiripPackage();

        // make Identity
        Identity identity = new Identity();
        identity.setServiceId(serviceId);
        identity.setChannelId(yhmc);
        identity.setPassword("qqqqqq");
        tiripPackage.setIdentity(identity);

        // make ContentControl
        ContentControl contentControl = new ContentControl();
        Control control1 = new Control();
        control1.setId("1");
        control1.setType("zip");
        control1.setImpl("Zlib");
        contentControl.addControl(control1);

        Control control2 = new Control();
        control2.setId("2");
        control2.setType("crypt");
        control2.setImpl("SBMMJM");
        contentControl.addControl(control2);

        Control control3 = new Control();
        control3.setId("3");
        control3.setType("code");
        control3.setImpl("Base64");
        contentControl.addControl(control3);
        tiripPackage.setContentControl(contentControl);

        //make RouterSession
        RouterSession routerSession = new RouterSession();
        ParamList paramList = new ParamList();
        paramList.setName("MM");
        paramList.setValue(jmmm);
        routerSession.addParamList(paramList);
        tiripPackage.setRouterSession(routerSession);

        // make BussinessContent
        BusinessContent businessContent =  new BusinessContent();
        SubPackage subPackage = new SubPackage();
        subPackage.setId("1");
        subPackage.setContent(content);
        businessContent.addSubPackage(subPackage);
        tiripPackage.setBusinessContent(businessContent);
        tiripPackage.setVersion("1.0");
        String xml = null;
        try {
            xml = XmlJavaParser.parseObjectToXml(tiripPackage);
        } catch (IOException e) {
            _log.error("IOException: " + e);
            e.printStackTrace();
        } catch (MarshalException e) {
            _log.error("MarshalException: " + e);
            e.printStackTrace();
        } catch (ValidationException e) {
            _log.error("ValidationException: " + e);
            e.printStackTrace();
        }
        _log.info("tiripPackage:\n" + xml);
        return xml;
    }

    private static String processBusinessPkg(String pkgStr, String name){
        HashMap<String, String> map = new HashMap<String, String>();
        map.put(name, pkgStr);
        return processBusinessPkg(map);
    }

    private static String processBusinessPkg(HashMap<String, String> map){
        try {
            ZipUtil zu = new ZipUtil();
            byte[] bytes2 = zu.compress(map);

            CryptUtil cu = new CryptUtil();
            byte[] i = cu.encryptContent(bytes2, "88888888");

            CodeUtil code = new CodeUtil();
            return code.encodeContent(i);
        }catch (Exception e){
            _log.error("Exception: " + e);
            e.printStackTrace();
        }
        return null;
    }

    private String processBusinessPkgBytdps(String pkgStr, String name,String mm){
        HashMap<String, String> map = new HashMap<String, String>();
        map.put(name, pkgStr);
        return processBusinessPkgBytdps(map,mm);
    }

    private  String processBusinessPkgBytdps(HashMap<String, String> map,String mm){
        try {
            byte[] bytes2 = zipUtiltdps.compress(map);

            byte[] i = cryptutiltdps.encryptContent(bytes2, mm.getBytes());

            return codeUtiltdps.encodeContent(i);
        }catch (Exception e){
            _log.error("Exception: " + e);
            e.printStackTrace();
        }
        return null;
    }
    public  Map<String, String> processBackBusinessPkgBytdps(TiripPackage tiripPackage){
        Map<String, String> map = new HashMap<>();
        String returnCode = tiripPackage.getReturnState().getReturnCode();

        if(returnCode.equals("00000000") && tiripPackage.getBusinessContent().getSubPackageCount() > 0) {
            String contents = tiripPackage.getBusinessContent().getSubPackage(0).getContent();
            try {
                byte[] contentBytes = codeUtiltdps.decodeContent("BASE64", contents);
                com.abc12366.uc.jrxt.model.tiripPackage.ParamList[] paramlist=tiripPackage.getRouterSession().getParamList();
                for(ParamList param :paramlist){
                    if("MM".equals(param.getName())&&!"".equals(param.getValue())){
                        contentBytes = cryptutiltdps.decryptContent(cryptutiltdps.b64AsymmetricDecrypt(param.getValue()), "SBMMJM", contentBytes);
                    }
                }
                map = zipUtiltdps.decompress(contentBytes);
            } catch (Exception e) {
                _log.error("Exception: " + e);
                throw new ServiceException(4631);
            }
        }
        map.put("rescode", returnCode);
        map.put("message", tiripPackage.getReturnState().getReturnMessage());
        return map;
    }

    public static Map<String, String> processBackBusinessPkg(TiripPackage tiripPackage){
        Map<String, String> map = new HashMap<String, String>();
        String returnCode = tiripPackage.getReturnState().getReturnCode();

        if(returnCode.equals("00000000") && tiripPackage.getBusinessContent().getSubPackageCount() > 0) {
            String contents = tiripPackage.getBusinessContent().getSubPackage(0).getContent();
            try {
                CodeUtil code = new CodeUtil();
                byte[] contentBytes = code.decodeContent("BASE64", contents);

                CryptUtil cu = new CryptUtil();
                contentBytes = cu.decryptContent("88888888", "YHMMMJM", contentBytes);

                ZipUtil zu = new ZipUtil();
                map = zu.decompress(contentBytes);
            } catch (Exception e) {
                _log.error("Exception: " + e);
                e.printStackTrace();
            }
        }
        map.put("rescode", returnCode);
        map.put("message", tiripPackage.getReturnState().getReturnMessage());
        return map;
    }

    private static String makeTY03(final Map<String, String> map){
        //com.abc12366.uc.tdps.vo.nsraqxxSzRequest.
        com.abc12366.uc.tdps.vo.nsraqxxSzRequest.AQSZS request = new com.abc12366.uc.tdps.vo.nsraqxxSzRequest.AQSZS();
        com.abc12366.uc.tdps.vo.nsraqxxSzRequest.AQSZ aqsz = new com.abc12366.uc.tdps.vo.nsraqxxSzRequest.AQSZ();
        aqsz.setNSRSBH(map.get("NSRSBH"));
        aqsz.setJMTZ("01");
        com.abc12366.uc.tdps.vo.nsraqxxSzRequest.MMXX  mmxx= new com.abc12366.uc.tdps.vo.nsraqxxSzRequest.MMXX();
        mmxx.setOLDPASS(map.get("OLDPASS"));
        mmxx.setNEWPASS(map.get("NEWPASS"));
        aqsz.setMMXX(mmxx);
        request.addAQSZ(aqsz);
        try {
            String str = XmlJavaParser.parseObjectToXml(request);
            return str;
        } catch (IOException e) {
            _log.error("IOException: " + e);
            e.printStackTrace();
        } catch (MarshalException e) {
            _log.error("MarshalException: " + e);
            e.printStackTrace();
        } catch (ValidationException e) {
            _log.error("ValidationException: " + e);
            e.printStackTrace();
        }
        return null;
    }

    private static String makeTY12(final Map<String, String> map){
        com.abc12366.uc.jrxt.model.TY11Request.REQUEST request = new com.abc12366.uc.jrxt.model.TY11Request.REQUEST();
        request.setNSRSBH(map.get("NSRSBH"));
        com.abc12366.uc.jrxt.model.TY11Request.NSRJBXX nsrjbxx=new  com.abc12366.uc.jrxt.model.TY11Request.NSRJBXX();
        com.abc12366.uc.jrxt.model.TY11Request.MXXX  mxxx=new com.abc12366.uc.jrxt.model.TY11Request.MXXX();
        mxxx.setCODE("NSRSBH");
        mxxx.setVALUE(map.get("NSRSBH"));
        nsrjbxx.addMXXX(mxxx);
        request.setNSRJBXX(nsrjbxx);
        try {
            String str = XmlJavaParser.parseObjectToXml(request);
            return str;
        } catch (IOException e) {
            _log.error("IOException: " + e);
            e.printStackTrace();
        } catch (MarshalException e) {
            _log.error("MarshalException: " + e);
            e.printStackTrace();
        } catch (ValidationException e) {
            _log.error("ValidationException: " + e);
            e.printStackTrace();
        }
        return null;
    }

    private static String makeTY21(final Map<String, String> map){
        //com.abc12366.uc.tdps.vo.nsraqxxSzRequest.
        com.abc12366.uc.jrxt.model.TY11Request.REQUEST request = new com.abc12366.uc.jrxt.model.TY11Request.REQUEST();
        request.setNSRSBH(map.get("nsrsbh"));
        com.abc12366.uc.jrxt.model.TY11Request.NSRJBXX nsrjbxx=new  com.abc12366.uc.jrxt.model.TY11Request.NSRJBXX();
        com.abc12366.uc.jrxt.model.TY11Request.MXXX  mxxx=new com.abc12366.uc.jrxt.model.TY11Request.MXXX();
        com.abc12366.uc.jrxt.model.TY11Request.MXXX  mxxx2=new com.abc12366.uc.jrxt.model.TY11Request.MXXX();
        com.abc12366.uc.jrxt.model.TY11Request.MXXX  mxxx3=new com.abc12366.uc.jrxt.model.TY11Request.MXXX();
        mxxx.setCODE("NSRSBH");
        mxxx.setVALUE(map.get("nsrsbh"));
        nsrjbxx.addMXXX(mxxx);
        mxxx2.setCODE("USERID");
        mxxx2.setVALUE(map.get("userid"));
        nsrjbxx.addMXXX(mxxx2);
        mxxx3.setCODE("FWMM");
        mxxx3.setVALUE(map.get("fwmm"));
        nsrjbxx.addMXXX(mxxx3);
        request.setNSRJBXX(nsrjbxx);
        try {
            String str = XmlJavaParser.parseObjectToXml(request);
            return str;
        } catch (IOException e) {
            _log.error("IOException: " + e);
            e.printStackTrace();
        } catch (MarshalException e) {
            _log.error("MarshalException: " + e);
            e.printStackTrace();
        } catch (ValidationException e) {
            _log.error("ValidationException: " + e);
            e.printStackTrace();
        }
        return null;
    }
    
    private String makeTY06(Map<String, String> map) {
    	com.abc12366.uc.jrxt.model.TY11Request.REQUEST request = new com.abc12366.uc.jrxt.model.TY11Request.REQUEST();
        request.setNSRSBH(map.get("nsrsbh"));
        com.abc12366.uc.jrxt.model.TY11Request.NSRJBXX nsrjbxx=new  com.abc12366.uc.jrxt.model.TY11Request.NSRJBXX();
        com.abc12366.uc.jrxt.model.TY11Request.MXXX  mxxx=new com.abc12366.uc.jrxt.model.TY11Request.MXXX();
        com.abc12366.uc.jrxt.model.TY11Request.MXXX  mxxx2=new com.abc12366.uc.jrxt.model.TY11Request.MXXX();
        com.abc12366.uc.jrxt.model.TY11Request.MXXX  mxxx3=new com.abc12366.uc.jrxt.model.TY11Request.MXXX();
        com.abc12366.uc.jrxt.model.TY11Request.MXXX  mxxx4=new com.abc12366.uc.jrxt.model.TY11Request.MXXX();
        com.abc12366.uc.jrxt.model.TY11Request.MXXX  mxxx5=new com.abc12366.uc.jrxt.model.TY11Request.MXXX();
        com.abc12366.uc.jrxt.model.TY11Request.MXXX  mxxx6=new com.abc12366.uc.jrxt.model.TY11Request.MXXX();
        com.abc12366.uc.jrxt.model.TY11Request.MXXX  mxxx7=new com.abc12366.uc.jrxt.model.TY11Request.MXXX();
        com.abc12366.uc.jrxt.model.TY11Request.MXXX  mxxx8=new com.abc12366.uc.jrxt.model.TY11Request.MXXX();
        com.abc12366.uc.jrxt.model.TY11Request.MXXX  mxxx9=new com.abc12366.uc.jrxt.model.TY11Request.MXXX();
        mxxx.setCODE("RJDQR");
        mxxx.setVALUE(map.get("yqdqr"));
        nsrjbxx.addMXXX(mxxx);
        
        mxxx2.setCODE("YQDQR");
        mxxx2.setVALUE(map.get("yqdqr"));
        nsrjbxx.addMXXX(mxxx2);
        
        mxxx3.setCODE("NSRLX");
        mxxx3.setVALUE(map.get("nsrlx"));
        nsrjbxx.addMXXX(mxxx3);
        
        mxxx4.setCODE("ZXBZ");
        mxxx4.setVALUE("0");
        nsrjbxx.addMXXX(mxxx4);
        
        mxxx5.setCODE("GTBZ");
        mxxx5.setVALUE("0");
        nsrjbxx.addMXXX(mxxx5);
        
        mxxx6.setCODE("RJBB");
        mxxx6.setVALUE("20");
        nsrjbxx.addMXXX(mxxx6);
        
        mxxx7.setCODE("KHSJ");
        mxxx7.setVALUE(map.get("khsj"));
        nsrjbxx.addMXXX(mxxx7);
        
        mxxx8.setCODE("SCBZ");
        mxxx8.setVALUE("0");
        nsrjbxx.addMXXX(mxxx8);
        
        mxxx9.setCODE("TSYHLX");
        mxxx9.setVALUE("01");
        nsrjbxx.addMXXX(mxxx9);
        request.setNSRJBXX(nsrjbxx);
        try {
            String str = XmlJavaParser.parseObjectToXml(request);
            return str;
        } catch (IOException e) {
            _log.error("IOException: " + e);
            e.printStackTrace();
        } catch (MarshalException e) {
            _log.error("MarshalException: " + e);
            e.printStackTrace();
        } catch (ValidationException e) {
            _log.error("ValidationException: " + e);
            e.printStackTrace();
        }
        return null;
	}


}
