package org.example;


import java.util.List;
import java.util.stream.Collectors;

class RaceCondition extends Thread{

    static int count = 0;

    public synchronized static void increment(){
        for(int i=0;i<10000;i++)
            count++;
    }

    @Override
    public void run(){
        increment();
    }

}

class Main{
    public static void main(String[] args) throws InterruptedException {
        RaceCondition r1 = new RaceCondition();
        RaceCondition r2 = new RaceCondition();

        r1.start();
        r2.start();

        r1.join();
        r2.join();

        System.out.println(RaceCondition.count);


    }
}


//double minSalary = employees.stream()
//        .mapToDouble(Employee::getSalary)
//        .min()
//        .orElse(0);
//
//// 2. Find names where the employee ID is in the 'mId' list of low-earners
//List<String> targetNames = employees.stream()
//        .filter(e -> e.getSalary() == minSalary) // Find Rohan & Rahul
//        .map(Employee::getmId)                   // Get their mIds (4 and 3)
//        .flatMap(mid -> employees.stream()       // Look up who has ID 4 or 3
//                .filter(emp -> emp.getId() == mid)
//                .map(Employee::getName))
//        .collect(Collectors.toList());
//
//        System.out.println(targetNames);
