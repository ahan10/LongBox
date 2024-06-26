package org.longbox.presentation.profile;

import lombok.Getter;
import lombok.Setter;
import org.longbox.businesslogic.*;
import org.longbox.presentation.tablemodels.ComicBookTableModel;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.CardLayout;
import java.awt.ComponentOrientation;

@Getter
@Setter
public class HomeFrame extends JFrame {
    private final String SEARCH_COMIC_BOOK = "Search Comics Panel";
    private final String COMIC_REPO_PANEL = "Comic Repo Panel";
    private final String FAVOURITES_PANEL = "User Favourites Panel";
    private final String PROFILE_PANEL = "Profile Panel";
    private final String ADD_COMIC_TO_REPO = "Add Comic To Repo";
    private final String TRENDING_PANEL = "Trending Panel";
    private final String RECCOMENDEDATIONS_PANEL = "Recommendations Panel";
    private final String FRAME_TITLE = "LongBox - Home Page";
    private final String SOCIAL_PANEL = "Social Panel";
    private final int BUTTON_WIDTH = 135;
    private final int BUTTON_X_POSITION = 10;
    private final int BUTTON_Y_POSITION = 11;
    private final int BUTTON_HEIGHT = 25;
    private JButton logOutButton;
    private JButton searchButtonNexus;
    private JButton addComicButton;
    private JButton comicRepoButton;
    private JButton profileButton;
    private JButton favouritesButton;
    private JButton trendingButton;
    private JButton recommendationsButton;
    private JPanel nexusPanel;
    private JPanel activityPanel;
    private FavouritesPanel favouritesPanel;
    private TrendingPanel trendingComicsPanel;
    private ComicRepositoryPanel comicRepoPanel;
    private ProfilePanel profilePanel;
    private AddComicToRepoPanel addComicToRepoPanel;
    private RecommendationsPanel recommendationsPanel;
    private SocialPanel socialPanel;
    private CardLayout cardLayout;
    private UserSession userSession;
    private JLabel userNameLabel;
    private JButton socialButton;
    
    public HomeFrame(UserSession user) {
    	this.userSession = user;
    	buildHomeFrame();
    	((ComicRepositoryPanel) comicRepoPanel).setUserSession(this.userSession);
    	favouritesPanel.setUserSession(this.userSession);
        userNameLabel = new JLabel(user.getUser().getUserName());
        userNameLabel.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
        userNameLabel.setBounds(965, 11, 129, 25);
        nexusPanel.add(userNameLabel);
        
        
    }

    private void buildHomeFrame() {
        //initializing the home frame and its parent container
        setTitle(FRAME_TITLE);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 1200, 900);
        nexusPanel = new JPanel();
        nexusPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
        setLocationRelativeTo(null);
        setContentPane(nexusPanel);

        // adding elements to the ContentPane
        nexusPanel.setLayout(null);
        profilePanel  = new ProfilePanel(this.userSession);
        favouritesPanel = new FavouritesPanel();
        comicRepoPanel = new ComicRepositoryPanel();
        addComicToRepoPanel = new AddComicToRepoPanel();
        trendingComicsPanel = new TrendingPanel(this.userSession);
        recommendationsPanel = new RecommendationsPanel(this.userSession);
        socialPanel = new SocialPanel();
        
        activityPanel = new JPanel();
        activityPanel.setBounds(10, 47, 1164, 803);
        nexusPanel.add(activityPanel);
        cardLayout = new CardLayout();
        
        activityPanel.setLayout(cardLayout);
        activityPanel.add(trendingComicsPanel, TRENDING_PANEL);
        activityPanel.add(comicRepoPanel, COMIC_REPO_PANEL);
        activityPanel.add(favouritesPanel, FAVOURITES_PANEL);
        activityPanel.add(profilePanel, PROFILE_PANEL);
        activityPanel.add(addComicToRepoPanel, ADD_COMIC_TO_REPO);
        activityPanel.add(recommendationsPanel, RECCOMENDEDATIONS_PANEL);
        activityPanel.add(socialPanel, SOCIAL_PANEL);
        
        cardLayout.show(activityPanel, RECCOMENDEDATIONS_PANEL);

        //adding buttons
        comicRepoButton = new JButton("Comic Repository");
        comicRepoButton.setBounds(BUTTON_X_POSITION, BUTTON_Y_POSITION, BUTTON_WIDTH, BUTTON_HEIGHT);
        nexusPanel.add(comicRepoButton);

        addComicButton = new JButton("Add Comic");
        addComicButton.setBounds(BUTTON_X_POSITION + BUTTON_WIDTH, BUTTON_Y_POSITION, BUTTON_WIDTH, BUTTON_HEIGHT);
        nexusPanel.add(addComicButton);

        favouritesButton = new JButton("Favourites");
        favouritesButton.setBounds(BUTTON_X_POSITION + 2*BUTTON_WIDTH, BUTTON_Y_POSITION, BUTTON_WIDTH, BUTTON_HEIGHT);
        nexusPanel.add(favouritesButton);
        
        profileButton = new JButton("Profile");
        profileButton.setBounds(BUTTON_X_POSITION + 3*BUTTON_WIDTH, BUTTON_Y_POSITION, BUTTON_WIDTH, BUTTON_HEIGHT);
        nexusPanel.add(profileButton);
        
        trendingButton = new JButton("Trending");
        trendingButton.setBounds(BUTTON_X_POSITION + 4*BUTTON_WIDTH, 11, BUTTON_WIDTH, BUTTON_HEIGHT);
        nexusPanel.add(trendingButton);
        
        recommendationsButton = new JButton("Recommended");
        recommendationsButton.setBounds(BUTTON_X_POSITION + 5*BUTTON_WIDTH, BUTTON_Y_POSITION, BUTTON_WIDTH, BUTTON_HEIGHT);
        nexusPanel.add(recommendationsButton);
        
        socialButton = new JButton("Social");
        socialButton.setBounds(BUTTON_X_POSITION + 6*BUTTON_WIDTH, BUTTON_Y_POSITION, BUTTON_WIDTH, BUTTON_HEIGHT);
        nexusPanel.add(socialButton);

        logOutButton = new JButton("Log Out");
        logOutButton.setBounds(1099, 11, 78, 25);
        nexusPanel.add(logOutButton);
    }
}