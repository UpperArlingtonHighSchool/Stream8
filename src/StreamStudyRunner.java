import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

import GUI.*;

public class StreamStudyRunner{
    File dataFile;
    String[][] data;
    public void initiate() throws FileNotFoundException {

        // load data file
        System.out.println(System.getProperty("user.dir"));
        dataFile = new File("./data/data.txt");


        // read data into dataLines arraylist
        ArrayList<String> dataLines = new ArrayList<String>();
        Scanner sc = new Scanner(dataFile);
        while(sc.hasNext()){
            dataLines.add(sc.nextLine());
            //System.out.println(dataLines.get(dataLines.size()-1)); // print data
        }

        //separate data from each data line
        data = new String[dataLines.size()][];
        for(int i = 0; i < dataLines.size(); i++){
            String line = dataLines.get(i);
            String[] separatedData = line.split(" ");
            data[i] = separatedData;
            //System.out.println(separatedData[0] + " " + separatedData.length); // print numbers of data each location has
        }

        //create GUI elements
        Window visualizer = new Window(data);

    }
}
