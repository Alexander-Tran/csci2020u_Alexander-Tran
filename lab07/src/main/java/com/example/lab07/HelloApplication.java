package com.example.lab07;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Side;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.stage.Stage;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        BufferedReader csvReader = new BufferedReader(new FileReader("src/main/resources/com/example/lab07/weatherwarnings-2015.csv"));
        //System.out.println(csvReader.lines().count());

        //Read csv, store data in map
        Map<String, Double> warnings = new HashMap<>();
        String line;
        while((line = csvReader.readLine()) != null) {
            String[] data = line.split(",");
            if(warnings.get(data[5]) != null) {
                warnings.put(data[5], warnings.get(data[5])+1);
            }
            else {
                warnings.put(data[5], 1.0);
            }
        }
        csvReader.close();

        //Add PieChart data
        ObservableList<PieChart.Data> pieData = FXCollections.observableArrayList();
        for (Map.Entry entry : warnings.entrySet())
        {
            pieData.add(new PieChart.Data((String) entry.getKey(), (Double) entry.getValue()));
        }

        /*
        //Print PieChart data
        for (Map.Entry entry : warnings.entrySet())
        {
            System.out.println("key: " + entry.getKey() + "; value: " + entry.getValue());
        }*/

        //PieChart formatting
        PieChart piechart = new PieChart(pieData);
        piechart.setLabelsVisible(false);
        piechart.setClockwise(false);
        piechart.setLegendSide(Side.LEFT);

        //Display scene
        Scene scene = new Scene(piechart, 500, 300);
        stage.setTitle("Lab 07 Solution");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}