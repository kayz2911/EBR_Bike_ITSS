package com.ebr.serverapi.test.customer;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import com.ebr.bean.Address;
import com.ebr.bean.bikestation.BikeStation;
import com.ebr.bean.customer.Card;
import com.ebr.enums.BikeStatus;
import com.ebr.serverapi.customer.CardAPI;
import com.ebr.utils.Utils;

public class CardAPITest {
	private CardAPI api = new CardAPI();

	@Test
	public void testGetCadById() {
		String cardId = "ecb24c5e-6f26-4fdb-9aa7-e260b0e0f58c";
		String cardNumber = "4929296116773627";
		String issuingBank = "Visa";

		HashMap<String, String> params = new HashMap<String, String>();
		params.put("id", cardId);
		
		Card card = api.getById(params);
		assertTrue("Error in testGetCadById API!", card.getId().equals(cardId));
		assertTrue("Error in testGetCadById API!", card.getCardNumber().equals(cardNumber));
		assertTrue("Error in testGetCadById API!", card.getIssuingBank().equals(issuingBank));
	}

	@Test(timeout = 1000)
	public void testResponse() {
		HashMap<String, String> params = new HashMap<String, String>();
		params.put("id", "ecb24c5e-6f26-4fdb-9aa7-e260b0e0f58c");
		api.getById(params);
	}

	@Test
	public void testAddCard() {
		Card card = new Card("f84f3e4e-4140-4889-84b7-06a2269e6455", "NGUYEN TRUNG TRINH", "3088630179182414", "JCB",
				new Date(), 989);

		api.add(card);

		HashMap<String, String> params = new HashMap<String, String>();
		params.put("id", "f84f3e4e-4140-4889-84b7-06a2269e6455");
		Card newCard = api.getById(params);

		assertTrue("Error in testAddCard API!", card.getId().equals(card.getId()));
		assertTrue("Error in testAddCard API!", card.getCardholderName().equals(card.getCardholderName()));
		assertTrue("Error in testAddCard API!", card.getCardNumber().equals(card.getCardNumber()));
		assertTrue("Error in testAddCard API!", card.getIssuingBank().equals(card.getIssuingBank()));
		assertTrue("Error in testAddCard API!", card.getExpirationDate().equals(card.getExpirationDate()));
	}

	@Test
	public void testUpdateCard() {
		String cardId = "ecb24c5e-6f26-4fdb-9aa7-e260b0e0f58c";
		HashMap<String, String> params = new HashMap<String, String>();
		params.put("id", cardId);
		Card card = api.getById(params);

		String newCardholderName = "Name test";
		card.setCardholderName(newCardholderName);
		api.update(card);

		Card newCard = api.getById(params);

		assertEquals("Error in updateCard API!", newCard.getCardholderName(), newCardholderName);
	}
}