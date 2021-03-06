package com.org.coop.admin.ws;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.org.coop.admin.service.AdminLoginSvc;
import com.org.coop.admin.service.BranchSetupServiceImpl;
import com.org.coop.admin.servicehelper.BranchSetupServiceHelperImpl;
import com.org.coop.canonical.beans.UIModel;

@RestController
@RequestMapping("/rest")
public class BranchSetupWSImpl {
	
	private static final Logger log = Logger.getLogger(BranchSetupWSImpl.class); 
	
	@Autowired
	private BranchSetupServiceImpl branchSetupServiceImpl;
	
	@Autowired
	private AdminLoginSvc adminLoginService; 
	
	@RequestMapping(value = "/createBranch", method = RequestMethod.POST, headers="Accept=application/json",produces="application/json",consumes="application/json")
	public UIModel createBranch(@RequestBody UIModel uiModel) {
		uiModel = branchSetupServiceImpl.addOrUpdateBranch(uiModel);
		return uiModel;
	}
	
	@RequestMapping(value = "/getBranch", method = RequestMethod.GET, headers="Accept=application/json",produces="application/json")
	public UIModel getBranch(@RequestParam(value = "branchId",required = true,defaultValue = "0") Integer branchId) {
		UIModel uiModel = new UIModel();
		try {
			uiModel = branchSetupServiceImpl.getBranch(branchId);
		} catch (Exception e) {
			log.error("Error Retrieving branch by branch Id", e);
			uiModel.setErrorMsg("Error Retrieving branch by branch Id: " + branchId);
		}
		return uiModel;
	}
	
	@RequestMapping(value = "/getUserBranch", method = RequestMethod.GET, headers="Accept=application/json",produces="application/json")
	public UIModel getUserBranch(@RequestParam(value = "username",required = true,defaultValue = "") String username) {
		UIModel uiModel = new UIModel();
		try {
			if(StringUtils.isNotBlank(username)) {
				uiModel = adminLoginService.getBranchConfig(username);
			} else {
				uiModel.setErrorMsg("username can not be blank");
			}
		} catch (Exception e) {
			log.error("Error Retrieving branch by username", e);
			uiModel.setErrorMsg("Error Retrieving branch by username: " + username);
		}
		return uiModel;
	}
	
}
