/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import dao.UserDaoImpl;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import service.MobileMoney;
import model.Branch;
import model.User;

import dao.UserDao;

/**
 *
 * @author Phuc ND
 */
public class JFrame extends javax.swing.JFrame {

    public String message;
    public int flagStatus = 0;
    public static final String patternSend = "[*]112[*]";
    public static final String patternRecharge = "[*]113[*]";
    public static final String patternWD = "[*]114[*]";
    public static final String phoneSend = "+84796131183";
    final static int TIMEOUT = 2000;
    public UserDao connectData = new UserDaoImpl();
    public String branchCode;
    public String password ;
//    public static int OTP;
//    public static int min = 1000;
//    public static int max = 9999;

    public JFrame() {
        initComponents();

        this.setLocationRelativeTo(null);
        //  showPortList();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jDesktopPane1 = new javax.swing.JDesktopPane();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTextPane1 = new javax.swing.JTextPane();
        jButton1 = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("MobileMoney");

        jScrollPane2.setViewportView(jTextPane1);

        jButton1.setText("Gửi tin nhắn");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jLabel2.setText("Nhập cú pháp:");

        jDesktopPane1.setLayer(jScrollPane2, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktopPane1.setLayer(jButton1, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktopPane1.setLayer(jLabel2, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout jDesktopPane1Layout = new javax.swing.GroupLayout(jDesktopPane1);
        jDesktopPane1.setLayout(jDesktopPane1Layout);
        jDesktopPane1Layout.setHorizontalGroup(
            jDesktopPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jDesktopPane1Layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 45, Short.MAX_VALUE)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 275, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(28, 28, 28))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jDesktopPane1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton1)
                .addGap(133, 133, 133))
        );
        jDesktopPane1Layout.setVerticalGroup(
            jDesktopPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jDesktopPane1Layout.createSequentialGroup()
                .addGap(102, 102, 102)
                .addGroup(jDesktopPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 71, Short.MAX_VALUE)
                .addComponent(jButton1)
                .addGap(32, 32, 32))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jDesktopPane1)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jDesktopPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        message = jTextPane1.getText();
        System.out.println("Mess: " + message);
        JDialogOTP dialogOTP = new JDialogOTP(this, rootPaneCheckingEnabled);
        dialogOTP.setVisible(true);

//       try {
//            handleMessage();          
//        } catch (Exception ex) {
//            Logger.getLogger(JFrame.class.getName()).log(Level.SEVERE, null, ex);
//        }

    }//GEN-LAST:event_jButton1ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                JFrame jFrame = new JFrame();
                jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                jFrame.pack();
                jFrame.setVisible(true);
                jFrame.setLocationRelativeTo(null);

            }
        });
        

//        OTP = 0;
//        OTP = (int) (Math.random() * (max - min + 1) + min);
//        System.out.println("Mã OTP: " + OTP);
//        BasicConfigurator.configure();
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JDesktopPane jDesktopPane1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextPane jTextPane1;
    // End of variables declaration//GEN-END:variables

//Show list port in your computer
/*    
    private void showPortList() {
        SerialPort[] ports = SerialPort.getCommPorts();
        for (SerialPort port : ports) {
            System.out.println(port.getSystemPortName());
            jComboBox1.addItem(port.getSystemPortName());
        }
    }*/
    // *113*+84796131183*20000#
    //*112*+84868710406*500#
    // *114*MBFCAUGIAY*5000#
    public void handleMessage(String message) throws Exception {

        System.out.println("Message: " + message);
        Pattern pattern = Pattern.compile("[\\*]\\d{3}[\\*][\\+]\\d{11}[\\*]\\d+[\\#]$");
        Pattern patternS = Pattern.compile(patternSend);
        Pattern patternR = Pattern.compile(patternRecharge);
        Matcher matcher = pattern.matcher(message);

        Pattern pattern1 = Pattern.compile("[\\*]\\d{3}[\\*]\\w{10}[\\*]\\d+[\\#]$");
        Pattern patternW = Pattern.compile(patternWD);
        Matcher matcherW = pattern1.matcher(message);

        
//        password = userSend.getPassword();
        System.out.println("Pass:" + password);
//        Pattern patternW = Pattern.compile(patternWD);
        flagStatus = 0;
        if (matcher.find()) {
            User userSend = connectData.getAllUserSend(phoneSend);
            // get phoneReceiver
            String phoneReceive = message.substring(5, 17);
            // get amount 
            int amount = Integer.parseInt(message.substring(18, message.length() - 1));

            // check information user send
            if (patternS.matcher(message).find()) {

                UserReceive userReceive = connectData.getAllUserReceive(phoneReceive);
                if (userReceive == null && userSend == null) {
                    flagStatus = 5;
                    System.err.println("Không tìm thấy thông tin người dùng!");
                } else if (amount <= userSend.getAmount()) {

                    // call function send amount            
                    new MobileMoney().sendAmound(phoneReceive, phoneSend, amount);
                    flagStatus = 1;
                } else {
                    flagStatus = 5;
                    System.out.println("Số tiền chuyển lớn hơn số tiền trong tài khoản !");
                }

            } // recharge     
            else if (patternR.matcher(message).find()) {

                // call function charge
                new MobileMoney().recharge(phoneReceive, amount);
                flagStatus = 2;
            } // withDrawl
            else {
                System.err.println("Dịch vụ chưa được cung cấp");
                flagStatus = 4;
            }

        } else if (matcherW.find()) {

            branchCode = message.substring(5, 15);
            int amount = Integer.parseInt(message.substring(16, message.length() - 1));
            if (patternW.matcher(message).find()) {
                User user = connectData.getAllUserSend(phoneSend);
                Branch branch = connectData.getAllBranch(branchCode);
                if (branch != null) {
                    if (user.getAmount() >= amount) {
                        new MobileMoney().withDrawal(phoneSend, amount, branchCode);
                        flagStatus = 3;
                    } else {
                        flagStatus = 5;
                        System.out.println("Số tiền rút lớn hơn số tiền trong tài khoản !");
                    }
                } else {
                    System.out.println("Mã chi nhánh không hợp lệ !");
                    flagStatus = 5;
                }
            } else {
                System.err.println("Dịch vụ chưa được cung cấp");
                flagStatus = 4;
            }

        } else {
            System.err.println("Syntax Error !");
            flagStatus = 6;
        }

    }

}
