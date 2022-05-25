
import java.util.ArrayList;
import java.util.Scanner;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;


// code by Mihai Crisan
public class WebScrape {

    ArrayList<Double> turbidity = new ArrayList<Double>();//contains turbidity
    ArrayList<Double> temperature = new ArrayList<Double>();//contains temperature
    public WebScrape() throws Exception {
        String date = java.time.LocalDate.now().toString();
        String lastWeek = java.time.LocalDate.now().minusDays(7).toString();
        final Document document = Jsoup.connect("https://waterdata.usgs.gov/oh/nwis/uv?cb_00010=on&cb_00095=on&cb_00300=on&cb_00400=on&cb_63680=on&format=rdb&site_no=04212100&period=&begin_date="+lastWeek+"&end_date="+date).get();
        String doc = document.toString().substring(900);
        //System.out.print(doc);

        Scanner chopper = new Scanner(doc);

        String[] split = doc.split(" ");
        //System.out.print(Arrays.toString(split));
        for(int i = 0; i<split.length;i++)
        {
            if(split[i].equals("EDT"))
            {
                temperature.add(Double.parseDouble(split[i+1]));
                turbidity.add(Double.parseDouble(split[i+9]));
            }
        }

        String line = "";
        int index = 0;
    }
    public Double[] get_temperature(){
        Double[] array = new Double[temperature.size()];
        temperature.toArray(array); // fill the array
        return array;
    }
    public Double[] get_turbidity(){
        Double[] array = new Double[turbidity.size()];
        turbidity.toArray(array); // fill the array
        return array;
    }
}





