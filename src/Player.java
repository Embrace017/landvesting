public class Player {
    private final String name;
    private int money;
    private int level;
    private int experience;
    private int gold;
    private int land;
    private int farmland;
    private int people;
    private int workers;
    private int farmers;
    private int warriors;

    boolean workerMsg;

    public Player(String name) {
        this.name = name;
        this.money = 1_000;
        this.level = 1;
        this.experience = 0;
        this.gold = 0;
        this.farmers = 0;
    }

    public String getName() {
        return name;
    }
    public int getMoney() {
        return money;
    }
    public int getLevel() {
        return level;
    }
    public int getExperience() {
        return experience;
    }
    public int getGold() {
        return gold;
    }
    public int getFarmers() {
        return farmers;
    }
    public int getWarriors() {
        return warriors;
    }
    public int getWorkers() {
        return workers;
    }
    public int getPeople() {
        return people;
    }
    public int getFarmland() {
        return farmland;
    }
    public int getLand() {
        return land;
    }


    public void setMoney(int money) {
        this.money = money;
    }
    public void setLevel(int level) {
        this.level = level;
    }
    public void setExperience(int experience) {
        this.experience = experience;
    }
    public void setGold(int gold) {
        this.gold = gold;
    }
    public void setFarmers(int farmers) {
        this.farmers = farmers;
    }
    public void setFarmland(int farmland) {
        this.farmland = farmland;
    }
    public void setLand(int land) {
        this.land = land;
    }
    public void setPeople(int people) {
        this.people = people;
    }
    public void setWorkers(int workers) {
        this.workers = workers;
    }
    public void setWarriors(int warriors) {
        this.warriors = warriors;
    }

    public void stats() {
        System.out.println("Name: " + this.name);
        System.out.println("Money: " + this.money);
        System.out.println("Level: " + this.level);
        System.out.println("Experience: " + this.experience + "/" + (1000 * this.level * 2));
        System.out.println("Gold: " + this.gold);
        if (this.land != 0)
            System.out.println("Acres of Land: " + this.land);
        if (this.farmland != 0)
            System.out.println("Farmland: " + this.farmland);
        if (this.people != 0)
            System.out.println("People: " + this.people);
        if (this.workers != 0)
            System.out.println("Workers: " + this.workers);
        if (this.farmers != 0)
            System.out.println("Farmers: " + this.farmers);
        if (this.warriors != 0)
            System.out.println("Warriors: " + this.warriors);
    }
    public void gainExperience(int experience) {
        this.experience += experience;
        if (this.experience >= 1000 * this.level * 2) {
            this.experience = 0;
            this.level++;
            System.out.println("~You gained a Landvestment level!~");
        }
        levelUps();
    }
    public void levelUps() {
        if (this.level > 1 && !workerMsg) {
            System.out.println("~~~Workers unlocked~~~");
            workerMsg = true;
        }
    }
}
