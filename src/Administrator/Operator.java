package Administrator;
import OldCustomer.OldCustomerDriver;

public class Operator extends AdministratorDriver {

    private int tarrif;
            
    
    protected void runOperatorDashboard() {

        do {

            viewOperatorDashboard();

            do {

                System.out.print("\n[+] Choose a number(0 to return to main menu): ");
                
                choice = input.next().charAt(0);
                input.nextLine();
                
                switch (choice) {
                    case '1':
                        collectPaymentsFromCustomer();
                        break;
                    case '2':
                        printBill();
                        break;
                    case '3':
                        viewBillsOfSpecificRegion();
                        break;
                    case '4':

                        break;
                    case '0':
                        return;
                    default:
                        System.out.println("\n[-] Enter a valid choice.");
                        break;
                }

            } while (choice != '1' && choice != '2' && choice != '3' && choice != '4');

            System.out.print("\n[+] Do you want to perform any additional operation in this dashboard? (y/n): ");
            qContinue = input.next().charAt(0);

        } while (qContinue == 'Y' || qContinue == 'y');

    }

    
    private static void viewOperatorDashboard() {

        clearScreen();
        printOperatorBanner();
        printOperatorSelections();
        
    }

    
    private static void printOperatorBanner() {

        System.out.println("\n\t\t  ——————————————————————————————");
        System.out.print("\t\t |  Electricity Billing System  |\n");
        System.out.println("\t\t  ——————————————————————————————\n");
        System.out.println("\t      [-] Welcome in Operator Dashboard [-]");
        System.out.println("\t      —————————————————————————————————————");

    }

    
    private static void printOperatorSelections() {

        System.out.println("\n[-] What do you want to do?\n");
        System.out.println("\t     [1] - Collect payments from customer.\n");
        System.out.println("\t     [2] - Print bill with meter code.\n");
        System.out.println("\t     [3] - View bills of specific region.\n");
        System.out.println("\t     [4] - Stop meter and cancel subscription for customer.");
        
    }
    
    private void printBill(){
        
        System.out.print("\n[+] Enter a meter code: ");
        String[] billInfo = getLastBillInfo(Customer_Val(input.nextLine()));
        
        System.out.println("\n  ===============================================================================================================================================");
            System.out.printf("\t  %-15s | %-15s | %-15s | %-15s | %-10s | %-15s | %-15s | %-20s", "GovernmentCode", "PastReading", "CurrentReading",
                              "Consumption", "Tariff", "MoneyValue", "Status", "DateOfBill");
            System.out.println();
            System.out.println("  ===============================================================================================================================================");
            
            
            System.out.format("\t  %-15s | %-15s | %-15s | %-15s | %-10s | %-15s | %-15s | %-20s",
                                billInfo[0], billInfo[1], billInfo[2], billInfo[3],
                                billInfo[4], billInfo[5], billInfo[6], billInfo[7]);
  
            System.out.println("\n  ===============================================================================================================================================");
        
    }
    
    
    private void collectPaymentsFromCustomer(){
        
        System.out.print("\n[+] Enter a meter code: ");
        String meterCode = Customer_Val(input.nextLine());
        
        OldCustomerDriver ocd = new OldCustomerDriver();
        ocd.payBill(meterCode);
        
        collectPayments(meterCode);
        
    }
    

    public double getMoneyValue(int consumption, String typeOfUse){

        double moneyValue = 0;

        if (typeOfUse.equals("Home")){
            
            if (consumption >= 0 && consumption <= 50) {
                
                moneyValue = 0.38 * consumption;
                tarrif = 1;
                
            } else if (consumption <= 100) {
                
                moneyValue = 19 + 0.48 * (consumption - 50);
                tarrif = 2;
                
            } else if (consumption <= 200) {
                
                moneyValue = 0.65 * consumption;
                tarrif = 3;
                
            } else if (consumption <= 350) {
                
                moneyValue = 130 + 0.96 * (consumption - 200);
                tarrif = 4;
                
            } else if (consumption <= 650) {
                
                moneyValue = 274 + 1.18 * (consumption - 350);
                tarrif = 5;
                
            } else if (consumption <= 1000) {
                
                moneyValue = 1.18 * consumption;
                tarrif = 6;
                
            } else {
                
                moneyValue = 1.45 * consumption;
                tarrif = 7;
                
            }
        }
        
        else {
            
            if (consumption <= 100) {
                
                moneyValue = consumption * 0.65;
                tarrif = 1;
                
            } else if (consumption <= 200) {
                
                moneyValue = consumption * 1.20;
                tarrif = 2;
                
            } else if (consumption <= 600) {
                
                moneyValue = consumption * 1.40;
                tarrif = 3;
                
            } else if (consumption <= 1000) {
                
                moneyValue = 840 + (consumption - 600) * 1.55;
                tarrif = 4;
                
            } else {
                
                moneyValue = consumption * 1.60;
                tarrif = 5;
                
            }
            
        }

        return moneyValue;
    }

    public int getTarrif(){
        return tarrif;
    }

    
}