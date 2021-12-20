import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BookstoreModel {

    private static final String DB_USER_ID = "postgres";
    private static final String DB_PASSWORD = "";
    private static final String DB_URL = "jdbc:postgresql://localhost:5432/COMP3005";

    private List<BookstoreView> bookstoreViews;
    private UserType userType;
    private String currentUserEmail;
    private int currentOrderNumber;
    private ArrayList<Book> currentSearchResults;
    private ArrayList<Book> currentCartContents;

    public enum UserType {
        UNDECIDED(-1), CUSTOMER(0), ADMINISTRATOR(1);
        private final int value;
        UserType(int value) {
            this.value = value;
        }
        public int getValue() {
            return this.value;
        }
    }
    public enum SearchCategory {
        TITLE("Title"), AUTHOR("Author"), ISBN("ISBN"), GENRE("Genre"), PUBLISHER("Publisher");
        private final String value;
        SearchCategory(String value) {
            this.value = value;
        }
        public String getValue() {
            return this.value;
        }
    }

    public BookstoreModel() {
        this.bookstoreViews = new ArrayList<>();
        this.userType = UserType.UNDECIDED;
        this.currentUserEmail = "";
        this.currentSearchResults = new ArrayList<>();
        this.currentCartContents = new ArrayList<>();
        this.currentOrderNumber = 4;
    }

    public void addView(BookstoreView view) {
        this.bookstoreViews.add(view);
    }

    public void removeView(BookstoreView view) {
        this.bookstoreViews.remove(view);
    }

    public void notifyViews() {
        for (BookstoreView view : bookstoreViews) {
            view.update(new BookstoreEvent(this));
        }
    }

    public static void printQueryResults(ArrayList<ArrayList<Object>> result) {
        for (ArrayList<Object> row : result) {
            System.out.print("| ");
            for (Object elem : row) {
                System.out.print(elem + " | ");
            }
            System.out.println();
        }
    }

    public static ArrayList<ArrayList<Object>> queryDatabase(String query) {
        // Create and ArrayList to store query results
        ArrayList<ArrayList<Object>> result = new ArrayList<>();

        // Connect to the database and query
        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER_ID, DB_PASSWORD);
             Statement statement = connection.createStatement();) {
            ResultSet resultSet = statement.executeQuery(query);
            ResultSetMetaData rsmd = resultSet.getMetaData();

            // Iterate through the results and store all information to be returned
            for (int row = 0; resultSet.next(); row++) {
                result.add(new ArrayList<>());
                for (int column = 1; column <= rsmd.getColumnCount(); column++) {
                    result.get(row).add(resultSet.getObject(column));
                }
            }
        } catch (Exception sqle) {
            System.out.println("Exception: " + sqle);
        }
        printQueryResults(result);
        return result;
    }

    public static void updateDatabase(String query) {
        // Connect to the database and query
        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER_ID, DB_PASSWORD);
             Statement statement = connection.createStatement();) {
            statement.executeQuery(query);
        } catch (Exception sqle) {
            System.out.println("Exception: " + sqle);
        }
    }

    public ArrayList<Book> extractBooks(ArrayList<ArrayList<Object>> queryResult) {
        ArrayList<Book> books = new ArrayList<>();
        for (ArrayList<Object> row : queryResult) {
            books.add(new Book(row.get(0).toString(), (String) row.get(1), (String) row.get(2),
                    ((BigDecimal) row.get(3)).intValue(), ((BigDecimal) row.get(4)).doubleValue(),
                    ((int) row.get(5)), ((BigDecimal) row.get(6)).intValue(),
                    (int) row.get(7), row.get(9).toString(), (String) row.get(10)));
        }
        return books;
    }

    public void resetUser() {
        this.userType = UserType.UNDECIDED;
    }

    public void setUser(UserType userType, String email) {
        this.userType = userType;
        this.currentUserEmail = email;
        notifyViews();
    }

    public UserType getUserType() {
        return this.userType;
    }

    public ArrayList<Book> getCurrentSearchResults() {
        return this.currentSearchResults;
    }

    public ArrayList<Book> getCurrentCartContents() {
        return currentCartContents;
    }

    public void search(String searchText, SearchCategory searchCategory) {
        currentSearchResults = new ArrayList<>();
        String[] searchTerms = searchText.split(" ");
        ArrayList<ArrayList<Object>> result = null;
        for (String term : searchTerms) {
            term = term.toLowerCase();
            result = switch (searchCategory) {
                case TITLE -> queryDatabase("select * " +
                        "from book " +
                        "where title like '%" + term + "%';");
                case AUTHOR -> queryDatabase("select * " +
                        "from book join author on book.author_email = author.email " +
                        "where first_name like '%" + term + "%' or last_name like '%" + term + "%';");
                case ISBN -> queryDatabase("select * " +
                        "from book " +
                        "where isbn = '" + term + "';");
                case GENRE -> queryDatabase("select * " +
                        "from book " +
                        "where genre like '%" + term + "%';");
                case PUBLISHER -> queryDatabase("select * " +
                        "from book join publisher on book.publisher_phone_number = publisher.phone_number " +
                        "where name like '%" + term + "%';");
            };
            if (result.size() > 0) {
                ArrayList<Book> books = extractBooks(result);
                for (Book book : books) {
                    if (!currentSearchResults.contains(book)) {
                        currentSearchResults.add(book);
                    }
                }
            }
        }
        notifyViews();
    }

    public void addBookToCart(int index) {
        currentCartContents.add(currentSearchResults.get(index));
        notifyViews();
    }

    public void removeBookFromCart(int index) {
        currentCartContents.remove(index);
        notifyViews();
    }

    public void emptyCart() {
        currentCartContents = new ArrayList<>();
        notifyViews();
    }

    public void checkout(String creditCardNumber, String securityCode, String firstName, String lastName,
                         String postalCode, String streetNumber, String streetName, String aptNumber, String city,
                         String province) {
        if (aptNumber.equals("")) {
            aptNumber = "-1";
        }

        currentOrderNumber++;

        double totalPrice = 0.0;
        for (Book book : currentCartContents) {
            totalPrice += book.getPrice();
        }

        updateDatabase("insert into address values ('" + postalCode  + "', '" + streetNumber + "', '" + streetName +
                "', '" + aptNumber + "', '" + city + "', '" + province + "');");
        updateDatabase("insert into book_order values ('" + currentOrderNumber + "', '" + totalPrice + "', " +
                "default, '" + creditCardNumber + "', '" + securityCode + "', '" + firstName + "', '" + lastName +
                "', '" + postalCode + "');");
        updateDatabase("insert into customer_order values ('" + currentOrderNumber + "', '" + currentUserEmail + "');");

        ArrayList<ArrayList<Object>> check;

        int quantity;
        int stock;

        for (Book book : currentCartContents) {
            check = queryDatabase("select quantity_ordered from books_ordered where ISBN = '" + book.getIsbn() + "' and order_number = '" + currentOrderNumber  + "';");
            if (check.size() != 0) {
                updateDatabase("update books_ordered set quantity_ordered = '" + (((int) check.get(0).get(0)) + 1) + "' where isbn = '" + book.getIsbn() + "' and order_number = '" + currentOrderNumber + "';");
            } else {
                updateDatabase("insert into books_ordered values ('" + book.getIsbn() + "', '" + currentOrderNumber + "', '1');");
            }
            updateDatabase("update book set stock = '" + (book.getStock() - 1) + "', num_sold_prev_month = '" + (book.getNum_sold_prev_month() + 1) + "' where ISBN = '" + book.getIsbn() + "';");
            book.setStock(book.getStock() - 1);
            book.setNum_sold_prev_month(book.getNum_sold_prev_month() - 1);
            if (book.getStock() < 10) {
                orderMore(book.getIsbn(), book.getStock());
            }
        }
        emptyCart();
    }

    public void orderMore(String isbn, int stock) {
        updateDatabase("update book set stock = '" + (stock + 10) + "' where ISBN = '" + isbn + "';");
        System.out.println("Ordering more of " + isbn + "!");
    }

    public void removeBook(String isbn) {
        updateDatabase("delete from book where ISBN = '" + isbn + "';");
    }

    public void addBook(String[] bookInfo) {
        for (String i : bookInfo) {
            System.out.println(i);
        }
        updateDatabase("insert into author values('" + bookInfo[10] + "', '" + bookInfo[11] + "', '" + bookInfo[12] + "');");

        updateDatabase("insert into address values('" + bookInfo[17] + "', '" + bookInfo[18] + "', '" + bookInfo[19] + "', '" + bookInfo[20] + "', '" + bookInfo[21] + "', '" + bookInfo[22] + "');");

        updateDatabase("insert into publisher values('" + bookInfo[9] + "', '" + bookInfo[13] + "', '" +
                bookInfo[14] + "', '" + bookInfo[15] + "', '" + bookInfo[16] + "', '0', '" + bookInfo[17] + "');");

        updateDatabase("insert into book values('" + bookInfo[0] + "', '" + bookInfo[1] + "', '" + bookInfo[2] +
                "', '" + bookInfo[3] + "', '" + bookInfo[4] + "', '" + bookInfo[5] + "', '" + bookInfo[6] + "', '" +
                bookInfo[7] + "', '" + bookInfo[8] + "', '" + bookInfo[9] + "', '" + bookInfo[10] + "');");
    }
}
