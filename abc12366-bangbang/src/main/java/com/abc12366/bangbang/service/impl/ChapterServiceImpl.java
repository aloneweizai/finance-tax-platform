package com.abc12366.bangbang.service.impl;

import com.abc12366.bangbang.mapper.db1.CurriculumChapterMapper;
import com.abc12366.bangbang.mapper.db1.CurriculumCoursewareMapper;
import com.abc12366.bangbang.mapper.db2.CurriculumChapterRoMapper;
import com.abc12366.bangbang.mapper.db2.CurriculumCoursewareRoMapper;
import com.abc12366.bangbang.model.curriculum.CurriculumChapter;
import com.abc12366.bangbang.model.curriculum.bo.CurriculumChapterBo;
import com.abc12366.bangbang.model.curriculum.bo.CurriculumCoursewareBo;
import com.abc12366.bangbang.service.ChapterService;
import com.abc12366.gateway.exception.ServiceException;
import net.sf.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * Created by xieyanmao on 2017/8/11.
 */
@Service
public class ChapterServiceImpl implements ChapterService {
    private static final Logger LOGGER = LoggerFactory.getLogger(ChapterServiceImpl.class);

    @Autowired
    private CurriculumChapterMapper chapterMapper;

    @Autowired
    private CurriculumChapterRoMapper chapterRoMapper;

    @Autowired
    private CurriculumCoursewareMapper coursewareMapper;

    @Autowired
    private CurriculumCoursewareRoMapper coursewareRoMapper;

    @Override
    public List<CurriculumChapterBo> selectList(Map<String,Object> map) {
        List<CurriculumChapterBo> chapterBoList;
        try {
            //查询章节列表
            chapterBoList = chapterRoMapper.selectList(map);

            for(CurriculumChapterBo chapterBo : chapterBoList){
                String chapterId =chapterBo.getChapterId();
                Map<String, Object> dataMap = new HashMap<>();
                dataMap.put("chapterId",chapterId);
                //查询课件
                List<CurriculumCoursewareBo> coursewareBoList = coursewareRoMapper.selectList(map);
                chapterBo.setCoursewareList(coursewareBoList);
            }

        } catch (Exception e) {
            LOGGER.error("查询章节列表信息异常：{}", e);
            throw new ServiceException(4340);
        }
        return chapterBoList;
    }

    @Override
    public CurriculumChapterBo save(CurriculumChapterBo chapterBo) {
        try {
            JSONObject jsonStu = JSONObject.fromObject(chapterBo);
            LOGGER.info("新增章节信息:{}", jsonStu.toString());
            //保存章节信息
            String uuid = UUID.randomUUID().toString().replace("-", "");
            CurriculumChapter chapter = new CurriculumChapter();
            chapterBo.setChapterId(uuid);
            BeanUtils.copyProperties(chapterBo, chapter);
            chapterMapper.insert(chapter);
        } catch (Exception e) {
            LOGGER.error("新增章节信息异常：{}", e);
            throw new ServiceException(4342);
        }

        return chapterBo;
    }

    @Override
    public CurriculumChapterBo selectChapter(String chapterId) {
        CurriculumChapterBo chapterBo = new CurriculumChapterBo();
        try {
            LOGGER.info("查询单个章节信息:{}", chapterId);
            //查询章节信息
            CurriculumChapter chapter = chapterRoMapper.selectByPrimaryKey(chapterId);
            BeanUtils.copyProperties(chapter, chapterBo);
        } catch (Exception e) {
            LOGGER.error("查询单个章节信息异常：{}", e);
            throw new ServiceException(4341);
        }
        return chapterBo;
    }

    @Override
    public CurriculumChapterBo update(CurriculumChapterBo chapterBo) {
        //更新章节信息
        CurriculumChapter chapter = new CurriculumChapter();
        try {
            JSONObject jsonStu = JSONObject.fromObject(chapterBo);
            LOGGER.info("更新章节信息:{}", jsonStu.toString());
            BeanUtils.copyProperties(chapterBo, chapter);
            chapterMapper.updateByPrimaryKeySelective(chapter);
        } catch (Exception e) {
            LOGGER.error("更新章节信息异常：{}", e);
            throw new ServiceException(4343);
        }
        return chapterBo;
    }

    @Override
    public String updateStatus(String chapterId,String status) {
        //更新章节信息
        try {
//            LOGGER.info("更新课程状态信息:{}", ChapterId+","+status);
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
//            ChapterMapper.updateStatus(curriculum);
        } catch (Exception e) {
            LOGGER.error("更新章节信息异常：{}", e);
            throw new ServiceException(4343);
        }
        return "";
    }

    @Transactional("db1TxManager")
    @Override
    public String delete(String chapterId) {
        try {
            LOGGER.info("删除章节信息:{}", chapterId);
            coursewareMapper.deleteByChapterId(chapterId);
            chapterMapper.deleteByPrimaryKey(chapterId);
        } catch (Exception e) {
            LOGGER.error("删除章节异常：{}", e);
            throw new ServiceException(4344);
        }
        return "";
    }

}
