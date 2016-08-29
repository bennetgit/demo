package com.wfc.cxf.core.dto;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.joda.time.LocalDateTime;

import com.wfc.cxf.common.util.DateParseUtil;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class ShipmentDTO implements Serializable {

    private static final long serialVersionUID = -7621516794246663843L;

    @XmlElement
    private Long id;
    @XmlElement
    private Long extNum;
    @XmlElement
    private LocalDateTime createTime;
    @XmlElement
    private String pickUpLoc;
    @XmlElement
    private LocalDateTime pickUpTime;
    @XmlElement
    private LocalDateTime pickUpSLA;
    @XmlElement
    private String delLoc;
    @XmlElement
    private LocalDateTime delTime;
    @XmlElement
    private LocalDateTime delSLA;
    @XmlElement
    private Integer status;

    private String createTimeStr;

    private String pickUpTimeStr;
    private String pickUpSLAStr;
    private String delTimeStr;
    private String delSLAStr;

    public String getPickUpTimeStr() {
        return pickUpTimeStr;
    }

    public void setPickUpTimeStr(String pickUpTimeStr) {
        this.pickUpTime = DateParseUtil.strDate2LocaleTime(pickUpTimeStr);
    }

    public String getPickUpSLAStr() {
        return pickUpSLAStr;
    }

    public void setPickUpSLAStr(String pickUpSLAStr) {

        this.pickUpSLA = DateParseUtil.strDate2LocaleTime(pickUpSLAStr);
    }

    public String getDelTimeStr() {
        return delTimeStr;
    }

    public void setDelTimeStr(String delTimeStr) {

        this.delTime = DateParseUtil.strDate2LocaleTime(delTimeStr);
    }

    public String getDelSLAStr() {
        return delSLAStr;
    }

    public void setDelSLAStr(String delSLAStr) {
        this.delSLA = DateParseUtil.strDate2LocaleTime(delSLAStr);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getExtNum() {
        return extNum;
    }

    public void setExtNum(Long extNum) {
        this.extNum = extNum;
    }

    public String getPickUpLoc() {
        return pickUpLoc;
    }

    public void setPickUpLoc(String pickUpLoc) {
        this.pickUpLoc = pickUpLoc;
    }

    public String getDelLoc() {
        return delLoc;
    }

    public void setDelLoc(String delLoc) {
        this.delLoc = delLoc;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }

    public String getCreateTimeStr() {
        return createTimeStr;
    }

    public void setCreateTimeStr(String createTimeStr) {

        this.createTime = DateParseUtil.strDate2LocaleTime(createTimeStr);
    }

    public LocalDateTime getPickUpTime() {
        return pickUpTime;
    }

    public void setPickUpTime(LocalDateTime pickUpTime) {
        this.pickUpTime = pickUpTime;
    }

    public LocalDateTime getPickUpSLA() {
        return pickUpSLA;
    }

    public void setPickUpSLA(LocalDateTime pickUpSLA) {
        this.pickUpSLA = pickUpSLA;
    }

    public LocalDateTime getDelTime() {
        return delTime;
    }

    public void setDelTime(LocalDateTime delTime) {
        this.delTime = delTime;
    }

    public LocalDateTime getDelSLA() {
        return delSLA;
    }

    public void setDelSLA(LocalDateTime delSLA) {
        this.delSLA = delSLA;
    }

    @Override
    public String toString() {
        return "ShipmentDTO [id=" + id + ", extNum=" + extNum + ", createTime=" + createTime + ", pickUpLoc="
                + pickUpLoc + ", pickUpTime=" + pickUpTime + ", pickUpSLA=" + pickUpSLA + ", delLoc=" + delLoc
                + ", delTime=" + delTime + ", delSLA=" + delSLA + ", status=" + status + ", createTimeStr="
                + createTimeStr + ", pickUpTimeStr=" + pickUpTimeStr + ", pickUpSLAStr=" + pickUpSLAStr
                + ", delTimeStr=" + delTimeStr + ", delSLAStr=" + delSLAStr + "]";
    }

}
