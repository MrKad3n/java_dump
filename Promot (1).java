import java.util.*;
import java.io.*;

public class Promot
{
	public static void main(String[] args) throws IOException{
	    BufferedReader br = new BufferedReader(new FileReader("promote.in"));
	    PrintWriter pw = new PrintWriter("promote.out");
	    /*BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	    PrintWriter pw = new PrintWriter(System.out);*/
	    
	    int orginal[]= new int[4];
	    int current[]= new int[4];
	    int moved[]= new int[3];
	    
	    StringTokenizer st;
	    
	    for (int i = 0;i<4;i++){
	        
	        st = new StringTokenizer(br.readLine());
	        orginal[i]=Integer.parseInt(st.nextToken());
	        current[i]=Integer.parseInt(st.nextToken());
	        
	        
	    }
	    
	    int moving=0;
		for (int i = 3;i>0;i--){
		    
            moving=current[i]-(orginal[i]-moving);
		    
		    
		    moved[i-1]=moving;
		}
		
		pw.println(moved[0]);
		pw.println(moved[1]);
		pw.println(moved[2]);
		pw.close();
	}
}
