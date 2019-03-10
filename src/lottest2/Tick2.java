package lottest2;
import java.time.LocalTime;

public class Tick2 {
    private int ticketNo = 0;
    private double feeAmt = 0;
    private LocalTime timeIn;
    private LocalTime timeOut;
    
    public static int number = 0;
    public static double fee = 0;
    
    public Tick2(){
        number++;
        this.ticketNo = number;
        
    }
    public Tick2(int number, LocalTime timeIn){
        this.ticketNo = number;
        this.timeIn = timeIn;
    }
    
    public Tick2(int number, Double amt, LocalTime timeIn, LocalTime timeOut){
        this.ticketNo = number;
        this.feeAmt = amt;
        this.timeIn = timeIn;
        this.timeOut = timeOut;
    }

    public int getTicketNo() {
        return ticketNo;
    }

    public double getFeeAmt() {
        return feeAmt;
    }
    
    public LocalTime getTimeIn(){
        return timeIn;
    }
    
    public LocalTime getTimeOut(){
        return timeOut;
    }

    public void setTicketNo(int ticketNo) {
        this.ticketNo = ticketNo;
    }

    public void setFeeAmt(double feeAmt) {
        this.feeAmt = feeAmt;
    }
    
    public void setTimeIn(LocalTime timeIn){
        this.timeIn = timeIn;
    }
    
    public void setTimeOut(LocalTime timeOut){
        this.timeOut = timeOut;
    }
    
    
}
