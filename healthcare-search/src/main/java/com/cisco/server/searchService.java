package com.cisco.server;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.HandlerList;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.glassfish.jersey.servlet.ServletContainer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class searchService {

    static Logger logger = LoggerFactory.getLogger(searchService.class);
    private static String filePath;

    public static void main(String[] args) throws Exception
    {
        if(args.length != 2) {
            System.out.println("USAGE: <server-port> <file-path-to-file-to-serve>");
        }

        int SERVER_PORT = Integer.parseInt(args[0]);
        filePath = args[1];

        Server server = new Server(SERVER_PORT);
        HandlerList handlers = new HandlerList();
        ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);
        context.setContextPath("/");


        // Setup API resources
        ServletHolder apiServlet = context.addServlet(ServletContainer.class, "/*");
        apiServlet.setInitOrder(1);
        apiServlet.setInitParameter("jersey.config.server.provider.packages",
                "com.cisco.services");//
        apiServlet.setInitParameter("jersey.config.server.provider.classnames",
                "org.glassfish.jersey.jackson.JacksonFeature");
        apiServlet.setInitParameter("javax.ws.rs.Application",
                "com.cisco.configuration.CustomApplication");
        
        handlers.addHandler(context);
        server.setHandler(handlers);
        logger.info("Simple Server listening on port " + SERVER_PORT);
        server.start();
        server.join();
    }

    public static String getFilePath(){
        return filePath;
    }
}
