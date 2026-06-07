package hus.fp.statistics;

public class MyLinkedList {
    private MyNode head;

    /*
     * Khởi tạo dữ liệu mặc định.
     */
    public MyLinkedList() {
        this.head = null;
    }

    /*
     * Hàm trả về kịch thước của list.
     */
    public int size() {
        int count = 0;
        MyNode current = head;
        while (current != null) {
            count++;
            current = current.next;
        }
        return count;
    }

    /*
     * Hàm trả về giá trị tại vị trí index trong list.
     */
    public double get(int index) {
        if (index < 0 || index >= size()) {
            throw new IndexOutOfBoundsException("Index out of bounds: " + index);
        }
        return getNodeByIndex(index).data;
    }

    /*
     * Hàm sửa giá trị ở vị trí index trong list.
     */
    public void set(double data, int index) {
        if (index < 0 || index >= size()) {
            throw new IndexOutOfBoundsException("Index out of bounds: " + index);
        }
        getNodeByIndex(index).data = data;
    }

    /*
     * Hàm thêm một phần tử vào list ở vị trí cuối list.
     */
    public void append(double data) {
        MyNode newNode = new MyNode(data);
        if (head == null) {
            head = newNode;
            return;
        }
        MyNode current = head;
        while (current.next != null) {
            current = current.next;
        }
        current.next = newNode;
    }

    /*
     * Hàm thêm một phần tử vào list ở vị trí index trong list.
     */
    public void insert(double data, int index) {
        if (index < 0 || index > size()) {
            throw new IndexOutOfBoundsException("Index out of bounds: " + index);
        }
        MyNode newNode = new MyNode(data);
        if (index == 0) {
            newNode.next = head;
            head = newNode;
            return;
        }
        MyNode prev = getNodeByIndex(index - 1);
        newNode.next = prev.next;
        prev.next = newNode;
    }

    /*
     * Hàm xóa phần tử trong list ở vị trí index.
     */
    public void remove(int index) {
        if (index < 0 || index >= size()) {
            throw new IndexOutOfBoundsException("Index out of bounds: " + index);
        }
        if (index == 0) {
            head = head.next;
            return;
        }
        MyNode prev = getNodeByIndex(index - 1);
        prev.next = prev.next.next;
    }

    /*
     * Hàm sắp xếp list theo giá trị tăng dần sử dụng thuật toán insertion sort, trả về list mới được sắp xếp.
     */
    public MyLinkedList sortIncreasingUsingInsertionSort() {
        MyLinkedList sortedList = new MyLinkedList();
        MyNode current = head;
        while (current != null) {
            double value = current.data;
            if (sortedList.head == null || value <= sortedList.head.data) {
                sortedList.insert(value, 0);
            } else {
                MyNode temp = sortedList.head;
                while (temp.next != null && temp.next.data < value) {
                    temp = temp.next;
                }
                MyNode newNode = new MyNode(value);
                newNode.next = temp.next;
                temp.next = newNode;
            }
            current = current.next;
        }
        return sortedList;
    }

    /*
     * Hàm tìm kiếm một phần tử data trong list sử dụng thuật toán binary search.
     */
    public boolean binarySearch(double data) {
        int left = 0;
        int right = size() - 1;
        while (left <= right) {
            int mid = (left + right) / 2;
            double midValue = get(mid);
            if (midValue == data) {
                return true;
            } else if (midValue < data) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return false;
    }

    /*
     * Hàm trả về node ở vị trí index.
     */
    private MyNode getNodeByIndex(int index) {
        MyNode current = head;
        for (int i = 0; i < index; i++) {
            current = current.next;
        }
        return current;
    }
}