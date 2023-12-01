package com.eightbit.books.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Controller
public class LoginController {
	
	/**
	 * SecurityContextLogoutHandlerをインスタンス化
	 * ユーザーがログアウトしたときにセキュリティコンテキストをクリアする役割がある
	 */
	SecurityContextLogoutHandler logoutHandler = new SecurityContextLogoutHandler();
	
	/**
	 * ユーザーがブラウザで"/login"にアクセスすると、"login"というビューが表示される
	 */
	@GetMapping("/login")
	public String redirectLoginPage() {
		return "login";
	}
	
	/**
	 * ログアウト処理を担当し、ログアウトが成功したらログインページにリダイレクト
	 */
	@PostMapping("/logout")
	public String logout(Authentication authentication, HttpServletRequest request, HttpServletResponse response) {
		this.logoutHandler.logout(request, response, authentication);
		return "redirect:/login";
	}
}