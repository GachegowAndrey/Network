import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class MainScheduledExecutorService
{
    public static void main(String[] args)
    {
        ScheduledExecutorService service = Executors.newScheduledThreadPool(5);

        service.schedule(()-> System.out.println("Yes"),1000, TimeUnit.MILLISECONDS);

        service.scheduleAtFixedRate(()-> System.out.println("NO"),1000,2000,TimeUnit.MILLISECONDS);//от начала задачи

        service.scheduleWithFixedDelay(()-> System.out.println("SOme"),1000,2000,TimeUnit.MILLISECONDS);//от конца задачи
    }

}
