import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.Normalizer;
import java.util.Locale;

public class EnglishInputStream extends FilterInputStream {

	public EnglishInputStream(InputStream in) {
		super(in);
	}

	public int read() throws IOException {
		 int c = super.read();
		 
		/*
		 * Locale enLocale = Locale.forLanguageTag("en_US");
		 * 
		 * String temp = (c + "").toLowerCase(enLocale);
		 * 
		 * return (int)temp.charAt(0);
		 */
		 
		 return c;
		 }

	public int read(byte[] b, int offset, int len) throws IOException {
		int result = super.read(b, offset, len);
		for (int i = offset; i < offset+result; i++) {
			b[i] = (byte)Character.toLowerCase((char)b[i]);
		}
		return result;
	}
	
	
	
}
