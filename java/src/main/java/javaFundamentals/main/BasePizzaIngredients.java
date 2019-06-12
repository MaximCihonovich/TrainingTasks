package javaFundamentals.main;

public enum BasePizzaIngredients {TOMATO_PASTE(1.0f), CHEESE (1.0f),
    SALAMI (1.5f), BACON (1.2f), GARLIC (0.3f), CORN (0.7f),
    PEPPERONI (0.6f), OLIVES (0.5f);
    float cost;

    BasePizzaIngredients(float cost) {
        this.cost = cost;
    }
    public float getCost () {return cost;}
}