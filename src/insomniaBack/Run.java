package insomniaBack;



public class Run {
    public static void main(String[] args){
        String url=null;
        boolean followRedirect=false;
        boolean shouldResponseHeadersBeShown=false;
        for (int i=0;i<args.length;i++) {
            if (args[i].contains("http"))
                url=args[i];
            if(args[i].equals("-f"))
                followRedirect=true;
            if(args[i].equals("-i"))
                shouldResponseHeadersBeShown=true;
        }
        if (url == null)
            System.out.println("invalid url");
        else
            new HttpRequest(url,followRedirect,shouldResponseHeadersBeShown);


    }
}
