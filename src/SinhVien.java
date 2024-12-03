import java.sql.*;
import java.util.Scanner;

public class SinhVien extends Nguoi {
    private int maSinhVien;
    private int maLop;
    private String Email; 
    public SinhVien(int maSinhVien, String hoTen,int maLop,String ngaySinh,String gioiTinh,String diaChi,String Email) {
        super(hoTen, ngaySinh, diaChi,gioiTinh);
        this.maSinhVien = maSinhVien;
        this.maLop = maLop;
    }
    public int getMaSinhVien() {
        return this.maSinhVien;
    }
    public void setMaSinhVien(int maSinhVien) {
        this.maSinhVien = maSinhVien;
    }
    public int getMaLop() {
        return this.maLop;
    }

    public void setMaLop(int maLop) {
        this.maLop = maLop;
    }

    public String getEmail() {
        return this.Email;
    }

    public void setEmail(String Email) {
        this.Email = Email;
    }
    //Hiển thị thông tin sinh viên
    public static void hienThiSV(){
        Connection conn = ConnectQLSV.getJDBCConnection();
        String query = "select * from sinhvien";
        System.out.printf("%-20s %-20s %-10s %-10s %-10s %-15s %-20s\n","Mã sinh viên","Họ Tên","Mã lớp" ,"ngày sinh","Giới tính","địa chỉ","Email");
        try(Statement st = conn.createStatement()){
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
                int maSinhVien = rs.getInt("maSinhVien");
                String hoTen = rs.getString("hoTen");
                int maLop = rs.getInt("maLop");
                String ngaySinh = rs.getString("ngaySinh");
                String diaChi = rs.getString("diaChi");
                String gioiTinh = rs.getString("gioiTinh");
                String Email = rs.getString("Email");
                System.out.printf("%-20s %-20s %-10s %-10s %-10s %-15s %-20s\n",maSinhVien,hoTen,maLop,ngaySinh,gioiTinh,diaChi,Email);
            }
            conn.close();
        }catch(SQLException e){
            e.printStackTrace();
        }
    }
    // Tìm kiếm sinh viên thêm mã sinh viên
    public static void timKiemSV() {
        Scanner sc = new Scanner(System.in);
    
        System.out.print("Nhập mã sinh viên cần tìm: ");
        int maSinhVien = sc.nextInt();

        Connection conn = ConnectQLSV.getJDBCConnection();
        String query = "SELECT * FROM sinhvien WHERE maSinhVien = ?";
    
        try (PreparedStatement pstm = conn.prepareStatement(query)) {
            pstm.setInt(1, maSinhVien);
            ResultSet rs = pstm.executeQuery();
            System.out.printf("%-10s %-15s %-10s %-10s %-15s %-20s\n","Họ Tên","Mã lớp" ,"ngày sinh","Giới tính","địa chỉ","Email");
            while (rs.next()) {
                String hoTen = rs.getString("hoTen");
                int maLop = rs.getInt("maLop");
                String ngaySinh = rs.getString("ngaySinh");
                String diaChi = rs.getString("diaChi");
                String gioiTinh = rs.getString("gioiTinh");
                String Email = rs.getString("Email");
                System.out.printf("%-10s %-15s %-10s %-10s %-15s %-20s\n",hoTen,maLop,ngaySinh,gioiTinh,diaChi,Email);
            }
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }   
    //Phương thức Thêm sinh viên
    public static void themSV() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Mời nhập số lượng sinh viên: ");
        int n = sc.nextInt();
        sc.nextLine();
        Connection conn = ConnectQLSV.getJDBCConnection();
        String query = "INSERT INTO sinhvien(maSinhVien, hoTen, maLop, ngaySinh, gioiTinh, diaChi,Email) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try(PreparedStatement ps = conn.prepareStatement(query)) {
            for (int i = 0; i < n; i++) {
                System.out.println("Nhập thông tin sinh viên thứ " + (i + 1) + ":");
                System.out.print("Nhập mã sinh viên: ");
                int maSinhVien = sc.nextInt();
                sc.nextLine();
                System.out.print("Nhập họ tên: ");
                String hoTen = sc.nextLine();
    
                System.out.print("Nhập mã lớp: ");
                int maLop = sc.nextInt();
                sc.nextLine();
                System.out.print("Nhập ngày sinh (dd-mm-yyyy): ");
                String ngaySinh = sc.nextLine();
                
                System.out.print("Nhập giới tính: ");
                String gioiTinh = sc.nextLine();
    
                System.out.print("Nhập địa chỉ: ");
                String diaChi = sc.nextLine();

                System.out.print("Nhập Email: ");
                String Email = sc.nextLine();
                sc.nextLine();
                ps.setInt(1, maSinhVien);
                ps.setString(2, hoTen);
                ps.setInt(3, maLop);
                ps.setString(4, ngaySinh);
                ps.setString(5, gioiTinh);
                ps.setString(6, diaChi);
                ps.setString(7, Email);
                int row = ps.executeUpdate();
                if (row != 0) {
                    System.out.println("Thêm sinh viên thứ " + (i + 1) + " thành công!");
                } else {
                    System.out.println("Sinh viên thứ " + (i + 1) + " đã tồn tại!");
                }
            }
            conn.close();
        } catch (SQLException e) {
            System.err.println("Lỗi: " + e.getMessage());
        }
    }
    //Cập nhật thông tin sinh viên
    public static void suaSV() {
        Scanner sc = new Scanner(System.in);
    
        System.out.print("Nhập mã sinh viên cần sửa: ");
        int maSinhVien = sc.nextInt();
        sc.nextLine();
        System.out.print("Nhập họ tên mới: ");
        String hoTen = sc.nextLine();
    
        System.out.print("Nhập mã lớp mới: ");
        int maLop = sc.nextInt();
        sc.nextLine();
        System.out.print("Nhập ngày sinh mới: ");
        String ngaySinh = sc.nextLine();

        System.out.print("Nhập giới tính: ");
        String gioiTinh = sc.nextLine();
    
        System.out.print("Nhập địa chỉ mới: ");
        String diaChi = sc.nextLine();
    
        System.out.print("Nhập Email mới: ");
        String Email = sc.nextLine();
        sc.nextLine();
    
        Connection conn = ConnectQLSV.getJDBCConnection();
        String query = "UPDATE sinhvien SET hoTen = ?, maLop = ?, ngaySinh = ?, gioiTinh = ?, diaChi = ?, Email = ? WHERE maSinhVien = ?";
    
        try (PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setString(1, hoTen);
            ps.setInt(2, maLop);
            ps.setString(3, ngaySinh);
            ps.setString(4, gioiTinh);
            ps.setString(5, diaChi);
            ps.setString(6, Email);
            ps.setInt(7, maSinhVien);
            int row = ps.executeUpdate();
            if (row != 0) {
                System.out.println("Cập nhật thành công ");
            } else {
                System.out.println("SV không tồn tại");
            }
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
     // Xóa sinh viên theo mã sinh viên
     public static void xoaSV() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Nhập mã sinh viên cần xóa: ");
        int maSinhVien = sc.nextInt();
        sc.nextLine();
        Connection conn = ConnectQLSV.getJDBCConnection();
        String query = "DELETE FROM sinhvien WHERE maSinhVien = ?";
    
        try (PreparedStatement pstm = conn.prepareStatement(query)) {
            pstm.setInt(1, maSinhVien);
            int row = pstm.executeUpdate();
            if (row != 0) {
                System.out.println("Xóa thành công ");
            } else {
                System.out.println("SV không tồn tại");
            }
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    // Đếm số lượng sinh viên trong mỗi lớp: 
    public static void demSLSV() {
        Connection conn = ConnectQLSV.getJDBCConnection();
        String query = "select maLop, count(maSinhVien) as Soluongsinhvien " +
                       "from sinhvien " +
                       "group by maLop";
    
        try (Statement st = conn.createStatement()) {
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
                int maLop = rs.getInt("maLop");
                int soluongSinhVien = rs.getInt("Soluongsinhvien");
                System.out.printf("%-10s %-10s\n", maLop, soluongSinhVien);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    // hiển thị các học sinh có tổng điểm > 8
    public static void hienThiHSG() {
        Connection conn = ConnectQLSV.getJDBCConnection();
        String query = "SELECT sv.maSinhVien, sv.hoTen, lop.tenLop, kq.tongDiem " +
               "FROM sinhvien sv " +
               "JOIN ketquahoctap kq ON sv.maSinhVien = kq.maSinhVien " +
               "JOIN lop ON sv.maLop = lop.maLop " +
               "WHERE kq.tongDiem > 8";
        try (Statement st = conn.createStatement();
             ResultSet rs = st.executeQuery(query)) {
            while (rs.next()) {
                int maSinhVien = rs.getInt("maSinhVien");
                String hoTen = rs.getString("hoTen");
                String tenLop = rs.getString("tenLop");
                float tongDiem = rs.getFloat("tongDiem");
                System.out.printf("%-10s %-20s %-15s %-10s\n",maSinhVien,hoTen,tenLop,tongDiem);
            }
            // Đóng kết nối
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public static void hienThiHSY() {
        Connection conn = ConnectQLSV.getJDBCConnection();
        String query = "SELECT sv.maSinhVien, sv.hoTen, lop.tenLop, kq.tongDiem " +
               "FROM sinhvien sv " +
               "JOIN ketquahoctap kq ON sv.maSinhVien = kq.maSinhVien " +
               "JOIN lop ON sv.maLop = lop.maLop " +
               "WHERE kq.tongDiem <= 5";
        try (Statement st = conn.createStatement();
             ResultSet rs = st.executeQuery(query)) {
            while (rs.next()) {
                int maSinhVien = rs.getInt("maSinhVien");
                String hoTen = rs.getString("hoTen");
                String tenLop = rs.getString("tenLop");
                float tongDiem = rs.getFloat("tongDiem");
                System.out.printf("%-10s %-20s %-15s %-10s\n",maSinhVien,hoTen,tenLop,tongDiem);
            }
            // Đóng kết nối
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public static void hienThiHSTB() {
        Connection conn = ConnectQLSV.getJDBCConnection();
        String query = "SELECT sv.maSinhVien, sv.hoTen, lop.tenLop, kq.tongDiem " +
               "FROM sinhvien sv " +
               "JOIN ketquahoctap kq ON sv.maSinhVien = kq.maSinhVien " +
               "JOIN lop ON sv.maLop = lop.maLop " +
               "WHERE kq.tongDiem > 6 and kq.tongDiem <=8";
        try (Statement st = conn.createStatement();
             ResultSet rs = st.executeQuery(query)) {
            while (rs.next()) {
                int maSinhVien = rs.getInt("maSinhVien");
                String hoTen = rs.getString("hoTen");
                String tenLop = rs.getString("tenLop");
                float tongDiem = rs.getFloat("tongDiem");
                System.out.printf("%-10s %-20s %-15s %-10s\n",maSinhVien,hoTen,tenLop,tongDiem);
            }
            // Đóng kết nối
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public static void hienThiThongKeGioiTinh() {
        Scanner sc = new Scanner(System.in);
        System.out.print("mời nhập mã lớp: ");
        int maLop = sc.nextInt();
        Connection conn = ConnectQLSV.getJDBCConnection();
        String query = "SELECT gioiTinh, COUNT(*) AS soluong " +
                       "FROM sinhvien " +
                       "WHERE maLop = ? " +
                       "GROUP BY gioiTinh;";
        System.out.printf("%-15s %-15s\n","Giới tính","số lượng");
        try (PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setInt(1, maLop);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                String gioiTinh = rs.getString("gioiTinh");
                int soluong = rs.getInt("soluong");
                System.out.printf("%-15s %-15s\n", gioiTinh, soluong);
            }
    
            // Đóng kết nối
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    
}
