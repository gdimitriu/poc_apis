package demo.order;

import jakarta.jws.WebMethod;
import jakarta.jws.WebService;

@WebService
public interface OrderProcess {
    @WebMethod
    String processOrder(Order order);
}
