package budget;

import java.util.*;

/**
 * sorts all the list of purchase
 * it also uses comparator from interface
 */
final class SortEverything implements SortBySomeMethod {
    @Override
    public void sort(ArrayList<Purchase> purchase) {
        if (purchase.isEmpty()) {
            System.out.println("Purchase list is empty!\n");
        } else {

            ArrayList<Purchase> sortedBudget = new ArrayList<>(purchase);
            System.out.println();
            for (int i = 0; i < sortedBudget.size() - 1; i++) {
                for (int j = 0; j < sortedBudget.size() - i - 1; j++) {
                    if (compareByPrice.compare(sortedBudget.get(j), sortedBudget.get(j + 1)) < 0) {
                        Collections.swap(sortedBudget, j, j + 1);
                    }
                }
            }
            System.out.println("All:");
            double sum = 0.;
            for (int i = 0; i < sortedBudget.size(); i++) {
                sum += sortedBudget.get(i).getPrice();
                System.out.println(sortedBudget.get(i));
            }
            System.out.printf("Total: $%.2f\n\n", sum);
        }
    }
}
