package mapper;

import model.Payment;
import utils.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

public class PaymentMapper implements RowMapper<Payment> {


    public Payment mapRow(ResultSet rs) throws SQLException {
        return new Payment(createMap(rs));
    }


    public Map<String, Object> createMap(ResultSet rs) throws SQLException {
        return RowMapper.getStringObjectMap(rs);
    }
}
