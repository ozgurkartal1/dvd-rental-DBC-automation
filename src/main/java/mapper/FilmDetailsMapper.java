package mapper;

import model.FilmDetails;
import utils.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

public class FilmDetailsMapper implements RowMapper<FilmDetails> {
    @Override
    public FilmDetails mapRow(ResultSet rs) throws SQLException {
        return new FilmDetails(createMap(rs));
    }

    @Override
    public Map<String, Object> createMap(ResultSet rs) throws SQLException {
        return RowMapper.getStringObjectMap(rs);
    }
}
