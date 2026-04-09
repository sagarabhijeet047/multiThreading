package org.example;

import java.util.concurrent.*;

class Name {
    static String printFirstName() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt(); // important
            throw new RuntimeException(e);
        }
        return "my";
    }

    static String printMiddleName() {
        try {
            Thread.sleep(1000);
            return "name is";
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    static String printLastName() {
        try {
            Thread.sleep(1000);
            return "khan";
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}

public class Tavant {
    public static void main(String[] args) {
        int threadSize = Runtime.getRuntime().availableProcessors();
        System.out.println(threadSize);
        ExecutorService service = Executors.newFixedThreadPool(threadSize);

        try {
            futureImplementation(service);
            completableFutureImplementation(service);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            service.shutdown();
        }
    }

    private static void completableFutureImplementation(ExecutorService service) {
        CompletableFuture<String> first = CompletableFuture.supplyAsync(Name::printFirstName, service);
        CompletableFuture<String> middle = CompletableFuture.supplyAsync(Name::printMiddleName, service);
        CompletableFuture<String> last = CompletableFuture.supplyAsync(Name::printLastName, service);
        CompletableFuture<String> result = first
                .thenCombine(middle, (fName, mName) -> fName + " " + mName)
                .thenCombine(last, (fm, lName) -> fm + " " + lName);

        CompletableFuture.allOf(first, middle, last).thenRun(() -> {
            try {
                result.thenAccept(res ->
                        System.out.println("completableFutureImplementation >>> " + res)
                );
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    private static void futureImplementation(ExecutorService service) throws InterruptedException, ExecutionException {
        Future<String> firstNameTask = service.submit(Name::printFirstName);
        Future<String> middleNameTask = service.submit(Name::printMiddleName);
        Future<String> lastNameTask = service.submit(Name::printLastName);

        String firstName = firstNameTask.get();
        String middleName = middleNameTask.get();
        String lastName = lastNameTask.get();

        System.out.println("futureImplementation >>> " + firstName + " " + middleName + " " + lastName);
    }
}
