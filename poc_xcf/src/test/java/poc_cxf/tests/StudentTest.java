package poc_cxf.tests;


import java.util.Map;

import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import javax.xml.ws.soap.SOAPBinding;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import poc_cxf.EndPointImpl;
import poc_cxf.IEndPoint;
import poc_cxf.model.IStudent;
import poc_cxf.model.StudentImpl;

import static org.junit.Assert.assertEquals;


public class StudentTest {
    private static QName SERVICE_NAME = new QName("http://poc_cxf/", "IEndPoint");
    private static QName PORT_NAME = new QName("http://poc_cxf/", "IEndPointPort");

    private static Service service;
    private IEndPoint baeldungProxy;
    private EndPointImpl baeldungImpl;

    @BeforeClass
    public static void init()
    {
        service = Service.create(SERVICE_NAME);
        final String endpointAddress = "http://localhost:8080/IEndPoint";
        service.addPort(PORT_NAME, SOAPBinding.SOAP11HTTP_BINDING, endpointAddress);
    }

    @Before
    public void reinstantiateBaeldungInstances() {
        baeldungImpl = new EndPointImpl();
        baeldungProxy = service.getPort(PORT_NAME, IEndPoint.class);
    }

    @Test
    public void whenUsingHelloMethod_thenCorrect() {
        final String endpointResponse = baeldungProxy.hello("TestEndPoint");
        final String localResponse = baeldungImpl.hello("TestEndPoint");
        assertEquals(localResponse, endpointResponse);
    }

    @Test
    public void whenUsingHelloStudentMethod_thenCorrect() {
        final IStudent student = new StudentImpl("John Doe");
        final String endpointResponse = baeldungProxy.helloStudent(student);
        final String localResponse = baeldungImpl.helloStudent(student);
        assertEquals(localResponse, endpointResponse);
    }

    @Test
    public void usingGetStudentsMethod_thenCorrect() {
        final IStudent student1 = new StudentImpl("Adam");
        baeldungProxy.helloStudent(student1);

        final IStudent student2 = new StudentImpl("Eve");
        baeldungProxy.helloStudent(student2);

        final Map<Integer, IStudent> students = baeldungProxy.getStudents();
        assertEquals("Adam", students.get(1).getName());
        assertEquals("Eve", students.get(2).getName());
    }
}