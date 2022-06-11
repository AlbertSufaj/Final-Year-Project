package vertexcover;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import guru.nidi.graphviz.engine.Format;
import guru.nidi.graphviz.engine.Graphviz;
import guru.nidi.graphviz.model.MutableGraph;
import guru.nidi.graphviz.parse.Parser;
import javafx.scene.text.HitInfo;

import static guru.nidi.graphviz.model.Factory.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;


class graph{
    private ArrayList<ArrayList<Integer>> adjList;
    private int graphSize;
    private String edgeChecked;
    private int highestDegree;

    private ArrayList<Integer> multHDegree;

    public graph(int noOfVertices){
        //Constructor also creates the graph.
        adjList = new ArrayList<ArrayList<Integer>>(noOfVertices);
        multHDegree = new ArrayList<Integer>();
        for(int i = 0; i<noOfVertices; i++){
            adjList.add(new ArrayList<Integer>()); //Adding a new vertex i to the graph.
        }
        setAdjList(adjList);
        graphSize = noOfVertices;
        edgeChecked="true";
    }

    //Second constructor just in case it has already been created.
    public graph(ArrayList<ArrayList<Integer>> conAdjList){
        setAdjList(conAdjList);
    }

    /** 
     * This method allows an edge to be added to the graph.
     * @param v1 one of the endpoints of the edge (v1, v2)
     * @param v2 one of the endpoints of the edge (v1, v2)
     */
    void addEdge(int v1, int v2){
        //Adds an edge in and out of the vertices.
        edgeChecked="true";
        if(!((v1>=getSize() || v2>=getSize())||(v1<0||v2<0))){
            //if statement to stop user adding to nodes which do not exist.
            if (!(getAdjList().get(v1).contains(v2))){
                getAdjList().get(v1).add(v2);
                getAdjList().get(v2).add(v1);
                
            //if statement to stop duplicate edges.
            }
            else{
                edgeChecked="false";
            }
        }
        else{
            edgeChecked="nope";
        }
    }

    /** 
     * This method deletes any specific edge on the graph.
     * @param v1 one of the endpoints of the edge (v1, v2)
     * @param v2 one of the endpoints of the edge (v1, v2)
     */
    void deleteEdge(int v1, int v2){ 
        getAdjList().get(v1).remove(getAdjList().get(v1).indexOf(v2));
        getAdjList().get(v2).remove(getAdjList().get(v2).indexOf(v1));    
    }

    
    /** 
     * @throws IOException
     */
    void printGraph() throws IOException{
        //String stringPrint = "";
        File dotFile = new File("C:/Users/alber/Documents/GitHub/GraphGui.gv");
        FileWriter fw = new FileWriter(dotFile);
        PrintWriter pw = new PrintWriter(fw);
        ArrayList<Integer> visited = new ArrayList<Integer>();
        pw.println("graph{");
        pw.println("    layout=neato");
        for (int i = 0; i < getAdjList().size(); i++) {
           //stringPrint+=" Node "+i+":";
            for( int j = 0; j < getAdjList().get(i).size(); j++){ //Nested for loop to see each edge connected to the vertex
                if(!visited.contains(getAdjList().get(i).get(j))){
                    pw.println("    "+i+"--"+getAdjList().get(i).get(j));
            //        stringPrint+=" "+getAdjList().get(i).get(j)+"\n";
                }
            }
            visited.add(i);
        }
        pw.println("}");
        pw.close();
    }

    
    /** This method takes the solution and creates .dot file which is then converted to a .png
     * @param VC list containing the solution
     * @throws IOException needed to allow file writer to work
     */
    void printVCGraph(ArrayList<Integer> VC, String dif) throws IOException{
        File dotFile = new File("C:/Users/alber/Documents/GitHub/GraphGui.gv");
        FileWriter fw = new FileWriter(dotFile);
        PrintWriter pw = new PrintWriter(fw);
        ArrayList<Integer> visited = new ArrayList<Integer>();
        pw.println("graph{");
        pw.println("    //layout=neato");
        for (int i = 0; i < getAdjList().size(); i++) {
            for( int j = 0; j < getAdjList().get(i).size(); j++){ //Nested for loop to see each edge connected to the vertex
                if(!visited.contains(getAdjList().get(i).get(j))){
                    pw.println("    "+i+"--"+getAdjList().get(i).get(j)+" [color=red]");
                }
            }
            visited.add(i);
        }
        for(int x = 0; x<VC.size();x++){
            pw.println("    "+VC.get(x)+" [color=red]");
        }
        pw.println("}");
        pw.close();
        try {
            FileInputStream dot = new FileInputStream("C:/Users/alber/Documents/GitHub/GraphGui.gv");
            MutableGraph g = new Parser().read(dot);
            Graphviz.fromGraph(g).width(125).render(Format.PNG).toFile(new File("C:/Users/alber/Documents/GitHub/GRAPH"+dif+".png"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    

    /** 
     * This method finds the number of edges in the graph.
     * @return int value containing the total number of edges
     */
    int getNoOfEdges(){
        int edgeTotal=0; //Count variable which returns the sum off all edges
        for (int i = 0; i<getAdjList().size(); i++){
            edgeTotal+= getAdjList().get(i).size(); //counting the number of edges connected to each vertex.
        }
        edgeTotal = edgeTotal/2; 
        //Since the graph is undirected, we divide it by two since there will an in and an out edge from the same vertex.
        return edgeTotal;
    }

    /** 
     * Simple egtter which gets the adjacency list of thr graph
     * @return ArrayList<ArrayList<Integer>> this is the adjacency list representing the graph
     */
    ArrayList<ArrayList<Integer>> getAdjList(){
        return adjList; //returns the graph
    }
    
    /** 
     * Simple setter to change the adjacency list in any way.
     * @param newAdjList the new adjacency list to be set as the current adjacency list
     */
    void setAdjList(ArrayList<ArrayList<Integer>> newAdjList){
        this.adjList = newAdjList; //sets the previous graph to the new graph
    }

    /** 
     * This method removes any vertex on the graph and all its corresponding edges.
     * @param v the vertex to be removed from the graph
     */
    void removeVertex(int v){
        //System.out.print("Removing "+v+"...");
        for(int i = 0; i<getAdjList().size();i++){
            if(getAdjList().get(i).contains(v)){
                deleteEdge(i, v); //call delete edge on all edges attached to v
            }
        }
    }
    
    /** 
     * This retrieves any edge on the graph.
     * @param u one of the endpoints of the edge
     * @param v the other endpoint of the edge
     * @return int the edge as an integer
     */
    public int getEdge(int u, int v){
        return getAdjList().get(u).get(v);
    }

    
    /** 
     * Gets the degree of a vertex.
     * @param u the vertex 
     * @return int the degree of u
     */
    public int getVertexDegree(int u){
        return getAdjList().get(u).size();
    }

    
    /** 
     * Retrieves the vertex with the highest degree.
     * @return int the vertex as integer
     */
    public int getHighestDegree(){
        int highestDegree=0;
        int vertex=0;
        for(int i = 0; i < graphSize; i++){
            if (highestDegree <= getVertexDegree(i)){
                highestDegree = getVertexDegree(i);
                vertex = i; //Simple way to find the highest number in a list ny just replacing it if the number increases
            }
        }
        setHighestDegree(vertex);
        return vertex;
    }
    
    /** 
     * Specifies the size of the graph.
     * @return int the size of the graph
     */
    public int getSize(){
        return getAdjList().size();
    }

    
    /** 
     * Converts .dot file into .png file
     * @throws IOException thrown for improper user input
     */
    void makeGuiGraph() throws IOException {
        try {
            FileInputStream dot = new FileInputStream("C:/Users/alber/Documents/GitHub/GraphGui.gv");
            MutableGraph g = new Parser().read(dot);
            Graphviz.fromGraph(g).width(125).render(Format.PNG).toFile(new File("C:/Users/alber/Documents/GitHub/GRAPH.png"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /** 
     * Removes the neighbours of a vertex.
     * @param vertex the vertex witg neighbours to be removed
     */
    void removeNeighbours(int vertex){
        for(int i = 0; i < getVertexDegree(vertex); i++){
            removeVertex(getAdjList().get(vertex).get(i));//calls remove vertex on every element of the vertex ArrayList
        }
    }
    
    /** 
     * A simple getter to validate of adding edges.
     * @return String containing three states specifying the type of error
     */
    String checkEdge(){
        return edgeChecked;
    }

    /** 
     * An attempt at trying to retrieve the total of a vertex's heighbourhood.
     * @param v the vertex
     * @return int the degree of all of v's neighbours
     */
    int getNeighboursDegree(int v){
        int count =0;
        for (int i = 0; i<getAdjList().get(v).size();i++){
            //int neighVer = getEdge(v, i);
            int neighVer = getAdjList().get(v).get(i);
            count+=getVertexDegree(neighVer);
            /*/if((getAdjList().get(neighVer).contains(v))){
                count--;
            }/*/
        }
        System.out.println("Neighbour Count: "+count);
        return count; 
    }
    
    /** 
     * Adds all the vertices with the same highest degree to an ArrayList.
     * @return ArrayList<Integer> contains all the vertices with the same degree
     */
    ArrayList<Integer> getHighDegree(){
        for(int i =0; i<getAdjList().size(); i++){ //loops through graph to find highest degree
            if(getAdjList().get(i).size()==highestDegree){
                multHDegree.add(i); //if there is a match in highest, it is added here
            }
        }
        return multHDegree;
    }
    
    /** 
     * This is to be used in conjunction with getHighDegree() to set the highest vertex
     * @param highest vertex with highest degree
     */
    void setHighestDegree(int highest){
        highestDegree = highest;
    }

    
    /** 
     * A simple setter which sets the ArrayList of the highest degrees.
     * @param newArray the new Array containing user specified elements.
     */
    void setDegreeArray(ArrayList<Integer> newArray){
        multHDegree = newArray;
    }
}