package com.abc12366.cms.web;

import com.abc12366.cms.model.questionnaire.Subjects;
import com.abc12366.cms.model.questionnaire.bo.SubjectsBO;
import com.abc12366.cms.service.SubjectsService;
import com.abc12366.gateway.util.Constant;
import com.abc12366.gateway.util.Utils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * 题目控制类
 *
 * @author lizhongwei
 * @create 2017-06-14
 * @since 2.0.0
 */
@RestController
@RequestMapping(path = "/subjects", headers = Constant.VERSION_HEAD + "=" + Constant.VERSION_1)
public class SubjectsController {

    private static final Logger LOGGER = LoggerFactory.getLogger(SubjectsController.class);

    @Autowired
    private SubjectsService subjectsService;

    /**
     * 题目列表查询
     *
     * @param questionId
     * @param title
     * @param pages
     * @return
     */
    @GetMapping(path = "/{questionId}")
    public ResponseEntity selectList(@PathVariable("questionId") String questionId,
                                     @RequestParam(value = "title", required = false) String title,
                                     @RequestParam(value = "pages", required = false) Integer pages) {
        SubjectsBO subjects = new SubjectsBO();
        subjects.setQuestionId(questionId);
        subjects.setTitle(title);
        subjects.setPages(pages);
        List<SubjectsBO> subjectsList = subjectsService.selectList(subjects);
        LOGGER.info("{}", subjectsList);
        return (subjectsList == null) ?
                new ResponseEntity<>(Utils.bodyStatus(4001), HttpStatus.BAD_REQUEST) :
                ResponseEntity.ok(Utils.kv("dataList", subjectsList));
    }


    /**
     * 查询题目详情
     *
     * @param id
     * @return
     */
    @GetMapping(path = "/select/{id}")
    public ResponseEntity<?> selectOne(@PathVariable String id) {
        LOGGER.info("{}", id);
        SubjectsBO subjectsBO = subjectsService.selectOne(id);
        LOGGER.info("{}", subjectsBO);
        return ResponseEntity.ok(Utils.kv("data", subjectsBO));
    }

    /**
     * 题目新增，并修改编号
     *
     * @param questionId
     * @return
     */
    @PostMapping(path = "/list/{questionId}")
    public ResponseEntity addSubjects(@Valid @RequestBody List<SubjectsBO> subjectsBOs, @PathVariable("questionId")
    String questionId) {
        LOGGER.info("{}", subjectsBOs);
        List<SubjectsBO> bos = subjectsService.insertList(subjectsBOs, questionId);
        LOGGER.info("{}", bos);
        return ResponseEntity.ok(Utils.kv("dataList", bos));
    }

    /**
     * 复制题目，并修改编号
     *
     * @param subjectsId
     * @return
     */
    @PostMapping(path = "/copy/{subjectsId}")
    public ResponseEntity copySubjects(@Valid @RequestBody List<SubjectsBO> subjectsBOs, @PathVariable("subjectsId")
    String subjectsId) {
        LOGGER.info("{}", subjectsBOs);
        SubjectsBO bos = subjectsService.copySubjects(subjectsBOs, subjectsId);
        LOGGER.info("{}", bos);
        return ResponseEntity.ok(Utils.kv("data", bos));
    }

    /**
     * 题目修改，并修改编号
     *
     * @param subjectsBOs
     * @param questionId
     * @param id
     * @return
     */
    @PutMapping(path = "/list/{id}/{questionId}")
    public ResponseEntity update(@Valid @RequestBody List<SubjectsBO> subjectsBOs, @PathVariable("questionId") String
            questionId, @PathVariable("id") String id) {
        LOGGER.info("{}", subjectsBOs);
        List<SubjectsBO> bos = subjectsService.updateList(subjectsBOs, questionId, id);
        LOGGER.info("{}", bos);
        return ResponseEntity.ok(Utils.kv("dataList", bos));
    }

    /**
     * 题目删除，并修改编号
     *
     * @param questionId
     * @param id
     * @return
     */
    @DeleteMapping(path = "/list/{id}/{questionId}")
    public ResponseEntity deleteList(@Valid @RequestBody List<SubjectsBO> subjectsBOs, @PathVariable("questionId")
    String questionId, @PathVariable("id") String id) {
        LOGGER.info("{}", subjectsBOs);
        List<SubjectsBO> bos = subjectsService.deleteList(subjectsBOs, questionId, id);
        LOGGER.info("{}", bos);
        return ResponseEntity.ok(Utils.kv("dataList", bos));
    }

    /**
     * 题目新增
     *
     * @param questionId
     * @return
     */
    @PostMapping(path = "/{questionId}")
    public ResponseEntity addSubjects(@Valid @RequestBody SubjectsBO subjectsBO, @PathVariable("questionId") String
            questionId) {
        LOGGER.info("{}", subjectsBO);
        subjectsBO.setQuestionId(questionId);
        SubjectsBO bos = subjectsService.insert(subjectsBO);
        LOGGER.info("{}", bos);
        return ResponseEntity.ok(Utils.kv("data", bos));
    }

    /**
     * 题目修改
     *
     * @param subjectsBO
     * @param questionId
     * @param id
     * @return
     */
    @PutMapping(path = "/{id}/{questionId}")
    public ResponseEntity update(@Valid @RequestBody SubjectsBO subjectsBO, @PathVariable("questionId") String
            questionId, @PathVariable("id") String id) {
        LOGGER.info("{}", subjectsBO);
        subjectsBO.setQuestionId(questionId);
        subjectsBO.setId(id);
        SubjectsBO bos = subjectsService.update(subjectsBO);
        LOGGER.info("{}", bos);
        return ResponseEntity.ok(Utils.kv("data", bos));
    }

    /**
     * 题目删除
     *
     * @param questionId
     * @param id
     * @return
     */
    @DeleteMapping(path = "/{id}/{questionId}")
    public ResponseEntity delete(@PathVariable("questionId") String questionId, @PathVariable("id") String id) {
        SubjectsBO subjectsBO = new SubjectsBO();
        subjectsBO.setQuestionId(questionId);
        subjectsBO.setId(id);
        LOGGER.info("{}", subjectsBO);
        subjectsService.delete(subjectsBO);
        return ResponseEntity.ok(Utils.kv());
    }

    /**
     * 根据pages，题目删除
     *
     * @param questionId
     * @param pages
     * @return
     */
    @DeleteMapping(path = "/pages/{pages}/{questionId}")
    public ResponseEntity deleteSubjectsByPages(@PathVariable("questionId") String questionId, @PathVariable("pages")
    Integer pages) {
        Subjects subjects = new Subjects();
        subjects.setQuestionId(questionId);
        subjects.setPages(pages);
        LOGGER.info("{}", subjects);
        subjectsService.deleteSubjectsByPages(subjects);
        return ResponseEntity.ok(Utils.kv());
    }
}
