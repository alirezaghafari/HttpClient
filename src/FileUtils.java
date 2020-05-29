import org.w3c.dom.ls.LSOutput;

import java.io.File;

public class FileUtils {
    private static final String NOTES_PATH = "./notes/";

    static {
        boolean isSuccessful = new File(NOTES_PATH).mkdirs();
        System.out.println("Creating " + NOTES_PATH + " directory is successful: " + isSuccessful);
    }

    public static File[] getFilesInDirectory() {
        return new File(NOTES_PATH).listFiles();
    }



}
