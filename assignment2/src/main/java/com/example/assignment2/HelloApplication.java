//Alexander Tran - 100788359
//Nicholas Kissoon - 100742790

package com.example.assignment2;

import javafx.application.Application;
import javafx.geometry.Side;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException, ParserConfigurationException, TransformerException {
        //Part 1
        //Read csv file
        BufferedReader csvReader = new BufferedReader(new FileReader("src/main/resources/airline_safety.csv"));
        String line;

        //Store csv data in ArrayList
        ArrayList<String> csvData = new ArrayList<>();

        //Add header to csvData
        csvData.add(csvReader.readLine() + ",total_incidents_85_14");

        while((line = csvReader.readLine()) != null) {
            //Read csv data
            String[] data = line.split(",");

            //Create new csv column
            String[] copy = Arrays.copyOf(data, data.length + 1);

            //Calculate total number of incidents between 1985 and 2014 for airline(excludes fatalities, fatal accidents)
            copy[data.length] = Integer.toString((Integer.parseInt(data[2])+Integer.parseInt(data[5])));

            //Copy csv data with new column into ArrayList
            csvData.add(Arrays.toString(copy));
        }

        //Write converted csv data to xml file
        DocumentBuilderFactory dbFactory1 = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder1 = dbFactory1.newDocumentBuilder();
        Document doc1 = dBuilder1.newDocument();

        Element rootElement1 = doc1.createElement("Converted_Airline_Safety");
        doc1.appendChild(rootElement1);

        for(int j = 1; j < csvData.size(); ++j) {
            Element stat = doc1.createElement("Safety_Data");
            rootElement1.appendChild(stat);

            Element col0 = doc1.createElement(csvData.get(0).split(",")[0]);
            col0.appendChild(doc1.createTextNode(csvData.get(j).split(",")[0]
                            .replaceAll("\\[", "").trim()));
            stat.appendChild(col0);

            Element col1 = doc1.createElement(csvData.get(0).split(",")[1]);
            col1.appendChild(doc1.createTextNode(csvData.get(j).split(",")[1].trim()));
            stat.appendChild(col1);

            Element col2 = doc1.createElement(csvData.get(0).split(",")[2]);
            col2.appendChild(doc1.createTextNode(csvData.get(j).split(",")[2].trim()));
            stat.appendChild(col2);

            Element col3 = doc1.createElement(csvData.get(0).split(",")[3]);
            col3.appendChild(doc1.createTextNode(csvData.get(j).split(",")[3].trim()));
            stat.appendChild(col3);

            Element col4 = doc1.createElement(csvData.get(0).split(",")[4]);
            col4.appendChild(doc1.createTextNode(csvData.get(j).split(",")[4].trim()));
            stat.appendChild(col4);

            Element col5 = doc1.createElement(csvData.get(0).split(",")[5]);
            col5.appendChild(doc1.createTextNode(csvData.get(j).split(",")[5].trim()));
            stat.appendChild(col5);

            Element col6 = doc1.createElement(csvData.get(0).split(",")[6]);
            col6.appendChild(doc1.createTextNode(csvData.get(j).split(",")[6].trim()));
            stat.appendChild(col6);

            Element col7 = doc1.createElement(csvData.get(0).split(",")[7]);
            col7.appendChild(doc1.createTextNode(csvData.get(j).split(",")[7].trim()));
            stat.appendChild(col7);

            Element col8 = doc1.createElement(csvData.get(0).split(",")[8]);
            col8.appendChild(doc1.createTextNode(csvData.get(j).split(",")[8]
                    .replaceAll("\\]", "").trim()));
            stat.appendChild(col8);
        }

        //Create xml file
        TransformerFactory transformerFactory1 = TransformerFactory.newInstance();
        Transformer transformer1 = transformerFactory1.newTransformer();
        DOMSource source1 = new DOMSource(doc1);
        StreamResult result1 = new StreamResult(new File("src/main/resources/converted_airline_safety.xml"));
        transformer1.transform(source1, result1);
        transformer1.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
        transformer1.setOutputProperty(OutputKeys.INDENT, "yes");
        transformer1. transform(source1, result1);

        //Test output in console
        //StreamResult consoleResult1 = new StreamResult(System.out);
        //transformer1.transform(source1, consoleResult1);


        //-----------------------------------------------------------------------------------------------------------
        //Part 2
        ArrayList<Double> avail_seat_km_per_week = new ArrayList<>();
        double avail_seat_km_per_week_avg = 0.0;
        ArrayList<Double> incidents_85_99 = new ArrayList<>();
        double incidents_85_99_avg = 0.0;
        ArrayList<Double> fatal_accidents_85_99 = new ArrayList<>();
        double fatal_accidents_avg = 0.0;
        ArrayList<Double> fatalities_85_99 = new ArrayList<>();
        double fatalities_85_99_avg = 0.0;
        ArrayList<Double> incidents_00_14 = new ArrayList<>();
        double incidents_00_14_avg = 0.0;
        ArrayList<Double> fatal_accidents_00_14 = new ArrayList<>();
        double fatal_accidents_00_14_avg = 0.0;
        ArrayList<Double> fatalities_00_14 = new ArrayList<>();
        double fatalities_00_14_avg = 0.0;
        ArrayList<Double> total_incidents_85_14 = new ArrayList<>();
        double total_incidents_85_14_avg = 0.0;

        float avg_85_99 = 0.0f;
        float avg_00_14 = 0.0f;

        //Create 2d ArrayList to store values
        ArrayList<ArrayList<Double>> arrList = new ArrayList<>();

        //Get csv data
        for(int i = 1; i < csvData.size(); ++i) {
            String[] cells = csvData.get(i).split(",");

            avail_seat_km_per_week.add(Double.parseDouble(cells[1].trim()));
            incidents_85_99.add(Double.parseDouble(cells[2].trim()));
            fatal_accidents_85_99.add(Double.parseDouble(cells[3].trim()));
            fatalities_85_99.add(Double.parseDouble(cells[4].trim()));
            incidents_00_14.add(Double.parseDouble(cells[5].trim()));
            fatal_accidents_00_14.add(Double.parseDouble(cells[6].trim()));
            fatalities_00_14.add(Double.parseDouble(cells[7].trim()));
            total_incidents_85_14.add(Double.parseDouble(cells[8].substring(0, cells[8].length() - 1).trim()));

            avail_seat_km_per_week_avg += Double.parseDouble(cells[1].trim());
            incidents_85_99_avg += Integer.parseInt(cells[2].trim());
            fatal_accidents_avg += Integer.parseInt(cells[3].trim());
            fatalities_85_99_avg += Integer.parseInt(cells[4].trim());
            incidents_00_14_avg += Integer.parseInt(cells[5].trim());
            fatal_accidents_00_14_avg += Integer.parseInt(cells[6].trim());
            fatalities_00_14_avg += Integer.parseInt(cells[7].trim());
            total_incidents_85_14_avg += Integer.parseInt(cells[8].substring(0, cells[8].length() - 1).trim());

            //Part b (Incident column ONLY)
            avg_85_99 += Integer.parseInt(cells[2].trim());
            //Part c (Incident column ONLY)
            avg_00_14 += Integer.parseInt(cells[5].trim());
        }

        //Add column values to 2d ArrayList
        arrList.add(avail_seat_km_per_week);
        arrList.add(incidents_85_99);
        arrList.add(fatal_accidents_85_99);
        arrList.add(fatalities_85_99);
        arrList.add(incidents_00_14);
        arrList.add(fatal_accidents_00_14);
        arrList.add(fatalities_00_14);
        arrList.add(total_incidents_85_14);

        //Part a
        ArrayList<Number> mins = new ArrayList<>();
        ArrayList<Number> maxs = new ArrayList<>();
        for (ArrayList<Double> i : arrList){
            mins.add(Collections.min(i));
            maxs.add(Collections.max(i));
        }
        ArrayList<Number> avgs = new ArrayList<>();
        avgs.add(avail_seat_km_per_week_avg / (csvData.size() - 1.0f));
        avgs.add(incidents_85_99_avg / (csvData.size() - 1.0f));
        avgs.add(fatal_accidents_avg / (csvData.size() - 1.0f));
        avgs.add(fatalities_85_99_avg / (csvData.size() - 1.0f));
        avgs.add(incidents_00_14_avg / (csvData.size() - 1.0f));
        avgs.add(fatal_accidents_00_14_avg / (csvData.size() - 1.0f));
        avgs.add(fatalities_00_14_avg / (csvData.size() - 1.0f));
        avgs.add(total_incidents_85_14_avg / (csvData.size() - 1.0f));

        //Part b
        avg_85_99 /= csvData.size() - 1.0f;
        //Part c
        avg_00_14 /= csvData.size() - 1.0f;


        //Write data to xml file
        String[] cols = csvData.get(0).split(",");

        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
        Document doc = dBuilder.newDocument();

        Element rootElement = doc.createElement("Summary");
        doc.appendChild(rootElement);

        for(int i = 0; i < 8; ++i) {
            Element stat = doc.createElement("Stat");
            rootElement.appendChild(stat);
            Element name = doc.createElement("Name");
            name.appendChild(doc.createTextNode(cols[i+1]));
            stat.appendChild(name);
            //Note: Max value for available seats is larger than integer limit, must be expressed as long
            if(i == 0) {
                Element min = doc.createElement("Min");
                min.appendChild(doc.createTextNode(String.valueOf(mins.get(i).intValue())));
                stat.appendChild(min);
                Element max = doc.createElement("Max");
                max.appendChild(doc.createTextNode(String.valueOf(maxs.get(i).longValue())));
                stat.appendChild(max);
                Element avg = doc.createElement("Avg");
                avg.appendChild(doc.createTextNode(String.valueOf((avgs.get(i)))));
                stat.appendChild(avg);
                continue;
            }
            Element min = doc.createElement("Min");
            min.appendChild(doc.createTextNode(String.valueOf(mins.get(i).intValue())));
            stat.appendChild(min);
            Element max = doc.createElement("Max");
            max.appendChild(doc.createTextNode(String.valueOf(maxs.get(i).intValue())));
            stat.appendChild(max);
            Element avg = doc.createElement("Avg");
            avg.appendChild(doc.createTextNode(String.valueOf((avgs.get(i)))));
            stat.appendChild(avg);

            rootElement.appendChild(stat);
        }

        //Part b
        Element stat = doc.createElement("Stat");
        rootElement.appendChild(stat);
        Element name = doc.createElement("Name");
        name.appendChild(doc.createTextNode("avg_85_99"));
        stat.appendChild(name);
        Element min = doc.createElement("Min");
        min.appendChild(doc.createTextNode(""));
        stat.appendChild(min);
        Element max = doc.createElement("Max");
        max.appendChild(doc.createTextNode(""));
        stat.appendChild(max);
        Element avg = doc.createElement("Avg");
        avg.appendChild(doc.createTextNode(String.valueOf(avg_85_99)));
        stat.appendChild(avg);
        rootElement.appendChild(stat);

        //Part c
        Element stat1 = doc.createElement("Stat");
        rootElement.appendChild(stat1);
        Element name1 = doc.createElement("Name");
        name1.appendChild(doc.createTextNode("avg_00_14"));
        stat1.appendChild(name1);
        Element min1 = doc.createElement("Min");
        min1.appendChild(doc.createTextNode(""));
        stat1.appendChild(min1);
        Element max1 = doc.createElement("Max");
        max1.appendChild(doc.createTextNode(""));
        stat1.appendChild(max1);
        Element avg1 = doc.createElement("Avg");
        avg1.appendChild(doc.createTextNode(String.valueOf(avg_00_14)));
        stat1.appendChild(avg1);
        rootElement.appendChild(stat1);

        //Create xml file
        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();
        DOMSource source = new DOMSource(doc);
        StreamResult result = new StreamResult(new File("src/main/resources/airline_summary_statistic.xml"));
        transformer.transform(source, result);
        transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
        transformer.setOutputProperty(OutputKeys.INDENT, "yes");
        transformer.transform(source, result);

        //Test output in console
        //StreamResult consoleResult = new StreamResult(System.out);
        //transformer.transform(source, consoleResult);

        //-----------------------------------------------------------------------------------------------------------
        //Part 3
        //Create bar chart axis
        CategoryAxis xAxis = new CategoryAxis();
        xAxis.setLabel("Airline");
        NumberAxis yAxis = new NumberAxis();
        yAxis.setLabel("Fatal Accidents");

        //Create bar chart
        BarChart barChart = new BarChart(xAxis, yAxis);

        //Add bar chart data from fatal_accidents_xx_xx
        //fatalities_xx_xx columns not used (doesn't make sense to add fatalities to fatal accidents)
        XYChart.Series data = new XYChart.Series();
        data.setName("Fatal Accidents 1985-1999");
        XYChart.Series data2 = new XYChart.Series();
        data2.setName("Fatal Accidents 2000-2014");
        for(int i = 1; i < csvData.size(); ++i) {
            data.getData().add(new XYChart.Data(csvData.get(i).split(",")[0].substring(1),
                    Integer.valueOf(csvData.get(i).split(",")[3].trim())));
            data2.getData().add(new XYChart.Data(csvData.get(i).split(",")[0].substring(1),
                    Integer.valueOf(csvData.get(i).split(",")[6].trim())));
        }
        barChart.getData().add(data);
        barChart.getData().add(data2);

        //Bar chart formatting
        barChart.setCategoryGap(5);
        barChart.setTitle("Fatal Accidents Between 1985-1999, 2000-2014 per Airline");
        barChart.setLegendSide(Side.TOP);

        //Display bar chart
        BorderPane borderPane = new BorderPane(barChart);
        Scene scene = new Scene(borderPane);
        stage.setTitle("Assignment 2 Solution");
        stage.setMaximized(true);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}