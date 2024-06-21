import java.util.*;

//make a unique death for every character using their dark back story ability
public class Character {
    private String name;
    private int health;
    private int trust;
    private int power;
    // strong skills should debuff following skill, it should also reset after using
    // the skill so debuff isn't permanent
    private double inMultiplier;
    private double outMultiplier;
    private HashMap<String, Double> skills = new HashMap<>();

    public Character(String name, int health, int trust, int power) {
        inMultiplier = 1;
        outMultiplier = 1;
        this.name = name;
        this.health = health;
        this.trust = trust;
        this.power = power;
    }

    public String name() {
        return this.name;
    }

    public int health() {
        return this.health;
    }

    public void damageTaken(int damage) {
        health -= damage;
    }

    public void instantKill() {
        health = 0;
    }

    public int trust() {
        return this.trust;
    }

    public int power() {
        return this.power;
    }

    public void htpUpdate(int health, int trust, int power) {
        this.health += health;
        this.trust += trust;
        this.power += power;
    }

    public double inMultiplier() {
        return this.inMultiplier;
    }

    public double outMultiplier() {
        return this.outMultiplier;
    }

    public void inOutMultChange(double in, double out) {
        inMultiplier = in;
        outMultiplier = out;
    }

    public void multiplierReset() {
        inMultiplier = 1.0;
        outMultiplier = 1.0;
    }

    public String quickCharacterChoice() {
        return "failure, facade, flower, fate, (ANY OTHER WORD WILL ALSO WORK)";
    }

    public Set<String> quickSkills() {
        Set<String> skillSet = skills.keySet();
        return skillSet;
    }

    // should create the character and maintain information from the player input
    // and introduce the character
    public Character initiation(String name) {
        switch (name) {
            case "failure":
                this.name = "The Battle Hardened Mercenary";
                System.out.println("\nThe one that has cowered under your wing is known as " + this.name);
                System.out.println(
                        "The contract states that this one will heed your every word regardless of their desires, mercenaries will kill anything for you for the right price\n"
                                + this.name + " looked away upon this being mentioned");
                return new Character(this.name, 200, 5, 30);
            case "facade":
                this.name = "The Infallible Gladiator";
                System.out.println("\nThe one that has cowered under your wing is known as " + this.name);
                System.out.println(
                        "The glory of battle entices them, only the strongest should survive, gladiators must please the audience afterall\n"
                                + this.name + " shivered upon hearing such words for some reason");
                return new Character(this.name, 145, 7, 45);
            case "flower":
                this.name = "The Suspicious Hooded Figure";
                System.out.println("\nThe one that has cowered under your wing is known as " + this.name);
                System.out.println(
                        "Secrecy of the truth is of utmost importance, don't let it spread, don't let it grow into an unstoppable figure, bring light to dark histories\n"
                                + this.name + " bites their nails as they look at you with a pained expression");
                return new Character(this.name, 90, 2, 65);
            case "fate":
                this.name = "The Lost Child";
                System.out.println("\nThe one that has cowered under your wing is known as " + this.name);
                System.out.println(
                        "Pathetic vermin should not live, they only take and have no dreams worth considering, vanquish them all, this one is simply too weak to survive here, why did you pick them?\n"
                                + this.name + " scoots away when they hear this");
                return new Character(this.name, 80, 10, 20);
            default:
                this.name = "The Doomed Soldier";
                System.out.println("\nThe one that has cowered under your wing is known as " + this.name);
                System.out.println("This faceless soldier has caught your eye, they are underprepared");
                return new Character(this.name, 100, 3, 15);
        }
    }

    // one fills it with skills a character can use, one check using the character
    // name and say what they can do, and then player command of what they want to
    // do
    // needs three more systems probably, skills and their attributes (power *
    // percent damage) in randomizer like darkest dungeon skills
    // one what it actually will do when used and minus health to what it attacks
    // one more for printing what it has done

    // according to name, make skills hashmap what can they do (damage)

    public double randomizer(double min, double max) {
        return (double) (Math.random() * (max - min) + min);
    }

    public void abilities() {
        switch (name) {
            case "The Battle Hardened Mercenary":
                skills.put("hew", 1.5);
                skills.put("shield", 0.3);
                skills.put("bash", 0.8);
                skills.put("killer", 4.0);
                break;
            case "The Infallible Gladiator":
                skills.put("havoc", 1.5);
                skills.put("shocker", 1.0);
                skills.put("finale", 2.6);
                skills.put("showtime", 0.5);
                break;
            case "The Suspicious Hooded Figure":
                skills.put("punch", 0.5);
                skills.put("flame", 1.0);
                skills.put("molotov", 3.5);
                skills.put("replenish", 0.0);
                break;
            case "The Lost Child":
                skills.put("sickle", 1.5);
                skills.put("rock", 1.0);
                skills.put("gun", 10.0);
                skills.put("selfcare", 0.0);
                break;
            case "The Doomed Soldier":
                skills.put("slash", 1.5);
                skills.put("slug", 0.5);
                skills.put("respite", 0.0);
                skills.put("mark", 0.0);
                break;
        }
    }

    // what does each skill also do (like status effects)
    public String attributes(String action) {
        switch (action) {
            case "hew":
                return "\nA cleave by " + name + " culls the target";
            case "shield":
                inMultiplier = 0.1;
                return "\nThe shield rises with might to guard " + name;
            case "bash":
                inMultiplier = 0.3;
                return "\nThe shield of " + name + " shoves the target against the wall";
            case "killer":
                inMultiplier = 1.0;
                trust -= 2;
                return "\nLowering their shield " + name
                        + " performs effortless strikes that divorces it from life \nSuch actions brings back nightmares of the battlefield\n"
                        + name + " shudders, this is clearly excessive for the situation\n" + name
                        + " Looks at you and mumbles, 'Please dont make me do that again, I have already taken too much'\n"
                        + //
                        "The Shock is setting in";
            case "havoc":
                outMultiplier = 1.5;
                return "\nBringing down their weapon with might, they tried to eviscerate the foe, the foe's defense is sheared";
            case "shocker":
                inMultiplier = 0.5;
                return "\nA bash in the head leaves the target reeling in response";
            case "finale":
                inMultiplier = 0.6;
                outMultiplier = 0.9;
                return "\n" + name
                        + " hits the foe strike after strike leaving them crippled. It was tiring to use such force however";
            case "showtime":
                inMultiplier = 1.5;
                outMultiplier = 3.0;
                power += 30;
                trust -= 3;
                return "\n" + name
                        + " is fueled with a intent to please, their heart rate accelerates and exposes themselves to the danger, hungry for blood\n"
                        + name
                        + " feels their sanity, their reputation slip away to reveal a darker side that they have tried to keep hidden this whole time\nThey grip their chest and pant, the pressure is building, the fanfare is beginning, the eyes are watching\nThe Shock is setting in";
            case "punch":
                outMultiplier = 1.5;
                inMultiplier = 0.7;
                return "\nA punch from " + name + " leaves the target reeling! It exposes their weaknesses";
            case "flame":
                power -= 10;
                return "\nA stream of flame shoots from " + name
                        + "'s lighter and ignites the target\nThey sweat slightly after concentrating so hard";
            case "molotov":
                power -= 20;
                health -= 15;
                return "\nAn explosion of fire ignites the whole room including " + name
                        + "\nThe energy that they spent to create such an inferno leaves them out of breath";
            case "replenish":
                power = 65;
                outMultiplier = 1.2;
                if (health < 90 || power < 65) {
                    health += (int) (randomizer(25, 45));
                }
                return "\nA breather and recovery magic allows " + name
                        + " to regain their energy, they may fight again with all their might";
            case "sickle":
                return "\nGardening work has helped " + name + " comprehend how to prune away their life force";
            case "rock":
                inMultiplier = 0.6;
                return "\n" + name + " tossed a rock and scored a hit in the face, confusing the target";
            case "gun":
                trust -= 3;
                power -= 5;
                return "\nDespite the fear of such a tool " + name
                        + " listens to your command \nand fires a shot that rings out through the entire dungeon. \nSilence follows and "
                        + name
                        + " trembles with shock, a tear springs from their eyes, they are frightened, they are breaking \nThe Shock is setting in";
            case "selfcare":
                if (health < 80 || power < 20) {
                    if (trust < 9) {
                        trust++;
                    }
                    health += (int) (randomizer(30, 55));
                    power = 20;
                    return "\nMother had always told " + name
                            + " that kissing the wounds would help make them feel better";
                } else if (trust < 10) {
                    return "\n" + name + " resisted the command to care for themselves, they looked away hurt";
                }
                return "\n" + name + " does not need to care for themselves at the moment";
            case "slash":
                return "\nA cleave by " + name + " culls the target";
            case "slug":
                return "\nA slug from " + name + " leaves the target reeling!";
            case "respite":
                if (health < 100) {
                    health += (int) (randomizer(25, 40));
                    return "\nA moment of respite helps recover some injuries";
                }
                return "\n" + name + " does not need to care for themselves at the moment";
            case "mark":
                outMultiplier = 2.0;
                return "\nA marker of the enemies weak spots have been revealed! Do not leave them unharried";
            default:
                return null;
        }
    }

    public String conversation(String topic) {
        switch (topic) {
            case "health":
                if (trust >= 5) {
                    return name + " looks at you and says\n'My current health is around " + health + "'";
                } else {
                    return name
                            + " glances off into the distance and mutters unhelpfully\n'You have eyes, look yourself'";
                }
            case "trust":
                if (trust >= 10) {
                    return name
                            + " looks at you with a slight smile, a soft gaze, and nodded their head with confidence towards you\nThey had faith in your words and trusted you. Surely you wouldn't lead them wrong now in this dire time\n'You got my back right?'\n";
                } else if (trust >= 5) {
                    return name
                            + " could care less about answering this\nBut exasperatedly tells you anyways that it is enough for them to hear you out so they can get out\n'A god doesn't actually care about those they watch over, they all have something they are trying to gain, something they want to take'\n";
                } else if (trust >= 3) {
                    return name
                            + " stares at you wearily with an untrusting gaze. \nYou have failed to provoke a sense of trust. Yet they will await to heed your next judgment.\n";
                } else {
                    return name
                            + "'s hands quivered from the insanity of your statements, they wished to tune you out but your words pierce through their soul. \nThey shake their head in stress but they still will proceed to carry out your wishes\n";
                }
            default:
                return null;
        }
    }

    // the damage and the system calculating what is using this skill doing
    // add in the reduction of target health
    public int combat(String action) {
        return (int) ((skills.get(action) * power) * randomizer(0.5, 1.5) * outMultiplier);
    }
    // Inventory System

    // private HashMap<String, Item> itemNames = new HashMap<>();
    // private Set<String> items;
    private ArrayList<Item> items = new ArrayList<>();

    public void addItem(Item item) {
        if (items.indexOf(item) == -1) {
            items.add(item);
        }
    }

    // you should readd the item back to the room should you drop the item
    public void removeItem(Item item) {
        if (items.indexOf(item) != -1) {
            items.remove(items.indexOf(item));
        }
    }

    public void removeItem(String item) {
        for (int i = 0; i < items.size(); i++) {
            if (items.get(i).getName().equals(item)) {
                items.remove(i);
                break;
            }
        }
    }

    public ArrayList<String> itemList() {
        ArrayList<String> itemNames = new ArrayList<>();
        for (int i = 0; i < items.size(); i++) {
            itemNames.add(items.get(i).getName() + "\n");
        }
        return itemNames;
    }

    // Returns the string of all the items in the arraylist so that the player can
    // see what is in the inventory
    public String itemString() {
        ArrayList<String> itemList = itemList();
        String itemString = "\n";
        for (int i = 0; i < itemList.size(); i++) {
            itemString += itemList.get(i);
        }
        return itemString;
    }
    // intended to add items from the item class into the inventory
    /*
     * public void addItem(Item item) {
     * if (!itemNames.containsKey(item.itemName())) {
     * itemNames.put(item.itemName(), item);
     * }
     * }
     * 
     * // intended to print out the inventory
     * public Set<String> itemList() {
     * items = itemNames.keySet();
     * return items;
     * }
     */
}
