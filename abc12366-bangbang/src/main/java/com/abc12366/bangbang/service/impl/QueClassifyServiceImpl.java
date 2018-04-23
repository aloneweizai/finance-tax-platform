package com.abc12366.bangbang.service.impl;

import com.abc12366.bangbang.mapper.db1.QuestionClassifyMapper;
import com.abc12366.bangbang.mapper.db1.QuestionClassifyTagMapper;
import com.abc12366.bangbang.mapper.db2.QuestionClassifyRoMapper;
import com.abc12366.bangbang.mapper.db2.QuestionClassifyTagRoMapper;
import com.abc12366.bangbang.model.question.QuestionClassify;
import com.abc12366.bangbang.model.question.QuestionClassifyStatistics;
import com.abc12366.bangbang.model.question.QuestionClassifyTag;
import com.abc12366.bangbang.model.question.bo.QuestionClassifyBo;
import com.abc12366.bangbang.model.question.bo.QuestionClassifyTagBo;
import com.abc12366.bangbang.service.QueClassifyService;
import com.abc12366.gateway.exception.ServiceException;
import net.sf.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**
 * Created by xieyanmao on 2017/9/14.
 */
@Service
public class QueClassifyServiceImpl implements QueClassifyService {
    private static final Logger LOGGER = LoggerFactory.getLogger(QueClassifyServiceImpl.class);

    @Autowired
    private QuestionClassifyMapper classifyMapper;

    @Autowired
    private QuestionClassifyRoMapper classifyRoMapper;

    @Autowired
    private QuestionClassifyTagMapper tagMapper;

    @Autowired
    private QuestionClassifyTagRoMapper tagRoMapper;

    @Override
    public List<QuestionClassifyBo> selectList(Map<String,Object> map) {
        List<QuestionClassifyBo> classifyBoList;
        try {
            //查询问题分类列表
            classifyBoList = classifyRoMapper.selectList(map);

        } catch (Exception e) {
            LOGGER.error("查询问题分类信息异常：{}", e);
            throw new ServiceException(6120);
        }
        return classifyBoList;
    }

    @Override
    public QuestionClassifyBo save(QuestionClassifyBo classifyBo) {
        try {
            JSONObject jsonStu = JSONObject.fromObject(classifyBo);
            LOGGER.info("新增问题分类信息:{}", jsonStu.toString());
            //保存问题分类信息
            String uuid = UUID.randomUUID().toString().replace("-", "");
            String code = "";

            String classifyCode = "";

            String parentCode = classifyBo.getParentCode();

            if(parentCode == null || "".equals(parentCode)){
                parentCode = "0";
                classifyBo.setParentCode("0");
            }

            for(int i=0;i<20;i++){
                code = this.genCodes(6);
                classifyCode = parentCode + code;
                int cnt = classifyRoMapper.selectClassifyCnt(classifyCode);
                if(cnt ==0){
                    break;
                }
            }

            QuestionClassify classify = new QuestionClassify();
            classifyBo.setClassifyId(uuid);
            classifyBo.setClassifyCode(classifyCode);
            BeanUtils.copyProperties(classifyBo, classify);


            List<QuestionClassifyTag> tagList = classifyBo.getTagList();

            if(tagList != null){
                for(QuestionClassifyTag tag :tagList){
                    tag.setId(UUID.randomUUID().toString().replace("-", ""));
                    tag.setClassifyId(classifyCode);
                    tagMapper.insert(tag);
                }
            }


            classifyMapper.insert(classify);
        } catch (Exception e) {
            LOGGER.error("新增问题分类信息异常：{}", e);
            throw new ServiceException(6122);
        }

        return classifyBo;
    }

    @Override
    public QuestionClassifyBo selectClassify(String classifyCode) {
        QuestionClassifyBo classifyBo = new QuestionClassifyBo();
        try {
            LOGGER.info("查询单个问题分类信息:{}", classifyCode);
            //查询课程分类信息
            QuestionClassify classify = classifyRoMapper.selectByPrimaryKey(classifyCode);
            List<QuestionClassifyTag> tagList = tagRoMapper.selectList(classifyCode);
            BeanUtils.copyProperties(classify, classifyBo);
            classifyBo.setTagList(tagList);
        } catch (Exception e) {
            LOGGER.error("查询单个问题分类信息异常：{}", e);
            throw new ServiceException(6121);
        }
        return classifyBo;
    }

    @Override
    public List<QuestionClassifyTagBo> selectClassifyTagList(String classifyCode) {
        List<QuestionClassifyTagBo> classifyTagBoList;
        try {
            LOGGER.info("查询单个问题分类标签信息:{}", classifyCode);
            //查询课程分类标签信息
            Map<String, Object> dataMap = new HashMap<>();
            dataMap.put("classifyId", classifyCode);//
            classifyTagBoList = tagRoMapper.selectClassifyTagList(dataMap);
        } catch (Exception e) {
            LOGGER.error("查询单个问题分类标签信息异常：{}", e);
            throw new ServiceException(6121);
        }
        return classifyTagBoList;
    }

    /* 修改分类信息，（不更新分类标签关联） */
    @Override
    public QuestionClassifyBo update(QuestionClassifyBo classifyBo) {
        //更新问题分类信息
        QuestionClassify classify = new QuestionClassify();
        try {
            JSONObject jsonStu = JSONObject.fromObject(classifyBo);
            LOGGER.info("更新问题分类信息:{}", jsonStu.toString());
            BeanUtils.copyProperties(classifyBo, classify);

            classifyMapper.updateByPrimaryKeySelective(classify);
        } catch (Exception e) {
            LOGGER.error("更新问题分类信息异常：{}", e);
            throw new ServiceException(6123);
        }
        return classifyBo;
    }

    @Override
    public String updateStatus(String classifyId,String status) {
        //更新问题分类信息
        try {

        } catch (Exception e) {
            LOGGER.error("更新问题分类信息异常：{}", e);
            throw new ServiceException(6123);
        }
        return "";
    }

    @Transactional("db1TxManager")
    @Override
    public String delete(String classifyCode) {
        LOGGER.info("删除问题分类信息:{}", classifyCode);
        try {
            tagMapper.deleteByPrimaryKey(classifyCode);
            classifyMapper.deleteByPrimaryKey(classifyCode);
        } catch (Exception e) {
            LOGGER.error("删除问题分类异常：{}", e);
            throw new ServiceException(6124);
        }
        return "";
    }

    /*修改分类标签关联关系*/
    @Transactional("db1TxManager")
    @Override
    public void updateClassifyTag(QuestionClassifyBo classifyBo) {

        tagMapper.deleteByPrimaryKey(classifyBo.getClassifyCode());

        List<QuestionClassifyTag> tagList = classifyBo.getTagList();

        if(tagList != null){
            for(QuestionClassifyTag tag :tagList){
                tag.setId(UUID.randomUUID().toString().replace("-", ""));
                tag.setClassifyId(classifyBo.getClassifyCode());
                tag.setTagId(tag.getTagId());
                tagMapper.insert(tag);
            }
        }
    }

    @Override
    public List<QuestionClassifyStatistics> selectClassifyStatistics(Map<String, Object> map) {
        return classifyRoMapper.selectClassifyStatistics(map);
    }

    public String genCodes(int length){

        String val = "";
        Random random = new Random();
        for(int i = 0; i < length; i++)
        {
            String charOrNum = random.nextInt(2) % 2 == 0 ? "char" : "num"; // 输出字母还是数字

            if("char".equalsIgnoreCase(charOrNum)) // 字符串
            {
                int choice = random.nextInt(2) % 2 == 0 ? 65 : 97; //取得大写字母还是小写字母
                val += (char) (choice + random.nextInt(26));
            }
            else if("num".equalsIgnoreCase(charOrNum)) // 数字
            {
                val += String.valueOf(random.nextInt(10));
            }
        }
        val=val.toLowerCase();

        return val;
    }

}
