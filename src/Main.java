import java.util.Scanner;

public class Main extends ConnectQLSV {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int choice;
        do {
            System.out.println("\n=== QUẢN LÝ SINH VIÊN ===");
            System.out.println("1. Hiển thị thông tin");
            System.out.println("2. bổ sung thông tin");
            System.out.println("3. Sửa thông tin");
            System.out.println("4. Xóa thông tin");
            System.out.println("5. Tìm kiếm thông tin");
            System.out.println("6. Thống Kê thông tin");
            System.out.println("0. Thoát");
            System.out.print("Nhập lựa chọn của bạn: ");
            choice = sc.nextInt();
            sc.nextLine();
            switch (choice) {
                case 1:
                    ConnectQLSV.hienThiThongTin();
                    break;
                case 2:
                    ConnectQLSV.themThongTin();
                    break;
                case 3:
                    ConnectQLSV.suaThongTin();
                    break;
                case 4:
                    ConnectQLSV.xoaThongTin();
                    break;
                case 5:
                    ConnectQLSV.timKiemThongTin();
                    break;
                case 6:
                    ConnectQLSV.tinhToanThongKe();
                    break;
                case 0:
                    System.out.println("Thoát chương trình. Hẹn gặp lại!");
                    break;
                default:
                    System.out.println("Lựa chọn không hợp lệ! Vui lòng thử lại.");
            }
        } while (choice != 0);
        sc.close();
    }
}
