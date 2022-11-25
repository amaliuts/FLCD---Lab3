package com.company;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class GeneratorFA {
    private final List<List<String>> fileContent;
    private final List<String> finalStatesList;
    private final List<String> statesList;
    private final List<String> alphabet;
    private final List<String> transitions;

    public GeneratorFA() {
        fileContent = new ArrayList<>();
        finalStatesList = new ArrayList<>();
        statesList = new ArrayList<>();
        alphabet = new ArrayList<>();
        transitions = new ArrayList<>();
    }

    public void scanFile(String filePath) throws IOException {
        File file = new File(filePath);
        FileReader fileReader = new FileReader(file);
        BufferedReader buff = new BufferedReader(fileReader);

        String currentLine;
        while ((currentLine = buff.readLine()) != null) {
            String[] elems = currentLine.split(",");
            List<String> tempLine = new ArrayList<>();
            for (String elem : elems) {
                tempLine.add(elem);
            }
            fileContent.add(tempLine);
        }
        populateLists();
        getStatesList();
        getFinalStatesList();
        getAlphabet();
        getTransitions();
    }

    private void populateLists() {
        for (List<String> currentLine : fileContent) {
            for (String elem : currentLine) {
                if (elem.equals("final")) {
                    String finalState = currentLine.get(0);
                    finalStatesList.add(finalState);
                    if (!statesList.contains(finalState))
                        statesList.add(finalState);
                }
            }
            if (currentLine.size() > 2) {
                int lastElemPos = currentLine.size()-1;
                String startingState = currentLine.get(0);
                String endingState = currentLine.get(lastElemPos);
                String alphabetElem = currentLine.get(1);
                String transition = startingState + "," + alphabetElem + "->" + endingState;
                transitions.add(transition);
                if (!statesList.contains(startingState))
                    statesList.add(startingState);
                if (!statesList.contains(endingState))
                    statesList.add(endingState);
                if (!alphabet.contains(alphabetElem))
                    alphabet.add(alphabetElem);
            }
        }
    }

    private void getStatesList() {
        System.out.println();
        System.out.print("Set of states: { ");
        for (String state : statesList) {
            System.out.print(state + " ");
        }
        System.out.print("}");
    }

    private void getFinalStatesList() {
        System.out.println();
        System.out.print("Set of final states: { ");
        for (String state : finalStatesList)
            System.out.print(state + " ");
        System.out.print("}");
    }

    private void getAlphabet() {
        System.out.println();
        System.out.print("Alphabet: { ");
        for (String alph : alphabet)
            System.out.print(alph + " ");
        System.out.print("}");
    }

    private void getTransitions() {
        System.out.println();
        for (String tr : transitions)
            System.out.println(tr);
    }
}
