package budget;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

/**
 * method sort is overridden
 * it gets some type of purchase and sort this type
 * using comparator from class's interface
 */
final class SortCertainType implements SortBySomeMethod {
    @Override
    public void sort(ArrayList<Purchase> arrayList) {
        System.out.println("Choose the type of purchase\n" +
                "1) Food\n" +
                "2) Clothes\n" +
                "3) Entertainment\n" +
                "4) Other");
        Scanner scanner = new Scanner(System.in);
        int action = scanner.nextInt();
        TYPE type = TYPE.EVERYTHING;
        String strType = "";
        switch (action) {
            case 1:
                type = TYPE.FOOD;
                strType = "Food";
                break;
            case 2:
                type = TYPE.CLOTHES;
                strType = "Clothes";
                break;
            case 3:
                type = TYPE.ENTERTAINMENT;
                strType = "Entertainment";
                break;
            case 4:
                type = TYPE.OTHER;
                strType = "Other";
        }
        if (type == TYPE.EVERYTHING) {
            return;
        }
        ArrayList<Purchase> sortedCertainType = new ArrayList<>();
        for (var i : arrayList) {
            if (i.getType() == type) {
                sortedCertainType.add(i);
            }
        }

        if (sortedCertainType.isEmpty()) {
            System.out.println("\nPurchase list is empty!\n");
            return;
        }
//        bubble sorting
        for (int i = 0; i < sortedCertainType.size() - 1; i++) {
            for (int j = 0; j < sortedCertainType.size() - i - 1; j++) {
                if (compareByPrice.compare(sortedCertainType.get(j), sortedCertainType.get(j + 1)) < 0) {
                    Collections.swap(sortedCertainType, j, j + 1);
                }
            }
        }
        double sum = 0.;
        System.out.println("\n" + strType );
        for (var i : sortedCertainType) {
            sum += i.getPrice();
            System.out.println(i.toString());
        }
        System.out.printf("Total sum: $%.2f\n\n", sum);
    }
}
