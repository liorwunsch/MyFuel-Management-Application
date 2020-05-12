//vlad
//simple ref to sql showing how to use if not exist
package ref;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class mysqlConnection {

	public static void main(String[] args) 
	{
		try 
		{
            Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
            System.out.println("Driver definition succeed");
        } catch (Exception ex) {
        	/* handle the error*/
        	 System.out.println("Driver definition failed");
        	 }
        
        try 
        {
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/test?serverTimezone=IST","root","1234");
            //Connection conn = DriverManager.getConnection("jdbc:mysql://192.168.3.68/test","root","Root");
            System.out.println("SQL connection succeed");
            
            //-------------------------------------------------------------------------
            createTableCourses(conn);
            createTableCourses(conn);
            insertCourses(conn,1,"shitHOT","s2");
            printCourses(conn);
            //----------------------------------------------------------------------------
     	} catch (SQLException ex) 
     	    {/* handle any errors*/
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
            }
   	}
	
	public static void printCourses(Connection con)
	{
		Statement stmt;
		try 
		{
			stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM courses;");
	 		while(rs.next())
	 		{
				 // Print out the values
				 System.out.println(rs.getString(1)+"  " +rs.getString(2) +"  " +rs.getString(3));
			} 
			rs.close();
			//stmt.executeUpdate("UPDATE course SET semestr=\"W08\" WHERE num=61309");
		} catch (SQLException e) {e.printStackTrace();}
	}

	
	public static void createTableCourses(Connection con1){
		Statement stmt;
		try {
			stmt = con1.createStatement();
			stmt.executeUpdate("create table if not exists courses(num int, name VARCHAR(40), semestr VARCHAR(10));");
			//stmt.executeUpdate("load data local infile \"courses.txt\" into table courses");
	 		
		} catch (SQLException e) {	e.printStackTrace();}
		 		
	}
	
	public static void insertCourses(Connection con1,int num,String name,String semstr) {
		try {
		PreparedStatement insertCourse = con1.prepareStatement("INSERT INTO courses VALUES(?,?, ?)");
		insertCourse.setInt(1, num);
		insertCourse.setString(2,name);
		insertCourse.setString(3,semstr);
		insertCourse.executeUpdate();
		} catch (SQLException e) {	e.printStackTrace();}
	}
	
	
}


