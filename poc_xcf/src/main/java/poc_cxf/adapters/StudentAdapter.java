package poc_cxf.adapters;

import poc_cxf.model.IStudent;
import poc_cxf.model.StudentImpl;

import javax.xml.bind.annotation.adapters.XmlAdapter;

public class StudentAdapter extends XmlAdapter<StudentImpl, IStudent> {
    @Override
    public IStudent unmarshal(StudentImpl student) throws Exception {
        return student;
    }

    @Override
    public StudentImpl marshal(IStudent student) throws Exception {
        if (student instanceof StudentImpl)
            return (StudentImpl)student;
        return new StudentImpl(student.getName());
    }
}
