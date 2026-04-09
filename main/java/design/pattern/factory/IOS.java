package design.pattern.factory;

public class IOS implements OSFactory{
    @Override
    public void show() {
        System.out.println("We are Rich 💵");
    }
}
