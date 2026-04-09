package org.example;

import java.util.concurrent.*;

public class HotelDataAggregator {

    public static void main(String[] args) {
        // 1. Create an ExecutorService with a pool of threads
        ExecutorService executor = Executors.newFixedThreadPool(3);

        try {
            System.out.println("Fetching hotel data concurrently...");

            // 2. Submit tasks to the executor
            Future<String> roomTask = executor.submit(() -> fetchRoomAvailability());
            Future<String> guestTask = executor.submit(() -> fetchGuestDetails());
            Future<String> priceTask = executor.submit(() -> fetchPricingDeals());

            // 3. Get results (this is where the main thread waits for completion)
            // .get() is a blocking call, but since they started together,
            // the total time is equal to the longest task, not the sum.
            String roomInfo = roomTask.get();
            String guestInfo = guestTask.get();
            String priceInfo = priceTask.get();

            // 4. Merge the data
            String finalReport = String.format("""
                --- Hotel Stay Summary ---
                %s
                %s
                %s
                --------------------------
                """, roomInfo, guestInfo, priceInfo);

            System.out.println(finalReport);

        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        } finally {
            // 5. Always shut down the executor
            executor.shutdown();
        }
    }

    // Mock Repository Methods
    private static String fetchRoomAvailability() throws InterruptedException {
        Thread.sleep(1000); // Simulating DB latency
        return "Rooms: Suite 101 is Available";
    }

    private static String fetchGuestDetails() throws InterruptedException {
        Thread.sleep(800);
        return "Guest: John Doe (VIP)";
    }

    private static String fetchPricingDeals() throws InterruptedException {
        Thread.sleep(1200);
        return "Pricing: $200/night (Seasonal Discount Applied)";
    }
}