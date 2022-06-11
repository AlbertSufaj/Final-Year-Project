package vertexcover;

import java.util.ArrayList;

public class vertexCover {
    vertexCover(){}
    
    /** 
     * @param visited
     * @param Full
     * @return boolean
     */
    boolean checkVC(ArrayList<Integer> visited, graph Full){
        boolean checkOver=true;
        for (int i=0;i<Full.getSize();i++){
            if (!visited.contains(i)){
                if(!visited.containsAll(Full.getAdjList().get(i))){
                    return false;
                }
                else{
                    checkOver=true;
                }
            }
        }
        return checkOver;
    }

    //look at vc as optimisation problem.

    //https://stackoverflow.com/questions/7034/graph-visualization-library-in-javascript
    //https://graphviz.org/ - look for coprresponding framework.

    /** 
     * This methods creates the vertex cover graph
     * @param VCList list of all vertices in the vertex cover
     * @param fullGraph a copy of the original graph so the vertex cover graph can be made
     * @return graph the vertex graph
     */
    graph makeVCGraph(ArrayList<Integer> VCList, graph fullGraph){
        ArrayList<ArrayList<Integer>> temp = new ArrayList<ArrayList<Integer>>();
        graph VCGraph = new graph(temp);
        for (int i = 0; i<VCList.size(); i++){
            VCGraph.getAdjList().add(fullGraph.getAdjList().get(VCList.get(i)));
        }
        return VCGraph;
    }
}
