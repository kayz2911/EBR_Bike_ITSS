package com.ebr.db.interfaces;

import java.util.ArrayList;

// beans
import com.ebr.bean.customer.Customer;

public interface ICustomerDatabase extends IDataManageDatabase<Customer>, IDataSearchDatabase<Customer>  {
}
