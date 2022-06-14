package kr.co.sist.prepared.vo;

/**
 * @author user
 * 컬럼의 정보를 저장할 목적의 VO
 */
public class ColumnVO {

	private String columnName,dataType;
	private int dataLength;
	
	
	public ColumnVO() {
	}

	
	public ColumnVO(String columnName, String dataType, int dataLength) {
		super();
		this.columnName = columnName;
		this.dataType = dataType;
		this.dataLength = dataLength;
	}


	/**
	 * @return the columnName
	 */
	public String getColumnName() {
		return columnName;
	}


	/**
	 * @param columnName the columnName to set
	 */
	public void setColumnName(String columnName) {
		this.columnName = columnName;
	}


	/**
	 * @return the dataType
	 */
	public String getDataType() {
		return dataType;
	}


	/**
	 * @param dataType the dataType to set
	 */
	public void setDataType(String dataType) {
		this.dataType = dataType;
	}


	/**
	 * @return the dataLength
	 */
	public int getDataLength() {
		return dataLength;
	}


	/**
	 * @param dataLength the dataLength to set
	 */
	public void setDataLength(int dataLength) {
		this.dataLength = dataLength;
	}


	@Override
	public String toString() {
		return "ColumnVO [columnName=" + columnName + ", dataType=" + dataType + ", dataLength=" + dataLength + "]";
	}

	
}//class
