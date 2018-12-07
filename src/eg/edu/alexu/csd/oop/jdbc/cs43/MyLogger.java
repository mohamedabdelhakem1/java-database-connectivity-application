package eg.edu.alexu.csd.oop.jdbc.cs43;

import java.util.logging.Level;
import java.util.logging.Logger;

public class MyLogger {
	private static final Logger LOGGER = Logger.getLogger(MyLogger.class.getName());

	public static void main(String[] args) {
		LOGGER.info("Logger Name: " + LOGGER.getName());

		LOGGER.warning("Can cause ArrayIndexOutOfBoundsException");

		// An array of size 3

		int[] a = { 1, 2, 3 };

		int index = 4;

		LOGGER.config("index is set to " + index);

		try {

			System.out.println(a[index]);

		} catch (ArrayIndexOutOfBoundsException ex) {

			LOGGER.log(Level.SEVERE, "Exception occur", ex);

		}

	}
}
