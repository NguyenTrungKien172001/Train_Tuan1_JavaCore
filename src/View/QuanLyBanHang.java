/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

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
    int index;
    
    public QuanLyBanHang() {
        initComponents();
        
        ReadFileQLBH();
        //
        sortByMaHD();
        
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
//            listQLBH.clear();
            for(SPmua sp : listSPmua){
                listQLBH.add(new QLBH(maHD, maKH, tenKhachHang, sp.getMaHang(), sp.getTenHang(), sp.getSoLuong(), sp.getTongGia()));
            }
            listQLBH.forEach(kh -> System.out.println(kh));
            
            
            
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
        txtTongTien.setText(tong_tien + " $");
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
        jPanel2 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        txtHDCT = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        txtTenKHtab2 = new javax.swing.JTextField();
        jScrollPane3 = new javax.swing.JScrollPane();
        tblSPmuatab2 = new javax.swing.JTable();
        jLabel7 = new javax.swing.JLabel();
        txtTongTientab2 = new javax.swing.JTextField();
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

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
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
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 497, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(22, Short.MAX_VALUE))
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
                .addContainerGap(12, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(13, 13, 13)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 293, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel1))
                        .addGap(37, 37, 37)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(cbKhachHang, javax.swing.GroupLayout.PREFERRED_SIZE, 173, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cbKhachHang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 36, Short.MAX_VALUE)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jTabbedPane1.addTab("Bán hàng", jPanel1);

        jLabel5.setText("Mã hóa đơn:");

        jLabel6.setText("Tên khách hàng:");

        tblSPmuatab2.setModel(new javax.swing.table.DefaultTableModel(
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
        tblSPmuatab2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblSPmuatab2MouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(tblSPmuatab2);

        jLabel7.setText("Tổng tiền: ");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 497, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(24, Short.MAX_VALUE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel5)
                            .addComponent(txtHDCT, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel6)
                            .addComponent(txtTenKHtab2, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(79, 79, 79))))
            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel2Layout.createSequentialGroup()
                    .addGap(24, 24, 24)
                    .addComponent(jLabel7)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(txtTongTientab2, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(337, Short.MAX_VALUE)))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(39, 39, 39)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(jLabel6))
                .addGap(10, 10, 10)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtHDCT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtTenKHtab2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(43, 43, 43)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(197, Short.MAX_VALUE))
            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel2Layout.createSequentialGroup()
                    .addGap(295, 295, 295)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel7)
                        .addComponent(txtTongTientab2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addContainerGap(142, Short.MAX_VALUE)))
        );

        jTabbedPane1.addTab("Hóa đơn", jPanel2);

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

        jButton3.setText("Sắp xếp theo mã khách hàng");
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
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane4)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jButton3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton1)))
                .addContainerGap(24, Short.MAX_VALUE))
        );

        jPanel4Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {jButton1, jButton2, jButton3});

        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(jButton2)
                    .addComponent(jButton3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 359, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(45, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Bảng danh sách mua hàng ", jPanel4);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 554, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 483, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void tblMatHangMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblMatHangMouseClicked
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

    private void btnLammoiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLammoiActionPerformed
        DefaultTableModel model = (DefaultTableModel) tblSPmua.getModel();
        model.setRowCount(0);
        txtTongTien.setText("0 $");
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
            txtHDCT.setText(txtMahoadon.getText());
            WriteFileQLBH();
            jTabbedPane1.setSelectedIndex(1);
            fillTableDSBH();
            
            DefaultTableModel model = (DefaultTableModel) tblSPmua.getModel();
            model.setRowCount(0);
            txtTongTien.setText("0 $");
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

    private void tblSPmuatab2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblSPmuatab2MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_tblSPmuatab2MouseClicked

    private void jTabbedPane1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTabbedPane1MouseClicked
//       if(jTabbedPane1.getSelectedIndex()==2){
//           System.out.println("tab3");
//           fillTableDSBH();
//       }if(jTabbedPane1.getSelectedIndex()==0){
//           System.out.println("tab1");
//       }if(jTabbedPane1.getSelectedIndex()==1){
//           System.out.println("tab2");
//       }
    }//GEN-LAST:event_jTabbedPane1MouseClicked

    private void jPanel4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel4MouseClicked
        
    }//GEN-LAST:event_jPanel4MouseClicked

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

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        listQLBH.sort(((o1, o2) -> Integer.compare(o1.getMaKH(), o2.getMaKH())));
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
    }//GEN-LAST:event_jButton3ActionPerformed


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
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTable tblDSmuahang;
    private javax.swing.JTable tblMatHang;
    private javax.swing.JTable tblSPmua;
    private javax.swing.JTable tblSPmuatab2;
    private javax.swing.JTextField txtHDCT;
    private javax.swing.JTextField txtMahoadon;
    private javax.swing.JTextField txtTenKHtab2;
    private javax.swing.JTextField txtTongTien;
    private javax.swing.JTextField txtTongTientab2;
    // End of variables declaration//GEN-END:variables
}
