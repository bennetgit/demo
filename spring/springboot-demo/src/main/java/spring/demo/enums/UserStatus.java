package spring.demo.enums;

/**
 * Created by wangfacheng on 2017-11-03.
 */
public enum UserStatus implements DBEnum {
    INACTIVE(0, "停用"), ACTIVE(1, "启用");

    private Integer dbConstant;

    private String message;

    UserStatus(Integer dbConstant, String message) {
        this.dbConstant = dbConstant;
        this.message = message;
    }

    @Override
    public Integer getConstant() {
        return dbConstant;
    }

    public String getMessage() {
        return message;
    }
}
