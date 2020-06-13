package httpClientBack.utils;

import httpClientBack.Request;

import java.io.*;

/**
 * a class to manage any operation on files
 * @author Alireza Ghafari
 * @version 1.0
 */
public class FileUtils {
    private static final String Request_PATH = "../documentations/requests/";

    static {
       new File(Request_PATH).mkdirs();
    }

    public static File[] getFilesInDirectory() {
        return new File(Request_PATH).listFiles();
    }

    public static Request objectReader(File file){
        Request request=null;
        try(FileInputStream fis = new FileInputStream(file);
            ObjectInputStream in = new ObjectInputStream(fis)){
            request = (Request) in.readObject();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return request;
    }

    public static void objectWriter(Request request){
        String st=java.time.LocalTime.now().toString().substring(0,8);
        try (FileOutputStream fos = new FileOutputStream(Request_PATH +"req"+st+".bin");
             ObjectOutputStream out = new ObjectOutputStream(fos)){
            out.writeObject(request);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
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
