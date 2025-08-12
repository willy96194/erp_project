package com.example.erp.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import com.example.erp.dto.RegistrationRequest;
import com.example.erp.entity.CompanyInfo;
import com.example.erp.entity.UserInfo;
import com.example.erp.service.CompanyInfoService;
import com.example.erp.service.UserInfoService;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/api/register")
@AllArgsConstructor
public class RegistrationController {
	
	private final CompanyInfoService companyService;
	private final UserInfoService userService;
	
	public RegistrationController(CompanyInfoService companyService, UserInfoService userService) {
		this.companyService = companyService;
		this.userService = userService;
	}
	
	@PostMapping
	@Transactional
	public ResponseEntity<String> register(@RequestBody RegistrationRequest request) {
		// 新增公司
		CompanyInfo company = new CompanyInfo();
		company.setcName(request.getcName());
		company.setTaxId(request.getTaxId());
		company.setrName(request.getrName());
		company.setrTel(request.getrTel());
		company.setrEmail(request.getuEmail());

		CompanyInfo savedCompany = companyService.save(company); ;

		
		// 新增User
		UserInfo user = new UserInfo();
		user.setuAccount(request.getuAccount() );
		user.setuEmail(request.getuEmail());
		user.setuPassword(UserInfoService.encodePassowrd(request.getPassword()));
		user.setStatus(request.getStatus());
		user.setRole(request.getRole());
		user.setCompanyId(savedCompany);  //companyId
		userService.save(user);
		
		return ResponseEntity.ok("註冊成功");
	}
	
}
