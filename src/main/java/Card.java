/* Card class is defined by a value (char) and suit (enum Suit) as well as
 * if it is in a valid state (boolean errorFlag)
 */

public class Card
{
    // enum Suit defines valid suits
    public static enum Suit
    {
       hearts, diamonds, spades, clubs
    }

    // private instance variables
    private char value;
    private Suit suit;
    private boolean errorFlag;

    // 0-arg constructor - Creates a default Card - A of spades
    public Card()
    {
        value = 'A';
        suit = Suit.spades;
    }

   // 2-arg constructor
    public Card(char value, Suit suit)
    {
        set(value, suit);
    }

    // Mutator method to set value and suit
    public boolean set(char value, Suit suit)
    {
        this.value = value;
        this.suit = suit;
        errorFlag = !isValid(value, suit); // set errorFlag to opposite of isValid
        return true;
    }

    // Accessor method returns Card's value (char)
    public char getValue()
    {
        return value;
    }

    // Accessor method returns Card's suit (enum)
    public Suit getSuit()
    {
        return suit;
    }

    // Accessor method returns Card's errorFlag (boolean)
    public boolean getErrorFlag()
    {
        return errorFlag;
    }

    // Checks if Card is in a valid state (valid value)
    private boolean isValid(char value, Suit suit)
    {
        //  returns true if we have a valid value
        return value == 'A' || value == 'K' || value == 'Q' || value == 'J' ||
                value == 'T' || (value >= '2' && value <= '9' );
    }

    // Checks if two cards have equivalent values and Suits
    public boolean equals(Card other)
    {
        return this.value == other.value && this.suit.equals(other.suit);
    }

    // Overridden toString method displays value and suit of Card
    @Override
    public String toString()
    {
        if(!errorFlag)
        {
            return value + " of " + suit;
        }
        return "** invalid **";
    }

}

