package org.example;

public class HotelManager {
    private HotelManager() {}

    private static class SingletonHelper {
        // Only loaded when getInstance() is called
        private static final HotelManager INSTANCE = new HotelManager();
    }

    public static HotelManager getInstance() {
        return SingletonHelper.INSTANCE;
    }
}
