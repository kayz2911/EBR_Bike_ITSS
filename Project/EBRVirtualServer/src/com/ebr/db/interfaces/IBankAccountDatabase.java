package com.ebr.db.interfaces;

import java.math.BigDecimal;
import java.util.ArrayList;

// beans
import com.ebr.bean.bank.BankAccount;

public interface IBankAccountDatabase {
	public BigDecimal checkBalance(BankAccount bankAccount);

	public boolean topup(BankAccount bankAccount, BigDecimal amount);

	public boolean topup(String cardNumber, BigDecimal amount);

	public boolean withdraw(BankAccount bankAccount, BigDecimal amount);

	public boolean withdraw(String cardNumber, BigDecimal amount);
}
