package learnbydoing;
import java.util.Scanner;
import java.io.*;

public class Euler
{
	public void solve (int[][] arr)		//function to initiate the sudoku solver.
	{
		solve1(arr, 0);
	}
	int sum =0;
	public boolean solve1 (int[][] arr, int index)		//function to solve the sudoku and add the top left block number.
	{	
		if(index==81)
		{
			sum = sum + (100*arr[0][0] + 10*arr[0][1] + arr[0][2]);
			return true;
        }
		int row = index/9;
		int col = index%9;
		int flag =1;		//flag =0 implies the number we selected is already present in the same row/column/block.
		
		if(arr[row][col]==0)
		{
			for(int i=1; i<=9; i++)
			{		
					for(int j=0; j<9; j++)
					{
						if((arr[row][j] == i) || (arr[j][col]) == i)		//condition for a number to be present in the same row/column
							flag =0;
					}
					
					int r, c;
					r = row - row%3;
					c = col - col%3;
					for(int j=0; j<3; j++)
					{
						for(int k=0; k<3; k++)
						{
							if(arr[r+k][c+j]==i)			//condition for a number to be present in the same block.
								flag =0;
						}
					}
					
				if(flag ==1)				
				{
					arr[row][col] = i;
					if(solve1(arr, index+1))
						return true;
					else
						arr[row][col] = 0;
				}
			}
		}
		else
			return solve1(arr, index+1);
		return false;
	}
	
	public static void main( String []args) throws IOException
	{	
		Euler096 obj = new Euler096();		
		try
		{
			Scanner f = new Scanner(new File("sudoku.txt"));
			
			for(int i=0; i< 50; i++)
			{
				f.nextLine();
				int[][] arr = new int[9][9];
				for(int j=0; j<9; j++)					//loop for filling the Sudoku array with integral values.
				{					
					String s = f.nextLine();
					char[] line = s.toCharArray();
					
					for(int k=0; k<9; k++)
					{
						arr[j][k] = line[k] - '0';			
					}					
				}
				obj.solve(arr);
			}
			System.out.println(obj.sum);
			f.close();
		}
		
		catch (FileNotFoundException e)
		{
			e.printStackTrace();
		}	
	}
}
