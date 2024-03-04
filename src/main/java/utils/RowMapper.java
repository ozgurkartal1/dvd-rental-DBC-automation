package utils;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;


public interface RowMapper<T>{
    T mapRow(ResultSet rs) throws SQLException;

    Map<String, Object> createMap(ResultSet rs) throws SQLException;

    static Map<String, Object> getStringObjectMap(ResultSet rs) throws SQLException {
        ResultSetMetaData rsmd = rs.getMetaData();
        Map<String, Object> map = new HashMap<>();

        for (int i = 1; i <= rsmd.getColumnCount(); i++) {
            map.put(rsmd.getColumnName(i), rs.getObject(i));
        }

        return map;
    }

}
