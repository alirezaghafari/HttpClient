package httpClientBack;

import java.io.Serializable;
import java.util.ArrayList;

public class Request implements Serializable {
    private String method = "GET";
    private ArrayList<String> headerFieldKey = new ArrayList<>();
    private ArrayList<String> headerField = new ArrayList<>();
    private String url = null;
    private boolean followRedirect = false;
    private boolean shouldResponseHeadersBeShown = false;
    private String messageBody="null";


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


    public boolean isShouldResponseHeadersBeShown() {
        return shouldResponseHeadersBeShown;
    }

    public boolean isFollowRedirect() {
        return followRedirect;
    }


    public void setFollowRedirect(boolean followRedirect) {
        this.followRedirect = followRedirect;
    }

    public void setHeaderField(ArrayList<String> headerField) {
        this.headerField = headerField;
    }

    public void setMethod(String method) {
        this.method = method;
    }


    public void setHeaderFieldKey(ArrayList<String> headerFieldKey) {
        this.headerFieldKey = headerFieldKey;
    }

    public void setShouldResponseHeadersBeShown(boolean shouldResponseHeadersBeShown) {
        this.shouldResponseHeadersBeShown = shouldResponseHeadersBeShown;
    }

    @Override
    public String toString() {
        String st= "url: "+url+"  |  method: "+method;
        if(headerFieldKey.size()>0) {
            st+="  |  headers: {";
            for (int i = 0; i < headerFieldKey.size();i++)
                st+=" "+headerFieldKey.get(i)+":"+headerField.get(i)+"  ";
            st+="}  |  body: "+messageBody;
        }
        return st;
    }
}

