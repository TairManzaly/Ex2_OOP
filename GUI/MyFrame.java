package GUI;

import api.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MyFrame extends JFrame implements KeyListener, ActionListener {
    private JMenuItem load;
    private JMenuItem save;
    private JMenuItem exit;
    private JMenuItem isConnected;
    private JMenuItem shortestPath;
    private JMenuItem shortestPathDist;
    private JMenuItem center;
    private JMenuItem tsp;
    private JMenuItem addNode;
    private JMenuItem connectNodes;
    private JMenuItem removeNode;
    private JMenuItem removeEdge;
    private DirectedWeightedGraphAlgorithms graph;
    MyPanel panel;


    public MyFrame(DirectedWeightedGraphAlgorithms g) {
        super();
        graph = g;
        this.setSize(Toolkit.getDefaultToolkit().getScreenSize());
        JMenuBar menuBar = new JMenuBar();
        JMenu file_menu = new JMenu("File");
        JMenu graph_menu = new JMenu("Graph");
        JMenu algo_menu = new JMenu("Algorithms");
        menuBar.add(file_menu);
        menuBar.add(graph_menu);
        menuBar.add(algo_menu);
        this.setJMenuBar(menuBar);

        load = new JMenuItem("Load");
        load.addActionListener(this);

        save = new JMenuItem("Save");
        save.addActionListener(this);
        exit = new JMenuItem("Exit");
        exit.addActionListener(this);
        file_menu.add(load);
        file_menu.add(save);
        file_menu.add(exit);

        addNode = new JMenuItem("Add Node");
        addNode.addActionListener(this);
        connectNodes = new JMenuItem("Connect Nodes");
        connectNodes.addActionListener(this);
        removeNode = new JMenuItem("Remove Nodes");
        removeNode.addActionListener(this);
        removeEdge = new JMenuItem("Remove Edge");
        removeEdge.addActionListener(this);
        graph_menu.add(addNode);
        graph_menu.add(connectNodes);
        graph_menu.add(removeNode);
        graph_menu.add(removeEdge);

        isConnected = new JMenuItem("isConnected");
        shortestPath = new JMenuItem("shortestPath");
        shortestPathDist = new JMenuItem("shortestPathDist");
        center = new JMenuItem("center");
        tsp = new JMenuItem("tsp");
        isConnected.addActionListener(this);
        shortestPath.addActionListener(this);
        shortestPathDist.addActionListener(this);
        center.addActionListener(this);
        tsp.addActionListener(this);

        algo_menu.add(isConnected);
        algo_menu.add(shortestPath);
        algo_menu.add(shortestPathDist);
        algo_menu.add(center);
        algo_menu.add(tsp);
        panel = new MyPanel(graph);
        this.add(panel);
        this.setVisible(true);


    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == load) {
            JFileChooser jFile = new JFileChooser();
            jFile.setCurrentDirectory(new java.io.File("./src"));
            int i = jFile.showOpenDialog(this);
            if (i == JFileChooser.APPROVE_OPTION) {
                File f = jFile.getSelectedFile();
                String file = f.getPath();
                this.graph.load(file);
                System.out.println("loaded file");
                this.repaint();
            }
        }
        if (e.getSource() == save) {
            JFileChooser jFile = new JFileChooser();
            int i = jFile.showSaveDialog(null);
            if (i == JFileChooser.APPROVE_OPTION) {
                File f = jFile.getSelectedFile();
                graph.save(f.getAbsolutePath() + ".json");
                System.out.println("Saved file");
            }
        }
        if (e.getSource() == exit) {
            System.exit(0);
        }
        if (e.getSource() == removeNode) {
            String location = JOptionPane.showInputDialog("Enter a node key:");
            System.out.println(graph.getGraph().nodeSize());
            this.graph.getGraph().removeNode(Integer.parseInt(location));
            System.out.println(graph.getGraph().nodeSize());
            this.repaint();
        }
        if (e.getSource() == addNode) {
            String loc = JOptionPane.showInputDialog(null, "Enter a new location(x,y):");
            System.out.println(loc);
            String[] split = loc.split(",");
            System.out.println(Arrays.toString(split));
            double x = Double.parseDouble(split[0]);
            double y = Double.parseDouble(split[1]);
            NodeData n = new Node_Data(graph.getGraph().nodeSize(), x, y, 0.0);
            this.graph.getGraph().addNode(n);
            this.repaint();
        }
        if (e.getSource() == connectNodes) {
            String location = JOptionPane.showInputDialog("Enter two node keys and weight(src,dest,weight): ");
            String[] split = location.split(",");
            this.graph.getGraph().connect(Integer.parseInt(split[0]), Integer.parseInt(split[1]), Double.parseDouble(split[2]));
            this.repaint();
        }

            if (e.getSource() == shortestPath) {
                String nodes = JOptionPane.showInputDialog("Enter source and dest(src,dest):");
                String node[] = nodes.split(",");
                List<NodeData> path = new ArrayList<>();
                path = graph.shortestPath(Integer.valueOf(node[0]), Integer.valueOf(node[1]));
                List<Integer> ans = null;
                for (NodeData i :
                        path) {
                    ans.add(i.getKey());
                }
                JOptionPane.showMessageDialog(null, "" + ans);
            }
        if (e.getSource() == isConnected) {
            boolean isConnected = this.graph.isConnected();
            JOptionPane.showMessageDialog(null, isConnected ? "The graph is connected. " : "The graph isn't connected.");
        }

        if (e.getSource() == removeEdge) {
                String loc = JOptionPane.showInputDialog( "Enter the source and the dest of the edge(source,dest): ");
                String[] split = loc.split(",");
                System.out.println(graph.getGraph().edgeSize());
                this.graph.getGraph().removeEdge(Integer.parseInt(split[0]), Integer.parseInt(split[1]));
                System.out.println(graph.getGraph().edgeSize());
                this.repaint();
            }
            if (e.getSource() == shortestPathDist) {
                String nodes = JOptionPane.showInputDialog("Enter source and dest (src,dest): ");
                String[] split = nodes.split(",");
                double dist = graph.shortestPathDist(Integer.valueOf(split[0]), Integer.valueOf(split[1]));
                JOptionPane.showMessageDialog(null, dist + "");
            }
            if (e.getSource() == center) {
                NodeData cen = (NodeData) graph.center();
                JOptionPane.showMessageDialog(null, "The node center: " + cen.getKey());
            }

            if (e.getSource() == tsp) {
                String nodes = JOptionPane.showInputDialog(null, "Enter a list of vertex: ");
                String[] split = nodes.split(",");
                List<NodeData> node = new ArrayList<>();
                for (String i : split) {
                    ;
                    node.add(graph.getGraph().getNode(Integer.parseInt(i)));
                }
                List<NodeData> nodes_tsp = graph.tsp(node);
                ArrayList ans = new ArrayList();
                for (NodeData i : nodes_tsp
                ) {
                    ans.add(i.getKey());
                }
                JOptionPane.showMessageDialog(null, "The path is: " + ans);

            }
        }




        public static void main (String[]args){
            MyFrame frame = new MyFrame(new Directed_Weighted_Graph_AlgorithmsI());
            frame.setVisible(true);
        }




    @Override
    public void keyPressed(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    @Override
    public void keyTyped(KeyEvent e) {

    }
}
