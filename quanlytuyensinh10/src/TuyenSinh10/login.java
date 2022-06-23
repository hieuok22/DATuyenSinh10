package TuyenSinh10;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.WindowConstants;
import javax.swing.border.TitledBorder;

public class login extends JFrame implements ActionListener{
	private static final long serialVersionUID = 1L;
	public login() {
        initComponents();
        setLocationRelativeTo(null);
        btnLogin.addActionListener(this);
        setVisible(true);
        getContentPane().setBackground(Color.lightGray);
    }

	@SuppressWarnings("deprecation")
	@Override
	public void actionPerformed(ActionEvent e) {

         if (e.getSource() == btnLogin) {
             String userText;
             String pwdText;
             userText = txtUser.getText();
             pwdText = txtPass.getText();
             if (userText.equalsIgnoreCase("admin") && pwdText.equalsIgnoreCase("admin")) {
            	 
                 JOptionPane.showMessageDialog(this, "Login Successful");
                 	// Qua JFrame quản lý và thoát khỏi form login                
                 new quanlyJFrame();
                 	this.dispose();
                 //cho quản lý JFrame vào đây    	
                 
             } else {
                 JOptionPane.showMessageDialog(this, "Invalid Username or Password");
             }
             
         }
         
	}
	private void btnCancelActionPerformed(ActionEvent evt) {
        int ret = JOptionPane.showConfirmDialog(this, "Are you sure?", "Confirm",
                JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
        if (ret == JOptionPane.YES_OPTION) {
            System.exit(0);
        } else {
        }
    }
    
    private void initComponents() {

        jPanel1 = new JPanel();
        lblUser = new JLabel();
        lblPassword = new JLabel();
        txtUser = new JTextField();
        btnLogin = new JButton();
        btnCancel = new JButton();
        txtPass = new JPasswordField();

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setTitle("Login\n");

        jPanel1.setBorder(BorderFactory.createTitledBorder(null, "Login", TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, new Font("Times New Roman", 1, 14))); // NOI18N

        lblUser.setText("User name:");

        lblPassword.setText("Password:");

        btnLogin.setText("Login");
        btnLogin.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
               
            }
        });

        btnCancel.setText("Cancel");
        btnCancel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                btnCancelActionPerformed(evt);
            }
        });

        GroupLayout jPanel1Layout = new GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
        jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
        .addGroup(jPanel1Layout.createSequentialGroup()
        .addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
        .addGroup(jPanel1Layout.createSequentialGroup() .addContainerGap()
        .addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
        .addComponent(lblUser).addComponent(lblPassword)).addGap(18, 18, 18)
        .addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
        .addComponent(txtUser, GroupLayout.DEFAULT_SIZE, 163, Short.MAX_VALUE)
        .addComponent(txtPass))).addGroup(jPanel1Layout.createSequentialGroup()       
        .addGap(86, 86, 86).addComponent(btnLogin).addGap(18, 18, 18)
        .addComponent(btnCancel))).addContainerGap())
        );
        
        jPanel1Layout.setVerticalGroup(
        jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
        .addGroup(jPanel1Layout.createSequentialGroup().addContainerGap()      
        .addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(lblUser)       
        .addComponent(txtUser, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
        .addGap(18, 18, 18).addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
        .addComponent(lblPassword).addComponent(txtPass,GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))      
        .addGap(18, 18, 18).addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
        .addComponent(btnLogin) .addComponent(btnCancel)).addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))    
        );

       GroupLayout layout = new GroupLayout(getContentPane());
       getContentPane().setLayout(layout);
       layout.setHorizontalGroup(
       layout.createParallelGroup(GroupLayout.Alignment.LEADING)
       .addGroup(layout.createSequentialGroup().addContainerGap()
       .addComponent(jPanel1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
       .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
        layout.createParallelGroup(GroupLayout.Alignment.LEADING)
        .addGroup(layout.createSequentialGroup()
        .addContainerGap()
        .addComponent(jPanel1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
        .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }
 // Variables 
    private JButton btnCancel;
    private JButton btnLogin;
    private JPanel jPanel1;
    private JLabel lblPassword;
    private JLabel lblUser;
    private JPasswordField txtPass;
    private JTextField txtUser;
	
    public static void main(String args[]) {
                new login();
            }
    }