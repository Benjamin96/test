
package App;

import java.util.*;
import Daos.*;
import Dtos.*;

/** test
 *
 * 
 * @author Ben,Chris,Aleks
 */ 
public class AdminApp {
    public static void main(String[] args) 
    {
        int switchvar = 0;
        UsersDao uDao = new UsersDao("libraryca");
        BookStockDao bDao = new BookStockDao("libraryca");
        BookLoanedDao lDao = new BookLoanedDao("libraryca");
        ArrayList<Users> users = uDao.getAllUsers();
        ArrayList<BookStock> books = bDao.getAllBooks();
        
        
        Scanner input = new Scanner(System.in);
      
        System.out.println("Do you wish to register or login");
        String choice = input.nextLine();
        
        boolean flag = true;
        while(flag == true)
        {
            
            
            if(choice.toLowerCase().equals("register"))
            {
                Users userReg = new Users();
                System.out.println("please enter your username?");
                userReg.setUsername(input.nextLine());
                
                System.out.println("please enter password?");
                userReg.setPassword(input.nextLine());
                
                System.out.println("please enter firstname");
                userReg.setFname(input.nextLine());
                
                System.out.println("please enter lastname");
                userReg.setLname(input.nextLine());
                
                System.out.println("please enter address");
                userReg.setAddress(input.nextLine());
                
                System.out.println("please enter email");
                String emailCheck = input.nextLine();
                userReg.setEmail(emailCheck);
                
                userReg.setBooksLoaned(0);
                userReg.setAdmin(0);
                
                if(emailCheck.contains("@"))
                {
                    uDao.RegistorUser(userReg);
                    
                    flag = false;
                }
                else if(!(emailCheck.contains("@")))
                {
                    System.out.println("There is something missing in your email e.g(@)");
                }
            }
            else if(choice.toLowerCase().equals("login"))
            {
                Users userlog = new Users();
                
                
                System.out.println("please enter usrname?");
                String name = input.nextLine();
                
                System.out.println("please enter password");
                String password = input.nextLine();
                
                userlog.setUsername(name);
                userlog.setPassword(password);
                
                
                if(uDao.LogingInUser(name, password).equals(userlog) && uDao.getAdminByName(name).getAdmin() == 1)
                {
                    System.out.println("Welcome " + name + " you have logged in as an admin");
                    /*
                    Because of the way this as been designed the admin and normal user
                    stuff is the same and copy and pasted into.
                    This may or may not change as we learn more about how to adapt the program
                    */
                    int userid = uDao.getUserbyName(name).getUser_id();
                    
                    
                    System.out.println("What Do you want to do dislpay all titles, whats on loan, borrow a copy, return, logout \n As admin you can also, add titles, edit details , change amount of copies ,\n Remove a book or delete a member");
                    String displayAns = input.nextLine().toLowerCase();
                    
                    
                    /*
                        Typing in an answer will set the value of "switchvar" allowing the user to navigate
                        the app using switch statements
                    */
                    
                    switch (displayAns) {
                        case "display":
                            switchvar = 1;
                            break;
                        case "on loan":
                            switchvar = 2;
                            break;
                        case "loan":
                            switchvar = 3;
                            break;
                        case "return":
                            switchvar = 4;
                            break;
                        case "logout":
                            switchvar = 5;
                            break;
                        case "add":
                            switchvar = 6;
                            break;
                        case "edit":
                            switchvar = 7;
                            break;
                        case "change copies":
                            switchvar = 8;
                            break;
                        case "remove":
                            switchvar = 9;
                            break;
                        case "delete":
                            switchvar = 10;
                            break;
                        
                        default:
                            switchvar = 11;
                            break;
                    }
                    
                    switch (switchvar)
                    {
                        //Displays all the book
                        case 1:
                        for(int x = 0; x < books.size(); x++ )
                        {
                           BookStock bookList = bDao.getABookById(x);
                           System.out.println(bookList.toString());
                            
                        }
                        break;
                        
                        case 2:
                        for(int i = 0; i < books.size(); i++)
                        {
                            BookStock bookloan = bDao.getAllBooks().get(i);
                            System.out.println(lDao.getAllBooksOnLoan(bookloan).toString());
                        }
                        break;
                        
                        case 3:
                        System.out.println("Which book do you wish to loan eg(bookid please)");
                        int id = input.nextInt();
                        
                             bDao.takeOutABook(id);
                             BookStock bookEntry = bDao.getABookById(id);
                             lDao.BorrowABook(bookEntry, userid);
                             
                             input.nextLine();
                        
                        break;
                        
                        case 4:
                        System.out.println("The following is the books you have loaned");
                        
                        ArrayList<BookLoaned> loanedBooks = lDao.getAllBooksOnLoanByUser(userid);
                        if(loanedBooks.size() > 0){
                        
                        for(int i = 0; i < loanedBooks.size(); i++)
                        {
                             System.out.println(loanedBooks.get(i).getBook().toString());
                        }
                        
                        System.out.println("Please enter the id of the book you wish to return");
                        int bookid = input.nextInt();
                        
                       String ChosenBook = bDao.getABookById(bookid).getBookName();
                        
                        boolean returning = bDao.ReturnABook(ChosenBook);
                        input.nextLine();
                        
                            if(returning == true)
                            {    
                                System.out.println("Book has been Returned");
                                lDao.RemovefromTable(bDao.getABookByName(ChosenBook), userid);
                            }
                        }
                        else if(!(loanedBooks.isEmpty()) || loanedBooks.isEmpty())
                        {
                            System.out.println("Sorry " + uDao.getUSerbyId(userid).getUsername() + " you have no books");
                        }
                        break;
                        
                        //logging out
                        case 5:
                            System.out.println("Are you sure you want to logout eg(yes and no)");
                            String ans = input.nextLine().toLowerCase();
                            //Logs the user out and ends the while loop
                            if(ans.equals("yes"))
                            {
                                flag = false;
                            }
                            break;
                            
                            //Add in a book
                        case 6:
                            BookStock bookAdd = new BookStock();
                            System.out.println("Enter in the details of the book");
                            System.out.println("Book name");
                            String bNameAdd = input.nextLine();
                            bookAdd.setBookName(bNameAdd);
                            
                            System.out.println("Book Author");
                            String bAuthor = input.nextLine();
                            bookAdd.setAuthor(bAuthor);
                            
                            System.out.println("Publisher");                          
                            String bPublisher = input.nextLine();
                            bookAdd.setPublisher(bPublisher);
                            
                            bookAdd.setCopies(10);
                            
                            boolean adding = bDao.AddingABook(bookAdd);
                            
                            if(adding == true)
                            {
                                System.out.println("Sucessful added the book " + bookAdd.getBookName() + " to the database");
                                
                            }
                            else if(adding == false)
                            {
                                System.out.println("The adding of the book " + bookAdd.getBookName() + " was unsuccessful");
                            }
                            break;
                            
                            //Edit a book
                        case 7:
                            System.out.println("Enter in a book ID");
                            int bookid = input.nextInt();
                            
                            input.nextLine();
                            System.out.println("Enter in the new books name");
                            String bookName = input.nextLine();
                            
                            bDao.EditingABook(bookName, bookid);
                            
                            System.out.println("Book name is now " + bookName);
                            break;
                            
                        //Edit the amount of copies for a book     
                        case 8:
                            System.out.println("Do you want to increase or decrease the amount of copies?");
                            String incOrDec = input.nextLine().toLowerCase();
                            
                            if(incOrDec.equals("increase"))
                            {
                                System.out.println("Enter in the books ID");
                                int bookID = input.nextInt();
                                
                                System.out.println("Enter in the amount of extra copies");
                                int copiesInc = input.nextInt();
                                boolean increase = bDao.IncreasingCopiesBook(bookID, copiesInc);
                                
                                input.nextLine();
                                
                                if(increase == true)
                                {
                                System.out.println("Sucessful added the copies to the library");
                                
                                }
                                else if(increase == false)
                                {
                                System.out.println("The adding of the copies to the library was unsuccessful");
                                }
                            break;
                            }
                            else if(incOrDec.equals("decrease"))
                            {
                                System.out.println("Enter in the books ID");
                                int bookID = input.nextInt();
                                
                                System.out.println("Enter in the amount of copies taken out");
                                int copiesDex = input.nextInt();
                                boolean decrease = bDao.DescresingCopiesBook(bookID,copiesDex);
                                
                                input.nextLine();
                                
                                 if(decrease == true)
                                {
                                System.out.println("Sucessful removed the copies to the library");
                                
                                }
                                else if(decrease == false)
                                {
                                System.out.println("The removing of the copies to the library was unsuccessful");
                                }
                            }
                            
                            else
                            {
                                System.out.println("Error");
                            }
                            break;
                            //Remove a book
                        
                        case 9:
                            System.out.println("123");
                            System.out.println("Enter in a books ID");
                            int removeBook = input.nextInt();
                            
                            bDao.RemoveABook(removeBook);
                            System.out.println("Book has been removed");
                            input.nextLine();
                            break;
                        
                        //Delete a user final
                        case 10:
                            System.out.println("Enter in a users ID \n IF THE USER IS AN ADMIN THEY WILL NOT BE DELETED");
                            int removeUserID = input.nextInt();
                            if(uDao.getAdminByID(removeUserID).getAdmin() == 0)
                            {
                                uDao.RemoveUser(removeUserID);
                                System.out.println("User successfully removed");
                                input.nextLine();
                            }
                            else if(uDao.getAdminByID(removeUserID).getAdmin() == 1)
                            {
                                System.out.println("User cannot be deleted");
                                input.nextLine();
                            }
                       
                            
                    }
                }
                    else if(!(uDao.LogingInUser(name, password).equals(userlog)))
                {
                    System.out.println("The details you have entered is incorrect try again");
                }
            
                
                //If the user doesn't type in the right information
                else if(!(uDao.LogingInUser(name, password).equals(userlog)))
                {
                    System.out.println("The details you have entered is incorrect try again");
                }
                
                //Checks to see if the user is an admin.
                else if(uDao.LogingInUser(name, password).equals(userlog))
                {
                    int userid = uDao.getUserbyName(name).getUser_id();
                    //we are now logged in
                    System.out.println("You have succesfully logged in");
                    
                    System.out.println("What Do you want to do display all titles, whats on loan, borrow a copy, return, logout");
                    String displayAns = input.nextLine().toLowerCase();
                    
                    switch (displayAns) {
                        case "display":
                            switchvar = 1;
                            break;
                        case "on loan":
                            switchvar = 2;
                            break;
                        case "loan":
                            switchvar = 3;
                            break;
                        case "return":
                            switchvar = 4;
                            break;
                        case "logout":
                            switchvar = 5;
                            break;
                        default:
                            switchvar = 6;
                            break;
                    }
                    
                    switch (switchvar)
                    {
                        case 1:
                        for(int x = 0; x < books.size(); x++ )
                        {
                           BookStock bookList = bDao.getABookById(x);
                           System.out.println(bookList.toString());
                            
                        }
                        break;
                        
                        case 2:
                        for(int i = 0; i < books.size(); i++)
                        {
                            BookStock bookloan = bDao.getAllBooks().get(i);
                            System.out.println(lDao.getAllBooksOnLoan(bookloan).toString());
                        }
                        break;
                        
                        case 3:
                        System.out.println("Which book do you wish to loan eg(bookid please)");
                        int id = input.nextInt();
                        for(int i = 0; i < 999; i++)
                        {
                             bDao.takeOutABook(id);
                             BookStock bookEntry = bDao.getABookById(id);
                             lDao.BorrowABook(bookEntry, userid);
                        }
                        break;
                        
                        case 4://figure this out
                            /*
                        System.out.println("The following is the books you have loaned");
                        
                        lDao.getAllBooksOnLoan(book);
                        */
                        break;
                        
                        case 5:
                            System.out.println("Are you sure you want to logout eg(yes and no)");
                            String ans = input.nextLine().toLowerCase();
                            
                            if(ans.equals("yes"))
                            {
                                flag = false;
                            }
                    }
                }
                    
                }
            }
        }
           
                      
         }
        
        //END OF THE WHILE LOOP
        
        
    


