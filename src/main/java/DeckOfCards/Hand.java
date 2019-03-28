package DeckOfCards;

/* Hand class
 * Has cards (Stored in private instance variable myCards, an array of Cards
 * Represents a player's hand of cards
 */
public class Hand
{
    public int MAX_CARDS = 50; // Max cards a Hand can have

    // private instance variables
    Card[] myCards;
    int numCards;

    // Zero argument constructor
    // Initalizes numCards to maximum length specified by MAX_CARDS
    // and initalizes numCards to 0 (empty hand)
    public Hand()
    {
        myCards = new Card[MAX_CARDS];
        numCards = 0;
    }

    // Accessor methods

    // Returns myCard array reference
    public Card[] getMyCards()
    {
        return myCards;
    }

    // Returns number of cards
    public int getNumCards()
    {
        return numCards;
    }

    // Returns Card at index k in myCards
    public Card inspectCard(int k)
    {
        if(k < myCards.length)
        {
            return myCards[k]; // return card if k is good
        }
        return new Card('X', Card.Suit.diamonds); // invalid card if k is bad

    }

    // Sets all values in myCards to null
    // Represents removing all cards from Hand
    public void resetHand()
    {
        numCards = 0;
        for(int index = 0; index < myCards.length; index++)
        {
            myCards[index] = null;
        }

    }

    // Take a card only if we can fit it in the Hand and increase numCards
    // Return if successful or not (boolean)
    public boolean takeCard(Card card)
    {
        if(numCards < MAX_CARDS)
        {

            myCards[numCards] = new Card(card.getValue(), card.getSuit());
            numCards++;
            return true;
        }
        return false;

    }

    // return and remove the Card last entered into the Hand
    public Card playCard()
    {
        Card result = new Card(myCards[numCards - 1].getValue(), myCards[numCards - 1].getSuit());
        myCards[numCards - 1] = null;
        numCards--;
        return result;
    }


    @Override
    public String toString()
    {
        String result = "Hand = ( ";
        for(int index = 0; index < myCards.length; index++)
        {
            if(myCards[index] != null)
            {
                result += myCards[index] + ", ";
                if (index % 5 == 3)
                {
                    result += "\n";
                }
            }

        }
        result = result.substring(0, result.length()-2) + " )";
        return result;
    }



}
