/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import Model.HoaDon;
import Model.SPmua;
import Model.KhachHang;
import Model.MatHang;
import Model.QLBH;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Random;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Hello Kiên
 */
public class QuanLyBanHang extends javax.swing.JInternalFrame {

    /**
     * Creates new form QuanLyBanHang
     */
    ArrayList<KhachHang> listKH = new ArrayList<>();
    ArrayList<MatHang> listMH = new ArrayList<>();
    ArrayList<SPmua> listSPmua = new ArrayList<>();
    ArrayList<QLBH> listQLBH = new ArrayList<>();
    ArrayList<HoaDon> listShowHoaDon = new ArrayList<>();
    int index;
    
    public QuanLyBanHang() {
        initComponents();
        
        ReadFileQLBH();
        //
//        sortByMaHD();
        
        this.fillTableSP();
        this.fillCBKhachHang();
        this.fillTableDSBH();
        
        //mã hóa đơn
        Random rd = new Random();
        int a = rd.nextInt(1000000);
        txtMahoadon.setText("HD" + a);
        txtMahoadon.disable();
    }
    
    private void fillTableSP() {
        ReadFile_MH_DAT();
        DefaultTableModel model = (DefaultTableModel) tblMatHang.getModel();
        model.setRowCount(0);
        for (MatHang mh : listMH) {
            model.addRow(new Object[]{mh.getMaHang(),mh.getTenHang(),mh.getGiaBan()});
        }
        tblMatHang.setRowSelectionInterval(0, 0);
    }
    
    private void fillTableSPmua() {
        DefaultTableModel model = (DefaultTableModel) tblSPmua.getModel();
        model.setRowCount(0);
        for (SPmua sp : listSPmua) {
            model.addRow(new Object[]{sp.getMaHang(),sp.getTenHang(),sp.getSoLuong(),sp.getTongGia()});
        }
    }
    
    private void fillCBKhachHang() {
        ReadFile_KH_DAT();
        DefaultComboBoxModel cbmodel = (DefaultComboBoxModel) cbKhachHang.getModel();
        cbmodel.removeAllElements();
        for (KhachHang kh : listKH) {
            cbmodel.addElement(kh.getHoTen());
        }
    }
    
    
    void ReadFile_MH_DAT(){
        try {
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream("MH.DAT"));
            listMH = (ArrayList<MatHang>) ois.readObject();
            ois.close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Lỗi mở file");
        }
    }
    
    void ReadFile_KH_DAT(){
        try {
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream("KH.DAT"));
            listKH = (ArrayList<KhachHang>) ois.readObject();
            ois.close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Lỗi mở file");
        }
    }
    
    void WriteFileQLBH(){
        try {
            String maHD = txtMahoadon.getText();
            String tenKhachHang = cbKhachHang.getSelectedItem()+"";
            int maKH = 0;
            for(KhachHang kh : listKH){
                if(kh.getHoTen().equals(tenKhachHang)){
                    maKH = kh.getMaKH();
                }
            }
            double tongTien = Double.parseDouble(txtTongTien.getText());
            
            for(SPmua sp : listSPmua){
                listQLBH.add(new QLBH(maHD, maKH, tenKhachHang, sp.getMaHang(), sp.getTenHang(), sp.getSoLuong(), sp.getTongGia()));
            }
//            listQLBH.forEach(kh -> System.out.println(kh));
            
            
            listShowHoaDon.clear();
            for(SPmua sp : listSPmua){
                listShowHoaDon.add(new HoaDon(maHD, tenKhachHang, sp.getMaHang(), sp.getTenHang(), sp.getSoLuong(), sp.getTongGia(), tongTien));
            }
            System.out.println("\n");
            System.out.println("===========================Hóa đơn===========================");
            System.out.println("| -----");
            System.out.println("| Mã hóa đơn: "+listShowHoaDon.get(0).getMaHD());
            System.out.println("| Tên khách hàng: "+listShowHoaDon.get(0).getTenKH());
            System.out.println("| -----");
            System.out.println("| Sản phẩm đã mua:");
            listShowHoaDon.forEach(hd -> System.out.println(hd));
            System.out.println("| -----");
            System.out.println("| Thành tiền: " + tongTien);
            System.out.println("| -----");
            System.out.println("=============================================================");
            
            JOptionPane.showMessageDialog(this, "Hóa đơn được in ra trên cửa sổ console!");



            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("QLBH.DAT"));
            oos.writeObject(listQLBH);
            oos.flush();
            oos.close();
            JOptionPane.showMessageDialog(this, "Đã lưu hóa đơn vào file QLBH.DAT");
            
            
           
            
            fillTableDSBH();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Lỗi ghi file");
        }
    }
    
    void ReadFileQLBH(){
        try {
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream("QLBH.DAT"));
            listQLBH = (ArrayList<QLBH>) ois.readObject();
            ois.close();
            
//            JOptionPane.showMessageDialog(this, "Open file QLBH.DAT");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Lỗi mở file QLBH");
        }
    }
    
    void fillTableDSBH(){
        DefaultTableModel model = (DefaultTableModel) tblDSmuahang.getModel();
        model.setRowCount(0);
        for(QLBH ql : listQLBH){
            model.addRow(new Object[]{ql.getMaHD(), ql.getMaKH(), ql.getTenKH(), ql.getMaHang(), ql.getTenHang(), ql.getSoLuong(), ql.getTongGia()});
        }
    }
    
    
    public void tong_tien() {
        float tong_tien = 0;
        for (SPmua sp : listSPmua) {
            tong_tien += sp.getTongGia();
        }
        txtTongTien.setText(tong_tien+"");
    }
    
    void sortByMaHD(){
        listQLBH.sort((Comparator.comparing(qlbh -> ((QLBH)qlbh).getMaHD())));
        fillTableDSBH();
        try {
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("QLBH.DAT"));
            oos.writeObject(listQLBH);
            oos.flush();
            oos.close();
            fillTableDSBH();
        } catch (Exception e) {
            System.out.println("fall");
        }
    }
    
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblMatHang = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        cbKhachHang = new javax.swing.JComboBox<>();
        jLabel2 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        txtMahoadon = new javax.swing.JTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblSPmua = new javax.swing.JTable();
        btnThanhtoan = new javax.swing.JButton();
        btnLammoi = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        txtTongTien = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        tblDSmuahang = new javax.swing.JTable();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();

        setClosable(true);
        setTitle("Quản lý bán hàng");

        jTabbedPane1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTabbedPane1MouseClicked(evt);
            }
        });

        tblMatHang.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "Mã hàng", "Tên hàng", "Giá bán"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblMatHang.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblMatHangMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblMatHang);

        jLabel1.setText("Chọn mặt hàng:");

        jLabel2.setText("Chọn khách hàng:");

        jLabel3.setText("Mã hóa đơn:");

        tblSPmua.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Mã hàng", "Tên hàng", "Số lượng", "Tổng giá"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblSPmua.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblSPmuaMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tblSPmua);

        btnThanhtoan.setText("Thanh toán");
        btnThanhtoan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThanhtoanActionPerformed(evt);
            }
        });

        btnLammoi.setText("Làm mới");
        btnLammoi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLammoiActionPerformed(evt);
            }
        });

        jLabel4.setText("Tổng tiền: ");

        jLabel5.setText("** Bấm thanh toán hóa đơn sẽ đc in ra trong console");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(36, 36, 36)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel5)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addGroup(jPanel3Layout.createSequentialGroup()
                            .addComponent(jLabel4)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(txtTongTien, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnLammoi)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(btnThanhtoan))
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3)
                            .addComponent(txtMahoadon, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 497, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtMahoadon, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btnThanhtoan)
                        .addComponent(btnLammoi))
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel4)
                        .addComponent(txtTongTien, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel5)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabel8.setText("* Click table mặt hàng để chọn mặt hàng cần mua");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(40, 40, 40)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 293, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1)
                    .addComponent(jLabel8))
                .addGap(37, 37, 37)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addComponent(cbKhachHang, javax.swing.GroupLayout.PREFERRED_SIZE, 173, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(51, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cbKhachHang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel8)
                .addGap(18, 18, 18)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Bán hàng", jPanel1);

        jPanel4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPanel4MouseClicked(evt);
            }
        });

        tblDSmuahang.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "MaHD", "Mã khách hàng", "Tên khách hàng", "Mã hàng", "Tên hàng", "Số lượng", "Tổng giá"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane4.setViewportView(tblDSmuahang);

        jButton1.setText("Sắp xếp theo tên khách hàng");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("Sắp xếp theo tên mặt hàng");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setText("Sắp xếp theo mã hóa đơn");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 501, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jButton3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton1)))
                .addContainerGap(65, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(37, 37, 37)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(jButton2)
                    .addComponent(jButton3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 359, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(49, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Bảng danh sách mua hàng ", jPanel4);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jTabbedPane1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTabbedPane1MouseClicked

    }//GEN-LAST:event_jTabbedPane1MouseClicked

    private void jPanel4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel4MouseClicked

    }//GEN-LAST:event_jPanel4MouseClicked

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        sortByMaHD();
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        listQLBH.sort((Comparator.comparing(qlbh -> ((QLBH)qlbh).getTenHang())));
        fillTableDSBH();
        try {
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("QLBH.DAT"));
            oos.writeObject(listQLBH);
            oos.flush();
            oos.close();
            //            JOptionPane.showMessageDialog(this, "Đã lưu hóa đơn vào file QLBH.DAT");
            fillTableDSBH();
        } catch (Exception e) {
            System.out.println("fall");
        }

    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        listQLBH.sort((Comparator.comparing(qlbh -> ((QLBH)qlbh).getTenKH())));
        fillTableDSBH();
        try {
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("QLBH.DAT"));
            oos.writeObject(listQLBH);
            oos.flush();
            oos.close();
            //            JOptionPane.showMessageDialog(this, "Đã lưu hóa đơn vào file QLBH.DAT");
            fillTableDSBH();
        } catch (Exception e) {
            System.out.println("fall");
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void btnLammoiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLammoiActionPerformed
        DefaultTableModel model = (DefaultTableModel) tblSPmua.getModel();
        model.setRowCount(0);
        txtTongTien.setText("0");
        cbKhachHang.setSelectedIndex(0);
        listSPmua.clear();
        //lam lai mã h dơn
        Random rd = new Random();
        int a = rd.nextInt(1000000);
        txtMahoadon.setText("HD" + a);
        txtMahoadon.disable();
    }//GEN-LAST:event_btnLammoiActionPerformed

    private void btnThanhtoanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThanhtoanActionPerformed
        try {
            if(listSPmua.size()<=0){
                JOptionPane.showMessageDialog(this, "Bạn chưa mua sản phẩm nào");
                return;
            }
            WriteFileQLBH();
            //            jTabbedPane1.setSelectedIndex(1);
            fillTableDSBH();

            DefaultTableModel model = (DefaultTableModel) tblSPmua.getModel();
            model.setRowCount(0);
            txtTongTien.setText("0");
            cbKhachHang.setSelectedIndex(0);
            listSPmua.clear();
            //lam lai mã h dơn
            Random rd = new Random();
            int a = rd.nextInt(1000000);
            txtMahoadon.setText("HD" + a);
            txtMahoadon.disable();
        } catch (Exception e) {
            System.out.println("fall");
        }
    }//GEN-LAST:event_btnThanhtoanActionPerformed

    private void tblSPmuaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblSPmuaMouseClicked
        try {
            if (evt.getClickCount() >= 2 && index>-1) {
                int ck = JOptionPane.showConfirmDialog(this, "Bạn muốn hủy bán sản phẩm này ?");
                if (ck != JOptionPane.YES_OPTION) {
                    return;
                }
                index = tblSPmua.getSelectedRow();
                listSPmua.remove(index);
                this.fillTableSPmua();
                this.tong_tien();
            }
        } catch (Exception e) {
            System.out.println("fall");
        }

    }//GEN-LAST:event_tblSPmuaMouseClicked

    private void tblMatHangMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblMatHangMouseClicked
        if(listSPmua.size()>=10){
            JOptionPane.showInputDialog(this, "Không thể mua quá 10 sản phẩm 1 lần");
            return;
        }
        String soluong = JOptionPane.showInputDialog(this, "nhập số lượng sản phẩm cần mua");
        int b = 0;
        try {
            b = Integer.parseInt(soluong);
            if (b <= 0) {
                JOptionPane.showMessageDialog(this, "Số lượng phải lớn hơn 0");
                return;
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Số lượng phải là số");
            return;
        }
        //add vao list
        index = tblMatHang.getSelectedRow();
        MatHang mh = listMH.get(index);
        int maHang = mh.getMaHang();
        String tenHang = mh.getTenHang();
        int so_luong = Integer.parseInt(soluong);
        if(listSPmua.size()>0){
            for(SPmua sp : listSPmua){
                if(sp.getMaHang() == maHang){
                    int index1 = listSPmua.indexOf(sp);
                    //                    System.out.println(index1);
                    int SL = sp.getSoLuong() + so_luong;
                    double tongGia = mh.getGiaBan()*so_luong;
                    listSPmua.set(index1, new SPmua(maHang, tenHang, SL, tongGia));
                    this.fillTableSPmua();
                    this.tong_tien();
                    return;
                }
            }       double tongGia = mh.getGiaBan()*so_luong;
            listSPmua.add(new SPmua(maHang, tenHang, so_luong, tongGia));
            this.fillTableSPmua();
            this.tong_tien();
            return;
        }else{
            double tongGia = mh.getGiaBan()*so_luong;
            listSPmua.add(new SPmua(maHang, tenHang, so_luong, tongGia));
            this.fillTableSPmua();
            this.tong_tien();
        }

    }//GEN-LAST:event_tblMatHangMouseClicked
    
    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnLammoi;
    private javax.swing.JButton btnThanhtoan;
    private javax.swing.JComboBox<String> cbKhachHang;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTable tblDSmuahang;
    private javax.swing.JTable tblMatHang;
    private javax.swing.JTable tblSPmua;
    private javax.swing.JTextField txtMahoadon;
    private javax.swing.JTextField txtTongTien;
    // End of variables declaration//GEN-END:variables
}
