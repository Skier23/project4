import java.io.File;
import java.util.Scanner;

public class SongSearch
{

    private static DataManager data;

    public static void main(String[] args)
    {
        File inputs = null;
        Scanner inputList = null;

        if (args.length < 3)
        {
            System.out.println("Insufficient number of arguments.  Expected 3,"
                    + " but got " + args.length);
            return;
        }

        inputs = new File(args[2]);
        try
        {
            // No file name, defaulting to SyntaxTest.txt
            inputList = new Scanner(inputs);

        }
        catch (Exception e)
        {
            // Exit if it can't find the specified command line
            System.out.println("Command list not found, exiting program");
            return;
        }

        data = new DataManager(Integer.parseInt(args[0]),
                Integer.parseInt(args[1]));

        // While loop breaks up the list into lines
        while (inputList.hasNextLine())
        {

            String line = inputList.nextLine().trim();
            // Ignore blank lines
            if (!line.isEmpty() && !line.equals("\n"))
            {
                // Divide up the line into an array of strings
                String[] split = line.split("\\s+");
                // Interpret command
                callFunction(split);
            }

        }
    }

    private static boolean callFunction(String[] command)
    {
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
                if (command.length == 3)
                {
                    switch (command[1].toLowerCase())
                    {
                        case "artist":
                            data.removeArtist(command[2]);
                            return true;
                        case "song":
                            data.removeSong(command[2]);
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
                        default:
                            System.out.println(
                                    "must specify whether to print all artists"
                                            + " or songs");
                            return false;
                    }
                }
                break;
            case "list":
                if (command.length == 3)
                {
                    switch (command[1].toLowerCase())
                    {
                        case "artist":
                            data.listArtist(command[2]);
                            return true;
                        case "song":
                            data.listArtist(command[2]);
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

            default:
                System.out.println("Unrecognized command!: " + command[0]);
                break;
        }
    }

}
