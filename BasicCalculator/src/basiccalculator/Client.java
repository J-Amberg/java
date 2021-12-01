package basiccalculator;

import javax.swing.JFrame;

/**
 * @author jordan.amberg
 * @version 12.1.2021
 */

public class Client {
    public static void main(String[] args) {
        CalculatorGUI gui = new CalculatorGUI();
        JFrame frame = new JFrame();
        frame.add(gui);
        frame.setSize(400, 380);
        frame.setVisible(true);
    }
}
