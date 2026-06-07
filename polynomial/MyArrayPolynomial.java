package hus.fp.polynomial;

/*
 * Tạo một đa thức với các hệ số được lưu trong biến coefficents. Hệ số tự do bắt đầu từ chỉ số 0.
 */
public class MyArrayPolynomial {
    private static final int DEFAULT_CAPACITY = 8;
    private double[] coefficents;
    private int length;

    /*
     * Khởi tạo dữ liệu mặc định.
     */
    public MyArrayPolynomial() {
        this.coefficents = new double[DEFAULT_CAPACITY];
        this.length = 0;
    }

    /*
     * Hàm trả về hệ số của đa thức ở vị trí index.
     */
    public double coefficient(int index) {
        if (index < 0 || index >= length) {
            throw new IndexOutOfBoundsException("Index out of bounds: " + index);
        }
        return coefficents[index];
    }

    /*
     * Hàm trả về các hệ số của đa thức.
     */
    public double[] coefficients() {
        double[] result = new double[length];
        for (int i = 0; i < length; i++) {
            result[i] = coefficents[i];
        }
        return result;
    }

    /*
     * Hàm thêm vào một giá trị ở cuối mảng coefficients để nhận được một đa thức có bậc lớn hơn.
     */
    public void append(double coefficient) {
        if (length >= coefficents.length) {
            allocateMore();
        }
        coefficents[length] = coefficient;
        length++;
    }

    /*
     * Hàm thêm vào một giá trị ở vị trí index của mảng coefficients để nhận được một đa thức có bậc lớn hơn.
     */
    public void insert(double coefficient, int index) {
        if (index < 0 || index > length) {
            throw new IndexOutOfBoundsException("Index out of bounds: " + index);
        }
        if (length >= coefficents.length) {
            allocateMore();
        }
        for (int i = length; i > index; i--) {
            coefficents[i] = coefficents[i - 1];
        }
        coefficents[index] = coefficient;
        length++;
    }

    /*
     * Hàm xóa giá trị ở vị trí index của mảng coefficients để nhận được một đa thức có bậc nhỏ hơn.
     */
    public void remove(int index) {
        if (index < 0 || index >= length) {
            throw new IndexOutOfBoundsException("Index out of bounds: " + index);
        }
        for (int i = index; i < length - 1; i++) {
            coefficents[i] = coefficents[i + 1];
        }
        coefficents[length - 1] = 0;
        length--;
    }

    /*
     * Hàm thay đổi giá trị ở vị trí index của mảng coefficients để nhận được một đa thức khác.
     */
    public void set(double coefficient, int index) {
        if (index < 0 || index >= length) {
            throw new IndexOutOfBoundsException("Index out of bounds: " + index);
        }
        coefficents[index] = coefficient;
    }

    /*
     * Hàm trả về bậc của đa thức.
     */
    public int degree() {
        for (int i = length - 1; i >= 1; i--) {
            if (coefficents[i] != 0) {
                return i;
            }
        }
        return 0;
    }

    /*
     * Hàm tính giá trị của đa thức khi biết giá trị của biến độc lập x.
     */
    public double evaluate(double x) {
        double result = 0;
        double xPow = 1;
        for (int i = 0; i < length; i++) {
            result = result + coefficents[i] * xPow;
            xPow = xPow * x;
        }
        return result;
    }

    /*
     * Hàm lấy đạo hàm của đa thức, trả về đa thức là đạo hàm của đa thức hiện tại.
     */
    public MyArrayPolynomial derivative() {
        MyArrayPolynomial result = new MyArrayPolynomial();
        if (length <= 1) {
            result.append(0);
            return result;
        }
        for (int i = 1; i < length; i++) {
            result.append(i * coefficents[i]);
        }
        return result;
    }

    /*
     * Hàm cộng đa thức hiện tại với một đa thức khác, trả về đa thức là tổng của hai đa thức.
     */
    public MyArrayPolynomial plus(MyArrayPolynomial right) {
        MyArrayPolynomial result = new MyArrayPolynomial();
        int maxLen;
        if (this.length > right.length) {
            maxLen = this.length;
        } else {
            maxLen = right.length;
        }
        for (int i = 0; i < maxLen; i++) {
            double leftCoeff = 0;
            double rightCoeff = 0;
            if (i < this.length) {
                leftCoeff = this.coefficents[i];
            }
            if (i < right.length) {
                rightCoeff = right.coefficents[i];
            }
            result.append(leftCoeff + rightCoeff);
        }
        return result;
    }

    /*
     * Hàm trừ đa thức hiện tại cho một đa thức khác, trả về đa thức là hiệu của đa thức hiện tại trừ đi một đa thức khác đã cho.
     */
    public MyArrayPolynomial minus(MyArrayPolynomial right) {
        MyArrayPolynomial result = new MyArrayPolynomial();
        int maxLen;
        if (this.length > right.length) {
            maxLen = this.length;
        } else {
            maxLen = right.length;
        }
        for (int i = 0; i < maxLen; i++) {
            double leftCoeff = 0;
            double rightCoeff = 0;
            if (i < this.length) {
                leftCoeff = this.coefficents[i];
            }
            if (i < right.length) {
                rightCoeff = right.coefficents[i];
            }
            result.append(leftCoeff - rightCoeff);
        }
        return result;
    }

    /*
     * Hàm nhân đa thức hiện tại với một đa thức khác, trả về đa thức là tích của hai đa thức.
     */
    public MyArrayPolynomial multiply(MyArrayPolynomial right) {
        MyArrayPolynomial result = new MyArrayPolynomial();
        int resultLen = this.length + right.length - 1;
        for (int i = 0; i < resultLen; i++) {
            result.append(0);
        }
        for (int i = 0; i < this.length; i++) {
            for (int j = 0; j < right.length; j++) {
                result.coefficents[i + j] = result.coefficents[i + j] + this.coefficents[i] * right.coefficents[j];
            }
        }
        return result;
    }

    /*
     * Hàm cho phép tăng kích thước mảng lên gấp đôi để lưu các hệ số của đa thức, nếu mảng ban đầu đã sử dụng hết.
     */
    private void allocateMore() {
        double[] newArray = new double[coefficents.length * 2];
        for (int i = 0; i < length; i++) {
            newArray[i] = coefficents[i];
        }
        coefficents = newArray;
    }

    /*
     * Hàm định dạng đa thức theo dạng: [a_nx^n + ... + a1x + a0]
     */
    public String toString() {
        if (length == 0) {
            return "[]";
        }

        String str = "[";
        boolean firstTerm = true;

        for (int i = length - 1; i >= 0; i--) {
            double coeff = coefficents[i];
            if (coeff == 0) {
                continue;
            }

            if (firstTerm == false) {
                if (coeff < 0) {
                    str = str + " - ";
                } else {
                    str = str + " + ";
                }
            } else {
                if (coeff < 0) {
                    str = str + "-";
                }
            }

            double absCoeff = coeff;
            if (absCoeff < 0) {
                absCoeff = -absCoeff;
            }

            if (i == 0) {
                if (absCoeff == (long) absCoeff) {
                    str = str + (long) absCoeff;
                } else {
                    str = str + absCoeff;
                }
            } else {
                if (absCoeff != 1.0) {
                    if (absCoeff == (long) absCoeff) {
                        str = str + (long) absCoeff;
                    } else {
                        str = str + absCoeff;
                    }
                }
                if (i == 1) {
                    str = str + "x";
                } else {
                    str = str + "x^" + i;
                }
            }

            firstTerm = false;
        }

        if (firstTerm == true) {
            str = str + "0";
        }

        str = str + "]";
        return str;
    }
}