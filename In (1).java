import java.util.*;
import java.io.*;

public class In
{
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int num = Integer.parseInt(st.nextToken());
		int test = Integer.parseInt(st.nextToken());
		String full = br.readLine();
		
		int max;
		String word;
		int left;
		int right;
		int calc;
		int tri[]=new int[test];
		int length;
		
		
		
		for (int l = 0;l<test;l++){
		    max=-1;
		    st = new StringTokenizer(br.readLine());
		    left=Integer.parseInt(st.nextToken())-1;
		    right=Integer.parseInt(st.nextToken());
		    
		    word=full.substring(left,right);
		    length=word.length();
		    for (int i = 0;i<length-2;i++){
		        for (int j = i+1;j<length-1;j++){
		            for (int k =j+1;k<length;k++){
		                
		                
		                if (word.substring(j,j+1).equals(word.substring(k,k+1))){
		                    if (!word.substring(i,i+1).equals(word.substring(j,j+1))){
		                       calc=(j-i)*(k-j);
		                       if (max<calc){
		                           max=calc;
		                       }
		                       
		                        
		                    }
		      
		                }
		                
		            }
		        }
		        
		        
		    }
		    tri[l]=max;
		    
		}
		for (int i =0;i<test;i++){
		    System.out.println(tri[i]);
		    
		}
		
		
		
	}
}
