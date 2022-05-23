package GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;

public class Window extends JFrame {
    JFrame window  = this;
    public <T> Window(T[][] data){
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
        JPanel mainPanel = new MainPanel();
        mainPanel.setPreferredSize(mainPanel.getPreferredSize());
        this.add(mainPanel, BorderLayout.CENTER);

        //setup left-side panel
        JPanel dataInfoPanel = new DataInfoPanel(data);
        dataInfoPanel.setPreferredSize(new Dimension(this.getWidth()/3, this.getHeight()));
        this.add(dataInfoPanel, BorderLayout.WEST);

        //setup right-side panel
        JPanel rightPanel = new RightSideBar();
        rightPanel.setPreferredSize(new Dimension(this.getWidth()/3, this.getHeight()));
        this.add(rightPanel, BorderLayout.EAST);

        //refresh window
        this.revalidate();

    }





    //window resize listener
    //source: https://stackoverflow.com/questions/1088595/how-to-do-something-on-swing-component-resizing
    /*class ResizeListener extends ComponentAdapter {
        public void componentResized(ComponentEvent e) {

        }
    }*/
}
