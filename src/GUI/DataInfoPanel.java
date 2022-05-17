package GUI;

import javax.swing.*;
import java.awt.*;

public class DataInfoPanel extends JPanel {
    public <T>DataInfoPanel(T[][] data){ // accept generic type 2d arrays
        super();
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS)); // up-down visual design
        this.add(new JLabel("Data Overview:"));
        this.setBackground(new Color(255, 161, 242));


    }
}
