package com.org.coop.retail.service;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.org.coop.canonical.beans.UIModel;
import com.org.coop.retail.servicehelper.RetailCustomerSetupServiceHelperImpl;
import com.org.coop.retail.servicehelper.RetailVendorSetupServiceHelperImpl;

@Service
public class RetailCustomerSetupServiceImpl {

	private static final Logger log = Logger.getLogger(RetailCustomerSetupServiceImpl.class); 
	
	@Autowired
	private RetailCustomerSetupServiceHelperImpl retailCustomerSetupServiceHelperImpl;
	
	
	public UIModel getRetailCustomer(int customerId, int branchId) {
		return retailCustomerSetupServiceHelperImpl.getRetailCustomer(customerId, branchId);
	}
	
	public UIModel saveRetailCustomer(UIModel uiModel) {
		uiModel = retailCustomerSetupServiceHelperImpl.saveRetailCustomer(uiModel);
		if(uiModel.getErrorMsg() != null) {
			return uiModel;
		}
		int customerId = uiModel.getBranchBean().getRetailCustomers().get(0).getCustomerId();
		int branchId = uiModel.getBranchBean().getRetailCustomers().get(0).getBranchId();
		return retailCustomerSetupServiceHelperImpl.getRetailCustomer(customerId, branchId);
	}
	
}
