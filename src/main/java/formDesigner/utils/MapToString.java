package formDesigner.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

public class MapToString extends BaseTypeHandler<Map> {
    @Override
    public void setNonNullParameter(PreparedStatement preparedStatement, int i, Map map, JdbcType jdbcType) throws SQLException {
        preparedStatement.setString(i, JSON.toJSONString(map));
    }

    @Override
    public Map getNullableResult(ResultSet resultSet, String s) throws SQLException {
        return JSONObject.parseObject(resultSet.getString(s));
    }

    @Override
    public Map getNullableResult(ResultSet resultSet, int i) throws SQLException {
        return JSONObject.parseObject(resultSet.getString(i));
    }

    @Override
    public Map getNullableResult(CallableStatement callableStatement, int i) throws SQLException {
        return JSONObject.parseObject(callableStatement.getString(i));
    }
}
