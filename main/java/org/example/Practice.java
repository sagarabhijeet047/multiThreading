package org.example;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

class Practice {
    public static void main(String[] args) {

        List<Employee> employees = Arrays.asList(
                new Employee(1, "Rohan", 20000, 4),
                new Employee(2, "Rahul", 20000, 3),
                new Employee(3, "Siva", 50000, 1),
                new Employee(4, "Om", 70000, 2)
        );

        double minSalary = employees.stream()
                .mapToDouble(Employee::getSalary)
                .min()
                .orElse(0);

        List<String> names = employees.stream()
                .filter(e -> e.getSalary() == minSalary)
                .map(Employee::getmId)
                .flatMap(mid -> employees.stream()
                        .filter(emp -> emp.getId() == mid)
                        .map(Employee::getName))
                .collect(Collectors.toList());

        System.out.println(names);

        // Get all employee names with salary > 30,000
        List<String> employeeMoreThen30k = employees.stream()
                .filter(e -> e.getSalary()>30000)
                .map(Employee::getName)
                .toList();

        System.out.println("Employees Salary more then 30K: "+employeeMoreThen30k);

        Employee emp = employees.stream()
                .min((e1, e2) ->Double.compare(e1.getSalary(), e2.getSalary()))
                .orElse(null);

    }
}