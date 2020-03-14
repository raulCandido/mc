package com.mc.service;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.mc.security.UserSS;

@Service
public class UserService {

	public static UserSS authenticated() {

		try {
			//metodo pega o usuario logado no sistema agora
			return (UserSS) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		} catch (Exception e) {
			return null;
		}

	}
}
