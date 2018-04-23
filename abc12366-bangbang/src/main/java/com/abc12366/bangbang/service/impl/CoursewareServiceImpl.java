package com.abc12366.bangbang.service.impl;

import com.abc12366.bangbang.mapper.db1.CurriculumCoursewareMapper;
import com.abc12366.bangbang.mapper.db2.CurriculumCoursewareRoMapper;
import com.abc12366.bangbang.model.curriculum.CurriculumCourseware;
import com.abc12366.bangbang.model.curriculum.bo.CurriculumCoursewareBo;
import com.abc12366.bangbang.service.CoursewareService;
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
 * Created by xieyanmao on 2017/8/10.
 */
@Service
public class CoursewareServiceImpl implements CoursewareService {
    private static final Logger LOGGER = LoggerFactory.getLogger(CoursewareServiceImpl.class);

    @Autowired
    private CurriculumCoursewareMapper coursewareMapper;

    @Autowired
    private CurriculumCoursewareRoMapper coursewareRoMapper;

    @Override
    public List<CurriculumCoursewareBo> selectList(Map<String,Object> map) {
        List<CurriculumCoursewareBo> coursewareBoList;
        try {
            //查询课件列表
            coursewareBoList = coursewareRoMapper.selectList(map);
        } catch (Exception e) {
            LOGGER.error("查询课件列表信息异常：{}", e);
            throw new ServiceException(4330);
        }
        return coursewareBoList;
    }

    @Override
    public CurriculumCoursewareBo save(CurriculumCoursewareBo coursewareBo) {
        Map<String, Object> dataMap1 = new HashMap<>();
        dataMap1.put("curriculumId", coursewareBo.getCurriculumId());
        dataMap1.put("title", coursewareBo.getTitle());
        int cnt1 = coursewareRoMapper.selectCoursewareCnt(dataMap1);
        if(cnt1 > 0){
            throw new ServiceException(4335);
        }

        Map<String, Object> dataMap2 = new HashMap<>();
        dataMap2.put("curriculumId", coursewareBo.getCurriculumId());
        dataMap2.put("chapterId", coursewareBo.getChapterId());
        dataMap2.put("coursewareSeq", coursewareBo.getCoursewareSeq());
        int cnt2 = coursewareRoMapper.selectCoursewareCnt(dataMap2);
        if(cnt2 > 0){
            throw new ServiceException(4336);
        }


        try {
            JSONObject jsonStu = JSONObject.fromObject(coursewareBo);
            LOGGER.info("新增课件信息:{}", jsonStu.toString());

            coursewareBo.setCreateTime(new Date());
            coursewareBo.setUpdateTime(new Date());
            //保存课件信息
            String uuid = UUID.randomUUID().toString().replace("-", "");
            CurriculumCourseware courseware = new CurriculumCourseware();
            coursewareBo.setCoursewareId(uuid);
            BeanUtils.copyProperties(coursewareBo, courseware);
            coursewareMapper.insert(courseware);
        } catch (Exception e) {
            LOGGER.error("新增课件信息异常：{}", e);
            throw new ServiceException(4332);
        }

        return coursewareBo;
    }

    @Override
    public CurriculumCoursewareBo selectCourseware(String coursewareId) {
        CurriculumCoursewareBo coursewareBo = new CurriculumCoursewareBo();
        try {
            LOGGER.info("查询单个课件信息:{}", coursewareId);
            //查询课件信息
            CurriculumCourseware courseware = coursewareRoMapper.selectByPrimaryKey(coursewareId);
            BeanUtils.copyProperties(courseware, coursewareBo);
        } catch (Exception e) {
            LOGGER.error("查询单个课件信息异常：{}", e);
            throw new ServiceException(4331);
        }
        return coursewareBo;
    }

    @Override
    public CurriculumCoursewareBo selectCoursewarebf(String coursewareId,String userId) {
        CurriculumCoursewareBo coursewareBo = new CurriculumCoursewareBo();

        LOGGER.info("查询单个课件信息:{}", coursewareId);
        //查询用户是否有权限播放
        CurriculumCourseware courseware = coursewareRoMapper.selectByPrimaryKey(coursewareId);
        BeanUtils.copyProperties(courseware, coursewareBo);

        int flag = 0;
        //查询课程是否免费
        int cnt1 = coursewareRoMapper.selectCurriculumisFree(coursewareId);
        if(cnt1 == 1){
            flag = 1;
        }
        //查询课件是否免费
        if(flag == 0){
            int cnt2 = coursewareRoMapper.selectCoursewareisFree(coursewareId);
            if(cnt2 == 1){
                flag = 1;
            }
        }

        //查询用户会员等级是否可观看
        if(flag == 0){
            Map<String, Object> dataMap = new HashMap<>();
            dataMap.put("curriculumId", courseware.getCurriculumId());
            dataMap.put("userId", userId);
            int cnt3 = coursewareRoMapper.selectGradeWatch(dataMap);
            if(cnt3 > 0){
                flag = 1;
            }
        }


        if(flag == 0){
            //课程收费，请购买课程
            throw new ServiceException(4339);
        }

        return coursewareBo;

    }

    @Override
    public CurriculumCoursewareBo update(CurriculumCoursewareBo coursewareBo) {
        Map<String, Object> dataMap1 = new HashMap<>();
        dataMap1.put("curriculumId", coursewareBo.getCurriculumId());
        dataMap1.put("title", coursewareBo.getTitle());
        dataMap1.put("coursewareId", coursewareBo.getCoursewareId());
        int cnt1 = coursewareRoMapper.selectCoursewareCnt(dataMap1);
        if(cnt1 > 0){
            throw new ServiceException(4335);
        }

        Map<String, Object> dataMap2 = new HashMap<>();
        dataMap2.put("curriculumId", coursewareBo.getCurriculumId());
        dataMap2.put("chapterId", coursewareBo.getChapterId());
        dataMap2.put("coursewareSeq", coursewareBo.getCoursewareSeq());
        dataMap2.put("coursewareId", coursewareBo.getCoursewareId());
        int cnt2 = coursewareRoMapper.selectCoursewareCnt(dataMap2);
        if(cnt2 > 0){
            throw new ServiceException(4336);
        }
        //更新课件信息
        CurriculumCourseware courseware = new CurriculumCourseware();
        try {
            JSONObject jsonStu = JSONObject.fromObject(coursewareBo);
            LOGGER.info("更新课件信息:{}", jsonStu.toString());
            coursewareBo.setUpdateTime(new Date());
            BeanUtils.copyProperties(coursewareBo, courseware);
            coursewareMapper.updateByPrimaryKeySelective(courseware);
        } catch (Exception e) {
            LOGGER.error("更新课件信息异常：{}", e);
            throw new ServiceException(4333);
        }
        return coursewareBo;
    }

    @Override
    public String updateStatus(String coursewareId,String status) {
        //更新课件信息
        try {
//            LOGGER.info("更新课程状态信息:{}", coursewareId+","+status);
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
//            coursewareMapper.updateStatus(curriculum);
        } catch (Exception e) {
            LOGGER.error("更新课件信息异常：{}", e);
            throw new ServiceException(4333);
        }
        return "";
    }

    @Transactional("db1TxManager")
    @Override
    public String delete(String coursewareId) {
        try {
            LOGGER.info("删除课件信息:{}", coursewareId);
            coursewareMapper.deleteByPrimaryKey(coursewareId);
        } catch (Exception e) {
            LOGGER.error("删除课件异常：{}", e);
            throw new ServiceException(4334);
        }
        return "";
    }

    @Override
    public List<CurriculumCoursewareBo> selectCoursewareList(Map<String, Object> dataMap) {
        return coursewareRoMapper.selectCoursewareList(dataMap);
    }

}
