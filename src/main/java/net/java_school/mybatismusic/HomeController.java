package net.java_school.mybatismusic;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;
import java.util.HashMap;

import net.java_school.commons.Paginator;
import net.java_school.commons.NumbersForPagination;

@Controller
public class HomeController implements Paginator {

	@Autowired
	private MusicVideoService service;

	@Override
	public int getTotalRecordCount(String searchWord) {
		return service.getTotalRecordCount(searchWord);
	}

	@Override
	public List<MusicVideo> getItems(String searchWord, int startRecord, int endRecord) {
		return service.getVideos(searchWord, startRecord, endRecord);
	}

	@GetMapping("/")
	public String index(Integer page, String searchWord, Model model) {
		if (page == null) return "redirect:/?page=1";

		int totalRecordCount = getTotalRecordCount(searchWord);

		int recordsPerPage = 4;
		int pagesPerBlock = 10;
		NumbersForPagination numbers = getNumbersForPagination(totalRecordCount, page, recordsPerPage, pagesPerBlock);
		Integer startRecord = numbers.getStartRecord();
		Integer endRecord = numbers.getEndRecord();
		List<MusicVideo> items = getItems(searchWord, startRecord, endRecord);

		model.addAttribute("items", items);
		model.addAttribute("prevBlock", numbers.getPrevBlock());
		model.addAttribute("nextBlock", numbers.getNextBlock());
		model.addAttribute("firstPage", numbers.getFirstPage());
		model.addAttribute("lastPage", numbers.getLastPage());
		model.addAttribute("totalPage", numbers.getTotalPage());

		return "index";
	}

	@PostMapping("/")
	@ResponseBody
	public void add(String content) {
		service.add(content);	
	}
}

