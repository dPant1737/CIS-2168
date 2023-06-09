package Assignments.Assignment_2;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class ArrayListExercises {

    //Time Complexity: O(n^2)
    public static <E> boolean unique(List<E> list) {
        for (int i = 0; i < list.size()-1; i++) {
            for (int k = i+1; k < list.size(); k++) {
                if (Objects.equals(list.get(i), list.get(k))) {
                    return false;
                }
            }
        }
        return true;
    }    

    //Time Complexity: O(n)
    public static List<Integer> allMultiples(List<Integer> list, int x) {
        List<Integer> output = new ArrayList<>();
        int newIndex = 0;
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i) % x == 0) {
                output.add(newIndex, list.get(i));
                newIndex++;
            }
        }
        return output;
    }

    //Time Complexity: O(n)
    public static List <String> allStringsOfSize(List<String> list, int length) {
        List<String> output = new ArrayList<>();
        String tempString;
        for (int i = 0; i < list.size(); i++) {
            tempString = list.get(i);
            if(tempString.length() == length){
                output.add(tempString);
            }
        }
        return output;
    }

    //Time Complexity: O(. )
    public static <E extends Comparable<E>> boolean isPermutation(List<E> list1, List<E> list2) {
        if (list1.size() != list2.size()) {
            return false;
        }

        Collections.sort(list1);  // O(n log n)
        Collections.sort(list2);
        return list1.equals(list2);
    }
 
    //Time Complexity: O(n)
    public static List<String> stringToListOfWords(String a) {
        List<String> output = new ArrayList<>();
        String[] splitString = a.split("\\s+", 0);
        for (int i = 0; i < splitString.length; i++) {
            output.add(i, splitString[i]);
        }    

        return output;
    }

    //Time Complexity: O(n^2)
    public static <E> void removeAllInstances(List<E> list, E item) {
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i) == item) {
                list.remove(item);
                i--;
            }
        }
    
    }


    public static void main(String[] args) {
        List<Integer> list1 = new ArrayList<>();
        list1.add(1);
        list1.add(4);
        list1.add(5);
        list1.add(25);
        list1.add(6);
        list1.add(5);
        list1.add(5);
        list1.add(2);
        list1.add(10);
    
        List<String> list2 = new ArrayList<>();
        list2.add("I");
        list2.add("am");
        list2.add("Dhruv");
        list2.add("and");
        Integer item = 5;
       
       
        //2.1 Uniquness
        System.out.println("2.1:");

        System.out.println(unique(list1));

        //2.2 All Multiples
        System.out.println("2.2:");
        System.out.println(list1);
        System.out.println(allMultiples(list1, 5));


        //2.3 All String of Size x
        System.out.println("2.3:");
        List <String> l3 = new ArrayList<>();
        l3.add(0, "Hello");
        l3.add(1, "world");
        l3.add(2, "I");
        l3.add(3,"am");
        l3.add(4, "Dhruv");
        System.out.println(l3);
        System.out.println(allStringsOfSize(l3, 5));
        
        
        //2.4 Permutation
        System.out.println("2.4:");
        Integer x = 10;
        Integer y = 10;

        String a = "bird";
        String b = "word";
        System.out.println(a.compareTo(b));

        //2.5 String to List of Words
        System.out.println("2.5:");
        String s = "Hello I am Dhruv!";
        stringToListOfWords(s);
        System.out.println(stringToListOfWords(s));


        //2.6 Remove all Instances
        System.out.println("2.6:");
        System.out.println(list1);
        removeAllInstances(list1, item);
        System.out.println(list1);
    }
}
