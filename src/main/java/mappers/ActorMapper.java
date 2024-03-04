package mappers;

import model.Actor;
import utils.DBUtils;
import utils.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ActorMapper implements RowMapper<Actor> {
	ResultSet innerRs = DBUtils.getResultset("SELECT * FROM actor");
	public Actor mapRow(ResultSet rs) throws SQLException {
		List<String> columnNameForRs = new ArrayList<>();
		for (int i = 1; i <= rs.getMetaData().getColumnCount(); i++) {
			columnNameForRs.add(rs.getMetaData().getColumnName(i));
		}
		Map<String , Object> columnsObject = new HashMap<>();
		int normalColumnCount = innerRs.getMetaData().getColumnCount();
		int count = 0;
		for (int i = 1; i <= normalColumnCount; i++) {
			String columName = innerRs.getMetaData().getColumnName(i);
			try {
				if(!columnNameForRs.contains(columName)){
                 count++;
				 throw new SQLException();
				}
				columnsObject.put(columName,rs.getObject(i-count));

			}catch (SQLException e){
				columnsObject.put(columName,null);

			}
		}
		return new Actor(columnsObject);
//		return new Actor(rs.getInt("actor_id"), rs.getString("first_name"), rs.getString("last_name"),
//				rs.getTimestamp("last_update"));
	}



}
