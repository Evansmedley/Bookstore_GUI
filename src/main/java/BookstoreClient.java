public class BookstoreClient {
    public static void main(String[] args) {
        BookstoreModel model = new BookstoreModel();
        BookstoreFrame frame = new BookstoreFrame(model);
        BookstoreController controller = new BookstoreController(model);
        frame.signIn();
    }
}
