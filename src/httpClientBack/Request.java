package httpClientBack;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * this is a class to keep request properties
 *
 * @author Alireza Ghafari
 * @version 2.0
 */
public class Request implements Serializable {
    private String name;
    private String method = "GET";
    private ArrayList<String> headerFieldKey = new ArrayList<>();
    private ArrayList<String> headerField = new ArrayList<>();
    private String url = null;
    private boolean followRedirect = false;
    private String messageBody = "null";
    private boolean saveResponseBody = false;
    private String responseBody_PATH = null;
    private boolean hasMessageBody = false;

    public boolean hasMessageBody() {
        return hasMessageBody;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setHasMessageBody(boolean hasMessageBody) {
        this.hasMessageBody = hasMessageBody;
    }

    public void setResponseBody_PATH(String responseBody_PATH) {
        this.responseBody_PATH = responseBody_PATH;
    }

    public String getResponseBody_PATH() {
        return responseBody_PATH;
    }

    public ArrayList<String> getHeaderField() {
        return headerField;
    }

    public ArrayList<String> getHeaderFieldKey() {
        return headerFieldKey;
    }

    public String getMethod() {
        return method;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }


    public boolean isFollowRedirect() {
        return followRedirect;
    }


    public void setFollowRedirect(boolean followRedirect) {
        this.followRedirect = followRedirect;
    }

    public void setMethod(String method) {
        this.method = method;
    }


    @Override
    public String toString() {
        String st = "url: " + url + "  |  method: " + method;
        if (headerFieldKey.size() > 0) {
            st += "  |  headers: {";
            for (int i = 0; i < headerFieldKey.size(); i++)
                st += " " + headerFieldKey.get(i) + ":" + headerField.get(i) + "  ";
            st += "}  |  body: " + messageBody;
        }
        return st;
    }

    public void setSaveResponseBody(boolean saveResponseBody) {
        this.saveResponseBody = saveResponseBody;
    }

    public boolean isSaveResponseBody() {
        return saveResponseBody;
    }

    public String getMessageBody() {
        return messageBody;
    }

    public void setMessageBody(String messageBody) {
        this.messageBody = messageBody;
    }
}

