package poc_cxf.tests;


import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import poc_cxf.IEndPoint;
import poc_cxf.Student;
import poc_cxf.StudentMap;
import poc_cxf.EndPointImplPortImpl;

import javax.xml.namespace.QName;
import javax.xml.ws.Endpoint;
import javax.xml.ws.Service;
import javax.xml.ws.soap.SOAPBinding;

import static org.junit.Assert.assertEquals;


public class StudentTest {
    private static QName SERVICE_NAME = new QName("http://poc_cxf/", "IEndPoint");
    private static QName PORT_NAME = new QName("http://poc_cxf/", "IEndPointPort");

    private static Service service;
    private IEndPoint iEndPointProxy;
    private EndPointImplPortImpl endPointImpl;

    @BeforeClass
    public static void init()
    {
        EndPointImplPortImpl implementor = new EndPointImplPortImpl();
        String address = "http://localhost:8080/IEndPoint";
        Endpoint.publish(address,implementor);
        service = Service.create(SERVICE_NAME);
        final String endpointAddress = "http://localhost:8080/IEndPoint";
        service.addPort(PORT_NAME, SOAPBinding.SOAP11HTTP_BINDING, endpointAddress);
    }

    @Before
    public void reinstantiateBaeldungInstances() {
        endPointImpl = new EndPointImplPortImpl();
        iEndPointProxy = service.getPort(PORT_NAME, IEndPoint.class);
    }

    @Test
    public void whenUsingHelloMethod_thenCorrect() {
        final String endpointResponse = iEndPointProxy.hello("TestEndPoint");
        final String localResponse = endPointImpl.hello("TestEndPoint");
        assertEquals(localResponse, endpointResponse);
    }

    @Test
    public void whenUsingHelloStudentMethod_thenCorrect() {
        final Student student = new Student();
        student.setName("John Doe");
        final String endpointResponse = iEndPointProxy.helloStudent(student);
        final String localResponse = endPointImpl.helloStudent(student);
        assertEquals(localResponse, endpointResponse);
    }

    @Test
    public void usingGetStudentsMethod_thenCorrect() {
        final Student student1 = new Student();
        student1.setName("Adam");
        iEndPointProxy.helloStudent(student1);

        final Student student2 = new Student();
        student2.setName("Eve");
        iEndPointProxy.helloStudent(student2);

        final StudentMap students = iEndPointProxy.getStudents();
        assertEquals("Adam", students.getEntry().get(0).getStudent().getName());
        assertEquals("Eve", students.getEntry().get(1).getStudent().getName());
    }
}