package cz.mendelu.pjj;
/////////////Program neni funkcni, prosim aspon o kontrolu, zda struktura aplikace vypada realizovatelne. Dekuji.
//Cteme podle parametru vstup, adresar, soubor
//umime rozparsovat na nejake casti
//takze umime cist soubor a z nej cist data podle regularnich vyrazu
import javax.swing.*;
import java.io.*;

public class Application {
    private static boolean guiNeeded = false;
    private static boolean paramsOk;
    private static File file;

    public static void main(String[] args) {  //pokud nejsou pri spusteni zadany parametry, length pole args je 0.

        assignParams(args);                   //Kontrola parametru pri spusteni a jejich prirazovani do atributu tridy

        if (args.length > 0) {                //Zda uzivatel zadal do programu parametry [--gui nebo cestu k souboru] nebo ne
            if (isGuiNeeded()) {              //pokud zadal --gui ,probehne metoda spusteni GUI
                startGUI();
            } else {                          //pokud nezadal --gui, pak zadal soubor a spustime metodu pro praci se soubory
                startNoneGUI(getFile());
            }
        } else {  //Uzivatel nezadal do programu zadne parametry, bude se cist ze standardniho vstupu
            startCommandLine();
        }
    }

    private static void assignParams(String[] cmdArguments){
        if (cmdArguments.length > 0) {
            if (cmdArguments[0].equalsIgnoreCase("--gui")){
                System.out.println("User set GUI parameter");
                guiNeeded = true;
            } else {
                file = new File(cmdArguments[0]);
                if (!file.exists()) {
                    System.err.printf("File %s doesn't exist. %n", cmdArguments[0]);
                    System.exit(-1);
                } else {
                    System.out.printf("File %s exists. %n", cmdArguments[0]);
                    paramsOk = true;
                }
            }
        } else {
            paramsOk = true; //Parameters not passed into commandline, reading from StdIn then...
        }
    }

    private static boolean isGuiNeeded(){
        return guiNeeded;
    }

    private static File getFile(){
        if (paramsOk) {
            return file;
        } else {
            throw new IllegalStateException("Firstly run checkParams() method");
        }
    }

    private static void startGUI(){
        GUIDealer guiDealer;
        guiDealer = new GUIDealer();
        guiDealer.start();
    }

    private static void startNoneGUI(File f){ //running File handling here if entered
        FileInputDealer fileDealer;
        try {
            fileDealer = new FileInputDealer(f);
            fileDealer.start();
            fileDealer.displayResult();
        } catch (FileNotFoundException e){
            System.err.println(e.getMessage());
            System.exit(-1);
        }
    }
    private static void startCommandLine(){
        CmdDealer cmdDealer = new CmdDealer(); //cmdDealer pohandluje input, organizuje data a vraci vysledek
        cmdDealer.start();  //provede se prace v prikazove radce
        cmdDealer.displayResult(); //zde se pouze zobrazi hotove ostrovy
    }
}

