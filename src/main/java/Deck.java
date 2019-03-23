public class Deck
{
    private static Card[] masterPack;

    // private instance variables
    private Card[] cards;
    private int topCard;
    private int numPacks;

    // zero argument constructor
    public Deck()
    {
        new Deck(1);
    }

    // one argument constructor
    public Deck(int numPacks)
    {
        allocateMasterPack();
        this.numPacks = numPacks;
        cards = new Card[numPacks*52]; // 52 cards per pack
        topCard = cards.length - 1; // last card in deck
        init(numPacks);
    }

    // accessors

    // access length of cards array
    public int getNumCards()
    {
        return cards.length;
    }

    // Returns integer representing the position of the last (top) card
    public int getTopCard()
    {
        return topCard;
    }

    // Returns Card at position k if Card is valid index
    // If k is invalid, return an invalid Card
    public Card inspectCard(int k)
    {
     if(k < cards.length)
     {
         return cards[k];
     }
     return new Card('X', Card.Suit.diamonds); // invalid card
    }

    // Populate the Deck with 52 * numPacks Cards
    public void init(int numPacks)
    {
        int cardIndex = 0;
        for(int packs = 0; packs < numPacks; packs++)
            for(int index = 0; index < 52; index++)
            {
              cards[cardIndex] = masterPack[index];
              cardIndex++;
            }
    }

    public void shuffle()
    {
        Card temp = new Card();
        for(int index = 0; index < cards.length; index++)
        {
            int randomIndex = (int) (Math.random() * 51);
            temp = cards[index];
            cards[index] = cards[randomIndex];
            cards[randomIndex] = temp;
        }
    }

    public Card dealCard()
    {
        Card result =  inspectCard(topCard);
        if(topCard >= 0)
        {
            cards[topCard] = null;
            topCard--;
        }

        return result;

    }

    private static void allocateMasterPack()
    {

        if(masterPack == null)
        {
            masterPack = new Card[52];
            int index = 0;
            char[] values = {'A', 'K', 'Q', 'J', 'T', '9', '8', '7', '6', '5', '4', '3', '2'};
            Card.Suit[] suits = { Card.Suit.clubs, Card.Suit.diamonds, Card.Suit.hearts, Card.Suit.spades };

            for(Card.Suit suit : suits)
            {
                for(char value : values)
                {
                    masterPack[index] = new Card(value, suit);
                    index++;
                }
            }
        }
    }

    @Override
    public String toString()
    {
        String result = "Deck = ( ";
        for(int index = 0; index <  cards.length; index++)
        {
            result += cards[index] + ", ";
            if(index % 5 == 3)
            {
                result+="\n";
            }

        }
        result = result.substring(0, result.length()-3) + " )";
        return result;
    }

}
