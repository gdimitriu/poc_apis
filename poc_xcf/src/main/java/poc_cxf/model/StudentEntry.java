package poc_cxf.model;

import javax.xml.bind.annotation.XmlType;

@XmlType(name = "StudentEntry")
public class StudentEntry {
    private Integer id;
    private IStudent student;

    public StudentEntry() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public IStudent getStudent() {
        return student;
    }

    public void setStudent(IStudent student) {
        this.student = student;
    }
}
