import java.util.*;

//there should be a seperate class for items in the room, and define which one is interactible and placeable into the inventory through booleans. inventory should be hashmap
public class Room {
        // this is to return discriptions relevant to only the room, this will be coming
        // from the player class
        private int layer;
        private Room parent;
        private ArrayList<Item> itemsInRoom = new ArrayList<>();
        // make a method that is called in the constructer that will fill the room with
        // enemies, or no enemies (chance!), make a randomizer using the layer number to
        // multiply room difficulty
        // layer/2 + 1 with room difficulty randomized should make each place resonably
        // more difficult
        private Enemy enemy;
        // the children
        private ArrayList<Room> childs = new ArrayList<Room>();
        private ArrayList<String> choices = new ArrayList<>();
        // all the colors that are avalible
        private ArrayList<String> allSides = new ArrayList<String>();
        // the colors that will hold the valid colors
        private ArrayList<String> colors = new ArrayList<String>();
        private ArrayList<String> roomKitSmall = new ArrayList<String>();
        private ArrayList<String> roomKitMedium = new ArrayList<String>();
        private ArrayList<String> roomKitLarge = new ArrayList<String>();
        private ArrayList<String> roomKitDetail = new ArrayList<String>();
        private ArrayList<String> childDescriptions = new ArrayList<String>();
        private HashMap<String, String> environment = new HashMap<>();
        private String selfDesc = "not created yet";
        // all the descriptions in the room
        private ArrayList<String> description = new ArrayList<>();

        private ArrayList<String> roomObjs = new ArrayList<>();

        private ArrayList<String> childDescSentences = new ArrayList<>();
        // private HashMap<String,String or item itself and description? or what can be
        // done with it?> roomItems = new HashMap<>();
        // this should be from the items class
        // private HashMap<String,String> roomItems = new HashMap<>();
        private HashMap<String, Room> places = new HashMap<>();
        private String childDescTemp;

        public Room(int layer) {
                this.layer = layer;
                itemRandomizer();
                // directions
                refill();
                // background text
                roomKit();
                environmentText();
                // childEnviroment();
                // descriptionPrinter();
                // children rooms
                addEnemy();
                children();
                // itemRandomizer();
                // childEnviroment();
                fillRoom();
                setDesc();
                directions();
                // making and saving discriptions
                // placeDescSet();
                // childEnviroment();
        }

        public Room(int layer, Room parent) {
                this.layer = layer;
                this.parent = parent;
                itemRandomizer();
                // directions
                refill();
                // background text
                roomKit();
                environmentText();
                // childEnviroment();
                // descriptionPrinter();
                // children rooms
                addEnemy();
                children();
                // itemRandomizer();
                // childEnviroment();
                fillRoom();
                setDesc();
                directions();
                // making and saving discriptions
                // placeDescSet();
                // childEnviroment();
        }

        public void environmentText() {
                environment.put("cups",
                                "There is a scattered bunch of dirty cups, they lay restless in their stationed places");
                environment.put("skull",
                                "There was skulls of soldiers in the past stare in search for a desire to fight on, their rotting faces twisted in contortion from pain to broken morale");
                environment.put("driftwood", "There is scattered pieces of driftwood here, they lay scattered");
                environment.put("crystal",
                                "A glint in the distance catches the eye of your patron, there is crystals deeply embeded in the walls");
                environment.put("torch",
                                "A burnt out stick laid here, it appears someone passed by here recently, taken by the darkness once their torch burnt out");
                environment.put("candle", "The candle within the room has burnt out, leaving a puddle of wax");
                environment.put("toys",
                                "A child has been in these parts before, unharmed, they left a gift for the dungeon's creatures, a small plushie of a cartoonish character lays here");
                environment.put("clothes",
                                "Unfinished clothes made from corpses and flesh lay strewn about, they appeared have to have been made with care");
                environment.put("paper",
                                "There are various parchments of paper scattered about, they read of the eldritch beings that comes with the land");
                environment.put("bullet",
                                "The floor has scattered bullet casings, clearly a fight has broken out here, however it cannot be determined who won");
                environment.put("needle",
                                "There are needles scattered about, \nthe contained liquid already fully employed to ease the pain of being in such a wretched place");
                environment.put("glove",
                                "A teared glove had a name embroidered upon it, it is not of use due to how shredded it was, but it seems to have been left for a child");
                environment.put("urns",
                                "The floor holds up various urns, each marked with the names of the lost souls, ensure that your patron does not join them");
                environment.put("puzzle",
                                "An excessively detailed puzzle was placed here to lock one of the doors, however it appears someone grew exasperated and ripped open the door");
                environment.put("hang",
                                "A rotting body hung from a noose, a few inches off the ground. An execution has taken place here");
                environment.put("guillotine",
                                "A head laid in a basket infront of the guillotine, the body however is missing, only dried blood surrounded the area in a star like fashion");
                environment.put("windowtaint",
                                "The wall has a singular undamaged cathedral glass that revealed a world so colorful and vivid that your patron couldn't even look at it, \nit seared their brain, they couldn't even approach it");
                environment.put("paint",
                                "Small drawings were painted alongside most of the blood, oddly it was painted purple which was quite a rare and expensive dye to use, the drawings seemed to be from a better time");
                environment.put("garden", "There was a miniature garden that still grew within the room");
                environment.put("spindle",
                                "A toy spindle lays on the floor in a small caved in part of the floor, it appears to be an arena made for fun and games");
                environment.put("crown", "A shattered crown lays on the floor, it is splintered and fragmented");
                environment.put("chalice",
                                "One chalice sat at each ends of a small table in the room, both empty but one side had white powder in the cup and a dead body");
                environment.put("stardust",
                                "The powder of the stars scattered across the air floating lazily, giving a twinkling haze to the room");
                environment.put("beartrap",
                                "There is a sprung beartrap had a rat split in half laying inside of it\nIts innards are spread out and tugged across the floor, blood droplets were scattered");
                environment.put("sprout",
                                "In the cracks of the wall, small sprouts and fungus were trying to break into the safety of the room\nInvading and rotting the bloody contraptions of the dungeon");
                environment.put("disposablebaton",
                                "A disposable shock baton was used and thrown to the floor, a small charred brain with appendages lays charred");
                environment.put("charredfood",
                                "Food lays inside of a box, they were charred thoroughly and a glow of red was emanating from it");

                environment.put("chair", "In here has a peculiar wooden chair, nothing is special about it");
                environment.put("table",
                                "The ornaments of the table within the room had an invoking sense of boredom, heed no mind to it");
                environment.put("cauldron",
                                "An offending iron chassis of a cauldron laid in scraps, clearly the last entity to cook was not cooking when they cooked");
                environment.put("barrel",
                                "A barrel was in the area, once used to ferment potatoes according to the scent of it");
                environment.put("crate",
                                "A wooden box decorated with an insignia of a proud lion intidimidating a shield, it has already been looted");
                environment.put("chains",
                                "Some chains wrapped a skeleton, entangling it, leaving it hanging from the ceiling");
                environment.put("campfire",
                                "A spent campfire lays here, there is a pile of ash as a reminder of the wood");
                environment.put("corpse",
                                "Bodies of various creatures lay thoroughly processed, only the bones of few remain");
                environment.put("weapon",
                                "The tool of destruction lays shattered across the floor, not a single piece of the weapon may be used");
                environment.put("sickle",
                                "A rusted sickle engraved with a name was jammed into the wall, it cannot be taken out without destroying it, marks of a bloody hand was left on the handle");
                environment.put("eviltable",
                                "Here sat a metal table with designs depicting a battle ground, this place was once used to plan the battlefield");
                environment.put("alter",
                                "A sacrificial alter was built in the side of the room, surrounding it was dried blood and empty plates");
                environment.put("trapdoor",
                                "There was a half built trapdoor sitting on a table, clearly the people who were attempting to build this dungeon were not finished");
                environment.put("prison",
                                "Some cell doors were broken open, the boxes were ravaged and it blatently obvious a fight with the guards had occured. \nConsidering that most of the bodies were the guards, it is safe to assume that the criminals won are about\nSomehow there is still an effort to make order in this land");
                environment.put("tapestry",
                                "There was a tapestry depicting three groups of people, one that worked to replenish and revive the land, \none that built a fort to protect what is left and filling it with evil as a guard, and the last the killed to stay alive");
                environment.put("waterwheel",
                                "There was a miniature waterwheel that spun lazily in the corner of the room, it was not connected to anything however");
                environment.put("modelplanet",
                                "A model of the planet stood erect on the side of the room, it only had one large form of land and the rest was water.\nThe land had a large crater in it");
                environment.put("potionbrew",
                                "There was a table for brewing potions, filled with alchemy ingredients. \nHowever, it seems like someone rolled them into a piece of paper and lit it on fire");
                environment.put("bloodyplan",
                                "A bounty paper was on a board with various red string strung across an conspiracy board\nA bloody handle of a broken blade protruded from the bounty paper");
                environment.put("shell",
                                "The shell of some large creature was left admist the room, the monsters are growing and living in here");
                environment.put("lightsphere",
                                "One sphere of light floated in the air, the pureness of light provides quite a high candela.\nThe shadows are looming");
                environment.put("dustbunny",
                                "A small bunny made from dust hopped around the room\nThis dust bunny upon seeing your patron, fell apart and scattered into a small pile of dust");
                environment.put("sapling",
                                "There was a sapling growing through the cracks of the floor, this kind of green life has not been seen for a long time");
                environment.put("floatingwater",
                                "Small collectives of spherical water has taken shape above the floor. \nThey floated around in unison like a colony, searching for a place to rest");

                environment.put("bookshelf",
                                "Within this space, it has been graced by a bookshelf! It has a singular damaged obscene book about handholding, how vulgar");
                environment.put("monument",
                                "A giant monument depicting a prophet clutching a wounded soldier on their knees draws attention to those within the room");
                environment.put("space",
                                "Here, there is a large empty clearing, a decent space for those who prefer rigorous activities such as walking");
                environment.put("bones",
                                "A mountainous pile of bones lays on the floor, an execution has been performed in the past");
                environment.put("flesh",
                                "The walls has live growing flesh, spreading and suffocating the room. It has slowly reverberating sounds that felt uncomfortable, it is not aggressive however");
                environment.put("ceiling",
                                "It appears the ceiling has partially caved in, looking up you see that there are hundreds upon hundreds of floors above you, the further the rooms, the less intricate they became");
                environment.put("schematics",
                                "The walls are etched with various designs illustrating monsterous weaponry. \nHowever, for each weapon that was displayed there appears to have had previous souls etching in messages pleading for them to never be created");
                environment.put("maiden",
                                "An iron maiden rests in the corner of the room, staring back at you, the doors are closed and in front has a pool of dried blood\nIt has feasted already");
                environment.put("pew",
                                "A religious pew stretched across the entire room, however there was no stand for a priest to speak on");
                environment.put("chandelier",
                                "An expensive chandelier hung from the ceiling, it was made with careful hands that appreciated the arts, it was made with love");
                environment.put("firepit",
                                "Rocks surrounded a firepit, it wasn't deep as it was filled to the brim with ash");
                environment.put("tree",
                                "A tree grew through the floor, it was one of the few rare times that your patron has ever seen the nature");
                environment.put("flower",
                                "Two newly deceased bodies held hands while sitting to the side of the room, from their mouth spilled fresh blood and flowers, unrequited love");
                environment.put("polestage", "A pole extrudes from a stage in the middle of the room to the ceiling");
                environment.put("astrology",
                                "An astrology diagram was posted on the wall with intricate writing filling the entire wall");
                environment.put("astronomy",
                                "An astronomy study was conducted here, various prestine research papers was left neatly laid on a table");
                environment.put("spacediagram",
                                "A picture of the space was put on the wall, the stars were endless, the cosmos stretched on forever\nThe home land is insignificant on this scale, and so were the people living on it");
                environment.put("gladiatorspace",
                                "This room was a dedicated colosseum filled with various objects as obstacles, dried blood and rusted armor on skeletons still rested\nFor a space so large and once filled with bodies in pain and joy, it felt empty, lonely, liminal");
                environment.put("tent",
                                "There was a tent pitched here, clearly someone had lived here recently, but never could pack up the tent");
                environment.put("sand",
                                "There was a large pit of sand in the middle of the room. The sand shifted ever so slightly, it is restless");
                environment.put("templegate",
                                "A wooden arch provided to once lead into a temple now encompass doors of the dungeon");
                environment.put("flowergate",
                                "Admist all the objects in the room, there was an ethereal pristine marbel gate that had flowers wrapping around it, this is of the highest quality");
                environment.put("ratkin",
                                "Here, propaganda paper to enlist to the ratkin was found on the floor, it tells creatures to give a helping paw in defending their home");
                environment.put("armory",
                                "There is a ramshackled armory that was left on the side, any gear worth taking is no longer there");

                environment.put("blood", "The walls and floors are stained with blood");
                environment.put("strawberries", "There is a faint scent of strawberries");
                environment.put("hair", "The floor has tuffs of hair, probably was shedded by some creature");
                environment.put("puddle",
                                "A dark puddle sat in the center of the room, in the light it looked like a endless pit");
                environment.put("cobwebs",
                                "The spiders have taken a liking to this room, it has various scattered cobwebs that lay about");
                environment.put("moss",
                                "A steady growth of moss cover these parts, they seem to have been tended well");
                environment.put("brick",
                                "Parts of the unfinished wall lay unconstructed, peering into them shows a bottomless void to the outside, the cracks aren't large enough to fit through");
                environment.put("provisions",
                                "There was a party here before, bottles of alcohol preposterously thrown around haphazardly, foods lay rotten and smeared across the floor");
                environment.put("glass",
                                "The floor is covered in shattered glass, a creature has rampaged in here recently with a severe distaste in fine china");
                environment.put("burn", "There are various burn marks across all the objects of room");
                environment.put("holy",
                                "A good soul has been here before, there was a small feeling of purity in here");
                environment.put("drip", "Sounds of water dripping was audible");
                environment.put("evil",
                                "A foreboding sense that an evil creature was staring at your patron leaves them taking a shaky grip on their weapon, scared");
                environment.put("scream", "Distance screams of the dammed kept your patron antsy");
                environment.put("shadow",
                                "The shadows on the walls moved as if there was another world that was disconnected from this one");
                environment.put("rot", "There was a great smell of rot in here");
                environment.put("steelwall", "The walls was partially reinforced by steel");
                environment.put("stonewall", "The walls had stone infused into it");
                environment.put("cobblestonewall", "Feeling the wall, it was textured with specks of cobblestone");
                environment.put("crudeironwall",
                                "An exposed part of the wall revealed that a blacksmith had took care to put crude iron in the foundation of the walls");
                environment.put("etchedstar", "Stars were etched on the walls, it gave off a light red glow");
                environment.put("wailbaby",
                                "When you entered the room, there was a distant wail of a child, but then it became silent");
                environment.put("physicalshadow",
                                "When you entered the room, you bumped into a void of darkness, a walking physical shadow\nIt faded through the wall as it ran away");
                environment.put("dust",
                                "There was a substantial increase of dust in the air, clouding the choking air and obscuring the view");
                environment.put("ratpoison", "Various rat poisons was littering th floor");
                environment.put("clawmarks",
                                "The walls had large claws dragged against them, a mark of this size must have been made while walking to have scraped an entire section of the wall");
                environment.put("insaneetch",
                                "Peculiar hieroglyphics was etched across the wall, they were inane but warned of some great danger");
                environment.put("invertedwater",
                                "A puddle of inverted water had moved across the room, drying everything it touches until it shrivels up and disintegrates");

        }

        public void roomKit() {
                ArrayList<String> small = new ArrayList<String>();
                ArrayList<String> medium = new ArrayList<String>();
                ArrayList<String> large = new ArrayList<String>();
                ArrayList<String> detail = new ArrayList<String>();
                small.add("cups");
                small.add("skull");
                small.add("driftwood");
                small.add("crystal");
                small.add("torch");
                small.add("candle");
                small.add("toys");
                small.add("clothes");
                small.add("paper");
                small.add("bullet");
                small.add("needle");
                small.add("glove");
                small.add("urns");
                small.add("puzzle");
                small.add("hang");
                small.add("guillotine");
                small.add("windowtaint");
                small.add("paint");
                small.add("garden");
                small.add("spindle");
                small.add("crown");
                small.add("chalice");
                small.add("stardust");
                small.add("beartrap");
                small.add("sprout");
                small.add("disposablebaton");
                small.add("charredfood");

                medium.add("chair");
                medium.add("table");
                medium.add("cauldron");
                medium.add("barrel");
                medium.add("crate");
                medium.add("chains");
                medium.add("campfire");
                medium.add("corpse");
                medium.add("weapon");
                medium.add("sickle");
                medium.add("eviltable");
                medium.add("alter");
                medium.add("trapdoor");
                medium.add("prison");
                medium.add("tapestry");
                medium.add("waterwheel");
                medium.add("modelplanet");
                medium.add("potionbrew");
                medium.add("bloodyplan");
                medium.add("shell");
                medium.add("lightsphere");
                medium.add("dustbunny");
                medium.add("sapling");
                medium.add("floatingwater");

                large.add("bookshelf");
                large.add("monument");
                large.add("space");
                large.add("bones");
                large.add("flesh");
                large.add("ceiling");
                large.add("schematics");
                large.add("maiden");
                large.add("pew");
                large.add("chandelier");
                large.add("firepit");
                large.add("tree");
                large.add("flower");
                large.add("polestage");
                large.add("astrology");
                large.add("astronomy");
                large.add("spacediagram");
                large.add("gladiatorspace");
                large.add("tent");
                large.add("sand");
                large.add("templegate");
                large.add("flowergate");
                large.add("ratkin");
                large.add("armory");

                detail.add("blood");
                detail.add("strawberries");
                detail.add("hair");
                detail.add("puddle");
                detail.add("cobwebs");
                detail.add("moss");
                detail.add("brick");
                detail.add("provisions");
                detail.add("glass");
                detail.add("burn");
                detail.add("holy");
                detail.add("drip");
                detail.add("evil");
                detail.add("scream");
                detail.add("shadow");
                detail.add("rot");
                detail.add("steelwall");
                detail.add("stonewall");
                detail.add("cobblestonewall");
                detail.add("crudeironwall");
                detail.add("etchedstar");
                detail.add("wailbaby");
                detail.add("physicalshadow");
                detail.add("dust");
                detail.add("ratpoison");
                detail.add("clawmarks");
                detail.add("insaneetch");
                detail.add("invertedwater");

                roomKitSmall = small;
                roomKitMedium = medium;
                roomKitLarge = large;
                roomKitDetail = detail;
        }

        public void childEnviroment() {
                for (int in = 0; in < getChilds().size(); in++) {
                        int smallTemp = randomizer(1, 2);
                        int mediumTemp = randomizer(1, 2);
                        int largeTemp = randomizer(1, 2);
                        int detailTemp = randomizer(1, 2);
                        for (int i = 0; i < smallTemp; i++) {
                                int temp = randomizer(0, roomKitSmall.size());
                                childDescriptions.add(roomKitSmall.get(temp));
                                roomKitSmall.remove(temp);
                                temp = randomizer(0, roomKitSmall.size());
                                // add the stuff in the room for the childDescriptions and then use hashmap to
                                // see what
                                // it will do
                        }
                        for (int i = 0; i < mediumTemp; i++) {
                                int temp = randomizer(0, roomKitMedium.size());
                                childDescriptions.add(roomKitMedium.get(temp));
                                roomKitMedium.remove(temp);
                                temp = randomizer(0, roomKitMedium.size());
                                // add the stuff in the room for the childDescriptions and then use hashmap to
                                // see what
                                // it will do
                        }
                        for (int i = 0; i < largeTemp; i++) {
                                int temp = randomizer(0, roomKitLarge.size());
                                childDescriptions.add(roomKitLarge.get(temp));
                                roomKitLarge.remove(temp);
                                temp = randomizer(0, roomKitLarge.size());
                                // add the stuff in the room for the childDescriptions and then use hashmap to
                                // see what
                                // it will do
                        }
                        for (int i = 0; i < detailTemp; i++) {
                                int temp = randomizer(0, roomKitDetail.size());
                                childDescriptions.add(roomKitDetail.get(temp));
                                roomKitDetail.remove(temp);
                                temp = randomizer(0, roomKitDetail.size());
                                // add the stuff in the room for the childDescriptions and then use hashmap to
                                // see what
                                // it will do
                        }
                        roomKit();
                        // puts everything into the description
                        for (int i = 0; i < childDescriptions.size(); i++) {
                                description.add(environment.get(childDescriptions.get(i)));
                        }
                        childDescriptions.removeAll(childDescriptions);
                        // printing system
                        childDescTemp = "\n";
                        for (int i = 0; i < description.size(); i++) {
                                // System.out.println(description.get(i));
                                childDescTemp += description.get(i) + ".\n";
                        }
                        description.removeAll(description);
                        childDescSentences.add(childDescTemp);
                }
        }

        // formats description so that it can be printed
        public void descriptionSetter() {
                for (int i = 0; i < description.size(); i++) {
                        if (i == 0) {
                                description.set(i, "\n" + description.get(i));
                        } else if (i > 0 && i < description.size() - 1) {
                                description.set(i, description.get(i) + ". \n");
                        } else {
                                description.set(i, description.get(i) + ".\n");
                        }
                }
        }

        public void fillRoom() {
                int smallTemp = randomizer(1, 2);
                int mediumTemp = randomizer(1, 2);
                int largeTemp = randomizer(1, 2);
                int detailTemp = randomizer(1, 2);
                for (int i = 0; i < smallTemp; i++) {
                        int temp = randomizer(0, roomKitSmall.size());
                        roomObjs.add(roomKitSmall.get(temp));
                        roomKitSmall.remove(temp);
                        temp = randomizer(0, roomKitSmall.size());
                        // add the stuff in the room for the childDescriptions and then use hashmap to
                        // see what
                        // it will do
                }
                for (int i = 0; i < mediumTemp; i++) {
                        int temp = randomizer(0, roomKitMedium.size());
                        roomObjs.add(roomKitMedium.get(temp));
                        roomKitMedium.remove(temp);
                        temp = randomizer(0, roomKitMedium.size());
                        // add the stuff in the room for the childDescriptions and then use hashmap to
                        // see what
                        // it will do
                }
                for (int i = 0; i < largeTemp; i++) {
                        int temp = randomizer(0, roomKitLarge.size());
                        roomObjs.add(roomKitLarge.get(temp));
                        roomKitLarge.remove(temp);
                        temp = randomizer(0, roomKitLarge.size());
                        // add the stuff in the room for the childDescriptions and then use hashmap to
                        // see what
                        // it will do
                }
                for (int i = 0; i < detailTemp; i++) {
                        int temp = randomizer(0, roomKitDetail.size());
                        roomObjs.add(roomKitDetail.get(temp));
                        roomKitDetail.remove(temp);
                        temp = randomizer(0, roomKitDetail.size());
                        // add the stuff in the room for the childDescriptions and then use hashmap to
                        // see what
                        // it will do
                }
                roomKit();
        }

        public void setDesc() {
                selfDesc = "";
                for (String obj : roomObjs) {
                        selfDesc += environment.get(obj) + ". \n";
                }
        }

        public String getDesc() {
                return selfDesc;
        }

        public Room currentRoom() {
                return this;
        }

        public Enemy getEnemy() {
                return enemy;
        }

        public void removeEnemy() {
                if (enemy.health() <= 0) {
                        enemy = null;
                }
        }

        public void addEnemy() {
                int tempRandom = randomizer(0, 5);
                if (tempRandom == 0) {
                        enemy = (Enemy.initiation(1));
                } else if (tempRandom == 1) {
                        enemy = (Enemy.initiation(2));
                } else if (tempRandom == 2) {
                        enemy = (Enemy.initiation(3));
                } else if (tempRandom == 3) {
                        enemy = (Enemy.initiation(4));
                } else {
                        enemy = (Enemy.initiation(5));
                }
        }

        public String enemyList() {
                if (enemy == null) {
                        return "!";
                }
                return enemy.name();
        }

        // Returns the string of all the items in the arraylist so that the player can
        // see what is in the room
        public String enemyString() {
                return enemyList();
        }

        // when we are dropping stuff, use this method to add it to the room
        public void addItemRoom(Item item) {
                itemsInRoom.add(item);
        }

        // when we pick stuff up we need to remove it
        public void removeItemRoom(Item item) {
                itemsInRoom.remove(itemsInRoom.indexOf(item));
        }

        public Item removeItemRoom(String item) {
                for (int i = 0; i < itemsInRoom.size(); i++) {
                        if (itemsInRoom.get(i).getName().equals(item)) {
                                return itemsInRoom.remove(i);
                        }
                }
                return null;
        }

        public void itemRandomizer() {
                ArrayList<String> itemList = Item.allItemNames();
                itemsInRoom.add(Item.initiation(itemList.get(randomizer(0, itemList.size()))));
        }

        public ArrayList<String> itemList() {
                ArrayList<String> itemNames = new ArrayList<>();
                for (int i = 0; i < itemsInRoom.size(); i++) {
                        itemNames.add(itemsInRoom.get(i).getName() + "\n");
                }
                return itemNames;
        }

        // Returns the string of all the items in the arraylist so that the player can
        // see what is in the room
        public String itemString() {
                ArrayList<String> itemList = itemList();
                String itemString = "\n";
                for (int i = 0; i < itemList.size(); i++) {
                        itemString += itemList.get(i);
                }
                return itemString;
        }

        // takes everything in description and returns it as a string
        // public String descriptionPrinter() {
        // childDescTemp = "\n";
        // for (int i = 0; i < description.size(); i++) {
        // System.out.println(description.get(i));
        // childDescTemp += description.get(i) + ".\n";
        // }
        // return childDescTemp;
        // }

        // PROBLEM: ONLY THE FIRST ROOM HAS DESCRIPTIONS, adds the description of one
        // room
        // public void placeDescSet() {
        // for (int i = 0; i < childs.size(); i++) {
        // childDescSentences.add(descriptionPrinter());
        // }
        // }

        // make a method that combines childEnviroment(), descriptionPrinter(),
        // placeDescSet()
        // from the position of the color get the description
        public void placeDescGet(int num) {
                System.out.println(childDescSentences.get(num));
                System.out.println("Current desc: " + childDescSentences.size());
        }

        public ArrayList<String> getAllSides() {
                return allSides;
        }

        public ArrayList<Room> getChilds() {
                return childs;
        }

        public void refill() {
                ArrayList<String> fillSides = new ArrayList<>();
                fillSides.add("red");
                fillSides.add("black");
                fillSides.add("white");
                fillSides.add("green");
                fillSides.add("yellow");
                fillSides.add("blue");
                fillSides.add("purple");
                allSides = fillSides;
        }

        public int randomizer(int min, int max) {
                return (int) (Math.random() * (max - min) + min);
        }

        public Room getParent() {
                return parent;
        }

        public int getLayer() {
                return layer;
        }

        public int difficulty() {
                return (int) (Math.log(layer) / Math.log(2.0));
        }

        public Room getPlaces(String command) {
                return places.get(command);
        }

        public ArrayList<String> getColors() {
                return colors;
        }

        public int getColorPos(String command) {
                for (int i = 0; i < colors.size(); i++) {
                        if (colors.get(i).equals(command)) {
                                return i;
                        }
                }
                return -1;
        }

        // if the layer is 8, then make it give back EndRoom instead, otherwise create
        // children that one can travel to
        public void children() {
                if (layer == 7) {
                        childs.add(new EndRoom());
                } else {
                        int temp = randomizer(1, 4);
                        for (int i = 0; i < temp; i++) {
                                Room space = new Room(layer + 1, this);
                                childs.add(space);
                        }
                }
        }

        // instead of doing the printing in directions, make a new method called
        // directions print for example that will do all the printing
        // applies directions from allSides to each room that can be traversed too (will
        // affect the traverse method that actually allows you to move)
        // each directions has a different Room that is given back
        public ArrayList<String> directions() {
                ArrayList<String> allChoice = new ArrayList<>();
                refill();
                int temp = randomizer(0, allChoice.size());
                allChoice = allSides;
                // THERE IS AN ERROR WITH SENTENCES AND MOVING TO THE END ROOM!!!!!!!!!!!!!!!!!!
                // END ROOM DOESN'T WORK
                for (int i = 0; i < childs.size(); i++) {
                        choices.add(allChoice.get(temp));
                        allChoice.remove(temp);
                        temp = randomizer(0, allChoice.size());
                        // assigns each choice to each room at random
                        colors.add(choices.get(i));
                        places.put(colors.get(i), childs.get(i));
                }
                return choices;
        }

        public ArrayList<String> getChoices() {
                return choices;
        }

        public void directionsPrint() {
                if (childs.get(0).getClass().equals(new EndRoom().getClass())) {
                        // PROBLEM: make sure to let them move back saying either no or back and forward
                        // with a yes
                        System.out.print(
                                        "After a perilous trail, your patron has found the final room. Tell them to enter the final red door once you are ready. There is no going back.\n\n> ");
                } // else {
                  // System.out.println("\nWhich colored door do you wish to enter? ");
                  // }
                for (int i = 0; i < choices.size(); i++) {
                        if (i < choices.size() - 1) {
                                System.out.print(choices.get(i) + ", ");

                        } else {
                                if (layer != 0) {
                                        System.out.print(choices.get(i) + ", back\n");

                                } else {
                                        System.out.print(choices.get(i) + "\n");
                                }

                        }
                }
        }

        // this should be the traversing between rooms which assigns a directions to
        // each child room and then
        // reads the directional input from the player and sends them there, there
        // should also be a go back thing
        // if it doesn't contain that direction, then it will keep them in the same room
        // and say that's not a possible direction
        public Room traverse(String command) {
                // read player message as they try to move
                if (childs.get(0).getClass().equals(new EndRoom().getClass())) {
                        // System.out.print(
                        // "After a perilous trail, you have found the final room. Are you ready to
                        // traverse to the end? There is no going back.\n\n> ");
                        if (Adventure.getValidValue(Adventure.translator(command))
                                        || command.equals(directions().get(0))) {
                                System.out.println("\nThe end is finally here\n");
                                return places.get(directions().get(0));
                        } else {
                                if (layer != 0) {
                                        if (command.equals("back")) {
                                                System.out.println(
                                                                "\nYour patron glances at you and sighs, they are fueled with an unresistable desire to find redemption and an end to this hell. Despite everything, they truly want to end this as soon as possible. \nThey stick around in the room, waiting.\n");
                                                return parent;
                                        }
                                }
                                System.out.println(
                                                "\nYour patron glances at you and sighs, they are fueled with an unresistable desire to find redemption and an end to this hell. Despite everything, they truly want to end this as soon as possible. \nThey stick around in the room, waiting.\n");
                                return this;
                        }
                }
                if (layer != 0) {
                        if (command.equals("back")) {
                                // System.out.println(parent.getDesc());
                                return parent;
                        }
                }
                return places.get(command);

        }
        // when creating a room, make an instance variable that says what level it's on,
        // on boot up it creates
        // 0-4 children and makes those children in a arraylist of children rooms that
        // would keep track of what room it came from (brach off of so you can go back)
        // (keep track of parent room to go back, and children for where it can go on
        // forward)
        // pass in itself and its current layer plus one to create the children
}
// there should be a prompt that says this is the final room, entering will mean
// there is no going back

class EndRoom extends Room {
        public EndRoom() {
                super(8, null);
        }

        // ovverride children so that it doesn't do anything
        public void children() {
        }

        public ArrayList<String> directions() {
                return null;
        }

        public Room traverse(String command) {
                return null;
        }

        // override traverse so that you are prompted that this is the last destination
        // and checking if you want to go back or not
        public void childEnviroment() {
        }
}
// TEACHER TIPS: FIX!
// in room add a method that isEnd, and in room implment it to return false. and
// in the EndRoom class it will return true
// do not need a start or end room, don't write subclasses if you are not adding
// or overriding methods
// to test your could print things to see if it is operational or not like
// adding all rooms into arraylist and have it print to see how it works