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
 * @version 2017-09-24
 */
public class DataManager
{
    private BST<KVPair> artistTree;
    private BST<KVPair> songTree;
    private MemoryManager database;
    private HashTable artistTable;
    private HashTable songTable;

    /**
     * Constructs a new DataManager object. Initializes the binaryTree field.
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
     * Inserts a new Point into the tree. Rejects Points that with 0 or negative
     * dimensions or ones that exceed the bounds of a 1024 x 1024 space. Also
     * rejects names that don't start with a letter of the alphabet.
     * 
     * @param song
     *            Name of the Point to insert
     * @param x
     *            X coordinate of the new Point's upper left corner
     * @param y
     *            y coordinate of the new Point's upper right corner
     */
    public void insert(String artist, String song)
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
                    + " duplicates a record already in the Artist database.");
        }
        if (songHandle == null)
        {
            songHandle = new Handle(database.insert(song), database);
            System.out.println("|" + song + "| is added to the Song database.");
            songTable.insert(songHandle);
        }
        else
        {
            System.out.println("|" + artist
                    + " duplicates a record already in the Artist database.");
        }
        KVPair artistPair = new KVPair(artistHandle, songHandle);
        KVPair songPair = new KVPair(songHandle, artistHandle);
        if (artistTree.insert(artistPair))
        {
            System.out.println("The KVPair " + artistPair.getString() + ","
                    + artistPair.toString() + "is added to the tree.");

            songTree.insert(songPair);
            System.out.println("The KVPair " + songPair.getString() + ","
                    + songPair.toString() + "is added to the tree.");
        }
        else
        {
            System.out.println("The KVPair " + artistPair.getString() + ","
                    + artistPair.toString()
                    + "duplicates a record already in the tree.");

            System.out.println("The KVPair " + songPair.getString() + ","
                    + songPair.toString()
                    + "duplicates a record already in the tree.");
        }
    }

    /**
     * @return the number of points held in the trees
     */
    public int getSize()
    {
        return artistTree.size();
    }

    public void delete(String artist, String song)
    {
        Handle artistHandle = artistTable.find(artist);
        Handle songHandle = artistTable.find(song);
        if (artistHandle == null)
        {
            System.out.println(
                    "|" + artist + "| does not exist in the artist database.");
            return;
        }
        if (songHandle == null)
        {
            System.out.println(
                    "|" + artist + "| does not exist in the artist database.");
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
            KVPair artistSearch = new KVPair(artistHandle, Handle.search);
            KVPair songSearch = new KVPair(songHandle, Handle.search);
            if (artistTree.find(artistSearch).isEmpty())
            {
                artistTable.remove(artist);
                database.delete(artistHandle.getHandle());
                System.out.println("|" + artist + "| is deleted from the"
                        + " Artist database.");
            }
            if (songTree.find(songSearch).isEmpty())
            {
                songTable.remove(song);
                database.delete(songHandle.getHandle());
                System.out.println("|" + song + "| is deleted from the"
                        + " Song database.");
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
            KVPair artistSearch = new KVPair(artistHandle, Handle.search);
            ArrayList<KVPair> toRemove = artistTree.find(artistSearch);
            for (int i = 0; i < toRemove.size(); i++)
            {
                KVPair removed = artistTree.remove(artistSearch);
                KVPair toRemoveOther = new KVPair(removed.getValue(),
                        removed.getKey());
                KVPair removedSearch = new KVPair(removed.getValue(),
                        Handle.search);
                System.out.println("The KVPair " + removed.getString()
                        + " is deleted from the tree.");
                songTree.remove(toRemoveOther);
                System.out.println("The KVPair " + toRemoveOther.getString()
                        + " is deleted from the tree.");
                if (songTree.find(removedSearch).isEmpty())
                {
                    songTable.remove(removedSearch.getKey().getString());
                    database.delete(removedSearch.getKey().getHandle());
                    System.out.println("|" + removedSearch.getKey().getString()
                            + "| is deleted from the" + " Song database.");
                }
            }
            artistTable.remove(artist);
            database.delete(artistHandle.getHandle());
            System.out.println("|" + artist + "| is deleted from the"
                    + " Artist database.");
        }
    }

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
            KVPair songSearch = new KVPair(songHandle, Handle.search);
            ArrayList<KVPair> toRemove = songTree.find(songSearch);
            for (int i = 0; i < toRemove.size(); i++)
            {
                KVPair removed = songTree.remove(songSearch);
                KVPair toRemoveOther = new KVPair(removed.getValue(),
                        removed.getKey());
                KVPair removedSearch = new KVPair(removed.getValue(),
                        Handle.search);
                System.out.println("The KVPair " + removed.getString()
                        + " is deleted from the tree.");
                artistTree.remove(toRemoveOther);
                System.out.println("The KVPair " + toRemoveOther.getString()
                        + " is deleted from the tree.");
                if (artistTree.find(removedSearch).isEmpty())
                {
                    artistTable.remove(removedSearch.getKey().getString());
                    database.delete(removedSearch.getKey().getHandle());
                    System.out.println("|" + removedSearch.getKey().getString()
                            + "| is deleted from the" + " Artist database.");
                }
            }
            songTable.remove(song);
            database.delete(songHandle.getHandle());
            System.out.println(
                    "|" + song + "| is deleted from the" + " Song database.");
        }
    }

    public void printArtist()
    {
        // TODO Auto-generated method stub

    }

    public void printSong()
    {
        // TODO Auto-generated method stub

    }

    /**
     * Prints out a list of all Points within the BST, followed by a list of the
     * points and nodes in the QuadTree Split to assist in testing
     */
    public void printTree()
    {

        System.out.println("Printing artist tree:");

        binaryDumpHelper(artistTree.root, 0);

        System.out.println("Printing song tree:");

        binaryDumpHelper(songTree.root, 0);

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
     */
    private void binaryDumpHelper(Node<KVPair> root, int level)
    {
        if (root != null)
        {

            binaryDumpHelper(root.left(), level + 1);

            // Print out the node's data.
            if (level > 0)
            {
                System.out.printf("%" + level * 2 + "s%s\n", "",
                        root.value().toString());
            }
            else
            {
                System.out.println(root.value().toString());
            }
            binaryDumpHelper(root.right(), level + 1);
        }
    }

    public void listArtist(String artist)
    {
        Handle artistHandle = artistTable.find(artist);
        if (artistHandle == null)
        {
            System.out.println(
                    "|" + artist + "| does not exist in the Artist database.");
            return;
        }

        KVPair findMe = new KVPair(artistHandle, Handle.search);
        ArrayList<KVPair> list = artistTree.find(findMe);

        for (KVPair pair : list)
        {
            System.out.println("|" + pair.getString() + "|");
        }

        System.out.println("total songs: " + list.size());

    }

    public void listSong(String song)
    {
        Handle songHandle = artistTable.find(song);
        if (songHandle == null)
        {
            System.out.println(
                    "|" + song + "| does not exist in the Artist database.");
            return;
        }

        KVPair findMe = new KVPair(songHandle, Handle.search);
        ArrayList<KVPair> list = artistTree.find(findMe);

        for (KVPair pair : list)
        {
            System.out.println("|" + pair.getString() + "|");
        }

        System.out.println("total songs: " + list.size());
    }

}
