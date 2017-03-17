package VisitorRegistry;

import Transaction.Borrow;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Tyler on 3/13/2017.
 */
 
public class Visitor {
    private String firstName;
    private String lastName;
    private String address;
    private String phoneNumber;
    private int id;
    List<Borrow> borrowing = new ArrayList<Borrow>();
}
