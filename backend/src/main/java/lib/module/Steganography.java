package lib.module;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

import lib.helper.Normalize;

public class Steganography {
	public byte[] encrypt(int[] numberList, byte[] imageInBytes) throws IOException {
        byte [] numberInBytes = Normalize.integersToBytes(numberList);
        
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
		outputStream.write(numberInBytes);
		outputStream.write(imageInBytes);
		
		return outputStream.toByteArray();
	}
	
	public byte[] encrypt(long[] numberList, byte[] imageInBytes) throws IOException {
        byte [] numberInBytes = Normalize.longToBytes(numberList);
        
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
		outputStream.write(numberInBytes);
		outputStream.write(imageInBytes);
		
		return outputStream.toByteArray();
	}
	
	public byte[] encrypt(double[] numberList, byte[] imageInBytes) throws IOException {
        byte [] numberInBytes = Normalize.doubleToBytes(numberList);
        
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
		outputStream.write(numberInBytes);
		outputStream.write(imageInBytes);
		
		return outputStream.toByteArray();
	}
	
	public byte[] encrypt(String stringValue, byte[] imageInBytes) throws IOException {
        byte [] stringInBytes = stringValue.getBytes();
        
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
		outputStream.write(stringInBytes);
		outputStream.write(imageInBytes);
		
		return outputStream.toByteArray();
	}
	
	public int[] decryptInt(byte[] stegoBytes, int necessaryDataAmount) throws IOException {
		byte [] dataBytes = Normalize.getByteArrayToLength(stegoBytes, necessaryDataAmount);
		int [] plainIntValues = Normalize.byteToInteger(dataBytes); 
		return plainIntValues;
	}
	
	public long[] decryptLong(byte[] stegoBytes, int necessaryDataAmount) throws IOException {
		byte [] dataBytes = Normalize.getByteArrayToLength(stegoBytes, necessaryDataAmount);
		long [] plainLongValues = Normalize.byteToLong(dataBytes);
		return plainLongValues;
	}
	
	public double[] decryptDouble(byte[] stegoBytes, int necessaryDataAmount) throws IOException {
		byte [] dataBytes = Normalize.getByteArrayToLength(stegoBytes, necessaryDataAmount);
		double [] plainDoubleValues = Normalize.byteToDouble(dataBytes);
		return plainDoubleValues;
	}
	
	public String decryptString(byte[] stegoBytes, int necessaryDataAmount) throws IOException {
		byte [] dataBytes = Normalize.getByteArrayToLength(stegoBytes, necessaryDataAmount);
		String plainStringValue = new String(dataBytes);
		return plainStringValue;
	}
}
