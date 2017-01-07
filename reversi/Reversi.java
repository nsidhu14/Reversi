/*
Name: Navdeep Sidhu
Date: May 15, 2012
Purpose: Reversi, JAVA game
Sources:
http://www.apkhdgames.com/2012_01_22_archive.html
http://www.culturefocus.com/wallpaper-egypt.html
http://www.fileguru.com/3d-reversi-unlimited/screenshot
*/
import java.awt.*;
import java.awt.event.*;
import java.applet.*;
import javax.swing.*;
public class Reversi extends Applet implements ActionListener
{
    JButton grid[] = new JButton [64]; //reversi board
    int track[] [] = new int [8] [8]; //tracking chip position
    JButton turnpic, reset, validpic, mainmenu, skipturn, start;
    JLabel turn, score;
    int t = 1; //track player turn
    int scoreblack, scorewhite;
    String input, input2; // player names
    Panel p_card; // to hold all the screens
    Panel card1, card2, card3; //  screens
    CardLayout cdLayout = new CardLayout ();
    public void init ()
    {
	resize (810, 595);
	p_card = new Panel ();
	p_card.setLayout (cdLayout);
	screen1 (); //home
	screen2 (); //game
	screen3 (); //instructions
	setLayout (new BorderLayout ());
	add ("Center", p_card);
    }


    public void screen1 ()
    {
	card1 = new Panel ();
	showStatus (" Welcome to Reversi");
	card1.setBackground (Color.black);
	JLabel title = new JLabel (createImageIcon ("title.jpg"));
	JButton instructions = new JButton (createImageIcon ("instructions.jpg"));
	instructions.setBackground (Color.black);
	instructions.setActionCommand ("instructions");
	instructions.addActionListener (this);
	start = new JButton (createImageIcon ("start.jpg"));
	start.setBackground (Color.black);
	start.setActionCommand ("start");
	start.addActionListener (this);
	//exit button
	JButton quit = new JButton (createImageIcon ("quit.jpg"));
	quit.setBackground (Color.black);
	quit.setActionCommand ("quit");
	quit.addActionListener (this);
	card1.add (title);
	card1.add (instructions);
	card1.add (start);
	card1.add (quit);
	p_card.add ("1", card1);
    }


    public void screen2 ()
    {

	card2 = new Panel ();
	card2.setBackground (new Color (54, 54, 54));
	// Background of the board
	Panel q = new Panel (new FlowLayout (5, 5, 5));
	q.setBackground (Color.black);
	// Holds the grid
	Panel p = new Panel (new GridLayout (8, 8, 2, 2));
	p.setBackground (Color.black);
	// Holds the turn button
	Panel r = new Panel ();
	r.setBackground (new Color (54, 54, 54));
	// Holds the score JLabel
	Panel s = new Panel ();
	s.setBackground (new Color (54, 54, 54));
	//extra space for formatting
	JLabel extraspace = new JLabel ("                                                                                                                                                                    ");
	Panel m = new Panel (new GridLayout (6, 1, 0, 0));
	//holds all other side panels
	JLabel title2 = new JLabel (createImageIcon ("title2.jpg"));
	turnpic = new JButton (createImageIcon ("chip1.jpg"));
	turnpic.setPreferredSize (new Dimension (65, 65));
	turn = new JLabel ("Waiting to begin...");
	turn.setForeground (Color.white);
	reset = new JButton ("Reset");
	reset.setActionCommand ("reset");
	reset.addActionListener (this);
	reset.setBackground (Color.black);
	reset.setForeground (Color.white);
	reset.setPreferredSize (new Dimension (85, 35));
	mainmenu = new JButton ("Main Menu");
	mainmenu.setActionCommand ("mainmenu");
	mainmenu.addActionListener (this);
	mainmenu.setBackground (Color.black);
	mainmenu.setForeground (Color.white);
	mainmenu.setPreferredSize (new Dimension (105, 35));
	skipturn = new JButton ("Skip Turn");
	skipturn.setActionCommand ("skipturn");
	skipturn.addActionListener (this);
	skipturn.setBackground (Color.black);
	skipturn.setForeground (Color.white);
	skipturn.setPreferredSize (new Dimension (145, 35));
	skipturn.setEnabled (false);
	score = new JLabel ("Score:    Black:---    White:---  ");
	score.setForeground (Color.white);
	// Othello board "for" loop
	for (int i = 0 ; i < grid.length ; i++)
	{
	    grid [i] = new JButton (createImageIcon ("green.jpg"));
	    grid [i].setActionCommand (i + "");
	    grid [i].addActionListener (this);
	    grid [i].setBackground (Color.black);
	    grid [i].setPreferredSize (new Dimension (65, 65));
	    p.add (grid [i]);
	}
	// tracks beginning pieces
	track [3] [3] = 2;
	track [4] [3] = 1;
	track [3] [4] = 1;
	track [4] [4] = 2;
	q.add (p);
	card2.add (extraspace);
	card2.add (q);
	m.add (title2);
	r.add (turnpic);
	r.add (turn);
	m.add (r);
	s.add (score);
	m.add (s);
	//holds main & reset in a panel
	Panel n = new Panel ();
	n.add (mainmenu);
	n.add (reset);
	m.add (skipturn);
	m.add (n);
	card2.add (m);
	p_card.add ("2", card2);
	redraw ();
    }


    public void reset1 ()
    {
	for (int i = 0 ; i < grid.length ; i++)
	{
	    grid [i] = new JButton (createImageIcon ("green.jpg"));
	    grid [i].setActionCommand (i + "");
	    grid [i].addActionListener (this);
	    grid [i].setBackground (Color.black);
	    grid [i].setPreferredSize (new Dimension (65, 65));

	}
	// tracks beginning pieces
	track [3] [3] = 2;
	track [4] [3] = 1;
	track [3] [4] = 1;
	track [4] [4] = 2;
    }


    public void count ()
    {
	scoreblack = 0;
	scorewhite = 0;
	// keeps score after each turn
	for (int i = 0 ; i < 8 ; i++)
	{
	    for (int j = 0 ; j < 8 ; j++)
	    {

		if (track [i] [j] == 1)
		    scoreblack++;
		else if (track [i] [j] == 2)
		    scorewhite++;
	    }
	}

	score.setText ("Score: Black: " + scoreblack + "  White: " + scorewhite + "  ");
    }


    public void screen3 ()
    { //instructions screen
	card3 = new Panel ();
	card3.setBackground (Color.black);
	JLabel instruct = new JLabel (createImageIcon ("instruct.jpg"));
	JButton back = new JButton (createImageIcon ("back.jpg"));
	back.setBackground (Color.black);
	back.setActionCommand ("back");
	back.addActionListener (this);
	card3.add (instruct);
	card3.add (back);
	p_card.add ("3", card3);
    }


    public void redraw ()
    {
	int move = 0;
	//holds starting position for black & white pieces
	//allows for more pieces to be placed
	for (int i = 0 ; i < 8 ; i++)
	{
	    for (int j = 0 ; j < 8 ; j++)
	    {
		if (track [i] [j] == 0)
		    grid [move].setIcon (createImageIcon ("green.jpg"));
		else if (track [i] [j] == 1)
		    grid [move].setIcon (createImageIcon ("chip1.jpg"));
		else if (track [i] [j] == 2)
		    grid [move].setIcon (createImageIcon ("chip2.jpg"));
		move++;
	    }
	}
    }


    public void actionPerformed (ActionEvent e)
    {
	if (e.getActionCommand ().equals ("start") && !e.getActionCommand ().equals ("cont"))
	{
	    cdLayout.show (p_card, "2");
	    //Get the first players name
	    input = JOptionPane.showInputDialog ("Player 1, Please enter your name. (Max 6 letters) ");
	    //Get the second players name
	    input2 = JOptionPane.showInputDialog ("Player 2, Please enter your name. (Max 6 letters) ");
	    turn.setText ("It's " + input + "'s turn");
	}
	else if (e.getActionCommand ().equals ("cont"))
	{ // needed to remove the input dialogue when continuing a game in progress
	    cdLayout.show (p_card, "2");
	    turn.setText ("It's " + input + "'s turn");
	}
	else if (e.getActionCommand ().equals ("mainmenu"))
	{ // displays continue button on screen 1
	    cdLayout.show (p_card, "1");
	    start.setIcon (createImageIcon ("continue.jpg"));
	    start.setActionCommand ("cont");
	    start.addActionListener (this);
	}
	else if (e.getActionCommand ().equals ("instructions"))
	{ // displays instruction screen
	    cdLayout.show (p_card, "3");
	}
	else if (e.getActionCommand ().equals ("quit"))
	{ //exit dialogue box
	    int input = JOptionPane.showConfirmDialog (null, "Are you sure you would like to exit the game?",
		    "Exit Program - Reversi", JOptionPane.YES_NO_OPTION);
	    if (input == 0)
		//yes is clicked
		System.exit (1);
	    else
		//no is clicked
		cdLayout.show (p_card, "1");
	}
	else if (e.getActionCommand ().equals ("back"))
	{ // back to main
	    cdLayout.show (p_card, "1");
	}
	//reset button
	else if (e.getActionCommand ().equals ("reset"))
	{
	    reset1 ();
	    redraw ();
	    score.setText ("Score:    Black:---    White:---  ");
	    turnpic.setIcon (createImageIcon ("chip1.jpg"));
	    turn.setText ("It's " + input + "'s turn");
	}

	else if (!(e.getActionCommand ().equals ("start")))
	{ // Switches turns between players
	    int pos = Integer.parseInt (e.getActionCommand ());
	    int i = pos / 8;
	    int j = pos % 8;
	    if (validmove (i, j))
	    {
		switchit (i, j);
		if (t == 1)
		{
		    track [i] [j] = t;
		    t = 2;
		    turnpic.setIcon (createImageIcon ("chip2.jpg"));
		    turn.setText ("It's " + input2 + "'s turn");
		}
		else
		{
		    track [i] [j] = t;
		    t = 1;
		    turnpic.setIcon (createImageIcon ("chip1.jpg"));
		    turn.setText ("It's " + input + "'s turn");
		}
		//places chip on board
		redraw ();
		//updates score
		count ();
	    }
	}
    }


    public void switchit (int i, int j)
    { //switches positions
	if (findLeft (i, j))
	    changeLeft (i, j);
	if (findRight (i, j))
	    changeRight (i, j);
	if (findDown (i, j))
	    changeDown (i, j);
	if (findUp (i, j))
	    changeUp (i, j);
	if (findDiagonalDownRight (i, j))
	    changeDiagonalDownRight (i, j);
	if (findDiagonalDownLeft (i, j))
	    changeDiagonalDownLeft (i, j);
	if (findDiagonalUpLeft (i, j))
	    changeDiagonalUpLeft (i, j);
	if (findDiagonalUpRight (i, j))
	    changeDiagonalUpRight (i, j);


    }


    public boolean validmove (int i, int j)
    { //checks for valid position
	char coordinate = 'i';
	int ivalue = 0;
	int me = t;
	int you = 1;
	if (t == 1)
	    you = 2;
	// added to show "j" grid coordinates with letters
	if (j == 0)
	    coordinate = 'A';
	else if (j == 1)
	    coordinate = 'B';
	else if (j == 2)
	    coordinate = 'C';
	else if (j == 3)
	    coordinate = 'D';
	else if (j == 4)
	    coordinate = 'E';
	else if (j == 5)
	    coordinate = 'F';
	else if (j == 6)
	    coordinate = 'G';
	else if (j == 7)
	    coordinate = 'H';
	// added to show "i" grid coordinates with numbers starting at 1
	if (i == 0)
	    ivalue = 1;
	else if (i == 1)
	    ivalue = 2;
	else if (i == 2)
	    ivalue = 3;
	else if (i == 3)
	    ivalue = 4;
	else if (i == 4)
	    ivalue = 5;
	else if (i == 5)
	    ivalue = 6;
	else if (i == 6)
	    ivalue = 7;
	else if (i == 7)
	    ivalue = 8;


	if (track [i] [j] != 0)
	{
	    return false;
	}
	// placed chips  in correct position & shows the exact placement of chip in the status bar as (i,j)
	else if (findRight (i, j))
	{
	    showStatus (" The chip was placed in coordinates (" + coordinate + "," + ivalue + ")");
	    return true;
	}
	else if (findLeft (i, j))
	{
	    showStatus (" The chip was placed in coordinates (" + coordinate + "," + ivalue + ")");
	    return true;
	}
	else if (findDown (i, j))
	{
	    showStatus (" The chip was placed in coordinates (" + coordinate + "," + ivalue + ")");
	    return true;
	}
	else if (findUp (i, j))
	{
	    showStatus (" The chip was placed in coordinates (" + coordinate + "," + ivalue + ")");
	    return true;
	}
	else if (findDiagonalDownRight (i, j))
	{
	    showStatus (" The chip was placed in coordinates (" + coordinate + "," + ivalue + ")");
	    return true;
	}
	else if (findDiagonalDownLeft (i, j))
	{
	    showStatus (" The chip was placed in coordinates (" + coordinate + "," + ivalue + ")");
	    return true;
	}
	else if (findDiagonalUpLeft (i, j))
	{
	    showStatus (" The chip was placed in coordinates (" + coordinate + "," + ivalue + ")");
	    return true;
	}
	else if (findDiagonalUpRight (i, j))
	{
	    showStatus (" The chip was placed in coordinates (" + coordinate + "," + ivalue + ")");
	    return true;
	}
	else
	{
	    showStatus (" Invalid move, Try again.");
	    return false;
	}
    }


    public void changeLeft (int i, int j)
    { //starting value
	int start = j;
	// ending value
	int end = start - 1;
	//first chip
	int me = t;
	// second chip
	int you = 1;
	if (t == 1)
	    you = 2;
	//stopping condition
	while (track [i] [end] == you && track [i] [end] != 0)
	{ // loops through all the chips and checks if its valid
	    track [i] [end] = t;
	    // proceeds towards stopping condition
	    end--;
	}
    }


    public boolean findLeft (int i, int j)
    {
	int me = t;
	int you = 1;
	if (t == 1)
	    you = 2;
	int start = j;
	int end = start - 1;
	//invalid placement
	if (j == 0)
	    return false;
	// out of bounds
	else if (start - 1 < 0)
	    return false;
	// first/second chip is beside the same chip color
	else if (track [i] [start] == 0 && track [i] [end] == me)
	    return false;
	// out of bounds
	else if (track [i] [end] == 0)
	    return false;
	// looping & going through the line of chips
	else
	{
	    while (end >= 0 && track [i] [end] == you)
	    {
		end--;
	    }
	    // invalid
	    if (end <= 0)
		return false;
	    // ending chip does not match with starting chip
	    else if (track [i] [end] != me)
		return false;
	    // valid situation
	    else
		return true;

	}
    }


    public void changeRight (int i, int j)
    {
	int start = j;
	int end = start + 1;
	int me = t;
	int you = 1;
	if (t == 1)
	    you = 2;
	while (track [i] [end] == you && track [i] [end] != 0)
	{
	    track [i] [end] = t;
	    end++;
	}
    }


    public boolean findRight (int i, int j)
    {
	int me = t;
	int you = 1;
	if (t == 1)
	    you = 2;

	int start = j;
	int end = start + 1;
	if (j == 7)
	    return false;
	else if (start + 1 > 7)
	    return false;
	else if (track [i] [start] == 0 && track [i] [end] == me)
	    return false;
	else if (track [i] [end] == 0)
	    return false;
	else
	{
	    while (end < 8 && track [i] [end] == you)
	    {
		end++;
	    }
	    if (end == 7)
		return false;
	    else if (track [i] [end] != me)
		return false;
	    else
		return true;

	}
    }


    public void changeUp (int i, int j)
    {
	int start = i;
	int end = start - 1;
	int me = t;
	int you = 1;
	if (t == 1)
	    you = 2;
	while (track [end] [j] == you && track [end] [j] != 0)
	{
	    track [end] [j] = t;
	    end--;
	}
    }


    public boolean findUp (int i, int j)
    {
	int me = t;
	int you = 1;
	if (t == 1)
	    you = 2;

	int start = i;
	int end = start - 1;
	if (i == 0)
	    return false;
	else if (start - 1 < 0)
	    return false;
	else if (track [start] [j] == 0 && track [end] [j] == me)
	    return false;
	else if (track [end] [j] == 0)
	    return false;
	else
	{
	    while (end >= 0 && track [end] [j] == you)
	    {
		end--;
	    }
	    if (end <= 0)
		return false;
	    else if (track [end] [j] != me)
		return false;
	    else
		return true;
	}
    }


    public void changeDown (int i, int j)
    {
	int start = i;
	int end = start + 1;
	int me = t;
	int you = 1;
	if (t == 1)
	    you = 2;
	while (track [end] [j] == you && track [end] [j] != 0)
	{
	    track [end] [j] = t;
	    end++;
	}
    }


    public boolean findDown (int i, int j)
    {
	int me = t;
	int you = 1;
	if (t == 1)
	    you = 2;

	int start = i;
	int end = start + 1;
	if (j == 7)
	    return false;
	else if (start + 1 >= 8)
	    return false;

	else if (track [start] [j] == 0 && track [end] [j] == me)
	    return false;
	else if (track [end] [j] == 0)
	    return false;
	else
	{
	    while (end < 8 && track [end] [j] == you)
	    {
		end++;
	    }
	    if (end > 7)
		return false;
	    else if (track [end] [j] != me)
		return false;
	    else
		return true;
	}
    }



    public void changeDiagonalDownRight (int i, int j)
    {
	int starti = i;
	int endi = starti + 1;
	int startj = j;
	int endj = startj + 1;
	int me = t;
	int you = 1;
	if (t == 1)
	    you = 2;
	while (track [endi] [endj] == you && track [endi] [endj] != 0)
	{
	    track [endi] [endj] = t;
	    endi++;
	    endj++;
	}
    }


    public boolean findDiagonalDownRight (int i, int j)
    {
	int me = t;
	int you = 1;
	if (t == 1)
	    you = 2;

	int starti = i;
	int endi = starti + 1;
	int startj = j;
	int endj = startj + 1;

	if (j == 7 || i == 7)
	    return false;

	else if (starti + 1 >= 8)
	    return false;
	else if (startj + 1 >= 8)
	    return false;


	else if (track [starti] [startj] == 0 && track [endi] [endj] == me)
	    return false;

	else if (track [endi] [endj] == 0)
	    return false;

	else
	{
	    while (endi < 8 && endj < 8 && track [endi] [endj] == you)
	    {
		endi++;
		endj++;
	    }

	    if (endi > 7)
		return false;
	    else if (endj > 7)
		return false;

	    else if (track [endi] [endj] != me)
		return false;

	    else
		return true;

	}
    }


    public void changeDiagonalDownLeft (int i, int j)
    {
	int starti = i;
	int endi = starti + 1;
	int startj = j;
	int endj = startj - 1;
	int me = t;
	int you = 1;
	if (t == 1)
	    you = 2;
	while (track [endi] [endj] == you && track [endi] [endj] != 0)
	{
	    track [endi] [endj] = t;
	    endi++;
	    endj--;
	}
    }


    public boolean findDiagonalDownLeft (int i, int j)
    {
	int me = t;
	int you = 1;
	if (t == 1)
	    you = 2;

	int starti = i;
	int endi = starti + 1;
	int startj = j;
	int endj = startj - 1;

	if (j == 0 || i == 7)
	    return false;

	else if (starti + 1 >= 8)
	    return false;
	else if (startj + 1 <= 0)
	    return false;


	else if (track [starti] [startj] == 0 && track [endi] [endj] == me)
	    return false;

	else if (track [endi] [endj] == 0)
	    return false;

	else
	{
	    while (endi < 8 && endj >= 0 && track [endi] [endj] == you)
	    {
		endi++;
		endj--;
	    }

	    if (endi > 7)
		return false;
	    else if (endj < 0)
		return false;

	    else if (track [endi] [endj] != me)
		return false;

	    else
		return true;

	}
    }


    public void changeDiagonalUpLeft (int i, int j)
    {
	int starti = i;
	int endi = starti - 1;
	int startj = j;
	int endj = startj - 1;
	int me = t;
	int you = 1;
	if (t == 1)
	    you = 2;
	while (track [endi] [endj] == you && track [endi] [endj] != 0)
	{
	    track [endi] [endj] = t;
	    endi--;
	    endj--;
	}
    }


    public boolean findDiagonalUpLeft (int i, int j)
    {
	int me = t;
	int you = 1;
	if (t == 1)
	    you = 2;

	int starti = i;
	int endi = starti - 1;
	int startj = j;
	int endj = startj - 1;

	if (j == 0 || i == 0)
	    return false;

	else if (starti + 1 < 0)
	    return false;
	else if (startj + 1 < 0)
	    return false;


	else if (track [starti] [startj] == 0 && track [endi] [endj] == me)
	    return false;

	else if (track [endi] [endj] == 0)
	    return false;

	else
	{
	    while (endi >= 0 && endj >= 0 && track [endi] [endj] == you)
	    {
		endi--;
		endj--;
	    }

	    if (endi < 0)
		return false;
	    else if (endj < 0)
		return false;

	    else if (track [endi] [endj] != me)
		return false;

	    else
		return true;

	}
    }


    public void changeDiagonalUpRight (int i, int j)
    {
	int starti = i;
	int endi = starti - 1;
	int startj = j;
	int endj = startj + 1;
	int me = t;
	int you = 1;
	if (t == 1)
	    you = 2;
	while (track [endi] [endj] == you && track [endi] [endj] != 0)
	{
	    track [endi] [endj] = t;
	    endi--;
	    endj++;
	}
    }


    public boolean findDiagonalUpRight (int i, int j)
    {
	int me = t;
	int you = 1;
	if (t == 1)
	    you = 2;

	int starti = i;
	int endi = starti - 1;
	int startj = j;
	int endj = startj + 1;

	if (j == 7 || i == 0)
	    return false;

	else if (starti + 1 < 0)
	    return false;
	else if (startj + 1 >= 8)
	    return false;


	else if (track [starti] [startj] == 0 && track [endi] [endj] == me)
	    return false;

	else if (track [endi] [endj] == 0)
	    return false;

	else
	{
	    while (endi >= 0 && endj < 8 && track [endi] [endj] == you)
	    {
		endi--;
		endj++;
	    }

	    if (endi < 0)
		return false;
	    else if (endj > 7)
		return false;

	    else if (track [endi] [endj] != me)
		return false;

	    else
		return true;

	}
    }


    protected static ImageIcon createImageIcon (String path)
    { // Finds images to use
	java.net.URL imgURL = Reversi.class.getResource (path);
	if (imgURL != null)
	    return new ImageIcon (imgURL);

	else
	{
	    System.err.println ("Couldn't find file: " + path);
	    return null;
	}
    }
}



