import java.util.*;
import java.io.*;

public class Main
{
	public static void main(String[] args) {
	   /* BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	    StringTokenizer st;*/
	    Scanner scan = new Scanner(System.in);
	    
	    
	    
	    
	    
	    
		System.out.print("Name: ");
		String name = scan.nextLine();
		System.out.println("Welcome "+name+" to Kaden's data structure dungeons");
		System.out.println("What Kind of data structure? linear/2d/tree/hashmap");
		String type = scan.nextLine();
		
		
		
		
		
		
		while (true){
		    if (type.equals("linear")){
            Hex map[]=new Hex[5];
            break;
        }
        else if (type.equals("2d")){
            Hex map[][]=new Hex[5][5];
            break;
        }
        else if(type.equals("hashmap")){
            HashMap<Hex, Integer> map = new HashMap<Hex, Integer>();
            break;
        }
        else if (type.equals("tree")){
            
            break;
        }
        
        System.out.println("try again");
        type = scan.nextLine();
        
		}
		
		
		
		
		
	}
}
