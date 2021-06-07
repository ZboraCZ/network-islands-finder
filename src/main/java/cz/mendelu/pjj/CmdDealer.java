package cz.mendelu.pjj;

import java.util.Scanner;

public class CmdDealer implements IDealer {

    private IslandsFinding islandsFinder;

    CmdDealer() {
        islandsFinder = new IslandsFinding();
    }

    @Override
    public void start(){  //Work with command line inputs starts here
        Scanner inputScanner = new Scanner(System.in);

        InputDataHandler dataHandler = new InputDataHandler(inputScanner);
    }

    @Override
    public void displayResult(){
        islandsFinder.printIslands();
    }

}
