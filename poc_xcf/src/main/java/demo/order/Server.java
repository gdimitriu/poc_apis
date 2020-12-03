package demo.order;

import javax.xml.ws.Endpoint;

public class Server {
    public static void main(String... args) throws InterruptedException {
        OrderProcessImpl implementor = new OrderProcessImpl();
        String address = "http://localhost:8080/OrderProcess";
        Endpoint.publish(address, implementor);
        Thread.sleep(600 * 1000);
        System.exit(0);
    }

}
