package lib.helper;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

public class Steg {
	public static byte [] transformFileToByteArray(File baseImage, byte[] output) throws IOException {
		ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
		outputStream.write(output);
		outputStream.write(Files.readAllBytes(baseImage.toPath()));
		
		return outputStream.toByteArray();
	}
	
	public static int getNecessaryCountBytes(String imageName, byte[] inputImage, int difference) throws IOException {
		byte [] fileBytes = getBaseImageBytes(imageName);
		int fileContent = difference + fileBytes.length;
		return inputImage.length - fileContent;
	}
	
	public static byte[] getBaseImageBytes(String imageName) throws IOException {
		File baseImage = new File("src/main/java/assets/"+imageName);
		return Files.readAllBytes(baseImage.toPath());
	}
}
