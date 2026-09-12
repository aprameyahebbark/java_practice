import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.util.List;

public class MovieRecommendationSystem extends JFrame {

    // =========================================================
    // MOVIE CLASS
    // =========================================================

    static class Movie {
        String title;
        int year;
        String genre;
        String director;
        String cast;
        double rating;

        Movie(String title, int year, String genre,
              String director, String cast, double rating) {

            this.title = title;
            this.year = year;
            this.genre = genre;
            this.director = director;
            this.cast = cast;
            this.rating = rating;
        }
    }

    // =========================================================
    // DATABASE
    // =========================================================

    static ArrayList<Movie> movies = new ArrayList<>();

    // =========================================================
    // COLORS / FONTS
    // =========================================================

    Color backgroundColor = new Color(248, 204, 119);
    Color cardColor = new Color(94, 205, 59, 255);
    Color accentColor = new Color(11, 200, 184);
    Color goldColor = new Color(119, 26, 197);
    Color textColor = Color.BLACK;
    Color secondaryText = new Color(123, 24, 24);

    Font titleFont = new Font("SansSerif", Font.BOLD, 34);
    Font headingFont = new Font("SansSerif", Font.BOLD, 26);
    Font normalFont = new Font("SansSerif", Font.PLAIN, 15);
    Font buttonFont = new Font("SansSerif", Font.BOLD, 14);

    // Main panel
    JPanel mainPanel;

    // =========================================================
    // CONSTRUCTOR
    // =========================================================

    public MovieRecommendationSystem() {

        loadMovies();

        setTitle("CineMatch - Movie Recommendation System");
        setSize(1200, 750);
        setMinimumSize(new Dimension(1000, 650));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        showHomePage();
    }

    // =========================================================
    // MAIN
    // =========================================================

    public static void main(String[] args) {

        SwingUtilities.invokeLater(() -> {

            try {
                UIManager.setLookAndFeel(
                        UIManager.getSystemLookAndFeelClassName()
                );
            } catch (Exception ignored) {
            }

            MovieRecommendationSystem app =
                    new MovieRecommendationSystem();

            app.setVisible(true);
        });
    }

    // =========================================================
    // COMMON PANEL
    // =========================================================

    void setupMainPanel() {

        mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBackground(backgroundColor);

        setContentPane(mainPanel);
    }

    // =========================================================
    // HOME PAGE
    // =========================================================

    void showHomePage() {

        setupMainPanel();

        // ---------------- HEADER ----------------

        JPanel header = new JPanel(new BorderLayout());
        header.setBackground(new Color(12, 12, 12));
        header.setBorder(new EmptyBorder(18, 35, 18, 35));

        JLabel logo = new JLabel("🎬 CINEMATCH");
        logo.setForeground(Color.WHITE);
        logo.setFont(new Font("SansSerif", Font.BOLD, 27));

        JLabel tagline = new JLabel(
                "Hollywood Movie Recommendation System"
        );
        tagline.setForeground(secondaryText);
        tagline.setFont(normalFont);

        header.add(logo, BorderLayout.WEST);
        header.add(tagline, BorderLayout.EAST);

        mainPanel.add(header, BorderLayout.NORTH);

        // ---------------- CENTER ----------------

        JPanel center = new JPanel();
        center.setBackground(backgroundColor);
        center.setLayout(new BoxLayout(center, BoxLayout.Y_AXIS));

        center.setBorder(new EmptyBorder(65, 80, 50, 80));

        JLabel welcome = new JLabel(
                "Find Your Next Great Movie"
        );
        welcome.setFont(titleFont);
        welcome.setForeground(Color.WHITE);
        welcome.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel description = new JLabel(
                "Explore Hollywood movies based on your favourite genres."
        );
        description.setFont(new Font("SansSerif", Font.PLAIN, 17));
        description.setForeground(secondaryText);
        description.setAlignmentX(Component.CENTER_ALIGNMENT);

        center.add(welcome);
        center.add(Box.createVerticalStrut(12));
        center.add(description);
        center.add(Box.createVerticalStrut(50));

        // ---------------- HOME BUTTONS ----------------

        JPanel buttonPanel = new JPanel(new GridLayout(1, 3, 25, 0));
        buttonPanel.setBackground(backgroundColor);
        buttonPanel.setMaximumSize(new Dimension(900, 150));

        JButton genreButton =
                createLargeButton("🎭  EXPLORE GENRES");

        JButton searchButton =
                createLargeButton("🔎  SEARCH MOVIES");

        JButton topButton =
                createLargeButton("⭐  TOP RATED");

        genreButton.addActionListener(e -> showGenrePage());
        searchButton.addActionListener(e -> showSearchPage());
        topButton.addActionListener(e -> showTopRatedPage());

        buttonPanel.add(genreButton);
        buttonPanel.add(searchButton);
        buttonPanel.add(topButton);

        center.add(buttonPanel);

        center.add(Box.createVerticalStrut(60));

        JLabel featured = new JLabel(
                "Popular Directors and Stars"
        );

        featured.setFont(headingFont);
        featured.setForeground(Color.WHITE);
        featured.setAlignmentX(Component.CENTER_ALIGNMENT);

        center.add(featured);
        center.add(Box.createVerticalStrut(20));

        JLabel names = new JLabel(
                "Christopher Nolan   •   Martin Scorsese   •   Stanley Kubrick   •   Clint Eastwood"
        );

        names.setFont(normalFont);
        names.setForeground(secondaryText);
        names.setAlignmentX(Component.CENTER_ALIGNMENT);

        center.add(names);

        mainPanel.add(center, BorderLayout.CENTER);

        // ---------------- FOOTER ----------------

        JPanel footer = new JPanel();
        footer.setBackground(new Color(12, 12, 12));

        JLabel footerText = new JLabel(
                "Cinema Paradise   |   Discover. Watch. Enjoy."
        );

        footerText.setForeground(secondaryText);
        footer.add(footerText);

        mainPanel.add(footer, BorderLayout.SOUTH);

        refresh();
    }

    // =========================================================
    // GENRE PAGE
    // =========================================================

    void showGenrePage() {

        setupMainPanel();

        JPanel top = createTopBar(
                "🎭  EXPLORE BY GENRE",
                true
        );

        mainPanel.add(top, BorderLayout.NORTH);

        JPanel genrePanel = new JPanel(
                new GridLayout(3, 3, 20, 20)
        );

        genrePanel.setBackground(backgroundColor);
        genrePanel.setBorder(
                new EmptyBorder(40, 80, 40, 80)
        );

        String[] genres = {
                "Crime",
                "Psychological Thriller",
                "Science Fiction",
                "Action",
                "Romance",
                "Drama",
                "Horror",
                "Mystery",
                "War"
        };

        String[] icons = {
                "🔫",
                "🧠",
                "🚀",
                "⚔",
                "❤",
                "🎭",
                "👻",
                "🔍",
                "🪖"
        };

        for (int i = 0; i < genres.length; i++) {

            final String selectedGenre = genres[i];

            JButton button = new JButton(
                    "<html><center>" +
                            "<font size='6'>" +
                            icons[i] +
                            "</font><br>" +
                            "<font size='5'>" +
                            genres[i] +
                            "</font></center></html>"
            );

            button.setFocusPainted(false);
            button.setBackground(cardColor);
            button.setForeground(Color.WHITE);
            button.setBorder(
                    BorderFactory.createLineBorder(
                            new Color(55, 55, 55)
                    )
            );
            button.setCursor(
                    new Cursor(Cursor.HAND_CURSOR)
            );

            button.addActionListener(
                    e -> showMoviesByGenre(selectedGenre)
            );

            genrePanel.add(button);
        }

        mainPanel.add(
                new JScrollPane(
                        genrePanel,
                        JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
                        JScrollPane.HORIZONTAL_SCROLLBAR_NEVER
                ),
                BorderLayout.CENTER
        );

        refresh();
    }

    // =========================================================
    // MOVIES BY GENRE
    // =========================================================

    void showMoviesByGenre(String genre) {

        setupMainPanel();

        JPanel top = createTopBar(
                "🎬  " + genre.toUpperCase() + " MOVIES",
                true
        );

        mainPanel.add(top, BorderLayout.NORTH);

        JPanel moviePanel = new JPanel(
                new GridLayout(0, 3, 20, 20)
        );

        moviePanel.setBackground(backgroundColor);
        moviePanel.setBorder(
                new EmptyBorder(25, 40, 30, 40)
        );

        for (Movie movie : movies) {

            if (movie.genre.equalsIgnoreCase(genre)) {

                moviePanel.add(
                        createMovieCard(movie)
                );
            }
        }

        JScrollPane scrollPane =
                new JScrollPane(moviePanel);

        scrollPane.setBorder(null);
        scrollPane.getVerticalScrollBar()
                .setUnitIncrement(16);

        mainPanel.add(
                scrollPane,
                BorderLayout.CENTER
        );

        refresh();
    }

    // =========================================================
    // MOVIE CARD
    // =========================================================

    JPanel createMovieCard(Movie movie) {

        JPanel card = new JPanel();
        card.setLayout(
                new BoxLayout(card, BoxLayout.Y_AXIS)
        );

        card.setBackground(cardColor);

        card.setBorder(
                BorderFactory.createCompoundBorder(
                        BorderFactory.createLineBorder(
                                new Color(55, 55, 55)
                        ),
                        new EmptyBorder(
                                18, 18, 18, 18
                        )
                )
        );

        // Movie icon / poster placeholder

        JLabel poster = new JLabel("🎬");
        poster.setFont(
                new Font("SansSerif", Font.PLAIN, 50)
        );
        poster.setAlignmentX(
                Component.CENTER_ALIGNMENT
        );

        card.add(poster);

        JLabel title = new JLabel(
                movie.title
        );

        title.setFont(
                new Font("SansSerif", Font.BOLD, 19)
        );

        title.setForeground(Color.WHITE);
        title.setAlignmentX(
                Component.CENTER_ALIGNMENT
        );

        card.add(Box.createVerticalStrut(10));
        card.add(title);

        JLabel year = new JLabel(
                movie.year + "  •  " + movie.genre
        );

        year.setForeground(secondaryText);
        year.setFont(normalFont);
        year.setAlignmentX(
                Component.CENTER_ALIGNMENT
        );

        card.add(Box.createVerticalStrut(6));
        card.add(year);

        JLabel rating = new JLabel(
                "⭐ " + movie.rating + " / 10"
        );

        rating.setForeground(goldColor);
        rating.setFont(
                new Font("SansSerif", Font.BOLD, 15)
        );

        rating.setAlignmentX(
                Component.CENTER_ALIGNMENT
        );

        card.add(Box.createVerticalStrut(8));
        card.add(rating);

        JLabel director = new JLabel(
                "<html><center>Directed by<br>" +
                        movie.director +
                        "</center></html>"
        );

        director.setForeground(secondaryText);
        director.setFont(
                new Font("SansSerif", Font.PLAIN, 13)
        );

        director.setAlignmentX(
                Component.CENTER_ALIGNMENT
        );

        card.add(Box.createVerticalStrut(8));
        card.add(director);

        JButton details =
                new JButton("VIEW DETAILS");

        details.setFocusPainted(false);
        details.setBackground(accentColor);
        details.setForeground(Color.WHITE);
        details.setFont(buttonFont);
        details.setAlignmentX(
                Component.CENTER_ALIGNMENT
        );

        details.setCursor(
                new Cursor(Cursor.HAND_CURSOR)
        );

        details.addActionListener(
                e -> showMovieDetails(movie)
        );

        card.add(Box.createVerticalStrut(15));
        card.add(details);

        return card;
    }

    // =========================================================
    // MOVIE DETAILS
    // =========================================================

    void showMovieDetails(Movie movie) {

        setupMainPanel();

        JPanel top = createTopBar(
                "🎬  MOVIE DETAILS",
                true
        );

        mainPanel.add(top, BorderLayout.NORTH);

        JPanel detailsPanel = new JPanel();
        detailsPanel.setBackground(backgroundColor);

        detailsPanel.setLayout(
                new BoxLayout(
                        detailsPanel,
                        BoxLayout.Y_AXIS
                )
        );

        detailsPanel.setBorder(
                new EmptyBorder(
                        50, 100, 50, 100
                )
        );

        JLabel movieTitle =
                new JLabel(movie.title);

        movieTitle.setFont(
                new Font(
                        "SansSerif",
                        Font.BOLD,
                        40
                )
        );

        movieTitle.setForeground(Color.WHITE);
        movieTitle.setAlignmentX(
                Component.CENTER_ALIGNMENT
        );

        detailsPanel.add(movieTitle);

        detailsPanel.add(
                Box.createVerticalStrut(15)
        );

        JLabel rating =
                new JLabel(
                        "⭐ " + movie.rating + " / 10"
                );

        rating.setFont(
                new Font(
                        "SansSerif",
                        Font.BOLD,
                        22
                )
        );

        rating.setForeground(goldColor);
        rating.setAlignmentX(
                Component.CENTER_ALIGNMENT
        );

        detailsPanel.add(rating);

        detailsPanel.add(
                Box.createVerticalStrut(35)
        );

        JPanel infoPanel =
                new JPanel(
                        new GridLayout(4, 2, 15, 20)
                );

        infoPanel.setBackground(cardColor);
        infoPanel.setBorder(
                new EmptyBorder(
                        30, 40, 30, 40
                )
        );

        addInfo(infoPanel, "Year", "" + movie.year);
        addInfo(infoPanel, "Genre", movie.genre);
        addInfo(infoPanel, "Director", movie.director);
        addInfo(infoPanel, "Cast", movie.cast);

        detailsPanel.add(infoPanel);

        detailsPanel.add(
                Box.createVerticalStrut(35)
        );

        JButton back =
                createButton("← BACK");

        back.setAlignmentX(
                Component.CENTER_ALIGNMENT
        );

        back.addActionListener(
                e -> showGenrePage()
        );

        detailsPanel.add(back);

        mainPanel.add(
                new JScrollPane(detailsPanel),
                BorderLayout.CENTER
        );

        refresh();
    }

    // =========================================================
    // ADD INFO
    // =========================================================

    void addInfo(
            JPanel panel,
            String label,
            String value
    ) {

        JLabel l =
                new JLabel(label);

        l.setFont(
                new Font(
                        "SansSerif",
                        Font.BOLD,
                        15
                )
        );

        l.setForeground(
                new Color(220, 220, 220)
        );

        JLabel v =
                new JLabel(
                        "<html>" + value +
                                "</html>"
                );

        v.setFont(normalFont);
        v.setForeground(secondaryText);

        panel.add(l);
        panel.add(v);
    }

    // =========================================================
    // SEARCH PAGE
    // =========================================================

    void showSearchPage() {

        setupMainPanel();

        JPanel top = createTopBar(
                "🔎  SEARCH MOVIES",
                true
        );

        mainPanel.add(top, BorderLayout.NORTH);

        JPanel searchArea = new JPanel(
                new BorderLayout(10, 10)
        );

        searchArea.setBackground(backgroundColor);
        searchArea.setBorder(
                new EmptyBorder(25, 80, 15, 80)
        );

        JTextField searchField =
                new JTextField();

        searchField.setFont(
                new Font(
                        "SansSerif",
                        Font.PLAIN,
                        18
                )
        );

        JButton searchButton =
                createButton("SEARCH");

        searchArea.add(
                searchField,
                BorderLayout.CENTER
        );

        searchArea.add(
                searchButton,
                BorderLayout.EAST
        );

        mainPanel.add(
                searchArea,
                BorderLayout.CENTER
        );

        JPanel results =
                new JPanel(
                        new GridLayout(0, 3, 20, 20)
                );

        results.setBackground(
                backgroundColor
        );

        results.setBorder(
                new EmptyBorder(
                        15, 80, 30, 80
                )
        );

        JScrollPane scroll =
                new JScrollPane(results);

        scroll.setBorder(null);

        mainPanel.add(
                scroll,
                BorderLayout.SOUTH
        );

        // Make results occupy proper space
        mainPanel.remove(searchArea);

        JPanel container =
                new JPanel(new BorderLayout());

        container.setBackground(
                backgroundColor
        );

        container.add(
                searchArea,
                BorderLayout.NORTH
        );

        container.add(
                scroll,
                BorderLayout.CENTER
        );

        mainPanel.add(
                container,
                BorderLayout.CENTER
        );

        Runnable performSearch = () -> {

            String query =
                    searchField.getText()
                            .trim()
                            .toLowerCase();

            results.removeAll();

            if (query.isEmpty()) {

                results.revalidate();
                results.repaint();
                return;
            }

            for (Movie movie : movies) {

                if (
                        movie.title
                                .toLowerCase()
                                .contains(query)

                                ||

                                movie.director
                                        .toLowerCase()
                                        .contains(query)

                                ||

                                movie.cast
                                        .toLowerCase()
                                        .contains(query)

                                ||

                                movie.genre
                                        .toLowerCase()
                                        .contains(query)
                ) {

                    results.add(
                            createMovieCard(movie)
                    );
                }
            }

            results.revalidate();
            results.repaint();
        };

        searchButton.addActionListener(
                e -> performSearch.run()
        );

        searchField.addActionListener(
                e -> performSearch.run()
        );

        refresh();
    }

    // =========================================================
    // TOP RATED PAGE
    // =========================================================

    void showTopRatedPage() {

        setupMainPanel();

        JPanel top = createTopBar(
                "⭐  TOP RATED MOVIES",
                true
        );

        mainPanel.add(top, BorderLayout.NORTH);

        ArrayList<Movie> sorted =
                new ArrayList<>(movies);

        sorted.sort(
                (a, b) ->
                        Double.compare(
                                b.rating,
                                a.rating
                        )
        );

        JPanel moviePanel =
                new JPanel(
                        new GridLayout(
                                0, 3, 20, 20
                        )
                );

        moviePanel.setBackground(
                backgroundColor
        );

        moviePanel.setBorder(
                new EmptyBorder(
                        30, 50, 30, 50
                )
        );

        int count = 0;

        for (Movie movie : sorted) {

            if (count >= 12)
                break;

            moviePanel.add(
                    createMovieCard(movie)
            );

            count++;
        }

        JScrollPane scroll =
                new JScrollPane(moviePanel);

        scroll.setBorder(null);

        mainPanel.add(
                scroll,
                BorderLayout.CENTER
        );

        refresh();
    }

    // =========================================================
    // TOP BAR
    // =========================================================

    JPanel createTopBar(
            String title,
            boolean backButton
    ) {

        JPanel bar =
                new JPanel(new BorderLayout());

        bar.setBackground(
                new Color(12, 12, 12)
        );

        bar.setBorder(
                new EmptyBorder(
                        15, 25, 15, 25
                )
        );

        JLabel heading =
                new JLabel(title);

        heading.setFont(
                new Font(
                        "SansSerif",
                        Font.BOLD,
                        25
                )
        );

        heading.setForeground(Color.WHITE);

        bar.add(
                heading,
                BorderLayout.CENTER
        );

        if (backButton) {

            JButton home =
                    createButton("🏠 HOME");

            home.addActionListener(
                    e -> showHomePage()
            );

            bar.add(
                    home,
                    BorderLayout.EAST
            );
        }

        return bar;
    }

    // =========================================================
    // BUTTON CREATOR
    // =========================================================

    JButton createButton(String text) {

        JButton button =
                new JButton(text);

        button.setFont(buttonFont);
        button.setFocusPainted(false);
        button.setBackground(accentColor);
        button.setForeground(Color.WHITE);

        button.setBorder(
                new EmptyBorder(
                        10, 18, 10, 18
                )
        );

        button.setCursor(
                new Cursor(
                        Cursor.HAND_CURSOR
                )
        );

        return button;
    }

    // =========================================================
    // LARGE BUTTON
    // =========================================================

    JButton createLargeButton(
            String text
    ) {

        JButton button =
                new JButton(text);

        button.setFont(
                new Font(
                        "SansSerif",
                        Font.BOLD,
                        16
                )
        );

        button.setFocusPainted(false);

        button.setBackground(
                cardColor
        );

        button.setForeground(
                Color.WHITE
        );

        button.setBorder(
                BorderFactory.createLineBorder(
                        new Color(
                                60, 60, 60
                        )
                )
        );

        button.setCursor(
                new Cursor(
                        Cursor.HAND_CURSOR
                )
        );

        return button;
    }

    // =========================================================
    // REFRESH
    // =========================================================

    void refresh() {

        revalidate();
        repaint();
    }

    // =========================================================
    // MOVIE DATABASE
    // =========================================================

    static void loadMovies() {

        // ---------------- CRIME ----------------

        movies.add(new Movie(
                "The Godfather",
                1972,
                "Crime",
                "Francis Ford Coppola",
                "Al Pacino, Marlon Brando, James Caan",
                9.2
        ));

        movies.add(new Movie(
                "The Godfather Part II",
                1974,
                "Crime",
                "Francis Ford Coppola",
                "Al Pacino, Robert De Niro, Robert Duvall",
                9.0
        ));

        movies.add(new Movie(
                "Goodfellas",
                1990,
                "Crime",
                "Martin Scorsese",
                "Robert De Niro, Ray Liotta, Joe Pesci",
                8.7
        ));

        movies.add(new Movie(
                "Casino",
                1995,
                "Crime",
                "Martin Scorsese",
                "Robert De Niro, Sharon Stone, Joe Pesci",
                8.2
        ));

        movies.add(new Movie(
                "Scarface",
                1983,
                "Crime",
                "Brian De Palma",
                "Al Pacino, Michelle Pfeiffer",
                8.3
        ));

        movies.add(new Movie(
                "The Departed",
                2006,
                "Crime",
                "Martin Scorsese",
                "Leonardo DiCaprio, Matt Damon, Jack Nicholson",
                8.5
        ));

        movies.add(new Movie(
                "Once Upon a Time in America",
                1984,
                "Crime",
                "Sergio Leone",
                "Robert De Niro, James Woods",
                8.3
        ));

        // ---------------- THRILLER ----------------

        movies.add(new Movie(
                "The Dark Knight",
                2008,
                "Psychological Thriller",
                "Christopher Nolan",
                "Christian Bale, Heath Ledger, Aaron Eckhart",
                9.0
        ));

        movies.add(new Movie(
                "Inception",
                2010,
                "Psychological Thriller",
                "Christopher Nolan",
                "Leonardo DiCaprio, Cillian Murphy, Tom Hardy",
                8.8
        ));

        movies.add(new Movie(
                "Shutter Island",
                2010,
                "Psychological Thriller",
                "Martin Scorsese",
                "Leonardo DiCaprio, Mark Ruffalo",
                8.2
        ));

        movies.add(new Movie(
                "The Shining",
                1980,
                "Psychological Thriller",
                "Stanley Kubrick",
                "Jack Nicholson, Shelley Duvall",
                8.4
        ));

        movies.add(new Movie(
                "Black Swan",
                2010,
                "Psychological Thriller",
                "Darren Aronofsky",
                "Natalie Portman, Mila Kunis",
                8.0
        ));

        movies.add(new Movie(
                "Oppenheimer",
                2023,
                "Psychological Thriller",
                "Christopher Nolan",
                "Cillian Murphy, Emily Blunt, Robert Downey Jr.",
                8.6
        ));

        // ---------------- SCI-FI ----------------

        movies.add(new Movie(
                "Interstellar",
                2014,
                "Science Fiction",
                "Christopher Nolan",
                "Matthew McConaughey, Anne Hathaway, Cillian Murphy",
                8.7
        ));

        movies.add(new Movie(
                "The Prestige",
                2006,
                "Science Fiction",
                "Christopher Nolan",
                "Christian Bale, Hugh Jackman, Scarlett Johansson",
                8.5
        ));

        movies.add(new Movie(
                "2001: A Space Odyssey",
                1968,
                "Science Fiction",
                "Stanley Kubrick",
                "Keir Dullea, Gary Lockwood",
                8.3
        ));

        movies.add(new Movie(
                "Tenet",
                2020,
                "Science Fiction",
                "Christopher Nolan",
                "John David Washington, Robert Pattinson",
                7.3
        ));

        movies.add(new Movie(
                "The Matrix",
                1999,
                "Science Fiction",
                "The Wachowskis",
                "Keanu Reeves, Laurence Fishburne",
                8.7
        ));

        // ---------------- ACTION ----------------

        movies.add(new Movie(
                "The Dark Knight Rises",
                2012,
                "Action",
                "Christopher Nolan",
                "Christian Bale, Tom Hardy, Anne Hathaway",
                8.4
        ));

        movies.add(new Movie(
                "Dunkirk",
                2017,
                "Action",
                "Christopher Nolan",
                "Tom Hardy, Cillian Murphy, Mark Rylance",
                7.8
        ));

        movies.add(new Movie(
                "Gladiator",
                2000,
                "Action",
                "Ridley Scott",
                "Russell Crowe, Joaquin Phoenix",
                8.5
        ));

        movies.add(new Movie(
                "The Good, the Bad and the Ugly",
                1966,
                "Action",
                "Sergio Leone",
                "Clint Eastwood, Eli Wallach",
                8.8
        ));

        movies.add(new Movie(
                "Unforgiven",
                1992,
                "Action",
                "Clint Eastwood",
                "Clint Eastwood, Gene Hackman, Morgan Freeman",
                8.2
        ));

        // ---------------- ROMANCE ----------------

        movies.add(new Movie(
                "Titanic",
                1997,
                "Romance",
                "James Cameron",
                "Leonardo DiCaprio, Kate Winslet",
                7.9
        ));

        movies.add(new Movie(
                "The Reader",
                2008,
                "Romance",
                "Stephen Daldry",
                "Kate Winslet, Ralph Fiennes",
                7.6
        ));

        movies.add(new Movie(
                "Eternal Sunshine of the Spotless Mind",
                2004,
                "Romance",
                "Michel Gondry",
                "Jim Carrey, Kate Winslet",
                8.3
        ));

        movies.add(new Movie(
                "Before Sunrise",
                1995,
                "Romance",
                "Richard Linklater",
                "Ethan Hawke, Julie Delpy",
                8.1
        ));

        // ---------------- DRAMA ----------------

        movies.add(new Movie(
                "The Shawshank Redemption",
                1994,
                "Drama",
                "Frank Darabont",
                "Tim Robbins, Morgan Freeman",
                9.3
        ));

        movies.add(new Movie(
                "There Will Be Blood",
                2007,
                "Drama",
                "Paul Thomas Anderson",
                "Daniel Day-Lewis, Paul Dano",
                8.2
        ));

        movies.add(new Movie(
                "The Irishman",
                2019,
                "Drama",
                "Martin Scorsese",
                "Robert De Niro, Al Pacino, Joe Pesci",
                7.8
        ));

        movies.add(new Movie(
                "Million Dollar Baby",
                2004,
                "Drama",
                "Clint Eastwood",
                "Clint Eastwood, Hilary Swank, Morgan Freeman",
                8.1
        ));

        movies.add(new Movie(
                "Mystic River",
                2003,
                "Drama",
                "Clint Eastwood",
                "Sean Penn, Tim Robbins, Kevin Bacon",
                7.9
        ));

        // ---------------- HORROR ----------------

        movies.add(new Movie(
                "The Exorcist",
                1973,
                "Horror",
                "William Friedkin",
                "Ellen Burstyn, Max von Sydow",
                8.1
        ));

        movies.add(new Movie(
                "Alien",
                1979,
                "Horror",
                "Ridley Scott",
                "Sigourney Weaver, Tom Skerritt",
                8.5
        ));

        // ---------------- MYSTERY ----------------

        movies.add(new Movie(
                "Memento",
                2000,
                "Mystery",
                "Christopher Nolan",
                "Guy Pearce, Carrie-Anne Moss",
                8.4
        ));

        movies.add(new Movie(
                "Zodiac",
                2007,
                "Mystery",
                "David Fincher",
                "Jake Gyllenhaal, Robert Downey Jr.",
                7.7
        ));

        movies.add(new Movie(
                "Eyes Wide Shut",
                1999,
                "Mystery",
                "Stanley Kubrick",
                "Tom Cruise, Nicole Kidman",
                7.5
        ));

        // ---------------- WAR ----------------

        movies.add(new Movie(
                "Full Metal Jacket",
                1987,
                "War",
                "Stanley Kubrick",
                "Matthew Modine, Vincent D'Onofrio",
                8.2
        ));

        movies.add(new Movie(
                "Saving Private Ryan",
                1998,
                "War",
                "Steven Spielberg",
                "Tom Hanks, Matt Damon",
                8.6
        ));

        movies.add(new Movie(
                "Letters from Iwo Jima",
                2006,
                "War",
                "Clint Eastwood",
                "Ken Watanabe, Kazunari Ninomiya",
                7.8
        ));
    }
}
