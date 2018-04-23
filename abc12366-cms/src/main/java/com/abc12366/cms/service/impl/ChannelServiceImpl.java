package com.abc12366.cms.service.impl;

import com.abc12366.cms.mapper.db1.*;
import com.abc12366.cms.mapper.db2.*;
import com.abc12366.cms.model.Channel;
import com.abc12366.cms.model.ChannelAttr;
import com.abc12366.cms.model.ChannelExt;
import com.abc12366.cms.model.ChnlGroupView;
import com.abc12366.cms.model.bo.*;
import com.abc12366.cms.service.ChannelService;
import com.abc12366.gateway.exception.ServiceException;
import net.sf.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;

/**
 * Created by xieyanmao on 2017/5/9.
 */
@Service
public class ChannelServiceImpl implements ChannelService {
    private static final Logger LOGGER = LoggerFactory.getLogger(ChannelServiceImpl.class);

    @Autowired
    private ChannelMapper channelMapper;

    @Autowired
    private ChannelRoMapper channelRoMapper;

    @Autowired
    private ChannelExtMapper channelExtMapper;

    @Autowired
    private ChannelExtRoMapper channelExtRoMapper;

    @Autowired
    private ChannelAttrMapper channelAttrMapper;

    @Autowired
    private ChannelAttrRoMapper channelAttrRoMapper;

    @Autowired
    private ModelItemRoMapper modelItemRoMapper;

    @Autowired
    private ChnlGroupViewRoMapper groupRoMapper;

    @Autowired
    private ChnlGroupViewMapper groupMapper;

    @Autowired
    private ContentRoMapper contentRoMapper;

    @Autowired
    private ChannelCountMapper channelCountMapper;


    @Override
    public List<ChannelBo> selectList() {
        List<ChannelBo> channelBoList = new ArrayList<>();
        try {
            List<Channel> channelList = channelRoMapper.selectList();
            for(Channel channel : channelList){
                ChannelBo channelBo = new ChannelBo();
                BeanUtils.copyProperties(channel,channelBo);
                channelBoList.add(channelBo);
            }
        } catch (Exception e) {
            LOGGER.error("查询栏目信息异常：{}", e);
            throw new ServiceException(4240);
        }
        return channelBoList;
    }

    @Override
    public List<ChannelBo> selectLists(Map<String,Object> map) {
        List<ChannelBo> channelBoList;
        try {
            JSONObject jsonStu = JSONObject.fromObject(map);
            LOGGER.info("查询栏目信息:{}", jsonStu.toString());
            channelBoList = channelRoMapper.selectLists(map);
        } catch (Exception e) {
            LOGGER.error("查询栏目信息异常：{}", e);
            throw new ServiceException(4240);
        }
        return channelBoList;
    }

    @Override
    public List<ModelItemBo> selectModeList(Map<String,Object> map) {
        List<ModelItemBo> modelItemBos;
        try {
            JSONObject jsonStu = JSONObject.fromObject(map);
            LOGGER.info("查询模型项信息:{}", jsonStu.toString());
            //查询模型项
            modelItemBos = modelItemRoMapper.selectList(map);
        } catch (Exception e) {
            LOGGER.error("查询模型项信息异常：{}", e);
            throw new ServiceException(4241);
        }
        return modelItemBos;
    }

    @Transactional("db1TxManager")
    @Override
    public ChannelSaveBo save(ChannelSaveBo channelSaveBo) {
        //栏目
        ChannelBo channelBo = channelSaveBo.getChannel();
        ChannelBo channelBo1 = new ChannelBo();
        channelBo1.setSiteId(channelBo.getSiteId());
        channelBo1.setChannelPath(channelBo.getChannelPath());
        int cnt1 = channelRoMapper.selectChannelPathCnt(channelBo1);
        if(cnt1 >0){
            throw new ServiceException(4239);
        }
        try {
            JSONObject jsonStu = JSONObject.fromObject(channelSaveBo);
            LOGGER.info("新增栏目信息:{}", jsonStu.toString());
//            String uuid = UUID.randomUUID().toString().replace("-", "");
            String uuid = "";
            String code = "";

            String parentId = channelBo.getParentId();

            for(int i=0;i<20;i++){
                code = this.genCodes(6);
                uuid = parentId + code;
                int cnt = channelRoMapper.selectChannelIdCnt(uuid);
                if(cnt ==0){
                    break;
                }
            }


            channelBo.setChannelId(uuid);
            Channel channel = new Channel();
            BeanUtils.copyProperties(channelBo, channel);
            channelMapper.insert(channel);
            //栏目扩展信息
            ChannelExtBo channelExtBo = channelSaveBo.getChannelExt();
            channelExtBo.setChannelId(uuid);
            ChannelExt channelExt = new ChannelExt();
            BeanUtils.copyProperties(channelExtBo, channelExt);
            channelExtMapper.insert(channelExt);
            //栏目扩展项信息
            List<ChannelAttrBo> channelAttrBoList = channelSaveBo.getChannelAttrList();
            if(channelAttrBoList != null){
                for(ChannelAttrBo channelAttrBo:channelAttrBoList){
                    channelAttrBo.setChannelId(uuid);
                    ChannelAttr channelAttr = new ChannelAttr();
                    BeanUtils.copyProperties(channelAttrBo, channelAttr);
                    channelAttrMapper.insert(channelAttr);
                }
            }

            //用户组
            List<ChnlGroupViewBo> groupBoList = channelSaveBo.getGroupList();
            if(groupBoList != null){
                for(ChnlGroupViewBo groupBo:groupBoList){
                    ChnlGroupView group = new ChnlGroupView();
                    groupBo.setChannelId(uuid);
                    BeanUtils.copyProperties(groupBo, group);
                    groupMapper.insert(group);
                }
            }
        } catch (Exception e) {
            LOGGER.error("新增栏目信息异常：{}", e);
            throw new ServiceException(4242);
        }
        return channelSaveBo;
    }

    @Override
    public ChannelSaveBo selectChannel(String channelId) {
        ChannelSaveBo channelSaveBo = new ChannelSaveBo();
        try {
            LOGGER.info("查询单个栏目信息:{}", channelId);
            //查询栏目信息
            Channel channel = channelRoMapper.selectByPrimaryKey(channelId);
            ChannelBo channelBo = new ChannelBo();
            if(channel != null){
                BeanUtils.copyProperties(channel, channelBo);
            }
            //查询栏目扩展信息
            ChannelExt channelExt = channelExtRoMapper.selectByPrimaryKey(channelId);
            ChannelExtBo channelExtBo = new ChannelExtBo();
            if(channelExt != null){
                BeanUtils.copyProperties(channelExt, channelExtBo);
            }
            //查询栏目扩展项信息
            List<ChannelAttr> channelAttrList = channelAttrRoMapper.selectByChannelId(channelId);
            List<ChannelAttrBo> channelAttrBoList = new ArrayList<ChannelAttrBo>();
            if(channelAttrList != null){
                for(ChannelAttr channelAttr:channelAttrList){
                    ChannelAttrBo channelAttrBo = new ChannelAttrBo();
                    BeanUtils.copyProperties(channelAttr, channelAttrBo);
                    channelAttrBoList.add(channelAttrBo);
                }
            }
            //用户组
            List<ChnlGroupView> groupList = groupRoMapper.selectList(channelId);
            List<ChnlGroupViewBo> groupBoList = new ArrayList<ChnlGroupViewBo>();
            if(groupBoList != null){
                for(ChnlGroupView group:groupList){
                    ChnlGroupViewBo groupBo = new ChnlGroupViewBo();
                    BeanUtils.copyProperties(group, groupBo);
                    groupBoList.add(groupBo);
                }
            }

            channelSaveBo.setChannel(channelBo);
            channelSaveBo.setChannelExt(channelExtBo);
            channelSaveBo.setChannelAttrList(channelAttrBoList);
            channelSaveBo.setGroupList(groupBoList);
        } catch (Exception e) {
            LOGGER.error("查询单个栏目信息异常：{}", e);
            throw new ServiceException(4243);
        }
        return channelSaveBo;
    }

    @Transactional("db1TxManager")
    @Override
    public ChannelSaveBo update(ChannelSaveBo channelSaveBo) {
        //栏目
        ChannelBo channelBo = channelSaveBo.getChannel();
        ChannelBo channelBo1 = new ChannelBo();
        channelBo1.setSiteId(channelBo.getSiteId());
        channelBo1.setChannelPath(channelBo.getChannelPath());
        channelBo1.setChannelId(channelBo.getChannelId());
        int cnt1 = channelRoMapper.selectChannelPathCnt(channelBo1);
        if(cnt1 >0){
            throw new ServiceException(4239);
        }
        try {
            JSONObject jsonStu = JSONObject.fromObject(channelSaveBo);
            LOGGER.info("更新栏目信息:{}", jsonStu.toString());

            //查询栏目信息
            Channel channel1 = channelRoMapper.selectByPrimaryKey(channelBo.getChannelId());
            if(channelBo.getIsDisplay() != channel1.getIsDisplay()){
                updateChannelByparentId(channelBo);
            }

            Channel channel = new Channel();
            BeanUtils.copyProperties(channelBo, channel);
            channelMapper.updateByPrimaryKeySelective(channel);
            //栏目扩展信息
            ChannelExtBo channelExtBo = channelSaveBo.getChannelExt();
            ChannelExt channelExt = new ChannelExt();
            BeanUtils.copyProperties(channelExtBo, channelExt);
            channelExtMapper.updateByPrimaryKeySelective(channelExt);
            //栏目扩展项信息
            List<ChannelAttrBo> channelAttrBoList = channelSaveBo.getChannelAttrList();
            if(channelAttrBoList != null){
                for(ChannelAttrBo channelAttrBo:channelAttrBoList){
                    ChannelAttr channelAttr = new ChannelAttr();
                    BeanUtils.copyProperties(channelAttrBo, channelAttr);
                    channelAttrMapper.updateByPrimaryKeySelective(channelAttr);
                }
            }

            //用户组
            groupMapper.deleteByPrimaryKey(channelBo.getChannelId());
            List<ChnlGroupViewBo> groupBoList = channelSaveBo.getGroupList();
            if(groupBoList != null){
                for(ChnlGroupViewBo groupBo:groupBoList){
                    ChnlGroupView group = new ChnlGroupView();
                    BeanUtils.copyProperties(groupBo, group);
                    groupMapper.insert(group);
                }
            }
        } catch (Exception e) {
            LOGGER.error("更新栏目信息异常：{}", e);
            throw new ServiceException(4244);
        }
        return channelSaveBo;
    }

    @Transactional("db1TxManager")
    @Override
    public ChannelBo updateChannelByparentId(ChannelBo channelBo) {
        //更新栏目是否启用
        Channel channel = new Channel();
        try {
            JSONObject jsonStu = JSONObject.fromObject(channelBo);
            LOGGER.info("启用禁用栏目信息:{}", jsonStu.toString());
            BeanUtils.copyProperties(channelBo, channel);
            channelMapper.updateByPrimaryKeySelective(channel);
            List<Channel> channelList = channelRoMapper.selectListByparentId(channelBo.getChannelId());
            if(channelList != null){
                for(Channel channel1 : channelList){
                    ChannelBo channelBo1 = new ChannelBo();
                        BeanUtils.copyProperties(channel1, channelBo1);
                    channelBo1.setIsDisplay(channelBo.getIsDisplay());
                    this.updateChannelByparentId(channelBo1);
                }
            }
        } catch (Exception e) {
            LOGGER.error("启用禁用栏目异常：{}", e);
            throw new ServiceException(4245);
        }

        return channelBo;
    }

    @Override
    public List<ChannelBo> selectListByparentId(String parentId) {
        List<ChannelBo> channelBoList = new ArrayList<>();
        try {
            LOGGER.info("根据父栏目查询栏目信息:{}", parentId);
            List<Channel> channelList = channelRoMapper.selectListByparentId(parentId);
            if(channelList != null){
                for(Channel channel : channelList){
                    ChannelBo channelBo = new ChannelBo();
                    BeanUtils.copyProperties(channel,channelBo);
                    channelBoList.add(channelBo);
                }
            }
        } catch (Exception e) {
            LOGGER.error("根据父栏目查询栏目信息异常：{}", e);
            throw new ServiceException(4246);
        }
        return channelBoList;
    }

    @Override
    public List<ChannelExtBo> selectListBytplChannel(String tplChannel) {
        //根据模板查询栏目信息(供生成静态页用)
        List<ChannelExtBo> channelExtBoList = new ArrayList<>();
        try {
            LOGGER.info("根据模板查询栏目信息:{}", tplChannel);
            List<ChannelExt> channelExtList = channelExtRoMapper.selectListBytplChannel(tplChannel);
            if(channelExtList != null){
                for(ChannelExt channelExt : channelExtList){
                    ChannelExtBo channelExtBo = new ChannelExtBo();
                    BeanUtils.copyProperties(channelExt,channelExtBo);
                    channelExtBoList.add(channelExtBo);
                }
            }
        } catch (Exception e) {
            LOGGER.error("根据模板查询栏目信息异常：{}", e);
            throw new ServiceException(4247);
        }
        return channelExtBoList;
    }

    @Transactional("db1TxManager")
    @Override
    public String delete(String channelId) {
        LOGGER.info("删除栏目信息:{}", channelId);
        int cnt = contentRoMapper.selectByChannelId(channelId).intValue();
        if(cnt != 0){
            //该栏目下存在内容信息,不能删除
            throw new ServiceException(4249);
        }
        try {
            //删除栏目扩展信息
            channelExtMapper.deleteByPrimaryKey(channelId);
            //删除栏目扩展项信息
            channelAttrMapper.deleteByPrimaryKey(channelId);
            //删除用户组
            groupMapper.deleteByPrimaryKey(channelId);
            //删除统计数
            channelCountMapper.deleteByPrimaryKey(channelId);
            //删除栏目信息
            channelMapper.deleteByPrimaryKey(channelId);

            List<Channel> channelList = channelRoMapper.selectListByparentId(channelId);
            for(Channel channel : channelList){
                this.delete(channel.getChannelId());
            }
        } catch (Exception e) {
            LOGGER.error("删除栏目信息异常：{}", e);
            throw new ServiceException(4248);
        }
        return "";
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
