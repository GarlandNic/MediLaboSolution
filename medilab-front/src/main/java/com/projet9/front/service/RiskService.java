package com.projet9.front.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.projet9.front.model.Risk;
import com.projet9.front.proxy.GatewayProxy;

@Service
public class RiskService {

	@Autowired 
	GatewayProxy gate;

	public Risk getRisk(int id) {
		return Risk.valueOf(gate.riskForPatient(id));
	}
	
	

}
