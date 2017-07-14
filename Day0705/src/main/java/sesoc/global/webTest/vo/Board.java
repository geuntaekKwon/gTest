package sesoc.global.webTest.vo;

public class Board {
	private int boardnum;
	private String custid;
	private String title;
	private String content; 
	private String inputdate;
	private int hits;
	private String originalfile;
	private String savedfile;
	
	public Board() {}
	
	public int getBoardnum() {
		return boardnum;
	}
	public void setBoardnum(int boardnum) {
		this.boardnum = boardnum;
	}
	public String getCustid() {
		return custid;
	}
	public void setCustid(String custid) {
		this.custid = custid;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getInputdate() {
		return inputdate;
	}
	public void setInputdate(String inputdate) {
		this.inputdate = inputdate;
	}
	public int getHits() {
		return hits;
	}
	public void setHits(int hits) {
		this.hits = hits;
	}
	public String getOriginalfile() {
		return originalfile;
	}
	public void setOriginalfile(String originalfile) {
		this.originalfile = originalfile;
	}
	public String getSavedfile() {
		return savedfile;
	}
	public void setSavedfile(String savedfile) {
		this.savedfile = savedfile;
	}
	@Override
	public String toString() {
		return "Board [boardnum=" + boardnum + ", custid=" + custid + ", title=" + title + ", content=" + content
				+ ", inputdate=" + inputdate + ", hits=" + hits + ", originalfile=" + originalfile + ", savedfile="
				+ savedfile + "]";
	}
}
