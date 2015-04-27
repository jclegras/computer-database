package com.excilys.main;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Properties;

import javax.xml.namespace.QName;
import javax.xml.ws.Service;

import com.excilys.command.CommandToken;
import com.excilys.command.ICommand;
import com.excilys.console.ComputerDatabaseScanner;
import com.excilys.console.IComputerDatabaseContext;
import com.excilys.console.IComputerDatabaseStorage;
import com.excilys.console.impl.ComputerDatabaseContext;
import com.excilys.console.impl.ComputerDatabaseStorage;
import com.excilys.webservice.IComputerDatabase;

public class Main {
	public static void main(String[] args) throws IOException {
		final Properties prop = new Properties();
		InputStream inputStream = Main.class.getClassLoader().getResourceAsStream("webservice.properties");
		prop.load(inputStream);
		URL url = new URL(prop.getProperty("endpoint") + "?wsdl");

		QName qname = new QName(prop.getProperty("namespaceuri"),
				prop.getProperty("localpart"));

		Service service = Service.create(url, qname);

		final IComputerDatabaseContext ctx = new ComputerDatabaseContext(
				service.getPort(IComputerDatabase.class));

		ctx.setScanner(new ComputerDatabaseScanner());
		final IComputerDatabaseStorage store = new ComputerDatabaseStorage();
		ICommand command;
		while (ctx.getScanner().hasNextToken()) {
			command = CommandToken.getCommand(ctx.getScanner().getNextToken());
			command.execute(store, ctx);
		}
	}
}
