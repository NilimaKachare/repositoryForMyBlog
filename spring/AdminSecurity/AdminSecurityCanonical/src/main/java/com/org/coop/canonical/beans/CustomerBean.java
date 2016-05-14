package com.org.coop.canonical.beans;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class CustomerBean {
	protected int customerId;
	protected int transferToCustomerId;
	protected String customerName;
	protected String customerType;
	protected int accuntId;
	protected int branchId;
	protected String branchName;
	protected Date actionDate;
	protected String activeInd;
	protected String cifNo;
	protected Date createDate;
	protected String createUser;
	protected String deleteInd;
	protected String deleteReason;
	protected Date lastUsedDate;
	protected String passingAuthInd;
	protected String passingAuthRemark;
	protected String primaryHolderInd;
	protected String aadharNo;
	protected String drivingLicence;
	protected String emailId;
	protected String firstName;
	protected String lastName;
	protected String middleName;
	protected String mobile1;
	protected String mobile2;
	protected String panNo;
	protected String salute;
	protected Date updateDate;
	protected String updateUser;
	protected String voterId;
	
	protected List<CustomerNotificationBean> notifications = new ArrayList<CustomerNotificationBean>();
	protected List<SmsNotificationBean> smsNotifications = new ArrayList<SmsNotificationBean>();
	
	public int getCustomerId() {
		return customerId;
	}
	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}
	public int getBranchId() {
		return branchId;
	}
	public void setBranchId(int branchId) {
		this.branchId = branchId;
	}
	public String getBranchName() {
		return branchName;
	}
	public void setBranchName(String branchName) {
		this.branchName = branchName;
	}
	public Date getActionDate() {
		return actionDate;
	}
	public void setActionDate(Date actionDate) {
		this.actionDate = actionDate;
	}
	public String getActiveInd() {
		return activeInd;
	}
	public void setActiveInd(String activeInd) {
		this.activeInd = activeInd;
	}
	public String getCifNo() {
		return cifNo;
	}
	public void setCifNo(String cifNo) {
		this.cifNo = cifNo;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	public String getCreateUser() {
		return createUser;
	}
	public void setCreateUser(String createUser) {
		this.createUser = createUser;
	}
	public String getDeleteInd() {
		return deleteInd;
	}
	public void setDeleteInd(String deleteInd) {
		this.deleteInd = deleteInd;
	}
	public String getDeleteReason() {
		return deleteReason;
	}
	public void setDeleteReason(String deleteReason) {
		this.deleteReason = deleteReason;
	}
	public Date getLastUsedDate() {
		return lastUsedDate;
	}
	public void setLastUsedDate(Date lastUsedDate) {
		this.lastUsedDate = lastUsedDate;
	}
	public String getPassingAuthInd() {
		return passingAuthInd;
	}
	public void setPassingAuthInd(String passingAuthInd) {
		this.passingAuthInd = passingAuthInd;
	}
	public String getPassingAuthRemark() {
		return passingAuthRemark;
	}
	public void setPassingAuthRemark(String passingAuthRemark) {
		this.passingAuthRemark = passingAuthRemark;
	}
	public String getPrimaryHolderInd() {
		return primaryHolderInd;
	}
	public void setPrimaryHolderInd(String primaryHolderInd) {
		this.primaryHolderInd = primaryHolderInd;
	}
	public Date getUpdateDate() {
		return updateDate;
	}
	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}
	public String getUpdateUser() {
		return updateUser;
	}
	public void setUpdateUser(String updateUser) {
		this.updateUser = updateUser;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + customerId;
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CustomerBean other = (CustomerBean) obj;
		if (customerId != other.customerId)
			return false;
		return true;
	}
	public String getCustomerName() {
		return customerName;
	}
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	public String getAadharNo() {
		return aadharNo;
	}
	public void setAadharNo(String aadharNo) {
		this.aadharNo = aadharNo;
	}
	public String getDrivingLicence() {
		return drivingLicence;
	}
	public void setDrivingLicence(String drivingLicence) {
		this.drivingLicence = drivingLicence;
	}
	public String getEmailId() {
		return emailId;
	}
	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getMiddleName() {
		return middleName;
	}
	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}
	public String getMobile1() {
		return mobile1;
	}
	public void setMobile1(String mobile1) {
		this.mobile1 = mobile1;
	}
	public String getMobile2() {
		return mobile2;
	}
	public void setMobile2(String mobile2) {
		this.mobile2 = mobile2;
	}
	public String getPanNo() {
		return panNo;
	}
	public void setPanNo(String panNo) {
		this.panNo = panNo;
	}
	public String getSalute() {
		return salute;
	}
	public void setSalute(String salute) {
		this.salute = salute;
	}
	public String getVoterId() {
		return voterId;
	}
	public void setVoterId(String voterId) {
		this.voterId = voterId;
	}
	public int getAccuntId() {
		return accuntId;
	}
	public void setAccuntId(int accuntId) {
		this.accuntId = accuntId;
	}
	public String getCustomerType() {
		return customerType;
	}
	public void setCustomerType(String customerType) {
		this.customerType = customerType;
	}
	public List<CustomerNotificationBean> getNotifications() {
		return notifications;
	}
	public void setNotifications(List<CustomerNotificationBean> notifications) {
		this.notifications = notifications;
	}
	public int getTransferToCustomerId() {
		return transferToCustomerId;
	}
	public void setTransferToCustomerId(int transferToCustomerId) {
		this.transferToCustomerId = transferToCustomerId;
	}
	public List<SmsNotificationBean> getSmsNotifications() {
		return smsNotifications;
	}
	public void setSmsNotifications(List<SmsNotificationBean> smsNotifications) {
		this.smsNotifications = smsNotifications;
	}
}
