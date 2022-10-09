/*
 * ----------------Design--------------------------------------------
 * [First] [Prev] 11 12 13 14 15 16 17 18 19 20 [Next] [Last] 
 * 
 * ----------------Numbers for pagination--------------------------------------
 * [First] -- 1
 * [Prev]  -- prevBlock
 * 11      -- firstPage (within current block)
 * 20      -- lastPage (within current block)
 * [Next]  -- nextBlock
 * [Last]  -- totalPage (totalPage is equal to the final page number)
 * 
 * -----------------Number for design-------------------------------------
 * listItemNo  -- The number to append to the first item in the list (Not persistent data)
 * 
 * ------------------Numbers for fetch query-------------------
 * startRecord -- First record number on the current page
 * endRecord   -- Last record number on the current page
 */
package net.java_school.commons;

import java.util.List;

public interface Paginator {

	public int getTotalRecordCount(String searchWord);

	public List<?> getItems(String searchWord, int startRecord, int endRecord);

	public default NumbersForPagination getNumbersForPagination(int totalRecordCount, int page, int recordsPerPage, int pagesPerBlock) {

		NumbersForPagination numbers = new NumbersForPagination();

		int totalPage = totalRecordCount / recordsPerPage;
		if (totalRecordCount % recordsPerPage != 0) totalPage++;

		int totalBlock = totalPage / pagesPerBlock;
		if (totalPage % pagesPerBlock != 0) totalBlock++;

		int block = page / pagesPerBlock;
		if (page % pagesPerBlock != 0) block++;

		int firstPage = (block - 1) * pagesPerBlock + 1;
		int lastPage = block * pagesPerBlock;

		int prevBlock = 0;
		if (block > 1) prevBlock = firstPage - 1;

		int nextBlock = 0;
		if (block < totalBlock) nextBlock = lastPage + 1;
		if (block >= totalBlock) lastPage = totalPage;
		
		int listItemNo = totalRecordCount - (page - 1) * recordsPerPage;
		int startRecord = (page - 1) * recordsPerPage + 1;
		int endRecord = page * recordsPerPage;

		numbers.setPrevBlock(prevBlock);
		numbers.setFirstPage(firstPage);
		numbers.setLastPage(lastPage);
		numbers.setNextBlock(nextBlock);
		numbers.setTotalPage(totalPage);

		numbers.setListItemNo(listItemNo);

		numbers.setStartRecord(startRecord);
		numbers.setEndRecord(endRecord);

		return numbers;
	}
}
