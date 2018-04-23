package com.abc12366.cms.service.impl;

import com.abc12366.cms.mapper.db1.OptionMapper;
import com.abc12366.cms.mapper.db1.SubjectsMapper;
import com.abc12366.cms.mapper.db2.AnswerRoMapper;
import com.abc12366.cms.mapper.db2.OptionRoMapper;
import com.abc12366.cms.mapper.db2.SubjectsRoMapper;
import com.abc12366.cms.model.bo.AnswerdttjBo;
import com.abc12366.cms.model.bo.SubjectsdtxxtjBo;
import com.abc12366.cms.model.questionnaire.Option;
import com.abc12366.cms.model.questionnaire.Subjects;
import com.abc12366.cms.model.questionnaire.bo.SubjectsBO;
import com.abc12366.cms.service.SubjectsService;
import com.abc12366.gateway.exception.ServiceException;
import com.abc12366.gateway.util.Utils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author lizhongwei
 * @create 2017-06-07 4:21 PM
 * @since 1.0.0
 */
@Service
public class SubjectsServiceImpl implements SubjectsService {

    private static final Logger LOGGER = LoggerFactory.getLogger(SubjectsServiceImpl.class);
    @Autowired
    private SubjectsRoMapper subjectsRoMapper;

    @Autowired
    private SubjectsMapper subjectsMapper;

    @Autowired
    private OptionRoMapper optionRoMapper;

    @Autowired
    private OptionMapper optionMapper;

    @Autowired
    private AnswerRoMapper answerRoMapper;


    @Override
    public List<SubjectsBO> selectList(SubjectsBO subjectsBO) {
        Subjects subjects = new Subjects();
        BeanUtils.copyProperties(subjectsBO, subjects);

        return subjectsRoMapper.selectList(subjects);
    }

    @Override
    public SubjectsBO selectOne(String id) {
        return subjectsRoMapper.selectOne(id);
    }


    @Transactional("db1TxManager")
    @Override
    public SubjectsBO insert(SubjectsBO subjectsBO) {
        Subjects subjects = new Subjects();
        BeanUtils.copyProperties(subjectsBO, subjects);
        String subjectsId = Utils.uuid();
        subjects.setId(subjectsId);
        int insert = subjectsMapper.insert(subjects);
        if (insert != 1) {
            LOGGER.info("{新增题目失败}", subjects);
            throw new ServiceException(4399);
        }
        List<Option> options = new ArrayList<>();
        List<Option> optionList = subjectsBO.getOptionList();
        int oInsert = 0;
        for (Option option : optionList) {
            option.setId(Utils.uuid());
            option.setSubjectsId(subjectsId);
            option.setStatus(true);
            oInsert = optionMapper.insert(option);
            if (oInsert != 1) {
                LOGGER.info("{新增选项失败}", option);
                throw new ServiceException(4396);
            }
            options.add(option);
        }
        SubjectsBO bo = new SubjectsBO();
        BeanUtils.copyProperties(subjects, bo);
        bo.setOptionList(options);
        return bo;
    }

    @Transactional("db1TxManager")
    @Override
    public SubjectsBO update(SubjectsBO subjectsBO) {
        Subjects subjects = new Subjects();
        BeanUtils.copyProperties(subjectsBO, subjects);
        int update = subjectsMapper.update(subjects);
        if (update != 1) {
            LOGGER.info("{修改题目失败}", subjects);
            throw new ServiceException(4398);
        }
        List<Option> options = new ArrayList<>();
        List<Option> optionList = subjectsBO.getOptionList();
        int oUpdate = 0;
        //先删除所有的选项，再新增
        optionMapper.deleteBySubjectsId(subjectsBO.getId());
        for (Option option : optionList) {
            //查询选项是否已存在
//            Option temp = optionRoMapper.selectByPrimaryKey(option.getId());
//            if(temp == null){
            option.setId(Utils.uuid());
            option.setSubjectsId(subjects.getId());
            option.setStatus(true);
            oUpdate = optionMapper.insert(option);
            if (oUpdate != 1) {
                LOGGER.info("{新增选项失败}", option);
                throw new ServiceException(4396);
            }
//            }else {
//                oUpdate = optionMapper.update(option);
//                if (oUpdate != 1){
//                    LOGGER.info("{修改选项失败}", option);
//                    throw new ServiceException(4395);
//                }
//            }
            options.add(option);
        }
        SubjectsBO bo = new SubjectsBO();
        BeanUtils.copyProperties(subjects, bo);
        bo.setOptionList(options);
        return bo;
    }

    @Transactional("db1TxManager")
    @Override
    public void delete(SubjectsBO subjectsBO) {
        Subjects subjects = new Subjects();
        BeanUtils.copyProperties(subjectsBO, subjects);
        int del = subjectsMapper.delete(subjects);
        if (del != 1) {
            LOGGER.info("{删除题目失败}", subjects);
            throw new ServiceException(4397);
        }
        optionMapper.deleteBySubjectsId(subjectsBO.getId());
    }

    @Transactional("db1TxManager")
    @Override
    public List<SubjectsBO> insertList(List<SubjectsBO> subjectsBOs, String questionId) {
        List<SubjectsBO> boList = new ArrayList<SubjectsBO>();
        if (subjectsBOs != null) {
            for (SubjectsBO sBO : subjectsBOs) {
                Subjects subjects = new Subjects();
                BeanUtils.copyProperties(sBO, subjects);
                //判断是新增还是修改，id为null就是新增
                if (sBO.getId() != null && !"".equals(sBO.getId())) {
                    sBO.setQuestionId(questionId);
                    //修改，只修改题目编号
                    int update = subjectsMapper.update(subjects);
                    if (update != 1) {
                        LOGGER.info("{修改题目失败}", subjects);
                        throw new ServiceException(4398);
                    }

                    boList.add(subjectsRoMapper.selectOne(subjects.getId()));
                } else {
                    String subjectsId = Utils.uuid();
                    subjects.setId(subjectsId);
                    int insert = subjectsMapper.insert(subjects);
                    if (insert != 1) {
                        LOGGER.info("{新增题目失败}", subjects);
                        throw new ServiceException(4399);
                    }
                    List<Option> options = new ArrayList<>();
                    List<Option> optionList = sBO.getOptionList();
                    for (Option option : optionList) {
                        option.setId(Utils.uuid());
                        option.setSubjectsId(subjectsId);
                        option.setStatus(true);
                        int oInsert = optionMapper.insert(option);
                        if (oInsert != 1) {
                            LOGGER.info("{新增选项失败}", option);
                            throw new ServiceException(4396);
                        }
                        options.add(option);
                    }
                    SubjectsBO bo = new SubjectsBO();
                    BeanUtils.copyProperties(subjects, bo);
                    bo.setOptionList(options);
                    boList.add(bo);
                }
            }
        }
        return boList;
    }

    @Transactional("db1TxManager")
    @Override
    public List<SubjectsBO> updateList(List<SubjectsBO> subjectsBOs, String questionId, String id) {
        List<SubjectsBO> boList = new ArrayList<SubjectsBO>();
        if (subjectsBOs != null) {
            for (SubjectsBO sBO : subjectsBOs) {
                sBO.setQuestionId(questionId);
                Subjects subjects = new Subjects();
                BeanUtils.copyProperties(sBO, subjects);
                //id 等于列表中的id时，修改里面数据，否则修改题目编号
                if (id.equals(sBO.getId())) {
                    int update = subjectsMapper.update(subjects);
                    if (update != 1) {
                        LOGGER.info("{修改题目失败}", subjects);
                        throw new ServiceException(4398);
                    }
                    List<Option> options = new ArrayList<>();
                    List<Option> optionList = sBO.getOptionList();
                    for (Option option : optionList) {
                        option.setSubjectsId(subjects.getId());
                        option.setStatus(true);
                        int oUpdate = optionMapper.update(option);
                        if (oUpdate != 1) {
                            LOGGER.info("{修改选项失败}", option);
                            throw new ServiceException(4395);
                        }
                        options.add(option);
                    }
                    SubjectsBO bo = new SubjectsBO();
                    BeanUtils.copyProperties(subjects, bo);
                    bo.setOptionList(options);
                    boList.add(bo);
                } else {
                    //修改，只修改题目编号
                    int update = subjectsMapper.update(subjects);
                    if (update != 1) {
                        LOGGER.info("{修改题目失败}", subjects);
                        throw new ServiceException(4398);
                    }
                    boList.add(subjectsRoMapper.selectOne(subjects.getId()));
                }
            }
        }
        return boList;
    }

    @Transactional("db1TxManager")
    @Override
    public List<SubjectsBO> deleteList(List<SubjectsBO> subjectsBOs, String questionId, String id) {
        Subjects subjects = new Subjects();
        subjects.setQuestionId(questionId);
        subjects.setId(id);
        int del = subjectsMapper.delete(subjects);
        if (del != 1) {
            LOGGER.info("{删除题目失败}", subjects);
            throw new ServiceException(4397);
        }
        optionMapper.deleteBySubjectsId(subjects.getId());

        List<SubjectsBO> boList = new ArrayList<SubjectsBO>();
        if (subjectsBOs != null) {
            for (SubjectsBO sBO : subjectsBOs) {
                sBO.setQuestionId(questionId);
                subjects = new Subjects();
                BeanUtils.copyProperties(sBO, subjects);
                //修改，只修改题目编号
                int update = subjectsMapper.update(subjects);
                if (update != 1) {
                    LOGGER.info("{修改题目失败}", subjects);
                    throw new ServiceException(4398);
                }
                boList.add(subjectsRoMapper.selectOne(subjects.getId()));
            }
        }
        return boList;
    }

    @Override
    public SubjectsBO copySubjects(List<SubjectsBO> subjectsBOs, String subjectsId) {
        SubjectsBO boList = new SubjectsBO();
        if (subjectsBOs != null) {
            //查询题目
            boList = subjectsRoMapper.selectOne(subjectsId);
            Integer number = boList.getNumber();
            boList.setNumber(number + 1);
            String subId = Utils.uuid();
            boList.setId(subId);
            Subjects subjects = new Subjects();
            BeanUtils.copyProperties(boList, subjects);
            //复制题目
            int insert = subjectsMapper.insert(subjects);
            if (insert != 1) {
                LOGGER.info("{新增题目失败}", subjects);
                throw new ServiceException(4399);
            }
            List<Option> options = new ArrayList<>();
            List<Option> optionList = boList.getOptionList();
            for (Option option : optionList) {
                option.setId(Utils.uuid());
                option.setSubjectsId(subId);
                option.setStatus(true);
                int oInsert = optionMapper.insert(option);
                if (oInsert != 1) {
                    LOGGER.info("{新增选项失败}", option);
                    throw new ServiceException(4396);
                }
                options.add(option);
            }

            for (SubjectsBO sBO : subjectsBOs) {
                subjects = new Subjects();
                BeanUtils.copyProperties(sBO, subjects);
                //修改，只修改题目编号
                int update = subjectsMapper.update(subjects);
                if (update != 1) {
                    LOGGER.info("{修改题目失败}", subjects);
                    throw new ServiceException(4398);
                }
            }
        }
        return boList;
    }

    @Override
    public void deleteSubjectsByPages(Subjects subjects) {
        List<SubjectsBO> subjectsList = subjectsRoMapper.selectSubjectsByPages(subjects);
        for (SubjectsBO sub : subjectsList) {
            int del = subjectsMapper.deleteByPrimaryKey(sub.getId());
            if (del != 1) {
                LOGGER.info("{删除题目失败}", sub);
                throw new ServiceException(4397);
            }
            optionMapper.deleteBySubjectsId(sub.getId());
        }
    }

    @Override
    public List<SubjectsdtxxtjBo> selectListdttj(Map<String, Object> map) {
        List<SubjectsdtxxtjBo> sublist = subjectsRoMapper.selectListdttj(map);
//        for (SubjectsdtxxtjBo subjectsdttjBo : sublist) {
//            String id = subjectsdttjBo.getId();
//            map.put("subjectsId", id);
//            List<AnswerdttjBo> anslist = answerRoMapper.selectdttj(map);
//            Integer cnt = answerRoMapper.selectdttjs(map);
//            subjectsdttjBo.setCnt(cnt);
//            subjectsdttjBo.setDtlist(anslist);
//        }
        return sublist;
    }
}
