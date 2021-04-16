package demo.restful;

import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import javax.ws.rs.WebApplicationException;

import org.apache.cxf.jaxrs.impl.ResponseBuilderImpl;

@Path("/categoryservice")
@Produces({"application/json","application/xml"})
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
    @Produces({"application/json","application/xml"})
    public Category getCategory(@PathParam("id") String id) {
        System.out.println("getCategory called with category id:" + id);
        Category cat = (Category) getCategoryDAO().getCategory(id);
        if (cat == null) {
            ResponseBuilderImpl builder = new ResponseBuilderImpl();
            builder.status(Response.Status.BAD_REQUEST);
            builder.type("application/xml");
            builder.entity("<error>Category Not Found</error>");
            throw new WebApplicationException(Response.Status.BAD_REQUEST);
        } else {
            return cat;
        }
    }

    @POST
    @Path("/category")
    @Consumes({"application/json","application/xml"})
    public Response addCategory(Category category) {
        System.out.println("addCategory called");
        Category cat = (Category) getCategoryDAO().getCategory(category.getCategoryId());
        if (cat != null) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        } else {
            getCategoryDAO().addCategory(category);
            return Response.ok(category).build();
        }
    }

    @DELETE
    @Path("/category/{id}")
    public Response deleteCategory(@PathParam("id") String id) {
        System.out.println("deleteCategory called with id=" + id);
        Category cat = (Category) getCategoryDAO().getCategory(id);
        if (cat == null) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        } else {
            getCategoryDAO().deleteCategory(id);
            return Response.ok().build();
        }
    }

    @PUT
    @Path("/category")
    public Response updateCategory(Category category) {
        System.out.println("updateCategory called with id=" + category.getCategoryId());
        Category cat = (Category) getCategoryDAO().getCategory(category.getCategoryId());
        if (cat == null) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        } else {
            getCategoryDAO().updateCategory(category);
            return Response.ok(category).build();
        }
    }

    @POST
    @Path("category/book")
    @Consumes({"application/json","application/xml"})
    public Response addBook(Category category) {
        System.out.println("addBook with category id=" + category.getCategoryId());

        Category cat = (Category) getCategoryDAO().getCategory(category.getCategoryId());
        if (cat == null) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        } else {
            getCategoryDAO().addBook(category);
            return Response.ok(category).build();
        }
    }

    @GET
    @Path("/category/{id}/books")
    @Produces({"application/json","application/xml"})
    public Category getBooks(@PathParam("id") String id) {
        System.out.println("getBooks called with id=" + id);
        Category cat = (Category) getCategoryDAO().getCategory(id);
        cat.setBooks(getCategoryDAO().getBooks(id));
        return cat;
    }
}
