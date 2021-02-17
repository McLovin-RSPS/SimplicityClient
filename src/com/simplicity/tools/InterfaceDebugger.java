package com.simplicity.tools;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTree;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreeSelectionModel;

import com.simplicity.client.RSInterface;

public class InterfaceDebugger extends JFrame {
	
	private static final long serialVersionUID = 1L;

	private DefaultMutableTreeNode interfaces;
	
	private JTree tree;
	
	private JTable details;
	
	private List<String> detailsData = new ArrayList<String>(Arrays.asList(new String[] { "Id", "Type", "ParentID", "Seen on", "MouseOver", "Tooltip", "EnabledMessage", "DisabledMessage", "EnabledSprite", "DisabledSprite", "MediaID", "Children", "Width", "Height", "X", "Y", "valueIndexArray", "valueCompareType", "requiredValues" }));
	
	public InterfaceDebugger() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setTitle("Interface Debugger");
		setSize(550, 600);
		setLocationRelativeTo(null);
		setVisible(true);
		
		interfaces = new DefaultMutableTreeNode("Interfaces");
		
		tree = new JTree(interfaces);
		tree.getSelectionModel().setSelectionMode(TreeSelectionModel.SINGLE_TREE_SELECTION);
		
		tree.addTreeSelectionListener(new TreeSelectionListener() {
			
			@Override
			public void valueChanged(TreeSelectionEvent e) {
				DefaultMutableTreeNode node = (DefaultMutableTreeNode) tree.getLastSelectedPathComponent();
				
				if (node == null) {
					return;
				}
				
				if (node.getParent() != null) {
					int interfaceId = Integer.parseInt(node.getUserObject().toString());
					
					loadDetails(RSInterface.interfaceCache[interfaceId]);
				}
				
			}
		});
		
		JScrollPane treeView = new JScrollPane(tree);
		treeView.setPreferredSize(new Dimension(200, 500));
		
		JPanel left = new JPanel();
		left.add(treeView);
		
		Object rowData[][] = new Object[detailsData.size()][2];
		
		for (int i = 0; i < detailsData.size(); i++) {
			String name = detailsData.get(i);
			rowData[i][0] = name;
			rowData[i][1] = "";
		}

		Object columnNames[] = { "", "" };
		
		details = new JTable(rowData, columnNames);
		details.setTableHeader(null);
		
		JScrollPane tableView = new JScrollPane(details);
		tableView.setPreferredSize(new Dimension(300, 500));

		JPanel right = new JPanel();
		right.add(tableView);
		
		getContentPane().setLayout(new FlowLayout(FlowLayout.LEFT));
		getContentPane().add(left);
		getContentPane().add(right);
		
		loadInterfaces();
	}
	
	public void loadDetails(RSInterface rsi) {
		int column = 0;
		details.getModel().setValueAt(rsi.id, column++, 1);
		details.getModel().setValueAt(getType(rsi.type), column++, 1);
		details.getModel().setValueAt(rsi.parentID, column++, 1);
		List<Integer> seenOn = new ArrayList<>();
		for (int i = 0; i < RSInterface.interfaceCache.length; i++) {
			RSInterface on = RSInterface.interfaceCache[i];
			
			if (on != null && on.children != null) {
				for (int j = 0; j < on.children.length; j++) {
					if (on.children[j] == rsi.id) {
						seenOn.add(on.id);
					}
				}
			}
		}
		details.getModel().setValueAt(Arrays.asList(seenOn.toArray()), column++, 1);
		details.getModel().setValueAt(rsi.interfaceShown, column++, 1);
		details.getModel().setValueAt(rsi.enabledMessage != null ? rsi.enabledMessage : "", column++, 1);
		details.getModel().setValueAt(rsi.message != null ? rsi.message : "", column++, 1);
		details.getModel().setValueAt(rsi.tooltip != null ? rsi.tooltip : "", column++, 1);
		details.getModel().setValueAt(rsi.enabledSprite != null ? rsi.enabledSprite : -1, column++, 1);
		details.getModel().setValueAt(rsi.disabledSprite != null ? rsi.disabledSprite : -1, column++, 1);
		details.getModel().setValueAt(rsi.mediaID, column++, 1);
		details.getModel().setValueAt(rsi.children != null ? rsi.children.length : 0, column++, 1);
		details.getModel().setValueAt(rsi.width, column++, 1);
		details.getModel().setValueAt(rsi.height, column++, 1);
		details.getModel().setValueAt(-1, column++, 1);
		details.getModel().setValueAt(-1, column++, 1);
		if (rsi.id != rsi.parentID) {
			RSInterface parent = RSInterface.interfaceCache[rsi.parentID];

			if (parent != null) {
				for (int i = 0; i < parent.children.length; i++) {
					if (parent.children[i] == rsi.id) {
						details.getModel().setValueAt(parent.childX[i], column - 1, 1);
						details.getModel().setValueAt(parent.childY[i], column - 2, 1);
						break;
					}
				}
			}
		}
		details.getModel().setValueAt(Arrays.toString(rsi.valueIndexArray != null && rsi.valueIndexArray.length > 2 ? rsi.valueIndexArray[2] : null), column++, 1);
		details.getModel().setValueAt(Arrays.toString(rsi.valueCompareType), column++, 1);
		details.getModel().setValueAt(Arrays.toString(rsi.requiredValues), column++, 1);
	}
	
	public void loadInterfaces() {
		for (RSInterface rsi : RSInterface.interfaceCache) {
			
			if (rsi == null) {
				continue;
			}
			
			DefaultMutableTreeNode interfaceNode = new DefaultMutableTreeNode(rsi.id);
			
			if (rsi.id == rsi.parentID) {
				interfaces.add(interfaceNode);
			}
			
			if (rsi.children == null) {
				continue;
			}
			
			for (int i = 0; i < rsi.children.length; i++) {
				if (rsi.children[i] < 0) {
					continue;
				}
				
				RSInterface child = RSInterface.interfaceCache[rsi.children[i]];

				if (child != null) {
					DefaultMutableTreeNode childNode = new DefaultMutableTreeNode(child.id);
					
					interfaceNode.add(childNode);
					
					RSInterface child2 = RSInterface.interfaceCache[child.id];
					
					if (child2.children != null) {
						for (int j = 0; j < child2.children.length; j++) {
							RSInterface child3 = RSInterface.interfaceCache[child2.children[j]];
							
							if (child3 != null) {
								DefaultMutableTreeNode childNode2 = new DefaultMutableTreeNode(child3.id);
								
								childNode.add(childNode2);
								
								if (child3.children != null) {
									for (int k = 0; k < child3.children.length; k++) {
										childNode2.add(new DefaultMutableTreeNode(child3.children[k]));
									}
								}
							}
						}
					}
				}
			}
			
		}
		
		tree.expandPath(tree.getPathForRow(0));
	}
	
	public String getType(int type) {
		switch (type) {
		case 0:
			return "Interface";
		case 4:
			return "Text";
		case 5:
		case 10:
			return "Sprite";
		case 6:
			return "Model";
		case 9:
			return "Tooltip";
		case 16:
			return "Input field";
		case 27:
			return "Dark box";
		}

		return Integer.toString(type);
	}

}
