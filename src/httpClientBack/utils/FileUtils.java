package httpClientBack.utils;

import httpClientBack.Request;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;

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
        String str="";
        try (InputStreamReader in = new InputStreamReader(inputStream)) {
            while (in.ready()) {
                str+=(char)in.read();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return str;
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


    /**
     * a method to save image in a file
     * @param imageUrl the url
     * @param destinationFile path of file
     */
    public static void saveImage(String imageUrl, String destinationFile) {
        URL url = null;
        try {
            url = new URL(imageUrl);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        InputStream is = null;
        try {
            is = url.openStream();
        } catch (IOException e) {
            e.printStackTrace();
        }
        OutputStream os = null;
        try {
            os = new FileOutputStream(destinationFile);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        byte[] b = new byte[1000000000];
        int length = 0;

        while (true) {
            try {
                if (!((length = is.read(b)) != -1)) break;
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                os.write(b, 0, length);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        try {
            is.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            os.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
