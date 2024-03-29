/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import Model.KhachHang;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Hello Kiên
 */
public class QuanLyKhachHang extends javax.swing.JInternalFrame {

    /**
     * Creates new form QuanLyKhachHang
     */
    ArrayList<KhachHang> listKH = new ArrayList<>();
    DefaultTableModel model;
    int index;
    
    
    public QuanLyKhachHang() {
        initComponents();
        
        jTabbedPane1.setSelectedIndex(1);
        btnThem.setEnabled(false);
        txtMaKH.setEnabled(false);
        model = (DefaultTableModel) tblQLKH.getModel();
        
        this.ReadFile();
        fillTable();
        
    }
    
    void ReadFile(){
        try {
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream("KH.DAT"));
            listKH = (ArrayList<KhachHang>) ois.readObject();
            ois.close();
            if(listKH.size()>0){
                fillTable();
                index=0;
                KhachHang kh = listKH.get(index);
                writeForm(kh);
            }
            JOptionPane.showMessageDialog(this, "Open file KH.DAT");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Lỗi mở file");
        }
    }
    
    void WriteFile(){
        try {
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("KH.DAT"));
            oos.writeObject(listKH);
            oos.flush();
            oos.close();
            JOptionPane.showMessageDialog(this, "Đã lưu thay đổi vào file KH.DAT");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Lỗi ghi file");
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
        jLabel1 = new javax.swing.JLabel();
        txtMaKH = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        txtHoten = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        txtSDT = new javax.swing.JTextField();
        btnThem = new javax.swing.JButton();
        btnSua = new javax.swing.JButton();
        btnXoa = new javax.swing.JButton();
        btnClear = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtDiachi = new javax.swing.JTextArea();
        jLabel7 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblQLKH = new javax.swing.JTable();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();

        setClosable(true);
        setTitle("Quản lý khách hàng");

        jLabel1.setText("Mã khách hàng:");

        txtMaKH.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtMaKHActionPerformed(evt);
            }
        });

        jLabel3.setText("Địa chỉ:");

        jLabel2.setText("Họ tên:");

        txtHoten.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtHotenActionPerformed(evt);
            }
        });

        jLabel4.setText("Số điện thoại:");

        txtSDT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtSDTActionPerformed(evt);
            }
        });

        btnThem.setText("Thêm");
        btnThem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemActionPerformed(evt);
            }
        });

        btnSua.setText("Sửa");
        btnSua.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSuaActionPerformed(evt);
            }
        });

        btnXoa.setText("Xóa");
        btnXoa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXoaActionPerformed(evt);
            }
        });

        btnClear.setText("Clear");
        btnClear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnClearActionPerformed(evt);
            }
        });

        txtDiachi.setColumns(20);
        txtDiachi.setRows(5);
        jScrollPane1.setViewportView(txtDiachi);

        jLabel7.setText("* Bấm clear để được mở khóa chức năng thêm");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel7)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(btnThem)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnSua)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnXoa)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnClear)
                        .addContainerGap(282, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addComponent(txtMaKH, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(txtHoten, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel4)
                            .addComponent(txtSDT, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(64, 64, 64))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtHoten, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtMaKH, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(28, 28, 28)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtSDT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(37, 37, 37)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnThem)
                            .addComponent(btnSua)
                            .addComponent(btnXoa)
                            .addComponent(btnClear))
                        .addGap(83, 83, 83)
                        .addComponent(jLabel7)
                        .addGap(35, 35, 35))))
        );

        jTabbedPane1.addTab("Cập nhật", jPanel1);

        tblQLKH.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Mã KH", "Họ tên", "Địa chỉ", "SĐT"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblQLKH.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblQLKHMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tblQLKH);

        jLabel5.setText("* Double click in table show form");

        jLabel6.setText("** Mọi thao tác thêm, sửa, xóa dữ liệu sẽ được ghi vào file");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 529, Short.MAX_VALUE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel5)
                            .addComponent(jLabel6))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(45, 45, 45)
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel6)
                .addContainerGap(77, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Danh sách", jPanel2);

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

    private void txtMaKHActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtMaKHActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtMaKHActionPerformed

    private void txtHotenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtHotenActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtHotenActionPerformed

    private void txtSDTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtSDTActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtSDTActionPerformed

    private void btnThemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemActionPerformed
        try {
            if (txtMaKH.getText().equals("")) {
                JOptionPane.showMessageDialog(this, "Hãy nhập mã khách hàng");
                txtMaKH.requestFocus();
                return;
            }

            int maKH;
            try {
                maKH = Integer.parseInt(txtMaKH.getText());
                //0
                if (maKH < 0) {
                    JOptionPane.showMessageDialog(this, "Nhập mã khách hàng hàng là số dương");
                    txtMaKH.requestFocus();
                    return;
                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, "Nhập mã khách hàng là số nguyên!");
                txtMaKH.requestFocus();
                return;
            }

            if (txtMaKH.getText().length()!=5) {
                JOptionPane.showMessageDialog(this, "Nhập mã khách hàng có 5 chữ số!");
                txtMaKH.requestFocus();
                return;
            }

            for(KhachHang kh : listKH){
                if(txtMaKH.getText().equals(kh.getMaKH()+"")){
                    JOptionPane.showMessageDialog(this, "Không được nhập trùng mã khách hàng!");
                    txtMaKH.requestFocus();
                    return;
                }
            }

            if (txtHoten.getText().equals("")) {
                JOptionPane.showMessageDialog(this, "Hãy nhập họ tên");
                txtHoten.requestFocus();
                return;
            }
            
            if (txtDiachi.getText().equals("")) {
                JOptionPane.showMessageDialog(this, "Hãy nhập địa chỉ");
                txtDiachi.requestFocus();
                return;
            }

            if (txtSDT.getText().equals("")) {
                JOptionPane.showMessageDialog(this, "Hãy nhập số điện thoại");
                txtSDT.requestFocus();
                return;
            }
            if(!txtSDT.getText().startsWith("0")){
                JOptionPane.showMessageDialog(this, "Số điện thoại phải bắt đầu bằng số 0");
                txtSDT.requestFocus();
                return;
            }
            int SDT;
            try {
                SDT = Integer.parseInt(txtSDT.getText());

                if (SDT<0) {
                    JOptionPane.showMessageDialog(this, "Số điện thoại là số dương");
                    txtSDT.requestFocus();
                    return;
                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, "Nhập SDT là số!");
                txtSDT.requestFocus();
                return;
            }
            if(txtSDT.getText().length()!=10){
                JOptionPane.showMessageDialog(this, "Số điện thoại đủ 10 chữ số");
                txtSDT.requestFocus();
                return;
            }

            addForm();
            clearForm();
            fillTable();
            JOptionPane.showMessageDialog(this, "Thêm thành công");
            jTabbedPane1.setSelectedIndex(1);
            WriteFile();

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Thêm thất bại");
            e.fillInStackTrace();
        }
    }//GEN-LAST:event_btnThemActionPerformed

    private void btnSuaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSuaActionPerformed
        try {
            index = tblQLKH.getSelectedRow();
            if (index >= 0) {
                if (txtHoten.getText().equals("")) {
                    JOptionPane.showMessageDialog(this, "Hãy nhập họ tên");
                    txtHoten.requestFocus();
                    return;
                }

                if (txtDiachi.getText().equals("")) {
                    JOptionPane.showMessageDialog(this, "Hãy nhập địa chỉ");
                    txtDiachi.requestFocus();
                    return;
                }

                if (txtSDT.getText().equals("")) {
                    JOptionPane.showMessageDialog(this, "Hãy nhập số điện thoại");
                    txtSDT.requestFocus();
                    return;
                }
                if(!txtSDT.getText().startsWith("0")){
                    JOptionPane.showMessageDialog(this, "Số điện thoại phải bắt đầu bằng số 0");
                    txtSDT.requestFocus();
                    return;
                }
                int SDT;
                try {
                    SDT = Integer.parseInt(txtSDT.getText());

                    if (SDT<0) {
                        JOptionPane.showMessageDialog(this, "Số điện thoại là số dương");
                        txtSDT.requestFocus();
                        return;
                    }
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(this, "Nhập SDT là số!");
                    txtSDT.requestFocus();
                    return;
                }
                if(txtSDT.getText().length()!=10){
                    JOptionPane.showMessageDialog(this, "Số điện thoại đủ 10 chữ số");
                    txtSDT.requestFocus();
                    return;
                }
                updateForm();
                clearForm();
                fillTable();
                JOptionPane.showMessageDialog(this, "Cập nhật thành công");
                jTabbedPane1.setSelectedIndex(1);
                WriteFile();
            } else {
                JOptionPane.showMessageDialog(this, "Chọn dữ liệu cần cập nhật trên bảng");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Cập nhật thất bại");
        }
    }//GEN-LAST:event_btnSuaActionPerformed

    private void btnXoaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoaActionPerformed
        try {
            index = tblQLKH.getSelectedRow();
            System.out.println(index);
            if (index >= 0) {
                int check = JOptionPane.showConfirmDialog(this, "Bạn muốn xóa dữ liệu này?");
                if (check != JOptionPane.YES_OPTION) {
                    return;
                }
                delete();
                fillTable();
                clearForm();
                JOptionPane.showMessageDialog(this, "Xóa thành công");
                jTabbedPane1.setSelectedIndex(1);
                WriteFile();
            } else {
                JOptionPane.showMessageDialog(this, "Chọn dữ liệu cần xóa trên bảng");
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Xóa thất bại");
            e.printStackTrace();
        }
    }//GEN-LAST:event_btnXoaActionPerformed

    private void btnClearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnClearActionPerformed
        clearForm();
    }//GEN-LAST:event_btnClearActionPerformed

    private void tblQLKHMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblQLKHMouseClicked
        try {
            index = tblQLKH.getSelectedRow();
            if (evt.getClickCount()==2) {
                KhachHang kh = listKH.get(index);
                writeForm(kh);
                jTabbedPane1.setSelectedIndex(0);
                btnSua.setEnabled(true);
                btnXoa.setEnabled(true);
                btnThem.setEnabled(false);
                txtMaKH.setEnabled(false);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_tblQLKHMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnClear;
    private javax.swing.JButton btnSua;
    private javax.swing.JButton btnThem;
    private javax.swing.JButton btnXoa;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTable tblQLKH;
    private javax.swing.JTextArea txtDiachi;
    private javax.swing.JTextField txtHoten;
    private javax.swing.JTextField txtMaKH;
    private javax.swing.JTextField txtSDT;
    // End of variables declaration//GEN-END:variables
    
    private void fillTable() {
        model.setRowCount(0);
        for (KhachHang kh : listKH) {
            model.addRow(new Object[]{kh.getMaKH(),kh.getHoTen(),kh.getDiaChi(),kh.getSoDT()});
        }
    }

    private void writeForm(KhachHang kh) {
        txtMaKH.setText(kh.getMaKH()+"");
        txtHoten.setText(kh.getHoTen());
        txtDiachi.setText(kh.getDiaChi());
        txtSDT.setText(kh.getSoDT()+"");
        tblQLKH.setRowSelectionInterval(index, index);//table chon index
    }
    
    private void addForm() {
        int maKhachHang = Integer.parseInt(txtMaKH.getText());
        String hoTen = txtHoten.getText();
        String diaChi = txtDiachi.getText();
        String SDT = txtSDT.getText();
        KhachHang kh = new KhachHang(maKhachHang, hoTen, diaChi, SDT);
        listKH.add(kh);
    }
    
    private void updateForm() {
        int maKhachHang = Integer.parseInt(txtMaKH.getText());
        String hoTen = txtHoten.getText();
        String diaChi = txtDiachi.getText();
        String SDT = txtSDT.getText();
        KhachHang kh = new KhachHang(maKhachHang, hoTen, diaChi, SDT);
        
        index = tblQLKH.getSelectedRow();
        listKH.set(index, kh);
    }

    private void clearForm() {
        txtMaKH.setText("");
        txtHoten.setText("");
        txtDiachi.setText("");
        txtSDT.setText("");
        this.index = -1;
        btnSua.setEnabled(false);
        btnXoa.setEnabled(false);
        btnThem.setEnabled(true);
        txtMaKH.setEnabled(true);
    }
    
    private void delete(){
        index = tblQLKH.getSelectedRow();
        listKH.remove(index);
    }

}
