import java.util.EventObject;

public class BookstoreEvent extends EventObject {

    /**
     * Constructs a prototypical Event.
     *
     * @param source the object on which the Event initially occurred
     * @throws IllegalArgumentException if source is null
     */
    public BookstoreEvent(BookstoreModel source) {
        super(source);
    }
}
