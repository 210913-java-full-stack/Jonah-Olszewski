package models;

import utils.datastructure.MyArrayList;

public class TestMyArrayList {
    public static void main(String[] args) {
        MyArrayList<Integer> test = new MyArrayList();
        System.out.println("Current size: " + test.size());
        test.add(5);
        test.add(23);
        test.add(12);
        test.add(0);
        test.add(100);
        for (int i = 0; i < test.size(); i++) {
            System.out.print(test.get(i) + ", "); // prints out all elements from array --> test
        }
        System.out.println("\nCurrent size: " + test.size());
        test.add(30, 4); // works
        System.out.println("\nadding an element(30) at position index(4): " + + test.size());
        for (int i = 0; i < test.size(); i++) {
            System.out.print(test.get(i) + ", "); // prints out all elements from array --> test
        }
        System.out.println("\nGet Element at index(2) in my array: " + test.get(2));
        System.out.println("\nCheck my array for specific value contains(12): " + test.contains(12)); // No
        test.remove(2);
        System.out.println("\nTotal List size after remove index(2): " + test.size());
        for (int i = 0; i < test.size(); i++) {
            System.out.print(test.get(i) + ", ");
        }
        System.out.println("\nTotal List size: " + test.size());
        // clear function used
        test.clear();
        // list after clearing all elements
        System.out.println("\nTotal List size after clear(): " + test.size()); //works
    }
}
