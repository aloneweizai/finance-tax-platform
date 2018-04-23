package com.abc12366.bangbang.web.question;

import com.abc12366.bangbang.model.question.CheatsSearchBo;
import com.abc12366.bangbang.model.question.QuestionSearchBo;
import com.abc12366.bangbang.model.question.SearchBo;
import com.abc12366.bangbang.service.SearchService;
import com.abc12366.gateway.exception.ServiceException;
import com.abc12366.gateway.util.Constant;
import com.abc12366.gateway.util.Utils;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.List;

/**
 * Created by stuy on 2017/11/9.
 */
@RestController
@RequestMapping(path = "/search", headers = Constant.VERSION_HEAD + "=" + Constant.VERSION_1)
public class SearchController {


    private static final Logger LOGGER = LoggerFactory.getLogger(com.abc12366.bangbang.web.question.SearchController.class);


    @Autowired
    private SearchService searchService;
    
    /* 列表查询 */
    @GetMapping(path = "/list")
    public ResponseEntity selectList(@RequestParam(value = "page", defaultValue = Constant.pageNum) int page,
                                     @RequestParam(value = "text", defaultValue = "") String text,
                                     @RequestParam(value = "size", defaultValue = Constant.pageSize) int size){
        PageHelper.startPage(page, size, true).pageSizeZero(true).reasonable(true);
        LOGGER.info("参数值:"+text);
        SearchBo searchBo=new SearchBo();
        if(text!=null&&!"".equals(text)){
            List<QuestionSearchBo> list = searchService.queryQuestionSearch(text);
            if(list!=null&&list.size()>0){
                searchBo.setQuestionSearchBoList((Page)list);
                searchBo.setQuestionTotal(((Page)list).getTotal());
            }
            List<CheatsSearchBo> listcheats = searchService.queryCheatsSearch(text);
            if(listcheats!=null&&listcheats.size()>0){
                searchBo.setCheatsSearchBoList((Page)listcheats);
                searchBo.setCheatsTotal(((Page)listcheats).getTotal());
            }
        }else{
            LOGGER.info("搜索值缺少  {}");
            throw new ServiceException(5000);
        }
        return ResponseEntity.ok(Utils.kv("data", searchBo));
    }


    /* 秘籍搜索列表查询 */
    @GetMapping(path = "/cheats/list")
    public ResponseEntity cheatslist(@RequestParam(value = "page", defaultValue = Constant.pageNum) int page,
                                     @RequestParam(value = "text", defaultValue = "") String text,
                                     @RequestParam(value = "size", defaultValue = Constant.pageSize) int size){
        PageHelper.startPage(page, size, true).pageSizeZero(true).reasonable(true);
        LOGGER.info("参数值:"+text);
        List<CheatsSearchBo> listcheats=null;
        if(text!=null&&!"".equals(text)){
            try {
                text=URLDecoder.decode(text,"UTF-8");
                listcheats = searchService.queryCheatsSearch(text);
            } catch (UnsupportedEncodingException e) {
                LOGGER.info("系统异常  {}");
                throw new ServiceException(5000);
            }
        }else{
            LOGGER.info("搜索值缺少  {}");
            throw new ServiceException(5000);
        }
        return (listcheats == null) ?
                ResponseEntity.ok(Utils.kv()) :
                ResponseEntity.ok(Utils.kv("dataList", (Page) listcheats, "total", ((Page) listcheats).getTotal()));
    }

    /* 话题搜索列表查询 */
    @GetMapping(path = "/question/list")
    public ResponseEntity questionlist(@RequestParam(value = "page", defaultValue = Constant.pageNum) int page,
                                     @RequestParam(value = "text", defaultValue = "") String text,
                                     @RequestParam(value = "size", defaultValue = Constant.pageSize) int size){
        PageHelper.startPage(page, size, true).pageSizeZero(true).reasonable(true);
        LOGGER.info("参数值:"+text);
        List<QuestionSearchBo> list=null;
        if(text!=null&&!"".equals(text)){
            try {
                text=URLDecoder.decode(text,"UTF-8");
                list = searchService.queryQuestionSearch(text);
            } catch (UnsupportedEncodingException e) {
                LOGGER.info("系统异常  {}");
                throw new ServiceException(5000);
            }
        }else{
            LOGGER.info("搜索值缺少  {}");
            throw new ServiceException(5000);
        }
        return (list == null) ?
                ResponseEntity.ok(Utils.kv()) :
                ResponseEntity.ok(Utils.kv("dataList", (Page) list, "total", ((Page) list).getTotal()));
    }
}
