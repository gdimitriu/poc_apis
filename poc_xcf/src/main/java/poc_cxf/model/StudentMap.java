package poc_cxf.model;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import java.util.ArrayList;
import java.util.List;

@XmlType(name = "StudentMap")
public class StudentMap {
    private List<StudentEntry> entries = new ArrayList<StudentEntry>();

    @XmlElement(nillable = false, name = "entry")
    public List<StudentEntry> getEntries() {
        return entries;
    }

}
