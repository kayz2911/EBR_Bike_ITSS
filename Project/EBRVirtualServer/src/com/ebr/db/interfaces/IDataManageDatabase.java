package com.ebr.db.interfaces;

import java.util.ArrayList;

public interface IDataManageDatabase<T> {
	public T getById(String id);

	public boolean checkExistById(String id);

	public T add(T model);

	public T update(T model);

	public boolean removeById(String id);
}
