import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Driver {
    static Connection connection = null;
    static String databaseName = "Student";
    static String url = "jdbc:mysql://localhost:3306/" + databaseName;

    static String username = "root";
    static String password = "Dolfin.499";

    public static void main(String[] args) throws Exception {
        Class.forName("com.mysql.cj.jdbc.Driver");
        connection = DriverManager.getConnection(url, username, password);
        populate(connection);

    }
    public static void populate(Connection connection) throws SQLException {
        //Creates Tables (If they don't already exist):
        PreparedStatement createStudents = connection.prepareStatement("CREATE TABLE IF NOT EXISTS Students(studentID int unsigned NOT NULL AUTO_INCREMENT, firstName varchar(255), lastName varchar(255), email varchar(255), sex varchar(255), PRIMARY KEY(studentID))");
        PreparedStatement createCourses = connection.prepareStatement("CREATE TABLE IF NOT EXISTS Courses(courseID int unsigned NOT NULL AUTO_INCREMENT, courseTitle varchar(255), department varchar(255), PRIMARY KEY(courseID))");
        PreparedStatement createClasses = connection.prepareStatement("CREATE TABLE IF NOT EXISTS Classes(courseID int unsigned NOT NULL, studentID int unsigned NOT NULL, sectionNo int unsigned NOT NULL, year int unsigned, semester varchar(255), grade varchar(255), PRIMARY KEY(courseID, studentID, sectionNo))");
        createStudents.executeUpdate();
        createCourses.executeUpdate();
        createClasses.executeUpdate();
        //Populates Tables:
    }
}

//        PreparedStatement ps = connection.prepareStatement("INSERT INTO `Student`.`Students` (`firstName`) VALUES ('Jack');");
//        int status = ps.executeUpdate();
//
//        if (status != 0){
//            System.out.println("Database was connected!");
//        }
