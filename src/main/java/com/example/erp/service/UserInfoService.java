package com.example.erp.service;

import org.springframework.stereotype.Service;

import com.example.erp.entity.CompanyInfo;
import com.example.erp.entity.UserInfo;
import com.example.erp.repository.CompanyInfoRespository;
import com.example.erp.repository.UserInfoRepository;
import com.example.erp.util.BCrypt;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class UserInfoService {

	private final UserInfoRepository userRespository;
	// private String encodedPassword;
	
	public UserInfoService(UserInfoRepository userRespository) {
		this.userRespository = userRespository;
	}
	
	public static String encodePassowrd(String rawPassword) {
		String encodedPassword= BCrypt.hashpw(rawPassword, BCrypt.gensalt());
		return encodedPassword;
	}
	
	public String signUpUser(UserInfo userInfo) {
			boolean userExists = userRespository.findByUEmail(userInfo.getuEmail()).isPresent();

			if (userExists) {
				throw new IllegalStateException("此信箱已使用");
			}
			String encodedPassword = BCrypt.hashpw(userInfo.getuPassword(), BCrypt.gensalt());
			userInfo.setuPassword(encodedPassword);
			userRespository.save(userInfo);
			
			return "SingUp Ok";
			// token待寫
	}
	
	public UserInfo save(UserInfo userInfo) {
		return userRespository.save(userInfo);
	}
	
	public void enableUser(String email) {
		userRespository.enableUser(email);
	}
	
	
	
}
