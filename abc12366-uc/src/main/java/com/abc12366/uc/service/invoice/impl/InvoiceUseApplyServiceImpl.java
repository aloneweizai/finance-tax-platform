package com.abc12366.uc.service.invoice.impl;

import com.abc12366.gateway.exception.ServiceException;
import com.abc12366.gateway.util.Utils;
import com.abc12366.uc.mapper.db1.*;
import com.abc12366.uc.mapper.db2.InvoiceDistributeRoMapper;
import com.abc12366.uc.mapper.db2.InvoiceRepoRoMapper;
import com.abc12366.uc.mapper.db2.InvoiceUseApplyRoMapper;
import com.abc12366.uc.mapper.db2.InvoiceUseDetailRoMapper;
import com.abc12366.uc.model.invoice.*;
import com.abc12366.uc.model.invoice.bo.InvoiceRepoBO;
import com.abc12366.uc.model.invoice.bo.InvoiceUseApplyBO;
import com.abc12366.uc.model.invoice.bo.InvoiceUseCheckBO;
import com.abc12366.uc.model.invoice.bo.InvoiceUseDetailBO;
import com.abc12366.uc.service.invoice.InvoiceUseApplyService;
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
public class InvoiceUseApplyServiceImpl implements InvoiceUseApplyService {

    private static final Logger LOGGER = LoggerFactory.getLogger(InvoiceUseApplyServiceImpl.class);

    @Autowired
    private InvoiceUseApplyRoMapper invoiceUseApplyRoMapper;

    @Autowired
    private InvoiceUseApplyMapper invoiceUseApplyMapper;

    @Autowired
    private InvoiceRepoRoMapper invoiceRepoRoMapper;

    @Autowired
    private InvoiceRepoMapper invoiceRepoMapper;

    @Autowired
    private InvoiceDetailMapper invoiceDetailMapper;
    @Autowired
    private InvoiceUseDetailMapper invoiceUseDetailMapper;

    @Autowired
    private InvoiceUseDetailRoMapper invoiceUseDetailRoMapper;

    @Autowired
    private InvoiceDistributeMapper invoiceDistributeMapper;

    @Autowired
    private InvoiceDistributeRoMapper invoiceDistributeRoMapper;

    @Autowired
    private InvoiceApprovalLogMapper invoiceApprovalLogMapper;

    @Override
    public List<InvoiceUseApplyBO> selectList(InvoiceUseApplyBO applyBO) {
        return invoiceUseApplyRoMapper.selectList(applyBO);
    }

    @Override
    public InvoiceUseApplyBO selectInvoiceUseApply(InvoiceUseApply invoiceUseApply) {
        return invoiceUseApplyRoMapper.selectInvoiceUseApply(invoiceUseApply.getId());
    }

    @Override
    public void delete(String id) {
        int delete = invoiceUseApplyMapper.delete(id);
        if(delete != 1){
            LOGGER.warn("删除失败，参数{}：" + id);
            throw new ServiceException(4103);
        }
        invoiceUseDetailMapper.deleteByUseId(id);
        invoiceApprovalLogMapper.deleteByUseId(id);
    }

    @Override
    public InvoiceUseApplyBO add(InvoiceUseApplyBO invoiceUseApplyBO) {
        if(invoiceUseApplyBO == null){
            LOGGER.warn("数据错误{}：" + null);
            throw new ServiceException(4906);
        }
        String id = Utils.uuid();
        invoiceUseApplyBO.setId(id);
        Date date = new Date();
        invoiceUseApplyBO.setApplyUser(Utils.getAdminId());
        invoiceUseApplyBO.setApplyTime(date);
        InvoiceUseApply invoiceUseApply = new InvoiceUseApply();
        BeanUtils.copyProperties(invoiceUseApplyBO,invoiceUseApply);
        int insert = invoiceUseApplyMapper.insert(invoiceUseApply);
        if(insert != 1){
            LOGGER.warn("新增失败，参数{}：" + invoiceUseApplyBO);
            throw new ServiceException(4101);
        }
        List<InvoiceUseDetailBO> invoiceUseDetailBOList = invoiceUseApplyBO.getInvoiceUseDetailBOList();
        if(invoiceUseDetailBOList == null || invoiceUseDetailBOList.size()<1){
            LOGGER.warn("数据错误{}：" + null);
            throw new ServiceException(4906);
        }
        for (InvoiceUseDetailBO detailBO:invoiceUseDetailBOList){
            detailBO.setId(Utils.uuid());
            detailBO.setUseId(id);
            InvoiceUseDetail invoiceUseDetail = new InvoiceUseDetail();
            BeanUtils.copyProperties(detailBO,invoiceUseDetail);
            int dInsert = invoiceUseDetailMapper.insert(invoiceUseDetail);
            if(dInsert != 1){
                LOGGER.warn("新增失败，参数{}：" + invoiceUseDetail);
                throw new ServiceException(4101);
            }
        }

        //加入日志，草稿状态不需要记录日志
        if(!"3".equals(invoiceUseApplyBO.getExamineStatus())){
            insertLog(id,"申请", Utils.getAdminInfo().getNickname(),"已提交");
        }
        return invoiceUseApplyBO;
    }

    private void insertLog(String id,String opinions,String nickName,String result) {
        InvoiceApprovalLog log = new InvoiceApprovalLog();
        log.setId(Utils.uuid());
        log.setUseId(id);
        log.setApprover(nickName);
        log.setApprovalOpinions(opinions);
        log.setApprovalResult(result);
        log.setApproverTime(new Date());
        invoiceApprovalLogMapper.insert(log);
    }

    @Override
    public InvoiceUseApplyBO update(InvoiceUseApplyBO invoiceUseApplyBO) {
        if(invoiceUseApplyBO == null){
            LOGGER.warn("数据错误{}：" + null);
            throw new ServiceException(4906);
        }
        Date date = new Date();
        invoiceUseApplyBO.setApplyTime(date);
        InvoiceUseApply invoiceUseApply = new InvoiceUseApply();
        BeanUtils.copyProperties(invoiceUseApplyBO,invoiceUseApply);
        int update = invoiceUseApplyMapper.update(invoiceUseApply);
        if(update != 1){
            LOGGER.warn("修改失败，参数{}：" + invoiceUseApplyBO);
            throw new ServiceException(4102);
        }
        List<InvoiceUseDetailBO> invoiceUseDetailBOList = invoiceUseApplyBO.getInvoiceUseDetailBOList();
        if(invoiceUseDetailBOList == null || invoiceUseDetailBOList.size()<1){
            LOGGER.warn("数据错误{}：" + null);
            throw new ServiceException(4906);
        }
        //根据UseId删除申请详情日志
        invoiceUseDetailMapper.deleteByUseId(invoiceUseApplyBO.getId());

        for (InvoiceUseDetailBO detailBO:invoiceUseDetailBOList){
            detailBO.setId(Utils.uuid());
            detailBO.setUseId(invoiceUseApplyBO.getId());
            InvoiceUseDetail invoiceUseDetail = new InvoiceUseDetail();
            BeanUtils.copyProperties(detailBO,invoiceUseDetail);
            int dInsert = invoiceUseDetailMapper.insert(invoiceUseDetail);
            if(dInsert != 1){
                LOGGER.warn("新增失败，参数{}：" + invoiceUseDetail);
                throw new ServiceException(4101);
            }
        }
        //加入日志，草稿状态不需要记录日志
        if(!"3".equals(invoiceUseApplyBO.getExamineStatus())){
            insertLog(invoiceUseApply.getId(),"申请", Utils.getAdminInfo().getNickname(),"已提交");
        }
        return invoiceUseApplyBO;
    }

    @Override
    public void checkUseApplay(InvoiceUseCheckBO invoiceUseCheckBO) {
        if(invoiceUseCheckBO == null){
            LOGGER.warn("数据错误{}：" + null);
            throw new ServiceException(4906);
        }
        Date date = new Date();
        invoiceUseCheckBO.setApplyTime(date);
        InvoiceUseApply invoiceUseApply = new InvoiceUseApply();
        BeanUtils.copyProperties(invoiceUseCheckBO, invoiceUseApply);
        int update = invoiceUseApplyMapper.update(invoiceUseApply);
        if(update != 1){
            LOGGER.warn("修改失败，参数{}：" + invoiceUseCheckBO);
            throw new ServiceException(4102);
        }

        String check = invoiceUseCheckBO.getExamineStatus();
        /**审批状态，0：待审核，1：审核通过，2：审核不通过，3：草稿**/
        List<InvoiceUseDetailBO> boList = invoiceUseCheckBO.getInvoiceUseDetailBOList();
        for(InvoiceUseDetailBO bo:boList){
            InvoiceUseDetail invoiceUseDetail = new InvoiceUseDetail();
            BeanUtils.copyProperties(bo,invoiceUseDetail);
            int dUpdate = invoiceUseDetailMapper.update(invoiceUseDetail);
            if(dUpdate != 1){
                LOGGER.warn("修改失败，参数{}：" + invoiceUseDetail);
                throw new ServiceException(4102);
            }
        }
        if(check.equals("1")){
            insertLog(invoiceUseApply.getId(),invoiceUseCheckBO.getCheckOpinion(), Utils.getAdminInfo().getNickname(),"审核通过");
        }else if(check.equals("2")){
            insertLog(invoiceUseApply.getId(),invoiceUseCheckBO.getCheckOpinion(), Utils.getAdminInfo().getNickname(),"审核不通过");
        }
        //加入日志
    }

    @Override
    public void distributeUseApply(InvoiceUseCheckBO invoiceUseCheckBO) {
        InvoiceUseApply invoiceUseApply = new InvoiceUseApply();
        BeanUtils.copyProperties(invoiceUseCheckBO,invoiceUseApply);
        invoiceUseApply.setDistributeTime(new Date());
        invoiceUseApply.setDistributeUser(Utils.getAdminId());
        invoiceUseApply.setIssueStatus("1");
        int aUpdate = invoiceUseApplyMapper.update(invoiceUseApply);
        if(aUpdate != 1){
            LOGGER.warn("修改失败，参数{}：" + invoiceUseCheckBO);
            throw new ServiceException(4102);
        }
        List<InvoiceUseDetailBO> useDetailBOList = invoiceUseCheckBO.getInvoiceUseDetailBOList();
        for(InvoiceUseDetailBO detailBO:useDetailBOList){
            InvoiceUseDetail invoiceUseDetail = new InvoiceUseDetail();
            BeanUtils.copyProperties(detailBO,invoiceUseDetail);
            int dUpdate = invoiceUseDetailMapper.update(invoiceUseDetail);
            if(dUpdate != 1){
                LOGGER.warn("修改失败，参数{}：" + invoiceUseDetail);
                throw new ServiceException(4102);
            }
            String[] invoiceRepoIds = detailBO.getInvoiceRepoIds();
            for(String repoId : invoiceRepoIds){
                InvoiceRepoBO repoBO = invoiceRepoRoMapper.selectInvoiceRepo(repoId);
                if(repoBO == null){
                    LOGGER.warn("发票不存在{}：" + repoBO);
                    throw new ServiceException(4907);
                }
                Date date = new Date();
                InvoiceDistribute invoiceDistribute = new InvoiceDistribute();
                invoiceDistribute.setId(Utils.uuid());
                invoiceDistribute.setInvoiceRepoId(repoBO.getId());
                invoiceDistribute.setInvoiceCode(repoBO.getInvoiceCode());
                invoiceDistribute.setInvoiceNoStart(repoBO.getInvoiceNoStart());
                invoiceDistribute.setInvoiceNoEnd(repoBO.getInvoiceNoEnd());
                invoiceDistribute.setShare(repoBO.getShare());
                invoiceDistribute.setStatus("1");
                invoiceDistribute.setBook(repoBO.getBook());
                invoiceDistribute.setInvoiceTypeCode(repoBO.getInvoiceTypeCode());
                invoiceDistribute.setDistributeUser(invoiceUseCheckBO.getDistributeUser());
                invoiceDistribute.setDistributeTime(date);
                invoiceDistribute.setUseId(invoiceUseCheckBO.getId());
                invoiceDistribute.setRemark(detailBO.getRemark());
                int insert = invoiceDistributeMapper.insert(invoiceDistribute);
                if(insert != 1){
                    LOGGER.warn("新增失败，参数{}：" + invoiceDistribute);
                    throw new ServiceException(4101);
                }

                //更新发票库存状态
                InvoiceRepo invoiceRepo = new InvoiceRepo();
                invoiceRepo.setUpdateUser(Utils.getAdminId());
                invoiceRepo.setLastUpdate(date);
                invoiceRepo.setId(invoiceDistribute.getInvoiceRepoId());
                invoiceRepo.setStatus("1");
                invoiceRepoMapper.update(invoiceRepo);
                //更新发票明细的状态
                InvoiceDetail invoiceDetail = new InvoiceDetail();
                invoiceDetail.setInvoiceRepoId(invoiceDistribute.getInvoiceRepoId());
                invoiceDetail.setLastUpdate(date);
                invoiceDetail.setStatus("0");
                invoiceDetailMapper.updateByRepoId(invoiceDetail);

            }
        }
    }

    @Override
    public void signUseApply(String id) {
        List<InvoiceDistribute> invoiceDistributeList = invoiceDistributeRoMapper.selectInvoiceDistributeListByUseId(id);
        Date date = new Date();
        for(InvoiceDistribute invoiceDistribute:invoiceDistributeList){
            invoiceDistribute.setStatus("2");
            invoiceDistribute.setSignTime(date);
            invoiceDistribute.setSignUser(Utils.getAdminId());
            //修改发票分发信息表
            int update = invoiceDistributeMapper.update(invoiceDistribute);
            if(update != 1){
                LOGGER.warn("修改失败，参数{}：" + invoiceDistribute);
                throw new ServiceException(4102);
            }
            //更新发票领用申请表状态
            InvoiceUseApply invoiceUseApply = new InvoiceUseApply();
            invoiceUseApply.setId(invoiceDistribute.getUseId());
            invoiceUseApply.setIssueStatus("2");
            invoiceUseApply.setSignTime(date);
            invoiceUseApply.setSignUser(Utils.getAdminId());
            invoiceUseApplyMapper.update(invoiceUseApply);
            //更新发票详情信息，改成，3：已签收
            InvoiceDetail invoiceDetail = new InvoiceDetail();
            invoiceDetail.setLastUpdate(date);
            invoiceDetail.setInvoiceRepoId(invoiceDistribute.getInvoiceRepoId());
            invoiceDetail.setStatus("3");
            invoiceDetailMapper.updateByRepoId(invoiceDetail);

        }
    }

    @Override
    public InvoiceUseDetailBO selectInvoiceRepoNum(String code) {
        return invoiceUseDetailRoMapper.selectInvoiceRepoNum(code);
    }

    @Override
    public Integer selectTodoListCount() {
        return invoiceUseApplyRoMapper.selectTodoListCount();
    }
}
