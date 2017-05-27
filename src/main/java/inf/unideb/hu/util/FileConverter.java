package inf.unideb.hu.util;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.util.logging.Logger;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * Class for file conversion. Convert the file to byte array and back.
 * @author mark
 */
public class FileConverter {

	/**
	 * Width of the ImageView displayed on the RecipeinfoView.
	 */
	private static final int WIDTH = 70;
	
	/**
	 * Height of the ImageView displayed on the RecipeinfoView.
	 */
	private static final int HEIGTH = 70;
	
	/**
	 * Logger of the FileConverter class.
	 */
	private static final Logger LOGGER = Logger.getLogger(FileConverter.class.getName());
	
	/**
	 * Converts the file to byte array.
	 * @param file the file that is converted to byte array
	 * @return the file in byte array
	 */
	public byte[] FileToArray(File file) {
		byte[] byteFile = new byte[(int) file.length()];
		try {
			FileInputStream fileInputStream = new FileInputStream(file);
			fileInputStream.read(byteFile);
			fileInputStream.close();
			} catch (Exception e) {
				LOGGER.severe("Error during executing FileToArray");
				LOGGER.severe(e.getMessage());
		}
		return byteFile;
	}

	/**
	 * Converts the byte array to image file.
	 * @param array the byte array that is converted to ImageView
	 * @return the byte array converted to ImageView
	 */
	public ImageView ArrayToFile(byte[] array) {
		ByteArrayInputStream inputstream = new ByteArrayInputStream(array);
	    Image image = new Image(inputstream);
	    ImageView iv = new ImageView(image);
	    iv = resize(iv);
		return iv;
	}

	/**
	 * Resize the given ImageView.
	 * @param iv the ImageView which is resized
	 * @return the resized ImageView
	 */
	public ImageView resize(ImageView iv) {
		iv.setFitWidth(WIDTH);
		iv.setFitHeight(HEIGTH);
		return iv;
	}
}
