import org.w3c.dom.Node;

import java.util.concurrent.ForkJoinPool;

public class MainForkJoinPool
{
    public static void main(String[] args)
    {
        Node root = null;
       long sum =  new ForkJoinPool().invoke(new NodeValueSumCalculator(root));
        System.out.println(sum);

    }
}
