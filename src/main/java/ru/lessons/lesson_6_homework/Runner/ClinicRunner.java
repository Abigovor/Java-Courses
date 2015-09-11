package ru.lessons.lesson_6_homework.Runner;

import ru.lessons.lesson_6_homework.Cat;
import ru.lessons.lesson_6_homework.Client;
import ru.lessons.lesson_6_homework.Clinic;
import ru.lessons.lesson_6_homework.UniqueIdentifier;
import ru.lessons.lesson_6_homework.UserException.UserException;
import ru.lessons.lesson_6_homework.UserException.UserPetException;

import java.util.List;
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
        final Clinic clinic = new Clinic();


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

            int id;
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
                    clinic.addClient(new Client(UniqueIdentifier.getNextInt(), name, new Cat(petName)));
                    System.out.println("+");
                    break;
                }
                case 2:
                    System.out.print("Enter name: ");
                    name = reader.next();
                    try {
                        showClient(clinic.findClientByName(name));
                    } catch (UserException e) {
                        e.printStackTrace();
                    }
                    break;
                case 3:
                    System.out.print("Enter pet name: ");
                    petName = reader.next();
                    try {
                        showClient(clinic.findClientByPetName(petName));
                    } catch (UserException e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 4:
                    System.out.print("Enter id of client: ");
                    id = Integer.parseInt(reader.next());
                    try {
                        clinic.removeClient(id);
                    } catch (UserException e) {
                        e.printStackTrace();
                    }
                    break;
                case 5:
                    System.out.print("Enter id of client: ");
                    id = Integer.parseInt(reader.next());
                    try {
                        clinic.removePet(id);
                    } catch (UserException | UserPetException e) {
                        e.printStackTrace();
                    }
                    break;
                case 6:
                    System.out.print("Enter id of client for rename: ");
                    id = Integer.valueOf(reader.next());
                    System.out.print("Enter new name of client: ");
                    newName = reader.next();
                    try {
                        clinic.renameClient(id, newName);
                    } catch (UserException e) {
                        e.printStackTrace();
                    }
                    break;
                case 7:
                    System.out.print("Enter id of client for rename the pet: ");
                    id = Integer.parseInt(reader.next());
                    System.out.print("Enter new pet name: ");
                    newName = reader.next();
                    try {
                        clinic.renamePet(id, newName);
                    } catch (UserException | UserPetException e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 8:
                    try {
                        clinic.showClient();
                    } catch (UserException e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 9:
                    showHelp();
                    break;
                default:
                    System.out.println("Please enter correct command!");
            }
        }
    }

    private static void showClient(List<Client> clients) {
        for (Client client : clients) {
            System.out.println(client.toString());
        }
    }
}
