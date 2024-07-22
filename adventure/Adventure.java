import java.util.*;

/*
 * Hashmap for the events of what player says and return according to what they say another method that translates various synonyms into an understandable key for the hashmap 
 * (ex knob open to door) (make a key to return a value, put in key to get value, method to allow multiple responses to put into one key to get a response should player have variations in their resposne)
 * From arraylist add random things into the rooms hashmap so the room only has access to a specific things (make a section to have like max 5 object, 5 items for example) 
 * use Boolean to see if an object is a room or not so it won’t say things that aren’t even in the room. 
 * Example: translator translate word and checks if room has the item, give to hashmap that figures out what to do with said object, 
 * hashmap returns a method that has description from a descriptions class that classifies what item it is and returns. 
 * Like for a mundane object it may say “this is a peculiar wooden item, nothing is special about it” or “your character could care less looking at this, but exasperatedly tells you anyways that it is made of metal and very much physical”
 * Main class should have the hashmap[string(ex IDs), string]
 * The rooms could be theme rooms where it’s random what theme it is but there is a method to describe the room and then add on important objects that are in the room that are mentioned later 
 * which are added randomly from the theme hashmap or something to see what can be done in the room. Different themes different objects. Light level affects theme
 * Class rooms has private room array of what rooms it has access to, private string array of the IDs of all the items, and private string array of the enemies
 * If player makes hard to understand things or things that don’t make sense, make it funny or make fun of them, like for “walk” and no instruction just say 
 * “you meandered around until you fell into a pit and died”
 * If player wants to do an action but better (ex look harder) begin to tell them over exaggerated details or let them see things they shouldn’t or have the character 
 * realize that they see the player for one second from the screen before returning back to normal 
 * Sentence variations method, each object has a method that returns a list of descriptions like adverbs to give all actions more personality and 
 * unique (the knife said sharply, the knife nodded precisely, etc) (look back at mad line coding for an idea)
 * The player’s character knows that they are being controlled by the player, they have a Trust Bar that if the player makes good/poor decisions there will be more flavor text like 
 * “The Guard stares at you wearily with an untrusting gaze. You have failed them many times. Yet they await to heed your next judgment.” 
 * “The Marksman’s hands quivered from the insanity of your statement, they wished to tune you out but your words pierce through their soul. They shake their head in stress but proceed to carry out your wish” 
 * “The Pirate nodded their head with confidence, they had faith in your words and trusted you. Surely you wouldn’t lead them wrong now in this dire time”
 * No stats shown to player, just descriptions of how they are doing on the outside, when asked they will respond how they feel depending on trust (more trust more honest, less trust may not answer or give a unhelpful answer like “you have eyes, look yourself”)
 * Trust 10 levels: 1,2-3,4-7,8-9,10
*/
public class Adventure {

  private static HashMap<String, Boolean> valid = new HashMap<>();
  private static HashMap<String, String> simple = new HashMap<>();
  private static HashMap<String, String> activate = new HashMap<>();
  private static Room room;
  private static Character character;
  private Scanner input = new Scanner(System.in);

  public Adventure(Room room, Character character) {
    Adventure.room = room;
    Adventure.character = character;
    options();
    possibilities();
    activatePrompts();
  }

  public static void options() {
    valid.put("yes", true);
    valid.put("no", false);
    valid.put(null, false);
  }

  public static void possibilities() {
    simple.put("yes", "yes");
    simple.put("confirm", "yes");
    simple.put("affirmative", "yes");
    simple.put("okay", "yes");
    simple.put("ok", "yes");
    simple.put("sure", "yes");
    simple.put("ye", "yes");
    simple.put("ya", "yes");
    simple.put("fine", "yes");
    simple.put("mhm", "yes");
    simple.put("got it", "yes");
    simple.put("gotcha", "yes");
    simple.put("yup", "yes");
    simple.put("yep", "yes");
    simple.put("facts", "yes");
    simple.put("no doubt", "yes");
    simple.put("indeedly doodly", "yes");
    simple.put("absolutely", "yes");
    simple.put("definitely", "yes");
    simple.put("y", "yes");
    simple.put("yippe", "yes");
    simple.put("ready", "yes");
    simple.put("agree", "yes");

    simple.put("no", "no");
    simple.put("naw", "no");
    simple.put("nope", "no");
    simple.put("nuh uh", "no");
    simple.put("nah", "no");
    simple.put("l", "no");
    simple.put("naur", "no");
    simple.put("bruh whut", "no");
    simple.put("erm, what the sigma?", "no");
    simple.put("n", "no");
    simple.put("quit", "no");
    simple.put("disagree", "no");
  }

  public static void activatePrompts() {
    activate.put("move", "move");
    activate.put("travel", "move");
    activate.put("walk", "move");
    activate.put("meander", "move");
    activate.put("run", "move");
    activate.put("go", "move");
    activate.put("advance", "move");
    activate.put("progress", "move");
    activate.put("rush", "move");
    activate.put("sprint", "move");
    activate.put("traverse", "move");
    activate.put("relocate", "move");

    activate.put("attack", "attack");
    activate.put("hit", "attack");
    activate.put("strike", "attack");
    activate.put("kill", "attack");
    activate.put("fight", "attack");
    activate.put("slice", "attack");
    activate.put("smite", "attack");
    activate.put("assault", "attack");
    activate.put("injure", "attack");
    activate.put("damage", "attack");
    activate.put("pummel", "attack");
    activate.put("break", "attack");
    activate.put("destroy", "attack");
    activate.put("hurt", "attack");
    activate.put("bonk", "attack");

    activate.put("attain", "attain");
    activate.put("pick up", "attain");
    activate.put("pocket", "attain");
    activate.put("grab", "attain");
    activate.put("snatch", "attain");
    activate.put("hold", "attain");
    activate.put("grasp", "attain");
    activate.put("pick", "attain");
    activate.put("clasp", "attain");
    activate.put("obtain", "attain");
    activate.put("acquire", "attain");
    activate.put("get", "attain");
    activate.put("gather", "attain");
    activate.put("collect", "attain");
    activate.put("nab", "attain");
    activate.put("bag", "attain");

    activate.put("drop", "drop");
    activate.put("let go", "drop");
    activate.put("fall", "drop");
    activate.put("slip out", "drop");
    activate.put("remove", "drop");
    activate.put("put down", "drop");
    activate.put("relieve", "drop");
    activate.put("alleviate", "drop");
    activate.put("disseminate", "drop");
    activate.put("decertify", "drop");
    activate.put("toss", "drop");

    activate.put("inventory", "inventory");
    activate.put("items", "inventory");
    activate.put("bag", "inventory");
    activate.put("backpack", "inventory");
    activate.put("holding", "inventory");
    activate.put("pocket", "inventory");
    activate.put("possessions", "inventory");
    activate.put("belongings", "inventory");
    activate.put("held", "inventory");

    activate.put("ask", "ask");
    activate.put("talk", "ask");
    activate.put("say", "ask");
    activate.put("wonder", "ask");
    activate.put("question", "ask");
    activate.put("speak", "ask");
    activate.put("converse", "ask");
    activate.put("dialogue", "ask");

    activate.put("investigate", "investigate");
    activate.put("look", "investigate");
    activate.put("seek", "investigate");
    activate.put("peer", "investigate");
    activate.put("eye", "investigate");
    activate.put("explore", "investigate");
    activate.put("peep", "investigate");
    activate.put("look around", "investigate");
    activate.put("see", "investigate");
    activate.put("keek", "investigate");
    activate.put("observe", "investigate");
    activate.put("find", "investigate");
    activate.put("search", "investigate");
    activate.put("locate", "investigate");
    activate.put("check", "investigate");

    // death test
    activate.put("suicide", "die");
    activate.put("give up", "die");
    activate.put("quit", "die");
    activate.put("die", "die");
  }

  // takes what they have said and returns the method for their desired course of
  // action and proceeds the game under the pretense of what they want to do
  public void activationResponse(String command) {
    switch (command) {
      case "move":
        if (room.getLayer() <= 7) {
          System.out.println("\n\nWhich colored door would you like to tell " + character.name() + " to enter?\n");
          room.directionsPrint();
          command = input.nextLine().toLowerCase().strip();
          if (room.getPlaces(command) == null && !(command.equals("back"))) {
            System.out.println("\n" + character.name()
                + " has ignored you \nPlease rephrase it. \nRemember this program works in a process that follows a check for what do you want to do, \nand then the action related to it after\nExample: 'move' 'red'\n");
            break;
          }
          spacer();
          if (!(room.enemyList().equals("!"))) {
            System.out.println("\n" + room.getEnemy().name() + " attacked for " + room.getEnemy().combat(command)
                + " damage as " + character.name() + " ran from the room without fighting");
            character.damageTaken(room.getEnemy().autoCast() * 5);
            character.htpUpdate(0, -1, 0);
          }
          room = room.traverse(command);
          if (character.health() <= 0) {
            System.out.println("\n");
            break;
          }
          if (room.getLayer() != 8) {
            System.out.println(room.getDesc());
            // debugging print
            // System.out.println(room.getColorPos(command));
            // room.placeDescGet(room.getColorPos(command));

          }
          character.multiplierReset();
        } else {
          System.out.println(
              "Your patron has successfully made it out of the world, such glorious times, the patron ran off into the distance without turning back\nThey are happier now");
        }
        break;
      case "attack":
        if (room.enemyList().equals("!")) {
          System.out.println("There are no enemies here");
          break;
        }
        System.out.println("\n\nThe enemies here are:");
        System.out.println(room.enemyString());
        System.out
            .println("\n\nWhich skill would you want to tell " + character.name() + " to perform?\n");
        character.abilities();
        System.out.println(character.quickSkills());
        command = input.nextLine().toLowerCase().strip();
        if (command == null || character.attributes(command) == null) {
          System.out.println("\n" + character.name()
              + " has ignored you \nPlease rephrase it. \nRemember this program works in a process that follows a check for what do you want to do, \nand then the action related to it after\nExample: 'attack' '(character skill) (no spaces)'\n");
          break;
        }
        spacer();
        System.out.println(character.attributes(command));
        System.out.println("\n" + character.name() + " attacked for " + character.combat(command) + " damage");
        room.getEnemy().damageTaken(character.combat(command));
        System.out
            .println("\n" + room.getEnemy().name() + " attacked for " + room.getEnemy().combat(command) + " damage");
        character.damageTaken(room.getEnemy().autoCast());
        room.removeEnemy();
        if (room.enemyList().equals("!")) {
          System.out.println("\nCreature Slaughtered!");
        }
        break;
      case "attain":
        System.out.println("\n\nWhat would you like tell " + character.name() + " to pick up?\n");
        command = input.nextLine().toLowerCase().strip();
        if (command == null || Item.allItemNames().indexOf(command) < 0 || room.itemString().indexOf(command) < 0) {
          System.out.println("\n" + character.name()
              + " has ignored you \nPlease rephrase it. \nRemember this program works in a process that follows a check for what do you want to do, \nand then the action related to it after\nExample: 'attain' '(name of item that you found after investigating)'\n");
          break;
        }
        spacer();
        character.addItem(Item.initiation(command));
        // potential repeated effects as you keep picking up and dropping it
        Item item = room.removeItemRoom(command);
        character.htpUpdate(item.health(), item.trust(), item.power());
        break;
      case "drop":
      if (character.itemString().length() <= 1) {
        System.out.println("\nThere is nothing in your patron's inventory at the moment that they are willing to drop\n");
        break;
      }
        System.out.println("\n\nWhat would you like tell " + character.name() + " to drop?\n");
        command = input.nextLine().toLowerCase().strip();
        if (command == null || Item.allItemNames().indexOf(command) < 0
            || character.itemString().indexOf(command) < 0) {
          System.out.println("\n" + character.name()
              + " has ignored you \nPlease rephrase it. \nRemember this program works in a process that follows a check for what do you want to do, \nand then the action related to it after\nExample: 'drop' '(name of item in your inventory)'\n");
          break;
        }
        spacer();
        character.removeItem(command);
        room.addItemRoom(Item.initiation(command));
        break;
      case "inventory":
        System.out.println("\n\nHere is what is in " + character.name() + "'s inventory\n");
        System.out.println(character.itemString());
        break;
      case "ask":
        System.out.println("\n\nWhat would you like to ask " + character.name() + "?\n");
        System.out.println("health, trust");
        command = input.nextLine().toLowerCase().strip();
        if (command == null || character.conversation(command) == null) {
          System.out.println("\n" + character.name()
              + " has ignored you \nPlease rephrase it. \nRemember this program works in a process that follows a check for what do you want to do, \nand then the action related to it after\nExample: 'talk' 'trust'\n");
          break;
        }
        spacer();
        System.out.println(character.conversation(command));
        break;
      case "investigate":
        System.out.println("\n\nThis is what " + character.name() + " sees\n");
        System.out.println(room.itemString());
        System.out.println("You may tell " + character.name() + " to pick it up\n");
        break;
      case "die":
        character.instantKill();
        break;
      case null:
        System.out.println("\n" + character.name()
            + " has ignored you \nPlease rephrase it. \nRemember this program works in a process that follows a check for what do you want to do, \nand then the action related to it after\n");
        break;
      default:
        System.out.println("\n" + character.name() +
            "\n" + character.name()
            + " has ignored you \nPlease rephrase it. \nRemember this program works in a process that follows a check for what do you want to do, \nand then the action related to it after\n");
        break;
    }

  }

  public String formatSpacer(String name) {
    String header = name;
    int total = 53 - name.length();
    for (int i = 0; i < total; i++) {
      header += "-";
    }
    return header;
  }

  public void spacer() {
    System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n"
        + formatSpacer(character.name()) + "\n" +
        "-----------------------------------------------------");
    // System.out.println(room.getDesc());
  }

  public static boolean getValidValue(String key) {
    return valid.get(key);
  }

  public static String translator(String word) {
    return simple.get(word);
  }

  public String actions() {
    return "move, attack, attain, drop, inventory, talk, investigate";
  }

  public static void main(String[] args) {
    Adventure activate = new Adventure(new Room(0), new Character("", 0, 0, 0));
    activate.play();
  }

  public void play() {
    possibilities();
    options();
    Scanner input = new Scanner(System.in);
    String command = "";
    System.out.println(
        "\n\n\n\nSAFE START ROOTKIT 1.1.7-----------------------------\n-----------------------------------------------------\nPossible Endings: 4");
    System.out.print(
        "\nYou have entered the dungeon of empty dreams. \nNot in the concept that the dreams were destroyed or unrealistic, \nbut as in within this realm they cannot exist while being stuck here. \nFight the nightmares that plague the mind of the denizens trapped here, help these creatures escape. \nEarn their trust that you aren't a parasite in their mind. \n\nAre you ready to venture forth to the 8th floor of freedom?\n\n> ");
    command = input.nextLine().toLowerCase();
    if (valid.get(simple.get(command))) {
      spacer();
      System.out.println(
          "\nThe people you can attempt to recruit to your cause can only be seen by what they stand for. \nOnly one may be deemed worthy of your kindness:\n"
              + character.quickCharacterChoice());
      command = input.nextLine().toLowerCase();
      spacer();
      character = character.initiation(command);
      while (character.health() > 0 && character.trust() > -1 && room.getLayer() != 8) {
        System.out.println("Current Layer: " + room.getLayer());
        if (!(room.enemyList().equals("!"))) {
          System.out.println("\n\nCAREFUL! THERE ARE ENEMIES IN HERE STILL:");
          System.out.println(room.enemyString());
          System.out.println(
              "If you run then they will do extra damage against you and your patron will lose faith in your show of cowardice");
        }
        System.out.println("\nPossible actions\n" + actions() + " (SYNONYMS WILL WORK)");
        command = input.nextLine().toLowerCase();
        spacer();
        // System.out.println(room.getDesc());
        activationResponse(activate.get(command));
      }
      if (!(character.health() > 0)) {
        System.out.println("Shell shocked, trembeling, wounded, " + character.name()
            + " crumples to the floor no longer listening to your words\nYou have wrought havoc upon their future, they are left with whatever tools in their hands, but it's simply not enough to escape now \n"
            + character.name()
            + " is alone in this world\nThey accept their fate, and you feel that their presence fade away, disconnecting with you\n\nENDING 2: Broken Dreams, Broken Soul, Broken Spirit");
      } else if (!(character.trust() > -1)) {
        System.out.println("That was the last push that shoved them off the edge, an eye of fear and utter hatred from "
            + character.name()
            + " looks down upon you\nYou have betrayed what they have stood for, your empty promises, your false words, your corrupt desire can no longer be tolerated\nThey are shaking, they are breaking slowly from this insanity that you have wrought upon them, but they can't take back the trust and control they have handed to you \nAfterall, no one can resist such an all controlling god\nDespite "
            + character.name()
            + "'s lack of guidance, they scornfully turn their head to you\nDisappointed, tears filling their eyes, you suddenly feel their presence fade away, disconnecting with you\n\nENDING 3: Malevolent God's Toy");
      } else if (room.getLayer() == 8) {
        System.out.println(
            "Such glorious times! The lights of the outside world has opened up and took your patron with open arms\nA beaming smile broke upon "
                + character.name()
                + "'s face as they ran off into the distance, never looking back to say good bye\nThey will live a much better life now, thank you for helping them find a way out of this wretched land\n\nENDING 4: The Light At The End Of The Tunnel");
      }
      input.close();
    } else if (simple.get(command) == null) {
      spacer();
      System.out.println(
          "\nSeems like your response isn't valid \nPlease rephrase it. \nTry something simple, such as 'yes' or 'no'");
      play();
    } else {
      System.out.println(
          "\n\nYour beliefs casted aside as you walked out the dungeon leaving the souls forever locked within. \nPerhaps simply being alive is an award in itself, \nit's blatently obvious strangers aren't worthy of your glorious prescense. \nNo, they don't deserve to be in the spotlight with you. \nThey don't deserve anything\n\nENDING 1: The Sensible One");

      input.close();
    }
    input.close();
  }
}