package files;

public class Payload {

	public static String getPayload(String fname,String lname) {
		String payload= "{\r\n"
				+ "    \"name\": \""+fname+"\",\r\n"
				+ "    \"job\": \"zion resident\"\r\n"
				+ "}";
		return payload;

	}
	

}
