package com.abc12366.bangbang.service.impl;

import com.abc12366.bangbang.mapper.db1.CurriculumLecturerMapper;
import com.abc12366.bangbang.mapper.db2.CurriculumLecturerGxRoMapper;
import com.abc12366.bangbang.mapper.db2.CurriculumLecturerRoMapper;
import com.abc12366.bangbang.model.curriculum.CurriculumLecturer;
import com.abc12366.bangbang.model.curriculum.bo.CurriculumLecturerBo;
import com.abc12366.bangbang.service.LecturerService;
import com.abc12366.gateway.exception.ServiceException;
import net.sf.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * Created by xieyanmao on 2017/8/11.
 */
@Service
public class LecturerServiceImpl implements LecturerService {
    private static final Logger LOGGER = LoggerFactory.getLogger(LecturerServiceImpl.class);

    @Autowired
    private CurriculumLecturerMapper lecturerMapper;

    @Autowired
    private CurriculumLecturerRoMapper lecturerRoMapper;

    @Autowired
    private CurriculumLecturerGxRoMapper lecturerGxRoMapper;

    @Override
    public List<CurriculumLecturerBo> selectList(Map<String,Object> map) {
        List<CurriculumLecturerBo> lecturerBoList;
        try {
            //查询讲师列表
            lecturerBoList = lecturerRoMapper.selectList(map);
            if(lecturerBoList != null){
                for(CurriculumLecturerBo lecturer : lecturerBoList){
                    int cnt = lecturerRoMapper.selectStudentCnt(lecturer.getLecturerId());
                    lecturer.setStudentNum(cnt);
                }
            }
        } catch (Exception e) {
            LOGGER.error("查询讲师列表信息异常：{}", e);
            throw new ServiceException(4350);
        }
        return lecturerBoList;
    }

    @Override
    public List<CurriculumLecturerBo> selectListByCurr(String curriculumId) {
        List<CurriculumLecturerBo> lecturerBoList;
        try {
            //查询讲师列表
            lecturerBoList = lecturerRoMapper.selectListByCurr(curriculumId);
            if(lecturerBoList != null){
                for(CurriculumLecturerBo lecturer : lecturerBoList){
                    int cnt = lecturerRoMapper.selectStudentCnt(lecturer.getLecturerId());
                    lecturer.setStudentNum(cnt);
                }
            }
        } catch (Exception e) {
            LOGGER.error("查询讲师列表信息异常：{}", e);
            throw new ServiceException(4350);
        }
        return lecturerBoList;
    }

    @Override
    public CurriculumLecturerBo save(CurriculumLecturerBo lecturerBo) {

            JSONObject jsonStu = JSONObject.fromObject(lecturerBo);
            LOGGER.info("新增讲师信息:{}", jsonStu.toString());
            //保存讲师信息
            String uuid = UUID.randomUUID().toString().replace("-", "");
            CurriculumLecturer lecturer = new CurriculumLecturer();
            lecturerBo.setLecturerId(uuid);
            lecturerBo.setCreateTime(new Date());

            int cnt = lecturerRoMapper.selectLecturerCnt(lecturerBo);
            if(cnt >0){
                //同一用户只能创建一位讲师信息
                throw new ServiceException(4355);
            }

        try {
            BeanUtils.copyProperties(lecturerBo, lecturer);
            lecturerMapper.insert(lecturer);
        } catch (Exception e) {
            LOGGER.error("新增讲师信息异常：{}", e);
            throw new ServiceException(4352);
        }

        return lecturerBo;
    }

    @Override
    public CurriculumLecturerBo selectLecturer(String lecturerId) {
        CurriculumLecturerBo lecturerBo = new CurriculumLecturerBo();
        try {
            LOGGER.info("查询单个讲师信息:{}", lecturerId);
            //查询讲师信息
            CurriculumLecturer lecturer = lecturerRoMapper.selectByPrimaryKey(lecturerId);
            BeanUtils.copyProperties(lecturer, lecturerBo);
        } catch (Exception e) {
            LOGGER.error("查询单个讲师信息异常：{}", e);
            throw new ServiceException(4351);
        }
        return lecturerBo;
    }

    @Override
    public CurriculumLecturerBo update(CurriculumLecturerBo lecturerBo) {
        //更新讲师信息
        CurriculumLecturer lecturer = new CurriculumLecturer();

            JSONObject jsonStu = JSONObject.fromObject(lecturerBo);
            LOGGER.info("更新讲师信息:{}", jsonStu.toString());
            lecturerBo.setUpdateTime(new Date());

            int cnt = lecturerRoMapper.selectLecturerCnt(lecturerBo);
            if(cnt >0){
                //同一用户只能创建一位讲师信息
                throw new ServiceException(4355);
            }


        try {
            BeanUtils.copyProperties(lecturerBo, lecturer);
            lecturerMapper.updateByPrimaryKeySelective(lecturer);
        } catch (Exception e) {
            LOGGER.error("更新讲师信息异常：{}", e);
            throw new ServiceException(4353);
        }
        return lecturerBo;
    }

    @Override
    public String updateStatus(String lecturerId,String status) {
        //更新讲师信息
        try {
//            LOGGER.info("更新课程状态信息:{}", LecturerId+","+status);
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
//            LecturerMapper.updateStatus(curriculum);
        } catch (Exception e) {
            LOGGER.error("更新讲师信息异常：{}", e);
            throw new ServiceException(4353);
        }
        return "";
    }

    @Transactional("db1TxManager")
    @Override
    public String delete(String lecturerId) {

            LOGGER.info("删除讲师信息:{}", lecturerId);
            int cnt = lecturerGxRoMapper.selectLecturerCnt(lecturerId);
            if(cnt > 0){
                //该讲师名下有课程，不能删除
                throw new ServiceException(4356);
            }
        try {
            lecturerMapper.deleteByPrimaryKey(lecturerId);
        } catch (Exception e) {
            LOGGER.error("删除讲师异常：{}", e);
            throw new ServiceException(4354);
        }
        return "";
    }

}
