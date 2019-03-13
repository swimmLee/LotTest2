package lottest2;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class LotWriter{
    public static void writeTickets(String fileName, List<Tick2> lot) throws FileNotFoundException, IOException{
        FileOutputStream fos = new FileOutputStream(fileName);
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        try {
            for(int i=0; i<lot.size();i++){
                oos.writeObject(lot.get(i));
                System.out.println("Writing file:\tTicketNo " + 
                    lot.get(i).getTicketNo() + 
                    "\tFee  " + lot.get(i).getFeeAmt() +
                    "\tIn " + lot.get(i).getTimeIn() +
                    "\tOut " + lot.get(i).getTimeOut());
            }
        }
        catch(IOException e){
            System.out.println("Caught error in LotWriter.");
            // Do Nothing
        }
        oos.close();
    }
}
