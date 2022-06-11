package vertexcover;

import javax.swing.*;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

class graphGui implements ActionListener {
    JFrame mainFrame;
    
    JButton fullGraphButton;
    JButton approxButton;
    JButton heurButton;
    JButton alomButton;
    JButton saveButton;

    JTextField inputNoNodes;
    JButton submitNodes;

    JTextField inputEdges;
    JTextField inputEdges2;
    JButton submitEdge;
    
    JPanel panel;
    JPanel panel2;

    JLabel lbl;
    JLabel edges;
    JLabel lblApprox;
    JLabel lblHeur;
    JLabel lblBack;
    JLabel errorMessage;

    ImageIcon image;

    JCheckBox checkA;
    JCheckBox checkH;
    JCheckBox checkB;
    String checked;
    JFileChooser fileToSave;

    //Graph things
    graph newGraph;
    graph copyGraph;
    ArrayList<ArrayList<Integer>> adj;
    ArrayList<ArrayList<Integer>> adj2;

    String edgesAdded;
    public static void main(String[] args) throws MalformedURLException, IOException {
        new graphGui();
    }

    public graphGui () throws MalformedURLException, IOException{
        JScrollBar vbar=new JScrollBar(JScrollBar.VERTICAL, 30, 40, 0, 500);
        
        mainFrame = new JFrame("Vertex Cover");
        mainFrame.setMinimumSize(new Dimension(300, 700));
        
        //mainFrame.setMaximumSize(new Dimension(300, 1000));
        
        //Buttons
        fullGraphButton = new JButton("Full Graph");
        heurButton = new JButton("Heuristic");
        approxButton = new JButton("Approximation");
        alomButton = new JButton("Alom's");
        saveButton = new JButton("Save");

        panel = new JPanel();
        panel.setBorder(BorderFactory.createEmptyBorder(30,30,10,30));

        mainFrame.add(vbar);
        
        lbl = new JLabel();
        lblApprox =new JLabel();
        lblHeur = new JLabel();
        lblBack = new JLabel();

        //Input no of nodes
        inputNoNodes = new JTextField(16);
        inputNoNodes.setMaximumSize(new Dimension(200,20));

        //Input edges
        inputEdges = new JTextField("V1V2");
        inputEdges.setMaximumSize(new Dimension(100,20));

        inputEdges.setBounds(100, 100, 100, 100);
        
        //Image tbc.
        edges = new JLabel();
        edgesAdded = "Edges: ";
        
        errorMessage = new JLabel();
        
        //Number of nodes selection
        JLabel lblNoNodes = new JLabel("Number of Nodes:");
        panel.add(lblNoNodes);
        panel.add(inputNoNodes);
        submitNodes = new JButton("Create Graph");

        //Checkboxes
        checkA = new JCheckBox("Approximation");
        checkH = new JCheckBox("Heuristic");
        checkB = new JCheckBox("Backtrack");
        fileToSave = new JFileChooser();
        fileToSave.setDialogTitle("Select Folder to save solution");
        
        NodesButtonAction();

        panel.add(Box.createRigidArea(new Dimension(0,5)));
        panel.add(submitNodes);
        
        //Edges
        submitEdge = new JButton("Add Edge");
        panel.add(Box.createRigidArea(new Dimension(0,5)));
        panel.add(inputEdges);
        panel.add(Box.createRigidArea(new Dimension(0,5)));
        panel.add(edges);
        panel.add(submitEdge);

        EdgeButtonAction();

        panel.add(errorMessage);
        panel.add(new JLabel("====================================================="));

        //Buttons
        panel.add(fullGraphButton);
        panel.add(lbl);
        panel.add(approxButton);
        panel.add(lblApprox);
        panel.add(heurButton);
        panel.add(lblHeur);
        panel.add(alomButton);
        panel.add(lblBack);

        fullGraphButtonAction();
        approxButtonAction();
        heurButtonAction();
        alomButtonAction();
        saveButtonAction();
        
        panel.add(new JLabel("====================================================="));

        //panel.add(checkA);
       //panel.add(checkH);
        //panel.add(checkB);
       // panel.add(saveButton);
        
        panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));

        //checkAAction();
        //checkHAction();
        //checkBAction();

        mainFrame.add(panel);
        mainFrame.setLocationRelativeTo(null);
        mainFrame.pack();
        mainFrame.setVisible(true);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        
    }

    void checkAAction(){
        checkA.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                checked = "approximation";
            }
        });
    }
    void checkHAction(){
        checkH.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                checked = "heuristic";
            }
        });
    }
    void checkBAction(){
        checkB.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                checked = "backtrack";
            }
        });
    }

    void EdgeButtonAction(){
        submitEdge.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                newGraph.addEdge(Integer.parseInt(Character.toString(inputEdges.getText().charAt(0))), Integer.parseInt(Character.toString(inputEdges.getText().charAt(1))));
                copyGraph.addEdge(Integer.parseInt(Character.toString(inputEdges.getText().charAt(0))), Integer.parseInt(Character.toString(inputEdges.getText().charAt(1))));
                switch(newGraph.checkEdge()){
                    case "true":
                        edgesAdded += Character.toString(inputEdges.getText().charAt(0))+"-"+Character.toString(inputEdges.getText().charAt(1))+", ";
                        edges.setText(edgesAdded);
                        break;
                    case "false":
                        errorMessage.setText("Edge already added!");
                        break;
                    case "nope":
                        errorMessage.setText("Node does not exist!");
                        break;
                }
            }
        });
    }

    void NodesButtonAction(){
        submitNodes.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                newGraph = new graph(Integer.parseInt(inputNoNodes.getText()));
                copyGraph = new graph(Integer.parseInt(inputNoNodes.getText()));
                JOptionPane.showMessageDialog(mainFrame, "Graph Created with "+inputNoNodes.getText()+" nodes");
            }
        });
    }

    void fullGraphButtonAction(){
        fullGraphButton.addActionListener(new java.awt.event.ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                //lbl.setText(newGraph.printGraph());
                try { 
                    copyGraph.printGraph();
                    copyGraph.makeGuiGraph();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
                ImageIcon image = new ImageIcon("C:/Users/alber/Documents/GitHub/GRAPH.png");
                lbl.setIcon(image);
            }
        });
    }
    void heurButtonAction(){
        heurButton.addActionListener(new java.awt.event.ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                //lbl.setText(newGraph.printGraph());
                try { 
                    heuristicVC v = new heuristicVC();
                    copyGraph.printVCGraph(v.getHeuristicVC(newGraph),"h");
                    copyGraph.makeGuiGraph();
                } catch (IOException e1) {
                    e1.printStackTrace();
                    JOptionPane.showMessageDialog(mainFrame, "Error finding Vertex Cover!");
                }
                ImageIcon imageV = new ImageIcon("C:/Users/alber/Documents/GitHub/GRAPHh.png");
                lblHeur.setIcon(imageV);
                System.out.println(newGraph.getAdjList());
                System.out.println(copyGraph.getAdjList());
            }
        });
    }
    void approxButtonAction(){
        approxButton.addActionListener(new java.awt.event.ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                //lbl.setText(newGraph.printGraph());
                try { 
                    approximationVC a= new approximationVC();
                    copyGraph.printVCGraph(a.getApproxVC(newGraph), "a");
                    copyGraph.makeGuiGraph();
                } catch (IOException e1) {
                    e1.printStackTrace();
                    JOptionPane.showMessageDialog(mainFrame, "Error finding Vertex Cover!");
                }
                ImageIcon imageA = new ImageIcon("C:/Users/alber/Documents/GitHub/GRAPHa.png");
                lblApprox.setIcon(imageA);
                System.out.println(newGraph.getAdjList());
                System.out.println(copyGraph.getAdjList());
                //lbl.setIcon(image);

            }
        });
    }
    void alomButtonAction(){
        alomButton.addActionListener(new java.awt.event.ActionListener(){

            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    alomVC aV = new alomVC();
                    copyGraph.printVCGraph(aV.getAlomVC(newGraph), "b");
                } catch (Exception e1) {
                    
                }
                ImageIcon imageB = new ImageIcon("C:/Users/alber/Documents/GitHub/GRAPHb.png");
                lblBack.setIcon(imageB);
                System.out.println(newGraph.getAdjList());
                System.out.println(copyGraph.getAdjList());
            }
            
        });
    }

    void saveButtonAction(){
        
        saveButton.addActionListener(new java.awt.event.ActionListener(){
            
            @Override
            public void actionPerformed(ActionEvent e){
                String temp = "";
                try{
                    switch(checked){
                        case "approximation":
                            approximationVC a= new approximationVC();
                            copyGraph.printVCGraph(a.getApproxVC(copyGraph), "a");
                            temp="a";
                            break;
                        case "heuristic":
                            heuristicVC v = new heuristicVC();
                            copyGraph.printVCGraph(v.getHeuristicVC(copyGraph),"h");
                            temp="h";
                            break;
                        case "backtrack":
                            break;
                    }
                } catch(Exception e1){
                    e1.printStackTrace();
                }
                fileToSave.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
                fileToSave.showSaveDialog(mainFrame);
                //File newFile = 
                //System.out.println(newFile);

                Path l = Paths.get("C:/Users/alber/Documents/GitHub/GRAPH"+temp+".png");
                //Path n = Paths.get(FileUtils.readFileToString(fileToSave.getSelectedFile()));
                Path t = Paths.get(fileToSave.getSelectedFile().getAbsolutePath()+"/lol.png");
                try {
                    Files.copy(l, t);
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
        });
    }
}