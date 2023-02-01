package com.ebr.bean.bank;

import java.math.BigDecimal;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonInclude(Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class BankAccount {
	private String id;
	private String cardholderName;
	private String cardNumber;
	private String issuingBank;
	@JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss")
	private Date expirationDate;
	@JsonProperty("CVC")
	private int CVC;
	private BigDecimal balance;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getCardholderName() {
		return cardholderName;
	}

	public void setCardholderName(String cardholderName) {
		this.cardholderName = cardholderName;
	}

	public String getCardNumber() {
		return cardNumber;
	}

	public void setCardNumber(String cardNumber) {
		this.cardNumber = cardNumber;
	}

	public String getIssuingBank() {
		return issuingBank;
	}

	public void setIssuingBank(String issuingBank) {
		this.issuingBank = issuingBank;
	}

	public Date getExpirationDate() {
		return expirationDate;
	}

	public void setExpirationDate(Date expirationDate) {
		this.expirationDate = expirationDate;
	}

	public void setCVC(int CVC) {
		this.CVC = CVC;
	}

	public BigDecimal getBalance() {
		return balance;
	}

	public void setBalance(BigDecimal balance) {
		this.balance = balance;
	}

	public boolean isCardExpired() {
		return this.expirationDate.after(new Date());
	}

	public BankAccount() {
		super();
	}

	public BankAccount(String cardholderName, String cardNumber, String issuingBank, Date expirationDate, int CVC) {
		super();
		this.cardholderName = cardholderName;
		this.cardNumber = cardNumber;
		this.issuingBank = issuingBank;
		this.expirationDate = expirationDate;
		this.CVC = CVC;
	}

	public BankAccount(String id, String cardholderName, String cardNumber, String issuingBank, Date expirationDate,
			int CVC) {
		super();
		this.id = id;
		this.cardholderName = cardholderName;
		this.cardNumber = cardNumber;
		this.issuingBank = issuingBank;
		this.expirationDate = expirationDate;
		this.CVC = CVC;
	}

	public boolean topup(BigDecimal amount) {
		this.setBalance(this.balance.add(amount));
		return true;
	}

	public boolean withdraw(BigDecimal amount) {
		if (amount.compareTo(this.balance) == 0 || amount.compareTo(this.balance) == -1) {
			this.setBalance(this.balance.subtract(amount));
			return true;
		}
		return false;
	}

	@Override
	public String toString() {
		return "id: " + this.id + ", cardholderName: " + this.cardholderName + ", cardNumber: " + this.cardNumber
				+ ", issuingBank: " + this.issuingBank + ", expirationDate: " + this.expirationDate.toString();
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null)
			return false;

		if (!(obj instanceof BankAccount))
			return false;

		BankAccount bankAccount = (BankAccount) obj;

		if (bankAccount.cardholderName != null && !bankAccount.cardholderName.equals("")
				&& !this.cardholderName.equals(bankAccount.cardholderName))
			return false;

		if (bankAccount.cardNumber != null && !bankAccount.cardNumber.equals("")
				&& !this.cardNumber.equals(bankAccount.cardNumber))
			return false;

		if (bankAccount.issuingBank != null && !bankAccount.issuingBank.equals("")
				&& !this.issuingBank.equals(bankAccount.issuingBank))
			return false;

		if (bankAccount.expirationDate != null && !this.expirationDate.equals(bankAccount.expirationDate))
			return false;

		if (bankAccount.CVC != 0 && this.CVC != bankAccount.CVC)
			return false;

		return true;
	}
}