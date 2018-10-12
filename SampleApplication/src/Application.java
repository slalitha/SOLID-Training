

public class Application {
	public static void main(String[] args) {
		Sample s1 = new Sample(10,"saching");
		Sample s2 = new Sample(7,"dhoni");
		Sample s3 = new Sample(10,"sachin");
		Sample s4 = s2;
		
		if(s1==s3) {
			System.out.println("s1==s3 - true");
		}
		if(s2==s4) {
			System.out.println("s2==s4 - true");
		}
		if(s1==s2) {
			System.out.println("s1==s2 - true");
		}
		
		if(s1.equals(s3)) {
			System.out.println("s1==s3 - equals");
		}
		if(s2.equals(s4)) {
			System.out.println("s2==s4 - equals");
		}
		if(s1.equals(s2)) {
			System.out.println("s1==s2 - equals");
		}
		
		Sample s = null;
		Object obj = null;
		String str = null;
		Integer integer = null;
		
		s1.add(null);
	}

}
