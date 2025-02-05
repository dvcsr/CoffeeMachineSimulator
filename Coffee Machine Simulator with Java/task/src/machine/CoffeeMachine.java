package machine;
import java.util.Scanner;

public class CoffeeMachine {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int[] totalIngredients = new int[5];
        totalIngredients[0] = 400; //water
        totalIngredients[1] = 540; //milk
        totalIngredients[2] = 120; //coffee
        totalIngredients[3] = 9; //disposable cup
        totalIngredients[4] = 550; //money
        boolean isExit = false;
        int cupServed = 0;


        while (!isExit) {

            boolean needCleaning = cupServed == 10;
            System.out.println("Write action (buy, fill, take, clean, remaining, exit): ");
            String action = scanner.nextLine();

            if (needCleaning) {
                if (action.equals("clean")){
                    cupServed = 0;
                    System.out.println("I have been cleaned!");
                }
                else System.out.println("I need cleaning!");
            }
            else if(cupServed < 10) {
                switch (action) {
                    case "fill":
                        fillTotalIngredients(scanner, totalIngredients);
                        break;
                    case "buy":
                        System.out.println("What do you want to buy? 1 - espresso, 2 - latte, 3 - cappuccino, back - to main menu:");
                        String coffeeChoice = scanner.nextLine();
                        System.out.println();
                        if (!coffeeChoice.equals("back")) {
                            createCustomerCoffee(coffeeChoice, totalIngredients);
                            cupServed++;
                        }
                        break;
                    case "take":
                        totalIngredients[4] = 0;
                        System.out.println("I gave you $550");
                        break;
                    case "remaining":
                        printCurrentState(totalIngredients);
                        break;
                    case "exit":
                        isExit = true;
                }
            }
            System.out.println();
        }

    }

    public static void createCustomerCoffee(String coffeeChoice, int[] totalIngredients) {
        //water milk coffee cup money
        int waterNeeded = 0; int milkNeeded = 0; int coffeeNeeded = 0; int cupsNeeded = 0; int moneyNeeded = 0;

        switch (coffeeChoice){
            case "1":
                waterNeeded = CoffeeType.ESPRESSO.water;
                milkNeeded = CoffeeType.ESPRESSO.milk;
                coffeeNeeded = CoffeeType.ESPRESSO.coffee;
                cupsNeeded = CoffeeType.ESPRESSO.cup;
                moneyNeeded = CoffeeType.ESPRESSO.money;
                break;
            case "2":
                waterNeeded = CoffeeType.LATTE.water;
                milkNeeded = CoffeeType.LATTE.milk;
                coffeeNeeded = CoffeeType.LATTE.coffee;
                cupsNeeded = CoffeeType.LATTE.cup;
                moneyNeeded = CoffeeType.LATTE.money;
                break;
            case "3":
                waterNeeded = CoffeeType.CAPPUCCINO.water;
                milkNeeded = CoffeeType.CAPPUCCINO.milk;
                coffeeNeeded = CoffeeType.CAPPUCCINO.coffee;
                cupsNeeded = CoffeeType.CAPPUCCINO.cup;
                moneyNeeded = CoffeeType.CAPPUCCINO.money;
                break;
        }
        boolean enoughWater = totalIngredients[0] - waterNeeded >= 0;
        boolean enoughMilk = totalIngredients[1] - milkNeeded >= 0;
        boolean enoughCoffee = totalIngredients[2] - coffeeNeeded >= 0;
        boolean enoughCup = totalIngredients[3] - cupsNeeded >= 0;

        if (enoughWater && enoughMilk && enoughCoffee && enoughCup) {
            System.out.println("I have enough resources, making you a coffee!");
            totalIngredients[0] = totalIngredients[0] - waterNeeded;
            totalIngredients[1] = totalIngredients[1] - milkNeeded;
            totalIngredients[2] = totalIngredients[2] - coffeeNeeded;
            totalIngredients[3] = totalIngredients[3] - cupsNeeded;
            totalIngredients[4] = totalIngredients[4] + moneyNeeded;
        }
        else if (!enoughWater) {
            System.out.println("Sorry, not enough water!");
        }
        if (!enoughMilk) {
            System.out.println("Sorry, not enough milk!");
        }
        if (!enoughCoffee) {
            System.out.println("Sorry, not enough coffee!");
        }
        if (!enoughCup) {
            System.out.println("Sorry, not enough cup!");
        }
    }

    public static void printCurrentState(int[] ingredients) {
        System.out.println("The coffee machine has:");
        System.out.println(ingredients[0] + " ml of water");
        System.out.println(ingredients[1] + " ml of milk");
        System.out.println(ingredients[2] + " g of coffee beans");
        System.out.println(ingredients[3] + " disposable cups");
        System.out.println("$"+ ingredients[4] + " of money");
    }

    public static void fillTotalIngredients(Scanner scanner, int[] totalIngredients) {
        System.out.println("Write how many ml of water you want to add: ");
        totalIngredients[0] = totalIngredients[0] + scanner.nextInt();
        System.out.println("Write how many ml of milk you want to add: ");
        totalIngredients[1] = totalIngredients[1] + scanner.nextInt();
        System.out.println("Write how many grams of coffee beans you want to add: ");
        totalIngredients[2] = totalIngredients[2] + scanner.nextInt();
        System.out.println("Write how many disposable cups you want to add:");
        totalIngredients[3] = totalIngredients[3] + scanner.nextInt();
    }

}

enum CoffeeType {
    //water milk coffee cup money
    ESPRESSO(250, 0, 16, 1, 4),
    LATTE(350, 75, 20, 1, 7),
    CAPPUCCINO(200, 100, 12, 1, 6);

    public final int water;
    public final int milk;
    public final int coffee;
    public final int cup;
    public final int money;


    CoffeeType(int water, int milk, int coffee, int cup, int money) {
        this.water = water;
        this.milk = milk;
        this.coffee = coffee;
        this.cup = cup;
        this.money = money;
    }
}