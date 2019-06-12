package javaFundamentals.main;

class PizzaElements {

    private String [] pizzaElements;
    private Integer numberOfPizzas = 1;
    private String typeOfPizza = "ordinary";
    private String clientName = "client";
    private int numberOfPizzaInOrder = 0;
    private String pizzaName = null;
    private String [] ingredientsList = null;

    PizzaElements(String[] pizzaElements) {
        this.pizzaElements = pizzaElements;
    }

    void setPizzaElements (String ... ingredients) {
        pizzaElements = new String[ingredients.length + 3];
        pizzaElements[0] = typeOfPizza;
        if (pizzaName != null) {
            pizzaElements[1] = pizzaName;
        } else {
            System.out.println("Имя пиццы не задано.");
        }
        pizzaElements[2] = numberOfPizzas.toString();
        System.arraycopy(ingredients, 0, pizzaElements, 3, ingredients.length);
        }

    void setNumberOfPizzas (int number){
        if (number <= 10) {
            numberOfPizzas = number;}
        else {
            System.out.println("Слишком большой заказ." + '\n' + "Максимальное число пицц в заказе 10" + '\n');
            numberOfPizzas = 10;}
    }

    void setTypeOfPizza (String type) {
        type = type.trim().toLowerCase().replaceAll("[^A-Za-z]", "");
        typeOfPizza = type.equals("calzone") ? type : "ordinary";
    }

    void setPizzaName (String newName, String clientName) {
        clientName = (clientName != null) ? clientName.trim().toLowerCase().replaceAll(" ", "_") : "client";
        numberOfPizzaInOrder = this.clientName.equals(clientName) ? ++numberOfPizzaInOrder : 1;
        newName = (newName != null) ? newName.trim().replaceAll("[^A-Za-z]", "") : "";
        this.clientName = clientName;
        pizzaName = (newName.length() > 4 && newName.length() < 20) ? newName : (clientName + "_" + numberOfPizzaInOrder);
    }

    void setIngredientsList (String ... ingredients){
        String [] newList;
        String [] oldList;
        String newIngredient;
        byte countOfIngredients = 0;
        newList = new String [ingredients.length];
        for ( String ingredient : ingredients) {
            newIngredient = ingredient.trim().toUpperCase().replaceAll(" ","_");
            if (checkIfThereIsAnIngredientInTheBasePizzaIngredientsList(newIngredient)) {
                newList [countOfIngredients] = newIngredient;
                ++countOfIngredients;}
            else {
                oldList = newList;
                newList = new String [oldList.length-1];
                System.arraycopy(oldList,0,newList,0, newList.length);}
        }
        ingredientsList = newList;
    }

    void addIngredientToList(String newIngredient){
        String [] newIngredientsList;
        boolean repeatingIngredient = false;
        newIngredient = newIngredient.trim().toUpperCase().replaceAll(" ","_");
        if (ingredientsList.length < BasePizzaIngredients.values().length) {
            for (String ingredient : ingredientsList) {
                repeatingIngredient = ingredient.equals(newIngredient) || repeatingIngredient;}
            if ( !repeatingIngredient && checkIfThereIsAnIngredientInTheBasePizzaIngredientsList(newIngredient)) {
                newIngredientsList = new String [ingredientsList.length+1];
                System.arraycopy(ingredientsList, 0, newIngredientsList, 0, ingredientsList.length);
                newIngredientsList[ingredientsList.length] = newIngredient;
                ingredientsList = newIngredientsList;}
            else {System.out.println("Повторите заказ ещё раз.");} }
        else {System.out.println("Пицца полна.");}
    }

    String [] getIngredientsList () {return ingredientsList;}

    String getPizzaName () {return pizzaName;}

    String getNumberOfPizzas () {return numberOfPizzas.toString();}

    String getTypeOfPizza () {return typeOfPizza;}

    private static boolean checkIfThereIsAnIngredientInTheBasePizzaIngredientsList(String ingredient) {
        boolean checkResult = false;
        for (BasePizzaIngredients pizzaIngredient: BasePizzaIngredients.values()) {
            checkResult = ingredient.equals(pizzaIngredient.toString()) || checkResult;}
        return checkResult;}
}