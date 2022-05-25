import GUI.DataInfoPanel;
import GUI.MainPanel;
import GUI.RightSideBar;

import javax.swing.*;
import java.awt.*;
import java.util.Arrays;

public class Window extends JFrame {
    JFrame window  = this;
    MainPanel mainPanel;
    public Window(DataPt[][] data){
        super();
        Dimension windowRes = Toolkit.getDefaultToolkit().getScreenSize();
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        this.setVisible(true);
        this.setLayout(new BorderLayout());
        this.setTitle("Stream Study");

        //add title
        JPanel titlePanel = new JPanel();
        titlePanel.setBackground(new Color(185, 255, 161));
        titlePanel.add(new JLabel(new ImageIcon("./src/GUI/title.png")));
        this.add(titlePanel, BorderLayout.NORTH);

        //setup main panel
        mainPanel = new MainPanel();
        mainPanel.setPreferredSize(mainPanel.getPreferredSize());
        this.add(mainPanel, BorderLayout.CENTER);

        //setup left-side panel
        JPanel dataInfoPanel = new DataInfoPanel(data);
        dataInfoPanel.setPreferredSize(new Dimension(this.getWidth()/5, this.getHeight()));
        this.add(dataInfoPanel, BorderLayout.WEST);


        //process dataset
        double[] temperatures = new double[data.length];
        double[] turbidities = new double[data.length];
        int i = 0;
        int j = 0;
        for(DataPt[] row:data){
            for(DataPt item: row){
                if(item.getCategory().equals("TempinC")){
                    temperatures[i] = (Double)item.getData();
                    i++;
                }
                if(item.getCategory().equals("turbidity")) {
                    turbidities[j] = (Double) item.getData();
                    j++;
                }
            }
        }
        System.out.println(Arrays.toString(temperatures));
        System.out.println(Arrays.toString(turbidities));

        //setup right-side panel
        JPanel rightPanel = new RightSideBar();
        ChartGenerator lineChart = new ChartGenerator(this);
        lineChart.lineGraph(temperatures,turbidities);
        rightPanel.add(lineChart);
        rightPanel.setPreferredSize(new Dimension(this.getWidth()/2, this.getHeight()));
        this.add(rightPanel, BorderLayout.EAST);

        //refresh window
        this.revalidate();

    }
    public void showWebInfo(){
        mainPanel.showInfo();
    }
    public void hideWebInfo(){
        mainPanel.hideInfo();
    }




}
