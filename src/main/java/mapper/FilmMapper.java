package mapper;

import model.Film;
import utils.RowMapper;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

public class FilmMapper implements RowMapper<Film> {
    public Film mapRow(ResultSet rs) throws SQLException {

        return new Film(createMap(rs));
    }

    public Map<String, Object> createMap(ResultSet rs) throws SQLException {
        return RowMapper.getStringObjectMap(rs);
    }

}
