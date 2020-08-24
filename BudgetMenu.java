package budget;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
/**
 * It's the second class after Main
 * Almost every menu option execute here except sorting
 * the last have implemented using design pattern Strategy
 */
final class BudgetMenu {
    final Scanner scanner;
    private final Budget budget;
    private File file;

    public BudgetMenu() {
        scanner = new Scanner(System.in);
        budget = new Budget();
    }

    public void begin() {
        createFile();
        while (true) {
            printMainMenu();
            int action = scanner.nextInt();
            scanner.nextLine();
            System.out.println();
            switch (action) {
                case 1:
                    addIncome();
                    break;
                case 2:
                    addPurchase();
                    break;
                case 3:
                    showListOfPurchase();
                    break;
                case 4:
                    printBalance();
                    break;
                case 5:
                    save();
                    break;
                case 6:
                    load();
                    break;
                case 7:
                    analyze();
                    break;
                case 0:
                    sayGoodBye();
                    return;

            }
        }
    }

    private void printMainMenu() {
        System.out.println("Choose your action:\n" +
                "1) Add income\n" +
                "2) Add purchase\n" +
                "3) Show list of purchases\n" +
                "4) Balance\n" +
                "5) Save\n" +
                "6) Load\n" +
                "7) Analyze (Sort)\n" +
                "0) Exit");
    }

    private void addIncome() {
        System.out.println("Enter income:");
        budget.addIncome(scanner.nextDouble());
        scanner.nextLine();
        System.out.println("Income was added!\n");
    }
// it adds some purchase with certain type, name and price
    private void addPurchase() {
        while (true) {
            System.out.println("Choose the type of purchase\n" +
                    "1) Food\n" +
                    "2) Clothes\n" +
                    "3) Entertainment\n" +
                    "4) Other\n" +
                    "5) Back");
            int number = scanner.nextInt();
            TYPE type = TYPE.OTHER;
            switch (number) {
                case 1:
                    type = TYPE.FOOD;
                    break;
                case 2:
                    type = TYPE.CLOTHES;
                    break;
                case 3:
                    type = TYPE.ENTERTAINMENT;
                    break;
                case 4:
                    type = TYPE.OTHER;
                    break;
                case 5:
                    System.out.println();
                    return;
            }
            scanner.nextLine();
            System.out.println("\nEnter purchase name:");
            String name = scanner.nextLine();
            System.out.println("Enter price:");
            double price = scanner.nextDouble();
            scanner.nextLine();
            budget.addPurchase(new Purchase(name, price, type));
            System.out.println("Purchase was added!\n");
        }
    }

    private void showListOfPurchase() {
        if (!budget.getPurchases().isEmpty()) {
            while (true) {
                System.out.println("Choose the type of purchases\n" +
                        "1) Food\n" +
                        "2) Clothes\n" +
                        "3) Entertainment\n" +
                        "4) Other\n" +
                        "5) All\n" +
                        "6) Back");
                int number = scanner.nextInt();
                TYPE type = TYPE.OTHER;
                scanner.nextLine();
                System.out.println();
                switch (number) {
                    case 1:
                        type = TYPE.FOOD;
                        System.out.println("Food:");
                        break;
                    case 2:
                        type = TYPE.CLOTHES;
                        System.out.println("Clothes:");
                        break;
                    case 3:
                        type = TYPE.ENTERTAINMENT;
                        System.out.println("Entertainment:");
                        break;
                    case 4:
                        type = TYPE.OTHER;
                        System.out.println("Other:");
                        break;
                    case 5:
                        showAllPurchase();
                        type = TYPE.EVERYTHING;
                        break;
                    case 6:
                        return;
                }
                double sum = 0;
                boolean isEmpty = true;
                for (var i : budget.getPurchases()) {
                    if (i.getType() == type) {
                        System.out.println(i.toString());
                        sum += i.getPrice();
                        isEmpty = false;
                    }
                }
                if (isEmpty && type != TYPE.EVERYTHING) {
                    System.out.println("Purchase list is empty!\n");
                } else {
                    System.out.printf("Total sum: $%.2f\n\n", type == TYPE.EVERYTHING ? budget.getExpenses() : sum);
                }
            }
        } else {
            System.out.println("Purchase list is empty!\n");
        }
    }

    private void showAllPurchase() {
        System.out.println("All:");
        for (var i : budget.getPurchases()) {
            System.out.println(i.toString());
        }
    }

    private void printBalance() {
        System.out.printf("Balance: $%.2f\n\n", budget.getBalance());
    }

    private void sayGoodBye() {
        System.out.println("Bye!");
    }
// save purchases to a file
    private void save() {
        try (FileWriter fileWriter = new FileWriter(file)) {
            boolean isFirstTime = true;
//            it writes type, name and price in separate lines
            for (var i : budget.getPurchases()) {
                if (isFirstTime) {
                    fileWriter.write(String.valueOf(budget.getBalance()));
                }
                isFirstTime = false;
                fileWriter.write("\n" + i.getType().toString() + "\n");
                fileWriter.write(i.getName() + "\n");
                fileWriter.write(String.valueOf(i.getPrice()));
            }
            System.out.println("Purchases were saved!\n");
        } catch (FileNotFoundException e) {
            System.out.println("File not found, boss");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
// load purchases from some file
    private void load() {
        try (Scanner fileScanner = new Scanner(file)) {

            budget.updateBalanceFromFile(Double.parseDouble(fileScanner.nextLine()));
            while (fileScanner.hasNextLine()) {
                TYPE type = TYPE.valueOf(fileScanner.nextLine());
                String name = fileScanner.nextLine();
                double price = Double.parseDouble(fileScanner.nextLine());
                budget.addPurchaseFromFile(new Purchase(name, price, type));
            }
            System.out.println("Purchases were loaded!\n");
        } catch (FileNotFoundException e) {
            System.out.println("File not found!");
        }
    }
// method for creating certain file if it does not exist
    private void createFile() {
        file = new File("purchases.txt");
        try {
            boolean createdNew = file.createNewFile();
        } catch (IOException e) {
            System.out.println("Cannot create the file: " + file.getPath());
        }
    }
// sorting
//    it uses Strategy
    private void analyze() {
        while (true) {
            System.out.println("How do you want to sort?\n" +
                    "1) Sort all purchases\n" +
                    "2) Sort by type\n" +
                    "3) Sort certain type\n" +
                    "4) Back");
            int action = scanner.nextInt();
            scanner.nextLine();
            SortBySomeMethod sort;
            System.out.println();
            switch (action) {
                case 1:
//                    sort all purchase using comparator
                    sort = new SortEverything();
                    sort.sort(budget.getPurchases());
                    break;
                case 2:
//                    sort types
                    sort = new SortTypes();
                    sort.sort(budget.getPurchases());
                    break;
                case 3:
//                    sort certain type
                    sort = new SortCertainType();
                    sort.sort(budget.getPurchases());
                    break;
                case 4:
                    return;
            }
        }
    }

}

