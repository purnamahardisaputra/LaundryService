/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package kelas;

import gui.Invoice;
import gui.Transaction;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;



/**
 *
 * @author Purnama Hardi Saputra
 */
public class Result {
    private BufferedImage image;
    private String jam, tanggal;
    
    public Result(){
        currentDate();
    }
    
    // mengakses tanggal dan waktu secara real time
    public void currentDate() {
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        tanggal = now.format(dateFormatter);

        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH-mm-ss");
        jam = now.format(timeFormatter);
    }
    
    // membuat invoice dalam bentuk gambar
    public void generateInvoiceImage(Transaction transaction, Invoice invoice) {
        int x = 20;
        int y = 50;
        int yShift = 10;
        int headerHeight = 15;
        // Write code to generate the invoice content and render it onto a BufferedImage
        // You can use Graphics2D to draw the content onto the image
        this.image = new BufferedImage(270, 350, BufferedImage.TYPE_INT_RGB);
        Graphics2D g2d = this.image.createGraphics();
        g2d.setColor(Color.WHITE);
        g2d.fillRect(0, 0, invoice.setInvoice().getWidth(), invoice.setInvoice().getHeight());
        
        // Draw invoice content onto the image
        g2d.setColor(Color.BLACK);
        g2d.setFont(new Font("Monospaced", Font.PLAIN, 9));
        g2d.drawString("                  INVOICE                  ", x, y); y+=yShift;
        g2d.drawString("-------------------------------------------", x, y); y+=headerHeight;
        g2d.drawString("            LAUNDRY APPLICATION            ", x, y); y+=yShift;
        g2d.drawString("   Jl. DI Panjaitan No.128, Karangreja,    ", x, y); y+=yShift;
        g2d.drawString(" Purwokerto Kidul, Kec. Purwokerto Selatan ", x, y); y+=yShift;
        g2d.drawString("   Kabupaten Banyumas, Jawa Tengah 53147   ", x, y); y+=yShift;
        g2d.drawString("               (0281) 641629               ", x, y); y+=yShift;
        g2d.drawString("-------------------------------------------", x, y); y+=headerHeight;
        g2d.drawString(" ID Transaction   :              " + transaction.getId() + "  " , x, y); y+=yShift;
        g2d.drawString("-------------------------------------------" , x, y); y+=yShift;
        g2d.drawString(" Customer Name    :              " + transaction.getCustomer() + "  " , x, y); y+=yShift;
        g2d.drawString("-------------------------------------------" , x, y); y+=yShift;
        g2d.drawString(" Number of Items  :              " + transaction.getItems() + "  " , x, y); y+=yShift;
        g2d.drawString("-------------------------------------------" , x, y); y+=yShift;
        g2d.drawString(" Type Service     :              " + transaction.getTypeService() + "  " , x, y); y+=yShift;
        g2d.drawString("-------------------------------------------" , x, y); y+=yShift;
        g2d.drawString(" Type Day         :              " + transaction.getTypeDay() + "  " , x, y); y+=yShift;
        g2d.drawString("-------------------------------------------" , x, y); y+=yShift;
        g2d.drawString(" Type Laundry     :              " + transaction.getTypeLaundry() + "  " , x, y); y+=yShift;
        g2d.drawString("-------------------------------------------" , x, y); y+=yShift;
        g2d.drawString(" Type Payment     :              " + transaction.getTypePayment() + "  " , x, y); y+=yShift;
        g2d.drawString("-------------------------------------------" , x, y); y+=yShift;
        g2d.drawString(" Total Price      :              " + transaction.getPrice() + "  " , x, y); y+=headerHeight;
        g2d.drawString("*******************************************" , x, y); y+=yShift;
        g2d.drawString("          THANK YOU FOR SHOPPING           " , x, y); y+=yShift;
        g2d.drawString("*******************************************", x, y); y+=yShift;
        g2d.dispose();
        ImageIcon icon = new ImageIcon(image);
        invoice.setInvoice().setIcon(icon);
    }
    
    // mendownload gambar invoice
    public void downloadImage(Transaction transaction) throws IOException{
        try {
            ImageIO.write(image, "png", new File("D:\\" + transaction.getCustomer() + "_" +
                    tanggal + "_" + jam + "_" + transaction.getId() + ".png"));
            JOptionPane.showMessageDialog(null, "Image has been downloaded", "Download Successed", JOptionPane.INFORMATION_MESSAGE);
        } catch (Exception e) {
            e.printStackTrace();
        }
        
    }
}
