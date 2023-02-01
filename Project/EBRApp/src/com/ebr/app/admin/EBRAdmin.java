package com.ebr.app.admin;

import java.awt.BorderLayout;

import javax.swing.*;

@SuppressWarnings("serial")
public class EBRAdmin extends JFrame {

	public static final int WINDOW_WIDTH = 800;
	public static final int WINDOW_HEIGHT = 550;

	public EBRAdmin(EBRAdminController controller) {
		JPanel rootPanel = new JPanel();
		setContentPane(rootPanel);

		BorderLayout layout = new BorderLayout();
		rootPanel.setLayout(layout);

		JTabbedPane tabbedPane = new JTabbedPane();
		rootPanel.add(tabbedPane, BorderLayout.CENTER);

		// bike tabs
		JPanel regularBikePage = controller.getRegularBikePage();
		tabbedPane.addTab("Regular bike", null, regularBikePage, "Regular bike");

		JPanel eBikePage = controller.getEBikePage();
		tabbedPane.addTab("EBike", null, eBikePage, "EBike");
		
		JPanel twinBikePage = controller.getTwinBikePage();
		tabbedPane.addTab("Twin bike", null, twinBikePage, "Twin bike");

		// bike station tabs
		JPanel bikeStationpage = controller.getBikeStationPage();
		tabbedPane.addTab("Bike station", null, bikeStationpage, "Bike station");
		
		// order page tabs
		JPanel orderpage = controller.getOrderPage();
		tabbedPane.addTab("Order", null, orderpage, "Order");
		
		// bike dock tabs
		JPanel bikeDockpage = controller.getBikeDockPage();
		tabbedPane.addTab("Bike dock", null, bikeDockpage, "Bike dock");
		
		// customer tab
		JPanel customerpage = controller.getCustomerPage();
		tabbedPane.addTab("Customer", null, customerpage, "Customer");
		// order tab
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("Eco bike rental for Administrator");
		setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
		setVisible(true);
	}

	public static void main(String[] args) {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (UnsupportedLookAndFeelException e) {
			e.printStackTrace();
		}

		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				new EBRAdmin(new EBRAdminController());
			}
		});
	}
}