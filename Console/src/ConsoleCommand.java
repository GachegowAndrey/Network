import javax.swing.*;
import java.awt.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;


public class ConsoleCommand
{

    ConsoleCommand() throws IOException {
        String commands;
//        boolean exit = false;
//        while (exit == false)
//        {
////            System.out.println("Choose actions  and enter one of the commands: ADD,  LIST, EXIT ");
////
////            JOptionPane.showMessageDialog("l","Choose actions  and enter one of the commands: ADD,  LIST, EXIT ",
////                    "Ошибка!",JOptionPane.PLAIN_MESSAGE);
//            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
//            commands = reader.readLine();
//            switch (commands)
//            {
//                case ("ADD"): {
//                    System.out.println("Enter email");
//
//                    break;
//                }
//                case ("LIST"): {
//                    System.out.println("ADD"+"\n"+"LIST"+"\n"+"EXIT");
//                    break;
//                }
//                case ("EXIT"): {
//                    exit = true;
//                    break;
//                }
//                default: {
//                    System.out.println("SORRY! Try again.");
//                }
//            }
//        }

    }

    public static void main(String[] args) throws IOException {

       ConsoleCommand command= new ConsoleCommand();
    }
    static int spc_count=-1;
    static void Process(File aFile) {
        spc_count++;
        JPanel mainPanel = null;
        String spcs = "";
        for (int i = 0; i < spc_count; i++)
            spcs += " ";
        if(aFile.isFile())
//            System.out.println(spcs + "[FILE] " + aFile.getName());
        JOptionPane.showMessageDialog(mainPanel,spcs + "[FILE] " + aFile.getName(),
                "LIST",JOptionPane.PLAIN_MESSAGE);
        else if (aFile.isDirectory()) {
//            System.out.println(spcs + "[DIR] " + aFile.getName());
            JOptionPane.showMessageDialog(mainPanel,spcs + "[DIR] " + aFile.getName(),
                    "LIST",JOptionPane.PLAIN_MESSAGE);
            File[] listOfFiles = aFile.listFiles();
            if(listOfFiles!=null) {
                for (int i = 0; i < listOfFiles.length; i++)
                    Process(listOfFiles[i]);
            } else {
//                System.out.println(spcs + " [ACCESS DENIED]");
                JOptionPane.showMessageDialog(mainPanel,spcs + " [ACCESS DENIED]",
                        "LIST",JOptionPane.PLAIN_MESSAGE);
            }
        }
        spc_count--;
    }

    public static long getFolderSize(File dir) throws NullPointerException
    {
        JPanel mainPanel = null;
        long size = 0;
        try{
        for (File file : dir.listFiles()) {
            if (file.isFile()) {
                //System.out.println(file.getName() + " " + file.length());
                size += file.length();
            }
            else
                size += getFolderSize(file);
        }

        }
        catch (NullPointerException ignored)
        {
            JOptionPane.showMessageDialog(mainPanel, " This is a File",
                    "Bag",JOptionPane.PLAIN_MESSAGE);

        }
        return size;


    }

    // метод возвращает размер файла в мегабайтах
    // длину файла делим на 1 мегабайт (1024 * 1024 байт) и узнаем количество мегабайт
    static String getFileSizeMegaBytes(File file) {
        return (double) file.length()/(1024*1024)+" mb";
    }

    // метод возвращает размер файла в килобайтах
    // длину файла делим на 1 килобайт (1024 байт) и узнаем количество килобайт
    static String getFileSizeKiloBytes(File file) {
        return (double) file.length()/1024 + " kb";
    }

    // просто вызываем метод length() и получаем размер файла в байтах
    static String getFileSizeBytes(File file) {
        return file.length() + " bytes";
    }

}

