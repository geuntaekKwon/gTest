package sesoc.global.webTest.util;


public class PageNavigator {
	//멤버
	private int countPerPage = 10; // 한 페이지당 글 개수
	private int pagePerGroup = 5; // 페이지 그룹의 개수
	private int currentPage; //현재 페이지
	private int totalRecordCount; // 전체 글 개수
	private int totalPageCount; // 전체 페이지
	private int currentGroup; // 현재 그룹
	private int startPageGroup; // 현재 그룹의 첫 페이지
	private int endPageGroup; // 현재그룹의 마지막 페이지
	private int startRecord; // 전체 레코드 중 현재 페이지의 첫 글의 위치
	// srow, erow 계산하지 않고, mybatis의 기능을 사용할 예정임
	
	
	//생성자
	public PageNavigator(int currentPage, int totalRecordCount) {

		totalPageCount = (totalRecordCount + countPerPage -1) / countPerPage; // 딱 맞아 떨어졌을 경우를 대비해서 만든 식. (-1을 한 이유다.)
		
		// 현재 페이지 요청 시 계산
		if(currentPage < 1) currentPage = 1;
		if(currentPage > totalPageCount) currentPage = totalPageCount;
		
		this.currentPage = currentPage;
		this.totalRecordCount = totalRecordCount;
		
		// 현재 그룹
		// 내가 4페이지를 보고있다면, 4페이지는 몇번째 긃인가
		currentGroup = (currentPage-1) / pagePerGroup;
		
		//현재 그룹의 첫 페이지 계산
		startPageGroup = currentGroup * pagePerGroup + 1;
		startPageGroup = startPageGroup < 1 ? 1 : startPageGroup;
		
		//현재 그룹의 마지막 페이지 계산
		endPageGroup = startPageGroup + pagePerGroup - 1;
		endPageGroup = endPageGroup < totalPageCount ? endPageGroup : totalPageCount;
		
		// 전체 레코드 중에서 현재 페이지의 첫글의 위치 # 오라클에서 제공하는 포인터를 이용해서 설정되도록 할 것이다! // mybatis의 rowbounds method 사용
		startRecord = (currentPage - 1) * countPerPage;
		
	}//PageNavigator


	public int getCountPerPage() {
		return countPerPage;
	}

	public int getPagePerGroup() {
		return pagePerGroup;
	}

	public int getCurrentPage() {
		return currentPage;
	}

	public int getTotalRecordCount() {
		return totalRecordCount;
	}

	public int getTotalPageCount() {
		return totalPageCount;
	}

	public int getCurrentGroup() {
		return currentGroup;
	}

	public int getStartPageGroup() {
		return startPageGroup;
	}

	public int getEndPageGroup() {
		return endPageGroup;
	}

	public int getStartRecord() {
		return startRecord;
	}

	@Override
	public String toString() {
		return "PageNavigator [COUNT_PER_PAGE=" + countPerPage + ", PAGE_PER_GROUP=" + pagePerGroup
				+ ", currentPage=" + currentPage + ", totalRecordCount=" + totalRecordCount + ", totalPageCount="
				+ totalPageCount + ", currentGroup=" + currentGroup + ", startPageGroup=" + startPageGroup
				+ ", endPageGroup=" + endPageGroup + ", startRecord=" + startRecord + "]";
	}
	
}//class
