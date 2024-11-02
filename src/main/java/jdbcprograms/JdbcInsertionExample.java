package jdbcprograms;

import java.sql.*;

public class JdbcInsertionExample {

	public static void insertStudent(Connection connection, int id, String name, int age) throws Exception {
		String insertSQL = "INSERT INTO student (Id,Name,Age) VALUES (?,?,?)";
		if(id==0 || name==null || age==0) {
			throw new Exception("Please enter valid inputs");
		}
		try (PreparedStatement preparedStatement = connection.prepareStatement(insertSQL)) {
			
			preparedStatement.setInt(1, id);
			preparedStatement.setString(2, name);
			preparedStatement.setInt(3, age);
			int rows = preparedStatement.executeUpdate();
			System.out.println("Inserted " + rows + " student(s).");
		} 
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	public static void updateStudent(Connection con,String newname,int id) throws Exception {
		String update="UPDATE student SET Name=? WHERE Id=?";
		try (PreparedStatement ps=con.prepareStatement(update)){
			ps.setString(1, newname);
			ps.setInt(2,id);
			int rows = ps.executeUpdate();
			System.out.println("Updated " + rows + " student(s).");
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
	}
	public static void deleteStudent(Connection con,int id) throws Exception {
		String update="DELETE FROM student WHERE Id=?";
		try (PreparedStatement ps=con.prepareStatement(update)){
			ps.setInt(1,id);
			int rows = ps.executeUpdate();
			System.out.println("Deleted " + rows + " student(s).");
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
	}
	public static void createCollege(Connection con) throws Exception {
		String create="create table college(college_id int primary key,college_name varchar(30))";
		try (PreparedStatement ps=con.prepareStatement(create)){
			int rows = ps.executeUpdate();
			System.out.println("Created " + rows + " college(s).");
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
	}
	public static void main(String[] args) throws Exception {
		String url = "jdbc:mysql://localhost:3306/ipcs";
		String username = "root";
		String password = "iiitS@123456";
		Connection connection = null;
		Class.forName("com.mysql.cj.jdbc.Driver");
		connection = DriverManager.getConnection(url, username, password);
		System.out.println("Connection established !");
		//insertStudent(connection, 9, "sravan", 24);
		//updateStudent(connection,"sriram",6);
		//deleteStudent(connection,8);
		createCollege(connection);
		
	}

}
