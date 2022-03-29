package com.example.lab09;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;

public class HelloApplication extends Application {
    //Read data from URL
    public ArrayList<Float> downloadStockPrices(String symbol) throws IOException {
        URL url = new URL("https://query1.finance.yahoo.com/v7/finance/download/" +
                symbol +
                "?period1=1262322000&" +
                "period2=1451538000&" +
                "interval=1mo&" +
                "events=history&" +
                "includeAdjustedClose=true");
        URLConnection conn = url.openConnection();
        conn.setDoOutput(false);
        conn.setDoInput(true);
        InputStream is = conn.getInputStream();

        //Read data
        String data = "";
        int i;
        while((i = is.read())!= -1) {
            data += (char)i;
        }

        //Get stock closing price values
        String[] rows = data.split("\n");
        ArrayList<Float> closeData = new ArrayList<Float>();
        for(int j = 1; j < rows.length; ++j) {
            String[] cells = rows[j].split(",");
            closeData.add(Float.parseFloat(cells[4]));
        }

        return closeData;
    }

    //Create LineChart
    public LineChart<Number,Number> drawLinePlot(ArrayList<Float> list1, ArrayList<Float> list2) {
        //Axis
        NumberAxis xAxis = new NumberAxis();
        NumberAxis yAxis = new NumberAxis();

        //Create LineChart
        LineChart<Number,Number> lineChart = new LineChart<Number,Number>(xAxis, yAxis);
        lineChart.setMinWidth(650-50);
        lineChart.setMinHeight(500-50);
        lineChart.setLegendVisible(false);
        lineChart.setCreateSymbols(false);
        lineChart.setVerticalGridLinesVisible(false);
        lineChart.setHorizontalGridLinesVisible(false);
        lineChart.getXAxis().setOpacity(0);
        lineChart.getYAxis().setOpacity(0);

        //Call plotLine()
        XYChart.Series line1 = plotLine(list1);
        XYChart.Series line2 = plotLine(list2);

        //Add plotLine data
        lineChart.getData().add(line1);
        lineChart.getData().add(line2);

        //Change line colours
        Node line1_colour = line1.getNode().lookup(".chart-series-line");
        line1_colour.setStyle("-fx-stroke: rgba(" + "255,0,0" + ", 1.0);");
        Node line2_colour = line2.getNode().lookup(".chart-series-line");
        line2_colour.setStyle("-fx-stroke: rgba(" + "0,0,255" + ", 1.0);");

        return lineChart;
    }

    //Create lines from data
    public XYChart.Series plotLine(ArrayList<Float> list) {
        XYChart.Series series = new XYChart.Series();
        for(int i = 0; i < list.size(); ++i) {
            series.getData().add(new XYChart.Data(i, list.get(i)));
        }
        return series;
    }

    @Override
    public void start(Stage stage) throws IOException {
        ArrayList<Float> GOOG = downloadStockPrices("GOOG");
        ArrayList<Float> AAPL = downloadStockPrices("AAPL");
        LineChart<Number,Number> chart = drawLinePlot(GOOG, AAPL);

        HBox hbox = new HBox(chart);
        hbox.setPadding(new Insets(50,0,50,0));
        Scene scene = new Scene(hbox, 650, 500);
        stage.setTitle("Lab 09 Solution");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}