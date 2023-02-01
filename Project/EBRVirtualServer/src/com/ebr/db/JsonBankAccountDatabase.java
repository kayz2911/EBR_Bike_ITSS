package com.ebr.db;

import java.math.BigDecimal;
import java.util.ArrayList;

// beans
import com.ebr.bean.bank.BankAccount;

// db interfaces
import com.ebr.db.interfaces.IBankAccountDatabase;

// db seed
import com.ebr.db.seed.Seed;

// utils
import com.ebr.utils.Utils;

public class JsonBankAccountDatabase implements IBankAccountDatabase {
	private static IBankAccountDatabase singleton = new JsonBankAccountDatabase();

	private ArrayList<BankAccount> bankAccounts = Seed.singleton().getBankAccounts();

	private JsonBankAccountDatabase() {
	}

	public static IBankAccountDatabase singleton() {
		return singleton;
	}

	@Override
	public BigDecimal checkBalance(BankAccount bankAccount) {
		if (bankAccount != null) {
			for (BankAccount ba : bankAccounts) {
				if (ba.equals(bankAccount)) {
					return ba.getBalance();
				}
			}
		}

		return null;
	}

	@Override
	public boolean topup(BankAccount bankAccount, BigDecimal amount) {
		if (bankAccount != null) {
			for (BankAccount ba : bankAccounts) {
				if (ba.equals(bankAccount)) {
					return ba.topup(amount);
				}
			}
		}

		return false;
	}

	@Override
	public boolean topup(String cardNumber, BigDecimal amount) {
		if (cardNumber != null) {
			for (BankAccount ba : bankAccounts) {
				if (ba.getCardNumber().equals(cardNumber)) {
					return ba.topup(amount);
				}
			}
		}

		return false;
	}

	public boolean withdraw(BankAccount bankAccount, BigDecimal amount) {
		if (bankAccount != null) {
			for (BankAccount ba : bankAccounts) {
				if (ba.equals(bankAccount)) {
					return ba.withdraw(amount);
				}
			}
		}

		return false;
	}

	@Override
	public boolean withdraw(String cardNumber, BigDecimal amount) {
		if (cardNumber != null) {
			for (BankAccount ba : bankAccounts) {
				if (ba.getCardNumber().equals(cardNumber)) {
					return ba.withdraw(amount);
				}
			}
		}

		return false;
	}
}
