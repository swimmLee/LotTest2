package lottest2;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.Duration;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;


public class LotTest2 implements RatesLT2 {
    private static List<Tick2> lot = new ArrayList<>();
    private static List<Integer> availableTickets = new ArrayList<>();
    private static List<Tick2> storeTickets = new ArrayList<>();
    Random randNum = new Random();
    Screen2 screen = new Screen2();
    Summary summary = new Summary();
    
    public static void main(String[] args)throws 
            FileNotFoundException, IOException, ClassNotFoundException{
        new LotTest2();
    }
    public LotTest2()throws FileNotFoundException,
            IOException, ClassNotFoundException{
        Scanner kb = new Scanner(System.in);
        String ans = "y";
        String fileName = "park.csv";
        double amount = 0;
        int countToday = 0,
            startNumber = 0;
        LocalTime timeIn, timeOut;
        Duration parkDuration;
        
        screen.setInitialize(fileName, lot);
        //LotWriter.writeTickets(fileName, lot);
        
        storeTickets = LotReader.readLot(fileName);
        if(storeTickets.isEmpty())
            startNumber = 0;
        else
            startNumber = storeTickets.get(storeTickets.size()-1).getTicketNo();
        
        ans = "";
                //screen.getInScreen(); -- load first car or close lot.
        while( !(ans.equals("1") || ans.equals("3")) ){
            screen.getInScreen();
            ans = kb.nextLine();
        }
        if(ans.equals("1")){
            countToday++;
            lot.add(new Tick2( (startNumber + countToday) , getClockIn()) );
            availableTickets.add(countToday); //adds to list of cars in lot
            //ticketCount++;  //tickets issued today
        }
        
        ans = "";
        while ( !ans.equalsIgnoreCase("3")){
            Double inOrOut = randNum.nextDouble();
                //use random number to decide which screen to offer for action.
            double divider = .5;
            if(inOrOut < divider){
                //Start checkIn screen, wait for valid reply;
                while( !( ans.equals("1") || ans.equals("3") ) ){
                    screen.getInScreen();
                    ans = kb.nextLine();
                }
                if( ans.equals("1")){   //Make Ticket;
                    countToday ++;    //set new ticket no.
                    lot.add(new Tick2( (startNumber + countToday), getClockIn()));
                    availableTickets.add(countToday); //add to tick in lot
                    ans = "";
                    //ticketCount++; //tickets issued today
                }
                else{
                    //Close Lot and Summarize; diagnostic print statement.
                    //unnecessary else statement falls through to exit.
                    System.out.println("Close The Lot.");
                }
            }
            else{           //Exit lot. Wait for valid response.
                while( !( ans.equals("1") || ans.equals("2") ) ){
                    screen.getOutScreen();
                    ans = kb.nextLine();
                }
                //test if possible to check out. Lot must have car to checkout;
                if( !availableTickets.isEmpty()){   //there is car to checkOut;
                    int atPointer =     //get random pointer to ticket numbers.
                            randNum.nextInt( availableTickets.size() );
                    int indexNumber = availableTickets.get(atPointer)-1;
                    int ticketNumber = indexNumber + 1 + startNumber;
                    if( ans.equals("1")){     //normal checkout
                        timeOut = getClockOut();
                        lot.get(indexNumber).setTimeOut(timeOut);   //timeOut
                        timeIn = lot.get(indexNumber).getTimeIn();
                        parkDuration = Duration.between(timeIn, timeOut);
                        int hoursParked = (int)parkDuration.toHours();
                        amount = getCharges(parkDuration);
                        lot.get(indexNumber).setFeeAmt(amount);    //fee
                        screen.setReceipt(ticketNumber, amount, hoursParked,
                                timeIn, timeOut);
                        availableTickets.remove(atPointer);
                    }
                    else if(ans.equals("2")){  //lost ticket. update statistics
                        timeOut = null;
                        lot.get(indexNumber).setTimeOut(timeOut);
                        amount = LOST_TICKET_FEE;
                        lot.get(indexNumber).setFeeAmt(amount);
                        screen.setLostTicketReceipt(amount);
                        availableTickets.remove(atPointer);
                    }
                }
                ans = "";
            }
        }
        getTodayListOut();
        getSummary(countToday);
            // add todays tickets to storeTickets ArrayList - All tickets Log
        for(int i=0; i<lot.size(); i++){
            storeTickets.add(lot.get(i));
        }
            ///write to ticket log file
        LotWriter.writeTickets(fileName, storeTickets);
    }
    
    
    public void getSummary(int countToday){
        int countLost = 0;
        double totalLost = 0;
        double totalRev = 0;
        double grandTotal = 0;
        for(int i=0; i<lot.size(); i++){
            totalRev += lot.get(i).getFeeAmt();
            if(lot.get(i).getFeeAmt() == LOST_TICKET_FEE){
                totalLost += LOST_TICKET_FEE;
                countLost++;
            }
            System.out.println(lot.get(i).getFeeAmt());
        }
        grandTotal = totalRev;
        for(int i=0; i<storeTickets.size(); i++){
            grandTotal += storeTickets.get(i).getFeeAmt();
        }
        screen.setSummary(totalRev, countToday, totalLost, countLost, grandTotal);
    }
    
    //  ----  Diagnostic  ---
    private void getTodayListOut(){
        System.out.println("Today's List of transactions: ");
        for (Tick2 ticket : lot){
            System.out.println("obj no. " + ticket.getTicketNo() +
                "\t amount " + ticket.getFeeAmt() +
                "\t timeIn " + ticket.getTimeIn() +
                "\t timeOut " + ticket.getTimeOut() );
        }
    }

    private LocalTime getClockIn(){
        int hour = randNum.nextInt(5) + 7;
        int min = randNum.nextInt(60);
        LocalTime hm = LocalTime.of(hour, min);
        return hm;
    }
    
    private LocalTime getClockOut(){
        int hour = randNum.nextInt(11) + 13;
        int min = randNum.nextInt(60);
        LocalTime hm = LocalTime.of(hour, min);
        return hm;
    }
    
    @Override
    public double getCharges(Duration eT){
        double charge = 0;
        int timeParked = (int)eT.toHours();
        if(timeParked<=3){
            charge = MIN_PARKING_FEE;
        } else if (timeParked > 3 && timeParked <= 13){
            charge = MIN_PARKING_FEE +(timeParked-3)*HOURLY_PARKING_FEE;
        } else if (timeParked>13 && timeParked<=24){
            charge = MAX_PARKING_FEE;
        }
        return charge;
    }
}