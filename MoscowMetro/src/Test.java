
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Test
{
    private static final Gson GSON =  new GsonBuilder().setPrettyPrinting().create();



    private static final String WEB_URL ="https://ru.wikipedia.org/wiki/Список_станций_Московского_метрополитена";

    public static void main(String args []) throws Exception {
        try
        {
            HashMap<Object, ArrayList<Line>> lines = new HashMap();
            HashMap<Object, List<String>> stations = new HashMap<Object, List<String>>();
            Document doc = Jsoup.connect(WEB_URL).maxBodySize(0).get();
            Elements trs = doc.select(".standard.sortable tr");
            //парсинг уникальных номеров линий
            ArrayList uniqueLineNumbers= new ArrayList();
            trs.stream().filter(tr -> !tr.select("td:nth-child(1) span:nth-child(1)").text().isEmpty())
                    .forEach(tr -> uniqueLineNumbers.add(tr.select("td:nth-child(1) span:nth-child(1)").text()));

            uniqueLineNumbers.forEach(line -> {
                List<String> stationList = new ArrayList<>();
                //парсинг станций
                trs.parallelStream().filter(tr -> tr.select("td:nth-child(1) span:nth-child(1)").text().equals(line))
                        .forEach(tr -> {
                            tr.select("td:nth-child(2) small").remove();
                            stationList.add(tr.select("td:nth-child(2)").text());
                        });

                stations.put(line, stationList);
                //парсинг линий

                ArrayList<Line> lineSet = new ArrayList<>();
                trs.parallelStream().filter(tr -> tr.select("td:nth-child(1) span:nth-child(1)")
                        .text().equals(line)).findFirst().ifPresent(x -> lineSet.add(new Line(
                        line,
                        x.select("td:nth-child(1) span:nth-child(2)").attr("title"),

                        stationList.size())));

                lines.put(line,lineSet);
            });


            Map map = new Map(stations, lines );
             String json1 = GSON.toJson(map);
              System.out.println(json1);

            PrintWriter writer = new PrintWriter("data/mapMoscow.json");
            writer.write(String.valueOf(json1));
            writer.flush();
            writer.close();

        }
        catch (FileNotFoundException exe)
        {
            exe.printStackTrace();
        }

    }

}

//class map1
//{
//    private HashMap stations;
//    private HashMap lineName;
//
//    public map1(HashMap stations, HashMap<Object, ArrayList<Line>> lineName)
//    {
//        this.lineName=lineName;
//        this.stations=stations;
//    }
//
//}
//class Line1
//{
//    Object line;
//    String lineName1;
//    Integer size;
//
//
//    public Line1(Object line,String lineName1,Integer size)
//    {
//        this.line=line;
//        this.lineName1=lineName1;
//        this.size=size;
//
//    }
//}

