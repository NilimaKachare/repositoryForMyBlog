package com.org.coop.retail.servicehelper;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.querydsl.QSort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.coop.org.exception.AdminSvcCommonException;
import com.mysema.query.types.Predicate;
import com.mysema.query.types.expr.BooleanExpression;
import com.org.coop.admin.servicehelper.CommonAdminServiceHelperImpl;
import com.org.coop.bs.util.CommonValidationUtils;
import com.org.coop.bs.util.RetailBusinessConstants;
import com.org.coop.canonical.beans.BranchBean;
import com.org.coop.canonical.beans.CustomerBean;
import com.org.coop.canonical.beans.SmsNotificationBean;
import com.org.coop.canonical.beans.UIModel;
import com.org.coop.retail.bs.mapper.SmsNotificationMappingImpl;
import com.org.coop.retail.entities.QSmsNotification;
import com.org.coop.retail.entities.SmsNotification;
import com.org.coop.retail.repositories.RetailBranchMasterRepository;
import com.org.coop.retail.repositories.SmsNotificationRepository;

@Service
public class SmsNotificationServiceHelperImpl {

	private static final Logger log = Logger.getLogger(SmsNotificationServiceHelperImpl.class); 
	
	@Autowired
	private RetailBranchMasterRepository branchMasterRepository;
	
	@Autowired
	private SmsNotificationMappingImpl smsNotificationMappingImpl;
	
	@Autowired
	private SmsNotificationRepository smsNotificationRepository;
	
	@Autowired
	private CommonValidationUtils commonValidationUtils;
	
	@Autowired
	private CommonAdminServiceHelperImpl commonAdminServiceHelperImpl;
	
	@Transactional(value="retailTransactionManager")
	public UIModel getSmsNotifications(int branchId, int customerId, int notificationId, String mobileNo, 
							Date startDate, Date endDate,int pageNo, int recordsPerPage) {
		UIModel uiModel = new UIModel();
		
		//**********************************************
		// VALIDATION 1: BranchId can not be zero
		//**********************************************
		if(branchId == 0) {
			String errorMsg = "BranchId can not be zero";
			log.error(errorMsg);
			throw new AdminSvcCommonException(errorMsg, RetailBusinessConstants.EXCEPTION_RETAIL_NOTIFICATION);
		}
		
		Page<SmsNotification> smsNotifications = null;
		QSmsNotification smsNotification = QSmsNotification.smsNotification;
		PageRequest pageable = new PageRequest(pageNo - 1, recordsPerPage, new QSort(smsNotification.smsPurpose.asc()));
		
		BooleanExpression smsNotificationCriteria = null;
		if(branchId > 0) smsNotificationCriteria = smsNotification.branchMaster.branchId.eq(branchId);
		if(customerId > 0) smsNotificationCriteria = smsNotificationCriteria.and(smsNotification.customer.customerId.eq(customerId));
		if(notificationId > 0) smsNotificationCriteria = smsNotificationCriteria.and(smsNotification.customerNotification.notificationId.eq(notificationId));
		if(StringUtils.isNotBlank(mobileNo)) {
			commonAdminServiceHelperImpl.validatePhoneNumber(mobileNo);
			smsNotificationCriteria = smsNotificationCriteria.and(smsNotification.mobileNo.eq(mobileNo));
		}
		if(endDate == null) endDate = new Date();
		Timestamp endTs = new Timestamp(endDate.getTime());
		if(startDate != null) {
			Timestamp startTs = new Timestamp(startDate.getTime());
			smsNotificationCriteria = smsNotificationCriteria.and(smsNotification.createDate.between(startTs, endTs));
		}
		
		Predicate predicate = smsNotificationCriteria;
		smsNotifications = smsNotificationRepository.findAll(predicate, pageable);
		if(log.isDebugEnabled()) {
			log.debug("Retrieved data from sms_notification table");
		}
		List<SmsNotificationBean> smsNotificationBeans = new ArrayList<SmsNotificationBean>(); 
		
		for(SmsNotification smsNotificationEntity : smsNotifications) {
			SmsNotificationBean smsNotificationBean = new SmsNotificationBean();
			smsNotificationMappingImpl.mapSmsNotificationBean(smsNotificationEntity, smsNotificationBean);
			smsNotificationBeans.add(smsNotificationBean);
		}
		
		List<CustomerBean> customerBeans = new ArrayList<CustomerBean>();
		customerBeans.add(new CustomerBean());
		uiModel.setBranchBean(new BranchBean());
		uiModel.getBranchBean().setBranchId(branchId);
		uiModel.getBranchBean().setCustomers(customerBeans);
		uiModel.getBranchBean().getCustomers().get(0).setSmsNotifications(smsNotificationBeans);
		int recordCount = (int)smsNotifications.getTotalElements();
		
		uiModel.setRecordCount(recordCount);
		uiModel.setPageNo(pageNo);
		uiModel.setRecordsPerPage(recordsPerPage);
		uiModel.setStartDate(startDate);
		uiModel.setEndDate(endDate);
		
		if(recordsPerPage == 0) {
			uiModel.setErrorMsg("No records found");
		}
		
		return uiModel;
	}
	
	@Transactional(value="retailTransactionManager")
	public UIModel saveSmsNotifications(UIModel uiModel) {
		if(uiModel.getBranchBean().getCustomers() != null && uiModel.getBranchBean().getCustomers().size() > 0 &&
				uiModel.getBranchBean().getCustomers().get(0).getSmsNotifications() != null && uiModel.getBranchBean().getCustomers().get(0).getSmsNotifications().size() > 0) {
			for(SmsNotificationBean smsNotificationBean : uiModel.getBranchBean().getCustomers().get(0).getSmsNotifications()) {
				SmsNotification smsNotification = new SmsNotification();
				smsNotificationMappingImpl.mapSmsNotificationBean(smsNotificationBean, smsNotification);
				
				validateInputForSMSNotification(smsNotificationBean, smsNotification);
				
				smsNotificationRepository.saveAndFlush(smsNotification);
				
				//************************************
				// Send Email
				//***********************************
				if(StringUtils.isNotBlank(smsNotification.getEmail())) {
					try {
						String emailSubscriptionInd = "Y";
						if(smsNotification.getCustomer() != null) {
							emailSubscriptionInd = smsNotification.getCustomer().getEmailSubcriptionInd();
							if(log.isDebugEnabled()) {
								log.debug("Email subscription status = " + emailSubscriptionInd + " for customer : " + smsNotification.getCustomer().getCustomerId());
							}
						}
						String gmailId = smsNotification.getBranchMaster().getGmailId();
						String gmailPassword = smsNotification.getBranchMaster().getGmailPassword();
						smsNotification.setEmailBody(smsNotification.getSmsSubject());
						String subject = "Notification from " + smsNotification.getBranchMaster().getBranchName();
						smsNotification.setEmailSubject(subject);
						if("Y".equals(emailSubscriptionInd)) {
							commonAdminServiceHelperImpl.sendEmail(gmailId, gmailPassword, smsNotification.getEmail(), subject, smsNotification.getEmailBody());
//							if(log.isDebugEnabled()) {
//								log.debug("Email sent successfully to " + smsNotification.getEmail());
//							}
						}
					} catch (Exception e) {
						String errorMsg = "Email sending failed to email id: " + smsNotification.getEmail();
						log.error(errorMsg);
						smsNotification.setEmailSuccessfulInd("N");
					}
				}
				
				//************************************
				// Send SMS
				//***********************************
				if(StringUtils.isNotBlank(smsNotification.getMobileNo())) {
					try {
						String smsSubscriptionInd = "Y";
						if(smsNotification.getCustomer() != null) {
							smsSubscriptionInd = smsNotification.getCustomer().getSmsSubcriptionInd();
							if(log.isDebugEnabled()) {
								log.debug("SMS subscription status = " + smsSubscriptionInd + " for customer : " + smsNotification.getCustomer().getCustomerId());
							}
						}
						String senderId = smsNotification.getBranchMaster().getSmsSenderId();
						String mobile = smsNotification.getMobileNo();
						smsNotification.setSmsSubject(smsNotification.getSmsSubject());
						if("Y".equals(smsSubscriptionInd)) {
							commonAdminServiceHelperImpl.sendSMS(mobile, senderId, smsNotification.getSmsSubject());
//							if(log.isDebugEnabled()) {
//								log.debug("SMS sent successfuly to mobile no : " + mobile);
//							}
						}
					} catch (Exception e) {
						String errorMsg = "SMS sending failed to mobile no: " + smsNotification.getMobileNo();
						log.error(errorMsg);
						smsNotification.setSmsSuccessfulInd("N");
					}
				}
			}
		}
		return uiModel;
	}

	private void validateInputForSMSNotification(SmsNotificationBean smsNotificationBean,
			SmsNotification smsNotification) {
		//**************************************
		// VALIDATION 1: Mobile number must not be blank
		//*************************************
		String mobile = smsNotificationBean.getMobileNo();
		if(StringUtils.isBlank(mobile) && smsNotification.getCustomer() != null) {
			mobile = smsNotification.getCustomer().getMobile1();
			if(StringUtils.isBlank(mobile)) {
				mobile = smsNotification.getCustomer().getMobile2();
			}
		}
		
		if(StringUtils.isBlank(mobile)) {
			String errorMsg = "Mobile number can not be null or blank";
			log.error(errorMsg);
			throw new AdminSvcCommonException(errorMsg,RetailBusinessConstants.EXCEPTION_RETAIL_NOTIFICATION);
		}
		
		commonAdminServiceHelperImpl.validatePhoneNumber(mobile);
		
		smsNotification.setMobileNo(mobile);
		
		//**************************************
		// VALIDATION 1: Mobile number must not be blank
		//*************************************
		String email = smsNotificationBean.getEmail();
		if(StringUtils.isBlank(email) && smsNotification.getCustomer() != null) {
			email = smsNotification.getCustomer().getEmailId();
		}
		if(StringUtils.isNotBlank(email)) {
			if(log.isDebugEnabled()) {
				log.debug("Email id to be validated");
			}
			commonAdminServiceHelperImpl.validateEmail(email);
			smsNotification.setEmail(email);
		}
		
		//**************************************
		// VALIDATION 2: SMS Purpose must not be blank
		//*************************************
		String smsPurpose = smsNotificationBean.getSmsPurpose();
		if(StringUtils.isBlank(smsPurpose) && smsNotification.getCustomerNotification() != null) {
			smsPurpose = smsNotification.getCustomerNotification().getNotificationType();
		}
		
		if(StringUtils.isBlank(smsPurpose)) {
			String errorMsg = "SMS purpose can not be null or blank";
			log.error(errorMsg);
			throw new AdminSvcCommonException(errorMsg,RetailBusinessConstants.EXCEPTION_RETAIL_NOTIFICATION);
		}
		smsNotification.setSmsPurpose(smsPurpose);
		
		//**************************************
		// VALIDATION 3: SMS Subject must not be blank
		//*************************************
		String smsSubject = smsNotificationBean.getSmsSubject();
		if(StringUtils.isBlank(smsSubject) && smsNotification.getCustomerNotification() != null) {
			smsSubject = smsNotification.getCustomerNotification().getNotificationDetail();
			smsSubject = "Your " + smsSubject + " is expiring on " + smsNotification.getCustomerNotification().getEndDate();
		}
		
		if(StringUtils.isBlank(smsSubject)) {
			String errorMsg = "SMS Subject can not be null or blank";
			log.error(errorMsg);
			throw new AdminSvcCommonException(errorMsg,RetailBusinessConstants.EXCEPTION_RETAIL_NOTIFICATION);
		}
		smsNotification.setSmsSubject(smsSubject);
		
		//**************************************
		// VALIDATION 4: BranchId must not be null/zero
		//*************************************
		int branchId = smsNotificationBean.getBranchId();
		if(branchId == 0) {
			String errorMsg = "BranchId must not be null/zero";
			log.error(errorMsg);
			throw new AdminSvcCommonException(errorMsg,RetailBusinessConstants.EXCEPTION_RETAIL_NOTIFICATION);
		} else {
			commonValidationUtils.validateBranch(branchId);
		}
	}
	
	@Transactional(value="retailTransactionManager")
	public UIModel deleteSmsNotifications(UIModel uiModel) {
		return uiModel;
	}
}
