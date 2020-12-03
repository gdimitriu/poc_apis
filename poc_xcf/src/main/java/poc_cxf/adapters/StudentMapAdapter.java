package poc_cxf.adapters;

import poc_cxf.model.IStudent;
import poc_cxf.model.StudentEntry;
import poc_cxf.model.StudentMap;

import javax.xml.bind.annotation.adapters.XmlAdapter;
import java.util.LinkedHashMap;
import java.util.Map;

public class StudentMapAdapter extends XmlAdapter<StudentMap, Map<Integer,IStudent>> {
    @Override
    public Map<Integer, IStudent> unmarshal(StudentMap valueMap) throws Exception {
        Map<Integer, IStudent> boundMap = new LinkedHashMap<>();
        for (StudentEntry studentEntry : valueMap.getEntries()) {
            boundMap.put(studentEntry.getId(), studentEntry.getStudent());
        }
        return boundMap;
    }

    @Override
    public StudentMap marshal(Map<Integer, IStudent> boundMap) throws Exception {
        StudentMap valueMap = new StudentMap();
        for(Map.Entry<Integer, IStudent> boundEntry : boundMap.entrySet()) {
            StudentEntry valueEntry = new StudentEntry();
            valueEntry.setStudent(boundEntry.getValue());
            valueEntry.setId(boundEntry.getKey());
            valueMap.getEntries().add(valueEntry);
        }
        return valueMap;
    }
}
