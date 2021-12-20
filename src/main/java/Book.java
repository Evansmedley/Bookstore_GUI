public class Book {
    private final String isbn;
    private final String title;
    private final String genre;
    private final int number_of_pages;
    private final double price;
    private final int stock;
    private final int percentage_of_sales;
    private final int num_sold_prev_month;
    private final String author_email;
    private final String publisher_phone_number;

    public Book(String isbn, String title, String genre, int number_of_pages, double price, int stock, int percentage_of_sales, int num_sold_prev_month, String author_email, String publisher_phone_number) {
        this.isbn = isbn;
        this.title = title;
        this.genre = genre;
        this.number_of_pages = number_of_pages;
        this.price = price;
        this.stock = stock;
        this.percentage_of_sales = percentage_of_sales;
        this.num_sold_prev_month = num_sold_prev_month;
        this.author_email = author_email;
        this.publisher_phone_number = publisher_phone_number;
    }

    public String getIsbn() {
        return isbn;
    }

    public String getTitle() {
        return title;
    }

    public String getGenre() {
        return genre;
    }

    public int getNumber_of_pages() {
        return number_of_pages;
    }

    public double getPrice() {
        return price;
    }

    public int getStock() {
        return stock;
    }

    public int getPercentage_of_sales() {
        return percentage_of_sales;
    }

    public int getNum_sold_prev_month() {
        return num_sold_prev_month;
    }

    public String getAuthor_email() {
        return author_email;
    }

    public String getPublisher_phone_number() {
        return publisher_phone_number;
    }


    @Override
    public String toString() {
        return title +
                ", genre='" + genre + '\'' +
                ", number_of_pages=" + number_of_pages +
                ", price=" + price +
                ", stock=" + stock +
                ", isbn='" + isbn + '\'' +
                '}';
    }
}
