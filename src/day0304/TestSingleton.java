package day0304;

/**
 * @author user
 * �̱��� ������ ������ Ŭ����
 * 1.Ŭ���� �ܺο��� ���� ��üȭ�� �� ������Ѵ�. => �����ڸ� private���� �ش�.
 * 2.��ü�� ��ȯ�ϴ� method ����
 * (��ü�� �ϳ��� ����)
 */
public class TestSingleton {
	
	private static TestSingleton ts;
	
	
	private TestSingleton() {
		
	}//TestSingleton
	
	
	
	public static TestSingleton getInstance() {
		
		if(ts==null) {//��ü�� �������� �ʴ� ��쿡�� ���� (����ȣ��/������ �÷����� ���� ��뵵�� ��ü�� ���� ���)
			ts=new TestSingleton();
		}//end if
		
		return ts;
	}//getInstance
	
	
	
	
}//class
