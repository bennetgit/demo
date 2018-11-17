package spring.demo.enums;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import org.joda.time.LocalDateTime;

public class LocalDateTimeTypeHandler extends BaseTypeHandler<LocalDateTime> {

    @Override
    public void setNonNullParameter(PreparedStatement ps, int i, LocalDateTime parameter, JdbcType jdbcType)
            throws SQLException {
        java.util.Date date = parameter.toDate();
        Timestamp timestamp = new Timestamp(date.getTime());
        ps.setTimestamp(i, timestamp);
    }

    @Override
    public LocalDateTime getNullableResult(ResultSet rs, String columnName) throws SQLException {
        LocalDateTime localDateTime = null;
        if (null != rs.getTimestamp(columnName)) {
            Timestamp timestamp = rs.getTimestamp(columnName);
            localDateTime = new LocalDateTime(timestamp.getTime());
        }
        return localDateTime;
    }

    @Override
    public LocalDateTime getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
        LocalDateTime localDateTime = null;
        if (null != rs.getTimestamp(columnIndex)) {
            localDateTime = new LocalDateTime(rs.getTimestamp(columnIndex).getTime());
        }
        return localDateTime;
    }

    @Override
    public LocalDateTime getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
        LocalDateTime localDateTime = null;
        if (null != cs.getTimestamp(columnIndex)) {
            localDateTime = new LocalDateTime(cs.getTimestamp(columnIndex).getTime());
        }
        return localDateTime;

    }

}
