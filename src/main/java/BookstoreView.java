/**
 * The interface that all views must implement in order to reflect the state of the model
 */
public interface BookstoreView {
    /**
     * Method that updates the BookstoreView
     * @param e, event
     */
    void update(BookstoreEvent e);
}
