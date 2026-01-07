import java.util.*;

public class Battle {
    private Person[] party;
    private Mob[] enemies;
    private Person current;
    private int alive;
    private int remaining;
    private List<TurnUnit> turnUnits = new ArrayList<>();
    private Queue<TurnUnit> turnQueue = new LinkedList<>();
    private final int TURN_THRESHOLD = 100;
    private Random random = new Random();

    // Inner class to represent a unit in battle with a turn meter
    private class TurnUnit {
        Object character; // Person or Mob
        int turnMeter = 0;

        TurnUnit(Object character) {
            this.character = character;
        }

        int getSpeed() {
            if (character instanceof Person) return ((Person) character).getSpeed();
            if (character instanceof Mob) return ((Mob) character).getSpeed();
            return 0;
        }

        boolean isAlive() {
            if (character instanceof Person) return ((Person) character).getHealth() > 0;
            if (character instanceof Mob) return ((Mob) character).getHealth() > 0;
            return false;
        }

        String getName() {
            if (character instanceof Person) return ((Person) character).getName();
            if (character instanceof Mob) return ((Mob) character).getName();
            return "";
        }
    }

    public Battle(Person[] p, Mob[] e) {
        party = p;
        enemies = e;
        current = party[0];
        alive = party.length;
        remaining = enemies.length;

        for (Person person : party) turnUnits.add(new TurnUnit(person));
        for (Mob mob : enemies) turnUnits.add(new TurnUnit(mob));
    }

    public void updateAlive() {
        int count = 0;
        for (Person person : party) {
            if (person.getHealth() > 0) count++;
        }
        alive = count;
    }

    public void updateRemaining() {
        int count = 0;
        for (Mob mob : enemies) {
            if (mob.getHealth() > 0) count++;
        }
        remaining = count;
    }

    private void updateTurnQueue() {
        for (TurnUnit unit : turnUnits) {
            if (!unit.isAlive()) continue;
            unit.turnMeter += unit.getSpeed();
            if (unit.turnMeter >= TURN_THRESHOLD) {
                turnQueue.offer(unit);
                unit.turnMeter -= TURN_THRESHOLD;
            }
        }

        // Randomize tied turn order
        List<TurnUnit> shuffled = new ArrayList<>(turnQueue);
        Collections.shuffle(shuffled);
        turnQueue = new LinkedList<>(shuffled);
    }

    public String processTurn(int attackSlot, int targetIndex) {
        StringBuilder log = new StringBuilder();
        updateTurnQueue();

        if (turnQueue.isEmpty()) {
            log.append("No one is ready to act.\n");
            return log.toString();
        }

        TurnUnit unit = turnQueue.poll();
        if (!unit.isAlive()) return ""; // Skip dead units

        // Player-controlled Person
        if (unit.character instanceof Person) {
            Person attacker = (Person) unit.character;
            current = attacker;

            if (targetIndex < 0 || targetIndex >= enemies.length) {
                log.append("Invalid target.\n");
                return log.toString();
            }

            Mob target = enemies[targetIndex];
            if (target.getHealth() <= 0) {
                log.append("Target is already defeated.\n");
                return log.toString();
            }

            Attack attack = attacker.getEquippedAttack(attackSlot);
            if (attack == null) {
                log.append("Invalid attack.\n");
                return log.toString();
            }

            int damage = calculateDamage(attack, attacker, target);
            target.setHealth(target.getHealth() - damage);

            log.append(String.format("%s used %s on %s for %d damage!\n",
                attacker.getName(), attack.getName(), target.getName(), damage));

            if (target.getHealth() <= 0) {
                target.setHealth(0);
                updateRemaining();
                log.append(String.format("%s was defeated!\n", target.getName()));
            }
        }

        // Enemy AI (Mob)
        else if (unit.character instanceof Mob) {
            Mob attacker = (Mob) unit.character;
            Person target = getRandomAlivePartyMember();

            if (target == null) {
                log.append("Your party was defeated. Game Over!\n");
                return log.toString();
            }

            int damage = calculateEnemyDamage(attacker, target);
            target.setHealth(target.getHealth() - damage);

            log.append(String.format("%s attacked %s for %d damage!\n",
                attacker.getName(), target.getName(), damage));

            if (target.getHealth() <= 0) {
                target.setHealth(0);
                updateAlive();
                log.append(String.format("%s was defeated!\n", target.getName()));
                if (target.equals(current)) {
                    current = getFirstAlivePartyMember();
                }
            }
        }

        // Victory or defeat check
        if (remaining <= 0) {
            grantRewards();
            log.append("You defeated all enemies! Victory!\n");
        } else if (alive <= 0) {
            log.append("Your party was defeated. Game Over!\n");
        }

        return log.toString();
    }

    private int calculateDamage(Attack attack, Person attacker, Mob target) {
        double physical = attacker.getStrength() * (attack.getPower() / 100.0);
        double magical = attacker.getMagic() * (attack.magicPower() / 100.0);
        double total = physical + magical;
        double defenseFactor = 1.0 - (target.getDefense() / 100.0);
        defenseFactor = Math.max(0.1, defenseFactor);
        return (int) (total * defenseFactor);
    }

    private int calculateEnemyDamage(Mob attacker, Person target) {
        double defenseFactor = 1.0 - (target.getDefense() / 100.0);
        defenseFactor = Math.max(0.1, defenseFactor);
        return (int) (attacker.getStrength() * defenseFactor);
    }

    private Person getRandomAlivePartyMember() {
        List<Person> aliveMembers = new ArrayList<>();
        for (Person member : party) {
            if (member.getHealth() > 0) aliveMembers.add(member);
        }
        return aliveMembers.isEmpty() ? null :
               aliveMembers.get(random.nextInt(aliveMembers.size()));
    }

    private Person getFirstAlivePartyMember() {
        for (Person member : party) {
            if (member.getHealth() > 0) return member;
        }
        return null;
    }

    // Getters
    public Person getCurrentCharacter() {
        return current;
    }

    public Mob[] getEnemies() {
        return enemies;
    }

    public boolean isBattleOver() {
        return remaining <= 0 || alive <= 0;
    }

    public boolean isVictory() {
        return remaining <= 0;
    }

    public void switchCharacter(int characterIndex) {
        if (characterIndex >= 0 && characterIndex < party.length
                && party[characterIndex].getHealth() > 0) {
            current = party[characterIndex];
        }
    }
    
    //battle rewards
    private void grantRewards() {
        int totalExp = 0;

        for (Mob m : enemies) {
            int level = m.getLevel();
            int tier = m.getTier(); // assuming this method exists
            int exp = (int) ((level / 20.0) * Math.pow(tier, 2)); // cast result to int
            totalExp += exp;
        }

        current.addExp(totalExp);

        // Optionally generate an item reward
        ItemGenerator generator = new ItemGenerator();
        Item reward = generator.generateRandomItem();
        Person.getItem(reward.getSlot(), reward);
        
    }
    
    // Get alive party members
    public List<Person> getAliveParty() {
        List<Person> alive = new ArrayList<>();
        for (Person p : party) {
            if (p.isAlive()) alive.add(p);
        }
        return alive;
    }
    
    // Get alive enemies
    public List<Mob> getAliveEnemies() {
        List<Mob> alive = new ArrayList<>();
        for (Mob m : enemies) {
            if (m.isAlive()) alive.add(m);
        }
        return alive;
    }
    
    // Battle summary
    public String getBattleState() {
        return String.format(
            "Party: %d/%d alive | Enemies: %d/%d remaining",
            alive, party.length, remaining, enemies.length
        );
    }
}
