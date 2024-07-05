package org.example;


import java.util.Scanner;


public class Main {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        System.out.println("Please enter your name:");
        String name = scan.nextLine();

        System.out.println("Please enter your surname:");
        String surname = scan.nextLine();

        System.out.println("Please enter your year of birth:");
        String birth = scan.nextLine();
        int birthy = Integer.parseInt(birth);
        if (birthy <= 2023) {
            System.out.println("You have successfully logged in");
            StringBuilder w = new StringBuilder();
            w.append("Welcome").append(" ").append(name).append(" ").append(surname);
            System.out.println(w.toString());
            System.out.println("Your password has expired for 3 months. \nPlease set a new 6-digit banking password");

            String newPassword = scan.nextLine();
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
                    }
                }
            }
        }
        else {
            System.out.println("You have not successfully logged in");
        }
    }

    public static boolean containNumbers(String newPassword){
        for (int i=0; i< newPassword.length()-1; i++){
            char a = newPassword.charAt(i);
            char b = newPassword.charAt(i+1);
            char c = newPassword.charAt(i+2);

            if (b == a + 1 && c == b + 1){
                return true;
            }
        }
        return false;
    }

}