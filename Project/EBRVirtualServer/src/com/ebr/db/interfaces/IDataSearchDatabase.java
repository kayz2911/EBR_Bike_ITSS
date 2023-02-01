package com.ebr.db.interfaces;

import java.util.ArrayList;

public interface IDataSearchDatabase<T> {
	public ArrayList<T> search(T model);
}
