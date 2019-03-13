package lottest2;

import java.io.IOException;
import java.time.Duration;
import java.time.LocalTime;
import java.util.List;
import java.util.Scanner;

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
    String ans = "";
    Scanner kb = new Scanner(System.in);
    
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
        System.out.println("\n" + billId + ": Unknown");
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
    
    public void setSummary(double totalRev, int ticketCount,
            double totalLost, int countLost, double grandTotal){
        System.out.println(header);
        System.out.println("\nActivity to Date");
        System.out.printf("\n\n$%.2f was collected from %d tickets.", totalRev, ticketCount);
        System.out.printf("\n$%.2f was collected from %d lost tickets.", totalLost, countLost);
        System.out.printf("\n\n$%.2f was collected overall.\n\n", grandTotal );
    }
    
    public void setInitialize(String fileName, List lot) throws IOException{
        System.out.println("Do You Want to start a new parking lot Log file?");
        if(kb.nextLine().equalsIgnoreCase("Y")){
            LotWriter.writeTickets(fileName, lot);
        };
    }
}
