import javax.swing.*;
import java.awt.*;

public class BookstoreFrame extends JFrame implements BookstoreView {

    private final BookstoreModel model;
    private BookSearchPanel searchPanel;
    private CheckoutPanel checkoutPanel;
    private ReportPanel reportPanel;
    private AddRemovePanel addRemovePanel;
    public static final String ADMIN_EMAIL = "admin@gmail.com";

    public BookstoreFrame(BookstoreModel model) {
        super();
        this.model = model;
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(new BorderLayout());
        this.setPreferredSize(new Dimension(1001,801));
        this.model.addView(this);

        this.searchPanel = new BookSearchPanel(model);
        this.checkoutPanel = new CheckoutPanel(model);
        this.reportPanel = new ReportPanel(model);
        this.addRemovePanel = new AddRemovePanel(model);

        JMenuBar menuBar = new JMenuBar();
        JMenu file = new JMenu("File");
        menuBar.add(file);
        JMenuItem signout = new JMenuItem("Sign Out");
        signout.addActionListener(e -> signIn());
        file.add(signout);
        this.setJMenuBar(menuBar);
    }

    public void signIn() {
        this.setVisible(false);
        model.resetUser();
        String[] options = {"Customer", "Administrator"};
        int user = -1;
        String message = "Are you a customer or an administrator?";
        while (!(user == BookstoreModel.UserType.CUSTOMER.getValue() | user == BookstoreModel.UserType.ADMINISTRATOR.getValue())) {
            user = JOptionPane.showOptionDialog(this, message, "Sign In",
                    JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[1]);
        }
        String email;
        String password;
        boolean success = false;

        JPanel signInPanel = new JPanel(new BorderLayout(5,5));
        JPanel label = new JPanel(new GridLayout(0,1,2,2));
        label.add(new JLabel("Email", SwingConstants.RIGHT));
        label.add(new JLabel("Password", SwingConstants.RIGHT));
        signInPanel.add(label, BorderLayout.WEST);

        JPanel controls = new JPanel(new GridLayout(0,1,2,2));
        controls.setPreferredSize(new Dimension(200,50));
        JTextField emailField = new JTextField();
        controls.add(emailField);
        JPasswordField passwordField = new JPasswordField();
        controls.add(passwordField);
        signInPanel.add(controls, BorderLayout.CENTER);

        while (!success) {
            emailField.setText("");
            passwordField.setText("");
            if (user == BookstoreModel.UserType.CUSTOMER.getValue()) {
                emailField.setEditable(true);
                JOptionPane.showMessageDialog(this, signInPanel, "Login", JOptionPane.QUESTION_MESSAGE);
                email = emailField.getText();
                password = new String(passwordField.getPassword());
                if (BookstoreModel.queryDatabase("select email, password from customer where email ='" + email + "' and password = '" + password + "'").size() == 1) {
                    success = true;
                    model.setUser(BookstoreModel.UserType.CUSTOMER, email);
                }
            } else {
                emailField.setText(ADMIN_EMAIL);
                emailField.setEditable(false);
                JOptionPane.showMessageDialog(this, signInPanel, "login", JOptionPane.QUESTION_MESSAGE);
                password = new String(passwordField.getPassword());
                if (BookstoreModel.queryDatabase("select email, password from customer where email = '" + ADMIN_EMAIL + "' and password = '" + password + "'").size() == 1) {
                    success = true;
                    model.setUser(BookstoreModel.UserType.ADMINISTRATOR, ADMIN_EMAIL);
                }
            }
        }
    }


    @Override
    public void update(BookstoreEvent e) {
        if (model.getUserType() == BookstoreModel.UserType.CUSTOMER) {
            this.remove(reportPanel);
            this.remove(addRemovePanel);
            this.add(searchPanel, BorderLayout.WEST);
            this.add(checkoutPanel, BorderLayout.EAST);
        } else if (model.getUserType() == BookstoreModel.UserType.ADMINISTRATOR) {
            this.remove(searchPanel);
            this.remove(checkoutPanel);
            this.add(reportPanel, BorderLayout.WEST);
            this.add(addRemovePanel, BorderLayout.EAST);
        } else {
            removeAll();
        }
        this.pack();
        this.setVisible(true);
    }
}
