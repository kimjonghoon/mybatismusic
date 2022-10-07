/*
 * ----------------Design--------------------------------------------
 * [First] [Prev] 11 12 13 14 15 16 17 18 19 20 [Next] [Last] 
 * 
 * ----------------Numbers for paging--------------------------------------
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

public interface Paginator {

	public default NumbersForPaging getNumbersForPaging(int totalRecord, int page, int numPerPage, int pagePerBlock) {

		NumbersForPaging numbers = new NumbersForPaging();

		int totalPage = totalRecord / numPerPage;
		if (totalRecord % numPerPage != 0) totalPage++;

		int totalBlock = totalPage / pagePerBlock;
		if (totalPage % pagePerBlock != 0) totalBlock++;

		int block = page / pagePerBlock;
		if (page % pagePerBlock != 0) block++;

		int firstPage = (block - 1) * pagePerBlock + 1;
		int lastPage = block * pagePerBlock;

		int prevBlock = 0;
		if (block > 1) prevBlock = firstPage - 1;

		int nextBlock = 0;
		if (block < totalBlock) nextBlock = lastPage + 1;
		if (block >= totalBlock) lastPage = totalPage;
		
		int listItemNo = totalRecord - (page - 1) * numPerPage;
		int startRecord = (page - 1) * numPerPage + 1;
		int endRecord = page * numPerPage;

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
