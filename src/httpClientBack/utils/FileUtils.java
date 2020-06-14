package httpClientBack.utils;

import httpClientBack.Request;

import java.io.*;

/**
 * a class to manage any operation on files
 *
 * @author Alireza Ghafari
 * @version 1.0
 */
public class FileUtils {
    private static final String Request_PATH = "./documentations/requests/";

    public static File[] getFilesInDirectory() {
        return new File(Request_PATH).listFiles();
    }

    /**
     * a method to read saved request
     *
     * @return request object
     */
    public static Request objectReader(File file) {
        Request request = null;
        try (FileInputStream fis = new FileInputStream(file);
             ObjectInputStream in = new ObjectInputStream(fis)) {
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

    /**
     * a method to save request
     *
     * @param request
     */
    public static void objectWriter(Request request) {
        String st = java.time.LocalTime.now().toString().substring(0, 8);
        try (FileOutputStream fos = new FileOutputStream(Request_PATH + request.getName() + ".bin");
             ObjectOutputStream out = new ObjectOutputStream(fos)) {
            out.writeObject(request);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * a method to save setting fields
     *
     * @param st setting fields
     */
    public static void settingWriter(String st) {
        try (FileWriter fileWriter = new FileWriter("./documentations/setting/Options.txt");
             BufferedWriter bufferedWriter = new BufferedWriter(fileWriter)) {
            bufferedWriter.write(st);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * a method to read message body
     *
     * @param inputStream stream of body
     * @return a string of body
     */
    public static String inputStreamReader(InputStream inputStream) {
        StringBuffer sb = null;
        try (InputStreamReader in = new InputStreamReader(inputStream)) {

            BufferedReader reader = new BufferedReader(in);
            sb = new StringBuffer();
            String str;
            while ((str = reader.readLine()) != null) {
                sb.append(str + "\n");
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return sb.toString();
    }


    /**
     * a method to write response message in a file
     *
     * @param content message body
     * @param path    the file path
     */
    public static void fileOutPutStream(String content, String path) {
        try (FileOutputStream fos = new FileOutputStream(path)) {
            byte[] bytes = content.getBytes();
            fos.write(bytes);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
