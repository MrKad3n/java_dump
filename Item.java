import java.util.*;
import java.io.*;

public class Item {
    private int strength;
    private int speed;
    private int defense;
    
    private int health;
    private int magic;
    private int ability;
    
    private String attack;
    private String name;
    private String slot;
    
    //Constructor
    
    public Item (int str, int sp, int de, int h, int m, int a, String at, String n, String s){
        
        strength=str;
        speed=sp;
        defense=de;
        
        health=h;
        magic=m;
        ability=a;
        
        attack=at;
        name=n;
        slot=s;
        
    }
    //defult item
    public Item (){
        
        strength=1;
        speed=1;
        defense=0;
        
        health=0;
        magic=1;
        ability=0;
        
        attack="slap";
        name="stick";
        slot="weapon";
        
    }
    
    //Accesor Methods
    
    public int getStrength(){
        
        return strength;
        
    }
    
    public int getSpeed(){
        
        return speed;
        
    }
    
    public int getMagic(){
        
        return magic;
        
    }
    
    public int getDefense(){
        
        return defense;
        
    }
    
    public int getHealth(){
        
        return health;
        
    }
    
    public int getAbility(){
        
        return ability;
        
    }
    
    public String getAttack(){
        
        return attack;
        
    }
    
    public String getName(){
        
        return name;
        
    }
    
    public String getSlot(){
        
        return slot;
        
    }
    
    
    //Update Methods - not implemented yet dont worry about
    
    
    
    
    
}