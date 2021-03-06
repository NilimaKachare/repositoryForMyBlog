package com.org.coop.canonical.beans;

import java.util.Date;

//@JsonInclude(Include.NON_NULL)
public class FyCloseBean {
	protected int fyId;
	protected int branchId;
	protected String branchName;
	protected Date yearClosedOn;
	protected Date createDate;
	protected String createUser;
	protected Date endDate;
	protected String fy;
	protected byte isClosed;
	protected Date startDate;
	protected Date updateDate;
	protected String updateUser;
	protected String deleteInd;
	protected String deleteReason;
	protected String passingAuthInd;
	protected String passingAuthRemark;
	public int getFyId() {
		return fyId;
	}
	public void setFyId(int fyId) {
		this.fyId = fyId;
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
	public Date getEndDate() {
		return endDate;
	}
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	public String getFy() {
		return fy;
	}
	public void setFy(String fy) {
		this.fy = fy;
	}
	public byte getIsClosed() {
		return isClosed;
	}
	public void setIsClosed(byte isClosed) {
		this.isClosed = isClosed;
	}
	public Date getStartDate() {
		return startDate;
	}
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
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
		result = prime * result + fyId;
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
		FyCloseBean other = (FyCloseBean) obj;
		if (fyId != other.fyId)
			return false;
		return true;
	}
	public Date getYearClosedOn() {
		return yearClosedOn;
	}
	public void setYearClosedOn(Date yearClosedOn) {
		this.yearClosedOn = yearClosedOn;
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
}
