package com.abc12366.cms.web;

import com.abc12366.cms.model.questionnaire.Images;
import com.abc12366.cms.service.ImagesService;
import com.abc12366.gateway.util.Constant;
import com.abc12366.gateway.util.Utils;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * 背景图片控制类
 *
 * @author lizhongwei
 * @create 2017-07-1
 * @since 1.0.0
 */
@RestController
@RequestMapping(path = "/images", headers = Constant.VERSION_HEAD + "=" + Constant.VERSION_1)
public class ImagesController {

    private static final Logger LOGGER = LoggerFactory.getLogger(ImagesController.class);

    @Autowired
    private ImagesService imagesService;

    /**
     * 背景图片列表查询
     *
     * @return
     */
    @GetMapping
    public ResponseEntity selectList(@RequestParam(value = "page", defaultValue = Constant.pageNum) int page,
                                     @RequestParam(value = "size", defaultValue = Constant.pageSize) int size) {
        PageHelper.startPage(page, size, true).pageSizeZero(true).reasonable(true);
        Images images = new Images();
        List<Images> dataList = imagesService.selectList(images);
        LOGGER.info("{}", dataList);
        return ResponseEntity.ok(Utils.kv("dataList", (Page) dataList, "total", ((Page) dataList).getTotal()));
    }


    /**
     * 查询背景图片详情
     *
     * @param id
     * @return
     */
    @GetMapping(path = "/{id}")
    public ResponseEntity<?> selectOne(@PathVariable String id) {
        LOGGER.info("{}", id);
        Images images = imagesService.selectOne(id);
        LOGGER.info("{}", images);
        return ResponseEntity.ok(Utils.kv("data", images));
    }

    /**
     * 背景图片新增
     *
     * @return
     */
    @PostMapping
    public ResponseEntity addImages(@Valid @RequestBody Images images) {
        LOGGER.info("{}", images);
        Images bos = imagesService.insert(images);
        LOGGER.info("{}", bos);
        return ResponseEntity.ok(Utils.kv("data", bos));
    }

    /**
     * 背景图片修改
     *
     * @param images
     * @param id
     * @return
     */
    @PutMapping(path = "/{id}")
    public ResponseEntity update(@Valid @RequestBody Images images, @PathVariable("id") String id) {
        LOGGER.info("{}", images);
        Images bos = imagesService.update(images, id);
        LOGGER.info("{}", bos);
        return ResponseEntity.ok(Utils.kv("data", bos));
    }

    /**
     * 背景图片删除
     *
     * @param id
     * @return
     */
    @DeleteMapping(path = "/{id}")
    public ResponseEntity delete(@PathVariable("id") String id) {
        LOGGER.info("{}", id);
        imagesService.delete(id);
        return ResponseEntity.ok(Utils.kv());
    }

}
