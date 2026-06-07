package hus.fp.rle;

import java.util.Random;

public class RunlengthEncoding {
    public static void main(String[] args) {
        /*
        TODO:
        - Sinh một số nguyên ngẫu nhiên trong đoạn [30, 50], lưu giá trị vào biến length.
        - Hoàn thiện các hàm bên dưới để sinh ngẫu một xâu ký tự. Thực hiện mã hóa run-length xâu ký tự được sinh ra và giải mã kiểm tra lại.
        In ra màn hình theo định dạng:
        Generated String: xxxxx 

        Decoded Text: xxxxx
        Encoded Text: xxxxx

        Encoded Text: xxxxx
        Decoded Text: xxxxx
        */
        Random random = new Random();

        // Sinh một số nguyên ngẫu nhiên trong đoạn [30, 50]
        int length = random.nextInt(21) + 30;

        // Sinh ngẫu nhiên một xâu ký tự có độ dài length
        String generatedString = randomLowerCaseString(length);
        System.out.println("Generated String: " + generatedString);
        System.out.println();

        // Test mã hóa
        testEncoding();
        System.out.println();

        // Test giải mã
        testDecoding();
    }

    /*
     * Hàm sinh ngẫu nhiên một xâu ký tự có độ dài length.
     * Sinh ra length số nguyên nằm trong khoảng [97, 122], chuyển những số nguyên này sang 
       ký tự chữ cái thường để có một xâu ký tự (String) được sinh ngẫy nhiên.
     */
    public static String randomLowerCaseString(int length) {
        Random random = new Random();
        String result = "";
        for (int i = 0; i < length; i++) {
            int code = random.nextInt(26) + 97;
            result = result + (char) code;
        }
        return result;
    }

    /*
     * Hàm mã hóa chuỗi ký tự text theo mã hóa run-length.
     */
    public static String encoding(String text) {
        if (text == null || text.length() == 0) {
            return "";
        }

        String encoded = "";
        int i = 0;

        while (i < text.length()) {
            char currentChar = text.charAt(i);
            int count = 1;

            while (i + count < text.length() && text.charAt(i + count) == currentChar) {
                count++;
            }

            encoded = encoded + count + currentChar;
            i = i + count;
        }

        return encoded;
    }

    /*
     * Hàm giải mã chuỗi ký tự text theo mã hóa run-length.
     */
    public static String decoding(String text) {
        if (text == null || text.length() == 0) {
            return "";
        }

        String decoded = "";
        int i = 0;

        while (i < text.length()) {
            // Đọc phần số (có thể nhiều chữ số, ví dụ: 12a)
            String numberStr = "";
            while (i < text.length() && Character.isDigit(text.charAt(i))) {
                numberStr = numberStr + text.charAt(i);
                i++;
            }

            // Đọc ký tự tiếp theo
            char currentChar = text.charAt(i);
            i++;

            int count = Integer.parseInt(numberStr);
            for (int j = 0; j < count; j++) {
                decoded = decoded + currentChar;
            }
        }

        return decoded;
    }

    /*
     * Hàm test mã hóa theo mã hóa run-length.
     */
    public static void testEncoding() {
        Random random = new Random();
        int length = random.nextInt(21) + 30;
        String original = randomLowerCaseString(length);
        String encoded = encoding(original);

        System.out.println("Decoded Text: " + original);
        System.out.println("Encoded Text: " + encoded);
    }

    /*
     * Hàm test giải mã theo mã hóa run-length.
     */
    public static void testDecoding() {
        Random random = new Random();
        int length = random.nextInt(21) + 30;
        String original = randomLowerCaseString(length);
        String encoded = encoding(original);
        String decoded = decoding(encoded);

        System.out.println("Encoded Text: " + encoded);
        System.out.println("Decoded Text: " + decoded);
    }
}