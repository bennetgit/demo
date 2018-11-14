package spring.demo.enums;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;

public class DBEnumTypeHandler extends BaseTypeHandler<DBEnum> {

    private Class<DBEnum> type;

    public DBEnumTypeHandler(Class<DBEnum> type) {
        this.type = type;
    }

    @Override
    public void setNonNullParameter(PreparedStatement ps, int i, DBEnum parameter, JdbcType jdbcType)
            throws SQLException {
        ps.setInt(i, parameter.getConstant());
    }

    @Override
    public DBEnum getNullableResult(ResultSet rs, String columnName) throws SQLException {
        int constant = rs.getInt(columnName);
        if (rs.wasNull()) {
            return null;
        } else {
            return convert(constant);
        }
    }

    @Override
    public DBEnum getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
        int constant = rs.getInt(columnIndex);
        if (rs.wasNull()) {
            return null;
        } else {
            return convert(constant);
        }
    }

    @Override
    public DBEnum getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
        int constant = cs.getInt(columnIndex);
        if (cs.wasNull()) {
            return null;
        } else {
            return convert(constant);
        }
    }

    private DBEnum convert(int constant) {
        DBEnum[] DBEnums = type.getEnumConstants();
        for (DBEnum DBEnum : DBEnums) {
            if (DBEnum.getConstant().equals(constant)) {
                return DBEnum;
            }
        }
        return null;
    }

}
