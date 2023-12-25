import javax.swing.*;
import java.awt.*;
import java.util.EventListener;
import java.util.Calendar;
import java.io.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import javax.swing.table.TableModel;

import java.awt.event.*;

public class ContactInterface implements ActionListener {
    private static String[] columnNames = { "First Name", "Last Name", "Birthday", "Address", "Contact No.",
            "Occupation", "Gender", "V Type", "V Model", "V Color", "V Number" };
    private static JTextField txtFirstName, txtLastName, txtBirthday, txtAddress, txtContact, txtOccupation,
    txtVehicleType, txtVehicleModel, txtVehicleColor, txtVehicleNumber;
    private static JRadioButton maleRadioButton, femaleRadioButton;
    private static JComboBox<Integer> dayComboBox;
    private static JComboBox<Integer> yearComboBox;
    private static JComboBox<String> monthComboBox;
    private static JButton btnAdd, btnSubmit, btnCancel, btnDispAll, btnExit;
    private static JFrame frame;
    private static JPanel panel, panelAdd;
    private static JLabel lblTitle, totalCustomersLabel, totalVehiclesLabel;
    private static JTable table;

    public static void main(String[] args) {
        CMSModules cms = new CMSModules();
        CMSModules.loadData();
        frame = new JFrame();
        frame.setSize(800, 500);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);

        JPanel panel = new JPanel();
        panel.setLayout(null);
        frame.add(panel);

        JLabel lblTitle = new JLabel("Customer's Vehicle Management System");
        lblTitle.setFont(new Font("ARIAL", Font.BOLD, 24));
        lblTitle.setBounds(180, 10, 500, 20);
        panel.add(lblTitle);

        JLabel homeTitle = new JLabel("Home");
        homeTitle.setFont(new Font("ARIAL", Font.BOLD, 20));
        homeTitle.setBounds(360, 40, 500, 20);
        panel.add(homeTitle);
        JButton btnAdd = new JButton("Add Customer");
        btnAdd.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    panelAdd = new JPanel();
                    panelAdd.setLayout(null);
                    frame.add(panelAdd);
                    JLabel lblTitle = new JLabel("Customer's Vehicle Management System");
                    lblTitle.setFont(new Font("ARIAL", Font.BOLD, 24));
                    lblTitle.setBounds(180, 10, 500, 20);

                    panelAdd.add(lblTitle);
                    JLabel addTitle = new JLabel("Add Customer");
                    addTitle.setFont(new Font("ARIAL", Font.BOLD, 20));
                    addTitle.setBounds(360, 40, 500, 20);

                    panelAdd.add(addTitle);
                    JLabel lblFirstName = new JLabel("First Name:");
                    lblFirstName.setFont(new Font("ARIAL", Font.PLAIN, 14));
                    lblFirstName.setBounds(80, 80, 100, 25);
                    panelAdd.add(lblFirstName);

                    txtFirstName = new JTextField();
                    txtFirstName.setBounds(160, 80, 170, 25);
                    panelAdd.add(txtFirstName);

                    JLabel lblLastName = new JLabel("Last Name:");
                    lblLastName.setFont(new Font("ARIAL", Font.PLAIN, 14));
                    lblLastName.setBounds(80, 120, 100, 25);
                    panelAdd.add(lblLastName);

                    txtLastName = new JTextField();
                    txtLastName.setBounds(160, 120, 170, 25);
                    panelAdd.add(txtLastName);

                    JLabel lblBirthday = new JLabel("Birthday:");
                    lblBirthday.setFont(new Font("ARIAL", Font.PLAIN, 14));
                    lblBirthday.setBounds(80, 160, 100, 25);
                    panelAdd.add(lblBirthday);

                    String[] monthsArray = { "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12" };
                    monthComboBox = new JComboBox<>(monthsArray);
                    monthComboBox.setBounds(160, 160, 50, 25);
                    panelAdd.add(monthComboBox);

                    Integer[] daysArray = new Integer[31];
                    for (int i = 1; i <= 31; i++) {
                        daysArray[i - 1] = i;
                    }
                    dayComboBox = new JComboBox<>(daysArray);
                    dayComboBox.setBounds(210, 160, 50, 25);
                    panelAdd.add(dayComboBox);

                    int currentYear = Calendar.getInstance().get(Calendar.YEAR);
                    Integer[] yearsArray = new Integer[101]; // Array size is 101 to hold 100 years
                    for (int i = 0; i < 101; i++) {
                        yearsArray[i] = currentYear - i;
                    }
                    yearComboBox = new JComboBox<>(yearsArray);
                    yearComboBox.setBounds(265, 160, 80, 25);
                    panelAdd.add(yearComboBox);

                    JLabel birthLabel = new JLabel("mm/dd/year");
                    birthLabel.setFont(new Font("ARIAL", Font.PLAIN, 14));
                    birthLabel.setBounds(350, 160, 100, 25);
                    panelAdd.add(birthLabel);

                    JLabel lblAddress = new JLabel("Address:");
                    lblAddress.setFont(new Font("ARIAL", Font.PLAIN, 14));
                    lblAddress.setBounds(80, 200, 100, 25);
                    panelAdd.add(lblAddress);

                    txtAddress = new JTextField();
                    txtAddress.setBounds(160, 200, 170, 25);
                    panelAdd.add(txtAddress);

                    JLabel lblContact = new JLabel("Contact No:");
                    lblContact.setFont(new Font("ARIAL", Font.PLAIN, 14));
                    lblContact.setBounds(80, 240, 120, 25);
                    panelAdd.add(lblContact);

                    txtContact = new JTextField();
                    txtContact.setBounds(160, 240, 170, 25);
                    panelAdd.add(txtContact);

                    JLabel lblOccupation = new JLabel("Occupation:");
                    lblOccupation.setFont(new Font("ARIAL", Font.PLAIN, 14));
                    lblOccupation.setBounds(80, 280, 100, 25);
                    panelAdd.add(lblOccupation);

                    txtOccupation = new JTextField();
                    txtOccupation.setBounds(160, 280, 170, 25);
                    panelAdd.add(txtOccupation);

                    JLabel lblGender = new JLabel("Gender:");
                    lblGender.setFont(new Font("ARIAL", Font.PLAIN, 14));
                    lblGender.setBounds(80, 320, 100, 25);
                    panelAdd.add(lblGender);

                    maleRadioButton = new JRadioButton("Male");
                    maleRadioButton.setBounds(160, 320, 80, 25);
                    panelAdd.add(maleRadioButton);

                    femaleRadioButton = new JRadioButton("Female");
                    femaleRadioButton.setBounds(250, 320, 80, 25);
                    panelAdd.add(femaleRadioButton);

                    ButtonGroup genderGroup = new ButtonGroup();
                    genderGroup.add(maleRadioButton);
                    genderGroup.add(femaleRadioButton);

                    JLabel lblVehicleType = new JLabel("Vehicle Type:");
                    lblVehicleType.setFont(new Font("ARIAL", Font.PLAIN, 14));
                    lblVehicleType.setBounds(450, 80, 100, 25);
                    panelAdd.add(lblVehicleType);

                    txtVehicleType = new JTextField();
                    txtVehicleType.setBounds(550, 80, 170, 25);
                    panelAdd.add(txtVehicleType);

                    JLabel lblVehicleModel = new JLabel("Vehicle Model:");
                    lblVehicleModel.setFont(new Font("ARIAL", Font.PLAIN, 14));
                    lblVehicleModel.setBounds(450, 120, 100, 25);
                    panelAdd.add(lblVehicleModel);

                    txtVehicleModel = new JTextField();
                    txtVehicleModel.setBounds(550, 120, 170, 25);
                    panelAdd.add(txtVehicleModel);

                    JLabel lblVehicleColor = new JLabel("Vehicle Color:");
                    lblVehicleColor.setFont(new Font("ARIAL", Font.PLAIN, 14));
                    lblVehicleColor.setBounds(450, 160, 100, 25);
                    panelAdd.add(lblVehicleColor);

                    txtVehicleColor = new JTextField();
                    txtVehicleColor.setBounds(550, 160, 170, 25);
                    panelAdd.add(txtVehicleColor);

                    JLabel lblVehicleNumber = new JLabel("Vehicle No:");
                    lblVehicleNumber.setFont(new Font("ARIAL", Font.PLAIN, 14));
                    lblVehicleNumber.setBounds(450, 200, 110, 25);
                    panelAdd.add(lblVehicleNumber);

                    txtVehicleNumber = new JTextField();
                    txtVehicleNumber.setBounds(550, 200, 170, 25);
                    panelAdd.add(txtVehicleNumber);

                    panelAdd.setSize(420, 580);

                    btnSubmit = new JButton("Add");
                    btnSubmit.setBounds(623, 400, 100, 30);
                    btnSubmit.setBackground(new java.awt.Color(102, 204, 255));
                    btnSubmit.addActionListener(new ContactInterface());
                    panelAdd.add(btnSubmit);

                    JButton btnCancel = new JButton("Back to menu");
                    btnCancel.setBounds(480, 400, 130, 30);
                    btnCancel.setBackground(new java.awt.Color(102, 204, 255));
                    btnCancel.addActionListener(new ContactInterface());
                    panelAdd.add(btnCancel);

                    panel.setVisible(false);
                    panelAdd.setVisible(true);

                    btnCancel.addActionListener(new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent e) {
                                panel.setVisible(true);
                                panelAdd.setVisible(false);
                            }
                        });
                }
            });

        btnAdd.setBounds(140, 80, 150, 35);
        btnAdd.setBackground(new java.awt.Color(102, 204, 255));
        panel.add(btnAdd);

        JButton btnSearch = new JButton("Search Customer");
        btnSearch.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    // Create the search panel
                    JPanel searchPanel = new JPanel();
                    searchPanel.setLayout(null);
                    frame.add(searchPanel);

                    // Create and add components to the search panel
                    JLabel title=new JLabel("Customer's Vehicle Management System");
                    title.setFont(new Font("ARIAL",Font.BOLD,24));
                    title.setBounds(180, 10, 500, 20);
                    searchPanel.add(title);
                    // Create and add components to the search panel
                    JLabel lblTitle = new JLabel("Search Customer");
                    lblTitle.setFont(new Font("ARIAL", Font.BOLD, 20));
                    lblTitle.setBounds(320, 40, 500, 20);
                    searchPanel.add(lblTitle);
                    // Center the title using a panel with FlowLayout

                    // Create a panel to hold input components and search result table
                    JPanel contentPanel = new JPanel();
                    contentPanel.setLayout(null); // Set layout to null for absolute positioning
                    contentPanel.setBounds(0, 65, 800, 280); // S
                    JPanel inputPanel = new JPanel();
                    inputPanel.setLayout(null); // Set layout to null for absolute positioning
                    inputPanel.setBounds(0, 0, 800, 60);
                    JLabel noteLabel = new JLabel("Search by Lastname:");
                    noteLabel.setBounds(35, 5, 150, 20);

                    JTextField txtSearchTerm = new JTextField(20);
                    txtSearchTerm.setBounds(35, 30, 150, 20);
                    JLabel selectLabel = new JLabel("Select the customer and press the update button");
                    selectLabel.setBounds(350, 30, 300, 20);
                    JButton btnPerformSearch = new JButton("Search");
                    btnPerformSearch.setBackground(new java.awt.Color(102, 204, 255));
                    btnPerformSearch.setBounds(155, 10, 80, 30);

                    inputPanel.add(selectLabel);
                    inputPanel.add(noteLabel);
                    inputPanel.add(txtSearchTerm);

                    contentPanel.add(inputPanel);

                    DefaultTableModel model = new DefaultTableModel(columnNames, 0);
                    JTable searchResultTable = new JTable(model);
                    JScrollPane searchScrollPane = new JScrollPane(searchResultTable);
                    searchScrollPane.setBounds(35, 70, 700, 300);
                    contentPanel.add(searchScrollPane);
                    // Set BorderLayout for the search result table
                    String[] columnNames = { "First Name", "Last Name", "Birthday", "Address", "Contact No.", "Occupation",
                            "Gender", "V Type", "V Model", "V Color", "V Number" };
                    DefaultTableModel searchResultModel = new DefaultTableModel(columnNames, 0);
                    JTable table = new JTable(searchResultModel);
                    table.setDefaultEditor(Object.class, null);
                    JScrollPane scrollPane = new JScrollPane(table);
                    scrollPane.setBounds(35, 70, 700, 300);
                    contentPanel.add(scrollPane);

                    searchPanel.add(contentPanel);
                    try (BufferedReader br = new BufferedReader(new FileReader("datafile.txt"))) {
                        String line;
                        while ((line = br.readLine()) != null) {
                            String[] info = line.split(",");
                            searchResultModel.addRow(new Object[] { info[0], info[1], info[2], info[3],
                                    info[4], info[5], info[6], info[7], info[8], info[9], info[10] });
                        }
                    } catch (IOException ex) {
                        JOptionPane.showMessageDialog(null, "Error reading contacts file.", "Error",
                            JOptionPane.ERROR_MESSAGE);
                        ex.printStackTrace();
                    }

                    // Perform search action
                    btnPerformSearch.addActionListener(new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent e) {
                                String searchTerm = txtSearchTerm.getText().trim().toLowerCase();
                                if (!searchTerm.isEmpty()) {
                                    searchResultModel.setRowCount(0); // Clear previous search results

                                    try (BufferedReader br = new BufferedReader(new FileReader("datafile.txt"))) {
                                        String line;
                                        while ((line = br.readLine()) != null) {
                                            if (line.toLowerCase().contains(searchTerm)) {
                                                String[] info = line.split(",");
                                                searchResultModel.addRow(new Object[] { info[0], info[1], info[2], info[3],
                                                        info[4], info[5], info[6], info[7], info[8], info[9], info[10] });
                                            }
                                        }
                                    } catch (IOException ex) {
                                        JOptionPane.showMessageDialog(null, "Error reading contacts file.", "Error",
                                            JOptionPane.ERROR_MESSAGE);
                                        ex.printStackTrace();
                                    }
                                }
                            }
                        });

                    // Create a panel for the SOUTH region with FlowLayout
                    JPanel southPanel = new JPanel();
                    southPanel.setLayout(null); // Set layout to null for absolute positioning
                    southPanel.setBounds(0, 350, 800, 100);

                    // Create a sub-panel for the buttons with a custom gap
                    JPanel buttonPanel = new JPanel();
                    buttonPanel.setLayout(null); // Set layout to null for absolute positioning
                    buttonPanel.setBounds(500, 10, 290, 80);
                    JButton btnBack = new JButton("Back to Menu");
                    btnBack.setBackground(new java.awt.Color(102, 204, 255));
                    btnBack.setBounds(20, 10, 120, 30);

                    buttonPanel.add(btnBack);
                    buttonPanel.add(btnPerformSearch);

                    btnBack.addActionListener(new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent e) {
                                frame.remove(searchPanel); // Remove the search panel from the frame
                                panel.setVisible(true);
                            }
                        });

                    // Add the button sub-panel to the main southPanel
                    southPanel.add(buttonPanel);

                    // Add the SOUTH panel to the main searchPanel
                    searchPanel.add(southPanel);

                    // Make the search panel visible
                    panel.setVisible(false);
                    searchPanel.setVisible(true);

                    // Refresh the frame
                    frame.revalidate();
                    frame.repaint();
                }
            });
        btnSearch.setBounds(140, 130, 150, 35);
        btnSearch.setBackground(new java.awt.Color(102, 204, 255));
        panel.add(btnSearch);

        JButton btnUpdate = new JButton("Update Customer");
        btnUpdate.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    JPanel updatePanel = new JPanel();
                    updatePanel.setLayout(null);
                    frame.add(updatePanel);
                    JLabel title=new JLabel("Customer's Vehicle Management System");
                    title.setFont(new Font("ARIAL",Font.BOLD,24));
                    title.setBounds(180, 10, 500, 20);
                    updatePanel.add(title);
                    // Create and add components to the search panel
                    JLabel updatelblTitle = new JLabel("Update Customer");
                    updatelblTitle.setFont(new Font("ARIAL", Font.BOLD, 20));
                    updatelblTitle.setBounds(320, 40, 500, 20);
                    updatePanel.add(updatelblTitle);

                    // Create a panel for the table
                    JPanel contentPanel = new JPanel();
                    contentPanel.setLayout(null);
                    contentPanel.setBounds(0, 40, 800, 280);// Set background color as needed

                    DefaultTableModel model = new DefaultTableModel(columnNames, 0);
                    JTable table = new JTable(model);
                    JScrollPane scrollPane = new JScrollPane(table);
                    scrollPane.setBounds(35, 40, 700, 300);

                    // Allow cell selection and enable cell editing for updates
                    table.setCellSelectionEnabled(true);
                    table.setDefaultEditor(Object.class, new DefaultCellEditor(new JTextField()));

                    contentPanel.add(scrollPane);
                    updatePanel.add(contentPanel);

                    // Create a panel for the SOUTH region with FlowLayout
                    JPanel southPanel = new JPanel();
                    southPanel.setLayout(null); // Set layout to null for absolute positioning
                    southPanel.setBounds(0, 350, 800, 100);

                    // Create a sub-panel for the buttons with a custom gap
                    JPanel updateButtonPanel = new JPanel(); // Adjust the gap
                    updateButtonPanel.setLayout(null);
                    updateButtonPanel.setBounds(440, 350, 290, 60);

                    // Set a fixed size for the buttons
                    JButton btnPerformUpdate = new JButton("Update");
                    btnPerformUpdate.setBackground(new Color(255, 255, 153));
                    btnPerformUpdate.setBounds(80, 350, 120, 25);
                    updatePanel.add(btnPerformUpdate);
                    btnPerformUpdate.addActionListener(new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent e) {
                                DefaultTableModel tableModel = (DefaultTableModel) table.getModel();
                                int rows = tableModel.getRowCount();

                                // Iterate through each row in the table
                                for (int i = 0; i < rows; i++) {
                                    String firstName = (String) tableModel.getValueAt(i, 0);
                                    String lastName = (String) tableModel.getValueAt(i, 1);
                                    String birthday = (String) tableModel.getValueAt(i, 2);
                                    String address = (String) tableModel.getValueAt(i, 3);
                                    String contact = (String) tableModel.getValueAt(i, 4);
                                    String occupation = (String) tableModel.getValueAt(i, 5);
                                    String gender = (String) tableModel.getValueAt(i, 6);
                                    String vehicleType = (String) tableModel.getValueAt(i, 7);
                                    String vehicleModel = (String) tableModel.getValueAt(i, 8);
                                    String vehicleColor = (String) tableModel.getValueAt(i, 9);
                                    String vehicleNumber = (String) tableModel.getValueAt(i, 10);

                                    // Assuming you have a Contact object or class to represent a contact
                                    // Find the existing contact in the contactList based on a unique identifier (e.g., firstName in this case)
                                    for (Contact newcontact : CMSModules.getContactList()) {
                                        if (newcontact.getFirstName().equals(firstName)) {
                                            // Update the existing contact's information
                                            newcontact.setFirstName(firstName);
                                            newcontact.setLastName(lastName);
                                            newcontact.setBirthday(birthday);
                                            newcontact.setAddress(address);
                                            newcontact.setContact(contact);
                                            newcontact.setOccupation(occupation);
                                            newcontact.setGender(gender);
                                            newcontact.setVehicleType(vehicleType);
                                            newcontact.setVehicleModel(vehicleModel);
                                            newcontact.setVehicleColor(vehicleColor);
                                            newcontact.setVehicleNumber(vehicleNumber);

                                            // Update the contact in the file
                                            cms.updateContactInFile(newcontact);
                                        }
                                    }
                                }

                                // Notify the user that changes have been saved
                                JOptionPane.showMessageDialog(null, "Changes Saved Successfully!");
                            }
                        });
                    JButton btnBack = new JButton("Back to Menu");
                    btnBack.setBackground(new java.awt.Color(102, 204, 255));
                    btnBack.setBounds(140, 10, 120, 25);

                    updateButtonPanel.add(btnBack);
                    updatePanel.add(updateButtonPanel);

                    // Load contacts into the table for updating
                    cms.loadContactsIntoTable(model);

                    btnBack.addActionListener(new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent e) {
                                panel.setVisible(true);
                                updatePanel.setVisible(false);
                            }
                        });

                    panel.setVisible(false);
                    updatePanel.setVisible(true);

                    // Add a ComponentAdapter to dynamically update the size of scrollPane
                    updatePanel.addComponentListener(new ComponentAdapter() {
                            @Override
                            public void componentResized(ComponentEvent e) {
                                int panelWidth = updatePanel.getWidth();
                                int panelHeight = updatePanel.getHeight();

                                // Adjust the bounds of scrollPane based on the panel's size
                                scrollPane.setBounds(panelWidth / 10, panelHeight / 10, panelWidth * 8 / 10,
                                    panelHeight * 7 / 10);
                            }
                        });
                }
            });
        btnUpdate.setBounds(140, 180, 150, 35);
        btnUpdate.setBackground(new java.awt.Color(102, 204, 255));
        panel.add(btnUpdate);


            // code
            // explanation ========================================================================

        JButton btnDelete = new JButton("Remove Customer");
        // 
        /*This line creates a new instance of the JButton class and assigns
         * it the name "btnDelete". The JButton is then added to the GUI interface as a button with the
         * label "Remove Customer". When the button is clicked, it will
         * trigger an event that will remove the customer associated with
         * the button from the system.
         * 
         */// This line adds an ActionListener to the JButton that will be called when the button is clicked.

        btnDelete.addActionListener(new ActionListener() {
            /*This line creates an instance of the ActionListener interface and
             * assigns it to the btnDelete button's ActionListener
             * property. The ActionListener interface is used to handle events that occur in a
             * component, such as a button click.
             * In this case, the ActionListener is used to handle the action event that occurs when the btnDelete
             * button is clicked. The code inside the ActionListener will be
             * executed when the event occurs.
             **/

                @Override
                public void actionPerformed(ActionEvent e) {
                    /* This code defines an actionPerformed method that
                     * takes an ActionEvent object as a parameter. This method is 
                     * typically used in event-driven programming to handle user 
                     * actions in a graphical user interface (GUI).
                     * This ActionEvent object contains information about the
                     * event that triggered the method, such as which button
                     * was clicked or which key was pressed. 
                     * The actionPerformed method can then perform some
                     * action based on this information, such as updating a
                     * data structure or displaying a new window.
                     **/


                    JPanel deletePanel = new JPanel(new BorderLayout());
                    /* This code creates a new JPanel object called "deletePanel" with
                     * a BorderLayout layout manager. The BorderLayout layout
                     * manager is a layout manager that provides a basic framework
                     * for placing components in a container, with different regions
                     * of the container acting as a "home" for the components. 
                     * 
                     * Creates a new panel (deletePanel) with a BorderLayout.
                     * This panel will serve as the main container for components related to customer removal.
                     **/

                    frame.add(deletePanel);
                    /*This code adds a panel with a
                     * label and a button to a graphical user interface (GUI) frame.
                     * The panel is added to the frame using the add() method. The
                     * label displays the text "Delete Selected" and the button is labeled "Delete". 
                     * When the button is clicked, it triggers an event that deletes the selected item from a 
                     * list or database.
                     * 
                     * Adds deletePanel to the main frame of the GUI.
                     ***/

                    JLabel title=new JLabel("Customer's Vehicle Management System");
                    /* a new JLabel component with the text "Customer's Vehicle Management System".
                     * The JLabel is a Swing component that displays text or an image
                     * on a graphical user interface (GUI). The text "Customer's Vehicle Management System" is
                     * displayed in a label that can be positioned on a GUI window.
                     * The JLabel component is often used to provide information or instructions to the user in a GUI application.
                     */

                    title.setFont(new Font("ARIAL",Font.BOLD,24));
                    /*  sets the font of a title to be bold and 24 pixels in size using the ARIAL font.
                     *  The Font class is used to create a new font with the specified font name, style, and size. 
                     * 
                     */
                    title.setBounds(180, 10, 500, 20);
                    /*
                    sets the bounds of a title label in a graphical user interface (GUI) program.
                    The setBounds() method is used to specify the position and size of the label on the screen.
                    In this case, the label is being positioned at (180, 10)
                    on the screen, and its width is set to 500 pixels and its height is set to 20 pixels. 
                    This means that the label will be 500 pixels wide and 20 pixels tall
                    , and its top-left corner will be positioned at (180, 10) on the screen.
                    ***/

                    deletePanel.add(title);
                    /* This line adds a JLabel component named title to a JPanel component named deletePanel. 
                     * The JLabel component displays the text "Delete".
                     * The deletePanel panel is likely used in a GUI
                     * application to display a delete confirmation dialog box.
                    **/


                    // Create and add components to the search panel
                    JLabel deletelblTitle = new JLabel("Remove Customer");
                    /* This line creates a new JLabel named deletelblTitle with the text "Remove 
                     * Customer." A JLabel is a Swing component used to display a single line of read-
                     * only text.
                     */
                    deletelblTitle.setFont(new Font("ARIAL", Font.BOLD, 20));
                    /* It sets the font of the label to "Arial" with bold style and a font size of 20. This styling 
                     * enhances the visual appearance of the label, making it stand out.
                     */
                    deletelblTitle.setBounds(320, 40, 500, 20);
                    /* This line sets the bounds of the label within the deletePanel.
                     * The setBounds method takes four parameters: (x-coordinate, y-coordinate, width, height).
                     * In this case:
                     * x-coordinate: 320 pixels from the left edge of the deletePanel.
                     * y-coordinate: 40 pixels from the top edge of the deletePanel.
                     * width: 500 pixels.
                     * height: 20 pixels.
                     */
                    deletePanel.add(deletelblTitle);
                    /* Finally, it adds the deletelblTitle label to the deletePanel. 
                     * This ensures that the label becomes a part of the GUI and is visible 
                     * when the deletePanel is displayed.
                     * 
                     * Specifically, it adds a JLabel component with the text "Remove Customer"
                     * to the deletePanel. This label likely serves as a heading or
                     * title for the section of the GUI where customers can be removed.
                     */


                    // Create a panel for the table
                    JPanel contentPanel = new JPanel();
                    contentPanel.setLayout(null); // Set layout to null for absolute positioning
                    /* Purpose: Creation of a panel (contentPanel) to house a table displaying customer information.
                     * Basis: A JPanel is instantiated to act as a container for components. The null 
                     * layout is set for absolute positioning within the panel.
                     
                    This code creates a new JPanel object called "contentPanel" and sets its layout
                    manager to null. A layout manager is responsible for determining the size and
                    position of the components within a container. By setting the layout manager
                    to null, the JPanel will not use any layout manager to arrange its components.
                    The null layout manager means that the
                    components added to the JPanel will be positioned at their default positions,
                    which may not be ideal for a specific layout or design. 
                     It is important to set a layout manager that is appropriate for
                     the desired layout or design of the JPanel.
                     */
                    
                    contentPanel.setBounds(0, 40, 800, 280); 
                    /* Purpose: Specifies the position and size of contentPanel within the parent container.
                     * Basis: setBounds is used to set the position (0, 40) and size (800 x 280 pixels) of 
                     * contentPanel within its parent container.
                     */
                    DefaultTableModel model = new DefaultTableModel(columnNames, 0);
                    /* Purpose: Creation of a table model (model) to manage data for the JTable.
                     * Basis: An instance of DefaultTableModel is created, initialized with column names, and zero initial rows.
                     * 
                     */
                    JTable table = new JTable(model);
                    /* Purpose: Creation of a JTable (table) using the defined model (model).
                     * Basis: A JTable is instantiated and associated with the previously created table model.
                     */
                    table.setModel(model);
                    /* Purpose: Setting the model for the JTable (redundant in this case).
                     * Basis: Although the model has been set during table creation, this line ensures 
                     * consistency by setting the model again.
                     */
                    table.setDefaultEditor(Object.class, null);
                    /* Purpose: Disabling cell editing in the table.
                     * Basis: This line ensures that cells in the table are not editable by default.
                     */
                    JScrollPane scrollPane = new JScrollPane(table);
                    /* Purpose: Creating a scroll pane (scrollPane) for the JTable to enable scrolling if needed.
                     * Basis: The JTable is wrapped in a JScrollPane to provide scroll functionality when 
                     * the number of rows or columns exceeds the visible area.
                     */
                    scrollPane.setBounds(35, 40, 700, 300);
                    /* Purpose: Setting the position and size of the scroll pane within contentPanel.
                     * Basis: Specifies the bounds of the scroll pane within contentPanel.
                     * 
                     */
                    contentPanel.add(scrollPane);
                    /* Purpose: Adding the scroll pane (with the table) to contentPanel. 
                     * Basis: This line ensures that the scroll pane, and thus the table, is part of the contentPanel.
                     */
                    deletePanel.add(contentPanel);
                    /* Purpose: Adding contentPanel to the main panel (deletePanel).
                     * Basis: Integrates the content panel into the larger delete panel.
                     * 
                     */
                    JPanel southPanel = new JPanel();
                    /* Purpose: Creating a panel (southPanel) for additional components.
                     * Basis: This panel is likely intended for holding buttons or controls related to the table.
                     */
                    southPanel.setLayout(null); // Set layout to null for absolute positioning
                    /* Purpose: Setting the position and size of southPanel within the parent container.
                     * 
                     */
                    southPanel.setBounds(0, 350, 800, 100);
                    /* Basis: Specifies the bounds of the south panel within its parent container.
                     *  
                     */

                    // Create a sub-panel for the buttons with a custom gap

                    JPanel deleteButtonPanel = new JPanel(); // Adjust the gap
                    /* Purpose: Creating a sub-panel (deleteButtonPanel) for buttons.
                     * Basis: This panel is intended to house buttons related to the deletion process.
                     */
                    deleteButtonPanel.setLayout(null);
                    /* Purpose: Setting the layout manager to null for absolute positioning within deleteButtonPanel.
                     * Basis: Absolute positioning is chosen for precise control over the placement of components.
                     * 
                     */
                    deleteButtonPanel.setBounds(450, 350, 290, 60);
                    /* Purpose: Setting the position and size of deleteButtonPanel within the parent container.
                     * Basis: Specifies the bounds of the delete button panel within its parent container.
                     */

                    // Set a fixed size for the buttons
                    JButton btnPerformDelete = new JButton("Delete");
                    /* Purpose: Creating a button (btnPerformDelete) for triggering the deletion process.
                     * Basis: Instantiates a JButton labeled "Delete."
                     * 
                     */
                    btnPerformDelete.setBackground(new Color(255, 153, 153));
                    /* Purpose: Setting the background color of btnPerformDelete.
                     * Basis: This line provides a visual cue by setting a background color.
                     */
                    btnPerformDelete.setBounds(80, 350, 120, 25);
                    /* Purpose: Setting the position and size of btnPerformDelete within deletePanel.
                     * Basis: Specifies the bounds of the button within its parent container.
                     */
                    deletePanel.add(btnPerformDelete);
                    /* Purpose: Adding btnPerformDelete to deletePanel.
                     * Basis: Ensures the button is part of the larger delete panel.
                     */
                    JButton btnBack = new JButton("Back to Menu");
                    /* Purpose: Creating a button (btnBack) for navigating back to the main menu.
                     * Basis: Instantiates a JButton labeled "Back to Menu."
                     */
                    btnBack.setBackground(new java.awt.Color(102, 204, 255));
                    /* Purpose: Setting the background color of btnBack.
                     * Basis: Provides a visual cue by setting a background color.
                     */
                    btnBack.setBounds(140, 10, 120, 25);
                    /* Purpose: Setting the position and size of btnBack within deletePanel.
                     * Basis: Specifies the bounds of the button within its parent container.
                     */
                    btnPerformDelete.addActionListener(new ActionListener() {
                        /* Purpose: Adding an action listener to btnPerformDelete for handling the delete button click event.
                         * Basis: An anonymous ActionListener is implemented to define the actions to be taken when btnPerformDelete is clicked.
                         * 
                         * Inside the ActionListener for btnPerformDelete, a confirmation dialog is shown, 
                         * and if confirmed, the selected row is removed from the table and the corresponding 
                         * data is deleted from the system.
                         */
                            @Override
                            /* Purpose: Indicates that the method below is meant to override a method in the superclass or interface.   
                             * Basis: In this case, the actionPerformed method is being overridden from the 
                             * ActionListener interface.
                             */
                            public void actionPerformed(ActionEvent e) {
                                /* Purpose: Defines the actions to be performed when the associated component triggers an action event.
                                 * Basis: The actionPerformed method is part of the ActionListener interface 
                                 * and is invoked when the "Delete" button is clicked.
                                 */
                                int selectedRow = table.getSelectedRow();
                                /* Purpose: Retrieves the index of the currently selected row in the JTable.
                                 * Basis: This line stores the index of the selected row in the variable selectedRow.
                                 * 
                                 */
                                int option = JOptionPane.showConfirmDialog(null, "Are you sure you want to remove this data?", "Confirmation", JOptionPane.YES_NO_OPTION);
                                /* Purpose: Displays a confirmation dialog box with "Yes" and "No" options.
                                 * Basis: The user is asked to confirm the deletion with a dialog box. The chosen option is stored in the variable option.
                                 * 
                                 */
                                if (selectedRow != -1) {
                                    DefaultTableModel model = (DefaultTableModel) table.getModel();
                                    String idToDelete = (String) model.getValueAt(selectedRow, 0); // Assuming the ID is in the first column

                                    // Remove contact from the file
                                    if(option == JOptionPane.YES_OPTION){
                                        if (cms.removeContactFromFile(idToDelete)) {
                                            model.removeRow(selectedRow);
                                            JOptionPane.showMessageDialog(null, "Contact deleted successfully.", "Success",
                                                JOptionPane.INFORMATION_MESSAGE);
                                        } else {
                                            JOptionPane.showMessageDialog(null, "Failed to delete contact.", "Error",
                                                JOptionPane.ERROR_MESSAGE);
                                        }
                                    }else{
                                    }
                                } else {
                                    JOptionPane.showMessageDialog(null, "Please select a contact to delete.", "No Selection",
                                        JOptionPane.WARNING_MESSAGE);
                                }
                            }
                        });

                    deleteButtonPanel.add(btnBack);
                    /* Purpose: Adding btnBack to deleteButtonPanel.
                     * Basis: Ensures the back button is part of the button panel.
                     */

                    southPanel.add(deleteButtonPanel);
                    /* Purpose: Adding deleteButtonPanel to southPanel.
                     * Basis: Integrates the button panel into the larger south panel.
                     */
                    deletePanel.add(southPanel);
                    /* Purpose: Adding southPanel to deletePanel.
                     * Basis: Integrates the south panel into the larger delete panel.
                     */

                    // Load contacts into the table for deletion
                    cms.loadContactsIntoTable(model);
                    /* Purpose: Loading customer data into the table for deletion.
                     * Basis: Calls a method (loadContactsIntoTable) of an object (cms) to populate 
                     * the table with customer data using the specified model.
                     */

                    btnBack.addActionListener(new ActionListener() {
                        /* Purpose: Adding an action listener to btnBack for handling the back button click event.
                         * Basis: An anonymous ActionListener is implemented to define the actions to be taken when btnBack is clicked.
                         * 
                         */
                            @Override
                            public void actionPerformed(ActionEvent e) {
                                panel.setVisible(true);
                                deletePanel.setVisible(false);
                                /* Purpose: Managing the visibility of panels to switch between different sections of the GUI.
                                 * Basis: Sets the visibility of panels (panel and deletePanel) to control which 
                                 * section is currently displayed in the GUI.
                                 */
                            }
                        });

                    panel.setVisible(false);
                    deletePanel.setVisible(true);

                    // Add a ComponentAdapter to dynamically update the size of scrollPane
                    deletePanel.addComponentListener(new ComponentAdapter() {
                        /* Purpose: Adding a ComponentAdapter to dynamically update the size of 
                         * scrollPane based on the resizing of deletePanel.
                         * Basis: The ComponentAdapter listens for component resizing events, adjusting 
                         * the size of scrollPane to maintain a responsive layout.
                         */
                            @Override
                            public void componentResized(ComponentEvent e) {
                                int panelWidth = deletePanel.getWidth();
                                int panelHeight = deletePanel.getHeight();

                                // Adjust the bounds of scrollPane based on the panel's size
                                scrollPane.setBounds(panelWidth / 10, panelHeight / 10, panelWidth * 8 / 10,
                                    panelHeight * 7 / 10);
                            }
                        });
                }
            });

        btnDelete.setBounds(140, 230, 150, 35);
        /* Purpose: Setting the position and size of the "Remove Customer" button (btnDelete) within panel.
         * Basis: Specifies the bounds of the button within its parent container.
         */
        btnDelete.setBackground(new java.awt.Color(102, 204, 255));
        /* Purpose: Setting the background color of btnDelete.
         * Basis: Provides a visual cue by setting a background color.
         */
        panel.add(btnDelete);
        /* Purpose: Adding the "Remove Customer" button to the main panel (panel).
         * Basis: Ensures that the button is part of the main GUI.
         */

            /* In summary, this code segment creates a GUI for managing customer data, including a
             * table for displaying information, buttons for deletion and navigation, and dynamic 
             * layout adjustments for responsiveness. The code employs Java's Swing library for GUI
             * components and event handling.
             *=====================================================*/

        btnDispAll = new JButton("View All Customer");
        btnDispAll.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    JPanel panelDispAll = new JPanel();
                    panelDispAll.setLayout(null);
                    frame.add(panelDispAll);

                    JLabel title=new JLabel("Customer's Vehicle Management System");
                    title.setFont(new Font("ARIAL",Font.BOLD,24));
                    title.setBounds(180, 10, 500, 20);
                    panelDispAll.add(title);
                    // Create and add components to the search panel
                    JLabel dispallTitle = new JLabel("View All Customer");
                    dispallTitle.setFont(new Font("ARIAL", Font.BOLD, 20));
                    dispallTitle.setBounds(320, 40, 500, 20);
                    panelDispAll.add(dispallTitle);

                    // Add a JLabel to display the note
                    JLabel noteLabel = new JLabel("List of all Customers:");
                    noteLabel.setBounds(35, 45, 200, 20); // Adjust the bounds as needed
                    panelDispAll.add(noteLabel);

                    String[] columnNames = { "First Name", "Last Name", "Birthday", "Address", "Contact No.", "Occupation",
                            "Gender", "V Type", "V Model", "V Color", "V Number" };
                    DefaultTableModel model = new DefaultTableModel(columnNames, 0);
                    JTable table = new JTable(model);
                    table.setDefaultEditor(Object.class, null);
                    JScrollPane scrollPane = new JScrollPane(table);
                    panelDispAll.add(scrollPane);
                    scrollPane.setBounds(35, 70, 700, 300);

                    try {
                        FileReader file = new FileReader("datafile.txt");
                        BufferedReader br = new BufferedReader(file);

                        String line;
                        while ((line = br.readLine()) != null) {
                            String[] info = line.split(","); // Adjust the delimiter based on your file format
                            model.addRow(new Object[] { info[0], info[1], info[2], info[3], info[4], info[5], info[6],
                                    info[7], info[8], info[9], info[10] });
                        }

                        br.close();
                    } catch (IOException ex) {
                        JOptionPane.showMessageDialog(null, "Error reading contacts file.", "Error",
                            JOptionPane.ERROR_MESSAGE);
                        ex.printStackTrace();
                    }

                    JButton btnBack = new JButton("Back to Menu");
                    btnBack.setBackground(new java.awt.Color(102, 204, 255));
                    panelDispAll.add(btnBack);
                    panel.setVisible(false);
                    panelDispAll.setVisible(true);

                    // Set the initial bounds of the 'Back' button
                    btnBack.setBounds(610, 380, 125, 30);

                    btnBack.addActionListener(new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent e) {
                                panel.setVisible(true);
                                panelDispAll.setVisible(false);
                            }
                        });
                }
            });
        btnDispAll.setBounds(140, 280, 150, 35);
        btnDispAll.setBackground(new java.awt.Color(102, 204, 255));
        panel.add(btnDispAll);

        btnExit = new JButton("EXIT");
        btnExit.setBounds(140, 330, 150, 35);
        btnExit.addActionListener(new ActionListener(){
                @Override
                public void actionPerformed(ActionEvent e){
                    if (e.getSource() == btnExit) {
                        System.exit(0);
                    }
                }
            });
        btnExit.setBackground(new java.awt.Color(102, 204, 255));
        panel.add(btnExit);

        totalCustomersLabel = new JLabel("Customer(s) Total: 0");
        totalCustomersLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        totalCustomersLabel.setBounds(475, 90, 150, 20);
        panel.add(totalCustomersLabel);

        totalVehiclesLabel = new JLabel("Vehicle(s) Total: 0");
        totalVehiclesLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        totalVehiclesLabel.setBounds(475, 120, 150, 20);
        panel.add(totalVehiclesLabel);

        JLabel quote1 = new JLabel("You're the driver of your own life,");
        quote1.setFont(new Font("Arial", Font.PLAIN, 14));
        quote1.setBounds(475, 190, 300, 20);
        panel.add(quote1);

        JLabel quote2 = new JLabel("Don't let anyone steal your seat.");
        quote2.setFont(new Font("Arial", Font.PLAIN, 14));
        quote2.setBounds(475, 220, 300, 20);
        panel.add(quote2);

        JLabel quote3 = new JLabel("-Moosa Rahat");
        quote3.setBounds(600, 250, 100, 20);
        panel.add(quote3);
        frame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == btnSubmit) {
            CMSModules cms = new CMSModules();
            int selectedDay = (int) dayComboBox.getSelectedItem(); // Use the class-level dayComboBox here
            String selectedMonth = (String) monthComboBox.getSelectedItem();
            int selectedYear = (int) yearComboBox.getSelectedItem();
            String lastname, firstname, contact, birthday, address, occupation, vehicleType, vehicleModel, vehicleColor,
            vehicleNumber;
            lastname = txtLastName.getText();
            firstname = txtFirstName.getText();
            contact = txtContact.getText();
            birthday = selectedMonth + " " + selectedDay + " " + selectedYear;
            address = txtAddress.getText();
            occupation = txtOccupation.getText();
            vehicleType = txtVehicleType.getText();
            vehicleModel = txtVehicleModel.getText();
            vehicleColor = txtVehicleColor.getText();
            vehicleNumber = txtVehicleNumber.getText();

            String gender = "";
            if (maleRadioButton.isSelected()) {
                gender = "Male";
            } else if (femaleRadioButton.isSelected()) {
                gender = "Female";
            } else {
                JOptionPane.showMessageDialog(null, "Please select a gender.", "Error", JOptionPane.WARNING_MESSAGE);
                return;
            }
            if (txtFirstName.getText().equals("")) {
                JOptionPane.showMessageDialog(null, "Enter First Name", "Error", JOptionPane.WARNING_MESSAGE);
                txtFirstName.requestFocus();
            } else if (txtLastName.getText().equals("")) {
                JOptionPane.showMessageDialog(null, "Enter Last Name", "Error", JOptionPane.WARNING_MESSAGE);
                txtLastName.requestFocus();
            } else if (txtOccupation.getText().equals("")) {
                JOptionPane.showMessageDialog(null, "Enter Occupation", "Error", JOptionPane.WARNING_MESSAGE);
                txtOccupation.requestFocus();
            } else if (txtVehicleType.getText().equals("")) {
                JOptionPane.showMessageDialog(null, "Enter Vehicle Type", "Error", JOptionPane.WARNING_MESSAGE);
                txtVehicleType.requestFocus();
            } else if (txtVehicleModel.getText().equals("")) {
                JOptionPane.showMessageDialog(null, "Enter Vehicle Model", "Error", JOptionPane.WARNING_MESSAGE);
                txtVehicleModel.requestFocus();
            } else if (txtVehicleColor.getText().equals("")) {
                JOptionPane.showMessageDialog(null, "Enter Vehicle Color", "Error", JOptionPane.WARNING_MESSAGE);
                txtVehicleColor.requestFocus();
            } else if (txtVehicleNumber.getText().equals("")) {
                JOptionPane.showMessageDialog(null, "Enter Vehicle Number", "Error", JOptionPane.WARNING_MESSAGE);
                txtVehicleNumber.requestFocus();
            } else if (txtContact.getText().equals("")) {
                JOptionPane.showMessageDialog(null, "Enter Contact", "Error", JOptionPane.WARNING_MESSAGE);
                txtContact.requestFocus();
            } else if (txtAddress.getText().equals("")) {
                JOptionPane.showMessageDialog(null, "Enter Address", "Error", JOptionPane.WARNING_MESSAGE);
                txtAddress.requestFocus();
            } else {
                lastname = txtLastName.getText();
                firstname = txtFirstName.getText();
                contact = txtContact.getText();
                address = txtAddress.getText();
                occupation = txtOccupation.getText();
                vehicleType = txtVehicleType.getText();
                vehicleModel = txtVehicleModel.getText();
                vehicleColor = txtVehicleColor.getText();
                vehicleNumber = txtVehicleNumber.getText();
                JOptionPane.showMessageDialog(null, "Data Successfully Added!", "Success", JOptionPane.INFORMATION_MESSAGE);
                txtFirstName.setText("");
                txtLastName.setText("");
                txtAddress.setText("");
                txtContact.setText("");
                txtOccupation.setText("");
                txtVehicleType.setText("");
                txtVehicleModel.setText("");
                txtVehicleColor.setText("");
                txtVehicleNumber.setText("");
                 cms.addData(firstname, lastname, birthday, address, contact, occupation, gender, vehicleType, vehicleModel,
                vehicleColor, vehicleNumber);
                int vehiclesCount = CMSModules.getCustomerCount();
                int customerCount = CMSModules.getCustomerCount();
                totalCustomersLabel.setText("Customer(s) Total: " + customerCount);
                totalVehiclesLabel.setText("Vehicle(s) Total: " + vehiclesCount);
            }
        }
    }

}
