/* 	Sara Kazemi
 * 	CST 338 Software Design
 * 	Assigment M1 - String Manipulation
 */
import java.util.Scanner;
import java.text.DecimalFormat;

public class M1{
    // static final variables for part 2
    public static final int MIN_HOURS = 12;
    public static final int MAX_HOURS = 24;

    public static void main(String[] args)
    {
        Scanner input = new Scanner(System.in);
        System.out.println("\n##### PART 1 #####\n##################\n\n");
        System.out.print("Enter your first name (first letter capitalized): ");
        String firstName = input.next();
        System.out.println();
        System.out.print("Enter your last name (first letter capitalized): ");
        String lastName = input.next();
        firstName = properCap(firstName);
        lastName = properCap(lastName);
        String fullName = firstName + " " + lastName;
        System.out.println();
        System.out.println("Full Name: " + fullName);
        System.out.println("Length of full name: " + (firstName.length() + lastName.length()));
        System.out.println("Full Name CAPITALIZED: " + fullName.toUpperCase());
        System.out.println("Full Name lowercased: " + fullName.toLowerCase());


        System.out.println("\n\n##### PART 2 #####\n##################\n\n");
        System.out.println("Range of hours: " + MIN_HOURS + "-" + MAX_HOURS);
        System.out.println("How many hours have you spent studying this week? Answer to three decimals: ");
        double answer = input.nextDouble();
        DecimalFormat df = new DecimalFormat("00.0");
        System.out.println("You have studied " + df.format(answer) + " hours this week.");

        input.close();



    }

    // Just in case our user doesn't follow proper capitalization, despite our prompt,
    // let's convert the first letter of firstName and lastName to uppercase
    public static String properCap(String proper)
    {
        proper = proper.substring(0,1).toUpperCase() + proper.substring(1);
        return proper;
    }


}

/* TEST RUNS

RUN #1
##### PART 1 #####
##################


Enter your first name (first letter capitalized): sara

Enter your last name (first letter capitalized): kazemi

Full Name: Sara Kazemi
Length of full name: 10
Full Name CAPITALIZED: SARA KAZEMI
Full Name lowercased: sara kazemi


##### PART 2 #####
##################


Range of hours: 12-24
How many hours have you spent studying this week? Answer to three decimals:
12.543
You have studied 12.5 hours this week.

Run #2
##### PART 1 #####
##################


Enter your first name (first letter capitalized): Sara

Enter your last name (first letter capitalized): Kazemi

Full Name: Sara Kazemi
Length of full name: 10
Full Name CAPITALIZED: SARA KAZEMI
Full Name lowercased: sara kazemi


##### PART 2 #####
##################


Range of hours: 12-24
How many hours have you spent studying this week? Answer to three decimals:
22.210
You have studied 22.2 hours this week.

*/
