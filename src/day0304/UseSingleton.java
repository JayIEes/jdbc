package day0304;

/**
 * �̱��� ������ ����� Ŭ���� ���.
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
