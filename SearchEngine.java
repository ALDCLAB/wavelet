import java.io.IOException;
import java.net.URI;
import java.util.*;

class Handler implements URLHandler {
    // The one bit of state on the server: a number that will be manipulated by
    // various requests.
    int num = 0;
    ArrayList<String>search = new ArrayList<String>(); 
    ArrayList<String>containSearch = new ArrayList<String>();
    String searchResult = "";
    int i = 0;
    String temp = "";

    public String handleRequest(URI url) {
        if (url.getPath().equals("/")) {
            return String.format("Hello!");
        } else {
            System.out.println("Path: " + url.getPath());
            if (url.getPath().contains("/add")) {
                String[] parameters = url.getQuery().split("=");
                if(parameters[0].equals("s")) {
                    search.add(parameters[1]);
                    return String.format(parameters[1]);
                }
            }
            else if (url.getPath().contains("/search")) {
                String[] parameters = url.getQuery().split("=");
                if(parameters[0].equals("s")) {
                    for (i = 0; i < search.size() - 1; i++) {
                        temp = search.get(i);
                        if(temp.contains(parameters[1])) {
                            containSearch.add(temp);

                        }
                    }
                    searchResult = containSearch.toString();
                    return String.format(searchResult);
                }
            return "404 Not Found!";
            }
        }
        return "Hello!";
    }
}

class SearchEngine {
    public static void main(String[] args) throws IOException {
        if(args.length == 0){
            System.out.println("Missing port number! Try any number between 1024 to 49151");
            return;
        }

        int port = Integer.parseInt(args[0]);

        Server.start(port, new Handler());
    }
}