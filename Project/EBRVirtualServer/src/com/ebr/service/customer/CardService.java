package com.ebr.service.customer;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

// beans
import com.ebr.bean.customer.Card;
import com.ebr.bean.customer.Customer;
// db interfaces
import com.ebr.db.interfaces.ICardDatabase;
import com.ebr.db.interfaces.ICustomerDatabase;

// dbs
import com.ebr.db.JsonCardDatabase;
import com.ebr.db.JsonCustomerDatabase;

@Path("/cards")
public class CardService {
	private ICardDatabase cardDatabase;
	private ICustomerDatabase customerDatabase;

	public CardService() {
		cardDatabase = JsonCardDatabase.singleton();
		customerDatabase = JsonCustomerDatabase.singleton();
	}

	@GET
	@Path("/get-card-by-id")
	@Produces(MediaType.APPLICATION_JSON)
	public Card getById(@QueryParam("id") String id) {
		return cardDatabase.getById(id);
	}

	@GET
	@Path("/get-card-by-customer-id")
	@Produces(MediaType.APPLICATION_JSON)
	public Card getByCustomerId(@QueryParam("customerId") String customerId) {
		Customer customer = customerDatabase.getById(customerId);
		return cardDatabase.getById(customer.getCardId());
	}

	@POST
	@Path("/add")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Card add(Card card) {
		return cardDatabase.add(card);
	}

	@POST
	@Path("/update")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Card update(Card card) {
		return cardDatabase.update(card);
	}
}