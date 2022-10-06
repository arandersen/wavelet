import java.io.IOException;
import java.net.URI;

class Handler implements URLHandler {
    // The one bit of state on the server: a number that will be manipulated by
    // various requests.
    String fruit[] = new String[] {""};
    boolean flag = false;

    public String handleRequest(URI url) {
        if (url.getPath().equals("/")) {
            return String.format("This are all the Fruits available: %d", fruit);
        } else if (url.getPath().equals("/search")) {
            String[] parameters = url.getQuery().split("=");
            if (parameters[0].equals("fruits")) {
               for(int i = 0; i < fruit.length; i++){
                    if(parameters.equals(fruit[i])){
                        flag = true;
                       return String.format("%s is already in the list", parameters);
                    }
               }
               if (flag = false){
                    fruit[fruit.length - 1] = parameters.toString();
               }
            }
             return String.format("%s have been added to the list!", parameters);
        }  else {
            System.out.println("Path: " + url.getPath());
            if (url.getPath().contains("/add")) {
                String[] parameters = url.getQuery().split("=");
                if (parameters[0].equals("fruits")) {
                    fruit[fruit.length - 1] = parameters.toString();
                    return String.format("%s have been added to the list!", parameters);
                }
            }
            return "404 Not Found!";
        }
    }
}

public class SearchEngine {
    public static void main(String[] args) throws IOException {
        if(args.length == 0){
            System.out.println("Missing port number! Try any number between 1024 to 49151");
            return;
        }

        int port = Integer.parseInt(args[0]);

        Server.start(port, new Handler());
    }
}
