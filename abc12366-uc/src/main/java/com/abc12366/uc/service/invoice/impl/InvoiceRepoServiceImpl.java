package com.abc12366.uc.service.invoice.impl;

import com.abc12366.gateway.exception.ServiceException;
import com.abc12366.gateway.util.Utils;
import com.abc12366.uc.mapper.db1.InvoiceDetailMapper;
import com.abc12366.uc.mapper.db1.InvoiceRepoMapper;
import com.abc12366.uc.mapper.db2.InvoiceDetailRoMapper;
import com.abc12366.uc.mapper.db2.InvoiceDistributeRoMapper;
import com.abc12366.uc.mapper.db2.InvoiceRepoRoMapper;
import com.abc12366.uc.model.invoice.InvoiceDetail;
import com.abc12366.uc.model.invoice.InvoiceDistribute;
import com.abc12366.uc.model.invoice.InvoiceRepo;
import com.abc12366.uc.model.invoice.bo.InvoiceDetailBO;
import com.abc12366.uc.model.invoice.bo.InvoiceRepoBO;
import com.abc12366.uc.service.invoice.InvoiceRepoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * @create 2017-05-15 10:17 AM
 * @since 1.0.0
 */
@Service
public class InvoiceRepoServiceImpl implements InvoiceRepoService {

    private static final Logger LOGGER = LoggerFactory.getLogger(InvoiceRepoServiceImpl.class);

    @Autowired
    private InvoiceRepoRoMapper invoiceRepoRoMapper;

    @Autowired
    private InvoiceRepoMapper invoiceRepoMapper;

    @Autowired
    private InvoiceDetailRoMapper invoiceDetailRoMapper;


    @Autowired
    private InvoiceDetailMapper invoiceDetailMapper;

    @Autowired
    private InvoiceDistributeRoMapper invoiceDistributeRoMapper;

    @Override
    public List<InvoiceRepoBO> selectList(InvoiceRepoBO invoiceRepoBO) {
        return invoiceRepoRoMapper.selectList(invoiceRepoBO);
    }

    @Override
    public InvoiceRepoBO selectInvoiceRepo(InvoiceRepo invoiceRepo) {
        return invoiceRepoRoMapper.selectInvoiceRepo(invoiceRepo.getId());
    }

    @Override
    public void delete(String id) {
        int delete = invoiceRepoMapper.deleteByPrimaryKey(id);
        if(delete != 1){
            LOGGER.warn("删除失败，参数{}：" + id);
            throw new ServiceException(4103);
        }
        invoiceDetailMapper.deleteByInvoiceRepoId(id);
    }

    @Override
    public InvoiceRepoBO add(InvoiceRepoBO invoiceRepoBO) {
        if(invoiceRepoBO == null){
            LOGGER.warn("数据错误{}：" + null);
            throw new ServiceException(4906);
        }

        Date date = new Date();
        invoiceRepoBO.setCreateTime(date);
        invoiceRepoBO.setCreateUser(Utils.getAdminId());
        invoiceRepoBO.setLastUpdate(date);
        int startLength = invoiceRepoBO.getInvoiceNoStart().length();
        int endLength = invoiceRepoBO.getInvoiceNoEnd().length();
        if(startLength != endLength){
            LOGGER.warn("发票起止长度必须保持一致{}{}：" + startLength+endLength);
            throw new ServiceException(4910);
        }

        int start = Integer.parseInt(invoiceRepoBO.getInvoiceNoStart());
        int end = Integer.parseInt(invoiceRepoBO.getInvoiceNoEnd());
        String ids[] = invoiceRepoBO.getId().split("-");
        int book = invoiceRepoBO.getBook();
        int startId = Integer.parseInt(ids[1]);
        int startIdLength = ids[1].length();
        int endId = Integer.parseInt(ids[2]);
        if(book != (endId - startId +1)){
            LOGGER.warn("编号的起止号码+1，必须等于本数{}{}：",startId,endId);
            throw new ServiceException(4911);
        }

        List<Integer> list = new ArrayList<>();
        for (int i = start; i <= end; i++) {
            list.add(i);
        }

        int pageSize = invoiceRepoBO.getShare();
        int totalCount = list.size();
        int m = totalCount % pageSize;
        System.out.println(m);
        if(m != 0){
            LOGGER.warn("必须要整本的加入{}{}：", m);
            throw new ServiceException(4912);
        }
        int pageCount = totalCount / pageSize;
        int endCount=0;
        int startCount=0;
        String invoiceTypeCode = invoiceRepoBO.getInvoiceTypeCode();
        boolean isIndex;
        for(int i = 1; i <= pageCount; i++) {
            List<Integer> subList = list.subList((i - 1) * pageSize, pageSize * (i));
            String repoId = invoiceTypeCode+autoGenericCode(startId,startIdLength);
            invoiceRepoBO.setId(repoId);
            InvoiceRepo invoiceRepo = new InvoiceRepo();
            isIndex = true;
            for(Integer j:subList){
                if(isIndex){
                    startCount = j;
                    isIndex = false;
                }
                InvoiceDetail invoiceDetail = new InvoiceDetail();
                invoiceDetail.setId(Utils.uuid());
                invoiceDetail.setCreateTime(date);
                invoiceDetail.setLastUpdate(date);
                invoiceDetail.setProperty(invoiceRepoBO.getProperty());
                invoiceDetail.setInvoiceNo(autoGenericCode(j,endLength));
                invoiceDetail.setInvoiceCode(invoiceRepoBO.getInvoiceCode());
                invoiceDetail.setInvoiceRepoId(repoId);
                invoiceDetail.setStatus("1");
                int dInsert = invoiceDetailMapper.insert(invoiceDetail);
                if(dInsert != 1){
                    LOGGER.warn("新增失败，参数{}：" + invoiceRepoBO);
                    throw new ServiceException(4101);
                }
                endCount = j;
            }
            invoiceRepoBO.setInvoiceNoStart(autoGenericCode(startCount,endLength));
            invoiceRepoBO.setInvoiceNoEnd(autoGenericCode(endCount,endLength));
            invoiceRepoBO.setStatus("0");
            invoiceRepoBO.setBook(1);
            BeanUtils.copyProperties(invoiceRepoBO,invoiceRepo);
            InvoiceRepo repo = invoiceRepoRoMapper.selectByPrimaryKey(invoiceRepo.getId());
            if(repo != null){
                LOGGER.warn("发票编号已存在");
                throw new ServiceException(7147);
            }
            int insert = invoiceRepoMapper.insert(invoiceRepo);
            if(insert != 1){
                LOGGER.warn("新增失败，参数{}：" + invoiceRepoBO);
                throw new ServiceException(4101);
            }
            startId ++;

        }
        return invoiceRepoBO;
    }

    /**
     * 不够位数的在前面补0，保留num的长度位数字
     * @param code 内容
     * @param num  长度
     */
    private static String autoGenericCode(int code, int num) {
        return String.format("%0" + num + "d", code);
    }

    @Override
    public InvoiceRepoBO update(InvoiceRepoBO invoiceRepoBO) {
        Date date = new Date();
        invoiceRepoBO.setLastUpdate(date);
        invoiceRepoBO.setUpdateUser(Utils.getAdminId());
        InvoiceRepo invoiceRepo = new InvoiceRepo();
        BeanUtils.copyProperties(invoiceRepoBO,invoiceRepo);
        //已出库的发票仓库不能进行修改
        InvoiceRepoBO tempRepo = invoiceRepoRoMapper.selectInvoiceRepo(invoiceRepoBO.getId());
        if(tempRepo != null && "1".equals(tempRepo.getStatus())){
            throw new ServiceException(4911,"已出库的仓库数据，不能进行修改");
        }
        int startLength = invoiceRepoBO.getInvoiceNoStart().length();
        int endLength = invoiceRepoBO.getInvoiceNoEnd().length();
        if(startLength != endLength){
            LOGGER.warn("发票起止长度必须保持一致{}{}：" + startLength+endLength);
            throw new ServiceException(4910);
        }
        int rInsert = invoiceRepoMapper.update(invoiceRepo);
        if(rInsert != 1){
            LOGGER.warn("修改失败，参数{}：" + invoiceRepo);
            throw new ServiceException(4102);
        }
        //删除发票详情表数据
        invoiceDetailMapper.deleteByInvoiceRepoId(invoiceRepoBO.getId());
        //插入发票详情数据
        int start = Integer.parseInt(invoiceRepoBO.getInvoiceNoStart());
        int end = Integer.parseInt(invoiceRepoBO.getInvoiceNoEnd());

        List<Integer> list = new ArrayList<>();
        for (int i = start; i <= end; i++) {
            list.add(i);
        }
        for(Integer j:list){
            InvoiceDetail invoiceDetail = new InvoiceDetail();
            invoiceDetail.setId(Utils.uuid());
            invoiceDetail.setCreateTime(date);
            invoiceDetail.setLastUpdate(date);
            invoiceDetail.setProperty(invoiceRepoBO.getProperty());
            invoiceDetail.setInvoiceNo(autoGenericCode(j,endLength));
            invoiceDetail.setInvoiceCode(invoiceRepoBO.getInvoiceCode());
            invoiceDetail.setInvoiceRepoId(invoiceRepoBO.getId());
            invoiceDetail.setStatus("1");
            int dInsert = invoiceDetailMapper.insert(invoiceDetail);
            if(dInsert != 1){
                LOGGER.warn("新增失败，参数{}：" + invoiceRepoBO);
                throw new ServiceException(4101);
            }
        }
        return invoiceRepoBO;
    }

    @Override
    public InvoiceRepo selectRepoId(String invoiceTypeCode) {
        Map<String,Object> map = new HashMap();
        map.put("codeLength",invoiceTypeCode.length());
        map.put("invoiceTypeCode",invoiceTypeCode);
        InvoiceRepo invoiceRepo = invoiceRepoRoMapper.selectRepoId(map);
        return invoiceRepo;
    }

    @Override
    public List<InvoiceDetail> selectInvoiceDetailList(InvoiceDetail invoiceDetail) {
        return invoiceDetailRoMapper.selectInvoiceDetailList(invoiceDetail);
    }

    @Override
    public void deleteInvoiceDetail(String id) {
        InvoiceDetail invoiceDetail = invoiceDetailRoMapper.selectByPrimaryKey(id);
        if (invoiceDetail != null) {
            if ("1".equals(invoiceDetail.getStatus()) || "2".equals(invoiceDetail.getStatus())) {
                LOGGER.info("{发票在分配中或已使用，不能删除}：{}", id);
                throw new ServiceException(4174);
            }
            int rDel = invoiceDetailMapper.delete(id);
            if (rDel != 1) {
                LOGGER.info("{发票详情信息删除失败}：{}", id);
                throw new ServiceException(4172);
            }
        } else {
            LOGGER.info("{发票信息不存在}：{}", id);
            throw new ServiceException(4175);
        }
    }

    @Override
    public void invalidInvoiceDetail(String id) {
        InvoiceDetail invoiceDetail = new InvoiceDetail();
        invoiceDetail.setId(id);
        invoiceDetail.setStatus("3");
        int update = invoiceDetailMapper.update(invoiceDetail);
        if (update != 1) {
            LOGGER.info("{发票详情信息作废失败}：{}", id);
            throw new ServiceException(4176);
        }
    }

    @Override
    public InvoiceRepoBO selectInvoiceRepoNum(String code) {
        return invoiceRepoRoMapper.selectInvoiceRepoNum(code);
    }

    @Override
    public InvoiceDetail selectInvoiceDetailByInvoice(InvoiceDetail invoiceDetail) {
        return invoiceDetailRoMapper.selectInvoiceDetailByInvoice(invoiceDetail);
    }

    @Override
    public boolean validateInvoice(InvoiceRepo invoiceRepo) {
        String id = invoiceRepo.getId();
        int startLength = invoiceRepo.getInvoiceNoStart().length();
        int endLength = invoiceRepo.getInvoiceNoEnd().length();
        if(startLength != endLength){
            LOGGER.warn("发票起止长度必须保持一致{}{}：" + startLength+endLength);
            throw new ServiceException(4910);
        }
        int start = Integer.parseInt(invoiceRepo.getInvoiceNoStart());
        int end = Integer.parseInt(invoiceRepo.getInvoiceNoEnd());

        List<Integer> list = new ArrayList<>();
        for (int i = start; i <= end; i++) {
            list.add(i);
        }
        for(Integer j:list){
            InvoiceDetail invoiceDetail = new InvoiceDetail();
            invoiceDetail.setInvoiceCode(invoiceRepo.getInvoiceCode());
            invoiceDetail.setInvoiceNo(autoGenericCode(j,endLength));
            InvoiceDetail temp  = invoiceDetailRoMapper.selectByInvoiceNoAndCode(invoiceDetail);
            if(temp != null && !temp.getInvoiceRepoId().equals(id)){
                return false;
            }
        }
        return true;
    }

    @Override
    public InvoiceDetailBO selectInvoiceDistributeByInv(String invoiceTypeCode) {
        InvoiceDistribute invoiceDistribute = new InvoiceDistribute();
        invoiceDistribute.setSignUser(Utils.getAdminId());
        invoiceDistribute.setInvoiceTypeCode(invoiceTypeCode);
        return invoiceDetailRoMapper.selectInvoiceDetail(invoiceDistribute);
    }

    @Override
    public List<InvoiceDetail> selectInvoiceDetailListByInvoice(InvoiceDetail invoiceDetail) {
        return invoiceDetailRoMapper.selectInvoiceDetailListByInvoice(invoiceDetail);
    }
}
