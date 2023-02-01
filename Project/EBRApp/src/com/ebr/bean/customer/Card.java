package com.ebr.bean.customer;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonInclude(Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class Card {
	private String id;
	private String cardholderName;
	private String cardNumber;
	private String issuingBank;
	@JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss")
	private Date expirationDate;
	@JsonProperty("CVC")
	private int CVC;

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

	public Card() {
		super();
	}

	public Card(String id, String cardholderName, String cardNumber, String issuingBank, Date expirationDate, int CVC) {
		super();
		this.id = id;
		this.cardholderName = cardholderName;
		this.cardNumber = cardNumber;
		this.issuingBank = issuingBank;
		this.expirationDate = expirationDate;
		this.CVC = CVC;
	}

	public Card(String cardholderName, String cardNumber, String issuingBank, Date expirationDate, int CVC) {
		super();
		this.cardholderName = cardholderName;
		this.cardNumber = cardNumber;
		this.issuingBank = issuingBank;
		this.expirationDate = expirationDate;
		this.CVC = CVC;
	}

	@Override
	public String toString() {
		return "id: " + this.id + ", cardholderName: " + this.cardholderName + ", cardNumber: " + this.cardNumber
				+ ", issuingBank: " + this.issuingBank + ", expirationDate: " + this.expirationDate != null
						? this.expirationDate.toString()
						: "";
	}

	public boolean match(Card card) {
		if (card == null)
			return true;

		if (card.id != null && !this.id.equals(card.id))
			return false;

		if (card.cardholderName != null && !card.cardholderName.equals("")
				&& !this.cardholderName.contains(card.cardholderName))
			return false;

		if (card.cardNumber != null && !this.cardNumber.equals(card.cardNumber))
			return false;

		if (card.issuingBank != null && !card.issuingBank.equals("") && !this.issuingBank.contains(card.issuingBank))
			return false;

		return true;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Card)
			return this.id.equals(((Card) obj).id);

		return false;
	}
}