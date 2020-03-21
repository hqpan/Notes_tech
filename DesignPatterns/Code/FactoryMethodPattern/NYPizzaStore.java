package FactoryPattern;

public class NYPizzaStore extends PizzaStore {
    protected Pizza createPizza(String item) {
        if (item.equals("cheese"))
            return new NYStyleCheesePizza();
        else
            return null;
    }
}
