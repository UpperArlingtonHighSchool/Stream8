package GUI;

import javax.swing.*;
import java.awt.*;

public class Window extends JFrame {
    public <T> Window(T[][] data){
        super();
        Dimension windowRes = Toolkit.getDefaultToolkit().getScreenSize();
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        this.setVisible(true);
        this.setLayout(new BorderLayout());
        this.setBackground(new Color(185, 255, 161));
        this.setTitle("Stream Study");

        //add title
        this.add(new JLabel(new ImageIcon("./src/GUI/title.png")), BorderLayout.NORTH);

        //setup main panel
        JPanel mainPanel = new MainPanel();
        mainPanel.setPreferredSize(mainPanel.getPreferredSize());
        this.add(mainPanel, BorderLayout.CENTER);

        //setup left-side panel
        JPanel dataInfoPanel = new DataInfoPanel(data);
        dataInfoPanel.setPreferredSize(new Dimension(this.getWidth()/3, this.getHeight()));
        this.add(dataInfoPanel, BorderLayout.WEST);

        //refresh window
        this.revalidate();
    }


}
