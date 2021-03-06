package com.org.coop.customer.ws;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.org.coop.canonical.beans.UIModel;
import com.org.coop.retail.service.RetailBranchSetupServiceImpl;

@RestController
@RequestMapping("/rest")
public class RetailBranchSetupWSImpl {
	
	private static final Logger log = Logger.getLogger(RetailBranchSetupWSImpl.class); 
	
	@Autowired
	private RetailBranchSetupServiceImpl branchSetupServiceImpl;
	
	@RequestMapping(value = "/getRetailBranch", method = RequestMethod.GET, headers="Accept=application/json",produces="application/json")
	public UIModel getRetailBranch(@RequestParam(value = "branchId",required = true,defaultValue = "0") Integer branchId) {
		UIModel uiModel = new UIModel();
		try {
			uiModel = branchSetupServiceImpl.getRetailBranch(branchId);
		} catch (Exception e) {
			log.error("Error Retrieving branch by branch Id", e);
			uiModel.setErrorMsg("Error Retrieving branch by branch Id: " + branchId);
		}
		return uiModel;
	}
	
	@RequestMapping(value = "/createRetailBranch", method = RequestMethod.POST, headers="Accept=application/json",produces="application/json")
	public UIModel createRetailBranch(@RequestBody UIModel uiModel) {
		try {
			uiModel = branchSetupServiceImpl.createRetailBranch(uiModel);
		} catch (Exception e) {
			log.error("Error while creating branch by branch Id", e);
			uiModel.setErrorMsg("Error while creating branch by branch Id: " + uiModel.getBranchBean().getBranchId());
		}
		return uiModel;
	}
	
	@RequestMapping(value = "/saveRetailVatRegNo", method = RequestMethod.POST, headers="Accept=application/json",produces="application/json")
	public UIModel saveRetailVatRegNo(@RequestBody UIModel uiModel) {
		try {
			uiModel = branchSetupServiceImpl.createRetailBranch(uiModel);
		} catch (Exception e) {
			log.error("Error while creating branch by branch Id", e);
			uiModel.setErrorMsg("Error while creating branch by branch Id: " + uiModel.getBranchBean().getBranchId());
		}
		return uiModel;
	}
	
	@RequestMapping(value = "/deleteRetailVatRegNo", method = RequestMethod.DELETE, headers="Accept=application/json",produces="application/json")
	public UIModel deleteRetailVatRegNo(@RequestBody UIModel uiModel) {
		try {
			uiModel = branchSetupServiceImpl.deleteVatRegNo(uiModel);
		} catch (Exception e) {
			log.error("Error while deteling Retail Vat Registration number for the branch Id", e);
			uiModel.setErrorMsg("Error while deteling Retail Vat Registration number for the branch Id: " + uiModel.getBranchBean().getBranchId());
		}
		return uiModel;
	}
}
