package GUI;

import javax.swing.*;
import java.awt.*;

public class MainPanel extends JPanel {
    JLabel webinfo;
    public MainPanel(){
        super();
        this.setBackground(new Color(242, 255, 161));
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        JLabel description = new JLabel("<html><body>" +
                "This is a project exploring the relationship between turbidity and temperature<br><br>" +
                "Turbidity:" +
                "The clearness of water, measured by particle concentrations in water<br>" +
                "Higher turbidity means worse water quality<br><br>" +
                "The hypothesis is there will be a direct relationship between these two factors.<br><br>" +
                "" +
                "</body></html>");

        this.add(description);
        webinfo = new JLabel("<html>Data of Grand River near Painesville OH, <br>source: https://waterdata.usgs.gov/oh/nwis/uv?cb_00010=on&cb_00095=on&cb_00300=on&cb_00400=on&cb_63680=on&format=rdb&site_no=04212100&period=&begin_date=2022-05-16&end_date=2022-05-23</html>");

        this.add(webinfo);
        webinfo.setVisible(false);
    }
    public void showInfo(){
        webinfo.setVisible(true);
        this.repaint();
    }
    public void hideInfo(){
        webinfo.setVisible(false);
        this.repaint();
    }
}
