package sample;

import model.PatirntsFlowImitator;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.RefineryUtilities;

public class Plotter extends ApplicationFrame {
    public Plotter(String applicationTitle, String chartTitle) {
        super(applicationTitle);
        JFreeChart lineChart = ChartFactory.createLineChart(
                chartTitle,
                "Year", "patients",
                createDataset(),
                PlotOrientation.VERTICAL,
                true, true, false);

        ChartPanel chartPanel = new ChartPanel(lineChart);
        chartPanel.setPreferredSize(new java.awt.Dimension(560, 367));
        setContentPane(chartPanel);

    }

    private DefaultCategoryDataset createDataset() {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        int from = 0;
        int to = 365 * 6;
        double[] patients = PatirntsFlowImitator.getPatientsFlow(from, to);
        for (int i = from; i < to; i++) {
            dataset.addValue(patients[i], "patients", Integer.toString(i));
        }

        return dataset;
    }

    public static void main(String... args) {
        Plotter chart = new Plotter(
                "Patients",
                "Patients");

        chart.pack();
        RefineryUtilities.centerFrameOnScreen(chart);
        chart.setVisible(true);
    }
}