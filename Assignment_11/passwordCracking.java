package Assignments.Assignment_11;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import edu.uci.ics.jung.graph.DirectedSparseGraph;

public class passwordCracking {
    public static void main(String[] args) {
        DirectedSparseGraph<Integer, String> graph = new DirectedSparseGraph<>();
        String fileName = "keylog.txt";
        try {
            Scanner scanner = new Scanner(new File(fileName));
            while(scanner.hasNextLine()) {
                String s = scanner.nextLine();
                int digit1 = Integer.parseInt(String.valueOf(s.charAt(0)));
                int digit2 = Integer.parseInt(String.valueOf(s.charAt(1)));
                int digit3 = Integer.parseInt(String.valueOf(s.charAt(2)));
                graph.addEdge(digit1+""+digit2, digit1, digit2);
                graph.addEdge(digit2+""+digit3, digit2, digit3);
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        System.out.println(solve(graph));
       
    }

    public static String solve(DirectedSparseGraph <Integer, String> graph){
        String code = "";
        while (graph.getEdgeCount() != 1) {
            for (int vert : graph.getVertices()) {
                if(graph.inDegree(vert) == 0){
                    code += vert;
                }
            }
            graph.removeVertex(Integer.parseInt(String.valueOf(code.charAt(code.length()-1))));
        }
        code += graph.getEdges().toArray()[0];
        return code;
    }



}
