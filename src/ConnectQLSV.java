import java.sql.*;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
public class ConnectQLSV {

    // Phương thức để kết nối đến CSDL MySQL
    public static Connection getJDBCConnection() {
        String url = "jdbc:mysql://localhost:3306/quanlysinhvien";
        String user = "root";
        String password = "123456Aa";
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            return DriverManager.getConnection(url, user, password);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ConnectQLSV.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Lỗi: " + ex.toString());
        } catch (SQLException ex) {
            Logger.getLogger(ConnectQLSV.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Lỗi: " + ex.toString());
            System.out.println("Lỗi kết nối CSDL!");
        }

        return null;
    }
    //menu hiển thị thông tin
    public static void hienThiThongTin() {
        Scanner sc = new Scanner(System.in);
        int choice;
        do {
            System.out.println("\n--- Menu Hiện Thị Thông Tin ---");
            System.out.println("1. hiển thị thông tin Sinh Viên");
            System.out.println("2. hiển thị thông tin Lớp Học");
            System.out.println("3. hiển thị thông tin Môn Học");
            System.out.println("4. hiển thị thông tin Kết Quả Học Tập");
            System.out.println("0. Quay lại");
            System.out.print("Lựa chọn của bạn: ");
            choice = sc.nextInt();
            sc.nextLine();
    
            switch (choice) {
                case 1:
                    SinhVien.hienThiSV();;
                    break;
                case 2:
                    Lop.hienThilop();;
                    break;
                case 3:
                    MonHoc.hienThiMH();;
                    break;
                case 4:
                    KetQuaHocTap.hienThiKQ();
                    break;
                case 0:
                    System.out.println("Quay lại menu chính.");
                    break;
                default:
                    System.out.println("Lựa chọn không hợp lệ. Vui lòng thử lại.");
            }
        } while (choice != 0);
    }
    //menu thêm thông tin
    public static void themThongTin() {
        Scanner sc = new Scanner(System.in);
        int choice;
        do {
            System.out.println("\n--- Menu Thêm Thông Tin ---");
            System.out.println("1. Thêm thông tin Sinh Viên");
            System.out.println("2. Thêm thông tin Lớp Học");
            System.out.println("3. Thêm thông tin Môn Học");
            System.out.println("4. Thêm thông tin Kết Quả Học Tập");
            System.out.println("0. Quay lại");
            System.out.print("Lựa chọn của bạn: ");
            choice = sc.nextInt();
            sc.nextLine();
    
            switch (choice) {
                case 1:
                    SinhVien.themSV();
                    break;
                case 2:
                    Lop.themLop();
                    break;
                case 3:
                    MonHoc.themMH();
                    break;
                case 4:
                    KetQuaHocTap.themKQ();
                    break;
                case 0:
                    System.out.println("Quay lại menu chính.");
                    break;
                default:
                    System.out.println("Lựa chọn không hợp lệ. Vui lòng thử lại.");
            }
        } while (choice != 0);
    }
    //menu xóa thông tin
    public static void xoaThongTin() {
        Scanner sc = new Scanner(System.in);
        int choice;
        do {
            System.out.println("\n--- Menu Xóa Thông Tin ---");
            System.out.println("1. Xóa thông tin Sinh Viên");
            System.out.println("2. Xóa thông tin Lớp Học");
            System.out.println("3. Xóa thông tin Môn Học");
            System.out.println("4. Xóa thông tin Kết Quả Học Tập");
            System.out.println("0. Quay lại");
            System.out.print("Lựa chọn của bạn: ");
            choice = sc.nextInt();
            sc.nextLine();
    
            switch (choice) {
                case 1:
                    SinhVien.xoaSV();;
                    break;
                case 2:
                    Lop.xoaLop();
                    break;
                case 3:
                    MonHoc.xoaMH();
                    break;
                case 4:
                    KetQuaHocTap.xoaKQSV();
                    break;
                case 0:
                    System.out.println("Quay lại menu chính.");
                    break;
                default:
                    System.out.println("Lựa chọn không hợp lệ. Vui lòng thử lại.");
            }
        } while (choice != 0);
    }
    //menu sửa thông tin
    public static void suaThongTin() {
        Scanner sc = new Scanner(System.in);
        int choice;
        do {
            System.out.println("\n--- Menu sửa Thông Tin ---");
            System.out.println("1. Sửa thông tin Sinh Viên");
            System.out.println("2. Sửa thông tin Lớp Học");
            System.out.println("3. Sửa thông tin Môn Học");
            System.out.println("4. Sửa thông tin Kết Quả Học Tập");
            System.out.println("0. Quay lại");
            System.out.print("Lựa chọn của bạn: ");
            choice = sc.nextInt();
            sc.nextLine();
    
            switch (choice) {
                case 1:
                    SinhVien.suaSV();
                    break;
                case 2:
                    Lop.suaLop();
                    break;
                case 3:
                    MonHoc.suaMH();
                    break;
                case 4:
                    KetQuaHocTap.suaKQ();
                    break;
                case 0:
                    System.out.println("Quay lại menu chính.");
                    break;
                default:
                    System.out.println("Lựa chọn không hợp lệ. Vui lòng thử lại.");
            }
        } while (choice != 0);
    }
    //menu tìm kiếm thông tin
    public static void timKiemThongTin() {
        Scanner sc = new Scanner(System.in);
        int choice;
        do {
            System.out.println("\n--- Menu tìm kiếm Thông Tin ---");
            System.out.println("1. tìm kiếm thông tin Sinh Viên");
            System.out.println("2. tìm kiếm thông tin Lớp Học");
            System.out.println("3. tìm kiếm thông tin Môn Học");
            System.out.println("4. tìm kiếm thông tin Kết Quả Học Tập");
            System.out.println("0. Quay lại");
            System.out.print("Lựa chọn của bạn: ");
            choice = sc.nextInt();
            sc.nextLine();
    
            switch (choice) {
                case 1:
                    SinhVien.timKiemSV();
                    break;
                case 2:
                    Lop.timKiemLop();
                    break;
                case 3:
                    MonHoc.timKiemMH();
                    break;
                case 4:
                    KetQuaHocTap.timKiemKQ();
                    break;
                case 0:
                    System.out.println("Quay lại menu chính.");
                    break;
                default:
                    System.out.println("Lựa chọn không hợp lệ. Vui lòng thử lại.");
            }
        } while (choice != 0);
    }
     //menu tính toán , thống kê
     public static void tinhToanThongKe() {
        Scanner sc = new Scanner(System.in);
        int choice;
        do {
            System.out.println("\n--- Menu chức năng ---");
            System.out.println("1. Thống kê số lượng sinh viên ở mỗi lớp học ");
            System.out.println("2. Thống kê giới tính theo lớp");
            System.out.println("3. Điểm trung bình của sinh viên theo môn học ");
            System.out.println("4. Hiển thị học sinh có học lực tốt ");
            System.out.println("5. Hiển thị học sinh có lực học yếu ");
            System.out.println("6. Hiển thị học sinh có lực học trung bình ");
            System.out.println("0. Quay lại");
            System.out.print("Lựa chọn của bạn: ");
            choice = sc.nextInt();
            sc.nextLine();
    
            switch (choice) {
                case 1:
                    SinhVien.demSLSV();
                    break;
                case 2:
                    SinhVien.hienThiThongKeGioiTinh();
                    break;
                case 3:
                    MonHoc.hienThiTBMon();
                    break;
                case 4:
                    SinhVien.hienThiHSG();
                    break;
                case 5:
                    SinhVien.hienThiHSY();
                    break;
                case 6:
                    SinhVien.hienThiHSTB();
                case 0:
                    System.out.println("Quay lại menu chính.");
                    break;
                default:
                    System.out.println("Lựa chọn không hợp lệ. Vui lòng thử lại.");
            }
        } while (choice != 0);
    }
}

