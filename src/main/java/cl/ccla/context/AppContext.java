package cl.ccla.context;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class AppContext {
	
	private AppContext() {
		
	}
	
    public static ConfigurableApplicationContext getAppContext(){
        return  new ClassPathXmlApplicationContext("Beans.xml");
    }
}