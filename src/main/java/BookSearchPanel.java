import javax.swing.*;
import java.awt.*;

public class BookSearchPanel extends BookListPanel {
    private JTextField searchBar;
    private JButton searchButton;
    private JButton addToCartButton;
    private JComboBox<String> searchCategories;

    public BookSearchPanel(BookstoreModel model) {
        super(model);

        BookstoreModel.SearchCategory[] categories = {BookstoreModel.SearchCategory.TITLE, BookstoreModel.SearchCategory.AUTHOR,
                BookstoreModel.SearchCategory.ISBN, BookstoreModel.SearchCategory.GENRE,
                BookstoreModel.SearchCategory.PUBLISHER};
        String[] categoryText = new String[5];
        for (int i = 0; i < categories.length; i++) {
            categoryText[i] = categories[i].getValue();
        }
        this.searchCategories = new JComboBox<>(categoryText);
        searchCategories.setEditable(false);
        searchCategories.setPreferredSize(new Dimension(120,20));

        this.searchBar = new JTextField();
        searchBar.setPreferredSize(new Dimension(300, 20));

        this.searchButton = new JButton("Search");
        searchButton.addActionListener(e -> model.search(searchBar.getText(), categories[searchCategories.getSelectedIndex()]));
        searchButton.setPreferredSize(new Dimension(60, 20));

        JPanel searchPanel = new JPanel();
        searchPanel.add(searchCategories);
        searchPanel.add(searchBar);
        searchPanel.add(searchButton);
        this.add(searchPanel, BorderLayout.PAGE_START);

        this.addToCartButton = new JButton("Add to Cart");
        addToCartButton.setPreferredSize(new Dimension(480,20));
        addToCartButton.addActionListener(e -> model.addBookToCart(bookList.getSelectedIndex()));
        JPanel bottomPanel = new JPanel();
        bottomPanel.add(addToCartButton);
        this.add(bottomPanel, BorderLayout.PAGE_END);
    }

    @Override
    public void update(BookstoreEvent e) {
        bookListModel.clear();
        for (Book book : model.getCurrentSearchResults()) {
            addBook(book);
        }
    }
}
