package lottest2;

import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LotReader implements Serializable {
    
    public static List<Tick2> readLot(String fileName) throws IOException,
            FileNotFoundException, ClassNotFoundException, EOFException {
        
        List<Tick2> oldTickets = new ArrayList<>();
        Object obj;
        
        FileInputStream fis = new FileInputStream(fileName);
        ObjectInputStream ois = new ObjectInputStream(fis);
        
        try {
            obj = ois.readObject();
            /*
                if(obj != null) {
                    System.out.println("obj not null at first read => true");
                }
                else{
                    System.out.println("obj is null at first read");
                }
            */
            while(obj != null){
                Tick2 ticket = (Tick2) obj;
                oldTickets.add(ticket);
                obj = ois.readObject();
            }
            
        }
        catch (EOFException e){
            // -- diag --System.out.println("Expected exception caught in read try catch");
            //Do Nothing, this is expected when end of file.
        }
            /* -- diagnostic -- output Old Thickets file to screen
            if (oldTickets.isEmpty()){
                System.out.println("No old Tickets Yet.");
            }

            for(int i =0; i < oldTickets.size () ; i++){
                    System.out.println("Ticket no. " + oldTickets.get(i).getTicketNo()+
                            "\tamount " + oldTickets.get(i).getFeeAmt());
            }*/
        ois.close();
        return oldTickets;
    }
    
}
