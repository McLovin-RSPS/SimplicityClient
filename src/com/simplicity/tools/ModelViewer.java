package com.simplicity.tools;

import java.awt.Color;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.Vector;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JTree;
import javax.swing.SwingUtilities;
import javax.swing.border.EmptyBorder;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumnModel;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreeSelectionModel;

import com.simplicity.client.Model;
import com.simplicity.client.cache.DataType;
import com.simplicity.client.cache.definitions.ItemDefinition;
import com.simplicity.task.Task;
import com.simplicity.task.TaskManager;

/**
 * A tool used for looking up model colors.
 * 
 * @author Blake
 *
 */
public class ModelViewer extends JFrame {
	
	private static final long serialVersionUID = 1L;

	private DefaultMutableTreeNode objects;
	
	private DefaultMutableTreeNode regular;
	private DefaultMutableTreeNode osrs;
	
	private JTree tree;

	private JPanel contentPane;
	private JTextField textField;
	
	private JTable table;
	private TableColumnModel tableColumnModel;
	private String[] columnNamesArr;
	private ArrayList<String> columnNamesList;
	private JScrollPane scrollPane2;
	private String[][] data;
	private ColorTableModel defaultTableModel;
	
	/**
	 * Create the frame.
	 */
	public ModelViewer() {
		setTitle("Model Colors");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setSize(475, 518);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblFilter = new JLabel("Search:");
		lblFilter.setBounds(10, 11, 45, 14);
		contentPane.add(lblFilter);
		
		textField = new JTextField();
		textField.setBounds(55, 8, 86, 20);
		contentPane.add(textField);
		textField.setColumns(10);
		
		objects = new DefaultMutableTreeNode("Items");
		
		JButton btnSubmit = new JButton("Submit");
		btnSubmit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String text = textField.getText().toLowerCase();
				
				if (text.isEmpty()) {
					objects.removeAllChildren();
					clearTree();
					resetDetails();
					init();
					return;
				}
				
				loadSearch(text);
			}
		});
		btnSubmit.setBounds(384, 7, 65, 23);
		contentPane.add(btnSubmit);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 36, 131, 431);
		contentPane.add(scrollPane);
		
		tree = new JTree(objects);
		scrollPane.setViewportView(tree);
		tree.getSelectionModel().setSelectionMode(TreeSelectionModel.SINGLE_TREE_SELECTION);
		tree.addTreeSelectionListener(new TreeSelectionListener() {
			
			@Override
			public void valueChanged(TreeSelectionEvent e) {
				DefaultMutableTreeNode node = (DefaultMutableTreeNode) tree.getLastSelectedPathComponent();
				
				if (node == null || node.getParent() == null || node.getParent().equals(objects)) {
					return;
				}
				
				if (node.getParent() != null) {
					
					String s = node.getUserObject().toString();
					
					int objectId = Integer.parseInt(s);
					
					if (node.getParent().equals(osrs)) {
						objectId += ItemDefinition.OSRS_ITEMS_OFFSET;
					}
					
					DataType type = node.getParent().equals(osrs) ? DataType.OLDSCHOOL : DataType.REGULAR;
					
					loadDetails(objectId, type);
				}
				
			}
		});
		
		columnNamesList = new ArrayList<String>();
		columnNamesList.add("Id");
		columnNamesList.add("Hex");
		columnNamesList.add("Color");
		
		data = new String[1][columnNamesList.size()];
		columnNamesArr = new String[columnNamesList.size()];
		for(int i=0;i<columnNamesList.size();i++)
		{
			columnNamesArr[i] = columnNamesList.get(i);
			data[0][i]="";
		}
		
		defaultTableModel = new ColorTableModel(data, columnNamesArr);
		table = new JTable(defaultTableModel);
		table.setBounds(151, 37, 298, 131);
		tableColumnModel = table.getColumnModel();
		table.setTableHeader(new JTableHeader(tableColumnModel));
		
		for(int i=0;i<columnNamesList.size();i++)
		{
			tableColumnModel.getColumn(i).setPreferredWidth(columnNamesList.get(i).length());
		}
		table.setPreferredScrollableViewportSize(table.getPreferredSize());
		scrollPane2 = new JScrollPane(table);
		scrollPane2.setBounds(151, 37, 298, 431);
		
		SwingUtilities.invokeLater(() -> {
			table.setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {
				@Override
			    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
			        ColorTableModel model = (ColorTableModel) table.getModel();
			        Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
			        
			        Object val = model.getValueAt(row, 0);
			        
			        if (!(val instanceof Integer)) {
			        	return c;
			        }
			        
			        int rgb = ItemDefinition.RS2HSB_to_RGB((int) val);
			        
			        if (column == 2) {
			        	c.setBackground(new Color(rgb));
			        } else {
			        	c.setBackground(new Color(0, 0, 0, 0));
			        }
			        
			        return c;
			    }
			});
		});

		contentPane.add(table.getTableHeader());
		contentPane.add(scrollPane2);
		
		init();
	}
	
	public void createNodes(DefaultMutableTreeNode root) {
		regular = new DefaultMutableTreeNode("Regular");
		root.add(regular);
		
		osrs = new DefaultMutableTreeNode("OSRS");
		root.add(osrs);
	}
	
	private Set<Integer> attemptsReg = new HashSet<>();
	private Set<Integer> attemptsOSRS = new HashSet<>();
	
	public void loadDetails(int id, DataType type) {
		resetDetails();
		
		Model model = Model.fetchModel(id, type);
		
		if (model == null) {
			
			boolean fetch = false;
			
			if (type == DataType.REGULAR && !attemptsReg.contains(id)) {
				attemptsReg.add(id);
				fetch = true;
			} else if (type == DataType.OLDSCHOOL && attemptsOSRS.contains(id)) {
				attemptsOSRS.add(id);
				fetch = true;
			}
			
			if (fetch) {
				TaskManager.submit(new Task(1000) {

					@Override
					public void execute() throws IOException {
						System.out.println("Fetching: " + id + " - " + type);
						loadDetails(id, type);
						stop();
					}
					
				});
			}
			
			return;
		}
		
		Set<Integer> colors = new HashSet<>();
		Arrays.stream(model.face_color).forEach(i -> colors.add(i));
		
		for (int color : colors) {
			int rgb = ItemDefinition.RS2HSB_to_RGB(color);
			Color col = new Color(rgb);
			String hex = String.format("#%02X%02X%02X", col.getRed(), col.getGreen(), col.getBlue());
			
			Vector rd = new Vector<>();
			rd.add(color);
			rd.add(hex);
			rd.add("");
			
			defaultTableModel.addRow(rd);
			table.validate();
		}
	}
	
	public void init() {
		createNodes(objects);
		
		for (int i = 0; i < LENGTH_REGULAR; i++) {
			regular.add(new DefaultMutableTreeNode(i));
		}
		
		for (int i = 0; i < LENGTH_OSRS; i++) {
			osrs.add(new DefaultMutableTreeNode(i));
		}
		
		tree.expandPath(tree.getPathForRow(0));
	}
	
	public void clearTree() {
		DefaultTreeModel model = (DefaultTreeModel) tree.getModel();
		
		regular.removeAllChildren();
		
		osrs.removeAllChildren();
		
		model.reload();
	}
	
	public void resetDetails() {
		defaultTableModel.setRowCount(0);
	}
	
	public static final int LENGTH_REGULAR = 80_000;
	public static final int LENGTH_OSRS = 65_000;
	
	public void loadSearch(String query) {
		if (query.isEmpty()) {
			init();
			return;
		}
		
		clearTree();
		
		resetDetails();
		
		int searchedId = -1;
		
		try {
			searchedId = Integer.parseInt(query);
		} catch (Exception e) {
			JOptionPane.showMessageDialog(
			        null, "Invalid ID specified.", "Error", JOptionPane.ERROR_MESSAGE);
			return;
		}
		
		for (int i = 0; i < LENGTH_REGULAR; i++) {
			if (!query.isEmpty()) {
				if (i != searchedId) {
					continue;
				}
			}
			
			regular.add(new DefaultMutableTreeNode(i));
		}
		
		for (int i = 0; i < LENGTH_OSRS; i++) {
			ItemDefinition def = null;
			
			try {
				def = ItemDefinition.forID(i);
			} catch (Exception e) {
				
			}
			
			if (def == null) {
				continue;
			}
			
			if (!query.isEmpty()) {
				if (i != searchedId) {
					continue;
				}
			}
			
			osrs.add(new DefaultMutableTreeNode(i));
		}
		
		tree.expandPath(tree.getPathForRow(0));
	}
	
	static class ColorTableModel extends DefaultTableModel {
		
		private static final long serialVersionUID = 1L;

		public ColorTableModel(Object[][] data, Object[] columnNames) {
			super(data, columnNames);
		}
		
	    @Override
	    public boolean isCellEditable(int row, int column) {
	    	return column != 2;
	    }

	}
	
}
