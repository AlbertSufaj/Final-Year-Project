package vertexcover;

import java.util.ArrayList;
import java.util.Arrays;

import org.junit.gen5.engine.support.descriptor.EngineDescriptor;

public class backtrackVC {
    
    backtrackVC(){
        this.v = new vertexCover();
    }

    private ArrayList<Integer> visited;
    private vertexCover v;

    /*/public void getBacktrackVC(graph copyGraph, graph fullGraph, ArrayList<Integer> visited, int edgeCount, int highest){

        if (edgeCount<=0 || visited.size() >= copyGraph.getSize()){
            return;
        }
        System.out.println("Edge Count: "+edgeCount+" Current Highest: "+highest+"\n");

        if(!((visited.size()==0)&&edgeCount<=0)){fullGraph.removeVertex(highest);}
        System.out.println(fullGraph.getAdjList());

        //int highest = fullGraph.getHighestDegree();

        if(!v.checkVC(visited, copyGraph)==false|| !visited.contains(highest)){
            visited.add(highest);
            edgeCount = edgeCount - fullGraph.getVertexDegree(highest);
            System.out.print("Before 1st: "+edgeCount+"\n");
            highest = fullGraph.getHighestDegree();
            getBacktrackVC(copyGraph, fullGraph, visited, edgeCount, highest);
            
            visited.remove(visited.indexOf(highest));
            edgeCount+= fullGraph.getVertexDegree(highest);
            for(int i =0 ; i<fullGraph.getVertexDegree(highest); i++){
                edgeCount=edgeCount - fullGraph.getNeighboursDegree(highest);
                visited.add(fullGraph.getAdjList().get(highest).get(i));
            }
            fullGraph.removeNeighbours(highest);
            System.out.print("Before 2nd: "+edgeCount);
            getBacktrackVC(copyGraph, fullGraph, visited, edgeCount, highest);
        }
        //Arrays.stream(visited).distinct().toArray();
        setVisited(visited);
        System.out.println("Backtrack: "+visited);
    }/*/

    ArrayList<Integer> getVisited(){
        return visited;
    }

    void setVisited(ArrayList<Integer> vTemp){
        visited = vTemp;
    }

    void getBacktrackVC(graph Full, graph Copy, int edgeCount, int highest, ArrayList<Integer> visited){
        System.out.println("Beginning Array: "+Full.getAdjList());
        System.out.println("Edge:"+edgeCount);
        System.out.println("Highest: "+highest);
        System.out.println("Visited: "+visited);
        if (edgeCount>0){
            if (visited.contains(highest)){
                visited.addAll(Full.getAdjList().get(highest));
                edgeCount=edgeCount-Full.getNeighboursDegree(highest);
                Full.removeNeighbours(highest);
                int newHighest = Full.getHighestDegree();
                System.out.println("Before 1st, Highest: "+newHighest);
                System.out.println(Full.getAdjList());
                getBacktrackVC(Full, Copy, edgeCount, newHighest, visited);
            }
            else{
                visited.add(highest);
                edgeCount=edgeCount-Full.getVertexDegree(highest);
                Full.removeVertex(highest);
                int newHighest = Full.getHighestDegree();
                System.out.println("Before 2nd, Highest: "+newHighest);
                System.out.println(Full.getAdjList());
                getBacktrackVC(Full, Copy, edgeCount, newHighest, visited);
            }
        }
        else{
            System.out.print("Backtrack: "+visited);
            setVisited(visited);
        }
    }

    void test(graph Full, graph Copy, int edgeCount, int highest, ArrayList<Integer> visited, String rec){
        if(edgeCount>0){
            switch (rec){
                case "start":
                    test(Full, Copy, edgeCount, highest, visited, "vertex");
                    test(Full, Copy, edgeCount, highest, visited, "neighbours");
                case "neighbours":
                    if(visited.contains(highest)){
                        visited.addAll(Full.getAdjList().get(highest));
                        System.out.println("1st"+edgeCount+" H:"+highest);
                        edgeCount=edgeCount-Full.getNeighboursDegree(highest);
                        //Full.removeNeighbours(highest);
                        //int newHighest = Full.getHighestDegree();
                        //System.out.println("Before 1st, Highest: "+newHighest);
                        //System.out.println(Full.getAdjList());
                    }
                case "vertex":
                    if(!visited.contains(highest)){
                        visited.add(highest);
                        System.out.println("2nd: "+edgeCount+" H:"+highest);
                        edgeCount=edgeCount-Full.getVertexDegree(highest);
                        //Full.removeVertex(highest);
                        //int newHighest = Full.getHighestDegree();
                        //System.out.println("Before 2nd, Highest: "+newHighest);
                        System.out.println(Full.getAdjList());
                        //getBacktrackVC(Full, Copy, edgeCount, newHighest, visited);
                    }
            }
            int newHighest = Full.getHighestDegree();
            System.out.println(visited);
            test(Full, Copy, edgeCount, newHighest, visited, "vertex");
            test(Full, Copy, edgeCount, newHighest, visited, "neighbours");
        }
        
        setVisited(visited);
    }
}
