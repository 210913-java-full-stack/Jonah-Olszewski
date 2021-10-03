package utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validator {
    public static boolean validateName(String name){
        // Regex to check valid username.
        String regex = "^[A-Za-z]{1,24}$";

        // Compile the ReGex
        Pattern p = Pattern.compile(regex);

        // If the username is empty return false
        if (name == null) {
            return false;
        }

        // Pattern class contains matcher() method to find matching between given username
        // and regular expression.
        Matcher m = p.matcher(name);

        // Return if the username matched the ReGex
        return m.matches();
    }

    public static boolean validateUsername(String username) {
        // Regex to check valid username.
        String regex = "^[A-Za-z]\\w{5,29}$";

        // Compile the ReGex
        Pattern p = Pattern.compile(regex);

        // If the username is empty return false
        if (username == null) {
            return false;
        }

        // Pattern class contains matcher() method to find matching between given username
        // and regular expression.
        Matcher m = p.matcher(username);

        // Return if the username matched the ReGex
        return m.matches();
    }

    public static boolean validatePassword(String password) {
        // Regex to check valid password.
        /*
        ^ represents starting character of the string.
        (?=.*[0-9]) represents a digit must occur at least once.
        (?=.*[a-z]) represents a lower case alphabet must occur at least once.
        (?=.*[A-Z]) represents an upper case alphabet that must occur at least once.
        (?=.*[@#$%^&-+=()] represents a special character that must occur at least once.
        (?=\\S+$) white spaces don’t allowed in the entire string.
        .{8, 20} represents at least 8 characters and at most 20 characters.
        $ represents the end of the string.
        * */
        String regex = "^(?=.*[0-9])"
                + "(?=.*[a-z])(?=.*[A-Z])"
                + "(?=.*[!@#$%^&+=])"
                + "(?=\\S+$).{8,20}$";

        // Compile the ReGex
        Pattern p = Pattern.compile(regex);

        // If the password is empty return false
        if (password == null) {
            return false;
        }

        // Pattern class contains matcher() method to find matching between given password
        // and regular expression.
        Matcher m = p.matcher(password);

        // Return if the password matched the ReGex
        return m.matches();
    }

    public static boolean validateEmail(String email) {
        // Regex to check valid username.
        String regex = "^[A-Za-z0-9+_.-]+@(.+)$";

        // Compile the ReGex
        Pattern p = Pattern.compile(regex);

        // If the email is empty return false
        if (email == null) {
            return false;
        }

        // Pattern class contains matcher() method to find matching between given email
        // and regular expression.
        Matcher m = p.matcher(email);

        // Return if the email matched the ReGex
        return m.matches();
    }
//    public static String validateString(String prompt, int maxAttempts, int minLength, int maxLength) throws TooManyAttemptsException {
//        //use the parameters passed in to do validation logic, just like we have in the RegistrationMenu now
//        //when we are happy, return the string.
//        Scanner sc = new Scanner(System.in);
//
//
//        //while loop starting here
//        while(valid) {
//            if(prompt == null || prompt.equals("")) {
//                //tell the user what happened and return false
//                System.out.println("First or last name cannot be blank.");
//                numOfTries++;
//                continue;
//            }
//            if((prompt.length() < minLength) || (prompt.length() > maxLength)) {
//                //either too long or too short, validation failed
//                //do stuff or just do a continue;
//                System.out.println("too long/short!");
//                numOfTries++;
//                continue;
//            }
//            for(int i = 0; i < prompt.length(); i++) {
//                char ch = prompt.charAt(i);
//                //if there is a foreign character
//                if ((!(ch >= 'A' && ch <= 'Z')) && (!(ch >= 'a' && ch <= 'z'))) {
//                    //tell the user what happened and return false
//                    System.out.println("Invalid characters in first or last name");
//                    continue;
//                }
//            }
//            //if they go too many attempts without succeeding:
//            if(numOfTries == maxAttempts) {
//                throw new TooManyAttemptsException("Too many attempts!");
//            }
//            valid = true;
//        }
//        //once we know it's valid:
//        return "Some string which is valid";//this needs to be replaced with the valid input later
//    }
}
