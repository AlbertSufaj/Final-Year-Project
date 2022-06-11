package vertexcover;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;


/**
 * Class which is used to find the 2-approximation solution to a vertex cover.
 * approximationVC
 */
public class approximationVC {
    int graphSize;
    approximationVC(){}
    
    /** 
     * This method finds the vertex cover of the graph.
     * @param fullGraph this is the graph that will be used to find a vertex for it
     * @return ArrayList<Integer> this a list containing all the vertices in the vertex cover.
     */
    ArrayList<Integer> getApproxVC(graph fullGraph){
        graphSize = fullGraph.getSize();
        int edgeCount=fullGraph.getNoOfEdges();
        ArrayList<Integer> tempVals = new ArrayList<Integer>();//used to randomly select a vertex
        ArrayList<Integer> visited = new ArrayList<Integer>();
        
        for (int v = 0; v<graphSize; v++){
            tempVals.add(v);
        }//adds all vertices from graph into this ArrayList

        Collections.shuffle(tempVals);
        //edgeCount>0
        while (edgeCount>0){
            //System.out.println("First edge: "+edgeCount);    
            Random edgePick = new Random();
            int ranVer1 = tempVals.get(0);
            
            int ranVer2 = edgePick.nextInt(fullGraph.getVertexDegree(ranVer1));
            
            int ver2 = fullGraph.getEdge(ranVer1, ranVer2);
            //Finds two random end points
            visited.add(ranVer1);
            visited.add(ver2);
            //Adds both to the current solution 
            int degree=fullGraph.getVertexDegree(ranVer1);
            int degree1=fullGraph.getVertexDegree(ver2);
            edgeCount=edgeCount-degree-degree1+1;
            //Deducts the edge count depending on each endpoint's degree
            fullGraph.removeVertex(ranVer1);
            fullGraph.removeVertex(ver2);
            //Removes both endpoints from the graph
            tempVals.remove(0);
            
            tempVals.remove(tempVals.indexOf(ver2));
            //Removes the enpoints used from temporary values.
        }
        System.out.println("Vertex Cover [Approximation]: "+visited);
        return visited;
    }
}