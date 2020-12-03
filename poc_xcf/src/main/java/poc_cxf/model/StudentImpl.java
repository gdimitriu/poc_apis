package poc_cxf.model;

import javax.xml.bind.annotation.XmlType;

@XmlType(name = "Student")
public class StudentImpl implements IStudent {
    private String name;

    public StudentImpl() {

    }
    public StudentImpl(String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
