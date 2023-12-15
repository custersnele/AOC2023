package be.ccfun.day15;

public class Hasher {

	public static int calculateHash(String data) {
		//Determine the ASCII code for the current character of the string.
		//Increase the current value by the ASCII code you just determined.
		//Set the current value to itself multiplied by 17.
		//Set the current value to the remainder of dividing itself by 256.
		int hash = 0;
		for (int i = 0; i < data.length(); i++) {
			int ascii = data.charAt(i);
			hash += ascii;
			hash *= 17;
			hash %= 256;
		}
		return hash;
	}

}
