package sample;

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
    static Connection connection = null;
    static String databaseName = "Student";

    @Override
    public void start(Stage primaryStage) {
        try {
            connection = getConnection(databaseName);
            dropTables(connection);
            createTables(connection);
            populate(connection);
            String tableUpdate = "UPDATE Classes SET year = \"2020\", semester = \"Fall\" where course = 1 AND student = 9;";
            updateTable(connection, tableUpdate);
            Map<Character, Integer> grades = getGrades(connection);

            int cWidth = 600, cHeight = 600;
            primaryStage.setTitle("SQL PieChart");
            BorderPane BP = new BorderPane();
            CV = addCanvas(cWidth, cHeight, grades);
            BP.setCenter(CV);
            Text topText = new Text("Displaying Grade Frequencies of: CSc 22000");
            BorderPane.setAlignment(topText, Pos.TOP_CENTER);
            BP.setStyle("-fx-padding: 10;");
            BP.setTop(topText);
            Scene SC = new Scene(BP, cWidth + 200, cHeight + 200);
            primaryStage.setScene(SC);
            primaryStage.show();

            connection.close();

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    private Canvas addCanvas(int cWidth, int cHeight, Map<Character, Integer> grades) {
        Canvas CV = new Canvas(cWidth, cHeight);
        SQLHistogram CSc22000 = new SQLHistogram(grades);
        CSc22000.drawPieChart(CV.getGraphicsContext2D());
        return CV;
    }
    public static Connection getConnection(String databaseName) throws SQLException, ClassNotFoundException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        String url = "jdbc:mysql://localhost:3306/" + databaseName;
        String username = "root";
        String password = "Dolfin.499";
        return DriverManager.getConnection(url, username, password);
    }
    public static void dropTables(Connection connection) throws SQLException {
        //Drops Tables:
        PreparedStatement dropStudents = connection.prepareStatement("DROP TABLE IF EXISTS Students");
        PreparedStatement dropCourses = connection.prepareStatement("DROP TABLE IF EXISTS Courses");
        PreparedStatement dropClasses = connection.prepareStatement("DROP TABLE IF EXISTS Classes");
        dropStudents.executeUpdate();
        dropCourses.executeUpdate();
        dropClasses.executeUpdate();
        dropStudents.close();
        dropCourses.close();
        dropClasses.close();
    }
    public static void createTables(Connection connection) throws SQLException {
        //Creates Tables:
        PreparedStatement createStudents = connection.prepareStatement("CREATE TABLE Students(studentID int unsigned NOT NULL AUTO_INCREMENT, firstName varchar(255), lastName varchar(255), email varchar(255), sex varchar(1), PRIMARY KEY(studentID))");
        PreparedStatement createCourses = connection.prepareStatement("CREATE TABLE Courses(courseID int unsigned NOT NULL AUTO_INCREMENT, courseTitle varchar(255), department varchar(5), PRIMARY KEY(courseID))");
        PreparedStatement createClasses = connection.prepareStatement("CREATE TABLE Classes(course int unsigned NOT NULL REFERENCES Courses(courseID), student int unsigned NOT NULL REFERENCES Students(studentID), sectionNo int unsigned NOT NULL, year int unsigned, semester varchar(6), grade varchar(4), PRIMARY KEY(course, student, sectionNo))");
        createStudents.executeUpdate();
        createCourses.executeUpdate();
        createClasses.executeUpdate();
        createStudents.close();
        createCourses.close();
        createClasses.close();
    }
    public static void populate(Connection connection) throws SQLException {
        //Populates Tables - Declares prepared statements for each table:
        String insertStudent = "INSERT INTO Students(firstName, lastName, email, sex) VALUES(?,?,?,?)";
        String insertCourse = "INSERT INTO Courses(courseTitle, department) VALUES(?,?)";
        String insertClass = "INSERT INTO Classes(course, student, sectionNo, year, semester, grade) VALUES(?,?,?,?,?,?)";
        PreparedStatement stuStatement = connection.prepareStatement(insertStudent);
        PreparedStatement courseStatement = connection.prepareStatement(insertCourse);
        PreparedStatement classStatement = connection.prepareStatement(insertClass);
        //Fills Students:
        String[] gender = {"M", "F"};
        for(int i = 0; i < 101; i++){
            stuStatement.setString(1, "Alex_" + i);
            stuStatement.setString(2, "Smith_" + i);
            stuStatement.setString(3, "alex.smith_" + i + "@CityMail.cuny.edu");
            stuStatement.setString(4, gender[i%2]);
            stuStatement.executeUpdate();
        }
        //Fill Courses:
        for(int i = 22000; i < 22500; i+=100){
            courseStatement.setString(1, String.valueOf(i));
            courseStatement.setString(2, "CSc");
            courseStatement.executeUpdate();
            courseStatement.setString(1, String.valueOf(i));
            courseStatement.setString(2, "EE");
            courseStatement.executeUpdate();
            courseStatement.setString(1, String.valueOf(i));
            courseStatement.setString(2, "THTR");
            courseStatement.executeUpdate();
        }
        //Fills Classes:
        String[] grades = {"A", "A", "A", "A", "A", "A", "A", "B", "B", "B", "B", "C", "C", "C", "D", "D", "F", "W"}; //18 grades
        //Fall 2020
        for(int i = 0; i < 101; i++){
            classStatement.setInt(1, (i%15+1));
            classStatement.setInt(2, (i%101+1));
            classStatement.setInt(3, (i%4));
            classStatement.setInt(4, 2020);
            classStatement.setString(5, "Fall");
            classStatement.setString(6, grades[i%18]);
            classStatement.executeUpdate();
        }
        for(int i = 0; i < 101; i++){
            classStatement.setInt(1, (i%15+1));
            classStatement.setInt(2, ((i+1)%101+1));
            classStatement.setInt(3, (i%4));
            classStatement.setInt(4, 2020);
            classStatement.setString(5, "Fall");
            classStatement.setString(6, grades[(i+1)%18]);
            classStatement.executeUpdate();
        }
        for(int i = 0; i < 101; i++){
            classStatement.setInt(1, (i%15+1));
            classStatement.setInt(2, ((i+2)%101+1));
            classStatement.setInt(3, (i%4));
            classStatement.setInt(4, 2020);
            classStatement.setString(5, "Fall");
            classStatement.setString(6, grades[(i+2)%18]);
            classStatement.executeUpdate();
        }
        //Spring 2021
        for(int i = 0; i < 101; i++){
            classStatement.setInt(1, (i%15+1));
            classStatement.setInt(2, ((i+3)%101+1));
            classStatement.setInt(3, (i%4));
            classStatement.setInt(4, 2021);
            classStatement.setString(5, "Spring");
            classStatement.setString(6, grades[i%18]);
            classStatement.executeUpdate();
        }
        stuStatement.close();
        courseStatement.close();
        classStatement.close();
    }
    public static void updateTable(Connection connection, String SQLQuery) throws SQLException {
        PreparedStatement updateQuery = connection.prepareStatement(SQLQuery);
        updateQuery.executeUpdate();
        updateQuery.close();
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
        searchGrades.close();
        RS.close();
        return grades;
    }
    public static void main(String[] args) {
        launch(args);
    }
}
