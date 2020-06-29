package com.vsc.website.vo.frontend;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.vsc.website.vo.backend.BJobOffersNamesVo;
import io.swagger.annotations.ApiModelProperty;
import java.util.Date;
import java.util.List;

public class FJobOffersListVo {
    @ApiModelProperty("id")
    private Integer id;
    @ApiModelProperty("招聘类型id")
    private Integer typeId;
    @ApiModelProperty("招聘类型名")
    private String typeName;
    @ApiModelProperty("工作地址id")
    private Integer addressId;
    @ApiModelProperty("工作地址名")
    private String addressName;
    @ApiModelProperty("招聘人数")
    private Integer number;
    @ApiModelProperty("所属部门id")
    private Integer departmentId;
    @ApiModelProperty("所属部门名")
    private String departmentName;
    @ApiModelProperty("状态01 未发布 已发布")
    private Integer state;
    @ApiModelProperty("操作人")
    private String updateUser;
    @ApiModelProperty("操作时间")
    @JsonFormat(pattern="yyyy-MM-dd", timezone="GMT+8")
    private Date updateAt;
    @ApiModelProperty("语言版本")
    private List<BJobOffersNamesVo> jobOffersNamesVos;

    public List<BJobOffersNamesVo> getJobOffersNamesVos() {
        return jobOffersNamesVos;
    }

    public void setJobOffersNamesVos(List<BJobOffersNamesVo> jobOffersNamesVos) {
        this.jobOffersNamesVos = jobOffersNamesVos;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getTypeId() {
        return typeId;
    }

    public void setTypeId(Integer typeId) {
        this.typeId = typeId;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public Integer getAddressId() {
        return addressId;
    }

    public void setAddressId(Integer addressId) {
        this.addressId = addressId;
    }

    public String getAddressName() {
        return addressName;
    }

    public void setAddressName(String addressName) {
        this.addressName = addressName;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public Integer getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(Integer departmentId) {
        this.departmentId = departmentId;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public String getUpdateUser() {
        return updateUser;
    }

    public void setUpdateUser(String updateUser) {
        this.updateUser = updateUser;
    }

    public Date getUpdateAt() {
        return updateAt;
    }

    public void setUpdateAt(Date updateAt) {
        this.updateAt = updateAt;
    }
}
