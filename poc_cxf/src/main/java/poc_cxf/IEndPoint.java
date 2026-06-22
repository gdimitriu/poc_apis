package poc_cxf;

import poc_cxf.adapters.StudentMapAdapter;
import poc_cxf.model.IStudent;

import jakarta.jws.WebService;
import jakarta.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.util.Map;

@WebService
public interface IEndPoint {
    public String alive();
    public String hello(String name);
    public String helloStudent(IStudent student);
    @XmlJavaTypeAdapter(StudentMapAdapter.class)
    public Map<Integer,IStudent> getStudents();
}
