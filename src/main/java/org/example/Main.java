package org.example;

import java.util.*;

public class Main {
    private static final Scanner scanner = new Scanner(System.in);
    private static final List<String> correctPasswords = new ArrayList<>();
    private static final List<String> wrongPasswords = new ArrayList<>();
    private static final Deque<String> lastPasswords = new ArrayDeque<>();
    private static final int MAX_PASSWORD_ATTEMPT = 3;
    private static final int MAX_PASSWORD_LENGTH = 6;
    private static final int OLD_PASSWORD_LIMIT = 5;

    public static void main(String[] args) {
        System.out.println("Your name!");
        String name = scanner.nextLine();

        System.out.println("Your surname!");
        String surname = scanner.nextLine();

        System.out.println("Your birth year!");
        int birthYear = scanner.nextInt();

        while (!birthYearValidation(birthYear)) {
            System.out.println("Your birth year is not valid. Please enter a valid birth year!");
            birthYear = scanner.nextInt();
        }

        StringBuilder welcomeAnnouncement =
                new StringBuilder("Welcome, ").append(name).append(" ").append(surname).append("!");
        System.out.println(welcomeAnnouncement);

        System.out.println("Your password has expired for 3 months.");
        passwordOperations(birthYear);

        while (true) {
            System.out.println("Do you want to change again your password? (Y/N)");
            String answer = scanner.next();
            if (answer.equalsIgnoreCase("Y")) {
                int passwordTryCount = 0;
                while (true) {
                    System.out.println("Enter your current password for password change request.");
                    String currentPassword = scanner.next();
                    if (lastPasswords.getLast().equals(currentPassword)) {
                        System.out.println("Your password has been successfully verified!");
                        passwordOperations(birthYear);
                        break;
                    } else {
                        System.out.println("Your password is not valid. Please enter your password again!");
                        passwordTryCount++;
                        if (passwordTryCount == MAX_PASSWORD_ATTEMPT) {
                            System.out.println("Your password has been blocked due to 3 incorrect password attempts.");
                            System.out.println("Please contact your branch.");
                            break;
                        }
                    }
                }
            } else if (answer.equalsIgnoreCase("N")) {
                break;
            } else {
                System.out.println("Invalid input! Please enter Y or N.");
            }
        }

        System.out.println("Wrong ones:");
        wrongPasswords.stream().sorted().forEach(System.out::println);
        System.out.println("Correct ones:");
        correctPasswords.stream().sorted(Comparator.reverseOrder()).forEach(System.out::println);


    }

    private static void passwordOperations(int birthYear) {
        while (true) {
            System.out.println("Enter your new password!");
            String password = scanner.next();

            if (passwordValidation(password, birthYear)) {
                System.out.println("Your password has been successfully updated!");
                lastPasswords.add(password);
                correctPasswords.add(password);
                if (lastPasswords.size() == OLD_PASSWORD_LIMIT) {
                    lastPasswords.removeFirst();
                }
                break;
            } else {
                wrongPasswords.add(password);
            }
        }
    }

    private static boolean birthYearValidation(int birthYear) {
        return birthYear > 1900 && birthYear < 2022;
    }

    private static boolean passwordValidation(String password, int birthYear) {
        if (password.length() != MAX_PASSWORD_LENGTH) {
            System.out.println("Your password must be 6 digits long!");
            return false;
        } else if (password.contains(String.valueOf(birthYear))) {
            System.out.println("Your password cannot be contain your birth year!");
            return false;
        } else if (lastPasswords.contains(password)) {
            System.out.println("You have used this password before. Please enter a new password!");
            return false;
        } else if (consecutiveNumbersValidation(password)) {
            System.out.println("Your password cannot contain consecutive numbers!");
            return false;
        } else {
            return true;
        }
    }

    private static boolean consecutiveNumbersValidation(String password) {
        for (int i = 0; i < password.length() - 1; i++) {
            if (password.charAt(i) == password.charAt(i + 1)) {
                return true;
            } else if (i < password.length() - 2 && password.charAt(i) + 1 == password.charAt(i + 1) && password.charAt(i) + 2 == password.charAt(i + 2)) {
                return true;
            }
        }
        return false;
    }

}