package day0307Work_VO;

public class SelectTableVO {

	private String column_name;
	private String data_type;
	private int data_length;
	
	public SelectTableVO() {
	}//

	public SelectTableVO(String column_name, String data_type, int data_length) {
		super();
		this.column_name = column_name;
		this.data_type = data_type;
		this.data_length = data_length;
	}

	/**
	 * @return the column_name
	 */
	public String getColumn_name() {
		return column_name;
	}

	/**
	 * @param column_name the column_name to set
	 */
	public void setColumn_name(String column_name) {
		this.column_name = column_name;
	}

	/**
	 * @return the data_type
	 */
	public String getData_type() {
		return data_type;
	}

	/**
	 * @param data_type the data_type to set
	 */
	public void setData_type(String data_type) {
		this.data_type = data_type;
	}

	/**
	 * @return the data_length
	 */
	public int getData_length() {
		return data_length;
	}

	/**
	 * @param data_length the data_length to set
	 */
	public void setData_length(int data_length) {
		this.data_length = data_length;
	}
	
	
	
	
	
}//class
