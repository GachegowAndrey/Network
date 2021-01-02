
import com.google.gson.Gson;
        import com.google.gson.GsonBuilder;
        import org.jsoup.Jsoup;
        import org.jsoup.nodes.Document;
        import org.jsoup.select.Elements;

        import java.io.*;
        import java.net.URL;
        import java.net.URLConnection;
        import java.nio.file.Files;
        import java.nio.file.InvalidPathException;
        import java.nio.file.Paths;
        import java.util.ArrayList;
        import java.util.HashMap;
        import java.util.List;

public class Main
{
    private static final Gson GSON =  new GsonBuilder().setPrettyPrinting().create();
    static Document doc1;

    private static final String WEB_URL ="https://ru.wikipedia.org/wiki/Список_станций_Московского_метрополитена";



//    static {
//        try {
//            doc1 = Jsoup.connect("https://www.moscowmap.ru/metro.html#lines").get();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }



    public static void main(String args []) throws Exception {


        try
        {

            ArrayList<Line> lineSet = new ArrayList<>();
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

                trs.parallelStream().filter(tr -> tr.select("td:nth-child(1) span:nth-child(1)")
                        .text().equals(line)).findFirst().ifPresent(x -> lineSet.add(new Line(
                        line,
                        x.select("td:nth-child(1) span:nth-child(2)").attr("title"),

                        stationList.size())));

            });


//            stations.entrySet().forEach(entry->{
//                System.out.println(entry.getKey() + " " + entry.getValue());
//            });
            System.out.println("==============");
            lineSet.forEach(System.out::println);
            //System.out.println(lineSet);
            //map map = new map(stations, lineSet);
            // String json = GSON.toJson(map);
            //  System.out.println(json);

            PrintWriter writer = new PrintWriter("data/mapMoscow.json");
//            writer.write(String.valueOf(json));
//            writer.flush();
//            writer.close();


//            trs.parallelStream().filter(tr -> tr.select("td:nth-child(1) span:nth-child(1)")
//                    .text().equals(line)).findFirst().ifPresent(x -> lineSet.add(new Line(
//                    line,
//                    x.select("td:nth-child(1) span:nth-child(2)").attr("title"),
//                    getMetroLineColor(x.select("td:nth-child(1)").attr("style")),
//                    stationList.size())));


            //Elements elements1 = document.select("span");
            //  Elements elements2 = doc1.select("span.name");
            //Elements elements1 = document.select("span");
            //Elements elements2 = document.select("a.js-metro-line t-metrostation-list-header t-icon-metroln ln-1");
//            elements1.forEach(System.out::println);
//            System.out.println("=======");
//            elements1.forEach(element -> System.out.println(element.text()));
//            System.out.println("===============");
            //  elements2.forEach(System.out::println);
            System.out.println("===============");
            // elements2.forEach(element -> System.out.println(element.text()));
            //  elements2.forEach(System.out::println);
            // System.out.println(elements1);

//            ObjectMapper mapper = new ObjectMapper();
//            return mapper.readValue("сюда json", "сюда класс");

            // System.out.println(file1);

        }
        catch (FileNotFoundException exe)
        {
            exe.printStackTrace();
        }



    }

//    private static void getMetroLineColor(String style)
//    {
//
//    }

    public static String parseFile(String path)
    {
        StringBuilder builder = new StringBuilder();
        try
        {
            List<String> lines = Files.readAllLines(Paths.get(path));
            lines.forEach(line ->builder.append(line+"\n"));
        }
        catch (IOException ex)
        {
            ex.printStackTrace();
        }
        return builder.toString();
    }

    private static String getContentOfHTTPPage(String pageAddress) throws Exception {

        StringBuilder sb = new StringBuilder();
        URL pageURL = new URL(pageAddress);
        URLConnection uc = pageURL.openConnection();
        BufferedReader br = new BufferedReader(new InputStreamReader(uc.getInputStream()));
        try {
            String inputLine;
            while ((inputLine = br.readLine()) != null) {
                sb.append(inputLine);
            }
        } finally {
            br.close();
        }

        return sb.toString();

    }
}

class map
{
    private HashMap stationName;
    private ArrayList<String> lineName;

    public map(HashMap stationName, ArrayList lineName)
    {
        this.lineName=lineName;
        this.stationName=stationName;
    }

}
//class Line
//{
//    Object line;
//    String lineName1;
//    Integer size;
//
//
//    public Line(Object line,String lineName1,Integer size)
//    {
//        this.line=line;
//        this.lineName1=lineName1;
//        this.size=size;
//
//    }
//}

