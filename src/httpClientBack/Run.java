package httpClientBack;

import httpClientBack.utils.FileUtils;

import java.io.File;

/**
 * this is the Main class to run program under console user interface
 * @author Alireza Ghafari
 * @version 2.0
 */
public class Run {
    public static void main(String[] args) {
        boolean helpMenu=false;
        if (args[0].equalsIgnoreCase("list")) {
            File[] files = FileUtils.getFilesInDirectory();
            for (int i = 0; i < FileUtils.getFilesInDirectory().length; i++) {
                Request myRequest = FileUtils.objectReader(files[i]);
                System.out.println(i + 1 + ": " + myRequest);
            }

        } else if (args[0].equalsIgnoreCase("fire")) {
            for(int i =1 ; i<args.length;i++)
                new HttpRequest(FileUtils.objectReader(FileUtils.getFilesInDirectory()[Integer.parseInt(args[i])-1]));
        } else {
            boolean saveRequest = false;
            Request newRequest = new Request();
            for (int i = 0; i < args.length; i++) {
                if (args[i].contains("-S") || args[i].contains("--save"))
                    saveRequest = true;
                if (args[i].contains("http"))
                    newRequest.setUrl(args[i]);
                if (args[i].equals("-f"))
                    newRequest.setFollowRedirect(true);
                if (args[i].equals("--output")||args[i].equals("-O")) {
                    newRequest.setSaveResponseBody(true);
                    if ((i+1)!=args.length&&((!args[i+1].equalsIgnoreCase("-f"))&&(!args[i+1].equalsIgnoreCase("-H"))&&
                            (!args[i+1].equalsIgnoreCase("--headers"))&&(!args[i+1].equalsIgnoreCase("-i"))&&(!args[i+1].contains("http."))&&
                            (!args[i+1].equalsIgnoreCase("-S"))&&(!args[i+1].equalsIgnoreCase("--save")))&&(!args[i+1].equalsIgnoreCase("-d"))&&(!args[i+1].equalsIgnoreCase("--data")))
                        newRequest.setResponseBody_PATH(args[i+1]);
                }
                if (args[i].equals("-i"))
                    newRequest.setShouldResponseHeadersBeShown(true);
                if (args[i].equals("-h")||args[i].equals("--help"))
                    helpMenu=true;
                if (args[i].equals("-d")||args[i].equals("--data")) {
                    newRequest.setMessageBody(args[i + 1]);
                    newRequest.setHasMessageBody(true);
                }
                if (args[i].equals("-H") || args[i].equals("--headers")) {
                    String st = null;
                    try {
                        st = args[i + 1];
                    } catch (Exception e) {
                        System.out.println("invalid input");
                    }

                    String key = st.substring(0, st.indexOf(':'));
                    newRequest.getHeaderFieldKey().add(key);

                    String value = st.substring(st.indexOf(':') + 1);
                    newRequest.getHeaderField().add(value);
                }
                if (args[i].equals("-M") || args[i].equals("--method"))
                    try {
                        newRequest.setMethod(args[i + 1].toUpperCase());
                    } catch (Exception e) {
                        System.out.println("invalid input");
                    }

            }
            if(helpMenu)
                System.out.println("\n\nHint:\n\nYou must enter url in http://... format\n-M: your request method\n--method: your request method\n-H: your request headers (headers should write in {key:value} format\n" +
                        "--headers: your request headers (headers should write in {key:value} format\n-i: it states that response headers should be printed or not\n-f: url should be redirected automatically or not\n-O: to save response body\n--output: to save response body\n" +
                        "--save: to save request properties\n-S: to save request properties\n--data: to sent request body\n-d: to send request body\nlist: to show your previous requests\nfire: to send your previous request\n\n  ");
            if (saveRequest) {
                FileUtils.objectWriter(newRequest);
            }

            if (newRequest.getUrl() == null)
                System.out.println("invalid url");
            else
                new HttpRequest(newRequest);
        }

    }
}
