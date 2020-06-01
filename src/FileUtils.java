import java.io.File;

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






}
