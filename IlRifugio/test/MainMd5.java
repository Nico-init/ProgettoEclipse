import org.junit.Test;

import static org.junit.Assert.assertTrue;

import org.apache.commons.codec.digest.DigestUtils;

public class MainMd5 {

	public static void main(String[] args) {
		

	}

	@Test
	public void givenPassword_whenHashingUsingCommons_thenVerifying()  {
	    String hash = "2943299556db3aafd672a679cae153aa";
	    String password = "niccolo";
	 
	    String md5Hex = DigestUtils.md5Hex(password);
	    
	    System.out.println(md5Hex);
	    assertTrue(md5Hex.equals(hash));
	}
}
