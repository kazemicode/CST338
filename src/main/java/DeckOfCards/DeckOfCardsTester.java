package DeckOfCards;

public class DeckOfCardsTester
{
    public static void main(String[] args)
    {
        Card c = new Card();
        Card x = new Card('f', Card.Suit.spades); // invalid
        Card f = new Card('K', Card.Suit.diamonds);
        Card d = new Card('Q', Card.Suit.clubs);
        System.out.println(f);
        System.out.println(d);
        System.out.println(c);

        // Phase 2

        Hand h = new Hand();
        for(int index = 0; index < h.myCards.length; index+=3)
        {
            h.takeCard(d);
            h.takeCard(c);
            h.takeCard(f);

        }
        if(!h.takeCard(d))
        {
            System.out.println("Hand full!");
        }
        System.out.println("After deal: ");
        System.out.println(h);

        System.out.println("Testing inspectCard()");
        System.out.println(h.inspectCard(99)); // illegal index
        for(int index = 0; index < h.myCards.length; index++)
        {
            System.out.println("Playing " + h.inspectCard(index));
        }

        // Phase 3

        // double pack

        Deck myDeck = new Deck(2);
        System.out.println("\n\n\n**********DEAL UNSHUFFLED");
        for(int index = 0; index < myDeck.getNumCards(); index++)
        {
            System.out.println("Dealing: "+ myDeck.dealCard());
        }


        System.out.println(" Test invalid k: " + myDeck.inspectCard(999));

        myDeck.init(2);
        myDeck.shuffle();
        System.out.println("\n\n\n**********SHUFFLING!!!!");
        System.out.println(myDeck);





        //Single pack
        Deck myDeck2 = new Deck(1);
        System.out.println("\n\n\n**********DEAL UNSHUFFLED");
        for(int index = 0; index < myDeck2.getNumCards(); index++)
        {
            System.out.println("Dealing: "+ myDeck2.dealCard());
        }


        System.out.println(" Test invalid k: " + myDeck2.inspectCard(999));

        myDeck2.init(1);
        myDeck2.shuffle();
        System.out.println("\n\n\n**********SHUFFLING!!!!");
        System.out.println(myDeck2);
    }
}
