import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.util.*;
import java.text.*;

/*************************************************************
 * GUI for a Baby Name Database
 * 
 * @author Scott Grissom
 * @version October 7, 2017
 ************************************************************/
public class BabyNamesDatabaseGUI extends JFrame implements ActionListener{

    /** BabyNamesDatabase object */
    BabyNamesDatabase database;

    /** JButtons */
    JButton yearButton;
    JButton mostPopularButton;
    JButton topTenButton;
    JButton nameButton;

    /** JTestFields */
    JTextField yearTextField;
    JTextField nameTextField;

    /** Results text area */
    JTextArea resultsArea;

    /** menu items */
    JMenuBar menus;
    JMenu fileMenu;
    JMenuItem quitItem;
    JMenuItem openItem;
    JMenuItem countItem;

    /*****************************************************************
     * Main Method
     ****************************************************************/ 
    public static void main(String args[]){
        BabyNamesDatabaseGUI gui = new BabyNamesDatabaseGUI();
        gui.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        gui.setTitle("Baby Names");
        gui.pack();
        gui.setVisible(true);
    }

    /*****************************************************************
     * constructor installs all of the GUI components
     ****************************************************************/    
    public BabyNamesDatabaseGUI(){
        setupGUI();
        setupMenus();
    }

    /*****************************************************************
     * instantiates and adds the GUI components to the JFrame
     ****************************************************************/ 
    public void setupGUI() {
        //  instantiate an object of type BabyNameDatbase  
        database = new BabyNamesDatabase();

        // set the layout to GridBag
        getContentPane().setBackground(Color.LIGHT_GRAY);
        setLayout(new GridBagLayout());
        GridBagConstraints loc = new GridBagConstraints();

        // create results area to span one column and 10 rows
        resultsArea = new JTextArea(20,20);
        JScrollPane scrollPane = new JScrollPane(resultsArea);
        loc.gridx = 0;
        loc.gridy = 1;
        loc.gridheight = 10;  
        loc.insets.left = 20;
        loc.insets.right = 20;
        loc.insets.bottom = 20;
        add(scrollPane, loc);  

        // create Results label
        loc = new GridBagConstraints();
        loc.gridx = 0;
        loc.gridy = 0;
        loc.insets.bottom = 20;
        loc.insets.top = 20;
        add(new JLabel("Results"), loc);

        // create Searches label
        loc = new GridBagConstraints();
        loc.gridx = 1;
        loc.gridy = 0;
        loc.gridwidth = 2;
        add(new JLabel("Searches"), loc);     

        // instantiating the JButtons and JTextFields
        yearButton = new JButton ("By Year");

        // TO DO: finish the statements to instantiate the JButtons
        mostPopularButton = new JButton ("Most Popular");
        topTenButton = new JButton ("Top 10");
        nameButton = new JButton ("Name");

        yearTextField = new JTextField (5);

        //TO DO: finish the statement to instantiate nameTextField
        nameTextField = new JTextField (15);

            // adding labels and buttons
        loc = new GridBagConstraints();
        loc.fill = GridBagConstraints.RELATIVE;
        loc.anchor = GridBagConstraints.LINE_START;
        loc.insets = new Insets(5,5,5,5);
        loc.gridx = 1;
        loc.gridy = 1;
        add(new JLabel ("Year"), loc);
        loc.gridx++;
        add(yearTextField, loc);

        loc.gridy++;
        add(yearButton, loc);

        loc.gridy++;
        add(mostPopularButton, loc);

        loc.gridx = 2;
        loc.gridy = 4;
        add(topTenButton, loc);
        loc.insets = new Insets(25,5,0,5);
        loc.gridx = 1;
        loc.gridy = 7;
        add(new JLabel ("Name"), loc);

        loc.gridx = 2;
        loc.gridy = 7;
        add(nameTextField, loc);

        loc.gridy = 8;
        loc.insets = new Insets(5,5,5,5);
        add(nameButton, loc);

        // register listeners for the buttons
        yearButton.addActionListener(this); 
        // TO DO: register listeners for the rest of the JButtons

    }

    /*****************************************************************
     * This method is called when any button is clicked.  The proper
     * internal method is called as needed.
     * 
     * @param e the event that was fired
     ****************************************************************/       
    public void actionPerformed(ActionEvent e){

        // extract the button that was clicked
        JComponent buttonPressed = (JComponent) e.getSource();

        // Allow user to load baby names from a file    
        if (buttonPressed == openItem){
            openFile();
        }  

        if (buttonPressed == quitItem){
            System.exit(1);
        }  

        if (database.countAllNames() == 0)
            JOptionPane.showMessageDialog(this, "Forgot to read the file?");
        else { 
            if (buttonPressed == countItem){
                displayCounts();
            }  

            // TO DO: write 4 ifs to invoke the private methods 
            // associated with: yearButton, mostPopularButton, topTenButton and nameButton

        }
    }

    /************************************************************
     * displays the elements of the list
     * @param - ArrayList <BabyName> - list to be displayed
     *************************************************************/
    private void displayNames (ArrayList <BabyName> list) {
        DecimalFormat fmt = new DecimalFormat ("###,###,###");
        resultsArea.setText("");
        for(BabyName b : list){
            resultsArea.append("\n" + b.toString());
        }
        //TO DO: append the total number of elements in the list
    }

    /*************************************************************
     * Display the most popular names
     ***********************************************************/
    private void displayMostPopular (){
        try{
            //gets the text from the yearTextField and converts it to a int
            int number = Integer.parseInt(yearTextField.getText());
            resultsArea.setText ("Most Popular Girl for year " + number);
            //invokes the mostPopularGirl method from the BabyNamesDatabase class
            resultsArea.append ("\n" + database.mostPopularGirl(number));

            //TO DO: complete the code to display the most popular boy for the year

        }      
        catch(NumberFormatException e){
            JOptionPane.showMessageDialog(this, "Enter a valid year");
        }

    }

    /*****************************************************************
     * diplayByYear
     * The searchForYear method retuns an ArrayList <BabyNames>
     * This ArrayList is used as the parameter of displayNames method
     *****************************************************************/
    private void displayByYear () {
        try{
            int number = Integer.parseInt(yearTextField.getText());        
            displayNames (database.searchForYear (number));
        }      
        catch(NumberFormatException e){
            JOptionPane.showMessageDialog(this, "Enter a valid year");
        }

    }

    /***************************************************************
     * displayTopTen 
     * similar logic of displayByYear
     * TO DO:
     ********************************************************/
    private void displayTopTen () {

    }

    /***************************************************************
     * displayByName
     * TO DO: 
     * display the ArrayList <BabyName> of the names on the list
     * with the name equal to the name entered in the nameTextField
     * 
     ********************************************************/
    private void displayByName () {

    }

    /****************************************************************
     * displayCounts
     * 
     * TO DO: 
     * display the total of items in the ArrayList, 
     * the total of number of girls
     * the total of number of boys
     * Use the DecimalFormat given to format the numbers
     ****************************************************************/
    private void displayCounts () {
        DecimalFormat fmt = new DecimalFormat ("###,###");
        

    }

    /*****************************************************************
     * open a data file with the name selected by the user
     ****************************************************************/ 
    private void openFile(){

        // create File Chooser so that it starts at the current directory
        String userDir = System.getProperty("user.dir");
        JFileChooser fc = new JFileChooser(userDir);

        // show File Chooser and wait for user selection
        int returnVal = fc.showOpenDialog(this);

        // did the user select a file?
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            String filename = fc.getSelectedFile().getName();
            database.readBabyNameData(filename);          
        }
    }

    /*******************************************************
    Creates the menu items
     *******************************************************/    
    private void setupMenus(){
        fileMenu = new JMenu("File");
        quitItem = new JMenuItem("Quit");
        countItem = new JMenuItem("Counts");
        openItem = new JMenuItem("Open...");
        fileMenu.add(countItem);
        fileMenu.add(openItem);
        fileMenu.add(quitItem);
        menus = new JMenuBar();
        setJMenuBar(menus);
        menus.add(fileMenu);

        // TO DO: register the 3 menu items with the action listener

    }
}
