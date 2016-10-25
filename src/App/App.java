
package App;

import java.util.*;
import Daos.*;
import Dtos.*;

/**
 *
 * @author Ben,Chris,Aleks
 */
public class App {
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
                
                if(uDao.LogingInUser(name, password).equals(userlog))
                {
                    int userid = uDao.getUserbyName(name).getUser_id();
                    //we are now logged in
                    System.out.println("You have succesfully logged in");
                    
                    System.out.println("What Do you want to do display all titles, whats on loan, borrow a copy, return, logout eg(display, on loan, borrow, return, logout)");
                    String displayAns = input.nextLine().toLowerCase();
                    
                    switch (displayAns) {
                        case "display":
                            switchvar = 1;
                            break;
                        case "on loan":
                            switchvar = 2;
                            break;
                        case "borrow":
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
                        
                             bDao.takeOutABook(id);
                             BookStock bookEntry = bDao.getABookById(id);
                             lDao.BorrowABook(bookEntry, userid);
                             
                             input.nextLine();
                        
                        break;
                        
                        case 4://figure this out
                          /*
                        System.out.println("The following is the books you have loaned");
                        
                        lDao.getAllBooksOnLoan();
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
                else if(!(uDao.LogingInUser(name, password).equals(userlog)))
                {
                    System.out.println("The details you have entered is incorrect try again");
                }
            }
            
        }
                 
         }
    }
    

