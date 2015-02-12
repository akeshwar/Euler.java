import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Euler067 {
	int sum = 0;
	
	public static void main(String[] args){
		try{
			Scanner s = new Scanner(new File("p067_triangle.txt"));
			int arr[][] = new int[100][100];
			for(int i=0; i<100; i++){
				for(int j=0; j<=i; j++){
					arr[i][j] = s.nextInt();		//parsing the elements in 2D array
				}
			}

/*LOGIC: Start from the bottom-most row and keep adding the bigger number from all the pairs (PAIR = two adjacent numbers) 
 * to the parent value just above the the pair. 
 */
			for(int i=99; i>0; i--){				
				for(int j=0; j<i; j++){
					if(arr[i][j] > arr[i][j+1]){	
						arr[i-1][j] = arr[i-1][j] + arr[i][j];
					}
					else{
						arr[i-1][j] = arr[i-1][j] + arr[i][j+1];
					}
				}
			}
			
			System.out.print(arr[0][0]);	
		s.close();
		}catch (FileNotFoundException e1) {
		      e1.printStackTrace();
	    }  		
	}
}
