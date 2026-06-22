package com.barangay.Main;

import java.time.LocalDate;

interface Resident{
    public void getResidentName();
}

//name storage
class residentName{
    private String firstName;
    private String lastName;

    public residentName(String firstName, String lastName){
        this.firstName = firstName;
        this.lastName = lastName;

    }

    public String getFirstName(){

        return firstName;
    }

    public void setFirstName(String firstName){
        this.firstName = firstName;
    }

    public String getlastName(){

        return lastName;

    }

    public void setLastName(String lastName){
        this.lastName = lastName;
    }


    
}

interface residentBirthday{
    public void getResidentBirthday();

}

class residentBirthDate{
    private LocalDate birthDate;
}
