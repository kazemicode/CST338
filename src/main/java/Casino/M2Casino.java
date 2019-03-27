package Casino;/* Sara Kazemi
 * CST 338
 * M2: Casino
 */
import java.util.Scanner;

import java.lang.Math;

/* Main client class */
public class M2Casino {


    public static Scanner input = new Scanner(System.in);

    // drives the program
    public static void main(String[] args) {
        int bet = -99;
        while(bet != 0)
        {
            bet = getBet();
            TripleString thePull = pull();
            display(thePull, getPayMultiplier(thePull, bet));
        }

        input.close();

    }

    // gets the bet from the user and returns it
    public static int getBet()
    {
        System.out.print("How much would you like to bet (1 - 100) or 0 to quit? ");
        int bet = input.nextInt();

        if(bet < 0 || bet > 100)
        {
            getBet();
        }

        return bet;

    }

    // simulates a random pull of the slot machine
    // generates three random strings and returns them as a TripleString object
    public static TripleString pull()
    {
        TripleString t = new TripleString();
        t.setString1(randString());
        t.setString2(randString());
        t.setString3(randString());
        return t;
    }

    public static void display(TripleString thePull, int winnings)
    {

        // exit case: User entered a bet 0 zero, which means -99 returned as winnings
        if(winnings == -99)
        {
            System.out.println("Play again soon!");
            System.out.println(TripleString.displayWinnings());
        }


        // if the above cases are false, we're still playing!
        else
        {
            System.out.println("whirrrrrr .... and your pull is ... ");
            System.out.println(thePull);

            if(winnings > 0)
            {
                System.out.println("Congratulations! You won $" + winnings +"\n");

            }
            else
            {
                System.out.println("Sorry, you lost.\n");
            }

            TripleString.saveWinnings(winnings);

        }

    }

    // Returns one of four Strings according to set probabilities
    private static String randString()
    {
        final int SIDES = 1000;
        int die = (int) (Math.random() * SIDES) + 1; // generates a number between 1-1000
        if(die > SIDES/2) // 50% chance
        {
            return "BAR"; // BAR
        }
        else if(die > SIDES/4) // 25% chance
        {
            return "cherries"; // cherries
        }
        else if(die > SIDES/8) // 12.5% chance
        {
            return "space"; // space
        }
        else // 12.5% chance
        {
            return "7"; // 7
        }

    }

    // Returns the payout of a pull
    public static int getPayMultiplier(TripleString thePull, int bet)
    {
        if(bet == 0)
        {
            return -99;
        }
        else if(thePull.getString1() == "cherries" && thePull.getString2() != "cherries")
        {
            return bet * 5;
        }
        else if(thePull.getString1() == "cherries" && thePull.getString2() == "cherries" && thePull.getString3() != "cherries")
        {
            return bet * 15;
        }
        else if(thePull.getString1() == "cherries" && thePull.getString2() == "cherries" && thePull.getString3() == "cherries")
        {
            return bet * 30;
        }
        else if(thePull.getString1() == "BAR" && thePull.getString2() == "BAR" & thePull.getString3() == "BAR")
        {
            return bet * 50;
        }
        else if(thePull.getString1() == "7" && thePull.getString2() == "7" && thePull.getString3() == "7")
        {
            return bet * 100;
        }
        else
        {
            return 0;
        }

    }

} // end of Assig2 class

/* TripleString class used to represent a casino game pull */
class TripleString
{
    // private instance variables
    private String string1, string2, string3;

    // static data members
    public static final int MAX_LEN = 20; // defines max length of any of the pivs
    public static final int MAX_PULLS = 40; // maximum plays in game
    public static int[] pullWinnings = new int[MAX_PULLS]; // keeps track of winnings
    public static int numPulls = 0; // keeps track of which pull we're on

    // zero-arg constructor -- sets all instance variables
    // to the empty String
    public TripleString()
    {
        string1 = "";
        string2 = "";
        string3 = "";
    }

    /* Accessor methods */
    // Accessor method for string1
    public String getString1()
    {
        return string1;
    }
    // Accessor method for string2
    public String getString2()
    {
        return string2;
    }
    // Accessor method for string3
    public String getString3()
    {
        return string3;
    }


    /* Mutator methods */
    // Mutator method for string1
    public boolean setString1(String s1)
    {
        if(validString(s1))
        {
            string1 = s1;
            return true;
        }
        else
        {
            return false;
        }
    }
    // Mutator method for string2
    public boolean setString2(String s2)
    {
        if(validString(s2))
        {
            string2 = s2;
            return true;
        }
        else
        {
            return false;
        }
    }
    // Mutator method for string3
    public boolean setString3(String s3)
    {
        if(validString(s3))
        {
            string3 = s3;
            return true;
        }
        else
        {
            return false;
        }
    }

    // Returns true if String is valid (not null and within the length limit)
    // false otherwise
    private boolean validString(String str)
    {
        return (str != null && str.length() <= MAX_LEN);
    }

    // Saves amount won to an array as long as there's room in the array
    // returns true if successful, false otherwise
    public static boolean saveWinnings(int winnings)
    {
        if(numPulls < MAX_PULLS)
        {
            pullWinnings[numPulls] = winnings;
            numPulls++;
            return true; // return true if winnings successfully stored
        }
        return false; // return false if length of array reached
    }

    // Displays the amount won by the player for each pull played
    public static String displayWinnings()
    {
        String result = "";
        for(int win = 0; win < numPulls; win++) // only print out the results for the number of pulls played
        {
            result += pullWinnings[win] + " ";
        }
        return result;
    }

    @Override
    public String toString()
    {
        return string1 + "  " + string2 + "  " + string3;
    }

} // end of TripleString class


/* ---------------------  Sample Run  -------------------------*
How much would you like to bet (1 - 100) or 0 to quit? -1
How much would you like to bet (1 - 100) or 0 to quit? 999
How much would you like to bet (1 - 100) or 0 to quit? 28
whirrrrrr .... and your pull is ...
BAR  BAR  BAR
Congratulations! You won $1400

How much would you like to bet (1 - 100) or 0 to quit? 15
whirrrrrr .... and your pull is ...
BAR  cherries  BAR
Sorry, you lost.

How much would you like to bet (1 - 100) or 0 to quit? 5
whirrrrrr .... and your pull is ...
BAR  7  BAR
Sorry, you lost.

How much would you like to bet (1 - 100) or 0 to quit? 6
whirrrrrr .... and your pull is ...
space  7  space
Sorry, you lost.

How much would you like to bet (1 - 100) or 0 to quit? 87
whirrrrrr .... and your pull is ...
7  BAR  space
Sorry, you lost.

How much would you like to bet (1 - 100) or 0 to quit? 25
whirrrrrr .... and your pull is ...
BAR  cherries  cherries
Sorry, you lost.

How much would you like to bet (1 - 100) or 0 to quit? 34
whirrrrrr .... and your pull is ...
cherries  cherries  BAR
Congratulations! You won $510

How much would you like to bet (1 - 100) or 0 to quit? 44
whirrrrrr .... and your pull is ...
space  7  space
Sorry, you lost.

How much would you like to bet (1 - 100) or 0 to quit? 25
whirrrrrr .... and your pull is ...
BAR  cherries  BAR
Sorry, you lost.

How much would you like to bet (1 - 100) or 0 to quit? 13
whirrrrrr .... and your pull is ...
cherries  BAR  7
Congratulations! You won $65

How much would you like to bet (1 - 100) or 0 to quit? 22
whirrrrrr .... and your pull is ...
BAR  space  BAR
Sorry, you lost.

How much would you like to bet (1 - 100) or 0 to quit? 55
whirrrrrr .... and your pull is ...
BAR  BAR  7
Sorry, you lost.

How much would you like to bet (1 - 100) or 0 to quit? 99
whirrrrrr .... and your pull is ...
7  space  BAR
Sorry, you lost.

How much would you like to bet (1 - 100) or 0 to quit? 89
whirrrrrr .... and your pull is ...
BAR  BAR  BAR
Congratulations! You won $4450

How much would you like to bet (1 - 100) or 0 to quit? 56
whirrrrrr .... and your pull is ...
BAR  7  cherries
Sorry, you lost.

How much would you like to bet (1 - 100) or 0 to quit? 5
whirrrrrr .... and your pull is ...
cherries  BAR  BAR
Congratulations! You won $25

How much would you like to bet (1 - 100) or 0 to quit? 2
whirrrrrr .... and your pull is ...
cherries  BAR  cherries
Congratulations! You won $10

How much would you like to bet (1 - 100) or 0 to quit? 3
whirrrrrr .... and your pull is ...
cherries  cherries  BAR
Congratulations! You won $45

How much would you like to bet (1 - 100) or 0 to quit? 4
whirrrrrr .... and your pull is ...
7  BAR  7
Sorry, you lost.

How much would you like to bet (1 - 100) or 0 to quit? 5
whirrrrrr .... and your pull is ...
7  BAR  space
Sorry, you lost.

How much would you like to bet (1 - 100) or 0 to quit? 6
whirrrrrr .... and your pull is ...
space  space  BAR
Sorry, you lost.

How much would you like to bet (1 - 100) or 0 to quit? 9
whirrrrrr .... and your pull is ...
BAR  BAR  space
Sorry, you lost.

How much would you like to bet (1 - 100) or 0 to quit? 1
whirrrrrr .... and your pull is ...
BAR  7  cherries
Sorry, you lost.

How much would you like to bet (1 - 100) or 0 to quit? 2
whirrrrrr .... and your pull is ...
cherries  7  BAR
Congratulations! You won $10

How much would you like to bet (1 - 100) or 0 to quit? 3
whirrrrrr .... and your pull is ...
BAR  space  cherries
Sorry, you lost.

How much would you like to bet (1 - 100) or 0 to quit? 4
whirrrrrr .... and your pull is ...
cherries  cherries  BAR
Congratulations! You won $60

How much would you like to bet (1 - 100) or 0 to quit? 5
whirrrrrr .... and your pull is ...
cherries  BAR  space
Congratulations! You won $25

How much would you like to bet (1 - 100) or 0 to quit? 6
whirrrrrr .... and your pull is ...
space  BAR  BAR
Sorry, you lost.

How much would you like to bet (1 - 100) or 0 to quit? 5
whirrrrrr .... and your pull is ...
BAR  cherries  cherries
Sorry, you lost.

How much would you like to bet (1 - 100) or 0 to quit? 4
whirrrrrr .... and your pull is ...
BAR  7  BAR
Sorry, you lost.

How much would you like to bet (1 - 100) or 0 to quit? 9
whirrrrrr .... and your pull is ...
cherries  BAR  space
Congratulations! You won $45

How much would you like to bet (1 - 100) or 0 to quit? 11
whirrrrrr .... and your pull is ...
cherries  cherries  space
Congratulations! You won $165

How much would you like to bet (1 - 100) or 0 to quit? 0
Play again soon!
1400 0 0 0 0 0 510 0 0 65 0 0 0 4450 0 25 10 45 0 0 0 0 0 10 0 60 25 0 0 0 45 165
--------------------------------------------------------------*/