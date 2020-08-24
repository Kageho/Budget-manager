package budget;

final class Purchase {
    private TYPE type;
    private Double price;
    private String name;

    Purchase(String name, double price, TYPE type) {
        this.name = name;
        this.price = price;
        this.type = type;
    }

    public TYPE getType() {
        return type;
    }

    public Double getPrice() {
        return price;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return String.format("%s $%.2f", this.name, this.price);
    }


}
