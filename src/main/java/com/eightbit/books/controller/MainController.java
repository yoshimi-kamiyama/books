package com.eightbit.books.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.eightbit.books.entity.History;
import com.eightbit.books.repository.HistoryRepository;

@Controller
public class MainController {
	
	/**
	 * HistoryRepositoryをインスタンス化
	 * HistoryRepositoryに定義されたメソッドなどを呼び出すことが出来る
	 */
	@Autowired
	private HistoryRepository historyRepo;
	
	/**
	 * データベースから全てのHistoryオブジェクトを取得し、modelに追加
	 */
	@GetMapping(path = "/index")
	public String routeToIndex(Model model) {

		List<History> historyList = historyRepo.findAll();
//		historyList.stream().forEach(h -> System.out.println(
//				h.getBooks().getTitle() + " " + h.getBooks().getGenre().getGenre() + " " + h.getUser().getLastName()));

		model.addAttribute("historyList", historyList);
		return "index";
	}
}
