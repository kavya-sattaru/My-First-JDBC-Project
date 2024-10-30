package jdbcprograms;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class jdbcexample {
	public static void main(String[] args) throws SQLException {
		 Scanner sc=new Scanner(System.in);
		 String url="jdbc:mysql://localhost:3306/ipcs";
		 String username="root";
		 String password="iiitS@123456";
		 Connection connection=null;
		 Statement statement=null;
		 ResultSet resultSet=null;
		 ResultSet resultSetPrepared=null;
		 PreparedStatement preparedStatement=null;
		 try{
			 Class.forName("com.mysql.cj.jdbc.Driver");
			 connection=DriverManager.getConnection(url,username,password);
			 System.out.println("Connection established !");
			 statement=connection.createStatement();
			 resultSet=statement.executeQuery("SELECT * FROM student");
			 while(resultSet.next()){
			 int Id=resultSet.getInt("Id");
			 String Name=resultSet.getString("Name");
			 int Age=resultSet.getInt("Age");
			 System.out.println(Id+"  "+Name+"  "+Age);
			 }
			 System.out.println();
			 preparedStatement=connection.prepareStatement("SELECT * FROM student WHERE id=? and age=?");
			 preparedStatement.setInt(1,sc.nextInt());
			 preparedStatement.setInt(2,sc.nextInt());
			 resultSetPrepared=preparedStatement.executeQuery();
			 while(resultSetPrepared.next()){
				 System.out.println("Student ID :"+resultSetPrepared.getInt("Id"));
			     System.out.println("Student Name :"+resultSetPrepared.getString("Name"));
			     System.out.println("Student Age :"+resultSetPrepared.getInt("Age"));
			 } 
		 }
		 catch(Exception e)
		 {
			 e.printStackTrace();
		 }
		 finally {
			 resultSet.close();
			 statement.close();
			 resultSetPrepared.close();
			 preparedStatement.close();
			 connection.close();
			 sc.close();
			 System.out.println("Connection closed!");
		 }
	}
	

}
