import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

public class StreamStudyRunner{
    private static String[] categories = {"location", "year", "flowrate", "turbidity", "TempinC", "TDS", "Conductivity", "Salinity", "Hardness", "TotalChlorine", "FreeChlorine", "Alkalinity", "pH", "Nitrate", "Nitrite", "Phosphate"};
    File dataFile;
    String[][] data;
    DataPt[][] dataPoints;
    public void initiate() throws Exception {

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

        // separate data from each data line
        data = new String[dataLines.size()][];
        for(int i = 0; i < dataLines.size(); i++){
            String line = dataLines.get(i);
            String[] separatedData = line.split(" ");
            data[i] = separatedData;
            //System.out.println(separatedData[0] + " " + separatedData.length); // print numbers of data each location has
        }

        // process and match each data with its category
        dataPoints = new DataPt[dataLines.size()][];
        int i = 0;
        int j = 0;
        for(String[] dataRow:data){
            dataPoints[i] = new DataPt[dataRow.length];
            for(String item:dataRow){
                try {
                    // if the data is numeric
                    double num = Double.parseDouble(item);
                    dataPoints[i][j] = new DataPt<Double>(num,categories[j]);
                } catch(NumberFormatException e){
                    dataPoints[i][j] = new DataPt<String>(item,categories[j]);
                }
                j++;
            }
            j = 0;
            i++;
        }


        //create GUI elements
        Window visualizer = new <DataPt>Window(dataPoints);
        WebScrape test = new WebScrape();
    }
}
