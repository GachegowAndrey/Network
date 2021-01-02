import java.util.ArrayList;
import java.util.HashMap;

public class Map
{
    private HashMap stations;
    private HashMap lineName;

    public Map(HashMap stations, HashMap<Object, ArrayList<Line>> lineName)
    {
        this.lineName=lineName;
        this.stations=stations;
    }
}
