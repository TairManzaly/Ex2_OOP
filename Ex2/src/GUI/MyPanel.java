package GUI;

import api.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Random;
import java.util.prefs.NodeChangeEvent;
import java.util.List;
import java.util.*;
public class MyPanel extends JPanel implements MouseListener
{
    private DirectedWeightedGraphAlgorithms graph;
    String message;
    ArrayList<NodeData> nodes;
    ArrayList<EdgeData> edges;



    public MyPanel(DirectedWeightedGraphAlgorithms graph){
        super();
        this.graph =  graph;
        this.addMouseListener(this);
        this.setBackground(Color.WHITE);
    }

//    void reset(){
//        points = new LinkedList<Point2D>();
//        this.repaint();
//    }






    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.white);
        nodes = new ArrayList<>();
        edges = new ArrayList<>();
        Iterator iNode = graph.getGraph().nodeIter();
        Iterator iEdge = graph.getGraph().edgeIter();
        while (iNode.hasNext()) {
            nodes.add((NodeData) iNode.next());
        }
        while ((iEdge.hasNext())) {
            edges.add((EdgeData) iEdge.next());
        }

        System.out.println(edges);
        System.out.println(nodes);
        g.setFont(new Font("MV", Font.PLAIN, 25));
        g.setColor(Color.black);
        double minX = Double.MAX_VALUE, minY = Double.MAX_VALUE;
        double maxX = Double.MIN_VALUE, maxY = Double.MIN_VALUE;

        for (int i = 0; i < nodes.size(); i++) {
            NodeData n = (NodeData) nodes.get(i);
            if (n.getLocation().x() > maxX) {
                maxX = n.getLocation().x();
            }
            if (n.getLocation().x() < minX) {
                minX = n.getLocation().x();
            }
            if (n.getLocation().y() > maxY) {
                maxY = n.getLocation().y();
            }
            if (n.getLocation().y() < minY) {
                minY = n.getLocation().y();
            }
        }
        double absX = Math.abs(maxX - minX);
        double absY = Math.abs(maxY - minY);
        double scaleX = (getSize().getWidth() / absX) * 0.9;
        double scaleY = (getSize().getHeight() / absY) * 0.9;
        for (int i = 0; i < nodes.size(); i++) {
            g.setColor(new Color(220, 234, 26));
            NodeData n = (NodeData) nodes.get(i);
            n.setLocation(new Geo_LocationI(((n.getLocation().x() - minX) * scaleX * 0.9 + 32), ((n.getLocation().y() - minY) * scaleY * 0.9 + 30), 0));
        }
            for (int i = 0; i < edges.size(); i++) {
                EdgeData e = (EdgeData) edges.get(i);
                NodeData dest = (NodeData) nodes.get(e.getDest());
                NodeData src = (NodeData) nodes.get(e.getSrc());
//            double src_x = (src.getLocation().x() * scaleX) % getSize().getWidth();
//            double src_y = (src.getLocation().y() * scaleY) % getSize().getHeight();
//            double dest_x = (dest.getLocation().x() * scaleX) % getSize().getWidth();
//            double dest_y = (dest.getLocation().y() * scaleY) % getSize().getHeight();
                double src_x = (src.getLocation().x());
                double src_y = (src.getLocation().y());
                double dest_x = (dest.getLocation().x());
                double dest_y = (dest.getLocation().y());
                Graphics2D g2 = (Graphics2D) g;
                g2.setStroke(new BasicStroke(3));
                g2.setColor(Color.black);
                g2.draw(new Line2D.Float((int) src_x, (int) src_y, (int) dest_x, (int) dest_y));

            }

            //Point2D prev = null;
            //   DirectedWeightedGraph prev = new Directed_Weighted_Graph();

//        for (Point2D p : points)
//        {
//            g.setColor(Color.WHITE);
//            g.fillOval((int)p.getX()-10, (int)p.getY()-10, 10, 10);
//
//            if(prev != null)
//            {
//                Double dist = p.distance(prev);
//                String distS = dist.toString().substring(0,dist.toString().indexOf(".")+2);
//                g.setColor(Color.RED);
//                g.drawLine((int)p.getX(), (int)p.getY(),
//                        (int)prev.getX(), (int)prev.getY());
//                g.setFont(new Font("MV Boli",Font.TRUETYPE_FONT,15)); //set font of text
//                g.drawString(distS, (int)((p.getX()+prev.getX())/2),(int)((p.getY()+prev.getY())/2));
//            }
//
//            prev = p;
//        }

        for (int i = 0; i < nodes.size(); i++) {
            g.setColor(Color.gray);
            NodeData n = (NodeData) nodes.get(i);
            g.fillOval((int) n.getLocation().x() - 7, (int) n.getLocation().y() - 7, 15, 15);
        }
    }

    public void displayHelp() {
        message = "To add a Node Press on the Screen";
        repaint();
    }
    @Override
    public Dimension getPreferredSize() {
        return new Dimension(5, 6);
    }
    private static void createAndShowGui() {
        List<Integer> scores = new ArrayList<>();
        Random random = new Random();
        int maxDataPoints = 16;
        int maxScore = 20;
        for (int i = 0; i < maxDataPoints; i++) {
            scores.add(random.nextInt(maxScore));
        }

        JFrame frame = new JFrame("DrawGraph");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //frame.getContentPane().add(mainPanel);
        frame.pack();
        frame.setLocationByPlatform(true);
        frame.setVisible(true);
    }
    @Override
    public void paint(Graphics g) {
        super.paint(g);
        paintComponent(g);
    }


    @Override
    public void repaint() {
        super.repaint();
    }
    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
