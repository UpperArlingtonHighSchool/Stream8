import GUI.MainPanel;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
import java.util.TreeMap;

public class ChartGenerator extends JPanel {
    JButton switchStyle = new JButton("Bar Graph");
    JButton switchSrc = new JButton("Use Data from Web");
    DefaultCategoryDataset dataset;
    Window window;
    WebScrape getData;
    // this class is responsible for all the chart generating algorithms
    public ChartGenerator(Window w){
        super();
        window = w;
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
    }

    public void lineGraph(double[] temp, double[] turb){
        this.removeAll();
        dataset = createDataset(temp,turb);
        // Create chart
        JFreeChart chart = ChartFactory.createLineChart(
                "Stream Study", "Temperature", "Turbidity", dataset,
                PlotOrientation.VERTICAL,
                true,true,false
        );

        ChartPanel graph = new ChartPanel(chart);
        this.add(graph);
        switchStyle.setText("Bar Graph");
        this.add(switchStyle);
        this.add(switchSrc);
        this.revalidate();
        this.repaint();
        switchStyle.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(switchSrc.getText().equals("Use Data from Web")) {
                    if (switchStyle.getText() == "Line Graph")
                        lineGraph(temp, turb);
                    else
                        barGraph();
                }
                else
                {
                    if (switchStyle.getText() == "Line Graph")
                        lineGraph(getData.get_temperature(), getData.get_turbidity());
                    else
                        barGraph();
                }
            }
        });
        switchSrc.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (switchSrc.getText().equals("Use Data from Web")) {
                    switchSrc.setText("Use Given Data");
                    getData = null;
                    try {
                        getData = new WebScrape();
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                    lineGraph(getData.get_temperature(), getData.get_turbidity());
                    window.showWebInfo();
                }
                else
                {
                    switchSrc.setText("Use Data from Web");
                    lineGraph(temp,turb);
                    window.hideWebInfo();
                }
            }
        });
    }


    public void lineGraph(Double[] temp, Double[] turb){
        this.removeAll();
        dataset = createDataset(temp,turb);
        // Create chart
        JFreeChart chart = ChartFactory.createLineChart(
                "Stream Study", "Temperature", "Turbidity", dataset,
                PlotOrientation.VERTICAL,
                true,true,false
        );

        ChartPanel graph = new ChartPanel(chart);
        graph.setSize(this.getSize());
        this.add(graph);
        switchStyle.setText("Bar Graph");
        this.add(switchStyle);
        this.add(switchSrc);
        this.revalidate();
        this.repaint();
    }

    public void barGraph(){
        this.removeAll();
        JFreeChart barGraph = ChartFactory.createBarChart3D( "Stream Study", "Temperature", "Turbidity", dataset,
                PlotOrientation.VERTICAL,
                true,true,false);

        ChartPanel graph = new ChartPanel(barGraph);
        this.add(graph);
        switchStyle.setText("Line Graph");
        this.add(switchStyle);
        this.add(switchSrc);
        this.revalidate();
        this.repaint();
    }




    private DefaultCategoryDataset createDataset(double[] temp, double[] turb) {

        String series1 = "Temperature";
        String series2 = "Turbidity";
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        if(temp.length != turb.length)
            return null;
        twoDouble[] list = new twoDouble[temp.length];
        String set1 = "Stream Data  ";
        for(int i = 0; i < temp.length; i++) {
            list[i] = new twoDouble(temp[i], turb[i]);
        }
        Arrays.sort(list);
        for(twoDouble item:list){
            dataset.addValue(item.turb, set1, (Double)item.temp);
        }
        return dataset;
    }
    private DefaultCategoryDataset createDataset(Double[] temp, Double[] turb) {

        String series1 = "Temperature";
        String series2 = "Turbidity";
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        if(temp.length != turb.length)
            return null;
        twoDouble[] list = new twoDouble[temp.length];
        String set1 = "Stream Data  ";
        for(int i = 0; i < temp.length; i++) {
            list[i] = new twoDouble(temp[i], turb[i]);
        }
        Arrays.sort(list);
        for(twoDouble item:list){
            dataset.addValue(item.turb, set1, (Double)item.temp);
        }
        return dataset;
    }
}
