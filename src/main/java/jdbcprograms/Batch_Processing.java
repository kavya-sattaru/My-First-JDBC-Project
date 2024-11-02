package jdbcprograms;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Batch_Processing {
	
	public static void processBatch() throws SQLException {
		Connection con=Connection_Pooling.getConnection();
		con.setAutoCommit(false);
		
		String sql="INSERT INTO students (id,name,section) VALUES (?,?,?)";
		PreparedStatement ps=con.prepareStatement(sql);
		
		ps.setInt(1, 10);
		ps.setString(2,"Kavya");
		ps.setString(3, "A");
		ps.addBatch();
		
		ps.setInt(1, 6);
		ps.setString(2,"Sai");
		ps.setString(3, "A");
		ps.addBatch();
		
		ps.setInt(1, 7);
		ps.setString(2,"Asha");
		ps.setString(3, "B");
		ps.addBatch();
		
		ps.setInt(1, 8);
		ps.setString(2,"Chaithu");
		ps.setString(3, "A");
		ps.addBatch();
		
		ps.setInt(1, 9);
		ps.setString(2,"Veera");
		ps.setString(3, "B");
		ps.addBatch();
		
		
		int[] results=ps.executeBatch();
		con.commit();
		System.out.println("Batch inserted successfully");
	}
	public static void main(String[] args) throws SQLException {
		processBatch();
	}
	

}
