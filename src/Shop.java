import java.util.Scanner;

public class Shop {

    private static final Scanner scanner = new Scanner(System.in);


    public Shop() {
    }

    public void openShop(Player player) {
        if (player.getLevel() >= 7) { // unlocks shop at lvl 7
            System.out.println("Welcome to the Landvesting shop!");
            System.out.println("What would you like to do?");
            System.out.println("Buy (b), Sell (s), or Go Back (g)");
            String choice = scanner.nextLine().toLowerCase();
            switch (choice) {
                case "b", "buy" -> buy(player);
                case "s", "sell" -> sell(player);

                default -> System.out.println("Goodbye!");
            }
        } else {
            System.out.println("Sorry, but you can't access the shop at this level!");
        }
    }

    public void buy(Player player) {
        int workerPrice = 1000;
        int farmerPrice = 5000;
        System.out.println("What would you like to buy?");
        System.out.println("Workers ($" + workerPrice + "), Farmers ($" + farmerPrice + ")");
        System.out.println("Warriors (10 gold)");


        String choice = scanner.nextLine().toLowerCase();
        switch (choice) {
            case "workers", "w" -> buyWorkers(player, workerPrice);
            case "farmers", "f" -> buyFarmers(player, farmerPrice);
            case "warriors" -> buyWarriors(player);
            default -> System.out.println("Goodbye!");
        }
    }

    public void sell(Player player) {
        System.out.println("What would you like to sell?");
        System.out.println("Workers (1 gold), Farmers (5 gold)...");

        String choice = scanner.nextLine().toLowerCase();
        switch (choice) {
            case "workers", "w" -> sellWorkers(player);
            case "farmers", "f" -> sellFarmers(player);
            default -> System.out.println("Goodbye!");
        }
    }

    private void buyWarriors(Player player) {
        System.out.println("How many warriors would you like to buy? (10 gold)");
        System.out.println("You have " + player.getGold() + " gold");
        int warriors = Integer.parseInt(scanner.nextLine()); // price for total purchase
        int goldPrice = warriors * 10;
        if (player.getGold() >= goldPrice) {
            player.setWarriors(player.getWarriors() + warriors);
            player.setGold(player.getGold() - goldPrice);
            System.out.println("You bought " + warriors + (warriors == 1 ? " warrior for " : " warriors for ") + goldPrice + " gold.");
        } else {
            System.out.println("You don't have enough gold!!!");
        }
    }

    private void buyWorkers(Player player, int workerPrice) {
        System.out.println("How many workers would you like to buy?");
        System.out.println("You have $" + player.getMoney());
        int workers = Integer.parseInt(scanner.nextLine());
        int price = workers * workerPrice;
        if (player.getMoney() >= price) {
            player.setWorkers(player.getWorkers() + workers); // buy workers for 1000, shows player money
            player.setMoney(player.getMoney() - price);
            System.out.println("You bought " + workers + (workers == 1 ? " worker for " : " workers for ") + price + " bucks.");
        } else {
            System.out.println("You don't have enough money!!!");
        }
    }
    private void buyFarmers(Player player, int farmerPrice) {
        System.out.println("How many farmers would you like to buy?");
        System.out.println("You have $" + player.getMoney());
        int farmers = Integer.parseInt(scanner.nextLine());
        int price = farmers * farmerPrice;
        if (player.getMoney() >= price) {
            player.setFarmers(player.getFarmers() + farmers);
            player.setMoney(player.getMoney() - price);
            System.out.println("You bought " + farmers + (farmers == 1 ? " farmer for " : " farmers for ") + price + " bucks.");
        } else {
            System.out.println("You don't have enough money!!!");
        }
    }
    private void sellWorkers(Player player) {
        System.out.println("How many workers would you like to sell?");
        int workers = Integer.parseInt(scanner.nextLine());
        if (player.getWorkers() >= workers) {
            player.setWorkers(player.getWorkers() - workers);
            player.setGold(player.getGold() + workers);
            System.out.println("You sold " + workers + (workers == 1 ? " worker for " : " workers for ") + workers + " gold."); //uses workers as res cus 1:1 ratio
        } else {
            System.out.println("You don't have enough workers!!!");
        }
    }
    private void sellFarmers(Player player) {
        System.out.println("How many farmers would you like to sell?");
        int farmers = Integer.parseInt(scanner.nextLine());
        if (player.getFarmers() >= farmers) {
            player.setFarmers(player.getFarmers() - farmers);
            player.setGold(player.getGold() + farmers * 5);
            System.out.println("You sold " + farmers + (farmers == 1 ? " farmer for " : " farmers for ") + farmers * 5 + " gold.");
        } else {
            System.out.println("You don't have enough farmers!!!");
        }
    }


}

