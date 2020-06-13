package httpClientBack;


import httpClientBack.utils.FileUtils;

import java.io.Closeable;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

/**
 * this is a class to send and receive message
 * @author Alireza Ghafari
 * @version 2.0
 */
public class HttpRequest {
    private URL url;
    private HttpURLConnection httpURLConnection;
    private Request myRequest;
    public HttpRequest(Request myRequest) {
        this.myRequest=myRequest;
        setRequest(myRequest.getMethod(),myRequest.getUrl(), myRequest.isFollowRedirect());
        getResponse(myRequest.isShouldResponseHeadersBeShown());
    }

    /**
     * a method to send request
     * @param method method of request
     * @param st the url
     * @param followRedirect to state that url should be redirected or not
     */
    public void setRequest(String method,String st, boolean followRedirect) {
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

        // set request method
        try {
            httpURLConnection.setRequestMethod(method);
            httpURLConnection.setDoOutput(true);
        } catch (ProtocolException e) {
            e.printStackTrace();
        }

        if(myRequest.hasMessageBody()){
            DataOutputStream wr = null;
            try {
                wr = new DataOutputStream(httpURLConnection.getOutputStream());
                wr.writeBytes(myRequest.getMessageBody());
                wr.flush();
                wr.close();
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                this.closeQuietly(wr);
            }
        }

        // a loop to follow url redirects
        if (followRedirect)
            while (httpURLConnection.getHeaderFields().containsKey("Location")) {
                String newUrl = httpURLConnection.getHeaderField("Location");
                try {
                    url = new URL(newUrl);
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                }
                try {
                    httpURLConnection = (HttpURLConnection) url.openConnection();

                    // set request headers
                    for (int i = 0; i < myRequest.getHeaderFieldKey().size(); i++)
                        httpURLConnection.setRequestProperty(myRequest.getHeaderFieldKey().get(i), myRequest.getHeaderField().get(i));


                } catch (IOException e) {
                    e.printStackTrace();
                }
                if(myRequest.hasMessageBody()){
                    DataOutputStream wr = null;
                    try {
                        wr = new DataOutputStream(httpURLConnection.getOutputStream());
                        wr.writeBytes(myRequest.getMessageBody());
                        wr.flush();
                        wr.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    } finally {
                        this.closeQuietly(wr);
                    }
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

    /**
     * a method to print response
     * @param shouldResponseHeadersBeShown should response headers be shown or not
     */
    public void getResponse(boolean shouldResponseHeadersBeShown) {
        if (shouldResponseHeadersBeShown) {
            getResponseHeaders();
        }

        // save response body
        String responseBody=null;
        try {
            responseBody=FileUtils.inputStreamReader(httpURLConnection.getInputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
        if(myRequest.isSaveResponseBody()){
            if(myRequest.getResponseBody_PATH()==null) {
                String st=java.time.LocalTime.now().toString().substring(0,8);
                myRequest.setResponseBody_PATH("../documentations/responses/output_" +st);
            }
            FileUtils.fileOutPutStream(responseBody,myRequest.getResponseBody_PATH());
        }


        System.out.println(responseBody);
    }

    /**
     * a method to get response headers
     */
    public void getResponseHeaders() {
        for (int i = 0; i < httpURLConnection.getHeaderFields().size(); i++)
            if (i == 0)
                System.out.println(httpURLConnection.getHeaderField(i));
            else
                System.out.println(httpURLConnection.getHeaderFieldKey(i) + ": " + httpURLConnection.getHeaderField(i));

    }
    protected void closeQuietly(Closeable closeable) {
        try {
            if( closeable != null ) {
                closeable.close();
            }
        } catch(IOException ex) {

        }
    }


}
