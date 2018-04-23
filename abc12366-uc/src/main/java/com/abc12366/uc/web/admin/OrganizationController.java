package com.abc12366.uc.web.admin;

import com.abc12366.gateway.util.Constant;
import com.abc12366.gateway.util.Utils;
import com.abc12366.uc.model.admin.Organization;
import com.abc12366.uc.model.admin.bo.OrganizationBO;
import com.abc12366.uc.model.admin.bo.OrganizationUpdateBO;
import com.abc12366.uc.service.admin.OrganizationService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * 组织管理控制器
 *
 * @author liuguiyao
 * @create 2017-04-27 10:08 AM
 * @since 1.0.0
 */
@Controller
@RequestMapping(path = "/admin/org", headers = Constant.VERSION_HEAD + "=" + Constant.VERSION_1)
public class OrganizationController {

    private static Logger LOGGER = LoggerFactory.getLogger(OrganizationController.class);

    @Autowired
    private OrganizationService organizationService;


    /**
     * 组织机构列表查询
     *
     * @param pageNum  当前页
     * @param pageSize 每页大小
     * @param name     机构名称
     * @param type     类型
     * @param parentId 父机构ID
     * @param status   状态
     * @return ResponseEntity
     */
    @GetMapping
    public ResponseEntity selectList(@RequestParam(value = "page", defaultValue = Constant.pageNum) int pageNum,
                                     @RequestParam(value = "size", defaultValue = Constant.pageSize) int pageSize,
                                     @RequestParam(value = "name", required = false) String name,
                                     @RequestParam(value = "type", required = false) String type,
                                     @RequestParam(value = "parentId", required = false) String parentId,
                                     @RequestParam(value = "status", required = false) Boolean status) {
        Organization organization = new Organization();
        organization.setName(name);
        organization.setStatus(status);
        organization.setType(type);
        organization.setParentId(parentId);
        LOGGER.info("{}", organization);

        PageHelper.startPage(pageNum, pageSize, true).pageSizeZero(true).reasonable(true);
        List<OrganizationBO> organizationList = organizationService.selectList(organization);
        LOGGER.info("{}", organizationList);
        return organizationList == null ?
                ResponseEntity.ok(Utils.kv()) :
                ResponseEntity.ok(Utils.kv("dataList", organizationList, "total", ((Page) organizationList)
                        .getTotal()));
    }

    /**
     * 根据名称查询
     *
     * @param name 名称
     * @return ResponseEntity
     */
    @GetMapping(path = "/name")
    public ResponseEntity selectList(@RequestParam(value = "name", required = false) String name) {
        LOGGER.info("{}", name);
        OrganizationBO bo = organizationService.selectOrganizationByName(name);
        LOGGER.info("{}", bo);
        return ResponseEntity.ok(Utils.kv("date", bo));
    }

    /**
     * 添加组织机构
     *
     * @param organizationBO OrganizationBO
     * @return ResponseEntity
     */
    @PostMapping
    public ResponseEntity addOrganization(@Valid @RequestBody OrganizationBO organizationBO) {
        LOGGER.info("{}", organizationBO);
        Organization organization = organizationService.addOrganization(organizationBO);
        LOGGER.info("{}", organization);
        return ResponseEntity.ok(Utils.kv("data", organization));
    }

    /**
     * 查询单个组织
     *
     * @param id PK
     * @return ResponseEntity
     */
    @GetMapping(path = "/{id}")
    public ResponseEntity selectOne(@PathVariable("id") String id) {
        LOGGER.info("{id}", id);
        OrganizationBO organizationBO = organizationService.selectOrganizationById(id);
        LOGGER.info("{organizationBO}", organizationBO);
        return ResponseEntity.ok(Utils.kv("data", organizationBO));
    }

    /**
     * 查询子机构组织
     *
     * @param id PK
     * @return ResponseEntity
     */
    @GetMapping(path = "/child/{id}")
    public ResponseEntity selectChildOrg(@PathVariable("id") String id) {
        LOGGER.info("{id}", id);
        List<OrganizationBO> list = organizationService.selectChildOrg(id);
        LOGGER.info("{organizationBO}", list);
        return ResponseEntity.ok(Utils.kv("dataList", list));
    }

    /**
     * 修改组织
     *
     * @param id             PK
     * @param organizationBO OrganizationBO
     * @return ResponseEntity
     */
    @PutMapping(path = "/{id}")
    public ResponseEntity updateOrganization(@Valid @RequestBody OrganizationBO organizationBO,
                                             @PathVariable("id") String id) {
        LOGGER.info("id", id);
        OrganizationBO bo = organizationService.updateOrganization(organizationBO);
        LOGGER.info("修改组织成功", bo);
        return ResponseEntity.ok(Utils.kv("data", bo));
    }

    /**
     * 删除组织--物理删除
     *
     * @param id PK
     * @return ResponseEntity
     */
    @DeleteMapping(path = "/{id}")
    public ResponseEntity deleteOrganizationById(@PathVariable("id") String id) {
        LOGGER.info("id", id);
        organizationService.deleteOrganizationById(id);
        LOGGER.info("删除组织成功");
        return ResponseEntity.ok(Utils.kv());
    }

    /**
     * 启用、禁用
     *
     * @param updateBO OrganizationUpdateBO
     * @return ResponseEntity
     */
    @PutMapping(path = "/enable")
    public ResponseEntity enable(@Valid @RequestBody OrganizationUpdateBO updateBO) {
        LOGGER.info("{}", updateBO);
        organizationService.enable(updateBO);
        return ResponseEntity.ok(Utils.kv());
    }

    /**
     * 全部启用、禁用
     *
     * @return ResponseEntity
     */
    @PutMapping(path = "/disableAll")
    public ResponseEntity disableAll() {
        organizationService.disableAll();
        return ResponseEntity.ok(Utils.kv());
    }
}
