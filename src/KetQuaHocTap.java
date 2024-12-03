import java.sql.*;
import java.util.Scanner;
public class KetQuaHocTap {
    private int maSinhVien;
    private int maMonHoc;
    private float diemCC;
    private float diemGK;
    private float diemCK;
    private float tongDiem;
    public KetQuaHocTap() {
    }
    public KetQuaHocTap(int maSinhVien, int maMonHoc, float diemCC, float diemGK, float diemCK, float tongDiem) {
        this.maSinhVien = maSinhVien;
        this.maMonHoc = maMonHoc;
        this.diemCC = diemCC;
        this.diemGK = diemGK;
        this.diemCK = diemCK;
        this.tongDiem = tongDiem;
    }
    public int getMaSinhVien() {
        return this.maSinhVien;
    }
    public void setMaSinhVien(int maSinhVien) {
        this.maSinhVien = maSinhVien;
    }
    public int getMaMonHoc() {
        return this.maMonHoc;
    }
    public void setMaMonHoc(int maMonHoc) {
        this.maMonHoc = maMonHoc;
    }
    public float getDiemCC() {
        return this.diemCC;
    }
    public void setDiemCC(float diemCC) {
        this.diemCC = diemCC;
    }
    public float getDiemGK() {
        return this.diemGK;
    }
    public void setDiemGK(float diemGK) {
        this.diemGK = diemGK;
    }
    public float getDiemCK() {
        return this.diemCK;
    }
    public void setDiemCK(float diemCK) {
        this.diemCK = diemCK;
    }
    public float getTongDiem() {
        return this.tongDiem;
    }
    public void setTongDiem(float tongDiem) {
        this.tongDiem = tongDiem;
    }
    // hiển thị kết quả
    public static void hienThiKQ(){
        Connection conn = ConnectQLSV.getJDBCConnection();
        String query = "select * from ketquahoctap";
        System.out.printf("%-20s %-15s %-15s %-15s %-15s %-15s\n","Mã sinh viên","Mã môn học","Điểm Chuyên cần","Điểm giữa kỳ","Điểm cuối kỳ","Tổng điểm");
        try(Statement st = conn.createStatement()){
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
                int maSinhVien = rs.getInt("maSinhVien");
                int maMonhoc = rs.getInt("maMonHoc");
                float diemCC = rs.getFloat("diemCC");
                float diemGK = rs.getFloat("diemGK");
                float diemCK = rs.getFloat("diemCK");
                float tongDiem = rs.getFloat("tongDiem");
                System.out.printf("%-20s %-15s %-15s %-15s %-15s %-15s\n",maSinhVien,maMonhoc,diemCC,diemGK,diemCK,tongDiem);
            }
            conn.close();
        }catch(SQLException e){
            e.printStackTrace();
        }
    }
     // Tìm kiếm Kết quả bằng mã sinh viên
     public static void timKiemKQ() {
        Scanner sc = new Scanner(System.in);
    
        System.out.print("Nhập mã sinh viên cần tìm: ");
        int maSinhVien = sc.nextInt();

        Connection conn = ConnectQLSV.getJDBCConnection();
        String query = "SELECT * FROM ketquahoctap WHERE maSinhVien = ?";
    
        try (PreparedStatement pstm = conn.prepareStatement(query)) {
            pstm.setInt(1, maSinhVien);
            ResultSet rs = pstm.executeQuery();
            System.out.printf("%-20s %-15s %-15s %-15s %-15s\n","Mã môn học","Điểm Chuyên cần","Điểm giữa kỳ","Điểm cuối kỳ","Tổng điểm");
            while (rs.next()) {
                int maMonhoc = rs.getInt("maMonHoc");
                float diemCC = rs.getFloat("diemCC");
                float diemGK = rs.getFloat("diemGK");
                float diemCK = rs.getFloat("diemCK");
                float tongDiem = rs.getFloat("tongDiem");
                System.out.printf("%-20s %-15s %-15s %-15s %-15s\n",maMonhoc,diemCC,diemGK,diemCK,tongDiem);
            }
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    } 
    //Thêm kết quả học tập sinh viên
    public static void themKQ() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Mời nhập giá trị: ");
        int n = sc.nextInt();
        sc.nextLine();
        Connection conn = ConnectQLSV.getJDBCConnection();
        String query = "INSERT INTO ketquahoctap(maSinhVien, maMonHoc, diemCC, diemGK, diemCK, tongDiem) VALUES (?, ?, ?, ?, ?, ?)";
        try(PreparedStatement ps = conn.prepareStatement(query)) {
            for (int i = 0; i < n; i++) {
                System.out.println("Nhập điểm cho sinh viên thứ " + (i + 1) + ":");
                System.out.print("Nhập mã sinh viên: ");
                int maSinhVien = sc.nextInt();
                sc.nextLine();
                System.out.print("Nhập mã môn học: ");
                int maMonHoc = sc.nextInt();
                sc.nextLine();
                System.out.print("Nhập điểm chuyên cần: ");
                float diemCC = sc.nextFloat();
                System.out.print("Nhập điểm giữa kỳ: ");
                float diemGK = sc.nextFloat();
                System.out.print("Nhập điểm cuối kỳ: ");
                float diemCK = sc.nextFloat();
                System.out.print("Nhập tổng điểm: ");
                float tongDiem = sc.nextFloat();
                sc.nextLine();
                ps.setInt(1, maSinhVien);
                ps.setInt(2, maMonHoc);
                ps.setFloat(3, diemCC);
                ps.setFloat(4, diemGK);
                ps.setFloat(5, diemCK);
                ps.setFloat(6, tongDiem);
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
    public static void suaKQ() {
        Scanner sc = new Scanner(System.in);
    
        System.out.print("Nhập mã sinh viên cần sửa: ");
        int maSinhVien = sc.nextInt();
        sc.nextLine();

        System.out.print("Nhập mã môn học: ");
        int maMonHoc = sc.nextInt();

        System.out.print("Nhập điểm chuyên cần: ");
        float diemCC = sc.nextInt();
    
        System.out.print("Nhập điểm giữa kỳ: ");
        float diemGK = sc.nextFloat();
        sc.nextLine();
        System.out.print("Nhập điểm cuối kỳ: ");
        float diemCK = sc.nextFloat();

        System.out.print("Nhập tổng điểm: ");
        float tongDiem = sc.nextFloat();

        sc.nextLine();
    
        Connection conn = ConnectQLSV.getJDBCConnection();
        String query = "UPDATE ketquahoctap SET maMonHoc = ?, diemCC = ?, diemGK = ?, diemCK = ?, tongDiem = ? WHERE maSinhVien = ?";

    
        try (PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setInt(1, maMonHoc);
            ps.setFloat(2, diemCC);
            ps.setFloat(3, diemGK);
            ps.setFloat(4, diemCK);
            ps.setFloat(5, tongDiem);
            ps.setInt(6, maSinhVien);
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
     // Xóa kết quả học tập theo mã sinh viên
     public static void xoaKQSV() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Nhập mã sinh viên cần xóa: ");
        int maSinhVien = sc.nextInt();
        sc.nextLine();
        Connection conn = ConnectQLSV.getJDBCConnection();
        String query = "DELETE FROM ketquahoctap WHERE maSinhVien = ?";
    
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
}
