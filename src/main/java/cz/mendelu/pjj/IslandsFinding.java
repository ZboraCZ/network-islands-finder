package cz.mendelu.pjj;

import javax.swing.*;
import java.util.*;
import java.util.List;

class IslandsFinding { //Universal trida pro hledani ostrovu
    private Set<String> nodes;
    private Set<String> edges;
    private List<Island> islands;

    IslandsFinding(){
        nodes = new TreeSet<>();
        edges = new TreeSet<>();
        islands = new ArrayList<>();
    }

    void findIslands(){ //Algoritmus hledani ostrovu
        System.out.println("Hledani uzlu prave zacalo");

        for (String node: nodes) {   //Kazdy uzel z mnoziny bude prirazen do nektereho ostrovu
            //System.out.println("Rozhodovani o uzlu " + node);

            if (islands.isEmpty()){  //pokud neni zadny ostrov, vytvori se prvni
                Island addingIsland = new Island();
                addingIsland.addNode(node);
                for (String edge: edges) {
                    if (edge.substring(0, 1).equals(node) && !addingIsland.getNodes().contains(edge.substring(1, 2))) {
                        addingIsland.addNode(edge.substring(1, 2)); //pokud byla nalezena hrana a druhy prvek od soucasneho uzlu tam stale neni, pridame ho
                    }
                    if (edge.substring(1, 2).equals(node) && !addingIsland.getNodes().contains(edge.substring(0, 1))) {
                        addingIsland.addNode(edge.substring(0, 1)); //pokud byla nalezena hrana a druhy prvek od soucasneho uzlu tam stale neni, pridame ho
                    }
                }
                islands.add(addingIsland);
            } //end if (islands.isEmpty())
            else { //pokud ostrovy nejsou prazdne
                boolean islandWithNodeExists = false;
                Island foundIslandWithNode = new Island();
                for (int i = 0; i < islands.size(); i++) { //zjistime, jestli uz existuje ostrov s danym uzlem
                    if (!islandWithNodeExists && (islands.get(i).getNodes().contains(node))) {
                        islandWithNodeExists = true;
                        foundIslandWithNode = islands.get(i);
                    }
                }
                if (islandWithNodeExists) { //uzel v ostrove existuje, projdeme vsechny hrany, jestli ma souseda pro pridani do ostrovu
                    for (String edge: edges) {
                        if (edge.substring(0,1).equals(node) && !foundIslandWithNode.getNodes().contains(edge.substring(1,2))) {
                            foundIslandWithNode.addNode(edge.substring(1,2)); //pokud byla nalezena hrana a druhy prvek od soucasneho uzlu tam stale neni, pridame ho
                        }
                        if (edge.substring(1,2).equals(node) && !foundIslandWithNode.getNodes().contains(edge.substring(0,1))) {
                            foundIslandWithNode.addNode(edge.substring(0,1)); //pokud byla nalezena hrana a druhy prvek od soucasneho uzlu tam stale neni, pridame ho
                        }
                    }
                } else { //uzel v ostrove neexistuje, pridame ho do noveho ostrova a hned projdem uzly, zda nema sousedy pro pridani do ostrova NEBO SE BUDOU PRIPADNE SPOJOVAT OSTROVY
                    Island addingIsland = new Island();
                    addingIsland.addNode(node);
                    boolean islandGoingToMerge = false; //pokud najdeme ostrov, ktery jiz obsahuje operujici hranu na ktere jsme, ostrovy se musi spojit
                    for (String edge: edges) {  //zkontrolujeme vsechny nactene hrany
                        if (edge.substring(0, 1).equals(node) && !addingIsland.getNodes().contains(edge.substring(1, 2))) { //pokud jsme nasli hranu pro pridani, a druha stale jeste neni pridana
                            for (Island island : islands) {     //projdeme vsechny ostrovy
                                if (island.getNodes().contains(edge.substring(1,2))){  //a pokud nektery ostrov jiz obsahuje tu druhou hranu, kterou novy pridavajici ostrov nema
                                    islandGoingToMerge = true;      //nastava situace spojeni ostrovu
                                    for (String nodeToMove : addingIsland.getNodes()) {  //tedy vsechny hrany z noveho ostrova se pridaji do toho stavajiciho
                                        island.addNode(nodeToMove);
                                    }
                                    addingIsland = island;
                                }
                                if (!islandGoingToMerge){
                                    addingIsland.addNode(edge.substring(1, 2)); //pokud byla nalezena hrana a druhy prvek od soucasneho uzlu tam stale neni a zadny jiny ostrov ho neobsahuje, pridame ho
                                }
                            }
                        }
                        if (edge.substring(1, 2).equals(node) && !addingIsland.getNodes().contains(edge.substring(0, 1))) {
                            for (Island island : islands) {     //projdeme vsechny ostrovy
                                if (island.getNodes().contains(edge.substring(0,1))){  //a pokud nektery ostrov jiz obsahuje tu druhou hranu, kterou novy pridavajici ostrov nema
                                    islandGoingToMerge = true;      //nastava situace spojeni ostrovu
                                    for (String nodeToMove : addingIsland.getNodes()) {  //tedy vsechny hrany z noveho ostrova se pridaji do toho stavajiciho
                                        island.addNode(nodeToMove);
                                    }
                                    addingIsland = island;
                                }
                                if (!islandGoingToMerge){
                                    addingIsland.addNode(edge.substring(0, 1)); //pokud byla nalezena hrana a druhy prvek od soucasneho uzlu tam stale neni a zadny jiny ostrov ho neobsahuje, pridame ho
                                }
                            }
                            addingIsland.addNode(edge.substring(0, 1)); //pokud byla nalezena hrana a druhy prvek od soucasneho uzlu tam stale neni, pridame ho
                        }
                    }
                    if (!islandGoingToMerge) {
                        islands.add(addingIsland);
                    }
                }
            }
        }
        printIslands();
    }

    void printIslands(){ //vypisovani ostrovu

        getNodes();
        getEdges();

        System.out.println("Vysledek - IslandsFinderovo zobrazeni vsech ostrovu: ");

        for (Island island : islands) {
            System.out.println(island.printIsland());
        }
    }

    void printIslands(JTextArea islandsTextArea){ //vypisovani ostrovu

        getNodes();
        getEdges();

        System.out.println("Vysledek - IslandsFinderovo zobrazeni vsech ostrovu: ");
        islandsTextArea.setText("");
        for (Island island : islands) {
            System.out.println(island.printIsland());
            islandsTextArea.append(island.printIsland()+"\n");
        }
    }

    void addNode(String node){
        if (!nodes.contains(node)){   //pridavani uzlu do mnoziny
            nodes.add(node);
        } else {
            System.out.println("Uzel "+ node + " uz existuje, bude vynechan.");
        }
    }

    void addEdge(String edge){  // pridavani
        String modifiedEdge = edge.substring(0,1) + edge.substring(2,3);
        String reversedModifiedEdge = edge.substring(2,3) + edge.substring(0,1);
        //System.out.println("Puvodni hrana pred ulozenim: " + edge + "; Upravena hrana pro ulozeni do IslandsFinderu: " + modifiedEdge + "; Upravena hrana POZPATKU pro porovnani stejnych uzlu: " + reversedModifiedEdge + ";");
        if (edges.contains(modifiedEdge) || edges.contains(reversedModifiedEdge)){
            System.out.println("Hrana " + edge + " uz existuje, bude vynechana.");
        } else {
            edges.add(modifiedEdge);
        }
    }

    private void getNodes(){
        System.out.println("IslandsFinder - Vypis uzlu: ");
        for (String node: nodes) {
            System.out.print(node + " ");
        }
        System.out.println();
    }

    private void getEdges(){
        System.out.println("IslandsFinder - Vypis hran: ");
        for (String edge: edges) {
            System.out.print(edge + " ");
        }
        System.out.println();
    }
}
