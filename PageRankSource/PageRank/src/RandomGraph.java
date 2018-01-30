import java.util.ArrayList;
import java.util.Random;

/**
 *
 * @author Ioannis Partalas
 * 
 * This program creates a random graph
 */
public class RandomGraph {
    
    public RandomGraph()
    {
        
    }
    
    /**
     * 
     * This function print in standard output a graph of the following form
     * 
     * N % number of web page
     * 0 4 5 6 7 1 % links of web page 0 to other web pages
     * 1 3 4
     * 2 8 5
     * ...
     * N-1 0 43 12
     * 
     * @param N the total number of web page in the random graph
     */
    public void createRandomGraph(int N)
    {
        Random rnd = new Random(System.currentTimeMillis());
                    System.out.println(N); // print the number of the web pages

         ArrayList<Integer> page_ints = new ArrayList<Integer>();
                    
        for(int i=0;i<N;i++)
        {
            int num_links = rnd.nextInt(12); // a random number of links (max 12)
            num_links++; // increase one to avoid zero links
            System.out.print(i); // print the web page
            
            while(num_links>0) // print the links for the web page i
            {
                int next_link = rnd.nextInt(N);
                if(next_link!=i)
                {
                    if(!page_ints.contains(next_link))
                        page_ints.add(next_link);
                    //System.out.print(" "+next_link);
                    num_links--;
                }
            }
            for(int k=0;k<page_ints.size();k++)
                System.out.print(" "+page_ints.get(k).intValue());
            System.out.println();
            page_ints.clear();
        }
        
    }
    
    
    public static void main(String args[])
    {
        RandomGraph rg = new RandomGraph();
        rg.createRandomGraph(Integer.parseInt(args[0]));
    }
    
}

