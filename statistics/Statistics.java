package hus.fp.statistics;

public class Statistics {
    private MyLinkedList data;

    /**
     * Khởi tạo dữ liệu cho BasicStatistic.
     */
    public Statistics(MyLinkedList data) {
        this.data = data;
    }

    /**
     * Hàm trả về giá trị lớn nhất trong list.
     * @return giá trị lớn nhất.
     */
    public double max() {
        double max = data.get(0);
        for (int i = 1; i < data.size(); i++) {
            if (data.get(i) > max) {
                max = data.get(i);
            }
        }
        return max;
    }

    /**
     * Hàm trả về giá trị nhỏ nhất trong list.
     * @return giá trị nhỏ nhất.
     */
    public double min() {
        double min = data.get(0);
        for (int i = 1; i < data.size(); i++) {
            if (data.get(i) < min) {
                min = data.get(i);
            }
        }
        return min;
    }

    /**
     * Hàm trả về kỳ vọng của mẫu theo dữ liệu trong list.
     * @return kỳ vọng.
     */
    public double mean() {
        double sum = 0;
        for (int i = 0; i < data.size(); i++) {
            sum = sum + data.get(i);
        }
        return sum / data.size();
    }

    /**
     * Hàm trả về phương sai của mẫu theo dữ liệu trong list.
     * @return phương sai.
     */
    public double variance() {
        double mean = mean();
        double sum = 0;
        for (int i = 0; i < data.size(); i++) {
            double diff = data.get(i) - mean;
            sum = sum + diff * diff;
        }
        return sum / data.size();
    }

    /**
     * Tìm kiếm trong list có phẩn tử nào có giá trị bằng data không, sử dụng binarySearch trong list.
     * @return true nếu tìm thấy, false nếu không tìm thấy
     */
    public boolean search(double data) {
        MyLinkedList sortedList = this.data.sortIncreasingUsingInsertionSort();
        return sortedList.binarySearch(data);
    }

    /**
     * Hàm sắp xếp các giá trị trong list theo thứ tự tăng dần, sử dụng thuật toán sắp xếp trong MyLinkedList.
     * @return list mới được sắp xếp theo thuật toán insertion sort.
     */
    public MyLinkedList sort() {
        return data.sortIncreasingUsingInsertionSort();
    }

    /**
     * Tính rank của các phần tử trong list.
     * @return rank của các phần tử trong list
     */
    public double[] rank() {
        int n = data.size();
        MyLinkedList sortedList = data.sortIncreasingUsingInsertionSort();
        double[] ranks = new double[n];

        for (int i = 0; i < n; i++) {
            double value = data.get(i);

            // Tìm tất cả vị trí của value trong sortedList để tính rank trung bình
            int firstPos = -1;
            int lastPos = -1;
            for (int j = 0; j < n; j++) {
                if (sortedList.get(j) == value) {
                    if (firstPos == -1) {
                        firstPos = j;
                    }
                    lastPos = j;
                }
            }

            // Rank = trung bình cộng các vị trí (1-based)
            ranks[i] = (double) (firstPos + lastPos + 2) / 2;
        }

        return ranks;
    }

    /**
     * Hàm định dạng list.
     * @return xâu ký tự biểu diễn list theo định dạng [a1, a2, ..., an]
     */
    public String toString() {
        if (data.size() == 0) {
            return "[]";
        }
        String result = "[";
        for (int i = 0; i < data.size(); i++) {
            double value = data.get(i);
            if (value == (long) value) {
                result = result + (long) value;
            } else {
                result = result + value;
            }
            if (i < data.size() - 1) {
                result = result + ", ";
            }
        }
        result = result + "]";
        return result;
    }
}