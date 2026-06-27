package com.barangay.services;

import java.util.Scanner;

interface InputProvider {
    String getInput(String prompt);
}

public class ConsoleInputProvider implements InputProvider {
    private Scanner sc = new Scanner(System.in);

    @Override
    public String getInput(String prompt) {
        System.out.print(prompt);
        return sc.nextLine();
    }
}
