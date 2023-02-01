package com.ebr;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;

// bike services
import com.ebr.service.bikes.BikeService;
import com.ebr.service.bikes.RegularBikeService;
import com.ebr.service.bikes.EBikeService;
import com.ebr.service.bikes.TwinBikeService;

// subdivision services
import com.ebr.service.subdivisions.DistrictService;
import com.ebr.service.subdivisions.ProvinceService;
import com.ebr.service.subdivisions.WardService;

// bike station services
import com.ebr.service.bikestation.BikeStationService;

// bike dock services
import com.ebr.service.bikestation.BikeDockService;

// customer services
import com.ebr.service.customer.CustomerService;
import com.ebr.service.customer.CardService;

// order service
import com.ebr.service.order.OrderService;

// bank service
import com.ebr.service.bank.BankService;

public class EBRServer {
	public static final int PORT = 8080;
	
	public static void main(String[] args) throws Exception {
		ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);
		context.setContextPath("/");

		Server jettyServer = new Server(PORT);
		jettyServer.setHandler(context);

		ServletHolder jerseyServlet = context.addServlet(org.glassfish.jersey.servlet.ServletContainer.class, "/*");
		jerseyServlet.setInitOrder(0);

		jerseyServlet.setInitParameter("jersey.config.server.provider.classnames",
				// subdivision services
				ProvinceService.class.getCanonicalName() + ", " +
				DistrictService.class.getCanonicalName() + ", " +
				WardService.class.getCanonicalName() + ", " +
				// bike services
				BikeService.class.getCanonicalName() + ", " + 
				RegularBikeService.class.getCanonicalName() + ", " + 
				EBikeService.class.getCanonicalName() + ", " +
				TwinBikeService.class.getCanonicalName() + ", " +
				// bike station service
				BikeStationService.class.getCanonicalName() + ", " +
				// bike dock service
				BikeDockService.class.getCanonicalName() + ", " +
				// customer services
				CustomerService.class.getCanonicalName() + ", " +
				CardService.class.getCanonicalName() + ", " +
				// order service
				OrderService.class.getCanonicalName() + ", " +
				// bank service
				BankService.class.getCanonicalName()
		);

		
		try {
			jettyServer.start();
			jettyServer.join();
			System.out.println("Server started on port: " + PORT);
		} catch (Exception e) {
			System.out.println("Errors when starting server: " + e);
			jettyServer.stop();
			jettyServer.destroy();
	  }
	}
}