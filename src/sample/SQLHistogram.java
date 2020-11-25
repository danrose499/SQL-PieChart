package sample;

import javafx.scene.canvas.Canvas;
import java.util.Map;

public class SQLHistogram {
    int totalStudents;
    Map<Character, Integer> grades;

    SQLHistogram(Map<Character, Integer> grades){
        this.grades = grades;
        int n = 0;
        for(Map.Entry<Character, Integer> entry : grades.entrySet()){
            n += entry.getValue();
        }
        totalStudents = n;
    }

    public void drawPieChart(Canvas CV){
        int cWidth = (int) CV.getWidth();
        int cHeight = (int) CV.getHeight();
        double d = (3.0 * (Math.min(cWidth, cHeight) / 4.0))-50;
        MyPoint arcPoint = new MyPoint(cWidth / 2 - (int) d / 2, cHeight / 2 - (int) d / 2);
        PieChart charChart = new PieChart(arcPoint, d);
        charChart.draw(CV.getGraphicsContext2D(), totalStudents, 6, grades);
    }
}
