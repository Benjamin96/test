
package Dtos;

/**
 *
 * Ben And Chris
 * @author Ben
 */

/*
bookID' int(4) NOT NULL,
    'bookName' varchar(20),
    'author' varchar(30),
    'publisher' varchar(20),
    'copies' int(2),
*/
public class BookStock implements Comparable<BookStock>{
    private int bookID;
    private String bookName;
    private String author;
    private String publisher;
    private int copies;

    public BookStock() {
    }

    public BookStock(int bookID, String bookName, String author, String publisher, int copies) {
        this.bookID = bookID;
        this.bookName = bookName;
        this.author = author;
        this.publisher = publisher;
        this.copies = copies;
    }

    public int getBookID() {
        return bookID;
    }

    public String getBookName() {
        return bookName;
    }

    public String getAuthor() {
        return author;
    }

    public String getPublisher() {
        return publisher;
    }

    public int getCopies() {
        return copies;
    }

    public void setBookID(int bookID) {
        this.bookID = bookID;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public void setCopies(int copies) {
        this.copies = copies;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + this.bookID;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final BookStock other = (BookStock) obj;
        if (this.bookID != other.bookID) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "bookID= " + bookID + " bookName= " + bookName + " author= " + author + " publisher= " + publisher + ", copies=" + copies + "\n";
    }

    @Override
    public int compareTo(BookStock o) {
        if(this.bookID > o.bookID)
        {
            return +1;
        }
        if(this.bookID < o.bookID)
        {
            return -1;
        }
        else
            return 0;
    }
    
    
    
    
}


