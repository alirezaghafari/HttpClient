package insomniaFore.utils;

import java.io.*;

/**
 * a class to manage any operation on files
 * @author Alireza Ghafari
 * @version 1.0
 */
public class FileUtils {
    private static final String NOTES_PATH = "./documentations/requests/";

    static {
        boolean isSuccessful = new File(NOTES_PATH).mkdirs();
        System.out.println("Creating " + NOTES_PATH + " directory is successful: " + isSuccessful);
    }

    public static File[] getFilesInDirectory() {
        return new File(NOTES_PATH).listFiles();
    }


    public static void settingWriter(String st){
        try(FileWriter fileWriter=new FileWriter("./documentations/setting/Options.txt");
            BufferedWriter bufferedWriter=new BufferedWriter(fileWriter)) {
            bufferedWriter.write(st);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }






}
