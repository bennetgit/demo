package spring.demo.enums;

/**
 * Created by feng on 17/11/19.
 */
public enum RequestMethod implements DBEnum {
    POST(10), PUT(20), GET(30), DELETE(40);

    private Integer dbConstant;

    RequestMethod(Integer dbConstant) {
        this.dbConstant = dbConstant;
    }

    @Override
    public Integer getConstant() {
        return dbConstant;
    }
}
