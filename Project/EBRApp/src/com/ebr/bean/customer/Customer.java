package com.ebr.bean.customer;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonInclude(Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class Customer {
	private String id;
	private String firstName;
	private String lastName;
	private String phoneNumber;
	private String cardId;

	// display data
	private Card card;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getCardId() {
		return cardId;
	}

	public void setCardId(String cardId) {
		this.cardId = cardId;
	}

	// display data
	public Card getCard() {
		return card;
	}

	public void setCard(Card card) {
		this.card = card;
	}

	public Customer() {
		super();
	}

	public Customer(String id) {
		super();
		this.id = id;
	}

	public Customer(String id, String firstName, String lastName, String phoneNumber) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.phoneNumber = phoneNumber;
	}

	public Customer(String firstName, String lastName, String phoneNumber) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.phoneNumber = phoneNumber;
	}

	public Customer(String id, String firstName, String lastName, String phoneNumber, String cardId) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.phoneNumber = phoneNumber;
		this.cardId = cardId;
	}

	@Override
	public String toString() {
		return "id: " + this.id + ", firstName: " + this.firstName + ", lastName: " + this.lastName + ", phoneNumber: "
				+ this.phoneNumber + ", cardId: " + this.cardId;
	}

	public boolean match(Customer customer) {
		if (customer == null)
			return true;

		if (customer.id != null && !this.id.equals(customer.id))
			return false;

		if (customer.firstName != null && !customer.firstName.equals("") && !this.firstName.contains(customer.firstName))
			return false;

		if (customer.lastName != null && !customer.lastName.equals("") && !this.lastName.contains(customer.lastName))
			return false;

		if (customer.phoneNumber != null && !this.phoneNumber.equals(customer.phoneNumber))
			return false;

		if (customer.cardId != null && !this.cardId.equals(customer.cardId))
			return false;

		return true;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Customer)
			return this.id.equals(((Customer) obj).id);

		return false;
	}
}