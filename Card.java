// Programmer: Sukhnain Deol
// Class: CS 145
// Date 01/19/2023
// Assignment: Lab 4: Deck of Cards

// Creates cards which have a face, suit, and rank.
// Rank can only be values 1 through 13 due to the 
// face's value being dependant on it.

// Card Values: 
    // Ace = 1
    // 2-10 have their normal values 
    // Jack = 11, Queen = 12, King = 13

public class Card 
{
    private final String face; // see FACE_NAMES below
    private final String suit; // normally Spades, Clubs, Diamonds, Hearts
    private final int rank; // see Card Values above
    private final static String[] FACE_NAMES = 
    {"Ace", "Deuce", "Three", "Four", "Five", "Six", 
    "Seven", "Eight", "Nine", "Ten", "Jack", "Queen", "King"};

    // Card constructor initalizes face, suit, rank
    public Card(String cardSuit, int cardRank)
    {
        // auto adjusts cardRank if its not a real card
        if (cardRank > 13) // if more than 13
            {cardRank = 13;}
        else if (cardRank < 1) // less than 13
            {cardRank = 1;}

        this.rank = cardRank;
        this.suit = cardSuit; 
        this.face = FACE_NAMES[cardRank-1]; // assigns face according to rank
    } // end of Card constructor method


    // Card rank accessor
    public int getRank()
    {
        return this.rank;
    } // end of getRank accessor method


    // returns card's title (e.g. Twp of Hearts )
    public String toString()
    {
        return face + " of " + suit;
    } // end ot toString method
} // end of Card class
