// Programmer: Sukhnain Deol
// Class: CS 145
// Date 01/19/2023
// Assignment: Lab 4: Deck of Cards

// Purpose: Create the card game, War, in which the player plays
// against the program. Each game they are given 26 cards out of
// a shuffled deck, then they pull the top card and the one with 
// the higher value card gets all of them. If there is a tie, both
// play another card until a winner is decided who takes all the cards.

import java.util.*;
class War
{
    public static void main(String[] args) 
    {
        boolean wantsToPlay = true;

        // creates card deck with standard 52 cards
        ArrayList<Card> deck = new ArrayList<>();
        createCardDeck(deck);

        // initialize player & computer's decks 
        Stack<Card> playerDeck = new Stack<>();
        Stack<Card> computerDeck = new Stack<>();

        // arraylist that hold's cards won every round on each side
        ArrayList<Card> playersWinnings = new ArrayList<>();
        ArrayList<Card> computersWinnings = new ArrayList<>();

        Scanner in = new Scanner(System.in); // scanner for player input

        printIntroduction(); // introduces player to game rules

        // start of full-game loop 
        // end if player doesnt wanna play another game
        while(wantsToPlay)
        {
            // resets values for each game
            int round = 1; // resets round count
            Collections.shuffle(deck); // shuffles deck

            // assigns new cards from deck 
            // adds first 26 cards to player's deck, last 26 to computer's
            splitDeck(deck, playerDeck, 0, 26);
            splitDeck(deck, computerDeck, 26, 52);

            // clears winnings piles
            playersWinnings.clear();
            computersWinnings.clear();

            // start of single-game loop
            // ends when not enough cards to play another round
            while(!playerDeck.isEmpty() && !computerDeck.isEmpty())
            {
                // start of round
                System.out.println("\nPress 'Enter' to start round "+round +":");
                in.nextLine();

                // one round of the game is played
                playWar(in, playerDeck, computerDeck, playersWinnings, 
                computersWinnings);

                // round output seperator
                printChar('-', 49);
                System.out.println();

                round++; // increments round count
            } // end of single-game loop

            // prints game result and how manys cards 
            printGameWinner(computersWinnings, playersWinnings);

            System.out.println("Do you want to play again?");
            // if first letter of first word isnt 'y' or 'Y'
            if(!(in.next().toLowerCase().charAt(0) == 'y')) 
                {wantsToPlay = false;} // ends game loop
            else 
                {in.nextLine();} // clears input for next game
        } // end of full-game loop

        in.close(); // closes Scanner in
    } // end of main method



    public static void printIntroduction()
    {
        printChar(' ', 34);
        System.out.println("Welcome to the War card game!");
        printChar('-', 98);
        System.out.println();
        System.out.println("\nThe rules are simple: ");
        printChar('-', 98);
        System.out.println();
        System.out.print("- The computer and player(you) are both given");
        System.out.println(" 26 cards from a shuffled deck");
        System.out.print("- Both draw a card, the one with the higher rank card");
        System.out.println(" wins the round as well as both cards played");
        System.out.println("- If the cards are the same rank, then it is War!");
        System.out.print("    - Each side plays another card until one " );
        System.out.println(" has a card of higher rank than the other"); 
        System.out.println("- The one with the most cards won, wins");
        printChar('-', 98);
        System.out.println();
    } // end of printIntroduction method



    // initalizes arraylist holding all 52 card combinations
    public static void createCardDeck(ArrayList<Card> deck)
    {
        // intializes 52 cards, 13 of each suit
        String[] cardSuits = {"Spades", "Clubs", "Hearts", "Diamonds"};
        for (int k = 0; k < 4; k++)
        {
            String suit = cardSuits[k];
            for(int i = 0; i < 13; i++)
                {deck.add(new Card(suit, i+1));}
        }
    } // end of createCardsDeck method



    // assigns a given number of elements to an arraylist of Cards
    public static void splitDeck(ArrayList<Card> deck, Stack<Card> 
    splitDeck, int startIndex, int endIndex)
    {
        for(int i = startIndex; i < endIndex; i++ ) 
        {splitDeck.push(deck.get(i));}
    } // end of splitDeck method
    

    
    // prints a char a number of times
    public static void printChar(char printedChar, int printCount)
    {
        for(int i = 0; i < printCount; i++)
            {System.out.print(printedChar);}
    } // end of printChar Method



    // anounces who won/tie and how many cards each had
    public static void printGameWinner(ArrayList<Card> computersWinnings,
    ArrayList<Card> playersWinnings)
    {
        // announces end of game results, asks if want to play again
        System.out.println("\nBoth players have run out of Cards\n");
        System.out.print("Result: ");
        
        if(computersWinnings.size() > playersWinnings.size())
        {
            System.out.println("The Computer Wins with "+computersWinnings.size()
            +" cards. Player had "+playersWinnings.size());
        }
        else if(computersWinnings.size() < playersWinnings.size()) 
        {
            System.out.println("The Player Wins with "+ playersWinnings.size()
            +" cards. Computer had "+computersWinnings.size());
        }
        else
            {System.out.println("It's a tie!");}
    } // end of printGameWinner method


    
    // body of War card game, both players draw, winner gets all cards
    // if tie, each play another card, winner gets all cards
    public static void playWar(Scanner in, Stack<Card> playerDeck, Stack<Card> 
    computerDeck,ArrayList<Card> playersWinnings,ArrayList<Card> computersWinnings)
    {
        boolean isTied = false; // checks if game is a tie 

        // holds card pool awared to winner of tie game
        Queue<Card> tieHoldings = new LinkedList<>();

        // both draw their card
        Card playerDraw = playerDeck.pop();
        Card computerDraw = computerDeck.pop();

        // boths draws announcements 
        System.out.println("You drew " + playerDraw);
        System.out.println("Computer drew " + computerDraw);

        // winner announced & cards moved to their winnings arraylist
        // if player's draw is bigger
        if(playerDraw.getRank() > computerDraw.getRank())
        {
            System.out.println("You Won");
            playersWinnings.add(playerDraw);
            playersWinnings.add(computerDraw);
        } 
        // if computer's draw is bigger
        else if(playerDraw.getRank() < computerDraw.getRank())
        {
            System.out.println("Computer Won");
            computersWinnings.add(playerDraw);
            computersWinnings.add(computerDraw);
        }
        // if tie and cards are available for war
        else if (playerDraw.getRank() == computerDraw.getRank() && 
        playerDeck.size() > 0 && computerDeck.size() > 0)
        {
            isTied = true;
            while(isTied)
            {
                // isTied changed to true later if its another tie
                isTied = false;

                // holds cards from both to award to tie round's winner
                tieHoldings.add(playerDraw);
                tieHoldings.add(computerDraw);

                System.out.println("\nTie: Press 'Enter' to start the War");
                in.nextLine();

                // both draw their card
                playerDraw = playerDeck.pop();
                computerDraw = computerDeck.pop();

                // boths card draws outputted
                System.out.println("You drew " + playerDraw);
                System.out.println("Computer drew " + computerDraw);

                // winner announced & all cards moved to winner's arraylist
                // if player's draw is bigger
                if(playerDraw.getRank() > computerDraw.getRank())
                {
                    System.out.println("You Won");
                    playersWinnings.add(playerDraw);
                    playersWinnings.add(computerDraw);
                    playersWinnings.addAll(tieHoldings);
                    tieHoldings.clear();
                } 
                // if computer's draw is bigger
                else if(playerDraw.getRank() < computerDraw.getRank())
                {
                    System.out.println("Computer Won");
                    computersWinnings.add(playerDraw);
                    computersWinnings.add(computerDraw);
                    computersWinnings.addAll(tieHoldings);
                    tieHoldings.clear();
                }
                // else another tie
                else 
                    {isTied = true;}
            } // end of tie while loop
        }
        // if tie but no cards left to play another round
        else 
        {
            System.out.println("\nNot enough cards to continue to a tie game.");
            System.out.println("Each player's remaining card will be put");
            System.out.println("in their respective winnings piles.");
            playersWinnings.add(playerDraw);
            computersWinnings.add(computerDraw);
        } // end of round winner evaluation if/else
    } // end of playWar method
} // end of War class
