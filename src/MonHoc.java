import java.sql.*;
import java.util.Scanner;
public class MonHoc {
    private int maMonHoc;
    private String tenMonHoc;
    private int soTinChi;
    private int hocKy;
    public MonHoc(){}
    public MonHoc(int maMonHoc, String tenMonHoc, int soTinChi, int hocKy) {
        this.maMonHoc = maMonHoc;
        this.tenMonHoc = tenMonHoc;
        this.soTinChi = soTinChi;
        this.hocKy = hocKy;
    }
    public int getMaMonHoc() {
        return this.maMonHoc;
    }

    public void setMaMonHoc(int maMonHoc) {
        this.maMonHoc = maMonHoc;
    }

    public String getTenMonHoc() {
        return this.tenMonHoc;
    }

    public void setTenMonHoc(String tenMonHoc) {
        this.tenMonHoc = tenMonHoc;
    }

    public int getSoTinChi() {
        return this.soTinChi;
    }

    public void setSoTinChi(int soTinChi) {
        this.soTinChi = soTinChi;
    }

    public int getHocKy() {
        return this.hocKy;
    }

    public void setHocKy(int hocKy) {
        this.hocKy = hocKy;
    }
    //Hiển thị thông tin môn học
    public static void hienThiMH(){
        Connection conn = ConnectQLSV.getJDBCConnection();
        String query = "select * from monhoc";
        System.out.printf("%-15s %-20s %-15s %-10s\n","Mã môn học","Tên môn học","Số tín chỉ" ,"Học Kỳ");
        try(Statement st = conn.createStatement()){
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
                int maMonHoc = rs.getInt("maMonHoc");
                String tenMonHoc = rs.getString("tenMonHoc");
                int soTinChi = rs.getInt("soTinChi");
                int hocKy = rs.getInt("hocKy");
                System.out.printf("%-15s %-20s %-15s %-10s\n",maMonHoc,tenMonHoc,soTinChi,hocKy);
            }
            conn.close();
        }catch(SQLException e){
            e.printStackTrace();
        }
    }
    //Phương thức Thêm môn học
    public static void themMH() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Mời nhập số lượng môn học: ");
        int n = sc.nextInt();
        sc.nextLine();
        Connection conn = ConnectQLSV.getJDBCConnection();
        String query = "INSERT INTO monhoc(maMonHoc, tenMonHoc, soTinChi, hocKy) VALUES (?, ?, ?, ?)";
        try(PreparedStatement ps = conn.prepareStatement(query)) {
            for (int i = 0; i < n; i++) {
                System.out.println("Nhập thông tin môn học thứ " + (i + 1) + ":");
                System.out.print("Nhập mã môn học: ");
                int maMonHoc = sc.nextInt();
                sc.nextLine();
                System.out.print("Nhập tên môn học: ");
                String tenMonHoc = sc.nextLine();
    
                System.out.print("Nhập số tín chỉ: ");
                int soTinChi = sc.nextInt();
                sc.nextLine();
                System.out.print("nhập học kỳ: ");
                int hocKy = sc.nextInt();
                sc.nextLine();
                ps.setInt(1, maMonHoc);
                ps.setString(2, tenMonHoc);
                ps.setInt(3, soTinChi);
                ps.setInt(4, hocKy);
                int row = ps.executeUpdate();
                if (row != 0) {
                    System.out.println("Thêm môn học thứ " + (i + 1) + " thành công!");
                } else {
                    System.out.println("Sinh viên thứ " + (i + 1) + " đã tồn tại!");
                }
            }
            conn.close();
        } catch (SQLException e) {
            System.err.println("Lỗi: " + e.getMessage());
        }
    }
    //Cập nhật thông tin môn học
    public static void suaMH() {
        Scanner sc = new Scanner(System.in);
    
        System.out.print("Nhập mã môn học cần sửa: ");
        int maMonHoc = sc.nextInt();
        sc.nextLine();
        System.out.print("Nhập tên môn học: ");
        String tenMonHoc = sc.nextLine();
    
        System.out.print("Nhập số tín chỉ: ");
        int soTinChi = sc.nextInt();
        sc.nextLine();
        System.out.print("Nhập học Kỳ: ");
        int hocKy = sc.nextInt();

        Connection conn = ConnectQLSV.getJDBCConnection();
        String query = "UPDATE monhoc SET  tenMonHoc= ?, soTinChi = ?, hocKy = ? Where maMonHoc = ?";
    
        try (PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setString(1, tenMonHoc);
            ps.setInt(2, soTinChi);
            ps.setInt(3, hocKy);
            ps.setInt(4, maMonHoc);
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
    //Tìm kiếm môn học
    public static void timKiemMH() {
        Scanner sc = new Scanner(System.in);
    
        System.out.print("Nhập mã Môn học cần tìm: ");
        int maMonHoc = sc.nextInt();

        Connection conn = ConnectQLSV.getJDBCConnection();
        String query = "SELECT * FROM monhoc WHERE maMonHoc = ?";
        try (PreparedStatement pstm = conn.prepareStatement(query)) {
            pstm.setInt(1, maMonHoc);
            ResultSet rs = pstm.executeQuery();
            System.out.printf("%-15s %-15s %-10s\n","Tên môn học","Số tín chỉ" ,"Học Kỳ");
            while (rs.next()) {
                String tenMonHoc = rs.getString("tenMonHoc");
                int soTinChi = rs.getInt("soTinChi");
                int hocKy = rs.getInt("hocKy");
                System.out.printf("%-15s %-15s %-10s\n",tenMonHoc,soTinChi,hocKy);
            }
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }   
    // Xóa môn học
    public static void xoaMH() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Nhập mã môn học cần xóa: ");
        int maMonHoc = sc.nextInt();
        sc.nextLine();
        Connection conn = ConnectQLSV.getJDBCConnection();
        String query = "DELETE FROM monhoc WHERE maMonHoc = ?";
    
        try (PreparedStatement pstm = conn.prepareStatement(query)) {
            pstm.setInt(1, maMonHoc);
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
    // Thống kê điểm trung bình của một môn học:
    public static void hienThiTBMon() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Mời nhập mã môn học: ");
        int maMonHoc = sc.nextInt();
        sc.nextLine();
        Connection conn = ConnectQLSV.getJDBCConnection();
        String query = "SELECT maMonHoc, AVG(tongDiem) AS DiemTrungBinh " +
               "FROM ketquahoctap " +
               "WHERE maMonHoc = ? " +
               "GROUP BY maMonHoc;";
        System.out.printf("%-15s %-15s\n","Mã môn học","Điểm trung bình");
        try (PreparedStatement ps = conn.prepareStatement(query)) {
             ps.setInt(1, maMonHoc);
             ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int mamonHoc = rs.getInt("maMonHoc");
                float DiemTrungBinh = rs.getFloat("DiemTrungBinh");
                System.out.printf("%-15s %-15s\n",mamonHoc,DiemTrungBinh);
            }
            // Đóng kết nối
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}


