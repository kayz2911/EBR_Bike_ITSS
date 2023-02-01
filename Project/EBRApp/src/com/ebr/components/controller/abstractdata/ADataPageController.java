package com.ebr.components.controller.abstractdata;

import java.util.List;
import java.util.Map;

import javax.swing.JPanel;

import com.ebr.components.gui.abstractdata.ADataListPane;
import com.ebr.components.gui.abstractdata.ADataSearchPane;
import com.ebr.components.gui.abstractdata.ADataSinglePane;
import com.ebr.components.gui.abstractdata.DataPagePane;

public abstract class ADataPageController<T> {
	protected DataPagePane<T> pagePane;
	
	public ADataPageController() {
		ADataSearchPane searchPane = createSearchPane();
		
		ADataListPane<T> listPane = createListPane();
		
		searchPane.setController(new IDataSearchController() {
			@Override
			public void search(Map<String, String> searchParams) {
				List<? extends T> list = ADataPageController.this.search(searchParams);
				listPane.updateData(list);
			}
		});
		
		searchPane.fireSearchEvent();
		
		pagePane = new DataPagePane<T>(searchPane, listPane);
	}
	
	public JPanel getDataPagePane() {
		return pagePane;
	}
	
	
	public abstract ADataSearchPane createSearchPane();

	public abstract List<? extends T> search(Map<String, String> searchParams);
	
	
	
	public abstract ADataSinglePane<T> createSinglePane();
	
	public abstract ADataListPane<T> createListPane();
}
