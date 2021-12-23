import GUI.MyFrame;
import GUI.*;
import api.DirectedWeightedGraph;
import api.DirectedWeightedGraphAlgorithms;
import api.Directed_Weighted_Graph;
import api.Directed_Weighted_Graph_AlgorithmsI;


import javax.swing.*;

/**
 * This class is the main clas for Ex2 - your implementation will be tested using this class.
 */
public class Ex2 {
    /**
     * This static function will be used to test your implementation
     *
     * @param json_file - a json file (e.g., G1.json - G3.gson)
     * @return
     */
    public static DirectedWeightedGraph getGrapg(String json_file) {
        // ****** Add your code here ******
        DirectedWeightedGraphAlgorithms ans = new Directed_Weighted_Graph_AlgorithmsI();
        ans.load(json_file);
        // ********************************
        return ans.getGraph();
    }

    /**
     * This static function will be used to test your implementation
     *
     * @param json_file - a json file (e.g., G1.json - G3.gson)
     * @return
     */
    public static DirectedWeightedGraphAlgorithms getGrapgAlgo(String json_file) {
        // ****** Add your code here ******
        DirectedWeightedGraphAlgorithms ans = new Directed_Weighted_Graph_AlgorithmsI();
        ans.load(json_file);
        // ********************************
        return ans;
    }

    /**
     * This static function will run your GUI using the json fime.
     *
     * @param json_file - a json file (e.g., G1.json - G3.gson)
     */
    public static void runGUI(String json_file) {
        DirectedWeightedGraphAlgorithms alg = getGrapgAlgo(json_file);
        // ****** Add your code here ******
        //
        {
            MyFrame frame = new MyFrame(alg);
            frame.setVisible(true);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

            // ********************************
        }
    }

    public static void main(String[] args) {
        runGUI(args[0]);

    }
}
