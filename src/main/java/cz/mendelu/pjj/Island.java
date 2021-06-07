package cz.mendelu.pjj;

import java.util.*;

class Island {
    private Set<String> nodes; //pak nekde v jine tride bude List<set> podle doporuceni Jedlicky

    Island(){
        nodes = new TreeSet<>();
    }

     void addNode(String node){
        nodes.add(node);
    }

    String printIsland(){
        boolean firstNode = true;
        StringBuilder completeIsland = new StringBuilder();
        for (String node: nodes) {
            if (!firstNode){
                String nodeToAppend = "," + node;
                completeIsland.append(nodeToAppend);
            } else{  //pokud vkladame prvni uzel, nebudeme na zacatku vyjimecne delat carku
                firstNode = false;
                completeIsland.append(node);
            }
        }
        return completeIsland.toString();
    }

    Set<String> getNodes() {
        return nodes;
    }

}
