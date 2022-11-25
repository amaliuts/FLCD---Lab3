package com.company;

import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {
        GeneratorFA gen = new GeneratorFA();
        gen.scanFile("relations.txt");
    }
}
