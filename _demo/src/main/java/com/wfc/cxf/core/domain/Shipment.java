package com.wfc.cxf.core.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.Type;
import org.joda.time.LocalDateTime;

import com.wfc.cxf.core.jpa.ShipmentRepositoryJPA;

@Entity
@Table(name = "shipment")
@SequenceGenerator(name = "seq_shipment", sequenceName = "seq_shipment")
@NamedQueries(value = { @NamedQuery(name = ShipmentRepositoryJPA.LOAD_SHIPMENT, query = "SELECT o FROM Shipment o"),
        @NamedQuery(name = ShipmentRepositoryJPA.GET_SHIPMENT, query = "SELECT o FROM Shipment o WHERE o.id = :id") })
public class Shipment {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_shipment")
    private Long id;

    @Column(name = "ext_num", nullable = false)
    private Long extNum;

    @Column(name = "create_Time", nullable = false)
    @Type(type = "org.joda.time.contrib.hibernate.PersistentLocalDateTime")
    private LocalDateTime createTime;

    @Column(name = "pickup_Loc", nullable = false, length = 32)
    private String pickUpLoc;

    @Column(name = "pickup_time", nullable = true)
    @Type(type = "org.joda.time.contrib.hibernate.PersistentLocalDateTime")
    private LocalDateTime pickUpTime;

    @Column(name = "pickup_sla", nullable = true)
    @Type(type = "org.joda.time.contrib.hibernate.PersistentLocalDateTime")
    private LocalDateTime pickUpSLA;

    @Column(name = "del_Loc", nullable = true, length = 32)
    private String delLoc;

    @Column(name = "del_time", nullable = true)
    @Type(type = "org.joda.time.contrib.hibernate.PersistentLocalDateTime")
    private LocalDateTime delTime;

    @Column(name = "del_sla", nullable = true)
    @Type(type = "org.joda.time.contrib.hibernate.PersistentLocalDateTime")
    private LocalDateTime delSLA;

    @Column(name = "status", nullable = false)
    private Integer status;

    public Shipment() {
        super();
    }

    public Shipment(Long id, Long extNum, LocalDateTime createTime, String pickUpLoc, LocalDateTime pickUpTime,
            LocalDateTime pickUpSLA, String delLoc, LocalDateTime delTime, LocalDateTime delSLA, Integer status) {
        super();
        this.id = id;
        this.extNum = extNum;
        this.createTime = createTime;
        this.pickUpLoc = pickUpLoc;
        this.pickUpTime = pickUpTime;
        this.pickUpSLA = pickUpSLA;
        this.delLoc = delLoc;
        this.delTime = delTime;
        this.delSLA = delSLA;
        this.status = status;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
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

    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }

    public String getPickUpLoc() {
        return pickUpLoc;
    }

    public void setPickUpLoc(String pickUpLoc) {
        this.pickUpLoc = pickUpLoc;
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

    public String getDelLoc() {
        return delLoc;
    }

    public void setDelLoc(String delLoc) {
        this.delLoc = delLoc;
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

}
