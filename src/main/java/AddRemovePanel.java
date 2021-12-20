import javax.swing.*;
import java.awt.*;

public class AddRemovePanel extends JPanel implements BookstoreView {
    private final BookstoreModel model;
    private String[] attributeValues;
    private JTextField[] jTextFields;

    public AddRemovePanel(BookstoreModel model) {
        super();
        this.model = model;
        this.model.addView(this);
        this.setPreferredSize(new Dimension(500,800));
        this.setBorder(BorderFactory.createLineBorder(Color.black));
        this.setLayout(new GridLayout(27, 2));
        this.add(new JLabel("Add a book", JLabel.CENTER));
        JButton add = new JButton("Add Book");
        add.addActionListener(e -> addBook());
        this.add(add);
        String[] attributes = new String[]{"ISBN: ", "Title: ", "Genre: ", "Number of pages: ", "Price: ", "Stock: ",
                "Percentage of sales: ", "Number sold prev month: ", "Date published: ", "Publisher phone number: ",
                "Author email: ", "Author first name: ", "Author last name: ", "Publisher name: ", "Publisher email: ",
                "Publisher transit number: ", "Publisher account number: ", "Publisher postal code: ",
                "Publisher street number: ", "Publisher street name: ", "Publisher apt number (optional): " +
                "", "Publisher city: ", "Publisher province: "};
        attributeValues = new String[23];
        jTextFields = new JTextField[23];

        for (int i = 0; i < attributes.length; i++) {
            this.add(new JLabel(attributes[i], JLabel.CENTER));
            jTextFields[i] = new JTextField();
            this.add(jTextFields[i]);
        }

        this.add(new JSeparator(JSeparator.HORIZONTAL));
        this.add(new JSeparator(JSeparator.HORIZONTAL));

        this.add(new JLabel("Remove a book", JLabel.CENTER));
        JButton remove = new JButton("Remove Book");
        JTextField removeIsbn = new JTextField();
        remove.addActionListener(e -> model.removeBook(removeIsbn.getText()));
        this.add(remove);
        this.add(new JLabel("ISBN: ", JLabel.CENTER));
        this.add(removeIsbn);
    }

    private void addBook() {
        for (int i = 0; i < jTextFields.length; i++) {
            attributeValues[i] = jTextFields[i].getText();
        }
        if (attributeValues[20].equals("")) {
            attributeValues[20] = "-1";
        }
        model.addBook(attributeValues);
    }

    @Override
    public void update(BookstoreEvent e) {
        for (int i = 0; i < jTextFields.length; i++) {
            jTextFields[i].setText("");
            attributeValues[i] = "";
        }
    }
}
