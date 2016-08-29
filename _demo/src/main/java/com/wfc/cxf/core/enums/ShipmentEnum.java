package com.wfc.cxf.core.enums;

public enum ShipmentEnum {

    Created("Created", 0), Released("Released", 1), Dispatched("Dispatched", 2), Pickup("Pickup", 3), Delivered(
            "Delivered", 4), Closed("Closed", 5);

    private String status;
    private int num;

    private ShipmentEnum(String status, int num) {
        this.status = status;
        this.num = num;
    }

    public static ShipmentEnum getShipmentEnum(Integer num) {

        switch (num) {
        case 0:
            return ShipmentEnum.Created;
        case 1:
            return ShipmentEnum.Released;
        case 2:
            return ShipmentEnum.Dispatched;
        case 3:
            return ShipmentEnum.Pickup;
        case 4:
            return ShipmentEnum.Delivered;
        case 5:
            return ShipmentEnum.Closed;
        default:
            return null;
        }
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

}
