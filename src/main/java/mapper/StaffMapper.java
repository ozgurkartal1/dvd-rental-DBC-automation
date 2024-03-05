package mapper;

import model.Staff;
import utils.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

public class StaffMapper implements RowMapper<Staff> {
    @Override
    public Staff mapRow(ResultSet rs) throws SQLException {
        return new Staff(createMap(rs));
    }

    @Override
    public Map<String, Object> createMap(ResultSet rs) throws SQLException {
        return RowMapper.getStringObjectMap(rs);
    }
}
