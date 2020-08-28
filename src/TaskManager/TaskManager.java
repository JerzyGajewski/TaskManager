package TaskManager;

import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Array;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Scanner;

public class TaskManager {

    public static void main(String[] args) {

        homeMenu();
    }


    public static void homeMenu() {
        String[][] arr = fileReader();

        Scanner scanner = new Scanner(System.in);
        options();
        while (scanner.hasNextLine()) {
            String userChoice = scanner.nextLine();
            switch (userChoice.toLowerCase()) {
                case "add":
                    arr = getStrings(arr, scanner);
                    break;
                case "remove":
                    break;
                case "list":
                    arr = Arrays.copyOf(arr, arr.length);
                    fileReader();
                    break;
                case "exit":
                    System.out.println("Good bye");
                    System.exit(0);
                    break;
                default:
                    System.out.println("wrong comment, try again");

            }
            options();
        }
    }

    private static String[][] fileReader() {
        String[][] arr = new String[0][0];

        Path filePath = Paths.get("tasks.csv");
        try {

            for (String line : Files.readAllLines(filePath)) {
                System.out.println(line);
                String[] row = line.split(",");
                //todo :: add row to arr variable
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return arr;
    }

    private static String[][] getStrings(String[][] arr, Scanner scanner) {
        System.out.println("dodaj opis zadania: ");
        String description = scanner.nextLine();
        System.out.println("dodaj date: ");
        String date = scanner.nextLine();
        System.out.println("czy twoje zadanie jest wazne? true/false: ");
        String importance = scanner.nextLine();
        String[] task = new String[3];
        task[0] = description;
        task[1] = date;
        task[2] = importance;

        arr = Arrays.copyOf(arr, arr.length + 1);
        arr[arr.length - 1] = task;
        FileWriter file = null;

        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[i].length; j++) {
            String data = arr[i][j];

            }
        }
        return arr;
    }

    private static void options() {
        System.out.println("Choose option: ");
        System.out.println("add");
        System.out.println("list");
        System.out.println("remove");
        System.out.println("list");
        System.out.println("exit");
        System.out.println("Write:");
    }
}
