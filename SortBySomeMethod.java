package budget;

import java.util.ArrayList;
import java.util.Comparator;

/**
 * interface for Strategy
 * it is also contains comparator for Purchase class
 */
public interface SortBySomeMethod {
    void sort(ArrayList<Purchase> array);

    //    i hope this help me compare to object of Budget class by price
    Comparator<Purchase> compareByPrice = (Purchase first, Purchase second) -> first.getPrice().compareTo(second.getPrice());
}
