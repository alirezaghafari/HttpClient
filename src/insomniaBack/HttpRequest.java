package insomniaBack;



import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class HttpRequest {
    private URL url;
    HttpURLConnection httpURLConnection;

    public HttpRequest(String url, boolean followRedirect, boolean shouldResponseHeadersBeShown){
        setUrl(url,followRedirect);
        getResponse(shouldResponseHeadersBeShown);
    }

    public void setUrl(String st,boolean followRedirect) {
        // open url connection
        try {
            url = new URL(st);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } finally {
            try {
                httpURLConnection = (HttpURLConnection) url.openConnection();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        // a loop to follow url redirects
        if(followRedirect)
            while(httpURLConnection.getHeaderFields().containsKey("Location")) {
                String newUrl = httpURLConnection.getHeaderField("Location");
                try {
                    url = new URL(newUrl);
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                }
                try {
                    httpURLConnection=(HttpURLConnection)url.openConnection();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        else {
            HttpURLConnection.setFollowRedirects(false);
            httpURLConnection.setInstanceFollowRedirects(false);
        }

        // its to confirm that url is valid
        try {
            httpURLConnection.getResponseCode();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void getResponse(boolean shouldResponseHeadersBeShown) {
        if (shouldResponseHeadersBeShown) {
            getResponseHeaders();
        }
    }

    public void getResponseHeaders(){
        for (int i = 0; i < httpURLConnection.getHeaderFields().size(); i++)
            if(i==0)
                System.out.println(httpURLConnection.getHeaderField(i));
            else
                System.out.println(httpURLConnection.getHeaderFieldKey(i) + ": " + httpURLConnection.getHeaderField(i));
    }



}
