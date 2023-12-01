package com.eightbit.books.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.eightbit.books.entity.Books;
import com.eightbit.books.entity.Genre;
import com.eightbit.books.model.BookSearchQuery;
import com.eightbit.books.service.BookService;


@Controller
public class BookController {
	
	private final BookService bookService;
	
	@Autowired
	public BookController(BookService bookService) {
		this.bookService = bookService;
	}
	
	@GetMapping(path = "/book/index")
	public String routeToBookIndex(Model model) {
		List<Books> bookList = bookService.findAll();
		model.addAttribute("bookList", bookList);
		model.addAttribute("searchQuery", new BookSearchQuery());
		return "bookIndex";
	}
	
	@GetMapping("/book/search")
	public String searchBook(Model model, @ModelAttribute BookSearchQuery searchQuery) {
		
		List<Books> bookList = bookService.searchBook(searchQuery);
		model.addAttribute("bookList", bookList);
		model.addAttribute("searchQuery", new BookSearchQuery());
		model.addAttribute("searchedQuery", searchQuery);
		model.addAttribute("count", bookList.size());
		
		return "bookIndex";
	}
	
	@GetMapping("/book/search/genre")
	public String searchBookGenre(Model model, @RequestParam("id") int genreId) {
		
		List<Books> bookList = bookService.searchBookGenre(genreId);
		model.addAttribute("bookList", bookList);
		model.addAttribute("searchQuery", new BookSearchQuery());
		model.addAttribute("count", bookList.size());
		
		return "bookIndex";
	}
	
	@GetMapping("/book/search/id")
	public String searchBookId(Model model, @RequestParam("id") int bookId) {
		
		Books book = bookService.findOne(bookId);
		model.addAttribute("book", book);
		model.addAttribute("searchQuery", new Books());
		
		return "bookDetail";
	}
	
	@PostMapping("/book/delete")
	public String deleteBook(@RequestParam("id") int bookId) {
		bookService.deleteBookAndHistoryData(bookId);
		return "redirect:/book/index";
	}
	
	@PostMapping("/book/update")
	public String updateBook(Model model, @RequestParam("id") int bookId, @RequestParam("stock") int stock,
			@RequestParam("status") int status) {
		
		bookService.updateBookStock(bookId, stock, status);
		
		String param = "?id=" + bookId;
		return "redirect:/book/search/id" + param;
	}
	
	@PostMapping("/book/checkout")
	public String checkoutBook(Model model, @RequestParam("id") int bookId) {
		Books book = bookService.findOne(bookId);
		model.addAttribute("book", book);
		return "checkout";
	}
	
	@GetMapping("/book/regist")
	public String bookRegist(Model model) {
		List<Genre> genreList = bookService.getGenreAll();
		model.addAttribute("genreList", genreList);
		model.addAttribute("bookEntity", new Books());
		return "bookRegist";
	}
	
	@PostMapping("/book/registExecute")
	public String bookRegistExcute(@ModelAttribute Books book, @RequestParam("genreId") int genreId) {
		bookService.bookRegist(book, genreId);
		return "redirect:/book/index";
	}
	
}
