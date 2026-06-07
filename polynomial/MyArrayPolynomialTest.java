package hus.fp.polynomial;

import java.util.Random;

/*
 * Đa thức với các hệ số được lưu trong mảng coefficents. Hệ số tự do bắt đầu từ chỉ số 0 của mảng.
 */
public class MyArrayPolynomialTest {
    public static void main(String[] args) {
        /*
        TODO:
          - Sinh ra một hai số nguyên ngẫu nhiên, lưu các giá trị sinh ra vào biến polyLength1 và polyLength2.
          - Tương ứng với các giá trị của polyLength1 và polyLength2, sinh ra các số thực kiểu double, và tạo hai đa thức với đã bộ số đã nhận được.
          - Thực hiện các chức năng của đa thức và in ra màn hình theo định dạng sau:
              Poly1: xxx (in ra đa thức theo dạng của hàm toString())
              Poly2: xxx

              Poly1 Derivative: xxx
              Poly2 Derivative: xxx

              Poly1 + Poly2: xxx
              Poly1 - Poly2: xxx
              Poly1 x Poly2: xxx

              Modify Poly1 at index xxx with the value xxx: xxx
              Modify Poly2 at index xxx with the value xxx: xxx

        */

        Random random = new Random();

        // Sinh ra hai số nguyên ngẫu nhiên làm độ dài (bậc + 1) của đa thức, từ 2 đến 6
        int polyLength1 = random.nextInt(5) + 2;
        int polyLength2 = random.nextInt(5) + 2;

        // Tạo đa thức 1
        MyArrayPolynomial poly1 = new MyArrayPolynomial();
        for (int i = 0; i < polyLength1; i++) {
            double coeff = random.nextInt(19) - 9; // hệ số từ -9 đến 9
            poly1.append(coeff);
        }

        // Tạo đa thức 2
        MyArrayPolynomial poly2 = new MyArrayPolynomial();
        for (int i = 0; i < polyLength2; i++) {
            double coeff = random.nextInt(19) - 9; // hệ số từ -9 đến 9
            poly2.append(coeff);
        }

        // In ra hai đa thức
        System.out.println("Poly1: " + poly1.toString());
        System.out.println("Poly2: " + poly2.toString());

        System.out.println();

        // In ra đạo hàm của hai đa thức
        System.out.println("Poly1 Derivative: " + poly1.derivative().toString());
        System.out.println("Poly2 Derivative: " + poly2.derivative().toString());

        System.out.println();

        // In ra tổng, hiệu, tích của hai đa thức
        System.out.println("Poly1 + Poly2: " + poly1.plus(poly2).toString());
        System.out.println("Poly1 - Poly2: " + poly1.minus(poly2).toString());
        System.out.println("Poly1 x Poly2: " + poly1.multiply(poly2).toString());

        System.out.println();

        // Chỉnh sửa hệ số tại một vị trí ngẫu nhiên của đa thức 1
        int modIndex1 = random.nextInt(polyLength1);
        double modValue1 = random.nextInt(19) - 9;
        poly1.set(modValue1, modIndex1);
        System.out.println("Modify Poly1 at index " + modIndex1 + " with the value " + (int) modValue1 + ": " + poly1.toString());

        // Chỉnh sửa hệ số tại một vị trí ngẫu nhiên của đa thức 2
        int modIndex2 = random.nextInt(polyLength2);
        double modValue2 = random.nextInt(19) - 9;
        poly2.set(modValue2, modIndex2);
        System.out.println("Modify Poly2 at index " + modIndex2 + " with the value " + (int) modValue2 + ": " + poly2.toString());
    }
}