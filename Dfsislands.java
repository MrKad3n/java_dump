import java.util.*;
import java.io.*;

public class Dfsislands
{
	public static void main(String[] args) throws IOException{
	    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	    StringTokenizer st;
	    
	    Stack<Integer> stack = new Stack<>();
        Set<Integer> visited = new HashSet<>();
	    
		System.out.println("How big is the grid");
		int num=Integer.parseInt(br.readLine());
		int count=0;
		
		HashMap<Integer, List<Integer>> Map = new HashMap<>();
		//Intiliaze Land
		for (int i =0;i<num;i++){
		    st=new StringTokenizer(br.readLine());
		    List<Integer> tokenList = new ArrayList<Integer>();
		    while(st.hasMoreTokens()){
		        tokenList.add(Integer.parseInt(st.nextToken()));
		        Map.put(i, tokenList);
		    }
		}
		//Print the map for debug
		for (int i =0;i<num;i++){
		    System.out.println(Map.get(i));
		}
		int land=0;
		int current=1;
		int row=0;
		int col=0;
		
		for (int i=0;i<num;i++){
		  for (int node=0; node<num;node++){
		      
		      if (Map.get(i).get(node)==1&&!visited.contains(i*num+node)){
		          //Stack starts on nearest unvisited 1
		          stack.push(i*num+node);
		          //Does DFS until found all nearest unvisited 1
		          while(!stack.isEmpty()){
		                //Does current based off a grid system
            		    current=stack.pop();
            		    row=(int)(current/num);
            		    col=current%num;
            		    System.out.print(current+"  ");
            		    
            		    
            		    if (!visited.contains(current)){
            		        visited.add(current);
            		        //ABOVE
            		        
            		        if (row>0){
            		            if (!visited.contains(current-num)&&Map.get(row-1).get(col)==1) {
                                    stack.push(current-num);
                                }
            		        }
            		        //RIGHT
            		        if (col<num-1){
            		            if (!visited.contains(current+1)&&Map.get(row).get(col+1)==1) {
                                    stack.push(current+1);
                                }
            		        }
            		        //BELOW
            		        if (row<num-1){
            		            if (!visited.contains(current+num)&&Map.get(row+1).get(col)==1) {
                                    stack.push(current+num);
                                }
            		        }
            		        //LEFT
            		        if (col>0){
            		            if (!visited.contains(current-1)&&Map.get(row).get(col-1)==1) {
                                    stack.push(current-1);
                                }
            		        }
            		        
            		                
            		        
            		    }
            		}
            		//For each Island
            		count++;
		      }
		  }
		  //END OF FOR LOOP
		}
		//Number of Islands
		System.out.println("The number of islands is "+count);
		
		
	}
}
