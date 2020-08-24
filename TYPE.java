package budget;

public enum TYPE {
    FOOD,
    CLOTHES,
    ENTERTAINMENT,
    OTHER,
    EVERYTHING;

    //    method which return every enum except EVERYTHING
    public static TYPE[] getFourCategories() {
        return new TYPE[]{FOOD, CLOTHES, ENTERTAINMENT, OTHER};
    }
}
