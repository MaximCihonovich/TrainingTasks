package javaFundamentals.main;

import java.time.LocalTime;

class PizzaOrder  {
    private int orderNumber;
    private int clientNumber;
    private PizzaElements pizzaElements;


    PizzaOrder(int orderNumber, int clientNumber, PizzaElements pizzaElements) {
        this.orderNumber = orderNumber >= 10000 ? 1 : orderNumber;
        this.clientNumber = clientNumber >= 10000 ? 1 : clientNumber;
        this.pizzaElements = pizzaElements;
    }

    void getPizzaOrderHeadline () {
    System.out.printf("[ %05d : %05d : %s : %s ]%n", orderNumber, clientNumber, pizzaElements.getPizzaName(), pizzaElements.getNumberOfPizzas());
    }

    public String toString () {
        float pizzaBase, ingredientSum, pizzaCost, totalCost;
        ingredientSum = 0f;
        StringBuilder bill = new StringBuilder();
        String toStringIngredient;
        LocalTime orderTime;
        orderTime = LocalTime.now();
        bill.append(" Время заказа :  ");
        bill.append(orderTime);
        bill.append("\n********************************");
        bill.append("\n Заказ :    ");
        bill.append(orderNumber);
        bill.append("\n Клиент :   ");
        bill.append(clientNumber);
        bill.append("\n Название : ");
        bill.append(pizzaElements.getPizzaName());
        bill.append("\n--------------------------------\n");
        if (pizzaElements.getTypeOfPizza().equals("calzone")) {
            pizzaBase = 1.5f;
            bill.append(String.format("%-12s%8s%12.2f%n","Pizza Base",pizzaElements.getTypeOfPizza(),pizzaBase));
        } else {
            pizzaBase = 1.0f;
            bill.append(String.format("%-12s%8s%12.2f%n","Pizza Base ",pizzaElements.getTypeOfPizza(),pizzaBase));
        }
        for (String ingredient : pizzaElements.getIngredientsList()) {
            ingredientSum = ingredientSum + BasePizzaIngredients.valueOf(ingredient).getCost();
            toStringIngredient = ingredient.toLowerCase().replaceAll("_"," ");
            bill.append(String.format("%-16s%16.2f%n",toStringIngredient, BasePizzaIngredients.valueOf(ingredient).getCost()));
        }
        bill.append("--------------------------------\n");
        pizzaCost = ingredientSum + pizzaBase;
        totalCost = pizzaCost * Integer.parseInt(pizzaElements.getNumberOfPizzas());
        bill.append(String.format("%-12s%20.2f%n","Всего : ",pizzaCost));
        bill.append(String.format("%-12s%18s%n","Кол-во : ",pizzaElements.getNumberOfPizzas()));
        bill.append("--------------------------------\n");
        bill.append(String.format("%-16s%16.2f%n","Общая сумма :",totalCost));
        bill.append("********************************\n");
        return bill.toString();
    }
}