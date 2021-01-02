import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;

public class Main_CALLABLE
{
    public static void main(String[] args)
    {
        Callable callable = ()->
        {
               double sum=0;
            for (int i = 0; i <1000 ; i++)
            {
                sum+=Math.random();
            }
                return sum;
        };

        FutureTask<Double> futureTask = new FutureTask<>(callable);
        new Thread(futureTask).start();

        try {
            System.out.println(futureTask.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }
}
