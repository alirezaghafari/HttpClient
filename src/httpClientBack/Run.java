package httpClientBack;

import httpClientBack.utils.FileUtils;

import java.io.File;

public class Run {
    public static void main(String[] args) {
        if(args[0].equalsIgnoreCase("list")) {
            File[] files = FileUtils.getFilesInDirectory();
            for (int i = 0; i < FileUtils.getFilesInDirectory().length; i++) {
                Request myRequest = FileUtils.objectReader(files[i]);
                System.out.println(i+": "+myRequest);
            }
        }else {
            boolean saveRequest = false;
            Request newRequest = new Request();
            for (int i = 0; i < args.length; i++) {
                if (args[i].contains("-S") || args[i].contains("--save"))
                    saveRequest = true;
                if (args[i].contains("http"))
                    newRequest.setUrl(args[i]);
                if (args[i].equals("-f"))
                    newRequest.setFollowRedirect(true);
                if (args[i].equals("-i"))
                    newRequest.setShouldResponseHeadersBeShown(true);
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
