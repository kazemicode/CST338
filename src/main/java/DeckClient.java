import java.util.Scanner;

public class DeckClient
{
    public static void main(String[] args)
    {
        Scanner input = new Scanner(System.in);
        System.out.print("Enter number of players (1-10): ");
        int numHands = input.nextInt();
        Hand[] players = new Hand[numHands]; // this will store the Hand objects
        Deck myDeck = new Deck(1); // single pack hand

        // generate numHands number of Hand objects
        // put them in the players array
        for(int index = 0; index < numHands; index++)
        {
            players[index] = new Hand();
        }

        // deal cards to each hand

        while(myDeck.getTopCard() >= 0)
        {
            for(int handIndex = 0; handIndex < numHands; handIndex++)
            {
                if(myDeck.getTopCard() >= 0)
                {
                    players[handIndex].takeCard(myDeck.dealCard());
                }
            }
        }

        // Print out each hand
        for(int index = 0; index < numHands; index++)
        {
            System.out.println(players[index]);
            System.out.println();
        }

        // reset everything
        myDeck.init(1); // reset deck
        for(int index = 0; index < numHands; index++)
        {
            players[index].resetHand(); // reset hand
        }

        myDeck.shuffle(); // shuffle deck

        // deal cards to each hand

        while(myDeck.getTopCard() >= 0)
        {
            for(int handIndex = 0; handIndex < numHands; handIndex++)
            {
                if(myDeck.getTopCard() >= 0)
                {
                    players[handIndex].takeCard(myDeck.dealCard());
                }
            }
        }

        // Print out each hand
        for(int index = 0; index < numHands; index++)
        {
            System.out.println(players[index]);
            System.out.println();
        }




    }

}
