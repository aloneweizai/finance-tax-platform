package com.abc12366.cms.web;

import com.abc12366.cms.model.bo.*;
import com.abc12366.cms.service.ChannelService;
import com.abc12366.cms.service.ModelService;
import com.abc12366.gateway.util.Constant;
import com.abc12366.gateway.util.Utils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 栏目管理模块
 *
 * @author xieyanmao
 * @create 2017-05-09
 * @since 1.0.0
 */
@RestController
@RequestMapping(path = "/channel", headers = Constant.VERSION_HEAD + "=1")
public class ChannelController {
    private static final Logger LOGGER = LoggerFactory.getLogger(ChannelController.class);

    @Autowired
    private ChannelService channelService;

    @Autowired
    private ModelService modelService;

    /**
     * 栏目列表查询
     */
    @GetMapping
    public ResponseEntity selectList(@RequestParam(value = "channelId", required = false) String channelId,
                                     @RequestParam(value = "parentId", required = false) String parentId,
                                     @RequestParam(value = "siteId", required = false) String siteId,
                                     @RequestParam(value = "isDisplay", required = false) String isDisplay,
                                     @RequestParam(value = "channelName", required = false) String channelName
    ) {
        Map<String, Object> dataMap = new HashMap<>();
        dataMap.put("channelId", channelId);//栏目ID
        dataMap.put("parentId", parentId);//父栏目ID
        dataMap.put("siteId", siteId);//站点ID
        dataMap.put("isDisplay", isDisplay);//是否启用
        dataMap.put("channelName", channelName);//栏目名称
        List<ChannelBo> dataList = channelService.selectLists(dataMap);
        return ResponseEntity.ok(Utils.kv("dataList", dataList));
    }

    /**
     * 栏目列表查询无需登录
     */
    @GetMapping(path = "/list")
    public ResponseEntity list(@RequestParam(value = "channelId", required = false) String channelId,
                               @RequestParam(value = "parentId", required = false) String parentId,
                               @RequestParam(value = "isDisplay", required = false) String isDisplay,
                               @RequestParam(value = "channelName", required = false) String channelName) {
        Map<String, Object> dataMap = new HashMap<>();
        dataMap.put("channelId", channelId);//栏目ID
        dataMap.put("parentId", parentId);//父栏目ID
        dataMap.put("channelName", channelName);//栏目名称
        dataMap.put("isDisplay", isDisplay);//栏目名称
        List<ChannelBo> dataList = channelService.selectLists(dataMap);
        return ResponseEntity.ok(Utils.kv("dataList", dataList));
    }

    /**
     * 初始化栏目
     */
    @GetMapping(path = "/init")
    public ResponseEntity init(@RequestParam(value = "modelId", required = false) String modelId) {
        //查询模型项
        Map<String, Object> dataMap = new HashMap<>();
        dataMap.put("modelId", modelId);//模型ID
        dataMap.put("isChannel", 1);//是否为栏目，1为是
        ChannelInitBo dataList = new ChannelInitBo();
        List<ModelItemBo> modelItems = channelService.selectModeList(dataMap);
        ModelBo modelBo = modelService.selectModel(modelId);
        //模型项
        dataList.setModelItems(modelItems);
        //栏目模板前缀
        dataList.setTplPrefix(modelBo.getTplChannelPrefix());
        return ResponseEntity.ok(Utils.kv("data", dataList));
    }

    /**
     * 新增栏目
     */
    @PostMapping
    public ResponseEntity save(@Valid @RequestBody ChannelSaveBo channelSaveBo) {
        //新增栏目信息
        channelSaveBo = channelService.save(channelSaveBo);
        return ResponseEntity.ok(Utils.kv("data", channelSaveBo));
    }

    /**
     * 查询单个栏目
     */
    @GetMapping(path = "/{channelId}")
    public ResponseEntity selectOne(@PathVariable String channelId) {
        //根据栏目ID查询栏目信息
        ChannelSaveBo channelSaveBo = channelService.selectChannel(channelId);
        return ResponseEntity.ok(Utils.kv("data", channelSaveBo));
    }

    /**
     * 根据模板查询栏目信息(供生成静态页用)
     */
    @GetMapping(path = "/channelList")
    public ResponseEntity channelList(@RequestParam(value = "startTime", required = false) String startTime,
                                      @RequestParam(value = "endTime", required = false) String endTime,
                                      @RequestParam(value = "tplChannel", required = false) String tplChannel) {
        //查询栏目信息
        Map<String, Object> dataMap = new HashMap<>();
        List<ChannelExtBo> channelExtBoList = channelService.selectListBytplChannel(tplChannel);
        List<ChannelSaveBo> dataList = new ArrayList<ChannelSaveBo>();
        if (channelExtBoList != null) {
            for (ChannelExtBo channelExtBo : channelExtBoList) {
                ChannelSaveBo channelSaveBo = channelService.selectChannel(channelExtBo.getChannelId());
                dataList.add(channelSaveBo);
            }
        }
        return ResponseEntity.ok(Utils.kv("dataList", dataList));
    }

    /**
     * 更新栏目
     */
    @PutMapping(path = "/{channelId}")
    public ResponseEntity update(@PathVariable String channelId,
                                 @Valid @RequestBody ChannelSaveBo channelSaveBo) {
        //更新栏目信息
        channelSaveBo = channelService.update(channelSaveBo);
        return ResponseEntity.ok(Utils.kv("data", channelSaveBo));
    }

    /**
     * 更新栏目是否启用，同时更新其下面的子栏目
     */
    @PutMapping(path = "/updateByparentId")
    public ResponseEntity update(@RequestBody ChannelBo channelBo) {
        //更新栏目信息
        channelBo = channelService.updateChannelByparentId(channelBo);
        return ResponseEntity.ok(Utils.kv("data", channelBo));
    }

    /**
     * 删除栏目
     */
    @DeleteMapping(path = "/{channelId}")
    public ResponseEntity delete(@PathVariable String channelId) {
        //删除栏目信息
        String rtn = channelService.delete(channelId);
        return ResponseEntity.ok(Utils.kv("data", rtn));
    }


}
