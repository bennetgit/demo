package com.wfc.cxf.core.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class IdTest1 implements Serializable {
    private static final long serialVersionUID = 5989556471534989833L;

    @Column(nullable = false)
    private Long userId;

    @Column(nullable = false)
    private Long shipId;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getShipId() {
        return shipId;
    }

    public void setShipId(Long shipId) {
        this.shipId = shipId;
    }

}
