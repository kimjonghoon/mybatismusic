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

		numbers.setTotalPage(totalPage);
		numbers.setFirstPage(firstPage);
		numbers.setLastPage(lastPage);
		numbers.setPrevBlock(prevBlock);
		numbers.setNextBlock(nextBlock);
		numbers.setStartRecord(startRecord);
		numbers.setEndRecord(endRecord);

		return numbers;
	}
}
