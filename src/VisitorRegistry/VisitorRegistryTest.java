package VisitorRegistry;

import BookRegistry.Book;
import Transaction.Borrow;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Used to test the visitor registry subsystem
 */
public class VisitorRegistryTest {
    public static void main(String[] args){

        VisitorRegistry vr = new VisitorRegistry();

        //Test visitor registration
        assert !vr.RegisterVisitor("Eric","Tino","1234","1234").equals("duplicate");
        assert vr.RegisterVisitor("Eric","Tino","1234","1234").equals("duplicate");

        //Test visitor arrival
        String id = vr.RegisterVisitor("John","Doe","4444","5555");
        assert vr.beginVisit(id, LocalDateTime.now()).equals("success");
        assert vr.beginVisit("notanid", LocalDateTime.now()).equals("invalid id");
        assert vr.beginVisit(id, LocalDateTime.now()).equals("duplicate");

        //test getVisiting()
        String id2 = vr.RegisterVisitor("Some","Guy","4444","5555");
        assert vr.getVisiting(id2);
        assert !vr.getVisiting("notanid");

        //test ending visits
        String id3 = vr.RegisterVisitor("Yngwie","Malmsteen","4444","5555");
        assert vr.endVisit(id3,LocalTime.now()).equals("success");
        assert vr.endVisit("notanid",LocalTime.now()).equals("invalid id");

        //Test borrowing books
        List<String> authors = new ArrayList<>();
        authors.add("yes");
        Borrow b = new Borrow(new Book(123,"yes",authors,"yes", LocalDate.now(),100),LocalDate.now());
        String id4 = vr.RegisterVisitor("King","Diamond","4444","5555");
        assert vr.borrowBook(id4,b).equals("success");
        assert vr.borrowBook("notanid",b).equals("invalid id");

        assert vr.borrowBook(id4,b).equals("success");
        assert vr.borrowBook(id4,b).equals("success");
        assert vr.borrowBook(id4,b).equals("success");
        assert vr.borrowBook(id4,b).equals("success");
        assert vr.borrowBook(id4,b).equals("book limit exceeded");

        //how do I test owing money??

        //Test returning book
        assert vr.returnBook(id4,b).equals("success");
        assert vr.returnBook("notanid",b).equals("invalid id");

        //test paying a fine?
    }
}
