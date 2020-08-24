package budget;

import java.util.*;

final class SortTypes implements SortBySomeMethod {
    /**
     * it sorts purchase of certain type
     * the first it get every types except TYPE.EVERYTHING
     * then it counts prices in every type of purchase
     * and create instance of TypeOfPurchase class
     * in the end it sorts using comparator and bubble sort, print the answer
     */
    @Override
    public void sort(ArrayList<Purchase> array) {

        ArrayList<TypeOfPurchase> result = new ArrayList<>(4);
        for (var i : TYPE.getFourCategories()) {
            result.add(new TypeOfPurchase(i));
        }
        for (var i : array) {
            for (var t : result) {
                if (t.getType() == i.getType()) {
                    t.increasePrice(i.getPrice());
                }
            }
        }
        for (int i = 0; i < result.size() - 1; i++) {
            for (int j = 0; j < result.size() - 1 - i; j++) {
                if (compareByPrice.compare(result.get(j), result.get(j + 1)) < 0) {
                    Collections.swap(result, j, j + 1);
                }
            }
        }
        double sum = 0.;
        System.out.println("Types:");
        for (var i : result) {
            System.out.println(i);
            sum += i.getPrice();
        }
        System.out.printf("Total sum: $%.2f\n\n", sum);
    }

    Comparator<TypeOfPurchase> compareByPrice = (TypeOfPurchase x, TypeOfPurchase y)
            -> x.getPrice().compareTo(y.getPrice());
    ;

    class TypeOfPurchase {
        private Double price;
        private TYPE type;
        private String strType;

        TypeOfPurchase(TYPE type) {
            this.type = type;
            this.price = 0.;
            strType = type.toString().charAt(0) + type.toString().toLowerCase().substring(1);
        }

        void increasePrice(double price) {
            this.price += price;
        }

        public Double getPrice() {
            return price;
        }

        public TYPE getType() {
            return type;
        }

        @Override
        public String toString() {
            return String.format("%s - $%.2f", strType, price);
        }
    }
}
