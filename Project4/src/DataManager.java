import java.util.ArrayList;

/**
 * DataManager creates and maintains a Binary Search Tree in the binaryTree
 * field. DataManager acts as middle-man between the BST data structure and the
 * Point1 front end. Point1 calls functions in DataManager, which in turn call
 * the required functions in the BST.
 * 
 * 
 * 
 * @author Tyler Bench, ski23
 * @author Christian Dy, k4b0odls
 * @version 2017-12-3
 */
public class DataManager
{
    private BST<KVPair> artistTree;
    private BST<KVPair> songTree;
    private MemoryManager database;
    private HashTable artistTable;
    private HashTable songTable;

    /**
     * Constructs a new DataManager object. Initializes fields.
     *
     * @param blocksize the blocksize for the database
     * @param initialHashSize the initial hash size
     */
    public DataManager(int blocksize, int initialHashSize)
    {
        artistTree = new BST<KVPair>();
        songTree = new BST<KVPair>();
        database = new MemoryManager(blocksize);
        artistTable = new HashTable(initialHashSize);
        songTable = new HashTable(initialHashSize);

    }

    /**
     * Inserts a KVPair into the database, hashtable, and tree if appropriate.
     *
     * @param artist the artist
     * @param song            Name of the Point to insert
     * @return the KV pair
     */
    public KVPair insert(String artist, String song)
    {
        Handle artistHandle = artistTable.find(artist);
        Handle songHandle = songTable.find(song);
        if (artistHandle == null)
        {
            artistHandle = new Handle(database.insert(artist), database);
            System.out.println(
                    "|" + artist + "| is added to the Artist database.");
            artistTable.insert(artistHandle);
        }
        else
        {
            System.out.println("|" + artist
                    + "| duplicates a record already in the Artist database.");
        }
        if (songHandle == null)
        {
            songHandle = new Handle(database.insert(song), database);
            System.out.println("|" + song + "| is added to the Song database.");
            songTable.insert(songHandle);
        }
        else
        {
            System.out.println("|" + song
                    + "| duplicates a record already in the Song database.");
        }
        KVPair artistPair = new KVPair(artistHandle, songHandle);
        KVPair songPair = new KVPair(songHandle, artistHandle);
        if (artistTree.insert(artistPair))
        {
            System.out.println("The KVPair " + artistPair.getString() + ","
                    + artistPair.toString() + " is added to the tree.");

            songTree.insert(songPair);
            System.out.println("The KVPair " + songPair.getString() + ","
                    + songPair.toString() + " is added to the tree.");
        }
        else
        {
            System.out.println("The KVPair " + artistPair.getString() + ","
                    + artistPair.toString()
                    + " duplicates a record already in the tree.");

            System.out.println("The KVPair " + songPair.getString() + ","
                    + songPair.toString()
                    + " duplicates a record already in the tree.");
        }

        return artistPair;
    }

    /**
     * Deletes the artist, song pair if it exists
     *
     * @param artist the artist part of the pair to delete
     * @param song the song part of the pair to delete
     */
    public void delete(String artist, String song)
    {
        Handle artistHandle = artistTable.find(artist);
        Handle songHandle = songTable.find(song);
        boolean valid = true;
        if (artistHandle == null)
        {
            System.out.println(
                    "|" + artist + "| does not exist in the artist database.");
            valid = false;
        }
        if (songHandle == null)
        {
            System.out.println(
                    "|" + song + "| does not exist in the song database.");
            valid = false;
        }
        
        if (!valid)
        {
            return;
        }
        KVPair artistPair = new KVPair(artistHandle, songHandle);
        KVPair songPair = new KVPair(songHandle, artistHandle);
        if (!artistTree.find(artistPair).isEmpty())
        {
            artistTree.remove(artistPair);
            System.out.println("The KVPair " + artistPair.getString()
                    + " is deleted from the tree.");
            songTree.remove(songPair);
            System.out.println("The KVPair " + songPair.getString()
                    + " is deleted from the tree.");
            KVPair artistSearch = new KVPair(artistHandle, Handle.SEARCH);
            KVPair songSearch = new KVPair(songHandle, Handle.SEARCH);

            if (artistTree.find(artistSearch).isEmpty())
            {
                System.out.println("|" + artist + "| is deleted from the"
                        + " artist database.");
                artistTable.remove(artist);
                database.delete(artistHandle.getHandle());

            }
            if (songTree.find(songSearch).isEmpty())
            {
                System.out.println("|" + song + "| is deleted from the"
                        + " song database.");
                songTable.remove(song);
                database.delete(songHandle.getHandle());

            }
        }
        else
        {
            System.out.println("The KVPair " + artistPair.getString()
                    + " was not found in the database.");
            System.out.println("The KVPair " + songPair.getString()
                    + " was not found in the database.");
        }
    }

    /**
     * Removes all songs by this artist
     *
     * @param artist the artist
     */
    public void removeArtist(String artist)
    {
        Handle artistHandle = artistTable.find(artist);
        if (artistHandle == null)
        {
            System.out.println(
                    "|" + artist + "| does not exist in the artist database.");
        }
        else
        {
            KVPair artistSearch = new KVPair(artistHandle, Handle.SEARCH);
            ArrayList<KVPair> toRemove = artistTree.find(artistSearch);
            for (int i = 0; i < toRemove.size(); i++)
            {
                KVPair removed = artistTree.remove(artistSearch);
                KVPair toRemoveOther = new KVPair(removed.getValue(),
                        removed.getKey());
                KVPair removedSearch = new KVPair(removed.getValue(),
                        Handle.SEARCH);
                KVPair removedSearch2 = new KVPair(removed.getKey(),
                        Handle.SEARCH);
                System.out.println("The KVPair " + removed.getString()
                        + " is deleted from the tree.");
                songTree.remove(toRemoveOther);
                System.out.println("The KVPair " + toRemoveOther.getString()
                        + " is deleted from the tree.");
                if (artistTree.find(removedSearch2).isEmpty())
                {
                    System.out.println("|" + artist + "| is deleted from the"
                            + " Artist database.");
                    artistTable.remove(artist);
                    database.delete(artistHandle.getHandle());
                }
                if (songTree.find(removedSearch).isEmpty())
                {
                    System.out.println("|" + removedSearch.getKey().getString()
                            + "| is deleted from the" + " Song database.");
                    songTable.remove(removedSearch.getKey().getString());
                    database.delete(removedSearch.getKey().getHandle());

                }
            }

        }
    }
    /**
     * Removes all artists who have a song with this name
     *
     * @param song the song
     */
    public void removeSong(String song)
    {
        Handle songHandle = songTable.find(song);
        if (songHandle == null)
        {
            System.out.println(
                    "|" + song + "| does not exist in the song database.");
        }
        else
        {
            KVPair songSearch = new KVPair(songHandle, Handle.SEARCH);
            ArrayList<KVPair> toRemove = songTree.find(songSearch);
            for (int i = 0; i < toRemove.size(); i++)
            {
                KVPair removed = songTree.remove(songSearch);
                KVPair toRemoveOther = new KVPair(removed.getValue(),
                        removed.getKey());
                KVPair removedSearch = new KVPair(removed.getValue(),
                        Handle.SEARCH);
                KVPair removedSearch2 = new KVPair(removed.getKey(),
                        Handle.SEARCH);
                System.out.println("The KVPair " + removed.getString()
                        + " is deleted from the tree.");
                artistTree.remove(toRemoveOther);
                System.out.println("The KVPair " + toRemoveOther.getString()
                        + " is deleted from the tree.");
                if (songTree.find(removedSearch2).isEmpty())
                {
                    System.out.println("|" + song + "| is deleted from the"
                            + " Song database.");
                    songTable.remove(song);
                    database.delete(songHandle.getHandle());
                }
                if (artistTree.find(removedSearch).isEmpty())
                {
                    System.out.println("|" + removedSearch.getKey().getString()
                            + "| is deleted from the" + " Artist database.");
                    artistTable.remove(removedSearch.getKey().getString());
                    database.delete(removedSearch.getKey().getHandle());

                }
            }
        }
    }

    /**
     * Prints all the artists
     */
    public void printArtist()
    {
        Handle[] artists = artistTable.toArray();
        int size = 0;
        for (int i = 0; i < artists.length; i++)
        {
            if (artists[i] != HashTable.TOMBSTONE && artists[i] != null)
            {
                System.out.println("|" + artists[i].getString() + "| " + i);
                size++;
            }
        }
        System.out.println("total artists: " + size);

    }
    /**
     * Prints all the songs
     */
    public void printSong()
    {
        Handle[] songs = songTable.toArray();
        int size = 0;
        for (int i = 0; i < songs.length; i++)
        {
            if (songs[i] != HashTable.TOMBSTONE && songs[i] != null)
            {
                System.out.println("|" + songs[i].getString() + "| " + i);
                size++;
            }
        }
        System.out.println("total songs: " + size);

    }

    /**
     * Prints out all the KVPairs in the artist and song trees
     *
     * @return the number of entries printed.
     */
    public int printTree()
    {
        int printed = 0;
        System.out.println("Printing artist tree:");

        printed += binaryDumpHelper(artistTree.getRoot(), 0);

        System.out.println("Printing song tree:");

        printed += binaryDumpHelper(songTree.getRoot(), 0);

        return printed;

    }

    /**
     * Helper method called by dump() to iterate through the BST recursively
     * This method is where the names and values of the Points are actually
     * printed when calling the dump() command
     * 
     * @param root
     *            The current node we are looking at
     * @param level
     *            The depth of the current node we're looking at
     * @return the number of entries printed.
     */
    private int binaryDumpHelper(Node<KVPair> root, int level)
    {
        int printed = 0;
        if (root != null)
        {

            printed += binaryDumpHelper(root.left(), level + 1);

            // Print out the node's data.

            String spaces = "";

            for (int i = 0; i < level; i++)

            {
                spaces += "  ";
            }

            System.out.println(spaces + root.value().toString());

            printed++;

            printed += binaryDumpHelper(root.right(), level + 1);
        }
        return printed;
    }

    /**
     * Prints a list of all songs performed by an artist in the database
     * 
     * @param artist
     *            The artists whose songs are to be printed
     * @return the number of songs printed.
     */
    public int listArtist(String artist)
    {
        Handle artistHandle = artistTable.find(artist);
        if (artistHandle == null)
        {
            System.out.println(
                    "|" + artist + "| does not exist in the artist database.");
            return 0;
        }

        KVPair findMe = new KVPair(artistHandle, Handle.SEARCH);
        ArrayList<KVPair> list = artistTree.find(findMe);

        for (KVPair pair : list)
        {
            System.out.println("|" + pair.getValue().getString() + "|");
        }

        return list.size();

    }

    /**
     * Prints a list of all artists that have performed a particular song in the
     * database
     * 
     * @param song
     *            The song whose artists we want printed
     * @return the number of songs printed.
     */
    public int listSong(String song)
    {
        Handle songHandle = songTable.find(song);
        if (songHandle == null)
        {
            System.out.println(
                    "|" + song + "| does not exist in the song database.");
            return 0;
        }

        KVPair findMe = new KVPair(songHandle, Handle.SEARCH);
        ArrayList<KVPair> list = songTree.find(findMe);

        for (KVPair pair : list)
        {
            System.out.println("|" + pair.getValue().getString() + "|");
        }

        return list.size();
    }

}
