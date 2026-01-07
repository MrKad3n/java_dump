import java.util.*;
import java.io.*;

public class Attack {
    private String name;
    
    private String source;
    private int power;
    private int magicPower;
    private int mana;
    private int skill;
    private int effect;
    
    
    public Attack(String n, int p, int mp, int m, int s, int e){
        
        name=n;
        power=p;
        magicPower=mp;
        mana=m;
        skill=s;
        effect=e;
        
    }
    
    public int getPower(){
        return power;
    }
    
    public String getName(){
        return name;
    }
    
    public String getSource(){
        return source;
    }
    
    public int magicPower(){
        return magicPower;
    }
    
    public int getMana(){
        return mana;
    }
    
    public int getSkill(){
        return skill;
    }
    
    public int getEffect(){
        return effect;
    }
    
    // Get attack cost description
    public String getCostDescription() {
        return (mana > 0) ? String.format("%d MP", mana) : "No cost";
    }
    
    // Check if attack is magical
    public boolean isMagical() {
        return magicPower > power;
    }
}