package com.org.coop.retail.bs.mapper;

import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.org.coop.retail.bs.config.RetailDozerConfig;

@Component
public class PaymentMappingImpl {
	@Autowired
	private RetailDozerConfig retailDozerConfig;
	
	public void mapBean(Object src, Object dest) {
		Mapper dozerBeanMapper = (Mapper) retailDozerConfig.dozerBean();
		dozerBeanMapper.map(src, dest, "paymentMap");
	}
	
	public void mapCashPaymentBean(Object src, Object dest) {
		Mapper dozerBeanMapper = (Mapper) retailDozerConfig.dozerBean();
		dozerBeanMapper.map(src, dest, "cashPaymentMap");
	}
	
	public void mapCardPaymentBean(Object src, Object dest) {
		Mapper dozerBeanMapper = (Mapper) retailDozerConfig.dozerBean();
		dozerBeanMapper.map(src, dest, "cardPaymentMap");
	}
}