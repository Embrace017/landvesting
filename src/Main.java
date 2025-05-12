import java.util.Random;
import java.util.Scanner;

import static java.lang.Integer.parseInt;

public class Main {

    private static final Random random = new Random();
    private static final Scanner scanner = new Scanner(System.in);
    public static final Time time = new Time();

    public static boolean buyAll = false;



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
                String choice = scanner.nextLine();
                switch (choice) {
                    case "buy", "b" -> basicLand(player);
                    case "collect", "c" -> profits(player);


                    case "stats" -> player.stats();
                    case "info" -> info();


                    // Dev commands
                    case "time" -> time.timeForward(parseInt(scanner.nextLine()));
                    case "time?" -> time.printTime();
                    case "clear time" -> time.clearTime();
                    default -> System.out.println("Invalid command");
                }
            } catch (Exception e) {
                System.out.println("Something went wrong");
            }
            if (time.getYear() > 100) {
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
        int workerRes;


        boolean buying = true;
        while (player.getMoney() >= price && buying) {
            workerRes = 0;
            peopleRes = random.nextInt(player.getLevel()) + 1; // Makes people depending on player level
            landRes = random.nextInt(player.getLevel()) + 1; // Makes land depending on player level
            // Level Up upgrades @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
            if (player.getLevel() > 1)
                workerRes = random.nextInt(player.getLevel()) + 1;
            res = peopleRes // Calculate price based on whats found
                    + landRes
                    + workerRes;
            price = res * 100 * (time.getYear() + 1) + random.nextInt(res * 100);


            System.out.println("\nYou found " + landRes + (landRes == 1 ? " acre of land with " : " acres of land with ") + peopleRes + (peopleRes == 1 ? " person." : " people."));

            // Level up upgrades @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
            if (player.getLevel() > 1)
                System.out.println("The land comes with " + workerRes + (workerRes == 1 ? " worker." : " workers."));


            System.out.println("You will need " + price + " bucks to make this purchase.");
            System.out.println("Your current money: " + player.getMoney());
            System.out.println("Are you going to commit??");

            String answer = scanner.nextLine();
            if (answer.equalsIgnoreCase("yes") || answer.equalsIgnoreCase("y")) {

                if (player.getMoney() >= price) {

                    int timeSpent = random.nextInt(10) + 1;
                    System.out.println("You spent " + timeSpent + " hours Landvesting.");
                    time.timeForward(timeSpent);


                    player.setLand(player.getLand() + landRes);
                    player.setPeople(player.getPeople() + peopleRes);
                    player.setMoney(player.getMoney() - price);
                    System.out.println("You make the Landvestment!");
                    System.out.println(" \n ");

                    // Level up upgrades @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
                    player.setWorkers(player.getWorkers() + workerRes);
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

            int times = Math.min(10, parseInt(scanner.nextLine()));
            for (int i = 0; i < times; i++) {
                System.out.println("\nCollection " + (i + 1) + " of " + times);
                collectProfits(player);
            }
            System.out.println("\nCollection completed.");
            player.stats();

        } else {
            System.out.println("Okay, maybe later!");
        }
    }

    private static void collectProfits(Player player) {
        try {
            time.timeForward(100); // Costs 100 hours to collect profits
            Thread.sleep(100);

            // Calculate multiplier
            boolean rolling = true;
            int multiplier = 0;
            while (rolling) {
                multiplier = (random.nextInt(10)) * random.nextInt(5);
                if (multiplier != 0) {  // Make sure multiplier is not 0
                    rolling = false;
                }
            }
            int land = player.getLand() * player.getLevel();
            int people = player.getPeople() * player.getLevel();
            int workers = player.getWorkers() * player.getLevel() * 2;
            // Add other incomes when necessary (make sure to add to finalRes as well)

            // Add all the resources together
            int finalRes = (land + people + workers) * multiplier;


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
        System.out.println("Land gets more expensive as years pass");
    }
}