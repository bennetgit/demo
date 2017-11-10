package spring.demo.enums;

/**
 * Created by wangfacheng on 2017-11-03.
 */
public enum SexType implements DBEnum {
    MALE(1), FEMALE(0);

    private Integer dbConstant;

    SexType(Integer dbConstant) {
        this.dbConstant = dbConstant;
    }

    @Override
    public Integer getConstant() {
        return dbConstant;
    }

}
