import java.io.File;
import java.util.Scanner;

// On my honor:
//
// - I have not used source code obtained from another student,
// or any other unauthorized source, either modified or
// unmodified.
//
// - All source code and documentation used in my program is
// either my original work, or was derived by me from the
// source code published in the textbook for this course.
//
// - I have not discussed coding details about this project with
// anyone other than my partner (in the case of a joint
// submission), instructor, ACM/UPE tutors or the TAs assigned
// to this course. I understand that I may discuss the concepts
// of this program with other students, and that another student
// may help me debug my program so long as neither of us writes
// anything during the discussion or modifies any computer file
// during the discussion. I have violated neither the spirit nor
// letter of this restriction.

/**
 * This program creates and manages a PR Quadtree filled with points within a
 * 1024 x 1024 space, as well as a binary search tree that contains the names of
 * said points. This program may add, remove, and search for points, as well as
 * displaying duplicate points and the entirety of the database.
 * -Add points 
 * -Remove points with a given name. 
 * -Remove points with given location.
 * -Search for points with a given name. 
 * -Search for points within a specified region.
 * -Search for duplicate points. 
 * -Display a list of all points in the system.
 * 
 * @author Tyler Bench, ski23
 * @author Christian Dy, k4b0odls
 * @version 2017-10-18
 */
public class Point1
{
    
    // The class that contains both the BST and PR Quadtree.
    private static DataManager data;

    /**
     * This is the main method for the Rectangle program. It kicks off the
     * process, but does nothing on its own. It takes in the name of a command
     * list file as an argument.
     * 
     * @param args
     *            The name of the command list file to draw commands from.
     */
    public static void main(String[] args)
    {
        Scanner scanner = getInputs(args);
        if (scanner != null)
        {
            processCommands(getInputs(args));
        }

    }

    /**
     * Takes the file name passed from the main method and attempts to return a
     * scanner from that file. Defaults to SyntaxTest.txt if a filename is not
     * given.
     * 
     * @param filename
     *      The name of our command list file   
     * @return
     *      A scanner made from the file it is given.
     */
    private static Scanner getInputs(String[] filename)
    {
        File inputs = null;
        Scanner inputList = null;

        if (filename.length == 1)
        {
            // File name provided
            inputs = new File(filename[0]);
        }
        else
        {
            System.out.println("Please specify a command list text file");
            return null;
        }

        try
        {
            inputList = new Scanner(inputs);

        }
        catch (Exception e)
        {
            // Exit if it can't find the specified command line
            System.out.println("Command list not found, exiting program");
            return null;
        }

        return inputList;
    }

    /**
     * Receives a list of commands in a scanner and runs through it line by 
     * line, calling the executeCommands method for each line as it goes.  
     * @param inputList
     *      The list of commands to process.
     */
    private static void processCommands(Scanner inputList)
    {
        data = new DataManager();

        // While loop breaks up the list into lines
        while (inputList.hasNextLine())
        {

            String line = inputList.nextLine().trim();
            // Ignore blank lines
            if (!line.isEmpty())
            {
                // Divide up the line into an array of strings
                String[] split = line.split("\\s+");
                // Interpret command
                executeCommand(split);
            }

        }
    }

    /**
     * Takes a string of arrays as a command and interprets them to call a
     * function in our data manager.  Invalid commands are rejected.
     * 
     * @param command
     *            The command to interpret and execute.
     */
    private static void executeCommand(String[] command)
    {
        switch (command[0].toLowerCase())
        {

            case "insert":
                String name = command[1];
                int x = Integer.parseInt(command[2]);
                int y = Integer.parseInt(command[3]);

                data.insert(name, x, y);
                break;
            case "remove":
                if (command.length == 2)
                {
                    data.remove(command[1]);
                }
                else
                {
                    int x1 = Integer.parseInt(command[1]);
                    int y1 = Integer.parseInt(command[2]);

                    data.remove(x1, y1);
                }
                break;
            case "regionsearch":
                    int x1 = Integer.parseInt(command[1]);
                    int y1 = Integer.parseInt(command[2]);
                    int w = Integer.parseInt(command[3]);
                    int h = Integer.parseInt(command[4]);
                    data.regionSearch(x1, y1, w, h);
                break;
            case "duplicates":
                data.duplicates();
                break;
            case "search":
                    data.search(command[1]);
                break;
            default:
                data.dump();
                break;
        }
    }
}
