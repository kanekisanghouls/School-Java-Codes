import java.io.*;
import javax.swing.table.*;
import javax.swing.*;
import java.util.ArrayList;

public class CMSModules {
    private static ArrayList<Contact> contactList = new ArrayList<>();

    public static void addData(String firstName, String lastName, String birthday, String address,
        String contact, String occupation, String gender, String vehicleType,
        String vehicleModel, String vehicleColor, String vehicleNumber) {

    String file = "datafile.txt";
    try (PrintWriter writer = new PrintWriter(new FileWriter(file, true))) {
        writer.println(firstName + ","
                + lastName + ","
                + birthday + ","
                + address + ","
                + contact + ","
                + occupation + ","
                + gender + ","
                + vehicleType + ","
                + vehicleModel + ","
                + vehicleColor + ","
                + vehicleNumber);
    } catch (IOException e) {
        e.printStackTrace();
    }

    Contact newContact = new Contact(firstName, lastName, birthday, address, contact, occupation, gender,
            vehicleType, vehicleModel, vehicleColor, vehicleNumber);
    contactList.add(newContact);
}
    public static int getCustomerCount() {
        return contactList.size();
    }

    public static void loadData() {
        try {
            FileReader file = new FileReader("datafile.txt");
            BufferedReader br = new BufferedReader(file);

            String line;
            while ((line = br.readLine()) != null) {
                String[] info = line.split(","); // Adjust the delimiter based on your file format
                String firstName = info[0];
                String lastName = info[1];
                String birthday = info[2];
                String address = info[3];
                String contact = info[4];
                String occupation = info[5];
                String gender = info[6];
                String vehicleType = info[7];
                String vehicleModel = info[8];
                String vehicleColor = info[9];
                String vehicleNumber = info[10];

                Contact newContact = new Contact(firstName, lastName, birthday, address, contact, occupation, gender,
                        vehicleType, vehicleModel, vehicleColor, vehicleNumber);
                contactList.add(newContact);
            }
            br.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public void updateContactInFile(Contact contact) {
        try {
            File inputFile = new File("datafile.txt");
            File tempFile = new File("tempfile.txt");

            BufferedReader reader = new BufferedReader(new FileReader(inputFile));
            BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile));

            String currentLine;
            while ((currentLine = reader.readLine()) != null) {
                String[] info = currentLine.split(",");
                if (info[0].equals(contact.getFirstName())) {
                    // Update the contact information
                    String updatedLine = contact.getFirstName() + ","
                            + contact.getLastName() + ","
                            + contact.getBirthday() + ","
                            + contact.getAddress() + ","
                            + contact.getContact() + ","
                            + contact.getOccupation() + ","
                            + contact.getGender() + ","
                            + contact.getVehicleType() + ","
                            + contact.getVehicleModel() + ","
                            + contact.getVehicleColor() + ","
                            + contact.getVehicleNumber();
                    System.out.println("Updated line: " + updatedLine);
                    writer.write(updatedLine + System.getProperty("line.separator"));
                } else {
                    writer.write(currentLine + System.getProperty("line.separator"));
                }
            }
            writer.close();
            reader.close();

            // Delete the original file and rename the temporary file to the original file name
            if (inputFile.delete()) {
                if (!tempFile.renameTo(inputFile)) {
                    System.out.println("Could not rename the file");
                }
            } else {
                System.out.println("Could not delete the file");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static ArrayList<Contact> getContactList() {
        return contactList;
    }

    public static void loadContactsIntoTable(DefaultTableModel model) {
        try {
            FileReader file = new FileReader("datafile.txt");
            BufferedReader br = new BufferedReader(file);

            String line;
            while ((line = br.readLine()) != null) {
                String[] info = line.split(","); // Adjust the delimiter based on your file format
                model.addRow(new Object[] { info[0], info[1], info[2], info[3],
                        info[4], info[5], info[6], info[7], info[8], info[9],
                        info[10] });
            }
            br.close();
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, "Error reading contacts file.", "Error", JOptionPane.ERROR_MESSAGE);
            ex.printStackTrace();
        }
    }

    public static boolean removeContactFromFile(String idToDelete) {
        /* This code defines a static method called removeContactFromFile that takes a String parameter
         * called idToDelete. The method returns a boolean value
         * indicating whether the contact with the specified
         * idToDelete was successfully removed from a file.
         * Without more context, it is difficult to determine the exact
         * implementation of this method. However, based on the
         * 
         * This code defines a method called removeContactFromFile that takes a String parameter
         * called idToDelete. The purpose of this method is to remove a contact from a file based on their unique
         * identifier.
         * 
         * Without more context, it is difficult to determine the
         * specific implementation details of this method. However,
         * based on the name of the method, it is likely that the
         * method is designed to read in a file containing contact information and remove the contact with the specified
         * idToDelete from the file.
         * It is important to note that this method may not be able
         * to remove the contact from the file if the file is not
         * accessible or if the idToDelete parameter does not
         * match any existing contacts in the file.
         */
        try {
            File inputFile = new File("datafile.txt");
            // The File class is a part of the Java standard library and
            File tempFile = new File("tempfile.txt");

            BufferedReader reader = new BufferedReader(new FileReader(inputFile));
            BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile));

            String currentLine;
            while ((currentLine = reader.readLine()) != null) {
                String[] info = currentLine.split(",");
                if (!info[0].equals(idToDelete)) {
                    writer.write(currentLine + System.getProperty("line.separator"));
                }
            }
            writer.close();
            reader.close();

            // Delete the original file and rename the temporary file to the original file name
            if (inputFile.delete()) {
                if (!tempFile.renameTo(inputFile)) {
                    System.out.println("Could not rename the file");
                    return false;
                }
            } else {
                System.out.println("Could not delete the file");
                return false;
            }

            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean updateContact(Contact updatedContact) {
        // Assume contactList is your list of contacts
        for (Contact contact : contactList) {
            // Assuming 'id' is a unique identifier (you might have a different unique identifier)
            if (contact.getFirstName().equals(updatedContact.getFirstName())) {
                // Update the existing contact's information
                contact.setFirstName(updatedContact.getFirstName());
                contact.setLastName(updatedContact.getLastName());
                contact.setBirthday(updatedContact.getBirthday());
                contact.setAddress(updatedContact.getAddress());
                contact.setContact(updatedContact.getContact());
                contact.setOccupation(updatedContact.getOccupation());
                contact.setGender(updatedContact.getGender());
                contact.setVehicleType(updatedContact.getVehicleType());
                contact.setVehicleModel(updatedContact.getVehicleModel());
                contact.setVehicleColor(updatedContact.getVehicleColor());
                contact.setVehicleNumber(updatedContact.getVehicleNumber());

                // Data has been updated, return true to indicate success
                return true;
            }
        }
        // If contact with the given ID is not found, return false
        return false;
    }
}
