 /**
 * Lab7ExampleTester - this file will implement the Customer, DatabaseConnect, CustomerDA classes and
                illustrate exception handling.  If one follows through the code
                they will find everything they will need to create/modify for
                the latest WEDJ4203 assignment
 *              methods  
 * @author Darren Puffer
 * @version .0 (07 March 2015)
 * @since 1.0
 */

import java.util.Vector;
import java.sql.Connection;

public class Lab1ExampleTester
{
	static Customer aCustomer; //a Customer object, when we are dealing with only one
                                    //at a time, it has been created outside of the
                                    //main() method, to take advantage of the
                                    //printDetails() method at the bottom
	public static void main(String args[])
  	{
      Vector<Customer> customers;  //a Vector to hold all customers
      Connection c = null;
	  try{  
            // initialize the databases (i.e. connect)
            c = DatabaseConnect.initialize();
            Customer.initialize(c);
            
            try // get a customer
            {
                    aCustomer = Customer.retrieve("9876543210");
                    printDetails();
            }
            catch(NotFoundException e)
                    {	System.out.println(e);}

            try // try to get a non-existent customer
            {
                    aCustomer = Customer.retrieve("0000000000");
                    printDetails();
            }
            catch(NotFoundException e)
                    {	System.out.println("Did not find " + Customer.formatPhoneNumber("0000000000")+"\n");}

            // get all customers
            customers = Customer.retrieveAll();
            for(int i = 0; i < customers.size(); i++)
            {
                // get customer reference
                aCustomer = customers.get(i);
                printDetails();
            }

            // try to add a new customer, invalid phone number 
            try{
                aCustomer = new Customer("Ed", "Toronto", "4166431234");
            }catch(InvalidPhoneNumberException e){
                System.out.println("Problem with the phone number");
            }
            
            try
            {
                aCustomer.create();
                System.out.println("Ed added\n");
            }
            catch(DuplicateException e)
            {	System.out.println(e);}

            try // now, find the new customer
            {
                    aCustomer = Customer.retrieve("4166431234");
                    printDetails();
            }
            catch(NotFoundException e)
                    {	System.out.println("Did not find " + Customer.formatPhoneNumber("4166431234") + "\n");}
/*
            try // now, delete the new customer
            {
                    aCustomer.delete();
                    System.out.println("Ed deleted\n");
            }
            catch(NotFoundException e)
                    {	System.out.println(e);}
*/
            try // now, try to find the deleted customer
            {
                    aCustomer = Customer.retrieve("4166431234");
                    printDetails();
            }
            catch(NotFoundException e)
            {	System.out.println("Did not find " + Customer.formatPhoneNumber("4166431234") + "\n");}

            // change Eleanor's address to Miami

            try
            {
                    aCustomer = Customer.retrieve("2345678901");
                    printDetails();
                    //change customer address
                    aCustomer.setAddress("Halifax");
                    aCustomer.update();
                    System.out.println("Sue updated\n");
            }
            catch (NotFoundException e)
                    {System.out.println(e);}

            try // now, try to find the Sue
            {
                aCustomer = Customer.retrieve("2345678901");
                printDetails();
            }
            catch(NotFoundException e)
            {
                System.out.println("Sue not found");
            }
	  }catch(Exception e){
		  System.out.println(e.toString());
	  }finally{ // close the database resources, if possible            
          try{  Customer.terminate(); }catch(Exception e){}
          try{  DatabaseConnect.terminate(); }catch(Exception e){}
	  }
	}
	//A method created just to limit the number of System.out.println() calls in the main() method above
	private static void printDetails() {
		System.out.println(aCustomer.toString()+ "\n");
	}
}
