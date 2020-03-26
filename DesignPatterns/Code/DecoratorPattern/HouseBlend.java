package DecoratorPattern;

public class HouseBlend extends Beverage {
    public HouseBlend() {
        this.description = "House Blend Coffee";
    }

    public double cost() {
        return 0.89;
    }
}