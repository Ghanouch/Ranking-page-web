import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;

/**
 *
 * @author Ioannis Partalas
 *
 *
 * This program reads a file in the following form and the calculates the 
 * transition matrix
 *
 * N % number of web page
 * 0 4 5 6 7 1 % links of web page 0 to other web pages
 * 1 3 4
 * 2 8 5
 * ...
 * N-1 0 43 12
 *
 */
public class TransitionMatrix {
    
    double pmatrix[][]; // matrix to store the probabilities
    double lambda=0.85; 
    int N; // number of pages
    
    
    /**
     *
     *@param filename The file where the graph is stored
     *@param lam the lambda value
     *
     */
    public TransitionMatrix(String filename,double lam)
    {
        lambda = lam;
        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader(filename));   
            String line = br.readLine(); // read first line with the number of pages

            N = Integer.parseInt(line);
            pmatrix = new double[N][N];

            int adjancence[][] = new int[N][N]; // keeps the adjacency  table: number of links from page i to page j
            int outdegree[] = new int[N]; // keeps the number of links that go out from page i
            while((line=br.readLine())!=null) // read the file line by line
            {
                String links[] = line.split("\\s+");
                int page_i = Integer.parseInt(links[0]); // get first the page i
                
                 // here you should read the links from page_i to other pages
       		    // and update the adjence table and the outdegree vector
                Arrays.stream(links)
                .map(p -> Integer.parseInt(p))
                .filter(page_j -> page_j != page_i)
                .forEach(page_j -> { 
                	adjancence[page_i][page_j] = 1;
                });
            }
            
            printAdjacenceMatrix(adjancence);
            
            for (int i = 0; i < N; i++)  {
            	outdegree = adjancence[i];
            	// Si la somme (j=1:N)  Aij == 0 Alors Ai = 1/N 
                if( ! isDiffZero(outdegree) )
                	 for (int j = 0; j < N; j++)   
                	   pmatrix[i][j] =  1d/N ;
                else
                {
	                for (int j = 0; j < N; j++) {      	
	                	pmatrix[i][j] =( lambda * ( adjancence[i][j] / sum(outdegree) ) ) + ( ( 1-lambda ) / N );
	
	                }
                }
            }
            
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
            ex.printStackTrace();
        }
        
    }
    
    public void printAdjacenceMatrix(int[][] matrix)
    {
    	  int num_pages = matrix.length;
          
          for (int i = 0; i < num_pages; i++)  {
                
                 for (int j = 0; j < num_pages; j++) {
                     System.out.print(matrix[i][j]);
                 }
                 System.out.println();
          }
    }
    
   /**
    * This method prints the transition matrix
    *
    *
    */ 
    public void prinTransitionMatrix()
    {
        int num_pages = pmatrix.length;
        
         for (int i = 0; i < num_pages; i++)  {
               
                for (int j = 0; j < num_pages; j++) {
                    System.out.format("%.3f ",pmatrix[i][j]);
                }
                System.out.println();
        }  
    }
    
    public boolean isDiffZero(int[] tab)
    {
    	return Arrays.stream(tab).sum() != 0 ;
    }
    
    public double sum(int[] tab)
    {
    	return Arrays.stream(tab).sum();
    }
    public static void main(String args[])
    {
	// arguments: the file graph and the lambda value
        TransitionMatrix tr = new TransitionMatrix(args[0],Double.parseDouble(args[1]));
        tr.prinTransitionMatrix();
    }
    
}
