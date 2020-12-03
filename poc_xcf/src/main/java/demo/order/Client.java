package demo.order;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Client {
    public static void main(String args[]) throws Exception {
        // START SNIPPET: client
        ApplicationContext context
                = new ClassPathXmlApplicationContext(new String[] {"client-beans.xml"});

        OrderProcess client = (OrderProcess) context.getBean("orderClient");
        // Populate the Order bean
        Order order = new Order();
        order.setCustomerID("C001");
        order.setItemId("I001");
        order.setQty(100);
        order.setPrice(200.00);
        String orderID = client.processOrder(order);
        String message = (orderID == null) ? "Order not approved" : "Order approved; order ID is " + orderID;
        System.out.println(message);
        System.exit(0);
    }
}
