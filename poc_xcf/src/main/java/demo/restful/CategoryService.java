package demo.restful;

import org.springframework.beans.factory.annotation.Autowired;

import javax.ws.rs.*;

@Path("/categoryservice")
@Produces("application/xml")
public class CategoryService {
    private CategoryDAO categoryDao = new CategoryDAO();

    public CategoryDAO getCategoryDAO() {
        return categoryDao;
    }

    public void setCategoryDao(CategoryDAO categoryDao) {
        this.categoryDao = categoryDao;
    }

    @GET
    @Path("/category/{id}")
    public Category getCategory(@PathParam("id") String id) {
        System.out.println("getCategory called with category id:" + id);
        return (Category) getCategoryDAO().getCategory(id);
    }

    @POST
    @Path("/category")
    @Consumes("application/xml")
    public void addCategory(Category category) {
        System.out.println("addCategory called");
        getCategoryDAO().addCategory(category);
    }

    @DELETE
    @Path("/category/{id}")
    public void deleteCategory(@PathParam("id") String id) {
        System.out.println("deleteCategory called with id=" + id);
        getCategoryDAO().deleteCategory(id);
    }

    @PUT
    @Path("/category")
    public void updateCategory(Category category) {
        System.out.println("updateCategory called with id=" + category.getCategoryId());
        getCategoryDAO().updateCategory(category);
    }

    @POST
    @Path("category/book")
    @Consumes("application/xml")
    public void addBook(Category category) {
        System.out.println("addBook with category id=" + category.getCategoryId());
        getCategoryDAO().addBook(category);
    }

    @GET
    @Path("/category/{id}/books")
    @Produces("application/xml")
    public Category getBooks(@PathParam("id") String id) {
        System.out.println("getBooks called with id=" + id);
        Category cat = (Category) getCategoryDAO().getCategory(id);
        cat.setBooks(getCategoryDAO().getBooks(id));
        return cat;
    }
}
