/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package bt02_1112301;

import bt02_1112301.Dict.DictAbstract;
import bt02_1112301.Dict.Updateable;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.text.DecimalFormat;
import java.util.Iterator;
import java.util.SortedMap;
import java.util.TreeMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ButtonGroup;
import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.ListModel;
import javax.swing.Timer;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 *
 * @author Nguyễn Chiến Thắng
 */




public class FrameDictionary extends javax.swing.JFrame implements Updateable{
    ButtonGroup grpDictType = new ButtonGroup();
    //ModelDictionary modelDictionary;
    
    
    //Frame loading
    JFrame frameLoading = new JFrame("Frame loading ...");
    JLabel lblLoadingPercent = new JLabel("    (0%) Đang load dữ liệu, vui lòng chờ ... ");
    
    SortedMap<String,String> listEnViSorted = new TreeMap<String,String>();
    SortedMap<String,String> listViEnSorted = new TreeMap<String,String>();
    
    DefaultListModel<String> modelEnVi = new DefaultListModel<String>();
    DefaultListModel<String> modelViEn = new DefaultListModel<String>();
    
    /**
     * Creates new form FrameDictionary
     */
    public FrameDictionary() {
        initComponents();
        
        initFrameLoading();    
        

        //Add radio to groupp
        grpDictType.add(rdoAnhViet);
        grpDictType.add(rdoVietAnh);
        
        

        //Disable edit word mean
        txtWordMean.setEditable(false);
        
        //Load data
        new Thread(new Runnable() {

            @Override
            public void run() {
               load("Viet_Anh.xml", 0, 50, listViEnSorted, modelViEn);
               load("Anh_Viet.xml", 50, 50, listEnViSorted, modelEnVi);
               lstWords.setModel(modelEnVi);
               rdoAnhViet.setSelected(true);
               lstWords.ensureIndexIsVisible(50);
               lstWords.ensureIndexIsVisible(0);
            }
        }).start();
    }
    
    public DefaultListModel getDictModel(){
        if(isEnViSelected())
            return modelEnVi;
        else 
            return modelViEn;
    }
    
    public SortedMap<String,String> getDict(){
        if(isEnViSelected())
            return listEnViSorted;
        else 
            return listViEnSorted;
    }
    
    
    public void load(String fileName, int startPercent, int maxPercent, SortedMap<String,String> listMap, DefaultListModel<String> model){
        File file = new File(fileName);
        DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory
                .newInstance();
        DocumentBuilder documentBuilder;
        Document document ;
        try {
            documentBuilder = documentBuilderFactory.newDocumentBuilder();
            document = documentBuilder.parse(file);
            NodeList list = document.getElementsByTagName("record");
            //list.item(index)
            int size = list.getLength();
            for(int i = 0; i < list.getLength(); i++){
                Node node = list.item(i);
                Element ele = (Element)node;
                String word = ele.getElementsByTagName("word").item(0).getTextContent();
                String mean = ele.getElementsByTagName("meaning").item(0).getTextContent();
                listMap.put(word, mean);
                model.addElement(word);
                if(i%20 == 0 || i == size - 1){
                    int tmp = i + 1;
                    float percent = startPercent + (float)tmp/size*100/(100/maxPercent);
                    //if(i == size - 2)
                    //    percent = 50f;
                    //DecimalFormat df = new DecimalFormat("###.##");
                    updatePercentLoaded(percent);
                }
            }
            
//            Iterator<String> iter = listSorted.keySet().iterator();
//            while(iter.hasNext()){
//                String key = iter.next();
//                listLinked.add(key);
//            }
        } catch (Exception ex) {
            Logger.getLogger(ModelDictionary.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    
    //Khởi tạo loading
    private void initFrameLoading(){
        Dimension windowSize =  java.awt.Toolkit.getDefaultToolkit().getScreenSize();
        
        frameLoading.setSize(new Dimension(300, 70));
        frameLoading.setAlwaysOnTop(true);
        frameLoading.setLocation(windowSize.width/2 - frameLoading.getWidth()/2, windowSize.height/2 - frameLoading.getHeight()/2 - 100);
        frameLoading.setUndecorated(true); // Remove title bar
        frameLoading.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frameLoading.getContentPane().add(lblLoadingPercent);
        
        //frame.pack();
        frameLoading.setVisible(true);
    }
    
    @Override
    public void updatePercentLoaded(final float percent){
        new Thread(new Runnable() {

            @Override
            public void run() {
                if(percent == 100f){
                    FrameDictionary.this.setVisible(true);
                    FrameDictionary.this.frameLoading.setVisible(false);
                    
                }else
                    lblLoadingPercent.setText("    ("+percent+"%) Đang load dữ liệu, vui lòng chờ ... ");
            }
        }).start();
        
    }
    
    public void search(){
        int indexSelected = lstWords.getSelectedIndex();
        
        //Kiểm tra có chọn 1 từ hay không
        if(lstWords.getSelectedIndex() < 0)
            return;
        DefaultListModel model = getDictModel();
        
        String word = model.get(indexSelected).toString();
        txtWordMean.setText(getDict().get(word));
        
        
    }
    
    public boolean isEnViSelected(){
        return grpDictType.isSelected(rdoAnhViet.getModel());
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        rdoAnhViet = new javax.swing.JRadioButton();
        rdoVietAnh = new javax.swing.JRadioButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        lstWords = new javax.swing.JList();
        jScrollPane2 = new javax.swing.JScrollPane();
        txtWordMean = new javax.swing.JTextArea();
        txtSearch = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);

        rdoAnhViet.setText("Anh-Việt");
        rdoAnhViet.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                rdoAnhVietItemStateChanged(evt);
            }
        });

        rdoVietAnh.setText("Việt-Anh");
        rdoVietAnh.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                rdoVietAnhItemStateChanged(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(rdoAnhViet)
                .addGap(18, 18, 18)
                .addComponent(rdoVietAnh))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(rdoAnhViet)
                .addComponent(rdoVietAnh))
        );

        lstWords.setModel(new javax.swing.AbstractListModel() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public Object getElementAt(int i) { return strings[i]; }
        });
        lstWords.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        lstWords.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lstWordsMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(lstWords);

        txtWordMean.setColumns(20);
        txtWordMean.setRows(5);
        jScrollPane2.setViewportView(txtWordMean);

        txtSearch.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtSearchKeyReleased(evt);
            }
        });

        jLabel1.setText("Nghĩa của từ:");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(20, 20, 20))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(txtSearch, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 166, Short.MAX_VALUE))
                        .addGap(6, 6, 6)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 390, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(0, 5, Short.MAX_VALUE)
                        .addComponent(txtSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 356, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void rdoAnhVietItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_rdoAnhVietItemStateChanged
        //lstWords.setSelectedIndex(50);
        //lstWords.setSelectedIndex(0);
        lstWords.setModel(modelEnVi);
        lstWords.ensureIndexIsVisible(50);
        lstWords.ensureIndexIsVisible(0);
        search();
    }//GEN-LAST:event_rdoAnhVietItemStateChanged

    private void rdoVietAnhItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_rdoVietAnhItemStateChanged
        //lstWords.setSelectedIndex(50);
        //lstWords.setSelectedIndex(0);
        lstWords.setModel(modelViEn);
        lstWords.ensureIndexIsVisible(50);
        lstWords.ensureIndexIsVisible(0);
        search();
    }//GEN-LAST:event_rdoVietAnhItemStateChanged

    private void lstWordsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lstWordsMouseClicked
        search();
    }//GEN-LAST:event_lstWordsMouseClicked

    boolean isPress = false;
    private void txtSearchKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtSearchKeyReleased
        if(evt.getKeyCode() == 10){
            search();
            return;
        }
        
        String word = txtSearch.getText().trim();
        ListModel model = lstWords.getModel();
        for(int i = 0; i < model.getSize(); i++){
            if(model.getElementAt(i).toString().startsWith(word)){
                lstWords.setSelectedIndex(i);
                lstWords.ensureIndexIsVisible(i);
                return;
            }
        }
        
    }//GEN-LAST:event_txtSearchKeyReleased

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(FrameDictionary.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FrameDictionary.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FrameDictionary.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FrameDictionary.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FrameDictionary().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JList lstWords;
    private javax.swing.JRadioButton rdoAnhViet;
    private javax.swing.JRadioButton rdoVietAnh;
    private javax.swing.JTextField txtSearch;
    private javax.swing.JTextArea txtWordMean;
    // End of variables declaration//GEN-END:variables

    /**
     * @return the frameLoading
     */
   
}
