package demo.order;

import javax.jws.WebService;

@WebService
public class OrderProcessImpl implements  OrderProcess{
    @Override
    public String processOrder(Order order) {
        String orderID = validate(order);
        return orderID;
    }

    /**
     * validate the order and return the orderID
     * @param order
     * @return
     */
    private String validate(Order order) {
        String custID = order.getCustomerID();
        String itemID = order.getItemId();
        int qty = order.getQty();
        double price = order.getPrice();
        if (custID != null && itemID != null && !custID.equals("") && !itemID.equals("")
            && qty > 0 && price > 0.0) {
            return "ORD1234";
        }
        return null;
    }
}
