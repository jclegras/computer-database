package com.excilys.publisher;

import com.excilys.webserviceimpl.ComputerDatabase;
import org.jboss.jandex.Main;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.xml.ws.Endpoint;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ComputerDatabasePublisher {
	public static void main(String[] args) throws IOException {
		final Properties prop = new Properties();
		InputStream inputStream = Main.class.getClassLoader().getResourceAsStream("webservice.properties");
		prop.load(inputStream);
		final ApplicationContext context = new ClassPathXmlApplicationContext(
				prop.getProperty("context"));
		Endpoint.publish(prop.getProperty("endpoint"),
				context.getBean(ComputerDatabase.class));
	}
}
