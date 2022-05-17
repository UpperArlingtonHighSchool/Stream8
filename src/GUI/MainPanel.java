package GUI;

import javax.swing.*;

public class MainPanel extends JPanel {
    public MainPanel(){
        super();
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        JLabel description = new JLabel("<html><body>" +
                "This is a project exploring the relationship between turbidity and temperature<br><br>" +
                "Turbidity:" +
                "The clearness of water, measured by particle concentrations in water<br>" +
                "Higher turbidity means worse water quality<br><br>" +
                "The hypothesis is there will be a direct relationship between these two factors.<br>" +
                "" +
                "</body></html>");

        this.add(description);

    }
}
