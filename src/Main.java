import java.util.Random;
import java.util.Scanner;

import static java.lang.Integer.parseInt;

public class Main {
    private static final Shop shop = new Shop();
    private static final Random random = new Random();
    private static final Scanner scanner = new Scanner(System.in);
    private static final Time time = new Time();


    static int maxCollection = 0;
    static int totalCollection = 0;
    static int maxTotalCollection = 0;




    public static void main(String[] args) {


        //System.out.print("Please enter your name: ");
        //String name = scanner.nextLine();
        String name = "libs";
        System.out.println("TESTING~~~");
        Player player = new Player(name);


        boolean running = true;
        while (running) {
            System.out.println(" ");
            try {
                System.out.println("What's it gunna be?");
                String choice = scanner.nextLine().toLowerCase();
                switch (choice) {
                    case "buy", "b" -> basicLand(player);
                    case "collect", "c" -> profits(player);
                    case "shop", "s" -> shop.openShop(player);


                    case "stats" -> player.stats();
                    case "info" -> info();


                    // Dev commands
                    case "time" -> time.timeForward(player, parseInt(scanner.nextLine()));
                    case "time?" -> time.printTime();
                    case "clear time" -> time.clearTime();
                    // Cheats
                    case "workers" -> {
                        System.out.println("How many workers?");
                        player.setWorkers(parseInt(scanner.nextLine()));
                    }
                    case "farmers" -> {
                        System.out.println("How many farmers?");
                        player.setFarmers(parseInt(scanner.nextLine()));
                    }
                    case "warriors" -> {
                        System.out.println("How many warriors?");
                        player.setWarriors(parseInt(scanner.nextLine()));
                    }
                    case "land" -> {
                        System.out.println("How many acres of land?");
                        player.setLand(parseInt(scanner.nextLine()));
                    }
                    case "money" -> player.setMoney(1000000);
                    case "gold" -> {
                        System.out.println("How much gold?");
                        player.setGold(parseInt(scanner.nextLine()));
                    }

                    default -> System.out.println("Invalid command");
                }
            } catch (Exception e) {
                System.out.println("Something went wrong");
            }
            if (time.getYear() > 100) {
                System.out.println("You've reaches 100 years! GAME OVER!");
                running = false;
            }
        }
    }


    public static void basicLand(Player player) { // buys low level land
        System.out.println("\nYou will Landvest until can't afford the next find or you choose to stop");

        int peopleRes; // Makes people depending on player level
        int landRes; // Makes land depending on player level
        int res;
        int price = 0;

        // Level Up upgrades @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
        int workerRes = 0;
        int farmerRes = 0;


        boolean buying = true;
        while (player.getMoney() >= price && buying) {


            peopleRes = random.nextInt(player.getLevel()) + 1; // Makes people depending on player level
            landRes = random.nextInt(player.getLevel()) + 1; // Makes land depending on player level

            // Level Up upgrades @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
            if (player.getLevel() >= 2)
                workerRes = random.nextInt(player.getLevel()) / 2;
            if (player.getLevel() >= 5)
                farmerRes = random.nextInt(player.getLevel()) / 5;


            res = peopleRes // Calculate price based on whats found
                    + landRes
                    + workerRes * 10
                    + farmerRes * 100;

            price = res * 100 * (time.getYear() + 1) + random.nextInt(res * 100);


            System.out.println("\nYou found " + landRes + (landRes == 1 ? " acre of land with " : " acres of land with ") + peopleRes + (peopleRes == 1 ? " person." : " people."));

            // Level up upgrades @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
            if (workerRes != 0)
                System.out.println("The land comes with " + workerRes + (workerRes == 1 ? " worker." : " workers."));
            if (farmerRes != 0)
                System.out.println("The land comes with " + farmerRes + (farmerRes == 1 ? " farmer." : " farmers."));


            System.out.println("You will need " + price + " bucks to make this purchase.");
            System.out.println("Your current money: " + player.getMoney());
            System.out.println("Are you going to commit??");

            String answer = scanner.nextLine();
            if (answer.equalsIgnoreCase("yes") || answer.equalsIgnoreCase("y")) {

                if (player.getMoney() >= price) {

                    int timeSpent = random.nextInt(10) + 1;
                    System.out.println("You spent " + timeSpent + " hours Landvesting.");
                    time.timeForward(player, timeSpent);


                    player.setLand(player.getLand() + landRes);
                    player.setPeople(player.getPeople() + peopleRes);
                    player.setMoney(player.getMoney() - price);
                    System.out.println("You make the Landvestment!");
                    System.out.println(" \n ");

                    // Level up upgrades @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
                    player.setWorkers(player.getWorkers() + workerRes); // Level 2
                    player.setFarmers(player.getFarmers() + farmerRes); // Level 5


                } else {
                    System.out.println("You don't have enough money!");
                }


            } else {
                System.out.println("Maybe next time!");
                buying = false;
            }
        }
        System.out.println("\nLandvestment searching completed... for now. ");
        player.stats();
    }


    public static void profits(Player player) { // Calculate profits


        System.out.println("\nTime will advance 100 hours per collection");
        System.out.println("Would you like to continue?");

        String answer = scanner.nextLine();
        if (answer.equalsIgnoreCase("yes") || answer.equalsIgnoreCase("y")) {
            System.out.println("How many times would you like to collect? (Max 10)");

            int times = Math.min(10, parseInt(scanner.nextLine())); // Make sure times is not more than 10
            for (int i = 0; i < times; i++) {  // choose how many times to collect then send to collect method
                System.out.println("\nCollection " + (i + 1) + " of " + times);
                collectProfits(player);
            }
            System.out.println("\nCollection completed. Highest collected amount: " + maxCollection + " bucks.");
            System.out.println("Total collected amount: " + totalCollection + " bucks.");
            System.out.println("Max ever collected amount: " + maxTotalCollection + " bucks.");
            totalCollection = 0;
            maxCollection = 0;
            player.stats();

        } else {
            System.out.println("Okay, maybe later!");
        }
    }

    private static void collectProfits(Player player) {
        try {
            time.timeForward(player, 100); // Costs 100 hours to collect profits
            Thread.sleep(100);

            // Calculate multiplier
            boolean rolling = true;
            int multiplier = 0;
            while (rolling) {
                multiplier = (random.nextInt(10)) * random.nextInt(3);
                if (multiplier != 0)   // Make sure the multiplier is not 0
                    rolling = false;
            }

            int land = player.getLand() * player.getLevel();
            int people = player.getPeople() * player.getLevel();
            int workers = player.getWorkers() * player.getLevel() * 2;
            int farmers = player.getFarmers() * player.getLevel() * 10;
            // Add other incomes when necessary (make sure to add to finalRes as well)

            // Add all the resources together
            int finalRes = ((land + people + workers + farmers) * multiplier) / player.getLevel();

            totalCollection += finalRes;

            if (totalCollection > maxTotalCollection)
                maxTotalCollection = totalCollection;

            if (finalRes > maxCollection)
                maxCollection = finalRes;


            // Set player money and exp
            player.setMoney(player.getMoney() + finalRes);
            System.out.println("You earned " + finalRes + " bucks!");
            player.gainExperience(finalRes / player.getLevel());

        } catch (Exception e) {
            System.out.println("Something went wrong");
        }
    }

    public static void info() { // Display info about the game
        System.out.println("Landvesting is a game where you can buy land and earn money.");
        System.out.println("Game will terminate at 100 years. See how rich you can become!");
        System.out.println("Buying land gets more expensive as years pass.");
    }
}