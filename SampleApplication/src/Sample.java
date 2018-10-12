
public class Sample {
	private int id;
	private String name;

	public Sample(int id, String name) {
		this.id = id;
		this.name = name;
	}
	
//	public void add(Integer a) {
//		System.out.println("add integer");	
//	}
	
	public void add(Object obj) {
		System.out.println("add object");
	}
	
	public void add(String str) {
		System.out.println("add String");
	}
	
	
	@Override
	public int hashCode() {
		// TODO Auto-generated method stub
		return super.hashCode();
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return super.toString();
	}
	
	private void Wai() {
		// TODO Auto-generated method stub

	}
	
	@Override
	public boolean equals(Object obj) {
		// TODO Auto-generated method stub
		return super.equals(obj);
	}
}
