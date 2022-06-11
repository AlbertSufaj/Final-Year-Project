package vertexcover;

import java.util.ArrayList;

public class heuristicVC {
    heuristicVC(){}
    /** 
     * @param fullGraph
     * @return ArrayList<Integer>
     */
    ArrayList<Integer> getHeuristicVC(graph fullGraph){
        
        ArrayList<Integer> visited = new ArrayList<Integer>();
        int edgeCount=fullGraph.getNoOfEdges();
        
        while (edgeCount>0){
            int highest = fullGraph.getHighestDegree(); //gets vertex with highest degree
            visited.add(highest);//adds to current solution
            int degree =fullGraph.getVertexDegree(highest);//gets teh degree of the vertex
            edgeCount=edgeCount-degree;//deduct edge count by the degree
            fullGraph.removeVertex(highest);//remove vertex from graph
        }
        System.out.println("Vertex Cover [Heuristic]: "+visited);
        return visited;
    }
}
