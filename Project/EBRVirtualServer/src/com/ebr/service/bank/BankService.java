package com.ebr.service.bank;

import java.math.BigDecimal;
import java.util.ArrayList;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

// beans
import com.ebr.bean.bank.BankAccount;

// db interfaces
import com.ebr.db.interfaces.IBankAccountDatabase;

// dbs
import com.ebr.db.JsonBankAccountDatabase;

@Path("/bank")
public class BankService {
	private IBankAccountDatabase bankAccountDatabase;

	public BankService() {
		bankAccountDatabase = JsonBankAccountDatabase.singleton();
	}

	@POST
	@Path("/check-balance")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public BigDecimal checkBalance(BankAccount bankAccount) {
		return bankAccountDatabase.checkBalance(bankAccount);
	}

	@POST
	@Path("/withdraw/{amount}")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public boolean withdraw(@PathParam("amount") BigDecimal amount, BankAccount bankAccount) {
		return bankAccountDatabase.withdraw(bankAccount, amount);
	}

	@POST
	@Path("/topup/{amount}")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public boolean topup(@PathParam("amount") BigDecimal amount, BankAccount bankAccount) {
		return bankAccountDatabase.topup(bankAccount, amount);
	}
}