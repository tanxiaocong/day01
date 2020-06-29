package com.vsc.website.qo.backend;

import io.swagger.annotations.ApiModelProperty;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

public class BJobOffersAddQo {

    @ApiModelProperty("工作岗位类型")
    @NotNull
    private Integer typeId;

    @ApiModelProperty("工作地点id")
    @NotNull
    private Integer addressId;

    @ApiModelProperty("招聘数量")
    @NotNull
    private Integer number;

    @ApiModelProperty("所属部门id")
    @NotNull
    private Integer departmentId;

    @ApiModelProperty("状态01 未发布已发布")
    @NotNull
    private Integer state;

    @ApiModelProperty("语言版本")
    @Valid
    private List<BJobOffersNamesQo> jobOffersNamesQos;

    public Integer getTypeId() {
        return typeId;
    }

    public void setTypeId(Integer typeId) {
        this.typeId = typeId;
    }

    public Integer getAddressId() {
        return addressId;
    }

    public void setAddressId(Integer addressId) {
        this.addressId = addressId;
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

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public List<BJobOffersNamesQo> getJobOffersNamesQos() {
        return jobOffersNamesQos;
    }

    public void setJobOffersNamesQos(List<BJobOffersNamesQo> jobOffersNamesQos) {
        this.jobOffersNamesQos = jobOffersNamesQos;
    }
}
