package poc_cxf;

import poc_cxf.model.IStudent;

import javax.jws.WebService;
import java.util.LinkedHashMap;
import java.util.Map;

@WebService(endpointInterface = "poc_cxf.IEndPoint")
public class EndPointImpl  implements  IEndPoint {

    private Map<Integer, IStudent> students = new LinkedHashMap<>();

    @Override
    public String alive() {
        return "I am Alive!!!";
    }

    @Override
    public String hello(String name) {
        return "Hello " + name;
    }

    @Override
    public String helloStudent(IStudent student) {
        students.put(students.size() + 1, student);
        return "Hello " + student.getName();
    }

    @Override
    public Map<Integer, IStudent> getStudents() {
        return students;
    }
}
