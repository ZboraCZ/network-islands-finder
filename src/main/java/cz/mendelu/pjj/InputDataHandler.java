package cz.mendelu.pjj;

import javax.swing.*;
import java.util.Scanner;

class InputDataHandler {
    IslandsFinding islandsFinder;

    InputDataHandler(Scanner inputScanner){

        islandsFinder = new IslandsFinding();

        System.out.println("Zacalo cteni ze souboru ");
        System.out.println("Zacalo cteni uzlu");
        String inputLine = inputScanner.nextLine();
        while (inputLine.length() != 0){ //vkladani uzlu
            System.out.println("Precteny uzel: " + inputLine);
            if (inputLine.length() == 1 && isLetter(inputLine)){
                islandsFinder.addNode(inputLine.toUpperCase());
            } else {
                System.out.println("Pri zadavani uzlu je povolen pouze jeden znak, tento vstup bude preskocen.");
            }
            inputLine = inputScanner.nextLine();
        }
        //vkladani hran
        System.out.println("Zacalo cteni hran");
        inputLine = inputScanner.nextLine();
        String edge;
        while(inputLine.length() != 0){  //vkladani hran
            System.out.println("Prectena hrana: " + inputLine);
            if (inputLine.length() == 3){
                edge = inputLine.toUpperCase();
                islandsFinder.addEdge(edge);
            } else {
                System.out.println("Spatny format vstupu, je povolen pouze format [Uzel1],[Uzel2] - tento vstup bude preskocen.");
            }
            inputLine = inputScanner.nextLine();
        }
        //po dokonceni vkladani dat vytvori islandsFinder ostrovy a pote je vypiseme
        islandsFinder.findIslands();
    }

    private boolean isLetter(String input) {
        return input.matches("[a-zA-Z]+");
    }

    public void displayResult(){
        islandsFinder.printIslands();
    }

    public void displayResult(JTextArea guiTextArea){
        islandsFinder.printIslands(guiTextArea);
    }
}
