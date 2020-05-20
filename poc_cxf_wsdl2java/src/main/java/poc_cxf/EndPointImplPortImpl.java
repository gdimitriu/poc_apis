package poc_cxf;

import java.util.LinkedHashMap;
import java.util.Map;

public class EndPointImplPortImpl implements IEndPoint {
    private Map<Integer, Student> students = new LinkedHashMap<>();

    @Override
    public String alive() {
        return "I am Alive!!!";
    }

    @Override
    public String hello(String name) {
        return "Hello " + name;
    }

    @Override
    public String helloStudent(Student student) {
        students.put(students.size() + 1, student);
        return "Hello " + student.getName();
    }

    @Override
    public StudentMap getStudents() {
        StudentMap studentMap = new StudentMap();
        for (Map.Entry<Integer, Student> entry : students.entrySet()) {
            StudentEntry st = new StudentEntry();
            st.setStudent(entry.getValue());
            studentMap.getEntry().add(st);
        }
        return studentMap;
    }

}
