import javax.swing.*;
import java.awt.*;

public class CheckoutPanel extends BookListPanel {
    private JButton checkoutButton;
    private JButton removeFromCartButton;
    private JButton emptyCartButton;

    public CheckoutPanel(BookstoreModel model) {
        super(model);

        this.emptyCartButton = new JButton("Empty Cart");
        emptyCartButton.setPreferredSize(new Dimension(160,20));
        emptyCartButton.addActionListener(e -> model.emptyCart());

        this.removeFromCartButton = new JButton("Remove Selection");
        removeFromCartButton.setPreferredSize(new Dimension(160,20));
        removeFromCartButton.addActionListener(e -> model.removeBookFromCart(bookList.getSelectedIndex()));

        this.checkoutButton = new JButton("Checkout");
        checkoutButton.setPreferredSize(new Dimension(160,20));
        checkoutButton.addActionListener(e -> checkout());

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(emptyCartButton);
        buttonPanel.add(removeFromCartButton);
        buttonPanel.add(checkoutButton);
        this.add(buttonPanel, BorderLayout.PAGE_END);

        JLabel cart = new JLabel("Cart", JLabel.CENTER);
        cart.setPreferredSize(new Dimension(480,20));
        JPanel topPanel = new JPanel();
        topPanel.add(cart);
        this.add(topPanel, BorderLayout.PAGE_START);
    }

    private void checkout() {
        JPanel checkoutPanel = new JPanel(new BorderLayout(5,5));
        JPanel label = new JPanel(new GridLayout(0,1,2,2));
        label.add(new JLabel("Credit Card Number", SwingConstants.RIGHT));
        label.add(new JLabel("Security Code", SwingConstants.RIGHT));
        label.add(new JLabel("First Name", SwingConstants.RIGHT));
        label.add(new JLabel("Last Name", SwingConstants.RIGHT));
        label.add(new JLabel("Postal Code", SwingConstants.RIGHT));
        label.add(new JLabel("Street Number", SwingConstants.RIGHT));
        label.add(new JLabel("Street Name", SwingConstants.RIGHT));
        label.add(new JLabel("Apartment Number", SwingConstants.RIGHT));
        label.add(new JLabel("City", SwingConstants.RIGHT));
        label.add(new JLabel("Province", SwingConstants.RIGHT));
        checkoutPanel.add(label, BorderLayout.WEST);

        JPanel controls = new JPanel(new GridLayout(0,1,2,2));
        controls.setPreferredSize(new Dimension(300,500));
        JTextField creditCardField = new JTextField();
        controls.add(creditCardField);
        JTextField securityCodeField = new JTextField();
        controls.add(securityCodeField);
        JTextField firstNameField = new JTextField();
        controls.add(firstNameField);
        JTextField lastNameField = new JTextField();
        controls.add(lastNameField);
        JTextField postalCodeField = new JTextField();
        controls.add(postalCodeField);
        JTextField streetNumberField = new JTextField();
        controls.add(streetNumberField);
        JTextField streetNameField = new JTextField();
        controls.add(streetNameField);
        JTextField aptNumberField = new JTextField();
        controls.add(aptNumberField);
        JTextField cityField = new JTextField();
        controls.add(cityField);
        JTextField provinceField = new JTextField();
        controls.add(provinceField);
        checkoutPanel.add(controls, BorderLayout.CENTER);

        JOptionPane.showMessageDialog(this.getParent(), checkoutPanel, "Checkout", JOptionPane.QUESTION_MESSAGE);

        String creditCardNumber = creditCardField.getText();
        String securityCode = securityCodeField.getText();
        String firstName = firstNameField.getText();
        String lastName = lastNameField.getText();
        String postalCode = postalCodeField.getText();
        String streetNumber = streetNumberField.getText();
        String streetName = streetNameField.getText();
        String apt_number = aptNumberField.getText();
        String city = cityField.getText();
        String province = provinceField.getText();

        model.checkout(creditCardNumber, securityCode, firstName, lastName, postalCode, streetNumber, streetName, apt_number, city, province);
    }

    @Override
    public void update(BookstoreEvent e) {
        bookListModel.clear();
        for (Book book : model.getCurrentCartContents()) {
            addBook(book);
        }
    }
}
