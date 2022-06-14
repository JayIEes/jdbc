package day0304;

/**
 * 싱글톤 패턴이 적용된 클래스 사용.
 * @author user
 */
public class UseSingleton {

	public static void main(String[] args) {
//		TestSingleton ts = new TestSingleton();
		TestSingleton ts= TestSingleton.getInstance();
		
		TestSingleton ts2= TestSingleton.getInstance();
		System.out.println(ts);
		System.out.println(ts2);
	}

}
