package com.excilys.webservice.impl;

import java.net.URL;

import javax.xml.namespace.QName;
import javax.xml.ws.Service;

import com.excilys.webservice.IComputerDatabase;

public class ComputerDatabaseClient {

	public static void main(String[] args) throws Exception {

		URL url = new URL("http://localhost:9999/ws/cdbService?wsdl");

		// 1st argument service URI, refer to wsdl document above
		// 2nd argument is service name, refer to wsdl document above
		QName qname = new QName("http://impl.webservice.excilys.com/",
				"ComputerDatabaseService");

		Service service = Service.create(url, qname);

		IComputerDatabase webservice = service.getPort(IComputerDatabase.class);

		System.out.println(webservice.getByName("a"));

	}

}
