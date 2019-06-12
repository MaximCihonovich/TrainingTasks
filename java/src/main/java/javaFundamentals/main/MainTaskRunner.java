package javaFundamentals.main;

public class MainTaskRunner {

    public static void main (String [] args){

        PizzaElements pizzaElements = new PizzaElements(null);

// Часть 1 и 2

        pizzaElements.setIngredientsList("tomato paste", "PEPPeroni", "GarLic", "bacon");
        pizzaElements.setPizzaName("Margarita", "client");
        System.out.println("Название пиццы: " + pizzaElements.getPizzaName());
        for (String ingredient : pizzaElements.getIngredientsList()) {
            System.out.print(ingredient+'\t');}
        System.out.print('\n');

        pizzaElements.setIngredientsList("tomato_paste", "   cheese ", "SalamI", "mushrooms");
        pizzaElements.addIngredientToList("oliVES  ");
        pizzaElements.setPizzaName(null, "client");
        System.out.println("Название пиццы: " + pizzaElements.getPizzaName());
        for (String ingredient : pizzaElements.getIngredientsList()) {
            System.out.print(ingredient+'\t');}
        System.out.print('\n');

// Часть 3 и 4

        pizzaElements.setIngredientsList("tomato paste", "PEPPeroni", "GarLic", "bacon");
        pizzaElements.setPizzaName("Margarita", null);
        pizzaElements.setTypeOfPizza("calzone");
        pizzaElements.setNumberOfPizzas(2);
        pizzaElements.setPizzaElements(pizzaElements.getIngredientsList());
        PizzaOrder order1 = new PizzaOrder(1000000,7717, pizzaElements);
        System.out.print('\n');
        order1.getPizzaOrderHeadline();
        System.out.println(order1.toString());

        pizzaElements.setIngredientsList("tomato paste", "garlic","pepperoni", "cheese", "salami");
        pizzaElements.setPizzaName("PepperoniOro", null);
        pizzaElements.setTypeOfPizza("normal");
        pizzaElements.setNumberOfPizzas(3);
        pizzaElements.setPizzaElements(pizzaElements.getIngredientsList());
        PizzaOrder order2 = new PizzaOrder(1000000,7717, pizzaElements);
        order2.getPizzaOrderHeadline();
        System.out.println(order2.toString());

        pizzaElements.setPizzaName("BasePZZ", "somebody");
        pizzaElements.setTypeOfPizza("ordinary");
        pizzaElements.setIngredientsList("cheese", "tomato_paste", "salami");
        pizzaElements.setNumberOfPizzas(12);
        pizzaElements.setPizzaElements(pizzaElements.getIngredientsList());
        PizzaOrder order3 = new PizzaOrder(2,4372, pizzaElements);
        order3.getPizzaOrderHeadline();
        System.out.println(order3.toString());
    }
}
