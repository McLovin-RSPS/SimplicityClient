package com.simplicity.tools;

import java.awt.Color;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Vector;
import java.util.concurrent.TimeUnit;

import javax.swing.AbstractAction;
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

import com.simplicity.client.Client;
import com.simplicity.client.Model;
import com.simplicity.client.cache.DataType;
import com.simplicity.client.cache.definitions.ItemDefinition;
import com.simplicity.client.cache.definitions.MobDefinition;
import com.simplicity.client.cache.definitions.ObjectDefinition;
import com.simplicity.task.Task;
import com.simplicity.task.TaskManager;
import com.simplicity.tools.colortools.WindowSelectColor;
import com.simplicity.tools.util.ModelColorMapping;
import com.simplicity.tools.util.TableCellListener;
import com.simplicity.util.StringUtils;

import javafx.application.Platform;
import javafx.scene.input.Clipboard;
import javafx.scene.input.ClipboardContent;

/**
 * A tool used for looking up model colors.
 * 
 * @author Blake
 *
 */
public class ModelViewer extends JFrame {
	
	private Map<Integer, List<Integer>> origColors = new HashMap<>();
	
	private int selectedId;
	private DataType selectedType;
	private boolean selectedIsItem;
	private Model selected;
	private int selectedRow;
	
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
	public ModelViewer(boolean compact) {
		setTitle("Model Colors");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setSize(compact ? 325 : 475, compact ? 508 : 558);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		if (!compact) {
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
			btnSubmit.setBounds(150, 7, 75, 23);
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
						if(node.getParent().equals(osrs)) {
							System.out.println("ITEM OSRS: " + objectId);
						}
						DataType type = node.getParent().equals(osrs) ? DataType.OLDSCHOOL : DataType.REGULAR;
						
						loadDetails(objectId, type, true);
					}
					
				}
			});
		}
		
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
		table.setBounds(compact ? 5 : 151, compact ? 5 : 37, 298, 131);
		tableColumnModel = table.getColumnModel();
		table.setTableHeader(new JTableHeader(tableColumnModel));
		
		for(int i=0;i<columnNamesList.size();i++)
		{
			tableColumnModel.getColumn(i).setPreferredWidth(columnNamesList.get(i).length());
		}
		table.setPreferredScrollableViewportSize(table.getPreferredSize());
		scrollPane2 = new JScrollPane(table);
		scrollPane2.setBounds(compact ? 5 : 151, compact ? 5 : 37, 298, 431);
		
		JButton copy = new JButton("Copy");
		copy.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				int rows = defaultTableModel.getRowCount();
				
				int[] colors = new int[rows];
				
				for (int i = 0; i < rows; i++) {
					colors[i] = Integer.parseInt(defaultTableModel.getValueAt(i, 0).toString());
				}
				
				String origColorString;
				
				if (origColors.containsKey(selectedId)) {
					origColorString = StringUtils.intListToString(origColors.get(selectedId), true);
				} else {
					origColorString = StringUtils.arrayToString(colors, true);
				}
				
				String orig = "originalColours = new int[] " + origColorString + ";";
				String dest = "destColours = new int[] " + StringUtils.arrayToString(colors, true) + ";";
				System.out.println(orig);
				System.out.println(dest);
			}
		});
		copy.setBounds(compact ? 5 : 150, compact ? 440 : 473, 75, 23);
		contentPane.add(copy);
		
		JButton reload = new JButton("Reload");
		reload.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if (selected == null) {
					return;
				}
				
				reloadModels();
			}
		});
		reload.setBounds(compact ? 100 : 245, compact ? 440 : 473, 75, 23);
		contentPane.add(reload);
		
		JButton revert = new JButton("Revert");
		revert.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if (selected == null || !Model.DEV_COLOR_MAPPINGS.containsKey(selectedId)) {
					return;
				}
					
				ModelColorMapping mapping = Model.DEV_COLOR_MAPPINGS.get(selectedId);
				
				selected.face_color = mapping.getOriginalFaceColors();
				
				Model.DEV_COLOR_MAPPINGS.remove(selectedId);
				origColors.remove(selectedId);
				reloadModels();
				loadDetails(selectedId, selectedType, selectedIsItem);
				loadDetails(selectedId, selectedType, selectedIsItem);
			}
		});
		revert.setBounds(compact ? 200 : 345, compact ? 440 : 473, 75, 23);
		contentPane.add(revert);
		
		ModelViewer instance = this;
		
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int row = table.getSelectedRow();
				int col = table.getSelectedColumn();
				
				if (col == 2) {
					int color = Integer.parseInt(table.getValueAt(row, 0).toString());
					
					int rgb = ItemDefinition.RS2HSB_to_RGB((int) color);
					
					SwingUtilities.invokeLater(() -> {
						WindowSelectColor rs2Picker = new WindowSelectColor();
						rs2Picker.setModelViewer(instance);
						rs2Picker.setSelectedColor(color);
	            		rs2Picker.setVisible(true);
	            		selectedRow = row;
	            	});
				}
			}
		});
		
		table.addPropertyChangeListener(new TableCellListener(table, new AbstractAction() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				TableCellListener listener = (TableCellListener) e.getSource();
				int row = listener.getRow();
				int col = listener.getColumn();
				
				if (col == 0 && listener.getOldValue() != listener.getNewValue()) { // RS2 color edited
					int prev = Integer.parseInt(listener.getOldValue().toString());
					int next = Integer.parseInt(listener.getNewValue().toString());
					colorEdited(prev, next, row, col);
				}
			}
		}));
		
		//table.putClientProperty("terminateEditOnFocusLost", Boolean.TRUE); 
		
		SwingUtilities.invokeLater(() -> {
			table.setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {
				@Override
			    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
			        ColorTableModel model = (ColorTableModel) table.getModel();
			        Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
			        
			        Object val = model.getValueAt(row, 0);
			        
			        if (val.toString().isEmpty()) {
			        	return c;
			        }
			        
			        int rgb = ItemDefinition.RS2HSB_to_RGB(Integer.parseInt(val.toString()));
			        
			        if (column == 2) {
			        	c.setBackground(new Color(rgb));
			        } else {
			        	c.setBackground(table.getBackground());
			        }
			        
			        return c;
			    }
			});
		});

		contentPane.add(table.getTableHeader());
		contentPane.add(scrollPane2);
		
		if (!compact) {
			init();
		}
	}
	
	private void colorEdited(int oldValue, int newValue, int row, int col) {
		ColorTableModel model = (ColorTableModel) table.getModel();
		model.fireTableDataChanged();
		table.setValueAt(newValue, row, col);
		
		ModelColorMapping mapping = null;
		
		if (Model.DEV_COLOR_MAPPINGS.containsKey(selectedId)) {
			mapping = Model.DEV_COLOR_MAPPINGS.get(selectedId);
		} else {
			mapping = new ModelColorMapping(selected.face_color);
		}
		
		int prevMapping = origColors.containsKey(selectedId) ? origColors.get(selectedId).get(row) : oldValue;
		
		mapping.recolor(prevMapping, newValue);
		Model.DEV_COLOR_MAPPINGS.put(selectedId, mapping);
	}
	
	public void setColor(int color) {
		int oldTableValue = Integer.parseInt(table.getValueAt(selectedRow, 0).toString());
		
		int prevMapping = origColors.containsKey(selectedId) ? origColors.get(selectedId).get(selectedRow) : oldTableValue;
		
		colorEdited(prevMapping, color, selectedRow, 0);
	}
	
	public void createNodes(DefaultMutableTreeNode root) {
		regular = new DefaultMutableTreeNode("Regular");
		root.add(regular);
		
		osrs = new DefaultMutableTreeNode("OSRS");
		root.add(osrs);
	}
	
	private Set<Integer> attemptsReg = new HashSet<>();
	private Set<Integer> attemptsOSRS = new HashSet<>();
	
	public void loadDetails(int id, DataType type, boolean isItemId) {
		resetDetails();
		
		Model model = isItemId ? ItemDefinition.forID(id).getItemModelFinalised(1) : Model.fetchModel(id, type);
		
		if (model == null) {
			boolean fetch = false;
			
			if (type == DataType.REGULAR && !attemptsReg.contains(id)) {
				attemptsReg.add(id);
				
				fetch = true;
			} else if (type == DataType.OLDSCHOOL && !attemptsOSRS.contains(id)) {
				attemptsOSRS.add(id);
				fetch = true;
			}
			
			if (fetch) {
				TaskManager.submit(new Task(1000) {

					@Override
					public void execute() throws IOException {
						System.out.println("Fetching: " + id + " - " + type);
						loadDetails(id, type, isItemId);
						stop();
					}
					
				});
			}
			
			return;
		}
		
		Set<Integer> colors = getColors(model);
		
		List<Integer> origMappings = null;
		
		if (origColors.containsKey(id)) {
			origMappings = origColors.get(id);
		} else {
			origMappings = new ArrayList<>();
		}
		
		for (int color : colors) {
			int rgb = ItemDefinition.RS2HSB_to_RGB(color);
			
			Vector rd = new Vector<>();
			rd.add(color);
			rd.add(rgbToHexString(rgb));
			rd.add("");
			
			defaultTableModel.addRow(rd);
			origMappings.add(color);
			table.validate();
		}
		
		origColors.put(id, origMappings);
		selectedId = id;
		selectedType = type;
		selectedIsItem = isItemId;
		selected = model;
	}
	
	public static String rgbToHexString(int rgb) {
		Color col = new Color(rgb);
		
		return String.format("#%02X%02X%02X", col.getRed(), col.getGreen(), col.getBlue());
	}
	
	public Set<Integer> getColors(int id, DataType type) {
		Set<Integer> colors = new HashSet<>();
		
        Model model = ItemDefinition.forID(id).getItemModelFinalised(1);
        
        if (model != null && model.face_color != null) {
	        for (int i : model.face_color) {
	            colors.add(i);
	        }
        }
        
		return colors;
	}
	
	public Set<Integer> getColors(Model model) {
		Set<Integer> colors = new HashSet<>();
        
        if (model != null && model.face_color != null) {
	        for (int i : model.face_color) {
	            colors.add(i);
	        }
        }
        
		return colors;
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
		origColors = new HashMap<>();
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
	
	public int getSelectedRow() {
		return selectedRow;
	}
	
	private void reloadModels() {
		Model.reinit();
	    ItemDefinition.modelCache.clear();
	    ItemDefinition.modelCacheCustom.clear();
	    ItemDefinition.modelCacheOSRS.clear();
	    ItemDefinition.spriteCache.clear();
	    MobDefinition.reloadCache();
	    ObjectDefinition.modelCache.clear();
	    ObjectDefinition.osrsModelCache.clear();
	    ObjectDefinition.completedModelCache.clear();
	    ObjectDefinition.completedOSRSModelCache.clear();
	    ObjectDefinition.reloadCache();
	    Client.myPlayer.clearCache();
	}
	
	public static void of(int modelID, DataType type) {
		if (modelID == -1 || modelID == 65535) {
			JOptionPane.showMessageDialog(null, "Invalid model.", "Error", 0, null);
			return;
		}
		
		SwingUtilities.invokeLater(() -> {
			ModelViewer v = new ModelViewer(true);
			v.loadDetails(modelID, type, false);
			v.setTitle(modelID + " - " + type);
			v.setVisible(true);
		});
	}
	
	static class ColorTableModel extends DefaultTableModel {
		
		private static final long serialVersionUID = 1L;

		public ColorTableModel(Object[][] data, Object[] columnNames) {
			super(data, columnNames);
		}
		
		@Override
		public void setValueAt(Object aValue, int row, int column) {
			if (column == 0) { // Updating HEX whenever RS2 Color is updated.
				int rgb = ItemDefinition.RS2HSB_to_RGB(Integer.parseInt(aValue.toString()));
				
				setValueAt(rgbToHexString(rgb), row, 1);
			}
			
			super.setValueAt(aValue, row, column);
		}
		
	    @Override
	    public boolean isCellEditable(int row, int column) {
	    	return column != 2;
	    }

	}
	
}
