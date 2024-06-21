import java.util.*;

//Mental Note:
//ENEMIES SHOULD NOT KNOW WHICH ROOM THEY ARE IN, THEY SHOULD BE GENERAL AND COULD FIT ANYWHERE
public class Enemy {
    private String name;
    private int health;
    private int difficulty;
    private int power;
    private double inMultiplier;
    private double outMultiplier;
    private HashMap<String, Double> skills = new HashMap<>();
    // we need to define the enemies in the room, and which room they are at

    // DECIDE ON IF ENEMIES CAN BE FOUND GOING BACK AND FORTH IN THE GAME OR IF IT
    // WILL BE ASSIGNED TO ROOMS UPON GENERATION
    public Enemy(String name, int health, int difficulty, int power) {
        inMultiplier = 1;
        outMultiplier = 1;
        this.name = name;
        this.health = health;
        this.difficulty = difficulty;
        this.power = power;
        abilities(name);
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

    public int difficulty() {
        return this.difficulty;
    }

    public int power() {
        return this.power;
    }

    public double inMultiplier() {
        return this.inMultiplier;
    }

    public double outMultiplier() {
        return this.outMultiplier;
    }

    public void multiplierReset() {
        inMultiplier = 1.0;
        outMultiplier = 1.0;
    }

    public void enemyLife() {
        if (health <= 0) {
            this.name = "Dead " + name;
        }
    }

    // MAKE SURE THIS IS USED IN THE CHILDREN ROOM SO THAT WHEN ROOMS ARE CREATED SO
    // WILL THE ENEMIES BE GENERATED ALONGSIDE THEM
    // THE STORY IS NOT DONE FOR EACH THING, LET'S TOUCH ON THIS LATER
    public static Enemy initiation(int num) {
        switch (num) {
            // hound skeleton should be a pair
            case 1:
                //System.out.println(
                        //"\nThere is an enemy here! They are known as the " + "Hound" + ". Their mouth is frothing");
                return new Enemy("Hound", 50, 4, 8);
            case 2:
                //System.out.println("\nThere is an enemy here! They are a broken Mercenary " + "Skeleton"
                        //+ ", they once fought for a price, now they must pay for their greed");
                return new Enemy("Skeleton", 70, 6, 10);
            // armor and mage should be a pair
            case 3:
                //System.out.println(
                        //"\nThere is an enemy here! A Suit of " + "Armor" + " respectfully nods at you for a duel");
                return new Enemy("Armor", 300, 5, 5);
            case 4:
                //System.out.println("\nThere is an enemy here! They are a glorious " + "Mage"
                        //+ ". They proclaim themselves as the owner of the armor");
                return new Enemy("Mage", 100, 5, 8);
            case 5:
                //System.out.println("\nThere is an enemy here! They are a live breathing " + "Soldier"
                        //+ " who has come to end you");
                return new Enemy("Solider", 200, 10, 15);
            default:
                return null;
        }
    }

    public double randomizer(double min, double max) {
        return (double) (Math.random() * (max - min) + min);
    }

    // Randomize between the two abilities
    // we can do Math.random()*2 for 50/50
    public int autoCast() {
        int num = (int) (Math.random() * 2);
        switch (name) {
            case "Hound":
                if (num == 0) {
                    System.out.println(attributes("bite"));
                    return combat("bite");
                } else {
                    System.out.println(attributes("claw"));
                    return combat("claw");
                }
            case "Skeleton":
                if (num == 0) {
                    System.out.println(attributes("slash"));
                    return combat("slash");
                } else {
                    System.out.println(attributes("shoulder"));
                    return combat("shoulder");
                }
            case "Armor":
                if (num == 0) {
                    System.out.println(attributes("hew"));
                    return combat("hew");
                } else {
                    System.out.println(attributes("shield"));
                    return combat("shield");
                }
            case "Mage":
                if (num == 0) {
                    System.out.println(attributes("fireball"));
                    return combat("fireball");
                } else {
                    System.out.println(attributes("magicshield"));
                    return combat("magicshield");
                }
            case "Soldier":
                if (num == 0) {
                    System.out.println(attributes("cut"));
                    return combat("cut");
                } else {
                    System.out.println(attributes("rush"));
                    return combat("rush");
                }
            default:
                return 0;
        }
    }

    public void abilities(String name) {
        switch (name) {
            case "Hound":
                skills.put("bite", 1.5);
                skills.put("claw", 0.8);
                break;
            case "Skeleton":
                skills.put("slash", 1.0);
                skills.put("shoulder", 0.5);
                break;
            case "Armor":
                skills.put("hew", 1.0);
                skills.put("shield", 0.2);
                break;
            case "Mage":
                skills.put("fireball", 1.0);
                skills.put("magicshield", 0.3);
                break;
            case "Soldier":
                skills.put("cut", 1.0);
                skills.put("rush", 1.5);
                break;
        }
    }

    public String attributes(String action) {
        switch (action) {
            case "bite":
                inMultiplier = 1.5;
                outMultiplier = 1.5;
                return "\nA bite by " + name + " scars your patron";
            case "claw":
                multiplierReset();
                return "\nA claw by " + name + " scratches your patron";
            case "slash":
                multiplierReset();
                return "\nA quick slash by " + name + " cuts your patron";
            case "shoulder":
                inMultiplier = 0.8;
                return "\nCareful! The " + name
                        + " mercenary has shoulder checked you, throwing your patron off balance";
            case "hew":
                multiplierReset();
                return "\nA horrific cleave from " + name + " struck down your patron";
            case "shield":
                inMultiplier = 0.2;
                return "\nThe " + name + " raised their heavy shield and gently shoved your patron with it";
            case "fireball":
                multiplierReset();
                return "\nThe " + name + " casted a fireball on your patron, leaving them burned";
            case "magicshield":
                inMultiplier = 0.2;
                return "\nThe " + name
                        + " has casted a magical shield and is immune to all damage, the force pushed your patron";
            case "cut":
                multiplierReset();
                return "\nA cut from " + name + " leaves your patron clutching their wounds";
            case "rush":
                inMultiplier = 1.5;
                outMultiplier = 1.5;
                return "\nThe sudden rushdown from " + name + " leaves your patron staggered, but so is the " + name;
            default:
                return null;
        }
    }

    public int combat(String action) {
        //return (int) ((skills.get(action) * power) * randomizer(0.5, 1.5) * outMultiplier);
        return (int) ((randomizer(0.5, 1.5) * power) * randomizer(0.5, 1.5) * outMultiplier);
    }
}
