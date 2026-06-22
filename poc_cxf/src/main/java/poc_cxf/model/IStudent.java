package poc_cxf.model;

import poc_cxf.adapters.StudentAdapter;

import jakarta.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

@XmlJavaTypeAdapter(StudentAdapter.class)
public interface IStudent {
    public String getName();
}
