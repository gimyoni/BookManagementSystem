package BMS;

import java.awt.Color; //JFrame import
import java.awt.Component;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableCellRenderer;

import BMS.Test.TableCell;

import javax.swing.ScrollPaneConstants;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.AbstractCellEditor;
import javax.swing.JButton;
import javax.swing.JTextPane;
import javax.swing.JTextField;
import java.awt.BorderLayout;

public class Return extends JFrame {
	
	private Object[] objColNms = new Object[] { "Title", "Return" }; //JTable 생성을 위한 컬럼생성
	 
    public static void main(String[] args) {
        new Return();
    }
 
    private JFrame jf;
    private DefaultTableModel dtm;
    private JTable jtable;
    private JScrollPane jsp;

 
    public Return() {
        jf = new JFrame("반납");
        jf.getContentPane().setBackground(Color.BLACK);
        jf.setTitle("\uBC18\uB0A9");
        jf.setLocationRelativeTo(null);
        jf.setSize(800, 600);
 
        dtm = new DefaultTableModel(new Object[][] {}, objColNms);
        jtable = new JTable(dtm);
        jsp = new JScrollPane(jtable);
 
        jtable.getColumnModel().getColumn(1).setCellRenderer(new TableCell());
        jtable.getColumnModel().getColumn(1).setCellEditor(new TableCell());
        jf.getContentPane().setLayout(new BorderLayout(0, 0));
 
        jf.getContentPane().add(jsp);
       
        jf.setVisible(true);
        
        try {
			String sql = "SELECT * FROM client"; //데이터 베이스에서 client 값 가져옴
			Connection conn= DriverManager.getConnection(Main.url, Main.user, Main.password);
			PreparedStatement pstmt = conn.prepareStatement(sql);
			ResultSet rs= pstmt.executeQuery();
			
			while(rs.next()) {
				String title = rs.getString("대출한책");	
				Object data[] = {};
				dtm.addRow(data);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
    }
 
    @SuppressWarnings("rawtypes")
    public void JTableRemoveRow() {
    	
        int row = jtable.getSelectedRow();
        if (row == -1) //열이 -1 값을 가지면 리턴
            return;
 
        System.out.println(row); //열 출력
        
        DefaultTableModel model = (DefaultTableModel) jtable.getModel();
        model.removeRow(row); //열 지우기
 
        int rowCnt = jtable.getRowCount(); //열 개수 세기위해 선언
 
        if (rowCnt > 0) { //열 개수가 양수이면
            Vector vector = model.getDataVector();
            Object[][] objData = new Object[vector.size()][((Vector) vector.get(0)).size()];
            for (int i = 0; i < vector.size(); i++) { 
                Vector vec = (Vector) vector.get(i);
                for (int j = 0; j < vec.size(); j++) {
                    objData[i][j] = vec.get(j);
                }
            }
 
            DefaultTableModel clonModel = new DefaultTableModel(objData, objColNms);
            JTable clonTable = new JTable(clonModel);
 
            jf.getContentPane().removeAll();
 
            jtable = clonTable;
            jsp = new JScrollPane(jtable);
            jtable.getColumnModel().getColumn(1).setCellRenderer(new TableCell()); //첫번째 열에 넣음
            jtable.getColumnModel().getColumn(1).setCellEditor(new TableCell()); //첫번째 열에 넣음
 
            jf.getContentPane().add(jsp);
            jf.revalidate();
            jf.repaint();
        } else {
            DefaultTableModel clonModel = new DefaultTableModel(null, objColNms);
            JTable clonTable = new JTable(clonModel);
 
            jf.getContentPane().removeAll();
 
            jtable = clonTable;
            jsp = new JScrollPane(jtable);
 
            jf.getContentPane().add(jsp);
            jf.revalidate();
            jf.repaint();
        }
    } // end public void JTableRemoveRow()
 
    class TableCell extends AbstractCellEditor implements TableCellEditor, TableCellRenderer {
        JButton jb;
 
        public TableCell() {
            jb = new JButton("반납"); //반납 버튼
            jb.addActionListener(e -> {
                JTableRemoveRow(); //열 지우기
            });
        }
 
        @Override
        public Object getCellEditorValue() {
            return null;
        }
 
        @Override
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,
                int row, int column) {
            return jb;
        }
 
        @Override
        public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row,
                int column) {
            return jb;
        }
    }
}
