package cz.mendelu.pjj;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class FileInputDealer implements IDealer{
    private File file;
    private IslandsFinding islandsFinder;
    private Scanner inputScanner;

    FileInputDealer(File file) throws FileNotFoundException {
        this.file = file;
        inputScanner = getInputScanner(file);
        islandsFinder = new IslandsFinding();
    }

    private Scanner getInputScanner(File file) throws FileNotFoundException {
        if (file == null) {
            return new Scanner(System.in);
        } else {
            try {
                return new Scanner(file);
            } catch (FileNotFoundException ex) {
                //String errMessage = "File " + file.getName() + " doesn't exist.";
                System.out.println("Soubor neexistuje error radek 27 FileInputDealer");
                throw new FileNotFoundException();
            }
        }
    }

    public void start(){
        InputDataHandler dataHandler = new InputDataHandler(inputScanner);
        dataHandler.displayResult();
    }

    public void displayResult(){
        islandsFinder.printIslands();
    }

    private boolean isLetter(String input) {
        return input.matches("[a-zA-Z]+");
    }
}