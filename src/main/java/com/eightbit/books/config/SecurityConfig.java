package com.eightbit.books.config;

import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {

	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//		System.out.println("in security	");
//		String pass = "eightbit";
//		String hashPass = this.passwordEncoder().encode("eightbit");
//		System.out.println(this.passwordEncoder().encode("eightbit"));
//		System.out.println(this.passwordEncoder().matches(pass, hashPass));

		http.formLogin(login -> login
				// 指定したURLがリクエストされるとログイン認証を行う。
				.loginProcessingUrl("/login")

				// ログイン時のURLの指定
				.loginPage("/login")

				// 認証成功後にリダイレクトする場所の指定
				.defaultSuccessUrl("/", true)

				// ログインに失敗した時のURL
				.failureUrl("/login?error")

				// アクセス権限の有無（permitAllは全てのユーザーがアクセス可能)
				.permitAll()

		).logout(logout -> logout.logoutSuccessUrl("/login")

		// アクセス制限
		).authorizeHttpRequests(
				ahr -> ahr.requestMatchers(PathRequest.toStaticResources().atCommonLocations()).permitAll()

						.requestMatchers("/img/**").permitAll()

						// "/admin"はADMIN権限のあるものだけがアクセスできる
						.requestMatchers("/admin").hasRole("ADMIN")

						// 他のリンクは全て認証が必要である。
						.anyRequest().authenticated());
		/**
		 * HttpSecurityオブジェクトを生成し、オブジェクトもしくはnullを返す。
		 * build()メソッドはHttpSecurityBuilderインターフェースのメソッド。 HttpSecurityBuilderは、
		 * HttpSecurity、WebSecurityなどの実装クラス
		 * HttpSecurityは、DefaultSecurityFilterChainインターフェースを実装しており、
		 * DefaultSecurityFilterChainはSecurityFilterChainを実装しているため、ポリモーフィズム的にも正しい。
		 */
		return http.build();

	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
}
