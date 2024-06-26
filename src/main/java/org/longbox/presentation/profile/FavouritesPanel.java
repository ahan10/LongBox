package org.longbox.presentation.profile;


import org.longbox.businesslogic.UserSession;
import org.longbox.businesslogic.exception.UserIDDoesNotExistException;
import org.longbox.config.HibernateUtils;
import org.longbox.domainobjects.dto.ComicBookDto;
import org.longbox.persistence.dao.ComicBookFavouritesListDaoImpl;

import lombok.Getter;
import lombok.Setter;
import org.longbox.presentation.tablemodels.ComicBookTableModel;

import javax.swing.table.JTableHeader;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.BorderLayout;
import java.awt.Font;
import java.util.List;

import javax.swing.*;
@Getter
@Setter
public class FavouritesPanel extends JPanel {
	private static final String DEFAULT_FONT = "Calibri";
	private static FavouritesPanel instance;
	private JPanel panel;
	private JLabel comicCollectionTitle;
	private JLabel lblNewLabel_1;
	private JSeparator separator;
	private JComboBox<String> comboBox;
	private JScrollPane scrollPane;
	private String currentItem;
	private JTable comicBookTable;
	private JTextField textField;
	private JComboBox<String> typeSelection;
	private ComicBookTableModel comicBookTableModel;
	private JButton unfavouriteButton;
	private TableRowSorter<TableModel> sorter;
	private ComicBookFavouritesListDaoImpl comicBookFavouritesListDaoImp;
	private UserSession userSession;
	private JButton refreshButton;
	private JLabel lblNewLabel;
	private JTableHeader header;

	public FavouritesPanel() {
		initComicCollectionPage();
		userSession = UserSession.getActiveUser();
	}

	public void update(long userId, long comicBookId) throws UserIDDoesNotExistException {
		comicBookFavouritesListDaoImp = new ComicBookFavouritesListDaoImpl(HibernateUtils.getSessionFactory());
		comicBookFavouritesListDaoImp.saveToFavourites(userId, comicBookId);
		List<ComicBookDto> updatedFavouriteComicBooks = comicBookFavouritesListDaoImp.getAllFavouritesComicBooks();
		comicBookTableModel.updateData(updatedFavouriteComicBooks);
	}

	private void initComicCollectionPage() {
		setBounds(10, 47, 1164, 803);
		setLayout(new BorderLayout());

		panel = new JPanel();
		panel.setLayout(null);

		comicCollectionTitle = new JLabel("User Favourites");
		comicCollectionTitle.setFont(new Font(DEFAULT_FONT, Font.PLAIN, 30));
		comicCollectionTitle.setHorizontalAlignment(SwingConstants.CENTER);
		comicCollectionTitle.setBounds(396, 11, 372, 43);
		panel.add(comicCollectionTitle);

		separator = new JSeparator();
		separator.setBounds(10, 92, 1144, 14);
		panel.add(separator);

		add(panel, BorderLayout.CENTER);

		comicBookFavouritesListDaoImp = new ComicBookFavouritesListDaoImpl(HibernateUtils.getSessionFactory());

		comicBookTableModel = new ComicBookTableModel(comicBookFavouritesListDaoImp.getAllFavouritesComicBooks());
		comicBookTable = new JTable(comicBookTableModel);
		comicBookTable.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int row = comicBookTable.rowAtPoint(e.getPoint());
				int col = comicBookTable.columnAtPoint(e.getPoint());
				if (col == 0 && e.getClickCount() == 2) {
					ComicBookDto comicBook = org.longbox.businesslogic.utils.ComicBookSearchUtils.searchComicBook(comicBookFavouritesListDaoImp.getAllFavouritesComicBooks(), comicBookTable.getValueAt(row, col).toString());
					System.out.println("Clicked on: " + comicBookTable.getValueAt(row, col).toString());
					org.longbox.businesslogic.utils.ComicBookSearchUtils.loadComicBookPage(comicBook, userSession);
				}
			}
		});

		sorter = new TableRowSorter<TableModel>(comicBookTable.getModel());
		comicBookTable.setRowSorter(sorter);

		scrollPane = new JScrollPane(comicBookTable);
		scrollPane.setViewportBorder(null);
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.setBounds(10, 110, 1144, 683);
		panel.add(scrollPane);

		typeSelection = new JComboBox<String>();
		typeSelection.setBounds(333, 62, 160, 22);

		typeSelection.addItem("Title");
		typeSelection.addItem("Author");
		typeSelection.addItem("Artist");
		typeSelection.addItem("Genre");
		typeSelection.addItem("Publisher");
		typeSelection.addItem("Year");

		panel.add(typeSelection);

		textField = new JTextField();
		textField.setBounds(116, 62, 213, 22);
		panel.add(textField);
		textField.setColumns(10);

		lblNewLabel = new JLabel("Search Favourites:");
		lblNewLabel.setBounds(10, 66, 120, 13);
		panel.add(lblNewLabel);

		unfavouriteButton = new JButton("Unfavourite");
		unfavouriteButton.setEnabled(false); // Initially disabled
		unfavouriteButton.setBounds(930, 62, 129, 23);
		panel.add(unfavouriteButton);

		refreshButton = new JButton("Refresh");
		refreshButton.setBounds(1065, 62, 89, 23);
		panel.add(refreshButton);

		comicBookTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		comicBookTable.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int selectedRow = comicBookTable.getSelectedRow();
				if (selectedRow != -1) {
					unfavouriteButton.setEnabled(true);
				}
			}
		});
		header = comicBookTable.getTableHeader();
		header.setReorderingAllowed(false);
	}

	public void reloadData() {
		comicBookTableModel.updateData(comicBookFavouritesListDaoImp.getAllFavouritesComicBooks());
	}
}
