package TaskManager;


import org.apache.commons.lang3.ArrayUtils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class TaskManager {
    static String[][] datas;
    static String FILE = "tasks.csv";
    public static void main(String[] args) {
        datas = loadFileToApp(FILE);
        Menu();
    }


    public static void Menu() {
        Scanner scann = new Scanner(System.in);
        options();
        while (scann.hasNextLine()) {
            String choise = scann.nextLine();
            switch (choise.toLowerCase()) {
                case "add":
                        datas = Arrays.copyOf(datas, datas.length + 1);
                        datas[datas.length - 1] = task();
                    break;
                case "remove":
                    removeTask(datas);
                    break;
                case "list":
                    showList();
                    break;
                case "exit":
                    saveDatasToFile(datas, FILE);
                    System.out.println(ConsoleColors.CYAN_BOLD + "Good bye");
                    System.out.println(ConsoleColors.RESET);
                    System.exit(0);
                    break;
                default:
                    System.out.println(ConsoleColors.RED + "Wrong commend, try again");
                    System.out.print(ConsoleColors.RESET);
            }
            options();
        }

    }

    private static void showList() {
        if ( datas.length != 0) {
            for (int i = 0; i < datas.length; i++) {
                System.out.print(i + ". ");
                for (int j = 0; j < datas[i].length; j++) {
                    System.out.print(datas[i][j] + " ");
                }
                System.out.println();
            }
        } else
            System.out.println("Nothing to show");
    }

    public static String[][] loadFileToApp(String file) {
        isExist(file);

        String[][] fileBody = new String[0][];
        Path filePath = Paths.get(file);
        try {
            if (Files.size(filePath) > 0) {
                try {
                    List<String> tables = Files.readAllLines(filePath);
                    fileBody = new String[tables.size()][tables.get(0).split(",").length];
                    for (int i = 0; i < tables.size(); i++) {
                        String[] counter = tables.get(i).split(",");
                        for (int j = 0; j < counter.length; j++) {
                            fileBody[i][j] = counter[j];
                        }

                    }
                } catch (IOException e) {
                    e.printStackTrace();

                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return fileBody;
    }

    // check if file exists
    public static void isExist(String file) {
        Path filePath = Paths.get(file);
        if (!Files.exists(filePath)) {
            System.out.println("File not exist");
            System.exit(0);
        }
    }

    public static void saveDatasToFile(String[][] str, String file) {
        Path filePath = Paths.get(file);

        String[] joinLines = new String[str.length];
        for (int i = 0; i < str.length; i++) {
            joinLines[i] = String.join(", ", str[i]);
        }
        try {
            Files.write(filePath, Arrays.asList(joinLines));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String[] task() {
        Scanner scanner = new Scanner(System.in);
        String[] answers = new String[3];
        System.out.println("Add task description:");
        String desc = scanner.nextLine();
        System.out.println("Add date: ");
        String date = scanner.nextLine();
        System.out.println("Is task important? true/false");
        while(true){
        String importance = scanner.nextLine();
            if(importance.toLowerCase().equals("true") || importance.equals("false")){
        answers[2] = importance;
                break;
            }else {
                System.out.println("Type true or false");
        System.out.println("Is task important? true/false");
            }
        }
        answers[0] = desc;
        answers[1] = date;


        return answers;
    }

    public static void removeTask(String[][] data) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Witch line you want to remove: ");
        while (!scanner.hasNextInt()) {
            System.out.println("This is not a number, try again");
            System.out.println("Witch line you want to remove: ");
            scanner.next();
        }
        int number = scanner.nextInt();


        if (number < data.length) {
            datas = ArrayUtils.remove(datas, number);
            System.out.println("Line removed");
        } else
            System.out.println("Element not exist");
    }

    private static void options() {
        System.out.println(ConsoleColors.YELLOW + "Choose option: ");
        System.out.print(ConsoleColors.RESET);
        System.out.println("add");
        System.out.println("remove");
        System.out.println("list");
        System.out.println("exit");
        System.out.println(ConsoleColors.PURPLE_BRIGHT + "Write:");
        System.out.print(ConsoleColors.RESET);
    }

}