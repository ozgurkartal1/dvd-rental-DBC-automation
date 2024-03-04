package mapper;


import model.Rental;
import utils.RowMapper;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

public class RentalMapper implements RowMapper<Rental> {
    public Rental mapRow(ResultSet rs) throws SQLException {
        return new Rental(createMap(rs));
    }

    public Map<String, Object> createMap(ResultSet rs) throws SQLException {
        return RowMapper.getStringObjectMap(rs);
    }
}
