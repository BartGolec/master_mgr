package com.mgr.bg.Service;

import com.mgr.bg.Model.BatchDataEntity;
import com.mgr.bg.Model.FileData;
import com.mgr.bg.Repository.BatchDataRepository;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.DateAxis;
import org.jfree.chart.block.BlockBorder;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.chart.title.TextTitle;
import org.jfree.data.time.Second;
import org.jfree.data.time.TimeSeries;
import org.jfree.data.time.TimeSeriesCollection;
import org.jfree.data.xy.XYDataset;
import org.springframework.beans.factory.annotation.Autowired;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class MultiplyLineChart extends JFrame {

    @Autowired
    BatchDataRepository batchDataRepository;

    private List<Double> PmaxList;
    private List<Double> CoList;
    private List<Double> BooList;
    private List<Double> CpList;
    private List<Double> BopList;
    private List<Double> BpoList;
    private List<Double> BppList;
    private String fileName;
    private String chartTitle;
    private String yAxisLabel;

    public String getFileName() {
        return fileName;
    }

    private List<TimeSeries> timeSeriesList = new ArrayList<>();

    public MultiplyLineChart(FileData fileData, List<BatchDataEntity> sortedBatchDataForSpecificFileList) throws IOException, ParseException {
        initUI(fileData, sortedBatchDataForSpecificFileList);
    }

    public void initUI(FileData fileData, List<BatchDataEntity> sortedBatchDataForSpecificFileList) throws IOException, ParseException {

        XYDataset dataset = createDataset(fileData, sortedBatchDataForSpecificFileList);
        JFreeChart chart = createChart(dataset, chartTitle, yAxisLabel, fileName);
    }

    private XYDataset createDataset(FileData fileData, List<BatchDataEntity> sortedBatchDataForSpecificFileList) throws ParseException {

        DateConverter dateConverter = new DateConverter();

        List<String> dateEntities = sortedBatchDataForSpecificFileList.stream().map(BatchDataEntity::getDate).collect(Collectors.toList());

            switch (fileData) {
                case CO:
                    CoList = sortedBatchDataForSpecificFileList.stream().map(BatchDataEntity::getCO).collect(Collectors.toList());
                    TimeSeries CoTimeSeries = new TimeSeries(FileData.CO.name());
                    for(int i = 0; i<dateEntities.size(); i++) {
                        CoTimeSeries.add(new Second(dateConverter.convertDateFromStringToLong(dateEntities.get(i))), CoList.get(i));
                    }
                    timeSeriesList.add(CoTimeSeries);
                    chartTitle = FileData.CO.name() + " over Time";
                    yAxisLabel = FileData.CO.name();
                    fileName = FileData.CO.name() + dateConverter.getCurrentDateInFormatyyyyMMddHHmmss();
                    break;
                case BOO:
                    BooList = sortedBatchDataForSpecificFileList.stream().map(BatchDataEntity::getBOO).collect(Collectors.toList());
                    TimeSeries BooTimeSeries = new TimeSeries(FileData.BOO.name());
                    for(int i = 0; i<dateEntities.size(); i++) {
                        BooTimeSeries.add(new Second(dateConverter.convertDateFromStringToLong(dateEntities.get(i))), BooList.get(i));
                    }
                    timeSeriesList.add(BooTimeSeries);
                    chartTitle = FileData.BOO.name() + " over Time";
                    yAxisLabel = FileData.BOO.name();
                    fileName = FileData.BOO.name() + dateConverter.getCurrentDateInFormatyyyyMMddHHmmss();
                    break;
                case CP:
                    CpList = sortedBatchDataForSpecificFileList.stream().map(BatchDataEntity::getCP).collect(Collectors.toList());
                    TimeSeries CpTimeSeries = new TimeSeries(FileData.CP.name());
                    for(int i = 0; i<dateEntities.size(); i++) {
                        CpTimeSeries.add(new Second(dateConverter.convertDateFromStringToLong(dateEntities.get(i))), CpList.get(i));
                    }
                    timeSeriesList.add(CpTimeSeries);
                    chartTitle = FileData.CP.name() + " over Time";
                    yAxisLabel = FileData.CP.name();
                    fileName = FileData.CP.name() + dateConverter.getCurrentDateInFormatyyyyMMddHHmmss();
                    break;
                case BOP:
                    BopList = sortedBatchDataForSpecificFileList.stream().map(BatchDataEntity::getBOP).collect(Collectors.toList());
                    TimeSeries BopTimeSeries = new TimeSeries(FileData.BOP.name());
                    for(int i = 0; i<dateEntities.size(); i++) {
                        BopTimeSeries.add(new Second(dateConverter.convertDateFromStringToLong(dateEntities.get(i))), BopList.get(i));
                    }
                    timeSeriesList.add(BopTimeSeries);
                    chartTitle = FileData.BOP.name() + " over Time";
                    yAxisLabel = FileData.BOP.name();
                    fileName = FileData.BOP.name() + dateConverter.getCurrentDateInFormatyyyyMMddHHmmss();
                    break;
                case BPO:
                    BpoList = sortedBatchDataForSpecificFileList.stream().map(BatchDataEntity::getBPO).collect(Collectors.toList());
                    TimeSeries BpoTimeSeries = new TimeSeries(FileData.BPO.name());
                    for(int i = 0; i<dateEntities.size(); i++) {
                        BpoTimeSeries.add(new Second(dateConverter.convertDateFromStringToLong(dateEntities.get(i))), BpoList.get(i));
                    }
                    timeSeriesList.add(BpoTimeSeries);
                    chartTitle = FileData.BPO.name() + " over Time";
                    yAxisLabel = FileData.BPO.name();
                    fileName = FileData.BPO.name() + dateConverter.getCurrentDateInFormatyyyyMMddHHmmss();
                    break;
                case PMAX:
                    PmaxList = sortedBatchDataForSpecificFileList.stream().map(BatchDataEntity::getPmax).collect(Collectors.toList());
                    TimeSeries PmaxTimeSeries = new TimeSeries(FileData.PMAX.name());
                    for(int i = 0; i<dateEntities.size(); i++) {
                        PmaxTimeSeries.add(new Second(dateConverter.convertDateFromStringToLong(dateEntities.get(i))), PmaxList.get(i));
                    }
                    timeSeriesList.add(PmaxTimeSeries);
                    chartTitle = FileData.PMAX.name() + " over Time";
                    yAxisLabel = FileData.PMAX.name();
                    fileName = FileData.PMAX.name() + dateConverter.getCurrentDateInFormatyyyyMMddHHmmss();
                    break;

                case BPP:
                    BppList = sortedBatchDataForSpecificFileList.stream().map(BatchDataEntity::getBPP).collect(Collectors.toList());
                    TimeSeries BppTimeSeries = new TimeSeries(FileData.BPP.name());
                    for(int i = 0; i<dateEntities.size(); i++) {
                        BppTimeSeries.add(new Second(dateConverter.convertDateFromStringToLong(dateEntities.get(i))), BppList.get(i));
                    }
                    timeSeriesList.add(BppTimeSeries);
                    chartTitle = FileData.BPP.name() + " over Time";
                    yAxisLabel = FileData.BPP.name();
                    fileName = FileData.BPP.name() + dateConverter.getCurrentDateInFormatyyyyMMddHHmmss();
                    break;
            }

        TimeSeriesCollection timeSeriesCollection = new TimeSeriesCollection();
        for(TimeSeries timeSeries : timeSeriesList){
            timeSeriesCollection.addSeries(timeSeries);
        }

        return timeSeriesCollection;
    }

    private JFreeChart createChart(final XYDataset dataset, String chartTitle, String yAxisLabel, String fileName) throws IOException {

        JFreeChart chart = ChartFactory.createXYLineChart(
                chartTitle,
                "Time Period",
                yAxisLabel,
                dataset,
                PlotOrientation.VERTICAL,
                true,
                true,
                true
        );

        chart.getLegend().setFrame(BlockBorder.NONE);
        chart.setTitle(new TextTitle(chartTitle,
                new Font("Serif", Font.BOLD, 18)));


        XYLineAndShapeRenderer renderer = new XYLineAndShapeRenderer();

        renderer.setSeriesPaint(0, Color.RED);
        renderer.setSeriesStroke(0, new BasicStroke(2.0f));

        renderer.setSeriesPaint(1, Color.BLUE);
        renderer.setSeriesStroke(1, new BasicStroke(2.0f));

        renderer.setSeriesPaint(2, Color.GREEN);
        renderer.setSeriesStroke(2, new BasicStroke(2.0f));

        renderer.setSeriesPaint(3, Color.YELLOW);
        renderer.setSeriesStroke(3, new BasicStroke(2.0f));

        renderer.setSeriesPaint(4, Color.MAGENTA);
        renderer.setSeriesStroke(4, new BasicStroke(2.0f));

        renderer.setSeriesPaint(5, Color.ORANGE);
        renderer.setSeriesStroke(5, new BasicStroke(2.0f));

        renderer.setSeriesPaint(6, Color.BLACK);
        renderer.setSeriesStroke(6, new BasicStroke(2.0f));

        renderer.setSeriesPaint(7, Color.CYAN);
        renderer.setSeriesStroke(7, new BasicStroke(2.0f));

        DateAxis dateAxis = new DateAxis();

        dateAxis.setDateFormatOverride(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));

        XYPlot xyPlot = chart.getXYPlot();
        xyPlot.setDomainAxis(dateAxis);
        xyPlot.getDomainAxis().setVerticalTickLabels(true);
        xyPlot.setRenderer(renderer);
        xyPlot.setBackgroundPaint(Color.WHITE);
        xyPlot.setRangeGridlinesVisible(true);
        xyPlot.setDomainGridlinesVisible(true);

        //ChartUtilities.saveChartAsPNG(new File("C:\\Praca Magisterska\\Application Files" + "\\" +  fileName + ".png"), chart, 1200, 800);
        ChartUtilities.saveChartAsPNG(new File("C:\\Users\\Bartek\\Desktop\\mgr\\src\\main\\resources\\static\\images" + "\\" +  fileName + ".png"), chart, 1200, 800);

        return chart;
    }
}
