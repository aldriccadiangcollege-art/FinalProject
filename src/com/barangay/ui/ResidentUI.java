package com.barangay.ui;

import java.util.Scanner;
import com.barangay.models.*;
import java.time.LocalDate;

public class ResidentUI {
    private Scanner sc = new Scanner(System.in);

    public Resident registerResident(){
        System.out.println("First Name: ");
        String firstName = sc.nextLine();

        System.out.println("Last Name: ");
        String lastName = sc.nextLine();
        residentName name = new residentName(firstName, lastName);

        System.out.println("Birthdate: ");
        LocalDate birthdate = LocalDate.parse(sc.nextLine());
        residentBirthDate birth = new residentBirthDate(birthdate);
        residentBdate bDate = new residentBDate(birth);

        System.out.print("Gender (M/F/X): ");
        String genderInput = sc.nextLine();
        ResidentGender gender;
        switch (genderInput.toUpperCase()){
            case "M": gender = new Male(); break;
            case "F": gender = new Female(); break; 
            default: gender = new PreferNotToSay(); break; 
        }

        System.out.println("Resident: " + name.getFirstName() + " " + name.getlastName() +
                          ", Birthday" + bDate.getResidentBirthDay() + 
                          ", Gender: " + gender.getResidentGender());
            return null;
    }


}


