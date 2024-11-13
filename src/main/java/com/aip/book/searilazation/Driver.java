package com.aip.book.searilazation;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.function.Function;
import java.util.function.Supplier;

public class Driver {
    public static void main(String[] args) {
        Employee employee = new Employee(24, "Publicis Sapient",
                "SDE", "ashutoshpkd@gmail.com",
                "Ashutosh", "Pawade", 134000);

        String filePath = "/Users/ashutosh/Documents/JAVA/DesignProblems/employee.ser";
        for (int i=0; i<5; ++i) System.out.println(i);
        try {
            FileOutputStream fos = new FileOutputStream(filePath);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(employee);
            oos.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            FileInputStream fis = new FileInputStream(filePath);
            ObjectInputStream ois = new ObjectInputStream(fis);
            Employee inputEmployee = (Employee) ois.readObject();
            ois.close();
            System.out.println(inputEmployee);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
