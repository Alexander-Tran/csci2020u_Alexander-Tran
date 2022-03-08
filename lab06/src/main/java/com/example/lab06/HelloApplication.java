package com.example.lab06;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.chart.*;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {
    //Bar chart data
    private static double[] avgHousingPricesByYear = {
            247381.0,264171.4,287715.3,294736.1,
            308431.4,322635.9,340253.0,363153.7
    };
    private static double[] avgCommercialPricesByYear = {
            1121585.3,1219479.5,1246354.2,1295364.8,
            1335932.6,1472362.0,1583521.9,1613246.3
    };

    //Pie chart data
    private static String[] ageGroups = {
            "18-25", "26-35", "36-45", "46-55", "56-65", "65+"
    };
    private static int[] purchasesByAgeGroup = {
            648, 1021, 2453, 3173, 1868, 2247
    };

//    public static Color[] pieColours = {
//            /*Color.AQUA, Color.GOLD, Color.DARKORANGE,
//            Color.DARKSALMON, Color.LAWNGREEN, Color.PLUM*/
//    };

    private static String[] pieColours = {
            "aqua", "gold", "darkorange", "darksalmon",
            "lawngreen", "plum"
    };

    @Override
    public void start(Stage stage) throws IOException {
        //Bar chart axis
        CategoryAxis xAxis = new CategoryAxis();
        xAxis.setLabel("Year");
        NumberAxis yAxis = new NumberAxis();
        yAxis.setLabel("Average Price");
        BarChart barchart = new BarChart(xAxis, yAxis);

        //Bar chart data
        XYChart.Series data = new XYChart.Series();
        data.setName("Average Housing Price");
        XYChart.Series data2 = new XYChart.Series();
        data2.setName("Average Commercial Price");
        for(int i = 0; i < avgHousingPricesByYear.length; ++i) {
            data.getData().add(new XYChart.Data(Integer.toString(i), avgHousingPricesByYear[i]));
            data2.getData().add(new XYChart.Data(Integer.toString(i), avgCommercialPricesByYear[i]));
        }
        barchart.getData().add(data);
        barchart.getData().add(data2);

        //Bar chart formatting
        barchart.setHorizontalZeroLineVisible(false);
        barchart.setVerticalGridLinesVisible(false);
        barchart.setHorizontalGridLinesVisible(false);
        barchart.setLegendVisible(false);
        barchart.getXAxis().setTickLabelsVisible(false);
        barchart.getXAxis().setTickMarkVisible(false);
        barchart.getXAxis().setOpacity(0);
        barchart.getYAxis().setTickLabelsVisible(false);
        barchart.getYAxis().setTickMarkVisible(false);
        barchart.getYAxis().setOpacity(0);

        //Set bar chart colours
        for(Node n:barchart.lookupAll(".default-color0.chart-bar")) {
            n.setStyle("-fx-bar-fill: red;");
        }
        for(Node n:barchart.lookupAll(".default-color1.chart-bar")) {
            n.setStyle("-fx-bar-fill: blue;");
        }

        //Pie chart data
        ObservableList<PieChart.Data> pieData = FXCollections.observableArrayList();
        for(int i = 0; i < purchasesByAgeGroup.length; ++i) {
            pieData.add(new PieChart.Data(ageGroups[i], purchasesByAgeGroup[i]));
        }

        //Pie chart & formatting
        PieChart piechart = new PieChart(pieData);
        piechart.setData(pieData);
        piechart.setClockwise(false);
        piechart.setLabelsVisible(false);
        piechart.setLegendVisible(false);

        //Pie chart color formatting
        //https://gist.github.com/jewelsea/5095145
        int i = 0;
        for (PieChart.Data x : pieData) {
            x.getNode().setStyle(
                    "-fx-pie-color: " + pieColours[i % pieColours.length] + ";"
            );
            i++;
        }

        HBox hbox = new HBox(barchart, piechart);
        Scene scene = new Scene(hbox);
        stage.setTitle("Lab 06 Solution");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}