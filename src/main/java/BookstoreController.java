public class BookstoreController {

    private final BookstoreModel model;
    private final BookstoreFrame frame;

    public BookstoreController(BookstoreModel model, BookstoreFrame frame) {
        this.model = model;
        this.frame = frame;

        frame.signIn();
    }
}