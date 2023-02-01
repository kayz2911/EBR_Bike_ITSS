package com.ebr.db;

import java.util.ArrayList;

// beans
import com.ebr.bean.customer.Card;

// db interfaces
import com.ebr.db.interfaces.ICardDatabase;

// db seed
import com.ebr.db.seed.Seed;

// utils
import com.ebr.utils.Utils;

public class JsonCardDatabase implements ICardDatabase {
	private static ICardDatabase singleton = new JsonCardDatabase();

	private ArrayList<Card> cards = Seed.singleton().getCards();

	private JsonCardDatabase() {
	}

	public static ICardDatabase singleton() {
		return singleton;
	}

	@Override
	public Card getById(String cardId) {
		if (cardId != null) {
			for (Card c : cards) {
				if (c.getId().equals(cardId)) {
					return c;
				}
			}
		}
		return null;
	}

	@Override
	public Card add(Card card) {
		for (Card c : cards) {
			if (c.equals(card)) {
				return null;
			}
		}

		if (card.getId() == null || card.getId().equals(""))
			card.setId(Utils.generateUUID());

		cards.add(card);

		return card;
	}

	@Override
	public Card update(Card card) {
		for (Card c : cards) {
			if (c.equals(card)) {
				cards.remove(c);
				cards.add(card);
				return card;
			}
		}

		return null;
	}

	@Override
	public ArrayList<Card> search(Card requestModel) {
		throw new UnsupportedOperationException("Not supported yet.");
	}

	@Override
	public boolean checkExistById(String id) {
		throw new UnsupportedOperationException("Not supported yet.");
	}

	@Override
	public boolean removeById(String id) {
		throw new UnsupportedOperationException("Not supported yet.");
	}
}
