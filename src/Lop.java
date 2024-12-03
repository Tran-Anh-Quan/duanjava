import java.sql.*;
import java.util.Scanner;
public class Lop {
    private int maLop;
    private String tenLop;
    private String Khoa;
    public Lop(){}
    public Lop(int maLop, String tenLop,String Khoa) {
        this.maLop = maLop;
        this.tenLop = tenLop;
        this.Khoa = Khoa;
    }
    public int getMaLop() {
        return this.maLop;
    }
    public void setMaLop(int maLop) {
        this.maLop = maLop;
    }
    public String getTenLop() {
        return this.tenLop;
    }
    public void setTenLop(String tenLop) {
        this.tenLop = tenLop;
    }
    public String getkhoa() {
        return this.Khoa;
    }
    public void setkhoa(int Khoa) {
        this.maLop = Khoa;
    }
    //hiển thị thông tin lớp
    public static void hienThilop(){
        Connection conn = ConnectQLSV.getJDBCConnection();
        String query = "select * from lop";
        System.out.printf("%-15s %-15s %-15s\n","Mã lớp","Tên lớp","Khoa");
        try(Statement st = conn.createStatement()){
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
                int maLop = rs.getInt("maLop");
                String tenLop = rs.getString("tenLop");
                String Khoa = rs.getString("Khoa");
                System.out.printf("%-15s %-15s %-15s\n",maLop,tenLop,Khoa);
            }
            conn.close();
        }catch(SQLException e){
            e.printStackTrace();
        }
    }
    //Phương thức Thêm Lớp
    public static void themLop() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Mời nhập số lượng lớp học: ");
        int n = sc.nextInt();
        sc.nextLine();
        Connection conn = ConnectQLSV.getJDBCConnection();
        String query = "INSERT INTO lop(maLop, tenLop, Khoa) VALUES (?, ?, ?)";
        try(PreparedStatement ps = conn.prepareStatement(query)) {
            for (int i = 0; i < n; i++) {
                System.out.println("Nhập thông tin lớp thứ " + (i + 1) + ":");
                System.out.print("Nhập mã lớp: ");
                int maLop = sc.nextInt();
                sc.nextLine();
                System.out.print("Nhập tên lớp: ");
                String tenLop = sc.nextLine();
    
                System.out.print("Nhập tên khoa: ");
                String Khoa = sc.nextLine();
                sc.nextLine();
                ps.setInt(1, maLop);
                ps.setString(2, tenLop);
                ps.setString(3, Khoa);
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
     //Cập nhật thông tin lớp
     public static void suaLop() {
        Scanner sc = new Scanner(System.in);
    
        System.out.print("Nhập mã Lớp cần sửa: ");
        int maLop = sc.nextInt();
        sc.nextLine();
        System.out.print("Nhập tên lớp: ");
        String tenLop = sc.nextLine();
        System.out.print("Nhập tên Khoa: ");
        String Khoa = sc.nextLine();
        Connection conn = ConnectQLSV.getJDBCConnection();
        String query = "UPDATE lop SET  tenLop= ?, Khoa = ? Where maLop = ?";
    
        try (PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setString(1, tenLop);
            ps.setString(2, Khoa);
            ps.setInt(3, maLop);
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
    //Tìm kiếm lớp
    public static void timKiemLop() {
        Scanner sc = new Scanner(System.in);
    
        System.out.print("Nhập mã Lớp cần tìm: ");
        int maLop = sc.nextInt();

        Connection conn = ConnectQLSV.getJDBCConnection();
        String query = "SELECT * FROM lop WHERE maLop = ?";
        try (PreparedStatement pstm = conn.prepareStatement(query)) {
            pstm.setInt(1, maLop);
            ResultSet rs = pstm.executeQuery();
            System.out.printf("%-15s %-15s\n","Tên lớp","Khoa");
            while (rs.next()) {
                String tenLop = rs.getString("tenLop");
                String Khoa = rs.getString("Khoa");
                System.out.printf("%-15s %-15s\n",tenLop,Khoa);
            }
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }   
    // Xóa lớp
    public static void xoaLop() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Nhập mã lớp học cần xóa: ");
        int maLop = sc.nextInt();
        sc.nextLine();
        Connection conn = ConnectQLSV.getJDBCConnection();
        String query = "DELETE FROM lop WHERE maLop = ?";
    
        try (PreparedStatement pstm = conn.prepareStatement(query)) {
            pstm.setInt(1, maLop);
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
