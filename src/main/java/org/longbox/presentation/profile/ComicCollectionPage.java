package org.longbox.presentation.profile;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.JTable;
import javax.swing.JTextField;

public class ComicCollectionPage extends JPanel implements ActionListener{

	private static final long serialVersionUID = 1L;
	
	private String[] comics = {"Sanctuary", "Zot!", "Elementals", "RobotTech", "Mars", "American Flagg!", "Nexus", "Appleseed", "Battle Pope"};
	private JPanel panel;
	private JLabel comicCollectionTitle;
	private JLabel lblNewLabel_1;
	private JSeparator separator;
	private JComboBox<String> comboBox;
	private JScrollPane scrollPane;
	private String currentItem;
	private JTable table;
	private JTextField textField;
	
	
	ComicCollectionPage() {
		initComicCollectionPage();
	}

	
	
	private void initComicCollectionPage() {

		setBounds(10, 47, 1164, 803);
		setLayout(new BorderLayout());
		
		panel = new JPanel();
	    panel.setLayout(null);
		
		comicCollectionTitle = new JLabel("Comic Collection");
		comicCollectionTitle.setFont(new Font("Tahoma", Font.PLAIN, 30));
		comicCollectionTitle.setHorizontalAlignment(SwingConstants.CENTER);
		comicCollectionTitle.setBounds(396, 11, 372, 43);
		panel.add(comicCollectionTitle);
		
		lblNewLabel_1 = new JLabel("Sort By:");
		lblNewLabel_1.setBounds(935, 67, 60, 14);
		panel.add(lblNewLabel_1);
		
		separator = new JSeparator();
		separator.setBounds(10, 92, 1144, 14);
		panel.add(separator);
		
		comboBox = new JComboBox<String>();
		comboBox.setBounds(992, 62, 162, 22);
		panel.add(comboBox);
		
		comboBox.addItem("A-Z");
		comboBox.addItem("Z-A");
		comboBox.addItem("Date Added (Recent)");
		comboBox.addItem("Date Added (Oldest)");
		comboBox.addItem("Publication Date (Recent)");
		comboBox.addItem("Publication Date (Oldest)");
		comboBox.addItemListener(e -> {
			currentItem = (String)comboBox.getSelectedItem();
			if(currentItem == "A-Z") {
				comics = sortAZ(comics);
				this.invalidate();
				this.repaint();
			}
		});
		
		add(panel, BorderLayout.CENTER);
		
		scrollPane = new JScrollPane();
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.setBounds(10, 110, 1144, 683);
		panel.add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		textField = new JTextField();
		textField.setBounds(116, 62, 213, 22);
		panel.add(textField);
		textField.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("Search Collection:");
		lblNewLabel.setBounds(10, 66, 120, 13);
		panel.add(lblNewLabel);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		String currentItem = (String)comboBox.getSelectedItem();
		if (e.getSource() == comboBox) {
			System.out.println("Clicked!");
		}
	}
	
	public String[] sortAZ(String[] ls) {
		String[] listAZ = new String[ls.length];
		for (int i = 0; i < ls.length; i++) {
			listAZ[i] = ls[i];
		}
		Arrays.sort(listAZ);
		return listAZ;
	}
}