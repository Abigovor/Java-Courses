package lesson_6_homework;

import lesson_6_homework.UserException.UserException;
import lesson_6_homework.UserException.UserPetException;

import java.util.Scanner;

/**
 * Created by Single on 18.04.2015.
 */
public class ClinicRunner {

    private static void showHelp() {
        System.out.println("Command 1 - add client");
        System.out.println("Command 2 - find client by name");
        System.out.println("Command 3 - find client by pet name");
        System.out.println("Command 4 - remove client by name");
        System.out.println("Command 5 - remove pet");
        System.out.println("Command 6 - rename client");
        System.out.println("Command 7 - rename pet");
        System.out.println("Command 8 - Show clients");
        System.out.println("Command 9 - Show help");
        System.out.println("Command 0 - Exit");
    }


    public static void main(String[] args) {
        final Clinic clinic = new Clinic(10);


        boolean start = true;
        Scanner reader = new Scanner(System.in);
        int command;
        showHelp();

        while (start) {
            System.out.printf("Enter command :");
            try {
                command = Integer.parseInt(reader.next());
            } catch (NumberFormatException e) {
                continue;
            }

            String name;
            String petName;
            String newName;

            switch (command) {
                case 0:
                    start = !start;
                    break;
                case 1: {
                    System.out.print("Enter name: ");
                    name = reader.next();
                    System.out.print("Enter name pet: ");
                    petName = reader.next();
                    clinic.addClient(new Client(name, new Cat(petName)));
                    System.out.println("+");
                    break;
                }
                case 2:
                    System.out.print("Enter name: ");
                    name = reader.next();
                    try {
                        clinic.findClientByName(name);
                    } catch (UserException e) {
                        e.printStackTrace();
                    }
                    break;
                case 3:
                    System.out.print("Enter pet name: ");
                    petName = reader.next();
                    try {
                        clinic.findClientByPetName(petName);
                    } catch (UserException e) {
                        e.printStackTrace();
                    } catch (UserPetException e) {
                        e.printStackTrace();
                    }
                    break;
                case 4:
                    System.out.print("Enter name: ");
                    name = reader.next();
                    try {
                        clinic.removeClient(name);
                    } catch (UserException e) {
                        e.printStackTrace();
                    }
                    break;
                case 5:
                    System.out.print("Enter client name: ");
                    name = reader.next();
                    try {
                        clinic.removePet(name);
                    } catch (UserException e) {
                        e.printStackTrace();
                    }
                    break;
                case 6:
                    System.out.print("Enter client name for rename: ");
                    name = reader.next();
                    System.out.print("Enter new name for client: ");
                    newName = reader.next();
                    try {
                        clinic.renameClient(name, newName);
                    } catch (UserException e) {
                        e.printStackTrace();
                    }
                    break;
                case 7:
                    System.out.print("Enter pet name for rename: ");
                    name = reader.next();
                    System.out.print("Enter new pet name: ");
                    newName = reader.next();
                    try {
                        clinic.renamePet(name, newName);
                    } catch (UserException e) {
                        e.printStackTrace();
                    } catch (UserPetException e) {
                        e.printStackTrace();
                    }
                    break;
                case 8:
                    clinic.showClient();
                    break;
                case 9:
                    showHelp();
                    break;
                default:
                    System.out.println("Please enter correct command!");
            }
        }

    }
}
