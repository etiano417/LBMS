package Request;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Saves visitors, visits, books, and fines then safely shuts the system down
 */
public class SafeShutDown implements Request
{
    public List<Object> executeCommand()
    {
        List<Object> output = new ArrayList<>();

        List<Object> toWrite = new ArrayList<>();
        toWrite.add(LBMS.LBMS.br.getBookList());
        toWrite.add(LBMS.LBMS.vr.getVisitors());
        toWrite.add(LBMS.LBMS.ur.getUserList());
        toWrite.add(LBMS.LBMS.vr.getVisitList());
        toWrite.add(LBMS.LBMS.clock);
        toWrite.add(LBMS.LBMS.library);

        try{
            FileOutputStream fileOut = new FileOutputStream("SafeSave.ser");
            ObjectOutputStream objOut = new ObjectOutputStream(fileOut);

            int objNum = toWrite.size();
            objOut.writeInt(objNum);

            for(int x=0;x<objNum;x++)
            {
                objOut.writeObject(toWrite.get(x));
            }

            objOut.close();
            fileOut.close();
        }catch(IOException ioe){
            throw new Error(ioe);
        }

        return output;
    }

    @Override
    public List<Object> undoCommand()
    {
        return null;
    }
}
