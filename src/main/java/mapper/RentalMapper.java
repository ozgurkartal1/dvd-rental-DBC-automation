package mapper;

import model.Film;
import model.Rental;
import utils.DBUtils;
import utils.RowMapper;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.*;

public class RentalMapper implements RowMapper<Rental> {
    public Rental mapRow(ResultSet rs) throws SQLException {
        return new Rental(createMap(rs));
    }

    public Map<String, Object> createMap(ResultSet rs) throws SQLException {
        ResultSetMetaData rsmd = rs.getMetaData();
        Map<String, Object> map = new HashMap<>();

        for (int i = 1; i <= rsmd.getColumnCount(); i++) {
            map.put(rsmd.getColumnName(i), rs.getObject(i));
        }

        return map;
    }
}
