package TuyenSinh10;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class quanlyJFrame extends JFrame implements ActionListener {	

	private static final long serialVersionUID = 1L;
	JLabel lbqly = new JLabel("Quản lý học sinh");
	 JButton bthoso = new JButton("Hồ sơ học sinh");
	 JButton btthongtin2 = new JButton("Thông tin cấp 2");
	 
	public quanlyJFrame() {
	   setTitle("Thông tin");
	   setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	   //button
	   bthoso.setBounds(50, 150, 150, 100);
	   btthongtin2.setBounds(300, 150, 150, 100);
	   //add
	   add(bthoso);
	   add(btthongtin2);
	   setLayout(null);
	   //size của JFame
	   setResizable(false);
	   setSize(500,400);
	   setLocationRelativeTo(null);
	   setVisible(true);
	   lbqly.setBounds(150, 50, 300, 30);
	   lbqly.setForeground(Color.blue);
	   lbqly.setFont(new Font("Times New Roman", 1, 25));
	   add(lbqly);
	   bthoso.addActionListener(this);
	   btthongtin2.addActionListener(this);
	   this.getContentPane().setBackground(Color.PINK);
	
	}

public static void main(Object object) {
}
@Override
public void actionPerformed(ActionEvent e) {	
	if (e.getSource() == bthoso) {
		new hosoJFrame();
		this.dispose();
     }
	if (e.getSource() == btthongtin2) {
		new ttcap2JFrame();
		this.dispose();
	}

  }
public static void main(String[] args) {
	new quanlyJFrame().setVisible(true);
}
}