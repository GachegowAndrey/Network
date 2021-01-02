import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.annotations.SerializedName;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Test2
{
    private static final Gson GSON =  new GsonBuilder().setPrettyPrinting().create();
    public static void main(String args [])


    {
        Person person = new Person("Rey", 25, Arrays.asList("Moscow", "Paris", "Edo"));
        String json = GSON.toJson(person);
        System.out.println(json);

        try
        {
            PrintWriter writer = new PrintWriter("data/person.json");
            writer.write(String.valueOf(json));
            writer.flush();
            writer.close();
        }
        catch(FileNotFoundException ex)
        {
            ex.printStackTrace();
        }

        Person personal =GSON.fromJson(json,Person.class);
       // System.out.println(personal);
        System.out.println(personal.getAge());
        System.out.println(personal.getName());
        System.out.println(personal.getGeoHistory());
    }

}
class  Person
{
    @SerializedName("FirstName")
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public List<String> getGeoHistory() {
        return geoHistory;
    }

    public void setGeoHistory(List<String> geoHistory) {
        this.geoHistory = geoHistory;
    }

    @SerializedName("years")
    private Integer age;
    @SerializedName("geo")
    private List<String> geoHistory = new ArrayList<>();

    public Person (String name, Integer age, List<String> geoHistory)
    {
        this.name=name;
        this.age=age;
        this.geoHistory=geoHistory;
    }
}
