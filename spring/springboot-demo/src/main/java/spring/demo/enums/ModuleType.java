package spring.demo.enums;

/**
 * Created by wangfacheng on 2017-11-03.
 */
public enum ModuleType implements DBEnum {
    SYSTEM(10, "系统管理"),//
    ;
    private Integer dbConstant;

    private String message;

    ModuleType(Integer dbConstant, String message) {
        this.dbConstant = dbConstant;
        this.message = message;
    }

    @Override
    public Integer getConstant() {
        return dbConstant;
    }

}
