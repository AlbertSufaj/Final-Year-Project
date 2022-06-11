package vertexcover;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class graphTest {
    graph testGraph = new graph(6);
    graph VCGraph = new graph(4);
    graph graphCopy = new graph(6);
    
    //Test 0 - Adding all the edges for testing certain graphs and if they're a vertex cover.
    @BeforeEach
    void testAddEdge(){
        
        testGraph.addEdge(0, 1);
        testGraph.addEdge(0, 3);
        

        testGraph.addEdge(1, 2);
        testGraph.addEdge(1, 3);
        testGraph.addEdge(1, 4);
        
        testGraph.addEdge(2, 4);
        testGraph.addEdge(2, 5);
       
        testGraph.addEdge(3, 4); 

        testGraph.addEdge(4, 5);

        VCGraph.addEdge(0, 1);
        VCGraph.addEdge(0, 3);

        VCGraph.addEdge(1, 2);
        VCGraph.addEdge(1, 3);
        
        graphCopy.addEdge(0, 1);
        graphCopy.addEdge(0, 3);

        graphCopy.addEdge(1, 2);
        graphCopy.addEdge(1, 3);
        graphCopy.addEdge(1, 4);
        
        graphCopy.addEdge(2, 4);
        graphCopy.addEdge(2, 5);

        graphCopy.addEdge(3, 4);

        graphCopy.addEdge(4, 5);
    }

    //Test 1 - testing to check if graph is created with printGraph() function.
    @Test
    public void testPrintGraphWithDelEdge() throws IOException{
        testGraph.printGraph();
        //System.out.println(testGraph.getAdjList());
        testGraph.deleteEdge(2, 5);
        testGraph.printGraph();
        //System.out.println(testGraph.getAdjList());

    }
    
    //Test 2 - testing the number of edges for testGraph.
    @Test
    public void testNoOfEdges(){
        assertEquals(testGraph.getNoOfEdges(), 9, "Testing if the method returns the correct no. of edges: 9");
    }

    //Test 3 - testing the number of edges for VCGraph.
    @Test
    public void testVCNoOfEdges(){
        assertEquals(VCGraph.getNoOfEdges(), 4, "Testing if method returns correct no. of edges: 4");
    }
    
    //Test 4 - testing a false VC on testGraph.
    @Test
    public void testFalseVC(){
        vertexCover VC = new vertexCover();
        ArrayList<Integer> visited = new ArrayList<Integer>();
        visited.add(0);
        visited.add(3);
        visited.add(1);
        //assertEquals(VC.checkVC(VCGraph, testGraph), false, "Testing if the method returns the correct boolean: false");
        assertEquals(VC.checkVC(visited, testGraph), false, "Testing if method returns a valid solution.");

    }
    //Test 5 - testing a vertex cover against testGraph.
    @Test
    public void testTrueVC(){
        vertexCover VC = new vertexCover();
        approximationVC test1 = new approximationVC();
        ArrayList<Integer> VCList = test1.getApproxVC(testGraph);
        //graph finalG = VC.makeVCGraph(VCList, graphCopy);
        assertEquals(VC.checkVC(VCList, graphCopy), true, "Testing if the method returns the correct boolean: true");
    }


    //Test 6 - testing if the remove vertex removes the intended vertex
    @Test
    public void testRemoveVertex() throws IOException{
        testGraph.printGraph();
        //testGraph.removeVertex(1);
        //testGraph.printGraph();
    }

 
    //Test 7 - tests if the approximation approach creates a vertex cover
    @Test
    public void testApproxVC() throws IOException{
        vertexCover VC = new vertexCover();
        approximationVC test1 = new approximationVC();
        ArrayList<Integer> VCList = test1.getApproxVC(testGraph);
        //graph finalG = VC.makeVCGraph(VCList, graphCopy);
        assertEquals(VC.checkVC(VCList, graphCopy), true, "Testing if the method returns the correct boolean: true");
        //graphCopy.printVCGraph(VCList, "a");
    }

    //Test 8 - tests to see if highest degree is correct
    @Test
    public void testHighestDegree(){
        assertEquals(graphCopy.getHighestDegree(), 4);
    }

    
    //Test 9 - Testing tehe heuristic appraoch
    @Test
    public void testHeuristicVC() throws IOException{
        vertexCover VC = new vertexCover();
        heuristicVC test1 = new heuristicVC();
        ArrayList<Integer> VCList = test1.getHeuristicVC(testGraph);
        //graph finalG = VC.makeVCGraph(VCList, graphCopy);
        //finalG.printGraph();

        assertEquals(VC.checkVC(VCList, graphCopy), true, "Testing if the method returns the correct boolean: true");
        //graphCopy.printVCGraph(VCList, "h");
        //graphCopy.makeGuiGraph();
        //finalG.printGraph();
    }

    //Test 10 - Testing if the .dot is created successfully
    @Test
    public void testVCGui() throws IOException{
        approximationVC a = new approximationVC();
        graphCopy.printVCGraph(a.getApproxVC(testGraph), "a");
        graphCopy.makeGuiGraph();
    }

    //Test 11 - testing if Alom's algorithm works
    @Test
    public void testAlom(){
        alomVC a = new alomVC();
        vertexCover VC = new vertexCover();
        //b.getBacktrackVC(graphCopy, testGraph, visited, edgeCount, ver);
        //b.getBacktrackVC(testGraph, graphCopy, edgeCount, ver, visited);
        
        assertEquals(VC.checkVC(a.getAlomVC(testGraph), graphCopy), true, "Testing if the method returns the correct boolean: true");
    }
}