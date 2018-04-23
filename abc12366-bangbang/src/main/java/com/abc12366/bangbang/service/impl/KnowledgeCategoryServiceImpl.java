package com.abc12366.bangbang.service.impl;

import com.abc12366.bangbang.mapper.db1.KnowledgeBaseMapper;
import com.abc12366.bangbang.mapper.db1.KnowledgeCategoryMapper;
import com.abc12366.bangbang.mapper.db2.KnowledgeBaseRoMapper;
import com.abc12366.bangbang.model.KnowledgeCategory;
import com.abc12366.bangbang.model.bo.SortBO;
import com.abc12366.bangbang.service.KnowledgeCategoryService;
import com.abc12366.bangbang.util.StringUtil;
import com.abc12366.gateway.exception.ServiceException;
import com.abc12366.gateway.util.RedisConstant;
import com.abc12366.gateway.util.Utils;
import com.alibaba.fastjson.JSONArray;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * @Author liuqi
 * @Date 2017/8/2 21:01
 */
@Service
public class KnowledgeCategoryServiceImpl implements KnowledgeCategoryService {

    private static final Logger LOGGER = LoggerFactory.getLogger(KnowledgeBaseServiceImpl.class);

    @Autowired
    private KnowledgeBaseMapper knowledgeBaseMapper;

    @Autowired
    private KnowledgeBaseRoMapper knowledgeBaseRoMapper;

    @Autowired
    private KnowledgeCategoryMapper knowledgeCategoryMapper;

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    @Override
    public List<KnowledgeCategory> listAll() {
        if (redisTemplate.hasKey("Bangb_KnowledgeCategoryList")) {
            return JSONArray.parseArray(redisTemplate.opsForValue().get("Bangb_KnowledgeCategoryList"),
                    KnowledgeCategory.class);
        } else {
            List<KnowledgeCategory> list = knowledgeCategoryMapper.selectAll();
            redisTemplate.opsForValue().set("Bangb_KnowledgeCategoryList", JSONArray.toJSONString(list),
                    RedisConstant.DAY_30, TimeUnit.DAYS);
            return list;
        }
    }

    @Override
    public KnowledgeCategory add(KnowledgeCategory record) {
        try {
            record.setId(Utils.uuid());
            record.setCreateUser(Utils.getAdminId());
            record.setUpdateUser(Utils.getAdminId());
            String parentCode = StringUtil.nullToString(record.getParentCode());
            if ("0".equals(parentCode)) {
                parentCode = "";
            }
            String code = parentCode + genCodes(6);
            for (; ; ) {
                KnowledgeCategory rs = knowledgeCategoryMapper.selectByCode(code);
                if (rs == null) {
                    break;
                } else {
                    code = parentCode + genCodes(6);
                }
            }
            record.setCode(code);
            knowledgeCategoryMapper.insert(record);

            redisTemplate.delete("Bangb_KnowledgeCategoryList");

            return record;
        } catch (Exception e) {
            LOGGER.error("KnowledgeCategoryServiceImpl.add()", e);
            throw new ServiceException(4511);
        }
    }

    @Transactional("db1TxManager")
    @Override
    public KnowledgeCategory modify(KnowledgeCategory knowledgeCategory) {
        try {
            String parentCode = knowledgeCategory.getParentCode();
            String id = knowledgeCategory.getId();
            /* 如果修改分类的时候，修改了父节点，判断 */
            if (!StringUtils.isEmpty(parentCode) && !StringUtils.isEmpty(id)) {
                KnowledgeCategory cate = knowledgeCategoryMapper.selectByPrimaryKey(id);
                String cateCode = cate.getCode();
                if (parentCode.equals("0")) {
                    parentCode = "";
                }
                if (!cateCode.substring(0, cateCode.length() - 6).equals(parentCode)) {
                    String newCode = parentCode + genCodes(6);
                    for (; ; ) {
                        KnowledgeCategory rs = knowledgeCategoryMapper.selectByCode(newCode);
                        if (rs == null) {
                            break;
                        } else {
                            newCode = parentCode + genCodes(6);
                        }
                    }
                    knowledgeCategory.setCode(newCode);
                    //同步修改
                    knowledgeBaseMapper.updateCategoryCode(cateCode, newCode);
                }
            }
            knowledgeCategory.setUpdateUser(Utils.getAdminId());
            knowledgeCategory.setUpdateTime(new Date());
            knowledgeCategoryMapper.updateByPrimaryKeySelective(knowledgeCategory);

            redisTemplate.delete("Bangb_KnowledgeCategoryList");

            return knowledgeCategory;
        } catch (Exception e) {
            LOGGER.error("KnowledgeCategoryServiceImpl.modify()", e);
            throw new ServiceException(4513);
        }
    }

    @Override
    public void modifyNameById(String id, String name) {
        try {
            KnowledgeCategory knowledgeCategory = new KnowledgeCategory();
            knowledgeCategory.setId(id);
            knowledgeCategory.setName(name);
            knowledgeCategory.setUpdateUser(Utils.getAdminId());
            knowledgeCategory.setUpdateTime(new Date());
            knowledgeCategoryMapper.updateByPrimaryKeySelective(knowledgeCategory);

            redisTemplate.delete("Bangb_KnowledgeCategoryList");
        } catch (Exception e) {
            LOGGER.error("KnowledgeCategoryServiceImpl.modifyNameById()", e);
            throw new ServiceException(4513);
        }
    }

    @Override
    public void modifySort(List<SortBO> list) {
        try {
            if (list != null && !list.isEmpty()) {
                knowledgeCategoryMapper.batchUpdateSort(list);
            }

            redisTemplate.delete("Bangb_KnowledgeCategoryList");
        } catch (Exception e) {
            LOGGER.error("KnowledgeCategoryServiceImpl.modifySort()", e);
            throw new ServiceException(4513);
        }
    }

    @Override
    public void deleteById(String id) {
        int refKnowledgeCnt = knowledgeBaseRoMapper.selectCntByCategoryId(id);
        if (refKnowledgeCnt > 0) {
            throw new ServiceException(4522);
        }
        try {
            knowledgeCategoryMapper.deleteByPrimaryKey(id);

            redisTemplate.delete("Bangb_KnowledgeCategoryList");
        } catch (Exception e) {
            LOGGER.error("KnowledgeCategoryServiceImpl.modifySort()", e);
            throw new ServiceException(4512);
        }
    }


    public String genCodes(int length) {
        String val = "";
        Random random = new Random();
        for (int i = 0; i < length; i++) {
            String charOrNum = random.nextInt(2) % 2 == 0 ? "char" : "num"; // 输出字母还是数字
            if ("char".equalsIgnoreCase(charOrNum)) // 字符串
            {
                int choice = random.nextInt(2) % 2 == 0 ? 65 : 97; //取得大写字母还是小写字母
                val += (char) (choice + random.nextInt(26));
            } else if ("num".equalsIgnoreCase(charOrNum)) // 数字
            {
                val += String.valueOf(random.nextInt(10));
            }
        }
        val = val.toLowerCase();
        return val;
    }
}
