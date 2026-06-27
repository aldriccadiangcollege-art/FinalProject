package com.barangay.models;


import java.util.Scanner;

public class ConsoleInputProvider implements InputProvider {
    private Scanner sc = new Scanner(System.in);

    @Override
    public String getInput(String prompt) {
        System.out.print(prompt);
        return sc.nextLine();
    }
}
