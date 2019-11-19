import com.sun.net.httpserver.HttpContext;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;

import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.util.concurrent.Executor;

public class SMBRESTServer {
    public static final int DEFAULT_PORT = 80;
    public static final String DEFAULT_PATH = "/";
    public static final HttpHandler DEFAULT_HANDLER = new SMBRESTServerHandler();
    private static final int NO_BACKLOG = 0;

    private int port = DEFAULT_PORT;
    private String path = DEFAULT_PATH;
    private boolean serverRuns;

    private HttpHandler handler = DEFAULT_HANDLER;
    private HttpServer server;

    SMBRESTServer() {

    }

    void start() {
        if (port < 0 || port > 65535) {
            throw new IllegalArgumentException("port out of range");
        }
        try {
            server = HttpServer.create(new InetSocketAddress(port), NO_BACKLOG);
            server.createContext(path, handler);
            server.setExecutor(null); // creates a default executor
            server.start();
            serverRuns = true;
        } catch (IOException e) {
            e.printStackTrace();
            serverRuns = false;
        }
    }

    void stop() {
        if (serverRuns) {
            server.stop(0);
        }
    }

}
