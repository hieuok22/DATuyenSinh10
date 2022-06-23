package TuyenSinh10;


import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Vector;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.LayoutStyle;
import javax.swing.WindowConstants;
import javax.swing.table.DefaultTableModel;

	public class hosoJFrame extends JFrame implements ActionListener {		
		private static final long serialVersionUID = 1L;
	      
		ArrayList<hoso> list = new ArrayList<>();
	    DefaultTableModel model;
	    int index = 0;
	    private Connection conn;
	    public hosoJFrame() {
	        initComponents();
	        setSize(1100,600);
	        setLocationRelativeTo(null);	
	        setVisible(true);
	        getContentPane().setBackground(Color.PINK);  
	         //kết nối sql
	        try{
	                    Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
	            conn = DriverManager.getConnection("jdbc:sqlserver://localhost:1433;databaseName=java;user=sa;password=hieudz01677");
	        } catch (Exception evt) {
	            System.out.println(evt);
	        }
	        
	        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
	        list = getListhoso();
	        model = (DefaultTableModel) tblBang.getModel();
	        loadDbToTable();
	    }
	    
	    public boolean check() {
	        if (txtMaHoSo.getText().equals("") || txtHoten.getText().equals("") || txtSDT.getText().equals("")
	                || txtDiemXet.getText().equals("") || txtTenlop.getText().equals("") || txtGioitinh.getText().equals("")
	                || txtNgaySinh.getText().equals("") || txtNoiSinh.getText().equals("")) {
	            JOptionPane.showMessageDialog(rootPane, "Please enter enough data!");
	            return false;
	        }
	        return true;
	    }
		@SuppressWarnings("unchecked")
		public void loadDbToTable() {
	        try {
	            model.setRowCount(0);
	            Statement st = conn.createStatement();
	            ResultSet rs = st.executeQuery("SELECT * FROM tuyensinh10");
	            while (rs.next()) {            	
					@SuppressWarnings("rawtypes")
					Vector row = new Vector();
	                row.add(rs.getString(1));
	                row.add(rs.getString(2));
	                row.add(rs.getString(3));
	                row.add(rs.getString(4));
	                row.add(rs.getString(5));
	                row.add(rs.getString(6));
	                row.add(rs.getString(7));
	                row.add(rs.getString(8));
	                model.addRow(row);
	            }
	            tblBang.setModel(model);
	        } catch (Exception e) {
	            System.out.println(e);
	        }
	    }

	    public void fillTable() {
	        model.setRowCount(0);
	        for (hoso s : list) {
	            Object[] row = new Object[] {s.getMaHoSo(), s.getHoTenHs(), s.getSDT(), 
	            s.getDiemXet(), s.getTenlop(), s.getGioiTinh(), s.getNgaySinh(),s.getNoiSinh()};
	            model.addRow(row);
	        }
	    }

	    public void showDetail(int index) {
	    	
	    	txtMaHoSo.setText(list.get(index).getMaHoSo());
	        txtHoten.setText(list.get(index).getHoTenHs());
	        txtSDT.setText(list.get(index).getSDT());
	        txtDiemXet.setText(list.get(index).getDiemXet());
	        txtTenlop.setText(list.get(index).getTenlop());
	        txtGioitinh.setText(list.get(index).getGioiTinh());
	        txtNgaySinh.setText(list.get(index).getNgaySinh());
	        txtNoiSinh.setText(list.get(index).getNoiSinh());
	        	       
	    }
	   
	    public void deleteStudent() {
	        try {
	            String ma = txtMaHoSo.getText();
	            String sql = "DELETE FROM tuyensinh10 WHERE MaHoSo = ?";
	            PreparedStatement ps = conn.prepareStatement(sql);
	            ps.setString(1, ma);
	            
	            ps.execute();
	        } catch (Exception e) {
	            System.out.println(e);
	        }
	    }

	    public void updateStudent() {
	        try {	       
	        	
	            String sql = "UPDATE tuyensinh10 SET HoTenHS = ?, SDT = ?, Diemxet = ?, Tenlop = ?, GioiTinh = ?, NgaySinh = ?, NoiSinh = ? WHERE MaHoSo = ?";
	            PreparedStatement ps = conn.prepareStatement(sql);	            
	            ps.setString(1, txtHoten.getText());
	            ps.setString(2, txtSDT.getText());
	            ps.setString(3, txtDiemXet.getText());
	            ps.setString(4, txtTenlop.getText());
	            ps.setString(5, txtGioitinh.getText());
	            ps.setString(6, txtNgaySinh.getText());
	            ps.setString(7, txtNoiSinh.getText());   
	            ps.setString(8, txtMaHoSo.getText()); 
	            
	            ps.executeUpdate();

	        } catch (Exception e) {
	            System.out.println(e);
	        }
	    }
//save
	    public boolean saveStudent(hoso sv) { 	
	    	String sql = "INSERT INTO tuyensinh10 VALUES(?,?,?,?,?,?,?,?)";
	        try {        	
	        	
	            PreparedStatement ps = conn.prepareStatement(sql);
	            ps.setString(1, sv.getMaHoSo());
	            ps.setString(2, sv.getHoTenHs());
	            ps.setString(3, sv.getSDT());
	            ps.setString(4, sv.getDiemXet());
	            ps.setString(5, sv.getTenlop());
	            ps.setString(6, sv.getGioiTinh());
	            ps.setString(7, sv.getNgaySinh());
	            ps.setString(8, sv.getNoiSinh());	
	            
	            ClientCTR ctr = new ClientCTR();
	            ctr.openSocket();
	            ctr.sendStudent(sv);
	            String res = ctr.getResult();
	            if(res.equals("ok")) {
	            	JOptionPane.showConfirmDialog(rootPane, "Success!");
	            	
	            } else {
	            	JOptionPane.showMessageDialog(rootPane, "Failed!");
	            }
	            ctr.closeConnection();
	            
	            return ps.executeUpdate() > 0;
	            
	        } catch (Exception e) {
	            System.out.println(e);
	        }
	        return false;
	    }
	 
		
	    	
	    public ArrayList<hoso> getListhoso() {
	        String sql = "SELECT * FROM tuyensinh10";
	        try {
	            PreparedStatement ps = conn.prepareStatement(sql);
	            ResultSet rs = ps.executeQuery();
	            while (rs.next()) {
	                hoso sv = new hoso();
	                sv.setMaHoSo(rs.getString(1));
	                sv.setHoTenHs(rs.getString(2));
	                sv.setSDT(rs.getString(3));
	                sv.setDiemXet(rs.getString(4));
	                sv.setTenlop(rs.getString(5));
	                sv.setGioiTinh(rs.getString(6));
	                sv.setNgaySinh(rs.getString(7));
	                sv.setNoiSinh(rs.getString(8));
	                list.add(sv);
	            }
	        } catch (Exception e) {
	            System.out.println(e);
	        }
	        return list;
	    }
	   
	    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
	    private void initComponents() {

	    	btnDelete = new JButton();
	    	btnNew = new JButton();
	    	btnSave = new JButton();
	    	btnUpdate = new JButton();
	    	btnsearch = new JButton();
	    	btnhome = new JButton();
	    	    
	    	jScrollPane2 = new JScrollPane();
	    	tblBang = new JTable();
	    	jLabel1 = new JLabel();
	        
	    	lblMaHoSo = new JLabel();
	    	lblHoten = new JLabel();
	    	lblSDT = new JLabel();
	    	lblDiemXet = new JLabel();
	    	lblTenlop = new JLabel();
	    	lblGioitinh = new JLabel();
	    	lblNgaySinh = new JLabel();
	    	lblNoiSinh = new JLabel();
	    	
	    	txtMaHoSo = new JTextField();
	    	txtHoten = new JTextField();
	    	txtSDT = new JTextField();
	    	txtDiemXet = new JTextField();
	    	txtTenlop = new JTextField();
	    	txtGioitinh = new JTextField();
	    	txtNgaySinh = new JTextField();
	    	txtNoiSinh = new JTextField();
	        
	        

	        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
	        setTitle("Hồ Sơ");
	        
	       
	        jLabel1.setFont(new Font("Times New Roman", 1, 25)); 
	        jLabel1.setForeground(new Color(0, 51, 204));
	        jLabel1.setText("Thông tin học sinh");

	        lblMaHoSo.setFont(new Font("Tahoma", 0, 12)); 
	        lblMaHoSo.setText("Mã hồ sơ:  ");

	        lblHoten.setFont(new Font("Tahoma", 0, 12));
	        lblHoten.setText("Họ tên:  ");

	        lblSDT.setFont(new Font("Tahoma", 0, 12)); 
	        lblSDT.setText("SDT:  ");

	        lblDiemXet.setFont(new Font("Tahoma", 0, 12)); 
	        lblDiemXet.setText("Điểm xét:  ");

	        lblTenlop.setFont(new Font("Tahoma", 0, 12)); 
	        lblTenlop.setText("Tên Lớp:  ");
	        
	        lblGioitinh.setFont(new Font("Tahoma", 0, 12)); 
	        lblGioitinh.setText("Giới tính:  ");
	        
	        lblNgaySinh.setFont(new Font("Tahoma", 0, 12)); 
	        lblNgaySinh.setText("Ngày sinh:  ");
	        
	        lblNoiSinh.setFont(new Font("Tahoma", 0, 12)); 
	        lblNoiSinh.setText("Nơi sinh:  ");
  
	        tblBang.setFont(new Font("Tahoma", 0, 12)); 
	        tblBang.setModel(new DefaultTableModel(
	            new Object [][] {

	            },
	            new String [] {
	            		 "Mã hồ sơ", "Họ tên", "Số ĐT", "Điểm Xét", "Tên lớp", "Giới Tính", "Ngày Sinh", "Nơi Sinh"
	            }
	        ));
	        tblBang.addMouseListener(new MouseAdapter() {
	            public void mouseClicked(MouseEvent evt) {
	                tblBangMouseClicked(evt);
	            }
	        });
	        
	        jScrollPane2.setViewportView(tblBang);
	        
	        btnNew.setFont(new Font("Tahoma", 0, 13)); // NOI18N
	        btnNew.setText("New");
	        btnNew.addActionListener(new ActionListener() {
	            public void actionPerformed(ActionEvent evt) {
	                btnNewActionPerformed(evt);
	            }
	        });
	        
	        
	        btnSave.setFont(new Font("Tahoma", 0, 13)); // NOI18N        
	        btnSave.setText("Save");
	        btnSave.addActionListener(new ActionListener() {
	            public void actionPerformed(ActionEvent evt) {
 	                btnSaveActionPerformed(evt);
	            }
	        });

	        btnDelete.setFont(new Font("Tahoma", 0, 13)); // NOI18N       
	        btnDelete.setText("Delete");
	        btnDelete.addActionListener(new ActionListener() {
	            public void actionPerformed(ActionEvent evt) {
	                btnDeleteActionPerformed(evt);
	            }
	        });

	        btnUpdate.setFont(new Font("Tahoma", 0, 13)); // NOI18N       
	        btnUpdate.setText("Update");
	        btnUpdate.addActionListener(new ActionListener() {
	            public void actionPerformed(ActionEvent evt) {
	                btnUpdateActionPerformed(evt);
	            }
	            
	        });
	        btnhome.setFont(new Font("Tahoma", 0, 13)); // NOI18N       
	        btnhome.setText("Home");
	        btnhome.addActionListener(new ActionListener() {
	            public void actionPerformed(ActionEvent evt) {
	                homeActionPerformed(evt);
	            }
	            
	        });
	        btnsearch.setFont(new Font("Tahoma", 0, 13)); // NOI18N       
	        btnsearch.setText("Search");
	        this.btnsearch.addActionListener(new ActionListener() {
	            public void actionPerformed(ActionEvent evt) {
	            	btnsearchActionPerformed(evt);
	            }            
	        });
	        GroupLayout layout = new GroupLayout(getContentPane());
	        getContentPane().setLayout(layout);
	        layout.setHorizontalGroup(
	        layout.createParallelGroup(GroupLayout.Alignment.LEADING)
	        .addGroup(layout.createSequentialGroup()
	        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
	        .addGroup(layout.createSequentialGroup()
	        .addContainerGap()
	        .addComponent(jScrollPane2))
	        .addGroup(layout.createSequentialGroup()
	        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
	        .addGroup(layout.createSequentialGroup()
	        .addGap(100, 100, 100)
	        
	        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
	        .addGroup(layout.createSequentialGroup())
	        .addComponent(lblMaHoSo)
	    	.addComponent(lblHoten)        
	    	.addComponent(lblSDT)	              
	    	.addComponent(lblDiemXet)	    	 
	    	.addComponent(lblTenlop)	        
	    	.addComponent(lblGioitinh)        
	    	.addComponent(lblNgaySinh)
	    	.addComponent(lblNoiSinh)
	    	
	    	.addGap(350, 350, 350))
	        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.CENTER)	 
	        .addGroup(layout.createSequentialGroup()
	        		       
	        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
	        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
	        .addComponent(btnNew, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
	        .addComponent(btnDelete, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)    
	        .addComponent(btnUpdate, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
	        .addComponent(btnSave, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)	       
	        .addComponent(btnhome, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)         
	        .addComponent(btnsearch, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
	        
	        .addGroup(layout.createSequentialGroup()
	        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
	        .addGroup(layout.createSequentialGroup())
	      
	        .addComponent(txtMaHoSo, GroupLayout.DEFAULT_SIZE, 165, Short.MAX_VALUE)
	        .addComponent(txtHoten)
	        .addComponent(txtSDT)
	        .addComponent(txtDiemXet)  
	        .addComponent(txtTenlop)
	        .addComponent(txtGioitinh)
	        .addComponent(txtNgaySinh)
	        .addComponent(txtNoiSinh)
	        .addComponent(jLabel1)
	        .addGap(58, 58, 58))	 
	        
	        .addGroup(layout.createSequentialGroup()
	        .addGap(400, 400, 400)))
	        
	        .addComponent(jLabel1))	        
	        .addGap(0, 12, Short.MAX_VALUE)))
	        .addContainerGap())))
	        
	        );
	        
	        layout.setVerticalGroup(
	        layout.createParallelGroup(GroupLayout.Alignment.LEADING)
	        .addGroup(layout.createSequentialGroup()
	        .addContainerGap()
	        .addComponent(jLabel1)
	        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
	        .addGroup(layout.createSequentialGroup()
	        .addGap(15, 15, 15)
	        
	        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
	    	.addComponent(lblMaHoSo)
	    	.addComponent(btnsearch)	
	    	.addComponent(txtMaHoSo, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
	    	.addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
	    	
	    	.addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
	        .addComponent(txtHoten, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
	        .addComponent(lblHoten))
	    	.addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
	    	
	        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
	    	.addComponent(lblSDT).addComponent(btnNew)
	    	.addComponent(txtSDT, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
	    	.addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
	    	                
	        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
	    	.addComponent(lblDiemXet)
	    	.addComponent(btnSave)
	    	.addComponent(txtDiemXet, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
	    	.addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
	    	    	          
	    	.addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
	    	.addComponent(lblTenlop)
	    	.addComponent(btnUpdate)
	    	.addComponent(txtTenlop, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
	    	.addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
	    		 
	    	.addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
	    	.addComponent(lblGioitinh)
	    	.addComponent(btnDelete)
	    	.addComponent(txtGioitinh, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
	    	.addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)

	    	.addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
	    	.addComponent(lblNgaySinh)	
	    	.addComponent(btnhome)
	    	.addComponent(txtNgaySinh, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
	    	.addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)

	    	.addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
	    	.addComponent(lblNoiSinh)	    	
	    	.addComponent(txtNoiSinh, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
	    	.addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)       	        
	        .addGap(18, 18, 18)	     
	        
	        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
	        .addGroup(layout.createSequentialGroup()
	        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.TRAILING)	       
	        .addGroup(GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
	        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE))))))
	        .addComponent(jScrollPane2, GroupLayout.PREFERRED_SIZE, 200, GroupLayout.PREFERRED_SIZE)
	        .addGap(39, 39, 39))
	        .addGroup(layout.createSequentialGroup()
	        .addGap(15, 15, 15)	       
	        .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)));

	        pack();
	    }
	    // event new 
	    private void btnNewActionPerformed(ActionEvent evt) {
	        txtMaHoSo.setText("");
	        txtHoten.setText("");
	        txtSDT.setText("");
	        txtDiemXet.setText("");
	        txtTenlop.setText("");
	        txtGioitinh.setText("");
	        txtNgaySinh.setText("");
	        txtNoiSinh.setText("");
	      
	    }

	// event save 
		private void btnSaveActionPerformed(ActionEvent evt) {
	        if (check()) {
	            hoso sv = new hoso();
	            sv.setMaHoSo(txtMaHoSo.getText());	            
	            sv.setHoTenHs(txtHoten.getText());
	            sv.setSDT(txtSDT.getText());
	            sv.setDiemXet(txtDiemXet.getText());
	            sv.setTenlop(txtTenlop.getText());
	            sv.setGioiTinh(txtGioitinh.getText());
	            sv.setNgaySinh(txtNgaySinh.getText());
	            sv.setNoiSinh(txtNoiSinh.getText());
	           
	            if (saveStudent(sv)) {
	                JOptionPane.showMessageDialog(rootPane, "Save Successful!");
	                list.add(sv);
	            } else {
	                JOptionPane.showMessageDialog(rootPane, "error");
	            }
	            fillTable();
	        }
		}
		// event delete
	    private void btnDeleteActionPerformed(ActionEvent evt) {
	        index = tblBang.getSelectedRow();
	        if (index == -1) {
	            JOptionPane.showMessageDialog(rootPane, "Invalid Delete!");
	        } else {
	            this.deleteStudent();
	            list.remove(index);
	            loadDbToTable();
	            this.btnNewActionPerformed(evt);
	            JOptionPane.showMessageDialog(rootPane, "Delete Successful!");
	        }
	    }
	   
	    private void tblBangMouseClicked(MouseEvent evt) {
	        try {
	            index = tblBang.getSelectedRow();
	            showDetail(index);
	        } catch (Exception e) {
	            System.out.println(e);
	        }
	    }
	    // event update
		private void btnUpdateActionPerformed(ActionEvent evt) {
	       index = tblBang.getSelectedRow();
	        if (index == -1) {
	            JOptionPane.showMessageDialog(rootPane, "Invalid Update!");
	        } else {
	            list.remove(index);
	            this.updateStudent();
	            hoso sv = new hoso();
	            sv.setMaHoSo(txtMaHoSo.getText());
	            sv.setHoTenHs(txtHoten.getText());
	            sv.setSDT(txtSDT.getText());
	            sv.setDiemXet(txtDiemXet.getText());
	            sv.setTenlop(txtTenlop.getText());
	            sv.setGioiTinh(txtGioitinh.getText());
	            sv.setNgaySinh(txtNgaySinh.getText());
	            sv.setNoiSinh(txtNoiSinh.getText());
	            
	            list.add(sv);
	            fillTable();
        JOptionPane.showMessageDialog(rootPane, "Update Successful!");
	        }
	        }   
		
		private void btnsearchActionPerformed(ActionEvent evt) {
			try {
		    	String sql = "SELECT * FROM tuyensinh10 WHERE MaHoSo =?";
		    	
		    		 PreparedStatement ps = conn.prepareStatement(sql);
		    		 ps.setString(1, txtMaHoSo.getText());
			            ResultSet rs = ps.executeQuery();
			            
			           if(rs.next()) {
			        	   
			        	txtMaHoSo.setText(rs.getString("MaHoSo"));
			   	        txtHoten.setText(rs.getString("HoTenHS"));
			   	        txtSDT.setText(rs.getString("SDT"));
			   	        txtDiemXet.setText(rs.getString("Diemxet"));
			   	        txtTenlop.setText(rs.getString("Tenlop"));
			   	        txtGioitinh.setText(rs.getString("GioiTinh"));
			   	        txtNgaySinh.setText(rs.getString("NgaySinh"));
			   	        txtNoiSinh.setText(rs.getString("NoiSinh"));
			           }   
			              
		    	} catch (Exception e) {
		    		System.out.println(e);
		    	}
		    }
				
		public void homeActionPerformed(ActionEvent e) {	
			if (e.getSource() == btnhome) {
				new quanlyJFrame();
				this.dispose();
		        }
			}	
		// Variables
	    private JButton btnDelete;
	    private JButton btnNew;
	    private JButton btnSave;
	    private JButton btnUpdate;
	    private JButton btnsearch;
	    private JButton btnhome;  
	    private JLabel jLabel1;
	    private JScrollPane jScrollPane2;
	    private JTable tblBang;
	    
	    private JLabel lblMaHoSo;
	    private JLabel lblHoten;
	    private JLabel lblSDT;
	    private JLabel lblDiemXet;
	    private JLabel lblTenlop;
	    private JLabel lblGioitinh;	    
	    private JLabel lblNgaySinh;
	    private JLabel lblNoiSinh;	
	    
	    private JTextField txtMaHoSo;
	    private JTextField txtHoten;
	    private JTextField txtSDT;
	    private JTextField txtDiemXet;
	    private JTextField txtTenlop;
	    private JTextField txtGioitinh;	    
	    private JTextField txtNgaySinh;
	    private JTextField txtNoiSinh;	   
	    @Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub	
		}
	    public static void main(String[] args) {
			new hosoJFrame();
		}	
}