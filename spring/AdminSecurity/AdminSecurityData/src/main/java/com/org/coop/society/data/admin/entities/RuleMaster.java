package com.org.coop.society.data.admin.entities;

import java.io.Serializable;

import javax.persistence.*;

import org.hibernate.annotations.Where;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the rule_master database table.
 * 
 */
@Entity
@Table(name="rule_master")
@NamedQuery(name="RuleMaster.findAll", query="SELECT r FROM RuleMaster r")
@Where(clause="delete_ind is NULL or delete_ind='N'")
public class RuleMaster implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="rule_id")
	private int ruleId;

	@Column(name="create_date", updatable=false)
	private Timestamp createDate;

	@Column(name="create_user")
	private String createUser;

	@Temporal(TemporalType.DATE)
	@Column(name="end_date")
	private Date endDate;

	@Column(name="rule_description")
	private String ruleDescription;

	@Column(name="rule_name")
	private String ruleName;

	@Temporal(TemporalType.DATE)
	@Column(name="start_date")
	private Date startDate;

	@Column(name="update_date", insertable=false)
	private Timestamp updateDate;

	@Column(name="update_user")
	private String updateUser;

	@Column(name="delete_ind")
	private String deleteInd;
	
	//bi-directional many-to-one association to BranchRule
	@OneToMany(mappedBy="ruleMaster", cascade=CascadeType.ALL)
	private List<BranchRule> branchRules;

	//bi-directional many-to-one association to ModuleMaster
	@ManyToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="module_id")
	private ModuleMaster moduleMaster;

	//bi-directional many-to-one association to RuleMasterValue
	@OneToMany(mappedBy="ruleMaster", cascade=CascadeType.ALL)
	private List<RuleMasterValue> ruleMasterValues;

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ruleId;
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
		RuleMaster other = (RuleMaster) obj;
		if (ruleId != other.ruleId)
			return false;
		return true;
	}

	public String getDeleteInd() {
		return deleteInd;
	}
	public void setDeleteInd(String deleteInd) {
		this.deleteInd = deleteInd;
	}
	
	public RuleMaster() {
	}

	public int getRuleId() {
		return this.ruleId;
	}

	public void setRuleId(int ruleId) {
		this.ruleId = ruleId;
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

	public Date getEndDate() {
		return this.endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public String getRuleDescription() {
		return this.ruleDescription;
	}

	public void setRuleDescription(String ruleDescription) {
		this.ruleDescription = ruleDescription;
	}

	public String getRuleName() {
		return this.ruleName;
	}

	public void setRuleName(String ruleName) {
		this.ruleName = ruleName;
	}

	public Date getStartDate() {
		return this.startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Timestamp getUpdateDate() {
		return this.updateDate;
	}

	public void setUpdateDate(Timestamp updateDate) {
		this.updateDate = updateDate;
	}

	public String getUpdateUser() {
		return this.updateUser;
	}

	public void setUpdateUser(String updateUser) {
		this.updateUser = updateUser;
	}

	public List<BranchRule> getBranchRules() {
		return this.branchRules;
	}

	public void setBranchRules(List<BranchRule> branchRules) {
		this.branchRules = branchRules;
	}

	public BranchRule addBranchRule(BranchRule branchRule) {
		getBranchRules().add(branchRule);
		branchRule.setRuleMaster(this);

		return branchRule;
	}

	public BranchRule removeBranchRule(BranchRule branchRule) {
		getBranchRules().remove(branchRule);
		branchRule.setRuleMaster(null);

		return branchRule;
	}

	public ModuleMaster getModuleMaster() {
		return this.moduleMaster;
	}

	public void setModuleMaster(ModuleMaster moduleMaster) {
		this.moduleMaster = moduleMaster;
	}

	public List<RuleMasterValue> getRuleMasterValues() {
		return this.ruleMasterValues;
	}

	public void setRuleMasterValues(List<RuleMasterValue> ruleMasterValues) {
		this.ruleMasterValues = ruleMasterValues;
	}

	public RuleMasterValue addRuleMasterValue(RuleMasterValue ruleMasterValue) {
		getRuleMasterValues().add(ruleMasterValue);
		ruleMasterValue.setRuleMaster(this);

		return ruleMasterValue;
	}

	public RuleMasterValue removeRuleMasterValue(RuleMasterValue ruleMasterValue) {
		getRuleMasterValues().remove(ruleMasterValue);
		ruleMasterValue.setRuleMaster(null);

		return ruleMasterValue;
	}
	@PreUpdate
	@PrePersist
	public void updateTimeStamps() {
	    updateDate = new Timestamp(System.currentTimeMillis());
	    if (createDate == null) {
	    	createDate = new Timestamp(System.currentTimeMillis());
	    }
	}
}