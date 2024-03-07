package org.longbox.presentation.profile;

import lombok.Getter;
import lombok.Setter;
import org.longbox.businesslogic.UserSession;
import org.longbox.businesslogic.utils.ComicBookSearch;
import org.longbox.domainobjects.dto.ComicBookDTO;
import org.longbox.persistence.dao.ComicBookDaoImpl;
import org.longbox.persistence.dao.UserDaoImpl;
import org.longbox.presentation.comicbook.ComicBookSearchResultsFrame;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;
import javax.swing.JButton;
import java.awt.Color;
@Getter
@Setter
public class ComicRepositoryPanel extends JPanel implements ActionListener{

	private static final long serialVersionUID = 1L;
	private static final String DEFAULT_FONT = "Calibri";
	public JPanel panel;
	private JLabel comicRepositoryTitle;
	private JLabel lblNewLabel_1;
	private JSeparator separator;
	private JButton refreshButton;
	private JComboBox<String> comboBox;
	public JScrollPane scrollPane;
	private String currentItem;
	private JTable comicBookTable;
	private JTextField textField;
	private JComboBox<String> typeSelection;
	private ComicBookTableModel comicBookTableModel;
	TableRowSorter<TableModel> sorter;	
	ComicBookDaoImpl comicBookDaoImpl;
	private UserSession userSession;

	public ComicRepositoryPanel() {
		initComicCollectionPage();
	}

	private void initComicCollectionPage() {

		setBounds(10, 47, 1164, 803);
		setLayout(new BorderLayout());
		
		panel = new JPanel();
	    panel.setLayout(null);
		
		comicRepositoryTitle = new JLabel("Comic Repository");
		comicRepositoryTitle.setFont(new Font(DEFAULT_FONT, Font.PLAIN, 30));
		comicRepositoryTitle.setHorizontalAlignment(SwingConstants.CENTER);
		comicRepositoryTitle.setBounds(396, 11, 372, 43);
		panel.add(comicRepositoryTitle);

		separator = new JSeparator();
		separator.setBounds(10, 92, 1144, 14);
		panel.add(separator);

		add(panel, BorderLayout.CENTER);
		
		this.reloadTable();
			
		typeSelection = new JComboBox<String>();
		typeSelection.setBounds(307, 62, 160, 22);
		
		typeSelection.addItem("Title");
		typeSelection.addItem("Author");
		typeSelection.addItem("Artist");
		typeSelection.addItem("Genre");
		typeSelection.addItem("Publisher");
		typeSelection.addItem("Year");
		
		panel.add(typeSelection);
		
		textField = new JTextField();
		textField.setBounds(90, 62, 213, 22);
		panel.add(textField);
		textField.setColumns(10);
		
		textField.addActionListener(this);
		
		JLabel lblNewLabel = new JLabel("Search Repo:");
		lblNewLabel.setBounds(10, 66, 120, 13);
		panel.add(lblNewLabel);
		
		refreshButton = new JButton("Refresh");
		refreshButton.setForeground(Color.BLACK);
		refreshButton.setBounds(1065, 62, 89, 23);
		refreshButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				panel.remove(scrollPane);
				reloadTable();
			}
			
		});
		panel.add(refreshButton);
	}
	
	@Override
    public void actionPerformed(ActionEvent e) {
		if (e.getSource() == textField && !textField.getText().isEmpty()) {
			String searchBy = typeSelection.getSelectedItem().toString();
			String target = textField.getText();
			List<ComicBookDTO> searchResults = null;
			
			searchResults = ComicBookSearch.comicAdvancedSearch(searchBy, target, searchResults, comicBookDaoImpl.getAllComicBooks());
			
			System.out.println("Search for: " + textField.getText() + " in "  + searchBy);
//			switch(searchBy) {
//				case "Title":
//					searchResults = ComicBookSearch.searchComicBookByTitle(comicBookDaoImpl.getAllComicBooks(), textField.getText());
//					break;
//				case "Author":
//					searchResults = ComicBookSearch.searchComicBookByAuthor(comicBookDaoImpl.getAllComicBooks(), textField.getText());
//					break;
//				case "Artist":
//					searchResults = ComicBookSearch.searchComicBookByArtist(comicBookDaoImpl.getAllComicBooks(), textField.getText());
//					break;
//				case "Genre":
//					searchResults = ComicBookSearch.searchComicBookByGenre(comicBookDaoImpl.getAllComicBooks(), textField.getText());
//					break;
//				case "Publisher":
//					searchResults = ComicBookSearch.searchComicBookByPublisher(comicBookDaoImpl.getAllComicBooks(), textField.getText());
//					break;
//				case "Year":
//					searchResults = ComicBookSearch.searchComicBookByYear(comicBookDaoImpl.getAllComicBooks(), textField.getText());
//					break;
//				default:
//					searchResults = ComicBookSearch.searchComicBookByPublisher(comicBookDaoImpl.getAllComicBooks(), "");
//					break;
//			}
			loadComicBookResultsPage(searchResults, target, searchBy, this.userSession);
		}
    }
	
	private void loadComicBookResultsPage(List<ComicBookDTO> displayResults, String target, String searchBy, UserSession user) {
		ComicBookSearchResultsFrame resultsPage = new ComicBookSearchResultsFrame(displayResults, target, searchBy, user);
		resultsPage.setVisible(true);
	}
	
	public void reloadTable() {
		comicBookDaoImpl = new ComicBookDaoImpl();
		
		comicBookTableModel = new ComicBookTableModel(comicBookDaoImpl.getAllComicBooks());

		comicBookTable = new JTable(comicBookTableModel);
		comicBookTable.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int row = comicBookTable.rowAtPoint(e.getPoint());
				int col = comicBookTable.columnAtPoint(e.getPoint());
				if (col == 0) {
					ComicBookDTO comicBook = ComicBookSearch.searchComicBook(comicBookDaoImpl.getAllComicBooks(), comicBookTable.getValueAt(row, col).toString());
					ComicBookSearch.loadComicBookPage(comicBook, userSession);
				}
			}
		});
		sorter = new TableRowSorter<TableModel>(comicBookTable.getModel());
		comicBookTable.setRowSorter(sorter);
		
		scrollPane = new JScrollPane(comicBookTable);
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		scrollPane.setViewportBorder(null);
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.setBounds(10, 110, 1144, 683);
		panel.add(scrollPane);
	}
}
