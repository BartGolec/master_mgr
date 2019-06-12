package com.mgr.bg.Service;

import com.mgr.bg.Model.VLL_Node_Model;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.CategoryLabelPositions;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.data.category.DefaultCategoryDataset;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.util.List;

@Service
public class VlnVoltageBarChart {

    private static final Logger log = LoggerFactory.getLogger(VlnVoltageBarChart.class);

    public String createVllNodeBarChart(List<VLL_Node_Model> vll_node_modelList){

        log.info("Create Vll node bar chart");

        DateConverter dateConverter = new DateConverter();
        String fileName = "vllNodeChart" + dateConverter.getCurrentDateInFormatyyyyMMddHHmmss();

        final DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        for(VLL_Node_Model vll_node_model : vll_node_modelList){
            dataset.addValue(vll_node_model.getVlnValue(), vll_node_model.getNode(), vll_node_model.getBus());
        }

        JFreeChart barChart = ChartFactory.createBarChart(
                "L-L Voltages for each node",
                "Nodes", "Voltage [kV]",
                dataset, PlotOrientation.VERTICAL,
                true, true, false);

        CategoryPlot plot = barChart.getCategoryPlot();
        CategoryAxis domainAxis = plot.getDomainAxis();
        domainAxis.setCategoryLabelPositions(CategoryLabelPositions.createUpRotationLabelPositions(Math.PI/3));

        BarRenderer renderer = (BarRenderer) barChart.getCategoryPlot().getRenderer();

        int width = 1800;    /* Width of the image */
        int height = 900;   /* Height of the image */
        File barChartPng = new File( "C:\\Users\\Bartek\\Desktop\\mgr\\src\\main\\resources\\static\\images" + "\\" +  fileName + ".png" );

        try {
            ChartUtilities.saveChartAsPNG( barChartPng , barChart , width , height );
        } catch (IOException e) {
            e.printStackTrace();
        }
        return barChartPng.getAbsolutePath();
    }

    public static void main( String[ ] args )throws Exception {
        List <VLL_Node_Model> vll_node_modelList = TextFileService.gatherDataForVllPlot("c:\\Praca Magisterska\\OneModel_VLL_Node.Txt");
        String node1 = "1-2";
        String node2 = "2-3";
        String node3 = "3-1";
        final String speed = "Speed";
        final String millage = "Millage";
        final String userrating = "User Rating";
        final String safety = "safety";

        final DefaultCategoryDataset dataset = new DefaultCategoryDataset( );
        for(VLL_Node_Model vll_node_model : vll_node_modelList){
            dataset.addValue(vll_node_model.getVlnValue(), vll_node_model.getNode(), vll_node_model.getBus());
        }

//        dataset.addValue( 1.0 , node1 , speed );
//        dataset.addValue( 3.0 , node1 , userrating );
//        dataset.addValue( 5.0 , node1 , millage );
//        dataset.addValue( 5.0 , node1 , safety );
//
//        dataset.addValue( 5.0 , node2 , speed );
//        dataset.addValue( 6.0 , node2 , userrating );
//        dataset.addValue( 10.0 ,node2 , millage );
//        dataset.addValue( 4.0 , node2 , safety );
//
//        dataset.addValue( 4.0 , node3 , speed );
//        dataset.addValue( 2.0 , node3 , userrating );
//        dataset.addValue( 3.0 , node3 , millage );
//        dataset.addValue( 6.0 , node3 , safety );

        JFreeChart barChart = ChartFactory.createBarChart(
                "L-L Voltages for each node",
                "Nodes", "Voltage [kV]",
                dataset, PlotOrientation.VERTICAL,
                true, true, false);

        CategoryPlot plot = barChart.getCategoryPlot();
        CategoryAxis domainAxis = plot.getDomainAxis();
        domainAxis.setCategoryLabelPositions(CategoryLabelPositions.createUpRotationLabelPositions(Math.PI/3));

        BarRenderer renderer = (BarRenderer) barChart.getCategoryPlot().getRenderer();

        int width = 1800;    /* Width of the image */
        int height = 900;   /* Height of the image */
        File BarChart = new File( "c:\\Praca Magisterska\\Application Files\\fotografia.png" );
        ChartUtilities.saveChartAsPNG( BarChart , barChart , width , height );
    }
}
