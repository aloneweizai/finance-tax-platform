package com.abc12366.bangbang.service.impl;

import com.abc12366.bangbang.mapper.db1.CurriculumMapper;
import com.abc12366.bangbang.mapper.db1.CurriculumStudyMapper;
import com.abc12366.bangbang.mapper.db2.CurriculumStudyRoMapper;
import com.abc12366.bangbang.model.curriculum.CurriculumStudy;
import com.abc12366.bangbang.model.curriculum.bo.CurriculumStudyBo;
import com.abc12366.bangbang.service.CurrStudyService;
import com.abc12366.bangbang.service.CurriculumBrowserWatchService;
import com.abc12366.gateway.component.SpringCtxHolder;
import com.abc12366.gateway.exception.ServiceException;
import com.abc12366.gateway.util.RestTemplateUtil;
import com.abc12366.gateway.util.TaskConstant;
import com.abc12366.gateway.util.Utils;
import net.sf.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * Created by xieyanmao on 2017/8/11.
 */
@Service
public class CurrStudyServiceImpl implements CurrStudyService {
    private static final Logger LOGGER = LoggerFactory.getLogger(CurrStudyServiceImpl.class);

    @Autowired
    private CurriculumStudyMapper studyMapper;

    @Autowired
    private CurriculumStudyRoMapper studyRoMapper;

    @Autowired
    private CurriculumMapper curriculumMapper;

    @Autowired
    private RestTemplateUtil restTemplateUtil;

    @Autowired
    private CurriculumBrowserWatchService curriculumBrowserWatchServiceImpl;

    @Override
    public List<CurriculumStudyBo> selectList(Map<String,Object> map) {
        List<CurriculumStudyBo> studyBoList;
        try {
            //查询课程学习列表
            studyBoList = studyRoMapper.selectList(map);
        } catch (Exception e) {
            LOGGER.error("查询课程学习列表信息异常：{}", e);
            throw new ServiceException(4360);
        }
        return studyBoList;
    }

    @Override
    public CurriculumStudyBo save(CurriculumStudyBo studyBo,HttpServletRequest request) {
        try {
            JSONObject jsonStu = JSONObject.fromObject(studyBo);
            LOGGER.info("新增课程学习信息:{}", jsonStu.toString());
            studyBo.setStudyTime(new Date());
            //保存课程学习信息
            String uuid = UUID.randomUUID().toString().replace("-", "");
            CurriculumStudy study = new CurriculumStudy();
            studyBo.setStudyId(uuid);
            BeanUtils.copyProperties(studyBo, study);
            studyMapper.insert(study);
            String url = SpringCtxHolder.getProperty("abc12366.uc.url") + "/todo/task/do/award/{userId}/{taskCode}";
            String responseStr;
            String userId = Utils.getUserId();
            String sysTaskId = TaskConstant.SYS_TASK_COURSE_LEARNING_CODE;
            responseStr = restTemplateUtil.send(url, HttpMethod.POST, request,userId,sysTaskId);
//            System.out.println(responseStr);

            curriculumMapper.updateWatchsDay(studyBo.getCurriculumId());
            curriculumBrowserWatchServiceImpl.updateWatchNum(studyBo.getCurriculumId());
        } catch (Exception e) {
            LOGGER.error("新增课程学习信息异常：{}", e);
            throw new ServiceException(4362);
        }

        return studyBo;
    }

    @Override
    public CurriculumStudyBo selectStudy(String studyId) {
        CurriculumStudyBo studyBo = new CurriculumStudyBo();
        try {
            LOGGER.info("查询单个课程学习信息:{}", studyId);
            //查询课程学习信息
            CurriculumStudy study = studyRoMapper.selectByPrimaryKey(studyId);
            BeanUtils.copyProperties(study, studyBo);
        } catch (Exception e) {
            LOGGER.error("查询单个课程学习信息异常：{}", e);
            throw new ServiceException(4361);
        }
        return studyBo;
    }

    @Override
    public CurriculumStudyBo update(CurriculumStudyBo studyBo) {
        //更新课程学习信息
        CurriculumStudy study = new CurriculumStudy();
        try {
            JSONObject jsonStu = JSONObject.fromObject(studyBo);
            LOGGER.info("更新课程学习信息:{}", jsonStu.toString());
            BeanUtils.copyProperties(studyBo, study);
            studyMapper.updateByPrimaryKeySelective(study);
        } catch (Exception e) {
            LOGGER.error("更新课程学习信息异常：{}", e);
            throw new ServiceException(4363);
        }
        return studyBo;
    }

    @Override
    public String updateStatus(String studyId,String status) {
        //更新课程学习信息
        try {
//            LOGGER.info("更新课程状态信息:{}", StudyId+","+status);
//            Curriculum curriculum = new Curriculum();
//            curriculum.setCurriculumId(curriculumId);
//            curriculum.setStatus(Integer.parseInt(status));
//            curriculum.setUpdateTime(new Date());
//            //1为发布
//            if("1".equals(status)){
//                curriculum.setIssueTime(new Date());
//            }else{
//                curriculum.setIssueTime(null);
//            }
//            StudyMapper.updateStatus(curriculum);
        } catch (Exception e) {
            LOGGER.error("更新课件信息异常：{}", e);
            throw new ServiceException(4363);
        }
        return "";
    }

    @Transactional("db1TxManager")
    @Override
    public String delete(String studyId) {
        try {
            LOGGER.info("删除课程学习信息:{}", studyId);
            studyMapper.deleteByPrimaryKey(studyId);
        } catch (Exception e) {
            LOGGER.error("删除课程学习信息异常：{}", e);
            throw new ServiceException(4364);
        }
        return "";
    }

    @Override
    public int selectStudyCnt(Map<String,Object> map) {
        int cnt = 0;
        try {
            //查询学习次数
            cnt = studyRoMapper.selectStudyCnt(map);
        } catch (Exception e) {
            LOGGER.error("查询学习次数信息异常：{}", e);
            throw new ServiceException(4360);
        }
        return cnt;
    }

}
