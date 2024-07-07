package org.example;

import java.util.*;


public class Main {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        System.out.println("Please enter your name:");
        String name = scan.nextLine();

        System.out.println("Please enter your surname:");
        String surname = scan.nextLine();

        System.out.println("Please enter your year of birth:");
        String birth = scan.nextLine();
        Set<String> passwordss = new HashSet<>();
        List<String> wrongPasswords = new ArrayList<>();
        List<String> correctPasswords = new ArrayList<>();


        int birthy = Integer.parseInt(birth);
        if (birthy <= 2023) {
            System.out.println("Successfully logged in");
            StringBuilder w = new StringBuilder();
            w.append("Welcome").append(" ").append(name).append(" ").append(surname);
            System.out.println(w.toString());
            System.out.println("Your password has expired for 3 months. \nPlease set a new 6-digit banking password");
            String newPassword = scan.nextLine();

            passwordss.add(newPassword);
            if (newPassword.length() != 6) {
                System.out.println("Your new password must contain 6 digits");

            }
            else{
                if (newPassword.equals(birth)) {
                    System.out.println("Your new password must be different from your birth year");

                }
                else {
                    if (containNumbers(newPassword)){
                        System.out.println("Your new password must not contain consecutive numbers");

                    }
                    else{
                        System.out.println("Your new password is successful ");
                        while(true) {
                            int maxAttempts = 3;
                            int remainingAttempts = maxAttempts;
                            System.out.println("Would you like to change your password again");
                            String pass = scan.nextLine();
                            if (pass.equals("E")) {
                                System.out.println("Enter your last password");
                                String tryPassword = scan.nextLine();
                                while (!newPassword.equals(tryPassword)) {
                                    remainingAttempts--;
                                    if (remainingAttempts == 0) {
                                        System.out.println("Your account is blocked, please call customer service");
                                       printPasswords(wrongPasswords, correctPasswords);
                                        return;
                                    }
                                    System.out.println("Password does not match, please try again");
                                    wrongPasswords.add(tryPassword);
                                    tryPassword = scan.nextLine();
                                }
                                System.out.println("Please enter your new password");
                                String lastPassword = scan.nextLine();

                                while (lastPassword.equals(tryPassword) || passwordss.contains(lastPassword)) {
                                    if(tryPassword.equals(lastPassword)) {
                                        System.out.println("The new password cannot be the same as the password. \nPlease enter your new password");
                                    }
                                    else{
                                        System.out.println("The new password cannot be the same as one of the previous 5 password. \nPlease enter your new password");
                                    }
                                    lastPassword = scan.nextLine();
                                }

                                passwordss.add(lastPassword);
                                correctPasswords.add(lastPassword);

                                break;
                            }
                            else {
                                System.out.println("Have a nice day");
                                printPasswords(wrongPasswords, correctPasswords);
                                return;
                            }

                        }

                    }
                }
            }
        }
        System.out.println("Failed to log in");
    }

    public static boolean containNumbers(String newPassword){
        for (int i=0; i< newPassword.length()-2; i++){
            char a = newPassword.charAt(i);
            char b = newPassword.charAt(i+1);
            char c = newPassword.charAt(i+2);

            if (b == a + 1 && c == b + 1){
                return true;
            }
        }
        return false;
    }

    public static void printPasswords(List<String> wrongPasswords, List<String> correctPasswords ){
        Collections.sort(wrongPasswords);
        System.out.println("Incorrectly entered passwords");
        for (String wrongPassword : wrongPasswords) {
            System.out.println(wrongPassword);
        }
        Collections.sort(correctPasswords);
        System.out.println("Correctly entered passwords");
        for (String correctPassword : correctPasswords) {
            System.out.println(correctPassword);
        }
    }
}







