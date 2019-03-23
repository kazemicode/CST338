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
    }
}
