package com.org.coop.retail.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.org.coop.retail.entities.MaterialGroup;
import com.org.coop.retail.entities.MaterialMaster;

public interface RetailMaterialGroupMasterRepository extends JpaRepository<MaterialGroup, Integer> {
	@Query("select mg from MaterialGroup mg where mg.branchMaster.branchId = :branchId order by mg.createDate desc")
	public List<MaterialGroup> findAllMaterialGroups(@Param("branchId") int branchId);
	
	@Query(value = "select count(*) from material_group where " +
			"exists(select * from material_master where material_grp_id = :materialGrpId limit 1) " +
			"and material_grp_id = :materialGrpId", nativeQuery=true)
	public int checkIfAnyChildRecordExists(@Param("materialGrpId") int materialGrpId);
}
