import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Driver {
    static Connection connection = null;
    static String databaseName = "Student";
    static String url = "jdbc:mysql://localhost:3306/" + databaseName;

    static String username = "root";
    static String password = "password";

    public static void main(String[] args) throws Exception {
        Class.forName("com.mysql.cj.jdbc.Driver");
        connection = DriverManager.getConnection(url, username, password);
        dropTables(connection);
        createTables(connection);
        populate(connection);
    }
    public static void dropTables(Connection connection) throws SQLException {
        //Drops Tables:
        PreparedStatement dropStudents = connection.prepareStatement("DROP TABLE IF EXISTS Students");
        PreparedStatement dropCourses = connection.prepareStatement("DROP TABLE IF EXISTS Courses");
        PreparedStatement dropClasses = connection.prepareStatement("DROP TABLE IF EXISTS Classes");
        dropStudents.executeUpdate();
        dropCourses.executeUpdate();
        dropClasses.executeUpdate();
    }
    public static void createTables(Connection connection) throws SQLException {
        //Creates Tables:
        PreparedStatement createStudents = connection.prepareStatement("CREATE TABLE Students(studentID int unsigned NOT NULL AUTO_INCREMENT, firstName varchar(255), lastName varchar(255), email varchar(255), sex varchar(255), PRIMARY KEY(studentID))");
        PreparedStatement createCourses = connection.prepareStatement("CREATE TABLE Courses(courseID int unsigned NOT NULL AUTO_INCREMENT, courseTitle varchar(255), department varchar(255), PRIMARY KEY(courseID))");
        PreparedStatement createClasses = connection.prepareStatement("CREATE TABLE Classes(course int unsigned NOT NULL REFERENCES Courses(courseID), student int unsigned NOT NULL REFERENCES Students(studentID), sectionNo int unsigned NOT NULL, year int unsigned, semester varchar(255), grade varchar(255), PRIMARY KEY(course, student, sectionNo))");
        createStudents.executeUpdate();
        createCourses.executeUpdate();
        createClasses.executeUpdate();
    }
    public static void populate(Connection connection) throws SQLException {
        //Populates Tables - Declares prepared statements for each table:
        String insertStudent = "INSERT INTO Students(firstName, lastName, email, sex) VALUES(?,?,?,?)";
        String insertCourse = "INSERT INTO Courses(courseTitle, department) VALUES(?,?)";
        String insertClass = "INSERT INTO Classes(course, student, sectionNo, year, semester, grade) VALUES(?,?,?,?,?,?)";
        PreparedStatement stuStatement = connection.prepareStatement(insertStudent);
        PreparedStatement courseStatement = connection.prepareStatement(insertCourse);
        PreparedStatement classStatement = connection.prepareStatement(insertClass);
        //Fill Students:
        //Student 1
        stuStatement.setString(1, "Daniel");
        stuStatement.setString(2, "Rosenthal");
        stuStatement.setString(3, "drosent000@citymail.cuny.edu");
        stuStatement.setString(4, "M");
        stuStatement.executeUpdate();
        //Student 2
        stuStatement.setString(1, "Bruce");
        stuStatement.setString(2, "Wayne");
        stuStatement.setString(3, "bwayne000@citymail.cuny.edu");
        stuStatement.setString(4, "M");
        stuStatement.executeUpdate();
        //Student 3
        stuStatement.setString(1, "Barbara");
        stuStatement.setString(2, "Gordon");
        stuStatement.setString(3, "bgordon000@citymail.cuny.edu");
        stuStatement.setString(4, "F");
        stuStatement.executeUpdate();
        //Student 4
        stuStatement.setString(1, "Jay");
        stuStatement.setString(2, "Harvey");
        stuStatement.setString(3, "jharvey000@citymail.cuny.edu");
        stuStatement.setString(4, "U");
        stuStatement.executeUpdate();
        //Student 5
        stuStatement.setString(1, "Pamela");
        stuStatement.setString(2, "Gold");
        stuStatement.setString(3, "pgold000@citymail.cuny.edu");
        stuStatement.setString(4, "F");
        stuStatement.executeUpdate();
        //Fill Courses:
        //Course 1
        courseStatement.setString(1, "22000");
        courseStatement.setString(2, "CSc");
        courseStatement.executeUpdate();
        //Course 2
        courseStatement.setString(1, "33000");
        courseStatement.setString(2, "EE");
        courseStatement.executeUpdate();
        //Course 3
        courseStatement.setString(1, "24100");
        courseStatement.setString(2, "EE");
        courseStatement.executeUpdate();
        //Course 4
        courseStatement.setString(1, "13100");
        courseStatement.setString(2, "THTR");
        courseStatement.executeUpdate();
        //Course 5
        courseStatement.setString(1, "47200");
        courseStatement.setString(2, "CSc");
        courseStatement.executeUpdate();
        //Fill Classes:
        //Class 1
        classStatement.setInt(1, 1);
        classStatement.setInt(2, 1);
        classStatement.setInt(3, 1);
        classStatement.setInt(4, 2020);
        classStatement.setString(5, "Fall");
        classStatement.setString(6, "A");
        classStatement.executeUpdate();
        //Class 2
        classStatement.setInt(1, 1);
        classStatement.setInt(2, 2);
        classStatement.setInt(3, 1);
        classStatement.setInt(4, 2020);
        classStatement.setString(5, "Fall");
        classStatement.setString(6, "B");
        classStatement.executeUpdate();
        //Class 3
        classStatement.setInt(1, 1);
        classStatement.setInt(2, 3);
        classStatement.setInt(3, 1);
        classStatement.setInt(4, 2020);
        classStatement.setString(5, "Fall");
        classStatement.setString(6, "C");
        classStatement.executeUpdate();
        //Class 4
        classStatement.setInt(1, 1);
        classStatement.setInt(2, 4);
        classStatement.setInt(3, 2);
        classStatement.setInt(4, 2020);
        classStatement.setString(5, "Fall");
        classStatement.setString(6, "D");
        classStatement.executeUpdate();
        //Class 5
        classStatement.setInt(1, 1);
        classStatement.setInt(2, 5);
        classStatement.setInt(3, 2);
        classStatement.setInt(4, 2020);
        classStatement.setString(5, "Fall");
        classStatement.setString(6, "F");
        classStatement.executeUpdate();
        //Class 6
        classStatement.setInt(1, 2);
        classStatement.setInt(2, 1);
        classStatement.setInt(3, 1);
        classStatement.setInt(4, 2020);
        classStatement.setString(5, "Fall");
        classStatement.setString(6, "W");
        classStatement.executeUpdate();
        //Class 7
        classStatement.setInt(1, 2);
        classStatement.setInt(2, 2);
        classStatement.setInt(3, 2);
        classStatement.setInt(4, 2020);
        classStatement.setString(5, "Fall");
        classStatement.setString(6, "A");
        classStatement.executeUpdate();
        //Class 8
        classStatement.setInt(1, 3);
        classStatement.setInt(2, 3);
        classStatement.setInt(3, 1);
        classStatement.setInt(4, 2020);
        classStatement.setString(5, "Fall");
        classStatement.setString(6, "C");
        classStatement.executeUpdate();
        //Class 9
        classStatement.setInt(1, 3);
        classStatement.setInt(2, 4);
        classStatement.setInt(3, 2);
        classStatement.setInt(4, 2020);
        classStatement.setString(5, "Fall");
        classStatement.setString(6, "C");
        classStatement.executeUpdate();
        //Class 10
        classStatement.setInt(1, 3);
        classStatement.setInt(2, 5);
        classStatement.setInt(3, 2);
        classStatement.setInt(4, 2020);
        classStatement.setString(5, "Fall");
        classStatement.setString(6, "B");
        classStatement.executeUpdate();
        //Class 11
        classStatement.setInt(1, 4);
        classStatement.setInt(2, 1);
        classStatement.setInt(3, 1);
        classStatement.setInt(4, 2020);
        classStatement.setString(5, "Fall");
        classStatement.setString(6, "A");
        classStatement.executeUpdate();
        //Class 12
        classStatement.setInt(1, 4);
        classStatement.setInt(2, 2);
        classStatement.setInt(3, 2);
        classStatement.setInt(4, 2020);
        classStatement.setString(5, "Fall");
        classStatement.setString(6, "B");
        classStatement.executeUpdate();
        //Class 13
        classStatement.setInt(1, 5);
        classStatement.setInt(2, 3);
        classStatement.setInt(3, 1);
        classStatement.setInt(4, 2020);
        classStatement.setString(5, "Fall");
        classStatement.setString(6, "A");
        classStatement.executeUpdate();
        //Class 14
        classStatement.setInt(1, 5);
        classStatement.setInt(2, 4);
        classStatement.setInt(3, 2);
        classStatement.setInt(4, 2020);
        classStatement.setString(5, "Fall");
        classStatement.setString(6, "C");
        classStatement.executeUpdate();
        //Class 15
        classStatement.setInt(1, 5);
        classStatement.setInt(2, 5);
        classStatement.setInt(3, 3);
        classStatement.setInt(4, 2020);
        classStatement.setString(5, "Fall");
        classStatement.setString(6, "W");
        classStatement.executeUpdate();
    }
}
