package build;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

//import com.mysql.cj.jdbc.Driver;

public class DBUtil implements SqlDao{
	final private String url="jdbc:mysql://localhost:3306/jspdemo?serverTimezone=UTC&useSSL=false";
	final private String user="root";
	final private String pwd="123456";
	final private String driver="com.mysql.cj.jdbc.Driver";
	Connection conn;

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new DBUtil();
	}
	
	public DBUtil() {
		// TODO Auto-generated constructor stub
		try {
			//加载类
			Class.forName(driver);
			//链接数据库
			conn=DriverManager.getConnection(url,user,pwd);
			//预处理sql语句
			String Sql="insert into logintable values(?,?),(?,?)";
			int resultint=this.insertexecute(Sql, new String[] {"361","224","362","134"});
			System.out.println(resultint);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
	}

	@Override
	public ResultSet selectexecute(String Sql, Map<String, String> values) {
		// TODO Auto-generated method stub
		PreparedStatement stmt;
		try {
			StringBuffer stb=new StringBuffer(Sql);
			if(values != null) {
				stb.append(" where ");
				boolean flag=true;
				for(Map.Entry<String,String>entry:values.entrySet()) {
					if(flag) {
						flag=false;
						stb.append(entry.getKey()+"='"+entry.getValue()+"' ");
					}
					stb.append("and "+entry.getKey()+"='"+entry.getValue()+"' ");
				}
			}
			stmt=conn.prepareStatement(stb.toString());
			return stmt.executeQuery();
		}catch(SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public int insertexecute(String Sql,String[] arg) {
		// TODO Auto-generated method stub
		PreparedStatement stmt;
		try {
			stmt=conn.prepareStatement(Sql);
			for(int i=0;i<arg.length;i++)
				stmt.setString(i+1,arg[i]);
			return stmt.executeUpdate();
		}catch(SQLException e) {
			e.printStackTrace();
			return 0;
		}
	}

}
