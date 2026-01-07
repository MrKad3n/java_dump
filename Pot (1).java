import java.util.*;
import java.io.*;
public class Pot
{
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int test = Integer.parseInt(br.readLine());
		
		int num;
		HashMap<Integer,Integer> height = new HashMap<>();
		
		int cows;
		int max;
		int tall;
		
		int counts[]=new int[test];
		
		for (int cas = 0;cas<test;cas++){
		    cows=1;
		    max=0;
		    num = Integer.parseInt(br.readLine());
		    st=new StringTokenizer(br.readLine());
		    
		    for (int i = 0;i<num;i++){
		        tall=Integer.parseInt(st.nextToken());
		        if (tall>max){
		            max=tall;
		        }
		        
		        if (height.containsKey(tall)){
		            height.put(tall,height.get(tall)+1);
		        }
		        else {
		            height.put(tall,1);
		        }
		        
		    }
		    
		        height.remove(max);
		    
		    for (int value : height.values()) {
            if (value>=2) {
                cows+=2;
                }
                
            }
		    counts[cas]=cows;
		    height.clear();
		}
		
		for (int i =0;i<test;i++){
		    System.out.println(counts[i]);
		}
		
	}
}
