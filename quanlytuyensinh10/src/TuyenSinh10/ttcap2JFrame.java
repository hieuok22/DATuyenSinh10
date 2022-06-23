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

	public class ttcap2JFrame extends JFrame implements ActionListener {		
		private static final long serialVersionUID = 1L;
		private Connection conn;
		
		ArrayList<ttcap2> list = new ArrayList<>();
	    DefaultTableModel model;
	    int index = 0;
	   
	    public ttcap2JFrame() {
	        initComponents();
	        setSize(1280,680);
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
	        list = getListtcap2();
	        model = (DefaultTableModel) tblBang.getModel();
	        loadDbToTable();
	    }
	    
	    public boolean check() {
	        if (txtMaHoSo.getText().equals("") || txtHoten.getText().equals("") || txthstruong.getText().equals("")
	                || txthk6.getText().equals("") || txthl6.getText().equals("") || txtdiem6.getText().equals("")
	                || txthk7.getText().equals("") || txthl7.getText().equals("") || txthk8.getText().equals("") || txthl8.getText().equals("")
	                || txtdiem8.getText().equals("") || txthk9.getText().equals("") || txthl9.getText().equals("") || txtdiem9.getText().equals("")){
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
	            ResultSet rs = st.executeQuery("SELECT * FROM ttcap2");
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
	                row.add(rs.getString(9));
	                row.add(rs.getString(10));
	                row.add(rs.getString(11));
	                row.add(rs.getString(12));
	                row.add(rs.getString(13));
	                row.add(rs.getString(14));
	                row.add(rs.getString(15));	                
	                model.addRow(row);
	            }
	            tblBang.setModel(model);
	        } catch (Exception e) {
	            System.out.println(e);
	        }
	    }

	    public void fillTable() {
	        model.setRowCount(0);
	        for (ttcap2 s : list) {
	            Object[] row = new Object[] {s.getMaHoSo(), s.getHoTenHS(), s.getHStruong(), 
	            s.getHK6(), s.getHL6(), s.getDiem6(), s.getHK7(),s.getHL7(), s.getDiem7(),
	            s.getHK8(), s.getHL8(), s.getDiem8(), s.getHK9(), s.getHL9(), s.getDiem9()};
	            model.addRow(row);
	        }
	    }

	    public void showDetail(int index) {
	    	
	        txtMaHoSo.setText(list.get(index).getMaHoSo());
	        txtHoten.setText(list.get(index).getHoTenHS());
	        txthstruong.setText(list.get(index).getHStruong());
	        txthk6.setText(list.get(index).getHK6());
	        txthl6.setText(list.get(index).getHL6());
	        txtdiem6.setText(list.get(index).getDiem6());
	        txthk7.setText(list.get(index).getHK7());
	        txthl7.setText(list.get(index).getHL7());
	        txtdiem7.setText(list.get(index).getDiem7());
	        txthk8.setText(list.get(index).getHK8());
	        txthl8.setText(list.get(index).getHL8());
	        txtdiem8.setText(list.get(index).getDiem8());
	        txthk9.setText(list.get(index).getHK9());
	        txthl9.setText(list.get(index).getHL9());
	        txtdiem9.setText(list.get(index).getDiem9());
	        	       
	    }
	   
	    public void deleteStudent() {
	        try {
	            String ma = txtMaHoSo.getText();
	            String sql = "DELETE FROM ttcap2 WHERE MaHoSo = ?";
	            PreparedStatement ps = conn.prepareStatement(sql);
	            ps.setString(1, ma);
	            
	            ps.execute();
	        } catch (Exception e) {
	            System.out.println(e);
	        }
	    }

	    public void updateStudent() {
	        try {	       
	        	
	            String sql = "UPDATE ttcap2 SET HoTenHS = ?, HStruong = ?, HK6 = ?, HL6 = ?, Diem6 = ?, HK7 = ?, HL7 = ?, Diem7 = ?, HK8 = ?, HL8 = ?, Diem8 = ?, HK9 = ?,HL9 = ?, Diem9 = ? WHERE MaHoSo = ?";
	            PreparedStatement ps = conn.prepareStatement(sql);	            
	            ps.setString(1, txtHoten.getText());
	            ps.setString(2, txthstruong.getText());
	            ps.setString(3, txthk6.getText());
	            ps.setString(4, txthl6.getText());
	            ps.setString(5, txtdiem6.getText());
	            ps.setString(6, txthk7.getText());
	            ps.setString(7, txthl7.getText());   
	            ps.setString(8, txtdiem7.getText()); 
	            ps.setString(9, txthk8.getText()); 
	            ps.setString(10, txthl8.getText()); 
	            ps.setString(11, txtdiem8.getText()); 
	            ps.setString(12, txthk9.getText()); 
	            ps.setString(13, txthl9.getText()); 
	            ps.setString(14, txtdiem9.getText()); 
	            ps.setString(15, txtMaHoSo.getText()); 
	            ps.executeUpdate();

	        } catch (Exception e) {
	            System.out.println(e);
	        }
	    }

	    public boolean saveStudent(ttcap2 sv) { 	
	    	String sql = "INSERT INTO ttcap2 VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
	        try {        	
	        	
	            PreparedStatement ps = conn.prepareStatement(sql);
	            ps.setString(1, sv.getMaHoSo());
	            ps.setString(2, sv.getHoTenHS());
	            ps.setString(3, sv.getHStruong());
	            ps.setString(4, sv.getHK6());
	            ps.setString(5, sv.getHL6());
	            ps.setString(6, sv.getDiem6());
	            ps.setString(7, sv.getHK7());
	            ps.setString(8, sv.getHL7());
	            ps.setString(9, sv.getDiem7());	  
	            ps.setString(10, sv.getHK8());
	            ps.setString(11, sv.getHL8());
	            ps.setString(12, sv.getDiem8());  
	            ps.setString(13, sv.getHK9());
	            ps.setString(14, sv.getHL9());
	            ps.setString(15, sv.getDiem9()); 
	            return ps.executeUpdate() > 0;
	            
	        } catch (Exception e) {
	            System.out.println(e);
	        }
	        return false;
	    }
	 
		
	    	
	    public ArrayList<ttcap2> getListtcap2() {
	        String sql = "SELECT * FROM ttcap2";
	        try {
	            PreparedStatement ps = conn.prepareStatement(sql);
	            ResultSet rs = ps.executeQuery();
	            while (rs.next()) {
	                ttcap2 sv = new ttcap2();
	                sv.setMaHoSo(rs.getString(1));
	                sv.setHoTenHS(rs.getString(2));
	                sv.setHStruong(rs.getString(3));
	                sv.setHK6(rs.getString(4));
	                sv.setHL6(rs.getString(5));
	                sv.setDiem6(rs.getString(6));
	                sv.setHK7(rs.getString(7));
	                sv.setHL7(rs.getString(8));
	                sv.setDiem7(rs.getString(9));
	                sv.setHK8(rs.getString(10));
	                sv.setHL8(rs.getString(11));
	                sv.setDiem8(rs.getString(12));
	                sv.setHK9(rs.getString(13));
	                sv.setHL9(rs.getString(14));
	                sv.setDiem9(rs.getString(15));
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
	    	lblhstruong = new JLabel();
	    	lblhk6 = new JLabel();
	    	lblhl6 = new JLabel();
	    	lbldiem6 = new JLabel();
	    	lblhk7 = new JLabel();
	    	lblhl7 = new JLabel();
	    	lbldiem7 = new JLabel();
	    	lblhk8 = new JLabel();
	    	lblhl8 = new JLabel();
	    	lbldiem8 = new JLabel();
	    	lblhk9 = new JLabel();
	    	lblhl9 = new JLabel();
	    	lbldiem9 = new JLabel();
	    	
	    	txtMaHoSo = new JTextField();
	    	txtHoten = new JTextField();
	    	txthstruong = new JTextField();
	    	txthk6 = new JTextField();
	    	txthl6 = new JTextField();
	    	txtdiem6 = new JTextField();
	    	txthk7 = new JTextField();
	    	txthl7 = new JTextField();
	    	txtdiem7 = new JTextField();
	    	txthk8 = new JTextField();
	    	txthl8 = new JTextField();
	    	txtdiem8 = new JTextField();
	    	txthk9 = new JTextField();
	    	txthl9 = new JTextField();
	    	txtdiem9 = new JTextField();
	        
	        

	        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
	        setTitle("Hồ Sơ");
	        
	       
	        jLabel1.setFont(new Font("Times New Roman", 1, 25)); 
	        jLabel1.setForeground(new Color(0, 51, 204));
	        jLabel1.setText("Thông tin cấp 2");

	        lblMaHoSo.setFont(new Font("Tahoma", 0, 12)); 
	        lblMaHoSo.setText("Mã hồ sơ:  ");

	        lblHoten.setFont(new Font("Tahoma", 0, 12));
	        lblHoten.setText("Họ tên:  ");

	        lblhstruong.setFont(new Font("Tahoma", 0, 12)); 
	        lblhstruong.setText("HS Trường:  ");

	        lblhk6.setFont(new Font("Tahoma", 0, 12)); 
	        lblhk6.setText("HK Lớp 6:  ");

	        lblhl6.setFont(new Font("Tahoma", 0, 12)); 
	        lblhl6.setText("HL Lớp 6:  ");
	        
	        lbldiem6.setFont(new Font("Tahoma", 0, 12)); 
	        lbldiem6.setText("Điểm Lớp 6:  ");
	        
	        lblhk7.setFont(new Font("Tahoma", 0, 12)); 
	        lblhk7.setText("HK Lớp 7:  ");

	        lblhl7.setFont(new Font("Tahoma", 0, 12)); 
	        lblhl7.setText("HL Lớp 7:  ");
	        
	        lbldiem7.setFont(new Font("Tahoma", 0, 12)); 
	        lbldiem7.setText("Điểm Lớp 7:  ");
	        
	        lblhk8.setFont(new Font("Tahoma", 0, 12)); 
	        lblhk8.setText("HK Lớp 8:  ");

	        lblhl8.setFont(new Font("Tahoma", 0, 12)); 
	        lblhl8.setText("HL Lớp 8:  ");
	        
	        lbldiem8.setFont(new Font("Tahoma", 0, 12)); 
	        lbldiem8.setText("Điểm Lớp 8:  ");
	        
	        lblhk9.setFont(new Font("Tahoma", 0, 12)); 
	        lblhk9.setText("HK Lớp 9:  ");

	        lblhl9.setFont(new Font("Tahoma", 0, 12)); 
	        lblhl9.setText("HL Lớp 9:  ");
	        
	        lbldiem9.setFont(new Font("Tahoma", 0, 12)); 
	        lbldiem9.setText("Điểm Lớp 9:  ");
  
	        tblBang.setFont(new Font("Tahoma", 0, 12)); 
	        tblBang.setModel(new DefaultTableModel(
	            new Object [][] {

	            },
	            new String [] {
	            		 "Mã hồ sơ", "Họ tên", "HS Trường", "HK lớp 6", "HL Lớp 6", 
	            		 "Điểm Lớp 6", "HK lớp 7", "HL Lớp 7", "Điểm Lớp 7",
	            		 "HK lớp 8", "HL Lớp 8", "Điểm Lớp 8","HK lớp 9", "HL Lớp 9", "Điểm Lớp 9"
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
	        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.TRAILING))
	        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
	        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
	    	.addGroup(layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
	    	.addGroup(layout.createSequentialGroup())
	        .addComponent(lblMaHoSo)
	    	.addComponent(lblHoten)        
	    	.addComponent(lblhstruong)	              
	    	.addComponent(lblhk6)	    	 
	    	.addComponent(lblhl6)	        
	    	.addComponent(lbldiem6)        
	    	.addComponent(lblhk7)))
	    	.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
	        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
	    	.addComponent(txtMaHoSo, GroupLayout.DEFAULT_SIZE, 165, Short.MAX_VALUE)
		    .addComponent(txtHoten)
		    .addComponent(txthstruong)
		    .addComponent(txthk6)  
		    .addComponent(txthl6)
		    .addComponent(txtdiem6)
		    .addComponent(txthk7)  
	    	.addGap(150, 150, 150))	        
	        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.TRAILING)	 
	        .addGroup(layout.createSequentialGroup()
	        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
	    	.addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
	    	.addGroup(layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
	    	.addGroup(layout.createSequentialGroup()  .addComponent(jLabel1))	    	
	        		.addComponent(lblhl7)	        
	    	    	.addComponent(lbldiem7) 
	    	    	.addComponent(lblhk8)	    	 
	    	    	.addComponent(lblhl8)	        
	    	    	.addComponent(lbldiem8) 
	    	    	.addComponent(lblhk9)	    	 
	    	    	.addComponent(lblhl9)	        
	    	    	.addComponent(lbldiem9)))
	    	.addGroup(layout.createSequentialGroup())
	    	.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
	    	.addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING, false)	
	    				
	    	.addComponent(txthl7, GroupLayout.DEFAULT_SIZE, 165, Short.MAX_VALUE)
	        .addComponent(txtdiem7)
	        .addComponent(txthk8)  
	        .addComponent(txthl8)
	        .addComponent(txtdiem8)
	        .addComponent(txthk9)  
	        .addComponent(txthl9)
	        .addComponent(txtdiem9))
	    	.addGap(80, 80, 80)
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
	        		 .addComponent(jLabel1)  
	        .addGap(250, 250, 250))	 	        
	       .addGroup(layout.createSequentialGroup()
	        .addGap(400, 400, 400))))	        
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
	    	.addComponent(lblMaHoSo).addComponent(txthl7).addComponent(btnsearch)	   	
	    	.addComponent(lblhl7, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)	    	
	    	.addComponent(txtMaHoSo, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
	    	.addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
	    	
	    	.addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
	    			.addComponent(lbldiem7)
	    	    	.addComponent(txtdiem7, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
	        .addComponent(txtHoten, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
	        .addComponent(lblHoten))
	    	.addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
	    	
	        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
	    	.addComponent(lblhstruong)
	    	.addComponent(lblhk8)		    	    
	        .addComponent(txthk8, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
	    	.addComponent(txthstruong, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
	    	.addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
	    	                
	        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
	    	.addComponent(lblhk6)
	    	.addComponent(lblhl8).addComponent(btnNew)    	
	    	.addComponent(txthl8, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
	    	.addComponent(txthk6, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
	    	.addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
	    	    	          
	    	.addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
	    	.addComponent(lblhl6)
	    	.addComponent(lbldiem8).addComponent(btnSave)
	    	.addComponent(txtdiem8, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
	    	.addComponent(txthl6, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
	    	.addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
	    		 
	    	.addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
	    	.addComponent(lbldiem6)	
	    	.addComponent(lblhk9).addComponent(btnUpdate)
	    	.addComponent(txthk9, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
	    	.addComponent(txtdiem6, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
	    	.addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)

	    	.addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
	    	.addComponent(lblhk7)
	    	.addComponent(lblhl9).addComponent(btnDelete)	    	
	    	.addComponent(txthl9, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
	    	.addComponent(txthk7, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
	    	.addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
    	    	
	    	.addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
	    	.addComponent(lbldiem9).addComponent(btnhome)
	    	.addComponent(txtdiem9, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
	    	.addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
	    	
	        .addGap(18, 18, 18)	     
	        
	        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
	        .addGroup(layout.createSequentialGroup()
	        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.TRAILING)	       
	        .addGroup(GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
	        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE))))))
	        .addComponent(jScrollPane2, GroupLayout.PREFERRED_SIZE, 280, GroupLayout.PREFERRED_SIZE)
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
	        txthstruong.setText("");
	        txthk6.setText("");
	        txthl6.setText("");
	        txtdiem6.setText("");
	        txthk7.setText("");
	        txthl7.setText("");
	        txtdiem7.setText("");
	        txthk8.setText("");
	        txthl8.setText("");
	        txtdiem8.setText("");
	        txthk9.setText("");
	        txthl9.setText("");
	        txtdiem9.setText("");
	      
	    }

	// event save 
		private void btnSaveActionPerformed(ActionEvent evt) {
	        if (check()) {
	        	ttcap2 sv = new ttcap2();
	            sv.setMaHoSo(txtMaHoSo.getText());	            
	            sv.setHoTenHS(txtHoten.getText());
	            sv.setHStruong(txthstruong.getText());
	            sv.setHK6(txthk6.getText());
	            sv.setHL6(txthl6.getText());
	            sv.setDiem6(txtdiem6.getText());
	            sv.setHK7(txthk7.getText());
	            sv.setHL7(txthl7.getText());
	            sv.setDiem7(txtdiem7.getText());
	            sv.setHK8(txthk8.getText());
	            sv.setHL8(txthl8.getText());
	            sv.setDiem8(txtdiem8.getText());
	            sv.setHK9(txthk9.getText());
	            sv.setHL9(txthl9.getText());
	            sv.setDiem9(txtdiem9.getText());
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
	    private void btnDeleteActionPerformed(ActionEvent evt) {//GEN-FIRST:event_btnDeleteActionPerformed
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
	            ttcap2 sv = new ttcap2();
	            sv.setMaHoSo(txtMaHoSo.getText());	            
	            sv.setHoTenHS(txtHoten.getText());
	            sv.setHStruong(txthstruong.getText());
	            sv.setHK6(txthk6.getText());
	            sv.setHL6(txthl6.getText());
	            sv.setDiem6(txtdiem6.getText());
	            sv.setHK7(txthk7.getText());
	            sv.setHL7(txthl7.getText());
	            sv.setDiem7(txtdiem7.getText());
	            sv.setHK8(txthk8.getText());
	            sv.setHL8(txthl8.getText());
	            sv.setDiem8(txtdiem8.getText());
	            sv.setHK9(txthk9.getText());
	            sv.setHL9(txthl9.getText());
	            sv.setDiem9(txtdiem9.getText());
	            
	            list.add(sv);
	            fillTable();
        JOptionPane.showMessageDialog(rootPane, "Update Successful!");
	        }
	        }   
		
		private void btnsearchActionPerformed(ActionEvent evt) {
			try {
		    	String sql = "SELECT * FROM ttcap2 WHERE MaHoSo =?";
		    	
		    		 PreparedStatement ps = conn.prepareStatement(sql);
		    		 ps.setString(1, txtMaHoSo.getText());
			            ResultSet rs = ps.executeQuery();
			            
			           if(rs.next()) {
			        	   
			        	txtMaHoSo.setText(rs.getString("MaHoSo"));
			   	        txtHoten.setText(rs.getString("HoTenHS"));
			   	        txthstruong.setText(rs.getString("HStruong"));
			   	        txthk6.setText(rs.getString("HK6"));
			   	        txthl6.setText(rs.getString("HL6"));
			   	        txtdiem6.setText(rs.getString("Diem6"));
			   	    	txthk7.setText(rs.getString("HK7"));
			   	        txthl7.setText(rs.getString("HL7"));
			   	        txtdiem7.setText(rs.getString("Diem7"));
			   	        txthk8.setText(rs.getString("HK8"));
			   	        txthl8.setText(rs.getString("HL8"));
			   	        txtdiem8.setText(rs.getString("Diem8"));
			   	        txthk9.setText(rs.getString("HK9"));
			   	        txthl9.setText(rs.getString("HL9"));
			   	        txtdiem9.setText(rs.getString("Diem9"));
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
	    private JLabel lblhstruong;
	    private JLabel lblhk6;
	    private JLabel lblhl6;
	    private JLabel lbldiem6;
	    private JLabel lblhk7;
	    private JLabel lblhl7;
	    private JLabel lbldiem7;
	    private JLabel lblhk8;
	    private JLabel lblhl8;
	    private JLabel lbldiem8;
	    private JLabel lblhk9;
	    private JLabel lblhl9;
	    private JLabel lbldiem9;
	    
	    private JTextField txtMaHoSo;
	    private JTextField txtHoten;
	    private JTextField txthstruong;
	    private JTextField txthk6;
	    private JTextField txthl6;
	    private JTextField txtdiem6;	    
	    private JTextField txthk7;
	    private JTextField txthl7;
	    private JTextField txtdiem7;	
	    private JTextField txthk8;
	    private JTextField txthl8;
	    private JTextField txtdiem8;	
	    private JTextField txthk9;
	    private JTextField txthl9;
	    private JTextField txtdiem9;	
	    @Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub	
		}
	    public static void main(String[] args) {
			new ttcap2JFrame();
		}	
}