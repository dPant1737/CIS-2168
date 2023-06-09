package Assignments.Assignment_10;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;
import java.util.Stack;

import edu.uci.ics.jung.graph.Graph;
import edu.uci.ics.jung.graph.SparseGraph;

public class graphSetup {
    public static void main(String[] args) {
        SparseGraph<Integer, String> g = new SparseGraph<>();
        g.addEdge("A12", 1, 2);
        g.addEdge("B13", 1, 3);
        g.addEdge("C34", 3, 4);
        g.addEdge("D25", 2, 5);
        g.addEdge("E35", 3, 5);
        g.addEdge("F45", 4, 5);

        System.out.println(breadthFirstSearch(g, 1));
        System.out.println(depthFirstSearch(g, 1));
    }

    public static List<Integer> breadthFirstSearch(Graph<Integer, String> g, int startVertex){
        Set<Integer> identified = new HashSet<>();
        List<Integer> visited = new ArrayList<>();
        Queue<Integer> q = new LinkedList<>();

        q.add(startVertex);

        while (!q.isEmpty()) {
            int curr = q.remove();
            visited.add(curr);
            for(int adjacent:g.getNeighbors(curr)){
                if(!identified.contains(adjacent) && !visited.contains(adjacent)){
                    identified.add(adjacent);
                    q.add(adjacent);
                }
            }
        }
        return visited;
    }

    public static List<Integer> depthFirstSearch(Graph<Integer, String> g, int startVertex){
        List<Integer> visited = new ArrayList<>();
        Stack<Integer> s = new Stack<>();
        s.push(startVertex);
        while (!s.isEmpty()) {
            int curr = s.pop();
            for (int adjacent : g.getNeighbors(curr)) {
                if(!visited.contains(adjacent) && !s.contains(adjacent)){
                    s.push(adjacent);
                }
            }
            visited.add(curr);
        }
        return visited;
    }

}
