import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Euler059{
	
	public static int compatible (int a, int b, int c, int i, int[] arr){	//function to check if the character is a valid one or not.
		int x = arr[i]^a;
		int y = arr[i+1]^b;
		int z = arr[i+2]^c;
		if((x>=32 && x<=125) && (y>=32 && y<=125) && (z>=32 && z<=125))
			return 3;
		return 0;
	}
	
	public static int findsum(int a, int b, int c, int count, int[] arr){	//function to find the sum of ASCII of decrypted message.
		int sum =0;
		for(int i=0; i+2<count; i += 3)
			sum = sum + (arr[i]^a) + (arr[i+1]^b) + (arr[i+2]^c);
		return sum;
	}
	
	
	public static void main(String[] args){
		
		try{
			Scanner s = new Scanner(new File("p059_cipher.txt"));
			String n = s.nextLine();
			char[] nn = n.toCharArray();
			int count=1;
			for(int i=0; i<nn.length; i++)	if(nn[i]==',')	count++;	//count is the number of numbers. It's coming 1201 for this.
			int[] arr = new int[count];
					
			for(int i=0, j=0; i<nn.length; j++, i++){	//loop to parse the input into an integer array.
				arr[j] = nn[i] - '0';
				i++;			
				if(nn[i] != ','){
					arr[j] = arr[j]*10 + (nn[i] - '0');
					i++;
				}
			}
			
			int sum=0;
			int com=0;
			
OUTERMOST:	for(int a = 97; a <= 122; a++){			//checking through all the possible 3 letter passwords.
				for(int b = 97; b <= 122; b++){
					for(int c = 97; c <= 122; c++){
							for(int i=0; i+2<count; i+=3){
								com = com + compatible(a,b,c,i,arr);
							}
							if(com == count-count%3){
								sum = findsum(a,b,c,count,arr);
								break OUTERMOST;
							}
							com =0;
						}
					}
				}
						
			System.out.println(sum);
			s.close();
		}catch (FileNotFoundException e1){
			e1.printStackTrace();
		}		
	}
}
