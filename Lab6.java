/**-------------------------------------------------------------
//AUTHOR: Gregory Feng
//FILENAME: Lab6.java
//SPECIFICATION: Code that takes the inputted retail price of your laptop, phone, and backpack and returns the price of what it is in Arizona,
 * what it is in another state, and how much you could save if you bought it in that other state.
//FOR: CSE 110 - Lab #6
//TIME SPENT: 20 min.
//-----------------------------------------------------------*/
import java.util.Scanner;

public class Lab6
{
    public static void main(String[] args)
    {
        /* Some variables */
        Scanner scanner = new Scanner(System.in);

        /* Get the retail price of something */
        double rpLaptop = -1;
        double rpPhone = -1;
        double rpBackpack = -1;
        
        //Uses a function to read the retail price of different items
        rpLaptop=readRetailPrice("Laptop",scanner);
        rpPhone=readRetailPrice("Phone",scanner);
        rpBackpack=readRetailPrice("Backpack",scanner);
        
        /* Compute after-tax prices in one state with those in AZ */
        System.out.println("Enter one state you'd like to compare after-tax prices: ");
        String targetState = scanner.next();
        double targetStateTax = getSalesTaxRate(targetState);

        /* Compute the after-tax prices */
        double afterTaxAZ = addTax(rpLaptop) + addTax(rpPhone) + addTax(rpBackpack);
        double afterTaxTarget = addTax(rpLaptop, targetStateTax) + addTax(rpPhone, targetStateTax)
                + addTax(rpBackpack, targetStateTax);
        double diff = afterTaxAZ - afterTaxTarget;

        /* Show the total after-tax prices of your stuff in AZ and the target state */
        System.out.println();
        System.out.printf("The total after-tax price in AZ: %.2f\n", afterTaxAZ);
        System.out.printf("The total after-tax price in %s: %.2f\n", targetState, afterTaxTarget);

        /* Show whether you have a chance to save */
        if (diff > 0)
        {
            System.out.printf("You may save %.2f for those stuff in %s.\n", diff, targetState);
        }

        scanner.close();
    }

    /**
     * Prompt and ask the user for the price of something. The price must be
     * non-negative.
     * 
     * @param name the name of something
     * @param scan a Scanner object
     * @return the price user inputs
     */
    private static double readRetailPrice(String name, Scanner scan)
    {
    	//Gets the input validation started
        double temp=-1;
    	System.out.printf("What is your %s's retail price? ",name);
    	//Keeps asking if the entered number is less than 0
        while(temp<0) {
        	temp=scan.nextDouble();
        	if(temp<0) {
        		System.out.print("[ERR] a price must be non-negative. Please type it again.\n");
        	}
        }
        //If its not less than 0 return the value
        return temp;
    }

    /**
     * Add the sales tax of AZ to a price and return the result.
     * 
     * @param price
     * @return the after-tax price
     */
    //Returns the price after tax for AZ specifically
    private static double addTax(double price)
    {   
    	//Since sales tax rate is in a %, the sales tax is divided by 100 and then multiplied to the price and then the price is added
        return price=price*getSalesTaxRate("AZ")/100+price;
    }

    /**
     * Add a sales tax to a price and return the result.
     * 
     * @param price
     * @param rate  sales tax in percent
     * @return the after-tax price
     */
    //Returns the price after tax for any state besides AZ
    private static double addTax(double price, double rate)
    {
    	//Since sales tax rate is in a %, the sales tax is divided by 100 and then multiplied to the price and then the price is added
    	return price=price*(rate/100)+price;
    }

    /**
     * Get the sales tax for a given state.
     * 
     * @param state an abbreviation of a state in the US
     * @return the sales tax in percent
     */
    //Returns the sales tax rate from this database
    private static double getSalesTaxRate(String state)
    {
    	//Sales tax rate database
        double SALES_TAX_AZ = 8.40;
        double SALES_TAX_CA = 8.66;
        double SALES_TAX_NV = 8.32;
        double SALES_TAX_UT = 7.18;
        double SALES_TAX_CO = 7.65;
        double SALES_TAX_NM = 7.82;
        double SALES_TAX_OR = 0.00;
        //Variable rt holds the rate
        double rt = 0;
        //Switch statement to return the rates depending on the state abbreviation
        switch (state)
        {
        case "AZ":
            rt = SALES_TAX_AZ;
            break;
        case "CA":
            rt = SALES_TAX_CA;
            break;
        case "NV":
            rt = SALES_TAX_NV;
            break;
        case "UT":
            rt = SALES_TAX_UT;
            break;
        case "CO":
            rt = SALES_TAX_CO;
            break;
        case "NM":
            rt = SALES_TAX_NM;
            break;
        case "OR":
            rt = SALES_TAX_OR;
            break;
        //In the case that the state is not part of the database, send a warning message and print as if the sales tax is 0.
        default:
            System.out.println("[WARN] The current database does not have sales tax info for " + state + ". Set to 0");
        }

        return rt;
    }
}
