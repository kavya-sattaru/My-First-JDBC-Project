package jdbcprograms;
import org.apache.commons.dbcp2.BasicDataSource;

import java.sql.Connection;
import java.sql.SQLException;

public class Connection_Pooling {
	private static BasicDataSource ds=new BasicDataSource();
	static {
		ds.setUrl("jdbc:mysql://localhost:3306/school");
		ds.setUsername("root");
		ds.setPassword("iiitS@123456");
		
		ds.setMinIdle(5);
		ds.setMaxIdle(10);
		ds.setMaxTotal(20);
		ds.setMaxWaitMillis(10000);
	}
	public static Connection getConnection() throws SQLException {
		return ds.getConnection();
	}
	public static void close() throws SQLException {
		ds.close();
	}
	
}
