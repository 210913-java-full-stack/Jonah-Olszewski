package utils;

import exceptions.TooManyAttemptsException;

import java.util.Scanner;

public class Validatotr {

    public static String validateString(String prompt, int maxAttempts, int minLength, int maxLength) throws TooManyAttemptsException {
        //use the parameters passed in to do validation logic, just like we have in the RegistrationMenu now
        //when we are happy, return the string.
        Scanner sc = new Scanner(System.in);

        //while loop starting here
        System.out.println(prompt);
        sc.nextLine();


        //if they go too many attempts without succeeding:
        if(false/*Change this logic later!*/) {
            throw new TooManyAttemptsException("Too many attempts!");
        }

        //once we know it's valid:
        return "Some string which is valid";//this needs to be replaced with the valid input later
    }

    public static double validateMoney() {

    }

    public static String validatePassword() {

    }

    public static String validateEmail() {

    }
}
