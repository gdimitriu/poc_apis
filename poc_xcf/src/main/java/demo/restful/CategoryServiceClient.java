package demo.restful;

import org.apache.cxf.jaxrs.client.WebClient;

import java.util.Iterator;

public class CategoryServiceClient {
    public static void main(String[] args) {
        //service Instance
        WebClient client = WebClient.create("http://localhost:9000/");
        Category category = client.path("categoryservice/category/001").accept("application/xml").get(Category.class);
        System.out.println("Category details from REST service.");
        System.out.println("Category Name " + category.getCategoryName());
        System.out.println("Category Id " + category.getCategoryId());
        System.out.println("Books in the category id " + category.getCategoryId() + " from REST service");

        WebClient bookClient = WebClient.create("http://localhost:9000");
        Category categoryBooks = bookClient.path("categoryservice/category/" + category.getCategoryId() + "/books")
                .accept("application/xml").get(Category.class);

        Iterator<Book> iterator = categoryBooks.getBooks().iterator();
        while(iterator.hasNext()) {
            Book book = iterator.next();
            System.out.println("Book name = " + book.getBookName());
            System.out.println("Book author = " + book.getAuthor());
            System.out.println("Book id = " + book.getBookId());
            System.out.println("Book ISBN = " + book.getBookISBNNumber());
        }
    }
}
