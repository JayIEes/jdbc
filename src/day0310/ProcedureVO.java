package day0310;

public class ProcedureVO {
	
	private int num,age;
	private String name, email,input_date;
	
	
	/**
	 * @return the num
	 */
	public int getNum() {
		return num;
	}
	/**
	 * @param num the num to set
	 */
	public void setNum(int num) {
		this.num = num;
	}
	/**
	 * @return the age
	 */
	public int getAge() {
		return age;
	}
	/**
	 * @param age the age to set
	 */
	public void setAge(int age) {
		this.age = age;
	}
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}
	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}
	/**
	 * @return the input_date
	 */
	public String getInput_date() {
		return input_date;
	}
	/**
	 * @param input_date the input_date to set
	 */
	public void setInput_date(String input_date) {
		this.input_date = input_date;
	}
	@Override
	public String toString() {
		return "ProcedureVO [num=" + num + ", age=" + age + ", name=" + name + ", email=" + email + ", input_date="
				+ input_date + "]";
	}
	
	
}//class
