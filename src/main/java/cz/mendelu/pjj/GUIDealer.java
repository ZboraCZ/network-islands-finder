package cz.mendelu.pjj;

import javax.swing.*;

public class GUIDealer implements IDealer{

    @Override
    public void start(){

        JFrame frame = new JFrame("Demo frame");
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setSize(600,400);
        frame.setContentPane(new GUIForm().getRootPanel());
        frame.setVisible(true);
    }

    @Override
    public void displayResult() {

    }
}
