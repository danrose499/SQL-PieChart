package sample;

import javafx.scene.canvas.GraphicsContext;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import static java.util.stream.Collectors.toMap;

public class HistogramAlphaBet {
    String filename;
    int totalChars;
    Map<Character, Integer> sortedFrequency;

    HistogramAlphaBet(String filename) throws IOException {
        this.filename = filename;
        getMap();
    }

    public void getMap() throws IOException {
        Path fileName = Path.of(filename);
        String actual = Files.readString(fileName);
        String s = actual.replaceAll("[^a-zA-Z]", "").toLowerCase();
        totalChars = s.length();

        Map<Character, Integer> frequency = new HashMap<>();

        for(int i = 0; i < totalChars; i++) {
            Character Ch = s.charAt(i);
            incrementFrequency(frequency, Ch);
        }

        this.sortedFrequency = frequency
                .entrySet()
                .stream()
                .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                .collect(toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e2, LinkedHashMap::new));
    }

    public static <K> void incrementFrequency(Map<K, Integer> m, K Key) {
        m.putIfAbsent(Key, 0);
        m.put(Key, m.get(Key) + 1);
    }

    public void drawPieChart(GraphicsContext GC, int SlicesToPrint) {
        int cWidth = (int) GC.getCanvas().getWidth();
        int cHeight = (int) GC.getCanvas().getHeight();
        double d = (3.0 * (Math.min(cWidth, cHeight) / 4.0))-50;
        MyPoint arcPoint = new MyPoint(cWidth / 2 - (int) d / 2, cHeight / 2 - (int) d / 2);
        PieChart charChart = new PieChart(arcPoint, d);
        charChart.drawFreq(GC, totalChars, SlicesToPrint, sortedFrequency);
    }
}