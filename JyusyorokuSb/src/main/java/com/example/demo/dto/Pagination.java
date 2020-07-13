package com.example.demo.dto;

public class Pagination {

	//1ページ当たりの件数
	private int pageSize =10;

	//ページングしたブロック数
	private int blockSize =10;

	//現在のページ
	private int page=1;

	//現在のブロック
	private int block=1;

	//総件数
	private int totalListCnt;

	//総ページ数
	private int totalPageCnt;

	//総ブロック数
	private int totalBlockCnt;

	//ブロックスタートページ
	private int startPage=1;

	//ブロック最終ページ
	private int endPage=1;

	//DB接近スタートインデックス
	private int startIndex=0;

	//前ブロック最後ページ
	private int prevBlock;

	//次のブロックの最後ページ
	private int nextBlock;


	//ゲッター、セッター
	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getBlockSize() {
		return blockSize;
	}

	public void setBlockSize(int blockSize) {
		this.blockSize = blockSize;
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public int getBlock() {
		return block;
	}

	public void setBlock(int block) {
		this.block = block;
	}

	public int getTotalListCnt() {
		return totalListCnt;
	}

	public void setTotalListCnt(int totalListCnt) {
		this.totalListCnt = totalListCnt;
	}

	public int getTotalPageCnt() {
		return totalPageCnt;
	}

	public void setTotalPageCnt(int totalPageCnt) {
		this.totalPageCnt = totalPageCnt;
	}

	public int getTotalBlockCnt() {
		return totalBlockCnt;
	}

	public void setTotalBlockCnt(int totalBlockCnt) {
		this.totalBlockCnt = totalBlockCnt;
	}

	public int getStartPage() {
		return startPage;
	}

	public void setStartPage(int startPage) {
		this.startPage = startPage;
	}

	public int getEndPage() {
		return endPage;
	}

	public void setEndPage(int endPage) {
		this.endPage = endPage;
	}

	public int getStartIndex() {
		return startIndex;
	}

	public void setStartIndex(int startIndex) {
		this.startIndex = startIndex;
	}

	public int getPrevBlock() {
		return prevBlock;
	}

	public void setPrevBlock(int prevBlock) {
		this.prevBlock = prevBlock;
	}

	public int getNextBlock() {
		return nextBlock;
	}

	public void setNextBlock(int nextBlock) {
		this.nextBlock = nextBlock;
	}


	//ページング処理
	public Pagination(int totalListCnt,int page) {

		//現在のページ
		setPage(page);

		//総件数
		setTotalListCnt(totalListCnt);

		//総ページ数
		setTotalPageCnt((int) Math.ceil(totalListCnt * 1.0/pageSize));

		//総ブロック数
		setTotalBlockCnt((int) Math.ceil(totalPageCnt * 1.0/blockSize));

		//現在のブロック
		setBlock((int) Math.ceil(page * 1.0/blockSize));

		//ブロック始ページ
		setStartPage((block-1)*blockSize+1);

		//ブロック終ページ
		setEndPage(startPage+blockSize-1);

		//ブロック終ページのバリエーション
		if(endPage>totalPageCnt) {this.endPage=totalPageCnt;}

		//前ブロック
		setPrevBlock((block*blockSize)-blockSize);

		//前ブロックのバリエーション
		if(prevBlock<1) {this.prevBlock=1;}

		//次ブロック
		setNextBlock((block*blockSize)+1);

		//次ブロックのバリエーション
		if(nextBlock>totalPageCnt) {nextBlock=totalPageCnt;}

		//DB接近スタートインデックス
		setStartIndex((page-1)*pageSize);

	}

}
