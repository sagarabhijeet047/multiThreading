package design.pattern.factory;


public class FactoryDesignPattern {
    public static OSFactory getOSFactory(String OSType){
       return switch (OSType){
            case "Android" -> new Android();
            case "IOS" -> new IOS();
            case "Windows" -> new Windows();
            default -> throw new IllegalArgumentException("Unknown OS type: " + OSType);
        };
    }

    public static void main(String[] args) {
        OSFactory osType = getOSFactory("Android");
        osType.show();
    }
}



