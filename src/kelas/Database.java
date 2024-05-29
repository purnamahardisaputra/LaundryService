/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package kelas;

import gui.Account;
import gui.Dashboard;
import gui.Inventory;
import gui.Invoice;
import gui.Login;
import static gui.Login.usernameTxt;
import gui.Qris;
import gui.TrackingStatus;
import gui.Transaction;
import java.awt.Image;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

/**
 *
 * @author Purnama Hardi Saputra
 */
// pusat penyimpanan data
public class Database {
    private static Connection c;
    private static Statement s;
    private static ResultSet rs;
    private static PreparedStatement ps;
    private ImageIcon format = null;
    
    // menyimpan data saat register
    public void addRegister(String username, String password, String code){
        try {
            Login login = new Login();
            openDb();
            if (isUsernameExists(username)) {
                JOptionPane.showMessageDialog(null, "Username sudah terdaftar. Silakan gunakan username lain.");
            } else {
                ps = c.prepareStatement("INSERT INTO `dataadmin` VALUES (?, ?, ?)");
                ps.setString(1, username);
                ps.setString(2, password);
                ps.setString(3, code);
                ps.executeUpdate();
                login.setVisible(true);
            }
        } catch (SQLException | ClassNotFoundException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, ex.getLocalizedMessage());
        } finally {
            closeDb();
        }
    }
    
    // jika username sudah ada
    public boolean isUsernameExists(String username) throws SQLException {
        ps = c.prepareStatement("SELECT `username` FROM `dataadmin` WHERE `username` = ?");
        ps.setString(1, username);
        rs = ps.executeQuery();

        return rs.next();
    }
    
    // menyocokkan username dan password saat register di halaman Login
    public void showLogin(String username, String password){
        try {
            Dashboard dashboard = new Dashboard();
            openDb();
            ps = c.prepareStatement("SELECT `username`, `password` FROM `dataadmin` WHERE `username` = ? AND `password` = ?");
            ps.setString(1, username);
            ps.setString(2, password);
            rs = ps.executeQuery();
            if (rs.next()) {
                JOptionPane.showMessageDialog(null, "Login Successfully");    
                dashboard.setVisible(true);
            } else {
                JOptionPane.showMessageDialog(null, "Invalid Username or Password");
            }
        } catch (SQLException | ClassNotFoundException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, ex.getLocalizedMessage());
        } finally {
            closeDb();
        }
    }
    
    // memasukkan data transaksi
    public void createTransaction(Transaction transaction){
        try {
            Qris qris = new Qris(transaction);
            Invoice invoice = new Invoice(transaction);
            openDb();
            ps = c.prepareStatement("INSERT INTO `transaksi` VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
            ps.setString(1, usernameTxt.getText());
            ps.setString(2, transaction.getId());
            ps.setString(3, transaction.getCustomer());
            ps.setDouble(4, transaction.getItems());
            ps.setDouble(5, transaction.getPrice());
            ps.setString(6, transaction.getTypeService());
            ps.setString(7, transaction.getTypeDay());
            ps.setString(8, transaction.getTypeLaundry());
            ps.setString(9, transaction.getTypePayment());
            ps.setString(10, transaction.getStatus());
            ps.executeUpdate();
            if(transaction.getTypePayment().equals("QRIS")){
                qris.setVisible(true);
            } else if(transaction.getTypePayment().equals("Cash")){
                invoice.setVisible(true);
            }
        } catch (SQLException | ClassNotFoundException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, ex.getLocalizedMessage());
        } finally {
            closeDb();
        }
    }
    
    // memunculkan data transaksi di halaman TrackingStatus
    public void showTransaction(TrackingStatus trackingStatus){
        try {
            ArrayList<String> id = new ArrayList<>();
            ArrayList<String> customer = new ArrayList<>();
            ArrayList<String> typeService = new ArrayList<>();
            ArrayList<String> typeDay = new ArrayList<>();
            ArrayList<String> typeLaundry = new ArrayList<>();
            ArrayList<String> typePayment = new ArrayList<>();
            ArrayList<String> pending = new ArrayList<>();
            ArrayList<Double> items = new ArrayList<>();
            ArrayList<Double> price = new ArrayList<>();
            openDb();
            ps = c.prepareStatement("SELECT `id`, `customer`, `items`, `price`, `typeService`, `typeDay`, `typeLaundry`, "
                    + "`typePayment`, `status` FROM `transaksi` WHERE `username` = ?");
            ps.setString(1, usernameTxt.getText());
            rs = ps.executeQuery();
            while (rs.next()) {
                id.add(rs.getString("id"));
                customer.add(rs.getString("customer"));
                items.add(rs.getDouble("items"));
                price.add(rs.getDouble("price"));
                typeService.add(rs.getString("typeService"));
                typeDay.add(rs.getString("typeDay"));
                typeLaundry.add(rs.getString("typeLaundry"));
                typePayment.add(rs.getString("typePayment"));
                pending.add(rs.getString("status"));
            }
            Tracking tracking = new Tracking(id, customer, items, price, typeService, typeDay, typeLaundry, typePayment, pending);
            trackingStatus.setTable(tracking);
        } catch (SQLException | ClassNotFoundException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, ex.getLocalizedMessage());
        } finally {
            closeDb();
        }
    }
    
    // memunculkan data transaksi di halaman Inventory
    public void showTransaction(Inventory inventory){
        try {
            ArrayList<String> id = new ArrayList<>();
            ArrayList<String> customer = new ArrayList<>();
            ArrayList<String> typeService = new ArrayList<>();
            ArrayList<String> typeDay = new ArrayList<>();
            ArrayList<String> typeLaundry = new ArrayList<>();
            ArrayList<String> typePayment = new ArrayList<>();
            ArrayList<Double> items = new ArrayList<>();
            ArrayList<Double> price = new ArrayList<>();
            openDb();
            ps = c.prepareStatement("SELECT `id`, `customer`, `items`, `price`, `typeService`, `typeDay`, `typeLaundry`, `typePayment`"
                    + " FROM `transaksi` WHERE `username` = ?");
            ps.setString(1, usernameTxt.getText());
            rs = ps.executeQuery();
            while (rs.next()) {
                id.add(rs.getString("id"));
                customer.add(rs.getString("customer"));
                items.add(rs.getDouble("items"));
                price.add(rs.getDouble("price"));
                typeService.add(rs.getString("typeService"));
                typeDay.add(rs.getString("typeDay"));
                typeLaundry.add(rs.getString("typeLaundry"));
                typePayment.add(rs.getString("typePayment"));
            }
            Storage storage = new Storage(id, customer, items, price, typeService, typeDay, typeLaundry, typePayment);
            inventory.setTable(storage);
        } catch (SQLException | ClassNotFoundException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, ex.getLocalizedMessage());
        } finally {
            closeDb();
        }
    }
    
    // mengupdate data transaksi pada kolom status berdasarkan id
    public void updateStatus(String id, String status){
        try {
            openDb();
            ps = c.prepareStatement("UPDATE `transaksi` SET `status` = '" + status + "' WHERE `id` = '" + id + "'");
            ps.executeUpdate();
        } catch (SQLException | ClassNotFoundException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, ex.getLocalizedMessage());
        } finally {
            closeDb();
        }
    }
    
    // mengupdate data transaksi pada kolom yang dipilih berdasarkan id
    public void updateInventory(String id, String input, String getColumn){
        try {
            String text = getColumn.toLowerCase();
            String column = text.replaceAll("\\b(service|payment|day|laundry)\\b", "type$1");
            openDb();
            ps = c.prepareStatement("UPDATE `transaksi` SET " + column + " = '" + input + "' WHERE `id` = '" + id + "'");
            ps.executeUpdate();
        } catch (SQLException | ClassNotFoundException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, ex.getLocalizedMessage());
        } finally {
            closeDb();
        }
    }
    
    // menghapus data transaksi
    public void deleteInventory(String id){
        try {
            openDb();
            ps = c.prepareStatement("DELETE FROM `transaksi` WHERE `id` = '" + id + "'");
            ps.executeUpdate();
        } catch (SQLException | ClassNotFoundException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, ex.getLocalizedMessage());
        } finally {
            closeDb();
        }
    }
    
    // menampilkan username dan kode akses di halaman Account
    public void showUser(Account account) {
        String username = Login.usernameTxt.getText();
        String code;
        try {
            openDb();
            ps = c.prepareStatement("SELECT `username`, `code` FROM `dataadmin` WHERE `username` = ?");
            ps.setString(1, username);
            rs = ps.executeQuery();
            if (rs.next()) {
                code = rs.getString("code");
                account.setProfile(username, code);
            }
        } catch (SQLException | ClassNotFoundException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, ex.getLocalizedMessage());
        } finally {
            closeDb();
        }
    }
    
    // menampilkan foto berdasarkan username
    public void showFoto(Account account) {
        String username = Login.usernameTxt.getText();
        JLabel imageLabel = account.getFoto();
        try {
            openDb();  
            ps = c.prepareStatement("SELECT `foto` FROM `fotoadmin` WHERE `username` = ?");
            ps.setString(1, username);
            rs = ps.executeQuery();            
            if(rs.next()) {
                byte[] imageData = rs.getBytes("foto");
                format = new ImageIcon(imageData);
                Image mm = format.getImage();
                int width = imageLabel.getWidth();
                int height = imageLabel.getHeight();
                Image img2 = mm.getScaledInstance(width, height, Image.SCALE_SMOOTH);
                ImageIcon image = new ImageIcon(img2);
                imageLabel.setIcon(image);
            }
        } catch (SQLException | ClassNotFoundException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, ex.getLocalizedMessage());
        } finally {
            closeDb();
        }
    }
    
    // menambahkan atau mengupdate foto
    public void foto(String username, byte[] foto) {
        try {
            openDb();
            if (isFotoExists(username)) {
                updateFoto(username, foto);
            } else {
                addFoto(username, foto);
            }
        } catch (SQLException | ClassNotFoundException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, ex.getLocalizedMessage());
        } finally {
            closeDb();
        }
    }
    
    // menambahkan foto
    public void addFoto(String username, byte[] foto) {
        try{
            openDb();
            ps = c.prepareStatement("INSERT INTO `fotoadmin` VALUES (?, ?)");
            if (foto != null) {
                ps.setString(1, username);
                ps.setBytes(2, foto);
                ps.executeUpdate();
            }
        } catch (SQLException | ClassNotFoundException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, ex.getLocalizedMessage());
        } finally {
            closeDb();
        }
    }
    
    // mengupdate foto
    public void updateFoto(String username, byte[] foto) {
        try {
            openDb();
            if(foto != null) {
                String q = "UPDATE `fotoadmin` SET `foto` = ? WHERE `username` = ?";               
                ps = c.prepareStatement(q);                
                ps.setBytes(1, foto);
                ps.setString(2, username);
                ps.execute(); 
                JOptionPane.showMessageDialog(null, "Image has been changed");
            } else {
                String q = "UPDATE `fotoadmin` SET `foto` = ? WHERE `username` = ?";               
                ps = c.prepareStatement(q);                
                ps.setBytes(1, null);
                ps.setString(2, username);
                ps.execute();
                JOptionPane.showMessageDialog(null, "Image has been changed");
            }
        }catch(SQLException | ClassNotFoundException ex) {
            ex.printStackTrace();
        } finally {
            closeDb();
        }
    }
    
    // mengecek apakah foto ada atau tidak
    public boolean isFotoExists(String username) throws SQLException {
        ps = c.prepareStatement("SELECT `foto` FROM `fotoadmin` WHERE `username` = ?");
        ps.setString(1, username);
        rs = ps.executeQuery();

        return rs.next();
    }
    
    // menampilkan jumlah income dan jumlah order di halaman Dashboard
    public void getDetail(Dashboard dashboard){
        String username = usernameTxt.getText();
        String order;
        double price;
        try {
            openDb();
            ps = c.prepareStatement("SELECT SUM(price) AS income, COUNT(*) AS jumlah_data FROM `transaksi` WHERE `username` = ?");
            ps.setString(1, username);
            rs = ps.executeQuery();
            if (rs.next()) {
                price = rs.getDouble("income");
                order = rs.getString("jumlah_data");
                dashboard.getIncome().setText(String.valueOf(price));
                dashboard.getOrder().setText(order);
            }
        } catch (SQLException | ClassNotFoundException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, ex.getLocalizedMessage());
        } finally {
            closeDb();
        }
    }
    
    // menampilkan jumlah status yang pending di halaman Dashboard
    public void getPending(Dashboard dashboard){
        String username = usernameTxt.getText();
        String status;
        try {
            openDb();
            ps = c.prepareStatement("SELECT COUNT(status) AS pending FROM `transaksi` WHERE `status` = 'Pending' AND `username` = ?");
            ps.setString(1, username);
            rs = ps.executeQuery();
            if (rs.next()) {
                status = rs.getString("pending");
                dashboard.getPending().setText(status);
            }
        } catch (SQLException | ClassNotFoundException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, ex.getLocalizedMessage());
        } finally {
            closeDb();
        }
    }
    
    // menampilkan jumlah status yang on progress di halaman Dashboard
    public void getProgress(Dashboard dashboard){
        String username = usernameTxt.getText();
        String status;
        try {
            openDb();
            ps = c.prepareStatement("SELECT COUNT(status) AS progress FROM `transaksi` WHERE `status` = 'On Progress' AND `username` "
                    + "= ?");
            ps.setString(1, username);
            rs = ps.executeQuery();
            if (rs.next()) {
                status = rs.getString("progress");
                dashboard.getProgress().setText(status);
            }
        } catch (SQLException | ClassNotFoundException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, ex.getLocalizedMessage());
        } finally {
            closeDb();
        }
    }
    
    // menampilkan jumlah status yang success di halaman Dashboard
    public void getSuccess(Dashboard dashboard){
        String username = usernameTxt.getText();
        String status;
        try {
            openDb();
            ps = c.prepareStatement("SELECT COUNT(status) AS success FROM `transaksi` WHERE `status` = 'Success' AND `username` = ?");
            ps.setString(1, username);
            rs = ps.executeQuery();
            if (rs.next()) {
                status = rs.getString("success");
                dashboard.getSuccess().setText(status);
            }
        } catch (SQLException | ClassNotFoundException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, ex.getLocalizedMessage());
        } finally {
            closeDb();
        }
    }
    
    // membuka database dan menghubungkannya ke localhost dengan nama database dblaundry
    private static void openDb() throws ClassNotFoundException, SQLException {
        String URL = "jdbc:mysql://localhost:3306/dblaundry";
        String USERNAME = "root";
        String PASSWORD = "";
        Class.forName("com.mysql.cj.jdbc.Driver");
        c = DriverManager.getConnection(URL, USERNAME, PASSWORD);
    }
    
    // menutup database jika sudah tidak terpakai
    private static void closeDb() {
        try {
            if (rs != null) {
                rs.close();
            }
            if (s != null) {
                s.close();
            }
            if (c != null) {
                c.close();
            }
            if (ps != null) {
                ps.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
