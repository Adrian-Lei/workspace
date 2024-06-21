import java.util.*;

public class Item {
    // interactible items
    private String name;
    // private String description;
    private int health;
    private int trust;
    private int power;

    public Item(String name, int health, int trust, int power) {
        this.name = name;
        this.health = health;
        this.trust = trust;
        this.power = power;
        // itemFill();
    }

    // Gets the string version of the item, use this for the player to translate
    // into the item
    public String getName() {
        return name;
    }

    // public String description() {
    // return description;
    // }

    public int health() {
        return health;
    }

    public int trust() {
        return trust;
    }

    public int power() {
        return power;
    }

    // this should be given to the room class to choose randomly for the item
    // initiation to add to that room as a pick upable item
    public static ArrayList<String> allItemNames() {
        ArrayList<String> allNames = new ArrayList<>();
        allNames.add("rotten food");
        allNames.add("burnt flowers");
        allNames.add("sword");
        allNames.add("shield");
        allNames.add("photo");
        allNames.add("ripped contract");
        allNames.add("pamphlet");
        allNames.add("pendant");
        allNames.add("propaganda");
        allNames.add("healing potion");
        allNames.add("alcohol");
        allNames.add("drugs");
        allNames.add("pure hate");
        return allNames;
    }

    // give back the item according to the name
    public static Item initiation(String name) {
        switch (name) {
            case "rotten food":
                // this.description = "There is a pile of rotten food within your reach";
                return new Item("rotten food", -45, -5, -10);
            case "burnt flowers":
                // this.description = "There is a burnt flower";
                return new Item("burnt flowers", 20, 1, 0);
            case "sword":
                // this.description = "Here has a sword";
                return new Item("sword", 10, 1, 10);
            case "shield":
                // this.description = "Here has a shield";
                return new Item("shield", 40, 1, 0);
            case "photo":
                // this.description = "One photo was laying on the ground";
                return new Item("photo", 10, 3, 0);
            case "ripped contract":
                // this.description = "One ripped contract was laying on the ground";
                return new Item("ripped contract", 10, -3, 20);
            case "pamphlet":
                // this.description = "Inside the room there was a pamplet";
                return new Item("pamphlet", 5, 1, 0);
            case "pendant":
                // this.description = "Inside the room there was a pendant";
                return new Item("pendant", 15, 1, 10);
            case "propaganda":
                // this.description = "Propaganda covered the walls";
                return new Item("propaganda", 35, -2, 15);
            case "healing potion":
                return new Item("healing potion", 50, 1, 0);
            case "alcohol":
                return new Item("alcohol", -10, 0, 10);
            case "drugs":
                return new Item("drugs", -20, -2, 20);
            case "pure hate":
                return new Item("pure hate", 30, -5, 40);
            default:
                return null;
        }
    }
    /*
     * public void itemFill() {
     * itemNames.put("rotten food", new Item("rotten food", -45, -5, -10));
     * itemNames.put("burnt flowers", new Item("burnt flowers", 0, 1, 0));
     * itemNames.put("sword", new Item("sword", 0, 0, 10));
     * itemNames.put("shield", new Item("shield", 20, 0, 0));
     * itemNames.put("photo", new Item("photo", 0, 3, 0));
     * itemNames.put("ripped contract", new Item("ripped contract", 0, 1, 0));
     * itemNames.put("pamphlet", new Item("pamphlet", 5, 1, 0));
     * itemNames.put("pendant", new Item("pendant", 15, 1, 15));
     * itemNames.put("propaganda", new Item("propaganda", 35, -2, 15));
     * }
     */

}
