package demo.restful;

import org.apache.cxf.jaxrs.JAXRSServerFactoryBean;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class CategoryServiceServer {
    public static void main(String[] args) {
        CategoryService categoryService = new CategoryService();
        JAXRSServerFactoryBean restServer = new JAXRSServerFactoryBean();
        restServer.setResourceClasses(Category.class);
        restServer.setServiceBean(categoryService);
        restServer.setAddress("http://localhost:9000/");
        restServer.create();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        try {
            br.readLine();
        } catch (IOException e) {

        }
        System.out.println("Server stopped!");
        System.exit(0);
    }
}
