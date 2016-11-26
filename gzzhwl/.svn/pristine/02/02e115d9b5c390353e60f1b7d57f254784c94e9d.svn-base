package com.gzzhwl.core.utils;

public final class Base64 {
	private static final byte[] base64Alphabet = new byte[128];
	private static final char[] lookUpBase64Alphabet = new char[64];

	private Base64() {
	}

	private static boolean isWhiteSpace(char octect) {
		return octect == 32 || octect == 13 || octect == 10 || octect == 9;
	}

	private static boolean isPad(char octect) {
		return octect == 61;
	}

	private static boolean isData(char octect) {
		return octect < 128 && base64Alphabet[octect] != -1;
	}

	public static String encode(byte[] binaryData) {
		if (binaryData == null) {
			return null;
		} else {
			int lengthDataBits = binaryData.length * 8;
			if (lengthDataBits == 0) {
				return "";
			} else {
				int fewerThan24bits = lengthDataBits % 24;
				int numberTriplets = lengthDataBits / 24;
				int numberQuartet = fewerThan24bits != 0 ? numberTriplets + 1 : numberTriplets;
				char[] var17 = new char[numberQuartet * 4];
				int encodedIndex = 0;
				int dataIndex = 0;

				byte val2;
				byte var18;
				byte var19;
				byte var20;
				byte var21;
				for (int val1 = 0; val1 < numberTriplets; ++val1) {
					var20 = binaryData[dataIndex++];
					var21 = binaryData[dataIndex++];
					byte var22 = binaryData[dataIndex++];
					var19 = (byte) (var21 & 15);
					var18 = (byte) (var20 & 3);
					val2 = (var20 & -128) == 0 ? (byte) (var20 >> 2) : (byte) (var20 >> 2 ^ 192);
					byte val21 = (var21 & -128) == 0 ? (byte) (var21 >> 4) : (byte) (var21 >> 4 ^ 240);
					byte val3 = (var22 & -128) == 0 ? (byte) (var22 >> 6) : (byte) (var22 >> 6 ^ 252);
					var17[encodedIndex++] = lookUpBase64Alphabet[val2];
					var17[encodedIndex++] = lookUpBase64Alphabet[val21 | var18 << 4];
					var17[encodedIndex++] = lookUpBase64Alphabet[var19 << 2 | val3];
					var17[encodedIndex++] = lookUpBase64Alphabet[var22 & 63];
				}

				byte var23;
				if (fewerThan24bits == 8) {
					var20 = binaryData[dataIndex];
					var18 = (byte) (var20 & 3);
					var23 = (var20 & -128) == 0 ? (byte) (var20 >> 2) : (byte) (var20 >> 2 ^ 192);
					var17[encodedIndex++] = lookUpBase64Alphabet[var23];
					var17[encodedIndex++] = lookUpBase64Alphabet[var18 << 4];
					var17[encodedIndex++] = 61;
					var17[encodedIndex++] = 61;
				} else if (fewerThan24bits == 16) {
					var20 = binaryData[dataIndex];
					var21 = binaryData[dataIndex + 1];
					var19 = (byte) (var21 & 15);
					var18 = (byte) (var20 & 3);
					var23 = (var20 & -128) == 0 ? (byte) (var20 >> 2) : (byte) (var20 >> 2 ^ 192);
					val2 = (var21 & -128) == 0 ? (byte) (var21 >> 4) : (byte) (var21 >> 4 ^ 240);
					var17[encodedIndex++] = lookUpBase64Alphabet[var23];
					var17[encodedIndex++] = lookUpBase64Alphabet[val2 | var18 << 4];
					var17[encodedIndex++] = lookUpBase64Alphabet[var19 << 2];
					var17[encodedIndex++] = 61;
				}

				return new String(var17);
			}
		}
	}

	public static byte[] decode(String encoded) {
		if (encoded == null) {
			return null;
		} else {
			char[] base64Data = encoded.toCharArray();
			int len = removeWhiteSpace(base64Data);
			if (len % 4 != 0) {
				return null;
			} else {
				int numberQuadruple = len / 4;
				if (numberQuadruple == 0) {
					return new byte[0];
				} else {
					int i = 0;
					int encodedIndex = 0;
					int dataIndex = 0;
					byte[] var17;
					byte var18;
					byte var19;
					byte var20;
					byte var21;
					char var22;
					char var23;
					char var24;
					char var25;
					for (var17 = new byte[numberQuadruple * 3]; i < numberQuadruple - 1; ++i) {
						if (!isData(var22 = base64Data[dataIndex++]) || !isData(var23 = base64Data[dataIndex++])
								|| !isData(var24 = base64Data[dataIndex++])
								|| !isData(var25 = base64Data[dataIndex++])) {
							return null;
						}

						var18 = base64Alphabet[var22];
						var19 = base64Alphabet[var23];
						var20 = base64Alphabet[var24];
						var21 = base64Alphabet[var25];
						var17[encodedIndex++] = (byte) (var18 << 2 | var19 >> 4);
						var17[encodedIndex++] = (byte) ((var19 & 15) << 4 | var20 >> 2 & 15);
						var17[encodedIndex++] = (byte) (var20 << 6 | var21);
					}

					if (isData(var22 = base64Data[dataIndex++]) && isData(var23 = base64Data[dataIndex++])) {
						var18 = base64Alphabet[var22];
						var19 = base64Alphabet[var23];
						var24 = base64Data[dataIndex++];
						var25 = base64Data[dataIndex++];
						if (isData(var24) && isData(var25)) {
							var20 = base64Alphabet[var24];
							var21 = base64Alphabet[var25];
							var17[encodedIndex++] = (byte) (var18 << 2 | var19 >> 4);
							var17[encodedIndex++] = (byte) ((var19 & 15) << 4 | var20 >> 2 & 15);
							var17[encodedIndex++] = (byte) (var20 << 6 | var21);
							return var17;
						} else {
							byte[] tmp;
							if (isPad(var24) && isPad(var25)) {
								if ((var19 & 15) != 0) {
									return null;
								} else {
									tmp = new byte[i * 3 + 1];
									System.arraycopy(var17, 0, tmp, 0, i * 3);
									tmp[encodedIndex] = (byte) (var18 << 2 | var19 >> 4);
									return tmp;
								}
							} else if (!isPad(var24) && isPad(var25)) {
								var20 = base64Alphabet[var24];
								if ((var20 & 3) != 0) {
									return null;
								} else {
									tmp = new byte[i * 3 + 2];
									System.arraycopy(var17, 0, tmp, 0, i * 3);
									tmp[encodedIndex++] = (byte) (var18 << 2 | var19 >> 4);
									tmp[encodedIndex] = (byte) ((var19 & 15) << 4 | var20 >> 2 & 15);
									return tmp;
								}
							} else {
								return null;
							}
						}
					} else {
						return null;
					}
				}
			}
		}
	}

	private static int removeWhiteSpace(char[] data) {
		if (data == null) {
			return 0;
		} else {
			int newSize = 0;
			int len = data.length;
			for (int i = 0; i < len; ++i) {
				if (!isWhiteSpace(data[i])) {
					data[newSize++] = data[i];
				}
			}
			return newSize;
		}
	}

	static {
		int i;
		for (i = 0; i < 128; ++i) {
			base64Alphabet[i] = -1;
		}
		for (i = 90; i >= 65; --i) {
			base64Alphabet[i] = (byte) (i - 65);
		}
		for (i = 122; i >= 97; --i) {
			base64Alphabet[i] = (byte) (i - 97 + 26);
		}
		for (i = 57; i >= 48; --i) {
			base64Alphabet[i] = (byte) (i - 48 + 52);
		}
		base64Alphabet[43] = 62;
		base64Alphabet[47] = 63;
		for (i = 0; i <= 25; ++i) {
			lookUpBase64Alphabet[i] = (char) (65 + i);
		}
		i = 26;
		int j;
		for (j = 0; i <= 51; ++j) {
			lookUpBase64Alphabet[i] = (char) (97 + j);
			++i;
		}
		i = 52;
		for (j = 0; i <= 61; ++j) {
			lookUpBase64Alphabet[i] = (char) (48 + j);
			++i;
		}
		lookUpBase64Alphabet[62] = 43;
		lookUpBase64Alphabet[63] = 47;
	}
}
