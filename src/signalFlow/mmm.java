package signalFlow;

public class mmm {

	public static void main(String[] args) {
		
		try {
			
			throw new Exception();
		}catch(Exception e) {
			e.printStackTrace();
			}
		System.out.println("1");
		
	try {
			
			throw new Exception();
		}catch(Exception e) {
			System.out.println("2");
		}
	
	}

}
