package day0304;

/**
 * @author user
 * 싱글톤 패턴을 적용한 클래스
 * 1.클래스 외부에서 집접 객체화할 수 없어야한다. => 생성자를 private으로 준다.
 * 2.객체를 반환하는 method 생성
 * (객체를 하나로 유지)
 */
public class TestSingleton {
	
	private static TestSingleton ts;
	
	
	private TestSingleton() {
		
	}//TestSingleton
	
	
	
	public static TestSingleton getInstance() {
		
		if(ts==null) {//객체가 존재하지 않는 경우에만 생성 (최초호출/가비지 컬렉터의 의해 사용도중 객체가 죽은 경우)
			ts=new TestSingleton();
		}//end if
		
		return ts;
	}//getInstance
	
	
	
	
}//class
