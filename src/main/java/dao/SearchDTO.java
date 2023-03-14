package dao;

public class SearchDTO {

	private String  searchTitle;
	private String  searchUserName;
	private String  searchFirstDate;
	private String  searchLastDate;
	private int min;
	private int max;
	
	public String getSearchTitle() {
		return searchTitle;
	}
	public void setSearchTitle(String searchTitle) {
		this.searchTitle = searchTitle;
	}
	public String getSearchUserName() {
		return searchUserName;
	}
	public void setSearchUserName(String searchUserName) {
		this.searchUserName = searchUserName;
	}
	public String getSearchFirstDate() {
		return searchFirstDate;
	}
	public void setSearchFirstDate(String searchFirstDate) {
		this.searchFirstDate = searchFirstDate;
	}
	public String getSearchLastDate() {
		return searchLastDate;
	}
	public void setSearchLastDate(String searchLastDate) {
		this.searchLastDate = searchLastDate;
	}
	public int getMin() {
		return min;
	}
	public void setMin(int min) {
		this.min = min;
	}
	public int getMax() {
		return max;
	}
	public void setMax(int max) {
		this.max = max;
	}
	
	
}
