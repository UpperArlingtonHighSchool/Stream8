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
    DefaultCategoryDataset webdataset;
    ChartPanel givengraph;
    ChartPanel webgraph;
    JFreeChart givenbargraph;
    JFreeChart webbargraph;
    Window window;
    WebScrape getData;
    // this class is responsible for all the chart generating algorithms
    public ChartGenerator(Window w){
        super();
        window = w;
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
    }

    public void lineGraph(double[] temp, double[] turb) {
        this.removeAll();

        if(givengraph == null){
        dataset = createDataset(temp, turb);
        // Create chart
        JFreeChart chart = ChartFactory.createLineChart(
                "Stream Study", "Temperature", "Turbidity", dataset,
                PlotOrientation.VERTICAL,
                true, true, false
        );
        givengraph = new ChartPanel(chart);
    }
        this.add(givengraph);
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
                    if(getData == null) {
                        try {
                            getData = new WebScrape();
                        } catch (Exception ex) {
                            ex.printStackTrace();
                        }
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
        if(webgraph==null) {
        if(webdataset == null)
            webdataset = createDataset(temp,turb);
        // Create chart
        JFreeChart chart = ChartFactory.createLineChart(
                "Stream Study", "Temperature", "Turbidity", webdataset,
                PlotOrientation.VERTICAL,
                true,true,false
        );
            webgraph = new ChartPanel(chart);
            webgraph.setSize(this.getSize());
        }
        this.add(webgraph);
        switchStyle.setText("Bar Graph");
        this.add(switchStyle);
        this.add(switchSrc);
        this.revalidate();
        this.repaint();
    }

    public void barGraph(){
        this.removeAll();
        if(switchSrc.getText().equals("Use Data from Web") && givenbargraph==null)
            givenbargraph = ChartFactory.createBarChart3D( "Stream Study", "Temperature", "Turbidity", dataset,
                PlotOrientation.VERTICAL,
                true,true,false);
        if(switchSrc.getText().equals("Use Given Data") && webbargraph==null)
            webbargraph = ChartFactory.createBarChart3D( "Stream Study", "Temperature", "Turbidity", webdataset,
                    PlotOrientation.VERTICAL,
                    true,true,false);
        ChartPanel graph;
        if(switchSrc.getText().equals("Use Data from Web"))
            graph = new ChartPanel(givenbargraph);
        else
            graph = new ChartPanel(webbargraph);
        this.add(graph);
        switchStyle.setText("Line Graph");
        this.add(switchStyle);
        this.add(switchSrc);
        this.revalidate();
        this.repaint();
    }




    private DefaultCategoryDataset createDataset(double[] temp, double[] turb) {
        if(dataset!=null)
            return dataset;
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
        if(webdataset != null)
            return webdataset;
        String series1 = "Temperature";
        String series2 = "Turbidity";
        webdataset = new DefaultCategoryDataset();
        if(temp.length != turb.length)
            return null;
        twoDouble[] list = new twoDouble[temp.length];
        String set1 = "Stream Data  ";
        for(int i = 0; i < temp.length; i++) {
            list[i] = new twoDouble(temp[i], turb[i]);
        }
        Arrays.sort(list);
        for(twoDouble item:list){
            webdataset.addValue(item.turb, set1, (Double)item.temp);
        }
        return webdataset;
    }
}
