import java.util.*;
import java.io.*;
import java.util.Map;


public class Person{
    private String name;
    private Attack[] equippedAttack = new Attack[5];
    private ArrayList<Attack> attackInventory;
    private HashMap<String, Item> equiped;
    private static HashMap<String, Item> inventory = new HashMap<>();;
    
    private int maxLevel;
    private int maxHealth;
    
    private int level;
    private int strength;
    private int speed;
    private int magic;
    
    private int defense;
    private int health;
    private int exp;
    
    private HashMap<String, Integer> stats = new HashMap<String, Integer>();
    
    //constructors
    
    public Person (String n){
        
        name = n;
        level=1;
        equiped= new HashMap<String, Item>();
        inventory= new HashMap<String, Item>();
        this.updateStats();
        
    }
    //default person
    public Person (){
        
        name = "Bob";
        level=1;
        equiped= new HashMap<String, Item>();
        inventory= new HashMap<String, Item>();
        
    }
    
    //accessor methods
    
    public static HashMap<String, Item> getInventory() {
        return inventory;
    }
    
    public String getName(){
        return name;
    }
    
    public int getStrength(){
        return strength;
    }
    
    public int getSpeed(){
        return speed;
    }
    
    public int getLevel(){
        return level;
    }
    
    public int getMaxHealth(){
        return maxHealth;
    }
    
    public int getHealth(){
        return health;
    }
    
    public int getMagic(){
        return magic;
    }
    
    public int getDefense(){
        return defense;
    }
    
    public boolean isAlive() {
    return health > 0;
    }
    
    //accesses an attack from the equipped attack list
    public Attack getEquippedAttack(int slot){
        return equippedAttack[slot];
    }
    
    
    public Attack getAttack(int slot){
        return attackInventory.get(slot);
    }
    
    
    // set methods
    
    public void newName(String n){
        name=n;
    }
    
    public void setHealth(int health) {
        this.health = Math.min(health, maxHealth);
    }
    
    public void addExp(int e){
        while (e>=exp){
            level++;
            e-=exp;
            exp=level*level;
        }
        exp-=e;
    }
    
    
    
    public void setMax(int l){
        maxLevel=l;
    }
    
    public static void getItem(String type, Item piece) {
        inventory.put(type, piece);
    }
    
    public void equip(Item piece){
        
        Item current = null;
        for (Map.Entry<String, Item> entry : equiped.entrySet()) {
            Item equippedItem = entry.getValue();
            if (equippedItem.getSlot().equals(piece.getSlot())) {
                current = equippedItem;
                break;
            }
        }
    
        if (current != null) {
            inventory.put(current.getName(), current);
            equiped.remove(current.getName());
        }
    
        equiped.put(piece.getName(), piece);
        inventory.remove(piece.getName());
        
       /* Item current = null;
        for (Map.Entry<String, Item> entry: equiped.entrySet()){
            current=entry.getValue();
            if (current.getSlot().equals(piece.getSlot())){
                break;
            }
            
        }
        inventory.put(current.getName(),current);
        equiped.put(piece.getName(),piece);
        inventory.remove(piece.getName());
        equiped.remove(current.getName());*/
        
    }
    
    //update methods
    
    public void updateStats(){
        
        int sum=0;
        
        //updates level based stats;
        
        strength=(int)(level*Math.sqrt(level));
        speed=(int)(Math.sqrt(level)+level);
        magic=(int)(level*Math.sqrt(level));
        health=(int)(level*Math.sqrt(level));
        defense=0;
        
        for (Map.Entry<String, Item> entry : equiped.entrySet()) {
            Item item = entry.getValue();
            strength += item.getStrength();
            speed += item.getSpeed();
            magic += item.getMagic();
            health += item.getHealth();
            defense += item.getDefense();
        }
        
        
        
    }
    
    public void addAttack(Attack attack) {
        if (attackInventory == null) {
            attackInventory = new ArrayList<>();
        }
        attackInventory.add(attack);
    }

    public void equipAttack(int slot, Attack attack) {
        if (slot >= 0 && slot < equippedAttack.length) {
            equippedAttack[slot] = attack;
        }
    }
    
}