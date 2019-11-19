import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.*;

public class SMBRESTServerHandler implements HttpHandler {

    SMBRESTServerHandler() {

    }

    @Override
    public void handle(HttpExchange t) throws IOException {
        InputStream is = t.getRequestBody();
        InputStreamReader isr = new InputStreamReader(is);
        BufferedReader br = new BufferedReader(isr);
        String response = "This is the default response\r\n";
        String body = br.readLine();
        if (body != null) {
            for (String part : body.split(",")) {
                String[] split = part.split(":");
                if (split[0].contains("TARGET")) {
                    response += split[1].replace("}","");
                }
            }
        }

        t.sendResponseHeaders(200, response.length());
        OutputStream os = t.getResponseBody();
        os.write(response.getBytes());
        os.close();
    }

}
