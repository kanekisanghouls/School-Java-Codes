// LIBRARY MANAGEMENT (SIMPLE JAVA GUI CODE)

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

class Book {
    private String bookId;
    private String title;
    private String author;
    private boolean available;
    private boolean notAvailable;

    public Book(String bookId, String title,String author, boolean available,boolean notAvailable){//Inheritance
        this.bookId = bookId;
        this.title = title;
        this.available = available;
        this.notAvailable = notAvailable;
        
    }

    public void setBookId(String BookId){
        this.bookId = bookId;
    }
    
    public void setTitle(String Title) {
        this.title = title;
    }
    
    public void setAuthor(String Author) {
        this.author = author;
    }
    
    public void setisAvailable(boolean isAvailable) {
         this.available = available;
    }
    
    public void setisnotAvailable(boolean isnotAvailable) {
         this.notAvailable = notAvailable;
    }
    
    public String getBookId() {
        return bookId;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public boolean getisAvailable() {
        return available;
    }
    
    public boolean getisnotAvailable() {
        return notAvailable;
    }

    @Override
    public String toString() {
        return "Book ID: " + bookId + " - " + title + " (" + (available ? "Available" : "Not Available") + ")";
    }
}


class LibrarySystem {
    private static List<Book> bookList;

    static {
        bookList = new ArrayList<>();
        bookList.add(new Book("101", "The Lord of the Rings", "By J.R.R. Tolkien", true, true));
        bookList.add(new Book("102", "Harry Potter", "By J.K Rowling", true, true));
        bookList.add(new Book("103", "The Book Thief", "By Markus Zusak", true, true));
        bookList.add(new Book("131", "Pride and Prejudice", "By Jane Austen", true, true));
        bookList.add(new Book("143", "Think and Grow Rich", "By Napoleon Hill", true, true));
        
    }

    public static List<Book> getBookList() {
        return new ArrayList<>(bookList);
    }
}

class LibraryGUI extends JFrame {
    private JPanel panel;
    private String Admin;
    private List<Book> selectedBooks;

    public LibraryGUI() {
        initialize();
        showLoginPanel();
    }

    private void initialize() {
        setSize(500, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);

        panel = new JPanel() {
        };
        panel.setLayout(null);
        add(panel);
    }

    private void showLoginPanel() {
        panel.removeAll();

        JLabel lblTitle = new JLabel("LIBRARY MANAGEMENT SYSTEM");
        lblTitle.setFont(new Font("Arial", Font.BOLD, 16));
        lblTitle.setBounds(100, 50, 300, 20);
        panel.add(lblTitle);

        JLabel lblUsername = new JLabel("Username:");
        lblUsername.setBounds(100, 100, 80, 25);
        panel.add(lblUsername);

        JTextField txtUsername = new JTextField();
        txtUsername.setBounds(200, 100, 160, 25);
        panel.add(txtUsername);

        JLabel lblPassword = new JLabel("Password:");
        lblPassword.setBounds(100, 150, 80, 25);
        panel.add(lblPassword);

        JPasswordField txtPassword = new JPasswordField();
        txtPassword.setBounds(200, 150, 160, 25);
        panel.add(txtPassword);

        JButton btnLogin = new JButton("Login");
        btnLogin.setBounds(200, 200, 80, 25);
        btnLogin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handleLogin(txtUsername.getText(), new String(txtPassword.getPassword()));
            }
        });
        panel.add(btnLogin);

        setVisible(true);
    }

    private void handleLogin(String username, String password) {
        String[] savedCredentials = UserCredentialsHandler.readCredentials();
        if (savedCredentials != null && username.equals(savedCredentials[0]) && password.equals(savedCredentials[1])) {
            Admin = username;
            selectedBooks = new ArrayList<>();
            showMainMenu();
        } else {
            JOptionPane.showMessageDialog(this, "The username or Password you've entered is incorrect!", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void showMainMenu() {
        panel.removeAll();

        JLabel lblWelcome = new JLabel("Welcome, " + Admin + "!");
        lblWelcome.setFont(new Font("Arial", Font.BOLD, 16));
        lblWelcome.setBounds(150, 50, 300, 20);
        panel.add(lblWelcome);

        JButton btnAddBook = new JButton("Add Book");
        btnAddBook.setBounds(150, 100, 200, 25);
        btnAddBook.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new AddBookMenu().show();
            }
        });
        panel.add(btnAddBook);

        JButton btnSearchBook = new JButton("Search Book");
        btnSearchBook.setBounds(150, 150, 200, 25);
        btnSearchBook.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new SearchBookMenu().show();
            }
        });
        panel.add(btnSearchBook);

        JButton btnDisplayBook = new JButton("Display Book");
        btnDisplayBook.setBounds(150, 200, 200, 25);
        btnDisplayBook.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new DisplayBookMenu().show();
            }
        });
        panel.add(btnDisplayBook);

        JButton btnDelete = new JButton("Delete");
        btnDelete.setBounds(150, 250, 200, 25);
        btnDelete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new DeleteMenu().show();
            }
        });
        panel.add(btnDelete);

        JButton btnLogout = new JButton("Logout");
        btnLogout.setBounds(150, 300, 200, 25);
        btnLogout.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Admin = null;
                selectedBooks = null;
                showLoginPanel();
                setVisible(false);
            }
        });
        panel.add(btnLogout);

        revalidate();
        repaint();
        setVisible(true);
    }

    private abstract class AbstractMenu {
        public abstract void show();
    }

    private class AddBookMenu extends AbstractMenu {
        @Override
        public void show() {
            panel.removeAll();
            JLabel label = new JLabel("Book List");
            label.setBounds(200, 50, 300, 20);
            panel.add(label);

            List<Book> books = LibrarySystem.getBookList();
            int yPosition = 100;
            for (Book book : books) {
                JRadioButton radioButton = new JRadioButton(book.toString());
                radioButton.setBounds(100, yPosition, 300, 20);
                radioButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if (radioButton.isSelected()) {
                            selectedBooks.add(book);
                        } else {
                            selectedBooks.remove(book);
                        }
                    }
                });
                panel.add(radioButton);
                yPosition += 30;
            }

            JButton btnSubmit = new JButton("Submit");
            btnSubmit.setBounds(150, yPosition, 100, 25);
            btnSubmit.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    showMainMenu();
                }
            });
            panel.add(btnSubmit);

            JButton btnBack = new JButton("Back");
            btnBack.setBounds(260, yPosition, 100, 25);
            btnBack.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    showMainMenu();
                }
            });
            panel.add(btnBack);

            revalidate();
            repaint();
        }
    }

    private class SearchBookMenu extends AbstractMenu {
        @Override
        public void show() {
            panel.removeAll();
            
            
            JLabel label = new JLabel("Book ID");
            label.setBounds(120, 75, 100, 80);
            panel.add(label);
            
            JTextField txtBookId = new JTextField();
            txtBookId.setBounds(200,100,160, 25);
            panel.add(txtBookId);

            JButton btnSearch = new JButton("Search");
            btnSearch.setBounds(115, 280, 125, 30);
            btnSearch.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    String searchBookId = txtBookId.getText();
                    for (Book book : selectedBooks) {
                        if (book.getBookId().equals(searchBookId)) {
                            JOptionPane.showMessageDialog(null, "Book Found: " + book.getTitle());
                            return;
                        }
                    }
                    JOptionPane.showMessageDialog(null, "Book Not Found");
                }
            });
            panel.add(btnSearch);

            JButton btnBack = new JButton("Back");
            btnBack.setBounds(255, 280, 125, 30);;
            btnBack.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    showMainMenu();
                }
            });
            panel.add(btnBack);

            revalidate();
            repaint();
        }
    }

    private class DisplayBookMenu extends AbstractMenu {
        @Override
        public void show() {
            panel.removeAll();
            JLabel label = new JLabel("Book Selected");
            label.setBounds(200, 50, 200, 20);
            panel.add(label);

            int yPosition = 100;
            for (Book book : selectedBooks) {
                JLabel bookLabel = new JLabel(book.getBookId() + " " + book.getTitle());
                bookLabel.setBounds(150, yPosition, 300, 20);
                panel.add(bookLabel);
                yPosition += 30;
            }

            JButton btnBack = new JButton("Back");
            btnBack.setBounds(150, yPosition, 100, 25);
            btnBack.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    showMainMenu();
                }
            });
            panel.add(btnBack);

            revalidate();
            repaint();
        }
    }

    private class DeleteMenu extends AbstractMenu {
        @Override
        public void show() {
            panel.removeAll();
            JLabel label = new JLabel("Delete");
            label.setBounds(200, 50, 200, 20);
            panel.add(label);

            int yPosition = 100;
            for (Book book : selectedBooks) {
                JRadioButton radioButton = new JRadioButton(book.toString());
                radioButton.setBounds(100, yPosition, 300, 20);
                panel.add(radioButton);
                yPosition += 30;
            }

            JButton btnDelete = new JButton("Delete");
            btnDelete.setBounds(150, yPosition, 100, 25);
            btnDelete.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    List<Book> toRemove = new ArrayList<>();
                    for (Book book : selectedBooks) {
                        for (Component component : panel.getComponents()) {
                            if (component instanceof JRadioButton) {
                                JRadioButton radioButton = (JRadioButton) component;
                                if (radioButton.isSelected() && radioButton.getText().contains(book.getBookId())) {
                                    toRemove.add(book);
                                }
                            }
                        }
                    }
                    selectedBooks.removeAll(toRemove);
                    showMainMenu();
                }
            });
            panel.add(btnDelete);

            JButton btnBack = new JButton("Back");
            btnBack.setBounds(260, yPosition, 100, 25);
            btnBack.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    showMainMenu();
                }
            });
            panel.add(btnBack);

            revalidate();
            repaint();
        }
    }
    
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new LibraryGUI();
            }
        });
    }
}

public class LibraryManagementSystem{
    public static void main(String[] args) {
        // File Handling: Write sample user credentials to a file
        LMS.lms("admin", "admin");

        // GUI: Create an instance of LibraryGUI
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new LibraryGUI();
            }
        });
    }
}
