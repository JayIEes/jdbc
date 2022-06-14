package day0310;

public class ResultVO {

	private int rowCnt;
	private String errMsg;
	/**
	 * @return the rowCnt
	 */
	public int getRowCnt() {
		return rowCnt;
	}
	/**
	 * @param rowCnt the rowCnt to set
	 */
	public void setRowCnt(int rowCnt) {
		this.rowCnt = rowCnt;
	}
	/**
	 * @return the errMsg
	 */
	public String getErrMsg() {
		return errMsg;
	}
	/**
	 * @param errMsg the errMsg to set
	 */
	public void setErrMsg(String errMsg) {
		this.errMsg = errMsg;
	}
	
	@Override
	public String toString() {
		return "ResultVO [rowCnt=" + rowCnt + ", errMsg=" + errMsg + "]";
	}
	
	
	
	
	
}//class
