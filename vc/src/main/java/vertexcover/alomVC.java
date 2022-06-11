package vertexcover;

import java.util.ArrayList;

public class alomVC {
    alomVC(){}

    
    /** 
     * Retrieves the minimum vertex cover using Alom's algorithm 
     * @param fullGraph graph without solution
     * @return ArrayList<Integer> the solution containing the min vertex cover
     */
    ArrayList<Integer> getAlomVC(graph fullGraph){
        ArrayList<Integer> visited = new ArrayList<Integer>();
        int edgeCount=fullGraph.getNoOfEdges();
        ArrayList<Integer> compare = new ArrayList<Integer>();
        while(edgeCount>0){
            int highest = fullGraph.getHighestDegree();
            //visited.add(highest);
            if(fullGraph.getHighDegree().size()!=0){ //if the ArrayList with the vertices is empty
                compare.addAll(fullGraph.getAdjList().get(fullGraph.getHighDegree().get(1)));
            }
            ArrayList<Integer> compareWith;
            
            int compareSize = fullGraph.getHighDegree().size();
            if(compareSize>1){
                for (int i =1; i<compareSize;i++){
                    compareWith = fullGraph.getAdjList().get(fullGraph.getHighDegree().get(i));
                    compare.retainAll(compareWith);

                }
                //Compares the vertices to see the common elements
                for(int x = 0; x<compareSize; x++){
                    if (compare.size()<= fullGraph.getAdjList().get(fullGraph.getHighDegree().get(x)).size()){
                        if(highest<=fullGraph.getHighDegree().get(x)){    
                            highest = fullGraph.getHighDegree().get(x);
                    
                        }
                    
                    }
                }
                //Checks for the vertex with the least amount of duplicate edges
                
            }
            //The rest is the same as the heuristic approach.
            visited.add(highest);
            int degree = fullGraph.getVertexDegree(highest);
            edgeCount=edgeCount-degree;
            fullGraph.removeVertex(highest);
            fullGraph.setDegreeArray(new ArrayList<Integer>());
        }
        System.out.println("Alom's: "+visited);
        return visited;
    }
}
