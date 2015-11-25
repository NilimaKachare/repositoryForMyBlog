package com.org.coop.society.data.customer.entities;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;


/**
 * The persistent class for the staff database table.
 * 
 */
@Entity
@NamedQuery(name="Staff.findAll", query="SELECT s FROM Staff s")
public class Staff implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;

	@Column(name="create_date")
	private Timestamp createDate;

	@Column(name="create_user")
	private String createUser;

	private String email1;

	private String email2;

	@Temporal(TemporalType.DATE)
	@Column(name="end_date")
	private Date endDate;

	@Column(name="first_name")
	private String firstName;

	@Column(name="last_name")
	private String lastName;

	@Column(name="middle_name")
	private String middleName;

	@Column(name="modify_date")
	private Timestamp modifyDate;

	@Column(name="modify_user")
	private String modifyUser;

	private String phone1;

	private String phone2;

	@Temporal(TemporalType.DATE)
	@Column(name="start_date")
	private Date startDate;

	@Column(name="user_name")
	private String userName;

	//bi-directional many-to-one association to StaffAddress
	@OneToMany(mappedBy="staff", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@Fetch(value = FetchMode.SUBSELECT)
	private List<StaffAddress> staffAddresses;

	//bi-directional many-to-one association to StaffRole
	@OneToMany(mappedBy="staff", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@Fetch(value = FetchMode.SUBSELECT)
	private List<StaffRole> staffRoles;

	public Staff() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Timestamp getCreateDate() {
		return this.createDate;
	}

	public void setCreateDate(Timestamp createDate) {
		this.createDate = createDate;
	}

	public String getCreateUser() {
		return this.createUser;
	}

	public void setCreateUser(String createUser) {
		this.createUser = createUser;
	}

	public String getEmail1() {
		return this.email1;
	}

	public void setEmail1(String email1) {
		this.email1 = email1;
	}

	public String getEmail2() {
		return this.email2;
	}

	public void setEmail2(String email2) {
		this.email2 = email2;
	}

	public Date getEndDate() {
		return this.endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public String getFirstName() {
		return this.firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return this.lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getMiddleName() {
		return this.middleName;
	}

	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}

	public Timestamp getModifyDate() {
		return this.modifyDate;
	}

	public void setModifyDate(Timestamp modifyDate) {
		this.modifyDate = modifyDate;
	}

	public String getModifyUser() {
		return this.modifyUser;
	}

	public void setModifyUser(String modifyUser) {
		this.modifyUser = modifyUser;
	}

	public String getPhone1() {
		return this.phone1;
	}

	public void setPhone1(String phone1) {
		this.phone1 = phone1;
	}

	public String getPhone2() {
		return this.phone2;
	}

	public void setPhone2(String phone2) {
		this.phone2 = phone2;
	}

	public Date getStartDate() {
		return this.startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public String getUserName() {
		return this.userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public List<StaffAddress> getStaffAddresses() {
		return this.staffAddresses;
	}

	public void setStaffAddresses(List<StaffAddress> staffAddresses) {
		this.staffAddresses = staffAddresses;
	}

	public StaffAddress addStaffAddress(StaffAddress staffAddress) {
		getStaffAddresses().add(staffAddress);
		staffAddress.setStaff(this);

		return staffAddress;
	}

	public StaffAddress removeStaffAddress(StaffAddress staffAddress) {
		getStaffAddresses().remove(staffAddress);
		staffAddress.setStaff(null);

		return staffAddress;
	}

	public List<StaffRole> getStaffRoles() {
		return this.staffRoles;
	}

	public void setStaffRoles(List<StaffRole> staffRoles) {
		this.staffRoles = staffRoles;
	}

	public StaffRole addStaffRole(StaffRole staffRole) {
		getStaffRoles().add(staffRole);
		staffRole.setStaff(this);

		return staffRole;
	}

	public StaffRole removeStaffRole(StaffRole staffRole) {
		getStaffRoles().remove(staffRole);
		staffRole.setStaff(null);

		return staffRole;
	}

}