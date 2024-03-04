package utils;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;


public interface RowMapper<T>{
    T mapRow(ResultSet rs) throws SQLException;

    Map<String, Object> createMap(ResultSet rs) throws SQLException;

}
