/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

/**
 *
 * @author Hello Kiên
 */
public class Ghi_chu extends javax.swing.JInternalFrame {

    /**
     * Creates new form Ghi_chu
     */
    public Ghi_chu() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();

        setClosable(true);
        setTitle("Note");

        jTextArea1.setColumns(20);
        jTextArea1.setRows(5);
        jTextArea1.setText("Click menu Item\n\nCâu 1: menu item \"quản lý mặt hàng\"\n\nCâu 2: menu item \"quản lý khách hàng\"\n\nCâu 3: menu item \"quản lý bán hàng\" \n     - tab1 \"Bán hàng\" - xử lý bán hàng cho khách hàng,\ndanh sách QLBH.DAT được in trên tab \"Bảng danh sách mua hàng\" \n\n\nCâu 4: menu item \"quản lý bán hàng\"\n     - tab2 \"Bảng danh sách mua hàng\" - sắp xếp danh \nsách Bảng danh sách mua hàng đã lưu trong QLBH.DAT \n     - button sắp xếp theo tên KH, tên MH và mã HĐ\n\nCâu 5: mỗi lần mua hàng hóa đơn sẽ được in ra trong phần \nconsole");
        jScrollPane1.setViewportView(jTextArea1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 497, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(18, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 353, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea jTextArea1;
    // End of variables declaration//GEN-END:variables
}
