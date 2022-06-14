package day0304Work_VO;

public class CarModelSelectVO {

	private String car_year;
	private int price;
	private int cc;
	private String car_option;
	
	public CarModelSelectVO() {
		
	}

	public CarModelSelectVO(String car_year, int price, int cc, String car_option) {
		super();
		this.car_year = car_year;
		this.price = price;
		this.cc = cc;
		this.car_option = car_option;
	}

	/**
	 * @return the car_year
	 */
	public String getCar_year() {
		return car_year;
	}

	/**
	 * @param car_year the car_year to set
	 */
	public void setCar_year(String car_year) {
		this.car_year = car_year;
	}

	/**
	 * @return the price
	 */
	public int getPrice() {
		return price;
	}

	/**
	 * @param price the price to set
	 */
	public void setPrice(int price) {
		this.price = price;
	}

	/**
	 * @return the cc
	 */
	public int getCc() {
		return cc;
	}

	/**
	 * @param cc the cc to set
	 */
	public void setCc(int cc) {
		this.cc = cc;
	}

	/**
	 * @return the car_option
	 */
	public String getCar_option() {
		return car_option;
	}

	/**
	 * @param car_option the car_option to set
	 */
	public void setCar_option(String car_option) {
		this.car_option = car_option;
	}
	
}//class
