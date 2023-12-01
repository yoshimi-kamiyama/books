package com.eightbit.books.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.eightbit.books.entity.History;
import com.eightbit.books.model.HistorySearchQuery;
import com.eightbit.books.service.HistoryService;

@Controller
public class HistoryController {
	
	/**
	 * HistoryServiceをインスタンス化
	 * HistoryServiceに定義されたメソッドなどを呼び出すことが出来る
	 */
	private final HistoryService historyService;
	
	@Autowired
	public HistoryController(HistoryService historyService) {
		this.historyService = historyService;
	}
	
	@GetMapping(path = "/")
	public String routeToIndex(Model model) {
		List<History> historyList = historyService.findAll();
		model.addAttribute("historyList", historyList);
		model.addAttribute("searchQuery", new HistorySearchQuery());
		return "index";
	}
	
	@GetMapping("/search")
	public String searchHistory(Model model, @ModelAttribute HistorySearchQuery searchQuery) {
		List<History> historyList = historyService.searchHistory(searchQuery);
		model.addAttribute("historyList", historyList);
		model.addAttribute("searchQuery", new HistorySearchQuery());
		model.addAttribute("searchedQuery", searchQuery);
		model.addAttribute("count", historyList.size());
		
		return "index";
	}
	
	@GetMapping("/search/id")
	public String searchHistoryId(Model model, @RequestParam("id") int historyId) {
		
		History history = historyService.findOne(historyId);
		model.addAttribute("history", history);
		
		return "historyDetail";
	}
	
	@PostMapping("/history/delete")
	public String deleteHistory(@RequestParam("id") int historyId) {
		historyService.deleteHistory(historyId);
		return "redirect:/";
	}
	
	@PostMapping("/history/update")
	public String updateDueDate(Model model, @RequestParam("id") int historyId,
			@RequestParam("dueDate") String dueDate) {
		
		historyService.updateDueDate(historyId, dueDate);
		
		String param = "?id=" + historyId;
		return "redirect:/search/id" + param;
	}
	
	@PostMapping("/history/return")
	public String returnBook(@RequestParam("id") int historyId) {
		historyService.returnBook(historyId);
		String param = "?id=" + historyId;
		return "redirect:/search/id" + param;
	}
	
	@PostMapping("/history/checkout")
	public String checkout(@RequestParam("bookId") int bookId, @RequestParam("userId") int userId,
			@RequestParam("due") String dueDate) {
		
		historyService.checkoutBook(bookId, userId, dueDate);
		
		return "redirect:/";
	}
}
