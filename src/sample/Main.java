package sample;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;

import java.util.LinkedHashMap;
import java.util.Map;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.scene.canvas.Canvas;

public class Main extends Application {
    Canvas CV;
    String className = "CSc22000";
    SQLHistogram CSc22000;

    static Connection connection = null;
    static String databaseName = "Student";

    @Override
    public void start(Stage primaryStage) /*throws Exception*/ {
        try {
            getConnection(databaseName);
            dropTables(connection);
            createTables(connection);
            populate(connection);
            Map<Character, Integer> grades = getGrades(connection);

            int cWidth = 600, cHeight = 600;
            primaryStage.setTitle("SQL PieChart");
            BorderPane BP = new BorderPane();
            CV = addCanvas(cWidth, cHeight, grades);
            BP.setCenter(CV);
            Text topText = new Text("Displaying Grade Frequencies of: " + className);
            BorderPane.setAlignment(topText, Pos.TOP_CENTER);
            BP.setStyle("-fx-padding: 10;");
            BP.setTop(topText);
            Scene SC = new Scene(BP, cWidth + 200, cHeight + 200);
            primaryStage.setScene(SC);
            primaryStage.show();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    private Canvas addCanvas(int cWidth, int cHeight, Map<Character, Integer> grades) throws IOException {
        Canvas CV = new Canvas(cWidth, cHeight);
        CSc22000 = new SQLHistogram(grades);
        CSc22000.drawPieChart(CV.getGraphicsContext2D());
        return CV;
    }
    public static void getConnection(String databaseName) throws SQLException, ClassNotFoundException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        String url = "jdbc:mysql://localhost:3306/" + databaseName;
        String username = "root";
        String password = "Dolfin.499";
        connection = DriverManager.getConnection(url, username, password);
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
        //Student 6
        stuStatement.setString(1, "Steve");
        stuStatement.setString(2, "Jone");
        stuStatement.setString(3, "sjones000@citymail.cuny.edu");
        stuStatement.setString(4, "M");
        //Student 7
        stuStatement.setString(1, "Jack");
        stuStatement.setString(2, "Ryan");
        stuStatement.setString(3, "jryan000@citymail.cuny.edu");
        stuStatement.setString(4, "M");
        //Student 8
        stuStatement.setString(1, "Selena");
        stuStatement.setString(2, "Martinez");
        stuStatement.setString(3, "smartinez000@citymail.cuny.edu");
        stuStatement.setString(4, "F");
        //Student 9
        stuStatement.setString(1, "Jordana");
        stuStatement.setString(2, "Gomez");
        stuStatement.setString(3, "sgomez000@citymail.cuny.edu");
        stuStatement.setString(4, "F");
        //Student 10
        stuStatement.setString(1, "Dave");
        stuStatement.setString(2, "Stock");
        stuStatement.setString(3, "dstock000@citymail.cuny.edu");
        stuStatement.setString(4, "M");
        //Student 11
        stuStatement.setString(1, "Jules");
        stuStatement.setString(2, "Olen");
        stuStatement.setString(3, "jolen000@citymail.cuny.edu");
        stuStatement.setString(4, "F");
        //Student 12
        stuStatement.setString(1, "Jack");
        stuStatement.setString(2, "Ryan");
        stuStatement.setString(3, "jryan001@citymail.cuny.edu");
        stuStatement.setString(4, "M");
        //Student 13
        stuStatement.setString(1, "Jordana");
        stuStatement.setString(2, "Smith");
        stuStatement.setString(3, "jsmith000@citymail.cuny.edu");
        stuStatement.setString(4, "F");
        //Student 14
        stuStatement.setString(1, "Rick");
        stuStatement.setString(2, "Roll");
        stuStatement.setString(3, "rroll000@citymail.cuny.edu");
        stuStatement.setString(4, "M");
        //Student 15
        stuStatement.setString(1, "Trey");
        stuStatement.setString(2, "Anastasio");
        stuStatement.setString(3, "aanastasio000@citymail.cuny.edu");
        stuStatement.setString(4, "M");
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
        //Class 16
        classStatement.setInt(1, 1);
        classStatement.setInt(2, 15);
        classStatement.setInt(3, 1);
        classStatement.setInt(4, 2020);
        classStatement.setString(5, "Fall");
        classStatement.setString(6, "A");
        classStatement.executeUpdate();
        //Class 17
        classStatement.setInt(1, 1);
        classStatement.setInt(2, 14);
        classStatement.setInt(3, 1);
        classStatement.setInt(4, 2020);
        classStatement.setString(5, "Fall");
        classStatement.setString(6, "D");
        classStatement.executeUpdate();
        //Class 18
        classStatement.setInt(1, 1);
        classStatement.setInt(2, 13);
        classStatement.setInt(3, 1);
        classStatement.setInt(4, 2020);
        classStatement.setString(5, "Fall");
        classStatement.setString(6, "B");
        classStatement.executeUpdate();
        //Class 19
        classStatement.setInt(1, 1);
        classStatement.setInt(2, 12);
        classStatement.setInt(3, 1);
        classStatement.setInt(4, 2020);
        classStatement.setString(5, "Fall");
        classStatement.setString(6, "A");
        classStatement.executeUpdate();
        //Class 20
        classStatement.setInt(1, 1);
        classStatement.setInt(2, 11);
        classStatement.setInt(3, 1);
        classStatement.setInt(4, 2020);
        classStatement.setString(5, "Fall");
        classStatement.setString(6, "B");
        classStatement.executeUpdate();
        //Class 21
        classStatement.setInt(1, 1);
        classStatement.setInt(2, 10);
        classStatement.setInt(3, 2);
        classStatement.setInt(4, 2020);
        classStatement.setString(5, "Fall");
        classStatement.setString(6, "C");
        classStatement.executeUpdate();
        //Class 22
        classStatement.setInt(1, 1);
        classStatement.setInt(2, 9);
        classStatement.setInt(3, 2);
        classStatement.setInt(4, 2020);
        classStatement.setString(5, "Fall");
        classStatement.setString(6, "B");
        classStatement.executeUpdate();
        //Class 23
        classStatement.setInt(1, 1);
        classStatement.setInt(2, 8);
        classStatement.setInt(3, 2);
        classStatement.setInt(4, 2020);
        classStatement.setString(5, "Fall");
        classStatement.setString(6, "A");
        classStatement.executeUpdate();
        //Class 24
        classStatement.setInt(1, 1);
        classStatement.setInt(2, 7);
        classStatement.setInt(3, 2);
        classStatement.setInt(4, 2020);
        classStatement.setString(5, "Fall");
        classStatement.setString(6, "A");
        classStatement.executeUpdate();
        //Class 25
        classStatement.setInt(1, 1);
        classStatement.setInt(2, 6);
        classStatement.setInt(3, 2);
        classStatement.setInt(4, 2020);
        classStatement.setString(5, "Fall");
        classStatement.setString(6, "W");
        classStatement.executeUpdate();
        //Class 26
        classStatement.setInt(1, 2);
        classStatement.setInt(2, 15);
        classStatement.setInt(3, 1);
        classStatement.setInt(4, 2020);
        classStatement.setString(5, "Fall");
        classStatement.setString(6, "A");
        classStatement.executeUpdate();
        //Class 27
        classStatement.setInt(1, 3);
        classStatement.setInt(2, 10);
        classStatement.setInt(3, 1);
        classStatement.setInt(4, 2020);
        classStatement.setString(5, "Fall");
        classStatement.setString(6, "B");
        classStatement.executeUpdate();
        //Class 28
        classStatement.setInt(1, 4);
        classStatement.setInt(2, 9);
        classStatement.setInt(3, 1);
        classStatement.setInt(4, 2020);
        classStatement.setString(5, "Fall");
        classStatement.setString(6, "F");
        classStatement.executeUpdate();
        //Class 29
        classStatement.setInt(1, 5);
        classStatement.setInt(2, 8);
        classStatement.setInt(3, 1);
        classStatement.setInt(4, 2020);
        classStatement.setString(5, "Fall");
        classStatement.setString(6, "B");
        classStatement.executeUpdate();
        //Class 30
        classStatement.setInt(1, 2);
        classStatement.setInt(2, 7);
        classStatement.setInt(3, 1);
        classStatement.setInt(4, 2020);
        classStatement.setString(5, "Fall");
        classStatement.setString(6, "A");
        classStatement.executeUpdate();
    }
    public static Map<Character, Integer> getGrades(Connection connection) throws SQLException {
        String gradeQuery = "SELECT grade FROM Classes WHERE course = 1 AND semester = \"Fall\" AND year = 2020;";
        PreparedStatement searchGrades = connection.prepareStatement(gradeQuery);
        ResultSet RS = searchGrades.executeQuery();
        Map<Character, Integer> grades = new LinkedHashMap<>();
        //Initialize Frequency of each grade to 0
        grades.put('A', 0); grades.put('B', 0); grades.put('C', 0);
        grades.put('D', 0); grades.put('F', 0); grades.put('W', 0);
        while(RS.next()){
            //Increment current grade frequency
            String currGrade = RS.getString("grade");
            char gradeKey = currGrade.charAt(0);
            grades.replace(gradeKey, grades.get(gradeKey) + 1);
        }
        return grades;
    }
    public static void main(String[] args) {
        launch(args);
    }
}