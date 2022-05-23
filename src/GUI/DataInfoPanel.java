package GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DataInfoPanel<T> extends JPanel {
    public int setNo = 0; // which individual set to display
    JButton next;
    JButton prev;
    public <T>DataInfoPanel(T[][] data){ // accept generic type 2d arrays
        super();
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS)); // up-down visual design
        if(data == null || data.length == 0) // defensive programming
            return;
        this.setBackground(new Color(255, 161, 242));
        next = new JButton("next");
        prev = new JButton("previous");
        DisplayIndividualSet(data[setNo]);
        next.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setNo++;
                if(setNo >= data.length)
                    setNo = 0;
                DisplayIndividualSet(data[setNo]);
            }
        });
        prev.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setNo--;
                if(setNo < 0)
                    setNo = data.length-1;
                DisplayIndividualSet(data[setNo]);
            }
        });
    }


    public <T> void DisplayIndividualSet(T[] data){ // display information of one single data set
        this.removeAll();
        this.add(new JLabel("Data Overview:"));
        for(T item:data){
            String text = item.toString();
            this.add(new JLabel(text));
        }
        this.add(next);
        this.add(prev);
        this.setSize(this.getWidth()+1, this.getHeight());
        this.setSize(this.getWidth()-1, this.getHeight());
        this.revalidate();
        this.repaint();
    }
}
