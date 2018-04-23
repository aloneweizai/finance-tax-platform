package com.abc12366.uc.webservice;

import com.abc12366.gateway.component.SpringCtxHolder;
import com.abc12366.uc.jrxt.model.tiripPackage.TiripPackage;
import com.abc12366.uc.jrxt.model.util.PkgUtil;
import com.abc12366.uc.jrxt.model.util.XmlJavaParser;
import com.abc12366.uc.model.job.DzsbJob;
import com.abc12366.uc.model.job.DzsbXxInfo;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * 电子申报处理业务入口
 *
 * @author lijun <ljun51@outlook.com>
 * @create 2017-06-07 4:02 PM
 * @since 1.0.0
 */
@Component("client")
@Scope("prototype")
public class AcceptClient {

    private static final Logger LOGGER = LoggerFactory.getLogger(AcceptClient.class);

    @Autowired
    private PkgUtil pkgutil;

    public Map<String, String> process(Map<String, String> map) {
        String target = SpringCtxHolder.getProperty("tdps.api.url") + "/tdps-accept/services/AcceptService?wsdl";

        try {
            AcceptServiceStub.AcceptRequest request = new AcceptServiceStub.AcceptRequest();
            request.setTiripPkgStr(pkgutil.generatorPkgStrbyTdps(map));
            AcceptServiceStub stub = new AcceptServiceStub(target);

            AcceptServiceStub.AcceptResponse processResponse = stub.accept(request);
            String response = processResponse.getTiripPkgStr();
            TiripPackage tiripPackage = (TiripPackage) XmlJavaParser.parseXmlToObject(TiripPackage.class, response);
            return pkgutil.processBackBusinessPkgBytdps(tiripPackage);
        } catch (Exception e) {
            e.printStackTrace();
            LOGGER.error("{}", e);
        }
        return null;
    }
    
    /**
     * 业务数据获取
     * @param map  参数
     * @return
     */
    public DzsbJob processYw(Map<String, String> map) {
    	DzsbJob dzsbJob=new DzsbJob();
    	dzsbJob.setYwlx(map.get("ywid").toUpperCase());
        String target =  SpringCtxHolder.getProperty("tdps.api.url") +"/tdps-accept/services/AcceptService?wsdl";
        try {
            AcceptServiceStub.AcceptRequest request = new AcceptServiceStub.AcceptRequest();
            request.setTiripPkgStr(pkgutil.generatorPkgStrbyTdps(map));
            AcceptServiceStub stub = new AcceptServiceStub(target);

            AcceptServiceStub.AcceptResponse processResponse = stub.accept(request);
            String response = processResponse.getTiripPkgStr();
            TiripPackage tiripPackage = (TiripPackage) XmlJavaParser.parseXmlToObject(TiripPackage.class, response);
            Map<String, String> resMap= pkgutil.processBackBusinessPkgBytdps(tiripPackage);
            dzsbJob.setRescode(resMap.get("rescode"));	
            dzsbJob.setMessage(resMap.get("message"));
            
            if("00000000".equals(dzsbJob.getRescode())){
            	String fileName=tiripPackage.getBusinessContent().getSubPackage(0).getParamList(0).getValue();
            	String value=resMap.get(fileName);
            	String jonstr=StringUtils.substringBetween(value,"<string>", "</string>");
            	JSONObject object=JSONObject.parseObject(jonstr);
            	dzsbJob.setIsExistData(object.getBoolean("isExistData"));
            	dzsbJob.setDataList(object.getJSONArray("dataList").toJavaList(DzsbXxInfo.class));
            }
        } catch (Exception e) {
            e.printStackTrace();
            LOGGER.error("{}", e);
            dzsbJob.setRescode("999999");
            dzsbJob.setMessage(e.getMessage());
        }
        return dzsbJob;
    }
}
