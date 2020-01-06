package build;

import java.sql.ResultSet;
import java.util.Map;

public interface SqlDao {
	/**
	 * select
	 * @param Sql
	 * @param values
	 * @return
	 */
	public ResultSet selectexecute(String Sql,Map<String,String> values);
	/**
	 * insert
	 * @param Sql
	 * @param arg
	 * @return
	 */
	public int insertexecute(String Sql,String[] arg);
	

}
