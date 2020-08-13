import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.util.Scanner;

public class MainThread {
    static CmdHandler cmdHandler = new CmdHandler();
    public static void main(String[] args) throws IOException {

        //Port
        int port = 8800;

        //Tao server voi Localhost:8800
        HttpServer server = HttpServer.create(new InetSocketAddress(port), 0);

        //Tao root va add Handle
        server.createContext("/api", apiHandle);

        //Executor mac dinh
        server.setExecutor(null);

        //start Server
        server.start();
        System.out.println("Server running on port: " + server.getAddress().getPort());
    }

    //Class Handlele
    static HttpHandler apiHandle = httpExchange -> {
        String resp;

        if ("POST".equals(httpExchange.getRequestMethod())) {
            //POST method voi json: result
            resp = "Hello! this is POST ";
            InputStream in = httpExchange.getRequestBody();
            Scanner s = new Scanner(in).useDelimiter("\\A");
            String result = s.hasNext() ? s.next() : "";
            System.out.println(result);
            resp = cmdHandler.handle(result);
        } else {
            resp = "Method " + httpExchange.getRequestMethod() + " not allowed!";
        }

        //Reurn du lieu
        httpExchange.sendResponseHeaders(200, resp.getBytes().length);
        OutputStream out = httpExchange.getResponseBody();
        out.write(resp.getBytes());
        out.flush();
        httpExchange.close();
    };
}
