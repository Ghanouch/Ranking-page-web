import java.util.Arrays;
import java.util.stream.IntStream;

public class MainPageRank {

	public static double[] calcul(String namefileOfGraph, double lambda, double epsilon)
	{
		TransitionMatrix tr = new TransitionMatrix(namefileOfGraph,lambda);
        //tr.prinTransitionMatrix();
		double vectPageRank_0[] = new double[tr.N];
		double vectPageRank_1[] = new double[tr.N];
		
		for(int i=0; i< tr.N ; i++)
			vectPageRank_0[i] = 1d/ tr.N ; 
		
		vectPageRank_1 = multiply(vectPageRank_0, tr.pmatrix, tr.N);
		
		while( distanceEuclidienne(vectPageRank_0,vectPageRank_1) > epsilon) {
			vectPageRank_0 = vectPageRank_1 ;
			vectPageRank_1 = multiply(vectPageRank_0, tr.pmatrix, tr.N);
		}
		
		return vectPageRank_0;
	}
	
	public static double distanceEuclidienne(double[] a1, double[] a2)
	{
		double diff = 0;
		for(int i=0 ; i < a1.length ; i++)
			diff += Math.pow(a1[i] - a2[i], 2); 
		return Math.sqrt(diff);
	}
	
	public static double sum(double tab[])
	{
		return Arrays.stream(tab)
		.sum();
	}
	
	public static double[] multiply(double[] vector , double[][] matrix, int N) {
		double vect[] = new double[N];
		double new_vect[] = new double[N];
		for(int i=0; i < N ; i++)
		{
			new_vect = extractColumn(matrix, i, N);
			for(int j=0; j < N ; j++)
			{
				vect[i] += vector[j] * new_vect[j];
			}
		}
		
	   return vect;
	   
	}
	
	public static double[] extractColumn(double[][] matrix , int j, int N)
	{
		double vect[] = new double[N];
		for(int i =0 ; i < N ; i++)
			vect[i] = matrix[i][j];
		return vect;
	}
	
	    
	public static void main(String[] args) {
		
		double[] rankVector = calcul(args[0], Double.parseDouble(args[1]), Double.parseDouble(args[2]));
		
		Arrays.stream(rankVector).forEach(p -> System.out.format("%.3f ", p ));
		
		
	}
	
		
	
}
