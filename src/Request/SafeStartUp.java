package Request;

import BookRegistry.Book;
import Clock.Clock;
import Library.Library;
import VisitorRegistry.Visit;
import VisitorRegistry.Visitor;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Deserializes the file written by SafeShutDown and replaces all objects into the application.....supposedly
 */
public class SafeStartUp implements Request
{
    public List<Object> executeCommand()
    {
        List<Object> output = new ArrayList<>();

        try{
            FileInputStream fileIn = new FileInputStream("SafeSave.ser");
            ObjectInputStream objIn = new ObjectInputStream(fileIn);

            Object o;
            int readCount = objIn.readInt();
            for(int x=0;x<readCount;x++)
            {
                o = objIn.readObject();
                /*System.out.println("Reading " + (x+1) + "...");
                System.out.println(o.toString());*/
                if(o instanceof Book)
                {
                    LBMS.LBMS.br.addBookFromStartUp((Book)o);
                }
                else if(o instanceof Visit)
                {
                    LBMS.LBMS.vr.addVisitFromStartUp((Visit)o);
                }
                else if(o instanceof Visitor)
                {
                    LBMS.LBMS.vr.addVisitorFromStartUp((Visitor)o);
                }
                else if(o instanceof Clock)
                {
                    LBMS.LBMS.clock = (Clock)o;
                }
                else if(o instanceof Library)
                {
                    LBMS.LBMS.library = (Library)o;
                }
            }

            fileIn.close();
            objIn.close();
        }catch(IOException ioe){
            throw new Error(ioe);
        }catch(ClassNotFoundException cnfe){
            throw new Error(cnfe);
        }

        return output;
    }

    @Override
    public List<Object> undoCommand()
    {
        return null;
    }
}
