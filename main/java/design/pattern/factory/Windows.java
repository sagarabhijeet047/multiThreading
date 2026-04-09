package design.pattern.factory;

public class Windows implements OSFactory{
    @Override
    public void show() {
        System.out.println("Keep Quite");
    }
}
