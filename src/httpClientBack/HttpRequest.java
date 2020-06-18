package httpClientBack;


import httpClientBack.utils.FileUtils;
import httpClientFore.gui.MainFrame;

import javax.swing.*;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * this is a class to send and receive message
 *
 * @author Alireza Ghafari
 * @version 2.0
 */
public class HttpRequest {
    private URL url;
    private HttpURLConnection httpURLConnection;
    private Request myRequest;
    private long startTime;
    private long endTime;
    public static String st;

    public HttpRequest(Request myRequest) {
        this.myRequest = myRequest;
        setRequest(myRequest.getMethod(), myRequest.getUrl(), myRequest.isFollowRedirect());
        getResponse();
    }

    /**
     * a method to send request
     *
     * @param method         method of request
     * @param st             the url
     * @param followRedirect to state that url should be redirected or not
     */
    public void setRequest(String method, String st, boolean followRedirect) {
        // open url connection
        try {
            url = new URL(st);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } finally {
            try {
                httpURLConnection = (HttpURLConnection) url.openConnection();
                startTime = System.currentTimeMillis();
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

        if (myRequest.hasMessageBody()) {
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
                if (myRequest.hasMessageBody()) {
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

        httpURLConnection.setConnectTimeout(10000);
        httpURLConnection.setReadTimeout(10000);
        // its to confirm that url is valid
        try {
            httpURLConnection.getResponseCode();
            endTime = System.currentTimeMillis();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    /**
     * a method to print response
     */
    public void getResponse() {
        getResponseHeaders();

        // save response body
        String responseBody = null;
        try {
            responseBody = FileUtils.inputStreamReader(httpURLConnection.getInputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
        st = "./documentations/responses/output_" + java.time.LocalTime.now().toString().substring(0, 8);


        if(httpURLConnection.getHeaderFields().toString().contains("image/png")||httpURLConnection.getHeaderFields().toString().contains("image/jpeg")||httpURLConnection.getHeaderFields().toString().contains("image/jpg")) {
            FileUtils.saveImage(myRequest.getUrl(), st+".jpeg");
            MainFrame.responsePanel.previewPanel.previewImage();
        }else{
            myRequest.setResponseBody_PATH(st+".txt");
            FileUtils.fileOutPutStream(responseBody, myRequest.getResponseBody_PATH());
        }

        MainFrame.responsePanel.getTextArea().setText(responseBody);
        try {
            MainFrame.responsePanel.getCodeStatus().setText(httpURLConnection.getResponseCode() + " " + (httpURLConnection.getResponseMessage()));
        } catch (IOException e) {
            e.printStackTrace();
        }
        MainFrame.responsePanel.getTimeStatus().setText(endTime - startTime + "ms");
        Path filePath = Paths.get(st);
        try {
            MainFrame.responsePanel.getSizeStatus().setText(Files.size(filePath) + " bytes");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * a method to get response headers
     */
    public void getResponseHeaders() {
        for (int i = 1; i < httpURLConnection.getHeaderFields().size(); i++) {
            MainFrame.responsePanel.addKeyAndValue();
            JPanel tempPanel = (JPanel) MainFrame.responsePanel.getHeaderPanel().getComponent(i - 1);
            JTextField key = (JTextField) (((JScrollPane) tempPanel.getComponent(0)).getViewport()).getView();
            JTextField value = (JTextField) (((JScrollPane) tempPanel.getComponent(1)).getViewport()).getView();
            key.setText(httpURLConnection.getHeaderFieldKey(i));
            value.setText(httpURLConnection.getHeaderField(i));
            MainFrame.responsePanel.revalidate();
            MainFrame.responsePanel.repaint();

        }

    }

    protected void closeQuietly(Closeable closeable) {
        try {
            if (closeable != null) {
                closeable.close();
            }
        } catch (IOException ex) {

        }
    }


}
