package jdbcprograms;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;


public class SqlCrudOperations {
	
	public static void main(String[] args) throws Exception {
		 String url="jdbc:mysql://localhost:3306/sales_details";
		 String username="root";
		 String password="iiitS@123456";
		 ResultSet rs=null;
		 Connection con=null;
		 try{
		 Class.forName("com.mysql.cj.jdbc.Driver");
		 con=DriverManager.getConnection(url,username,password);
		 System.out.println("Connection established !");
		 createCustomers(con);
		 insertCustomers(con,1,"Kavya","Hyderabad");
		 insertCustomers(con,2,"Sai","Pune");
		 insertCustomers(con,3,"Veera","Bangalore");
		 insertCustomers(con,4,"Asha","Chennai");
		 readCustomers(con,rs);
		 updateCustomers(con,"Nishanth",2);
		 readCustomers(con,rs);
		 deleteCustomers(con,4);
		 readCustomers(con,rs);
		 System.out.println("Connection closed!");
		}
		 catch(Exception e) {
				e.printStackTrace();
			}
		 finally{
			 con.close();
		 }
		
		
	}
	
	
	public static void createCustomers(Connection con) throws Exception {
		String createSQL="create table customers(id int primary key,name varchar(30),city varchar(30))";
		try (PreparedStatement ps=con.prepareStatement(createSQL)){
			int rows = ps.executeUpdate();
			System.out.println("Created " + rows + " customer(s).");
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public static void insertCustomers(Connection con, int id, String name,String city) throws Exception {
		String insertSQL = "INSERT INTO customers (id,name,city) VALUES (?,?,?)";
		if(id==0 || name==null || city==null) {
			throw new Exception("Please enter valid inputs");
		}
		try (PreparedStatement ps = con.prepareStatement(insertSQL)) {
			
			ps.setInt(1, id);
			ps.setString(2, name);
			ps.setString(3, city);
			int rows = ps.executeUpdate();
			System.out.println("Inserted " + rows + " customer(s).");
		} 
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void updateCustomers(Connection con,String name,int id) throws Exception {
		String updateSQL="UPDATE customers SET name=? WHERE id=?";
		try (PreparedStatement ps=con.prepareStatement(updateSQL)){
			ps.setString(1, name);
			ps.setInt(2,id);
			int rows = ps.executeUpdate();
			System.out.println("Updated " + rows + " student(s).");
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public static void deleteCustomers(Connection con,int id) throws Exception {
		String deleteSQL="DELETE FROM customers WHERE Id=?";
		try (PreparedStatement ps=con.prepareStatement(deleteSQL)){
			ps.setInt(1,id);
			int rows = ps.executeUpdate();
			System.out.println("Deleted " + rows + " customer(s).");
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public static void readCustomers(Connection con,ResultSet rs) {
		String readSQL="SELECT * FROM customers";
		try(PreparedStatement ps=con.prepareStatement(readSQL)){
			 rs=ps.executeQuery();
			 while(rs.next()){
				 System.out.println("Customer ID :"+rs.getInt("id"));
			     System.out.println("Customer Name :"+rs.getString("name"));
			     System.out.println("Customer City :"+rs.getString("city"));
			 } 
			
		}
		catch(Exception e)
		 {
			 e.printStackTrace();
		 }	
	}
}
