package kr.co.sist.prepared.vo;

public class ClobVO {

	private String subject,content,inputDate;

	/**
	 * @return the subject
	 */
	public String getSubject() {
		return subject;
	}

	/**
	 * @param subject the subject to set
	 */
	public void setSubject(String subject) {
		this.subject = subject;
	}

	/**
	 * @return the content
	 */
	public String getContent() {
		return content;
	}

	/**
	 * @param content the content to set
	 */
	public void setContent(String content) {
		this.content = content;
	}

	/**
	 * @return the inputDate
	 */
	public String getInputDate() {
		return inputDate;
	}

	/**
	 * @param inputDate the inputDate to set
	 */
	public void setInputDate(String inputDate) {
		this.inputDate = inputDate;
	}

	@Override
	public String toString() {
		return "ClobVO [subject=" + subject + ", content=" + content + ", inputDate=" + inputDate + "]";
	}//debug¿ë
	
	
	
}//class


