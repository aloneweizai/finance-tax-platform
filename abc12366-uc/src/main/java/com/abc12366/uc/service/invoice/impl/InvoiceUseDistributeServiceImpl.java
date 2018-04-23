package com.abc12366.uc.service.invoice.impl;

import com.abc12366.gateway.exception.ServiceException;
import com.abc12366.gateway.util.Utils;
import com.abc12366.uc.mapper.db1.InvoiceDistributeMapper;
import com.abc12366.uc.mapper.db1.InvoiceUseDetailMapper;
import com.abc12366.uc.mapper.db2.InvoiceDistributeRoMapper;
import com.abc12366.uc.model.invoice.InvoiceApprovalLog;
import com.abc12366.uc.model.invoice.InvoiceDistribute;
import com.abc12366.uc.model.invoice.bo.InvoiceDistributeBO;
import com.abc12366.uc.model.invoice.bo.InvoiceUseCheckBO;
import com.abc12366.uc.service.invoice.InvoiceDistributeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @create 2017-05-15 10:17 AM
 * @since 1.0.0
 */
@Service
public class InvoiceUseDistributeServiceImpl implements InvoiceDistributeService {

    private static final Logger LOGGER = LoggerFactory.getLogger(InvoiceUseDistributeServiceImpl.class);

    @Autowired
    private InvoiceDistributeRoMapper invoiceDistributeRoMapper;

    @Autowired
    private InvoiceDistributeMapper invoiceDistributeMapper;

    @Autowired
    private InvoiceUseDetailMapper invoiceUseDetailMapper;

    @Override
    public List<InvoiceDistributeBO> selectList(InvoiceDistributeBO applyBO) {
        return invoiceDistributeRoMapper.selectList(applyBO);
    }

    @Override
    public InvoiceDistributeBO selectInvoiceDistribute(InvoiceDistribute invoiceDistribute) {
        return invoiceDistributeRoMapper.selectInvoiceDistribute(invoiceDistribute.getId());
    }

    @Override
    public void delete(String id) {
        InvoiceDistribute invoiceDistribute = invoiceDistributeRoMapper.selectByPrimaryKey(id);
        if(invoiceDistribute == null){
            LOGGER.warn("删除失败，参数{}：" + invoiceDistribute);
            throw new ServiceException(4103);
        }
        int delete = invoiceDistributeMapper.delete(id);
        if(delete != 1){
            LOGGER.warn("删除失败，参数{}：" + id);
            throw new ServiceException(4103);
        }
        invoiceUseDetailMapper.deleteByUseId(invoiceDistribute.getUseId());
//        if(dDelete != 1){
//            LOGGER.warn("删除失败，参数{}：" + id);
//            throw new ServiceException(4103);
//        }
    }

    @Override
    public InvoiceDistributeBO add(InvoiceDistributeBO invoiceDistributeBO) {
        if(invoiceDistributeBO == null){
            LOGGER.warn("数据错误{}：" + null);
            throw new ServiceException(4906);
        }
        String id = Utils.uuid();
        invoiceDistributeBO.setId(id);
        Date date = new Date();
        InvoiceDistribute invoiceDistribute = new InvoiceDistribute();
        BeanUtils.copyProperties(invoiceDistributeBO,invoiceDistribute);
        int insert = invoiceDistributeMapper.insert(invoiceDistribute);
        if(insert != 1){
            LOGGER.warn("新增失败，参数{}：" + invoiceDistributeBO);
            throw new ServiceException(4101);
        }

        //加入日志
        insertLog(id,"","已提交");
        return invoiceDistributeBO;
    }

    private void insertLog(String id,String opinions,String result) {
        InvoiceApprovalLog log = new InvoiceApprovalLog();
        log.setId(Utils.uuid());
        log.setUseId(id);
        log.setApprovalOpinions(opinions);
        log.setApprovalResult(result);
    }

    @Override
    public InvoiceDistributeBO update(InvoiceDistributeBO invoiceDistributeBO) {
        if(invoiceDistributeBO == null){
            LOGGER.warn("数据错误{}：" + null);
            throw new ServiceException(4906);
        }
        Date date = new Date();
        InvoiceDistribute invoiceDistribute = new InvoiceDistribute();
        BeanUtils.copyProperties(invoiceDistributeBO,invoiceDistribute);
        int update = invoiceDistributeMapper.update(invoiceDistribute);
        if(update != 1){
            LOGGER.warn("修改失败，参数{}：" + invoiceDistributeBO);
            throw new ServiceException(4102);
        }
        //加入日志
        return invoiceDistributeBO;
    }

    @Override
    public void checkUseApplay(InvoiceUseCheckBO invoiceUseCheckBO) {
        if(invoiceUseCheckBO == null){
            LOGGER.warn("数据错误{}：" + null);
            throw new ServiceException(4906);
        }
        Date date = new Date();
        invoiceUseCheckBO.setApplyTime(date);
        InvoiceDistribute invoiceDistribute = new InvoiceDistribute();
        BeanUtils.copyProperties(invoiceUseCheckBO, invoiceDistribute);
        int update = invoiceDistributeMapper.update(invoiceDistribute);
        if(update != 1){
            LOGGER.warn("修改失败，参数{}：" + invoiceUseCheckBO);
            throw new ServiceException(4102);
        }
        String check = invoiceUseCheckBO.getExamineStatus();
        /**审批状态，0：待审核，1：审核通过，2：审核不通过，3：草稿**/
        if(check.equals("1")){
            insertLog(invoiceDistribute.getId(),invoiceUseCheckBO.getRemark(),"审核通过");
        }else if(check.equals("2")){
            insertLog(invoiceDistribute.getId(),invoiceUseCheckBO.getRemark(),"审核不通过");
        }
        //加入日志
    }

    @Override
    public void distributeUseApply(InvoiceUseCheckBO invoiceUseCheckBO) {

    }
}
