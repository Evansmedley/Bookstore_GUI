import javax.swing.*;
import java.awt.*;

public abstract class BookListPanel extends JPanel implements BookstoreView {
    protected final BookstoreModel model;
    protected DefaultListModel<Book> bookListModel;
    protected JList<Book> bookList;

    public BookListPanel(BookstoreModel model) {
        this.model = model;
        model.addView(this);
        this.setLayout(new BorderLayout());
        this.setPreferredSize(new Dimension(500,800));
        this.setBorder(BorderFactory.createLineBorder(Color.black));

        this.bookListModel = new DefaultListModel<>();
        this.bookList = new JList<>(bookListModel);
        this.add(new JScrollPane(bookList), BorderLayout.CENTER);
    }

    public void addBook(Book book) {
        bookListModel.addElement(book);
    }

    public void removeBook(Book book) {
        if (bookListModel.contains(book)) {
            bookListModel.removeElement(book);
        }
    }
}
