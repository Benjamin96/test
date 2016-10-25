
package Dtos;

/**
 *
 * Ben And Chris
 * @author Ben
 */
/*
'loanID' int(4) NOT NULL,
    'bookID' int(4) NOT NULL,
    'bookName' varchar(20),
    'userID' int(4) NOT NULL,
    'userName' varchar(20) 
*/
public class BookLoaned implements Comparable<BookLoaned>{
    private int loanID;
    private BookStock book;
    private Users user;

    public BookLoaned() {
    }

    public BookLoaned(int loanID, BookStock book, Users user) {
        this.loanID = loanID;
        this.book = book;
        this.user = user;
    }
    /*
    public BookLoaned(int loanID, int bookID, int userID) {
        this.loanID = loanID;
        this.book = book;
        this.user = user;
    }
    */

    public int getLoanID() {
        return loanID;
    }

    public BookStock getBook() {
        return book;
    }

    public Users gatUser() {
        return user;
    }

    public void setLoanID(int loanID) {
        this.loanID = loanID;
    }

    public void setBook(BookStock book) {
        this.book = book;
    }

    public void setUser(Users user) {
        this.user = user;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 13 * hash + this.loanID;
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
        final BookLoaned other = (BookLoaned) obj;
        if (this.loanID != other.loanID) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "BookLoaned{" + "loanID=" + loanID + ", book=" + book + ", user=" + user + '}';
    }

    @Override
    public int compareTo(BookLoaned o) {
        if(this.loanID > o.loanID)
        {
            return +1;
        }
        if(this.loanID < o.loanID)
        {
            return -1;
        }
        else
            return 0;
    }

    
    
    
}
