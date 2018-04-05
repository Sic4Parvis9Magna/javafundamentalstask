package epamtasks.exceptions.t01;

import java.util.Scanner;

public class FileMagerRunner {
    public static void main(String[] args) {
        StringBuilder text = new StringBuilder();
        text.append("Start file manager app:%")
                .append("mkdir [dirname] - for making new dirs\n")
                .append("mkfile [filename] - for making new files\n")
                .append("ls - for showing current dir content\n")
                .append("pwd - for showing current path\n")
                .append("read [filename.txt] - show file content\n")
                .append("write [fileName.txt] \"message\" - recreates and writes\n")
                .append("append [fileName.txt] \"message\" - append text to file \n")
                .append("cd [full_path] - change dir\n")
                .append("cd [\\nextDir] - change dir\n")
                .append("cd .. - for go back to previous directory\n")
                .append("exit - for closing app\n");
        boolean working =true;
        System.out.println(text);
        FileManager fm = FileManager.getDefaultManager();
        Scanner scanner = new Scanner(System.in);
        String com = "";

        do{
            System.out.println("Write a command here: ");
            com = scanner.next();

            switch (com){
                case "cd":
                    fm.goToDir(scanner.next());
                   printCurrentState(fm);
                    break;
                case "cd..":
                    fm.toPreviousDir();
                    printCurrentState(fm);
                    break;
                case "mkdir" :
                    fm.makeDir(scanner.next());
                    printCurrentState(fm);
                    break;
                case "mkfile" :
                    fm.createFile(scanner.next());
                    printCurrentState(fm);
                    break;
                case "read": fm.readFromFile(scanner.next());
                    System.out.println(fm.getContent());
                    break;
                case "write":
                    fm.writeToFile(scanner.next(),scanner.nextLine());
                    System.out.println("message was written!");
                    break;
                case "append":
                    fm.appendToFile(scanner.next(),scanner.nextLine());
                    System.out.println("message was appended!");
                    break;
                case "delete":
                    fm.deleteFile(scanner.next());
                    printCurrentState(fm);
                    break;
                case "ls":
                    System.out.println(fm.getDispFiles());
                    break;
                case "pwd":
                    System.out.println(fm.getCurrentPath());
                    break;
                case "exit" :
                    working = false;
                break;
                default:
                        System.out.println("Wrong input!");
            }


        }while (working);
        System.out.println("Program was closed");
    }

    public static void printCurrentState(FileManager fileManager){
        System.out.println(fileManager.getCurrentPath());
        System.out.println(fileManager.getDispFiles());
    }



}
