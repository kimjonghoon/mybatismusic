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
import net.java_school.commons.NumbersForPaging;

@Controller
public class HomeController implements Paginator {

	@Autowired
	private MusicVideoService musicVideoService;

	/*
	private Map<String, Integer> getNumbersForPaging(int totalRecord, int page, int numPerPage, int pagePerBlock) {
		Map<String, Integer> map = new HashMap<String, Integer>();

		int totalPage = totalRecord / numPerPage;
		if (totalRecord % numPerPage != 0) totalPage++;

		int totalBlock = totalPage / pagePerBlock;
		if (totalPage % pagePerBlock != 0) totalBlock++;

		int block = page / pagePerBlock;
		if (page % pagePerBlock != 0) block++;

		int firstPage = (block - 1) * pagePerBlock + 1;
		int lastPage = block * pagePerBlock;

		int prevPage = 0;
		if (block > 1) prevPage = firstPage - 1;

		int nextPage = 0;
		if (block < totalBlock) nextPage = lastPage + 1;
		if (block >= totalBlock) lastPage = totalPage;
		
		int listItemNo = totalRecord - (page - 1) * numPerPage;
		int startRecord = (page - 1) * numPerPage + 1;
		int endRecord = page * numPerPage;

		map.put("totalPage", totalPage);
		map.put("firstPage", firstPage);
		map.put("lastPage", lastPage);
		map.put("prevPage", prevPage);
		map.put("nextPage", nextPage);
		map.put("startRecord", startRecord);
		map.put("endRecord", endRecord);

		return map;
	}
	*/

	@GetMapping("/")
	public String index(Integer page, Model model) {
		if (page == null) return "redirect:/?page=1";

		int numPerPage = 4;
		int pagePerBlock = 30;

		int totalRecord = musicVideoService.getTotalRecords();

		NumbersForPaging numbers = getNumbersForPaging(totalRecord, page, numPerPage, pagePerBlock);
		Integer startRecord = numbers.getStartRecord();
		Integer endRecord = numbers.getEndRecord();

		List<MusicVideo> list = musicVideoService.getVideos(startRecord, endRecord);

		Integer prevBlock = numbers.getPrevBlock();
		Integer nextBlock = numbers.getNextBlock();
		Integer firstPage = numbers.getFirstPage();
		Integer lastPage = numbers.getLastPage();
		Integer totalPage = numbers.getTotalPage();

		model.addAttribute("list", list);
		model.addAttribute("prevBlock", prevBlock);
		model.addAttribute("nextBlock", nextBlock);
		model.addAttribute("firstPage", firstPage);
		model.addAttribute("lastPage", lastPage);
		model.addAttribute("totalPage", totalPage);

		return "index";
	}

	@PostMapping("/")
	@ResponseBody
	public void add(String content) {
		musicVideoService.add(content);	
	}
}

