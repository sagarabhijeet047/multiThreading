package design.pattern.factory;

public class Android implements OSFactory{
    @Override
    public void show() {
        System.out.println("Android is 💖");
    }
}
