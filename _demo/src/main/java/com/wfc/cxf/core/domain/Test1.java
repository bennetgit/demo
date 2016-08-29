package com.wfc.cxf.core.domain;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "test")
// @IdClass(IdTest1.class)
public class Test1 {

    // @Id
    // private Long userId;
    //
    // @Id
    // private Long shipId;
    @EmbeddedId
    private IdTest1 id;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "shipId", insertable = false, updatable = false)
    private Shipment shipMent;

    // public Long getUserId() {
    // return userId;
    // }
    //
    // public void setUserId(Long userId) {
    // this.userId = userId;
    // }
    //
    // public Long getShipId() {
    // return shipId;
    // }
    //
    // public void setShipId(Long shipId) {
    // this.shipId = shipId;
    // }
}
