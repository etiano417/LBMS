package UserRegistry;

/**
 * Tests the User Registry
 */
public class UserRegistryTest {

    public static void main(String[] args){
        UserRegistry ur = new UserRegistry();
        /**
         * System.out.println(ur.addUser("John","Password","0000",true));
         User u = ur.getUser("John","Password");
         System.out.println(u);
         System.out.println(u.isPassword("jo"));
         System.out.println(u.isPassword("Password"));
         System.out.println(ur.isEmployee("11"));
         System.out.println(ur.isEmployee("0000"));
         */

        //Test adding a user
        assert ur.addUser("John","Password","0000",true).equals("success");
        assert ur.addUser("John","Password","0001",true).equals("duplicate-username");
        assert ur.addUser("Jones","Password","0000",true).equals("duplicate-id");

        //Test getting a user
        assert ur.getUser("John","Password").getId().equals("0000");
        assert ur.getUser("Jones","Password") == null;

        //Testing to see if connecting works
        assert ur.connect() instanceof String;

        //Testing to see if user permissions checking works
        assert ur.isUser("0000");
        assert ! ur.isUser("0006");

        //Testing to see if employee permissions checking works
        assert ur.isEmployee("0000");
        assert ! ur.isEmployee("0006");
        ur.addUser("Joe","Pass","0003",false);
        assert ! ur.isEmployee("0003");
    }
}
