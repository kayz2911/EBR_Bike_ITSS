package com.ebr.app.admin;

import javax.swing.JPanel;

import com.ebr.bean.bikes.Bike;
import com.ebr.bean.bikestation.BikeStation;
import com.ebr.bean.order.Order;
import com.ebr.bean.customer.Customer;
import com.ebr.bean.bikestation.BikeDock;
import com.ebr.components.controller.abstractdata.ADataPageController;
import com.ebr.components.controller.bikedock.AdminBikeDockPageController;
import com.ebr.components.controller.ebike.AdminEBikePageController;
import com.ebr.components.controller.order.AdminOrderPageController;
import com.ebr.components.controller.regularbike.AdminRegularBikePageController;
import com.ebr.components.controller.twinbike.AdminTwinBikePageController;
import com.ebr.components.controller.bikestation.AdminBikeStationPageController;
import com.ebr.components.controller.customer.AdminCustomerPageController;
import com.ebr.components.controller.bikedock.AdminBikeDockPageController;


public class EBRAdminController {
	public EBRAdminController() {
	}

	public JPanel getRegularBikePage() {
		ADataPageController<Bike> controller = new AdminRegularBikePageController();
		return controller.getDataPagePane();
	}

	public JPanel getEBikePage() {
		ADataPageController<Bike> controller = new AdminEBikePageController();
		return controller.getDataPagePane();
	}

	public JPanel getTwinBikePage() {
		ADataPageController<Bike> controller = new AdminTwinBikePageController();
		return controller.getDataPagePane();
	}

	public JPanel getBikeStationPage() {
		ADataPageController<BikeStation> controller = new AdminBikeStationPageController();
		return controller.getDataPagePane();
	}
	
	public JPanel getOrderPage() {
		ADataPageController<Order> controller = new AdminOrderPageController();
		return controller.getDataPagePane();
	}
	public JPanel getCustomerPage() {
		ADataPageController<Customer> controller = new AdminCustomerPageController();
		return controller.getDataPagePane();
	}
	public JPanel getBikeDockPage() {
		ADataPageController<BikeDock> controller = new AdminBikeDockPageController();
		return controller.getDataPagePane();
	}
}
