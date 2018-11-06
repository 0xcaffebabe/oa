package wang.ismy.oa.dao.handler;

import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import wang.ismy.oa.enums.CheckingEnum;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CheckingTypeHandler extends BaseTypeHandler<CheckingEnum> {
    @Override
    public void setNonNullParameter(PreparedStatement preparedStatement, int i, CheckingEnum checkingEnum, JdbcType jdbcType) throws SQLException {

    }

    @Override
    public CheckingEnum getNullableResult(ResultSet resultSet, String s) throws SQLException {
        Boolean ret=resultSet.getBoolean(s);

        return ret?CheckingEnum.ON_DUTY:CheckingEnum.OFF_DUTY;
    }

    @Override
    public CheckingEnum getNullableResult(ResultSet resultSet, int i) throws SQLException {
        Boolean ret=resultSet.getBoolean(i);

        return ret?CheckingEnum.ON_DUTY:CheckingEnum.OFF_DUTY;
    }

    @Override
    public CheckingEnum getNullableResult(CallableStatement callableStatement, int i) throws SQLException {
        Boolean ret=callableStatement.getBoolean(i);

        return ret?CheckingEnum.ON_DUTY:CheckingEnum.OFF_DUTY;
    }
}
