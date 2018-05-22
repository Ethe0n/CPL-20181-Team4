package cdp2.mindle.manager;

import java.nio.charset.Charset;

import javax.print.DocFlavor.STRING;

public class SmartBuffer {
	private byte[] buffer;
	private String bits;
	public int bitOffset;
	private final Charset UTF8_CHARSET = Charset.forName("UTF-8");
	private final static int divisor = 0xFF;

	public SmartBuffer(byte[] buffer) {
		this.buffer = buffer;
		bitOffset = 0;
		
		bits = byteArrayToBinaryString(buffer);
	}

	public int getBitOffset() {
		return this.bitOffset >> 3;
	}

	public int getSum() {
		int sum = 0;
		
		for (byte iter : buffer) {
			sum += iter;
		}
		return sum % divisor;
	}
	
	private int read(int bitLength) {
		int[] range = new int[] { bitOffset, bitOffset + bitLength };
		int R = 0;

		for (int i = range[0]; i < range[1]; i++) {
			R <<= 1;
			R |= bits.charAt(i) == '1' ? 1 : 0;
		}

		move(bitLength);
		return R;
	}

	public int readSignedInteger(int bitLength) {
		int value = this.readUnsignedInteger(bitLength);

		if ((value & (1 << bitLength - 1)) != 0) {
			value = -(~value + 1);
		}
		return value;
	}
	
	public int readUnsignedInteger(int bitLength){
		return read(bitLength);
	}
	
	public String readString(int bitLength) {
		int byteLength = bitLength >> 3;
		byte[] bytes = new byte[byteLength];

		for (int i = 0; i < byteLength; i++) {
			bytes[i] = (byte) (readUnsignedInteger(8) & 0xFF);
		}

		return decodeUTF8(bytes);
	}
	  
	public String readDynamicString(int bitLengthLength) {
		int bitLength = 8 * this.readUnsignedInteger(bitLengthLength);

		return this.readString(bitLength);
	}
	
	private int move(int bitOffset) {
		return this.bitOffset += bitOffset;
	}

	private String decodeUTF8(byte[] bytes) {
		return new String(bytes, UTF8_CHARSET);
	}

	@SuppressWarnings("unused")
	private byte[] encodeUTF8(String string) {
		return string.getBytes(UTF8_CHARSET);
	}

	private String byteArrayToBinaryString(byte[] b) {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < b.length; ++i) {
			sb.append(byteToBinaryString(b[i]));
		}
		return sb.toString();
	}

	private String byteToBinaryString(byte n) {
		StringBuilder sb = new StringBuilder("00000000");
		for (int bit = 0; bit < 8; bit++) {
			if (((n >> bit) & 1) > 0) {
				sb.setCharAt(7 - bit, '1');
			}
		}
		return sb.toString();
	}
	
	public static String intToBinaryArray(int value, int bitSize)
	{
		String bits = "";
		
		for (int i = 0; i < bitSize; ++i) {
			bits += (value >> i) & 1;
		}
		
		return bits;
	}
	
	public static String strToBinaryArray(String s)
	{
		byte[] sBytes = s.getBytes();
		int sLength = sBytes.length;
		String bits = "";
		
		for (int i = 0; i < sLength * 8; ++i) {
			bits += (sBytes[i / 8] >> i) & 1;
		}
		
		return bits;
	}
	
	public static String variableStrToBinaryArray(String s, int bitSize) 
	{
		byte[] sBytes = s.getBytes();
		int sLength = sBytes.length;
		String bits = "";
		
		for (int i = 0; i < bitSize; ++i) {
			bits += (sLength >> i) & 1;
		}
		
		return bits + strToBinaryArray(s);
	}
	
	public static byte[] binaryStringToByteArray(String s) {
		int count = s.length() / 8;
		byte[] b = new byte[count];
		for (int i = 1; i < count; ++i) {
			String t = s.substring((i - 1) * 8, i * 8);
			b[i - 1] = binaryStringToByte(t);
		}
		return b;
	}

	private static byte binaryStringToByte(String s) {
		byte ret = 0, total = 0;
		for (int i = 0; i < 8; ++i) {
			ret = (s.charAt(7 - i) == '1') ? (byte) (1 << i) : 0;
			total = (byte) (ret | total);
		}
		return total;
	}
}
