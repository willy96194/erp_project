package com.example.erp.service;

import org.springframework.stereotype.Service;

import com.example.erp.entity.CompanyInfo;
import com.example.erp.repository.CompanyInfoRespository;

@Service
public class CompanyInfoService {
	private final CompanyInfoRespository comRespository;
	
	public CompanyInfoService(CompanyInfoRespository comRespository) {
		this.comRespository = comRespository;
	}
	
	public CompanyInfo save(CompanyInfo companyInfo) {
		return comRespository.save(companyInfo);
	}
	
}
