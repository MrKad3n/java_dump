import java.util.*;
import java.io.*;

public class Map {
    
    private Hex[] path;
    //creates a random dungeon based of level and type/lay out
    public Map(int lvl, String list){
        int ran=(int)(Math.random()*6);
        String[] types = {"undead", "flying", "army", "unknown", "creature", "ocean"};
        String type = types[(int)(Math.random() * types.length)];

        //currently only needs this one, in future I will make more structures/maps
        //if (list.equals("array")){
            int length=(int)(Math.random()*4+6);
            if (length%2==1){
                length+=1;
            }
            path=new Hex[length];
            for (int i =0;i<2;i++){
                for (int j=0;j<(length/2-1)-i;j++){
                    path[(i*length/2)+j]=new Hex("base", lvl, type);
                }
                path[((i+1)*length/2)-1]=new Hex("miniBoss", lvl, type);
            }
            path[length-1]=new Hex("boss", lvl, type);
            path[0]=new Hex("start", lvl, type);
            
            
        //}
        
        
    }
    
    //accessor Methods
    
    public Hex getHex(int num){
        return path[num];
    }
    
    // Get hex by index with bounds checking
    public Hex getSafeHex(int index) {
        return (index >= 0 && index < path.length) ? path[index] : null;
    }
    
    // Get map length
    public int getLength() {
        return path.length;
    }
    
    // Check if boss is defeated
    public boolean isBossDefeated() {
        return path[path.length-1].getCleared();
    }
}