package lottest2;

import java.time.Duration;
import java.time.LocalTime;

public class Screen2 {
    private String header = "\nBest Value Parking Garage"
            + "\n\n=========================";
    //private String line =   "=========================";
    private String inOption1 = "1 - Check/In";
    private String outOption1 = "1 - Check/Out";
    private String outOption2 = "2 - Lost Ticket";
    private String inOption3 = "3 - Close Garage";
    private String arrow = "=>";
    private String billId = "Receipt for a vehicle id ";
    private String billSum = " hours parked ";
    private String billLost = "Lost Ticket";
    
    public Screen2(){
    }
    
    public void getInScreen(){
        System.out.println(header);
        //System.out.println("\n" + line);
        System.out.println("\n" + inOption1);
        System.out.println("\n" + inOption3);
        System.out.println("\n" + arrow);
    }
    
    public void getOutScreen(){
        System.out.println(header);
        System.out.println("\n" + outOption1);
        System.out.println("\n" + outOption2);
        System.out.println("\n" + arrow);
    }
    
    public void setLostTicketReceipt(double fee){
        System.out.println(header);
        System.out.println("\n" + billId + "unknown");
        System.out.println("\n\n" + billLost);
        System.out.printf("\n$%.2f\n", fee );
    }
    
    public void setReceipt(int id, double fee, int hrs, LocalTime in, LocalTime out){
        System.out.println(header);
        System.out.println("\n" + billId + id);
        System.out.printf("\n\n%d " + billSum, hrs);
        System.out.println(in + " - " + out);
        System.out.printf("\n$%.2f\n", fee );
    }
    
}
