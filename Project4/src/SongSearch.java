import java.io.File;
import java.util.Scanner;

/**
 * Tests the methods of DataManager
 * 
 * @author Tyler Bench, ski23
 * @author Christian Dy, k4b0odls
 * @version 2017-10-18
 */
public class SongSearch
{
    /**
     * Executes the program with 3 arguments. The first argument as an integer
     * that
     * 
     * @param args
     *            The arguments to execute the program with.
     */
    public static void main(String[] args)
    {
        if (!checkArgs(args))
        {
            return;
        }
        
        File inputs =  new File(args[2]);
        Scanner inputList = makeScanner(inputs);

        if (inputList == null)
        {
            return;
        }
        
        DataManager manager = new DataManager(Integer.parseInt(args[0]),
                Integer.parseInt(args[1]));

        // While loop breaks up the list into lines
        while (inputList.hasNextLine())
        {

            String line = inputList.nextLine().trim();
            // Ignore blank lines
            if (!line.isEmpty() && !line.equals("\n"))
            {

                // Interpret command
                execute(line, manager);
            }

        }
        inputList.close();
    
    }
    
    /**
     * Checks to see if we are given the correct number of arguments and that
     * the initial sizes are not 0
     * @param args
     *      Arguments for main that need to be checked
     * @return true if the arguments are valid, false otherwise
     */
    public static boolean checkArgs(String[] args)
    {
        if (args.length < 3)
        {
            System.out.println("Insufficient number of arguments.  Expected 3,"
                    + " but got " + args.length);
            return false;
        }

        if (Integer.parseInt(args[0]) <= 0)
        {
            System.out.println("Initial block size must be greater than zero.");
            return false;
        }
        
        if (Integer.parseInt(args[1]) <= 0)
        {
            System.out.println("Initial hash table size must be greater than "
                    + "zero.");
            return false;
        }
        
        return true;
    }

    /**
     * Attempts to create a Scanner from a given file
     * @param file
     *      The File to make a Scanner from
     * @return the Scanner created
     */
    public static Scanner makeScanner(File file)
    {
        try
        {
            return new Scanner(file);
        }
        catch (Exception e)
        {
            // Exit if it can't find the specified command line
            System.out.println("Command list not found, exiting program");
            return null;
        }
    }
    
    /**
     * Executes a command by calling the appropriate method in DataManager
     * 
     * @param input
     *            A string that contains the command and its parameters
     * @param data
     *            The DataManager to send execute commands to           
     * @return true if the command was valid, false otherwise.
     */
    public static boolean execute(String input, DataManager data)
    {
        // Divide up the command into an array of strings
        String[] command = input.split("\\s+", 2);

        switch (command[0].toLowerCase())
        {

            case "insert":
                if (command.length == 2)
                {
                    String[] params = command[1].split("<SEP>");
                    if (params.length == 2)
                    {
                        data.insert(params[0], params[1]);
                        return true;
                    }
                    System.out.println("invalid format for insertion parameter."
                            + " Expecting artist<SEP>song");
                    return false;

                }

                System.out.println("invalid number of parameters for insert");
                return false;
            case "delete":
                if (command.length == 2)
                {
                    String[] params = command[1].split("<SEP>");
                    if (params.length == 2)
                    {
                        data.delete(params[0], params[1]);
                        return true;
                    }
                    System.out.println("invalid format for deletion parameter."
                            + " Expecting artist<SEP>song");
                    return false;

                }

                System.out.println("invalid number of parameters for delete");
                return false;

            case "remove":
                if (command.length == 2)
                {
                    String[] params = command[1].split("\\s+", 2);
                    if (params.length != 2)
                    {
                        System.out.println("invalid number of parameters for"
                                + " remove");
                        return false;
                    }
                    switch (params[0].toLowerCase())
                    {
                        case "artist":
                            data.removeArtist(params[1]);
                            return true;
                        case "song":
                            data.removeSong(params[1]);
                            return true;
                        default:
                            System.out.println(
                                    "must specify whether to remove an artist"
                                            + " or song");
                            return false;
                    }
                }

                System.out.println("invalid number of parameters for remove");

                return false;

            case "list":
                if (command.length == 2)
                {
                    String[] params = command[1].split("\\s+", 2);
                    if (params.length != 2)
                    {
                        System.out.println("invalid number of parameters for"
                                + " list");
                        return false;
                    }
                    switch (params[0].toLowerCase())
                    {
                        case "artist":
                            data.listArtist(params[1]);
                            return true;
                        case "song":
                            data.listSong(params[1]);
                            return true;
                        default:
                            System.out.println("Must specify whether to list"
                                    + " all songs from an artist or all "
                                    + "artists for a song");
                            return false;
                    }
                }

                System.out.println("invalid number of parameters for list");
                return false;

            case "print":
                if (command.length == 2)
                {
                    switch (command[1].toLowerCase())
                    {
                        case "artist":
                            data.printArtist();
                            return true;
                        case "song":
                            data.printSong();
                            return true;
                        case "tree":
                            data.printTree();
                            return true;
                        default:
                            System.out.println(
                                    "Invalid print format. Valid formats "
                                            + "are print {artist|song|tree}");
                            
                    }
                    return false;
                }

            default:
                System.out.println("Unrecognized command!: " + command[0]);

        }
        return false;
    }

}
