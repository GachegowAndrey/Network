import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Executor;
public class Main_Executor
{
    public static void main(String[] args)
    {
       Executor executor= Executors.newSingleThreadExecutor();
       executor.execute(()->
       {
          for(int i=0;i<9;i++)
          {
              System.out.println(Math.random());
          }
       });


        ExecutorService service =  Executors.newSingleThreadExecutor();
        service.submit(()->
        {
            for(int i =0;i<5;i++)
            {
                System.out.println(Math.random());
            }
        });

        service.shutdown();
    }
}
