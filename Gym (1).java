import java.util.*;
import java.io.*;

public class Gym
{
	public static void main(String[] args) throws IOException{
	//	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		BufferedReader br = new BufferedReader(new FileReader("gymnastics.in"));
		PrintWriter pw = new PrintWriter("gymnastics.out");
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int pratices = Integer.parseInt(st.nextToken());
		int num = Integer.parseInt(st.nextToken());
		
		int ranks[][]= new int[pratices][num];
		for (int i =0;i<pratices;i++){
		    st = new StringTokenizer(br.readLine());
		    for (int j =0;j<num;j++){
		        ranks[i][j]=Integer.parseInt(st.nextToken());
		    }
		}
		
		HashMap<String, Integer> pairs = new HashMap<>();
		
		for (int i = 0;i<pratices;i++){
		    for (int j =0;j<num-1;j++){
		        for (int k = j+1;k<num;k++){
		            
		            if (pairs.containsKey(ranks[i][j]+" "+ranks[i][k])){
		                pairs.put(ranks[i][j]+" "+ranks[i][k],pairs.get(ranks[i][j]+" "+ranks[i][k])+1);
		            }
		            else {
		                pairs.put(ranks[i][j]+" "+ranks[i][k],1);
		            }
		            
		        }
		    }
		}
		int count = 0;
		
		for (int value : pairs.values()) {
            if (value == pratices) {
                count++;
            }
        }
		
		pw.println(count);
		pw.close();
	}
}
