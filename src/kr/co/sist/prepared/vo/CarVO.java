package kr.co.sist.prepared.vo;

public class CarVO {

	private String carCountry,carMaker,carModel,carYear,carOption;
	private int price;

	
	public CarVO() {
	}


	public CarVO(String carCountry, String carMaker, String carModel, String carYear, String carOption, int price) {
		super();
		this.carCountry = carCountry;
		this.carMaker = carMaker;
		this.carModel = carModel;
		this.carYear = carYear;
		this.carOption = carOption;
		this.price = price;
	}


	/**
	 * @return the carCountry
	 */
	public String getCarCountry() {
		return carCountry;
	}


	/**
	 * @param carCountry the carCountry to set
	 */
	public void setCarCountry(String carCountry) {
		this.carCountry = carCountry;
	}


	/**
	 * @return the carMaker
	 */
	public String getCarMaker() {
		return carMaker;
	}


	/**
	 * @param carMaker the carMaker to set
	 */
	public void setCarMaker(String carMaker) {
		this.carMaker = carMaker;
	}


	/**
	 * @return the carModel
	 */
	public String getCarModel() {
		return carModel;
	}


	/**
	 * @param carModel the carModel to set
	 */
	public void setCarModel(String carModel) {
		this.carModel = carModel;
	}


	/**
	 * @return the carYear
	 */
	public String getCarYear() {
		return carYear;
	}


	/**
	 * @param carYear the carYear to set
	 */
	public void setCarYear(String carYear) {
		this.carYear = carYear;
	}


	/**
	 * @return the carOption
	 */
	public String getCarOption() {
		return carOption;
	}


	/**
	 * @param carOption the carOption to set
	 */
	public void setCarOption(String carOption) {
		this.carOption = carOption;
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


	@Override
	public String toString() {
		return "CarVO [carCountry=" + carCountry + ", carMaker=" + carMaker + ", carModel=" + carModel + ", carYear="
				+ carYear + ", carOption=" + carOption + ", price=" + price + "]";
	}

	
	
	
}//class
