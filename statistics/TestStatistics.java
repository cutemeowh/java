package hus.fp.statistics;

import java.util.Random;

public class TestStatistics {
    private Statistics statistics;

    public TestStatistics(Statistics statistics) {
        this.statistics = statistics;
    }

    public static void main(String[] args) {
        /* TODO
           - Thực hiện hàm test, lưu kết quả chạy chương trình và file text được đặt tên
             là <Statistics_MaSinhVien>.txt (Ví dụ, Statistics_123456.txt).
           - Nén các file source code và file text kết quả chạy chương trình vào file zip có tên
             <Statistics_MaSinhVien>.zip (Ví dụ, Statistics_123456.zip), nộp lên classroom.
         */
        MyLinkedList list = new MyLinkedList();
        Statistics statistics = new Statistics(list);
        TestStatistics test = new TestStatistics(statistics);
        test.testMyLinkedList();
    }

    public void testMyLinkedList() {
        /* TODO
           - Sinh ngẫu nhiên một số tự nhiên nằm trong đoạn [30, 50], lưu vào biến length.
           - Tạo một list kiểu MyLinkedList, có các phần tử lưu dữ liệu kiểu double được sinh ngẫu nhiên
             nằm trong đoạn [1, 20]. Tạo Statistics có dữ liệu là list dữ liệu vừa tạo, lưu vào statistics.
           - Sử dụng Statistics để tính các đại lượng thống kê cơ bản (max, min, kỳ vọng, phương sai, rank, sắp xếp, tìm kiếm).
             In ra màn hình tập dữ liệu, tập dữ liệu được sắp xếp, các đại lượng thống kê và kết quả tìm kiếm.
         */
        Random random = new Random();

        // Sinh ngẫu nhiên độ dài list trong đoạn [30, 50]
        int length = random.nextInt(21) + 30;

        // Tạo list và thêm các phần tử ngẫu nhiên trong đoạn [1, 20]
        MyLinkedList list = new MyLinkedList();
        for (int i = 0; i < length; i++) {
            double value = random.nextInt(20) + 1;
            list.append(value);
        }

        // Tạo Statistics với list vừa tạo
        statistics = new Statistics(list);

        // In ra tập dữ liệu gốc
        System.out.println("Data (" + length + " elements): " + statistics.toString());
        System.out.println();

        // In ra tập dữ liệu được sắp xếp
        MyLinkedList sortedList = statistics.sort();
        Statistics sortedStatistics = new Statistics(sortedList);
        System.out.println("Sorted Data: " + sortedStatistics.toString());
        System.out.println();

        // In ra các đại lượng thống kê
        System.out.println("Max: " + statistics.max());
        System.out.println("Min: " + statistics.min());
        System.out.println("Mean: " + statistics.mean());
        System.out.println("Variance: " + statistics.variance());
        System.out.println();

        // In ra rank của các phần tử
        double[] ranks = statistics.rank();
        System.out.print("Rank: [");
        for (int i = 0; i < ranks.length; i++) {
            if (ranks[i] == (long) ranks[i]) {
                System.out.print((long) ranks[i]);
            } else {
                System.out.print(ranks[i]);
            }
            if (i < ranks.length - 1) {
                System.out.print(", ");
            }
        }
        System.out.println("]");
        System.out.println();

        // Tìm kiếm một số ngẫu nhiên trong list
        double searchValue = random.nextInt(20) + 1;
        boolean found = statistics.search(searchValue);
        System.out.println("Search " + (int) searchValue + ": " + found);
    }
}