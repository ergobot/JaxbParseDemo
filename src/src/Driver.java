package src;

import java.io.File;
import javax.xml.bind.*;

public class Driver {

	/**
	 * @param args
	 * @throws JAXBException 
	 */
	public static void main(String[] args) throws JAXBException {
		
		System.out.println("Generating Output");
		
		JAXBContext jc = JAXBContext.newInstance(Testsuites.class);

        Unmarshaller unmarshaller = jc.createUnmarshaller();
        Testsuites testSuites = (Testsuites) unmarshaller.unmarshal(new File("src/src/results2.xml"));

        Marshaller marshaller = jc.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        marshaller.marshal(testSuites, System.out);
		
        System.out.println("\nCompleted xml parse\n");
        
        int errorCount = 0;
        StringBuilder sb = new StringBuilder();
        for(int i =0; i < testSuites.testsuite.testcase.size(); i++)
        {
        	if(testSuites.testsuite.testcase.get(i).failure != null){
//        	System.out.println("TestCase " + (int)(i+1) + "\n" + 
//        					   "Name: " + testSuites.testsuite.testcase.get(i).name + "\n" +
//        					   "Error: " + testSuites.testsuite.testcase.get(i).failure.message + "\n" +
//        					   "Stack: " + testSuites.testsuite.testcase.get(i).failure.type + "\n");
        						
        		sb.append("TestCase " + (int)(i+1) + "\n" + 
 					   "Name: " + testSuites.testsuite.testcase.get(i).name + "\n" +
 					   "Error: " + testSuites.testsuite.testcase.get(i).failure.message + "\n" +
 					   "Stack: " + testSuites.testsuite.testcase.get(i).failure.type + "\n");
        	errorCount++;
        	}
        	
        }
        
        System.out.println(sb.toString());
        System.out.println("Total errors: " + errorCount);
        System.out.println("All failures reported");
        
		System.out.println("Completed Generating Output");
		
	}

}
