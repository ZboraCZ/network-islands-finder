package cz.mendelu.pjj;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Scanner;

public class GUIForm {
    private JTextArea nodesAndEdgesListArea;
    private JTextArea islandsTextArea;
    private JLabel nodesListLabel;
    private JLabel edgesListLabel;
    private JPanel guiFormJPanel;
    private JLabel errorLabel;
    private JButton startButton;
    private Scanner inputScanner;

    private IslandsFinding islandsFinder;

    GUIForm() {
        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) { //finding islands
                islandsFinder = new IslandsFinding();
                inputScanner = new Scanner(nodesAndEdgesListArea.getText());

                InputDataHandler dataHandler = new InputDataHandler(inputScanner);
                dataHandler.displayResult(islandsTextArea);
            }
        });
    }

    JPanel getRootPanel() {
        return guiFormJPanel;
    }

    private void displayResult(){
        islandsFinder.printIslands(islandsTextArea);
    }

    private boolean isLetter(String input) {
        return input.matches("[a-zA-Z]+");
    }
}
