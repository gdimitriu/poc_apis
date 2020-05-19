package poc_cxf;

import javax.xml.ws.Endpoint;

public class Server {
    public static void main(String...args) throws InterruptedException {
        EndPointImpl implementor = new EndPointImpl();
        String address = "http://localhost:8080/IEndPoint";
        Endpoint.publish(address,implementor);
        Thread.sleep(60*1000);
        System.exit(0);
    }
}
