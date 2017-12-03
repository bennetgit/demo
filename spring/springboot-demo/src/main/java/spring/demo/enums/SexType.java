package spring.demo.enums;

/**
 * Created by wangfacheng on 2017-11-03.
 */
public enum SexType implements DBEnum {
    MALE(1, "male"), FEMALE(0, "female");

    private String messageKey;
    private Integer dbConstant;

    SexType(Integer dbConstant, String messageKey) {
        this.dbConstant = dbConstant;
        this.messageKey = messageKey;
    }

    @Override
    public Integer getConstant() {
        return dbConstant;
    }

    public String getMessageKey() {
        return messageKey;
    }
}
