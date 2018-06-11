import java.util.Iterator;
import java.util.ArrayList;
/**
An interface for a dictionary with distinct search keys.
@ Bryan Mira
@5/11/2017
 */
public class Dictionary<K extends Comparable <? super K>>
{
    private ArrayList<K> keys;
    private ArrayList<String> values;
    private int sortType;

    public static final int ASCENDING_KEYS = 1;
    public static final int DESCENDING_VALUES = 2;

    public Dictionary()
    {
        keys = new ArrayList<K>();
        values = new ArrayList<String>();
        sortType = ASCENDING_KEYS;
    }

    /** Adds a new entry to this dictionary. If the given search
    key already exists in the dictionary, replaces the
    corresponding value.
    @param key an object search key of the new entry
    @param value an object associated with the search key
    @return either null if the new entry was added to the dictionary
    or the value that was associated with key if that value
    was replaced */
    public String add(K key, String value)
    {
        if (keys.contains(key))
        {
            int location = keys.indexOf(key); // where key is in the keys list
            String temp = values.get(location); // old value at location
            values.set(location, value); //replace
            return temp;
        }
        else
        {
            keys.add(key); // add new key/value pair
            values.add(value);
            return null;    
        }
    }

    /** Removes a specific entry from this dictionary.
    @param key an object search key of the entry to be removed
    @return either the value that was associated with the search key
    or null if no such object exists */
    public String remove(K key)
    {
        if (keys.contains(key)) // if key is in the dictionary
        {
            int location = keys.indexOf(key); // find the location
            String temp = values.get(location);
            keys.remove(location); // remove the key and value from dictionary
            values.remove(location);
            return temp;
        }
        //item not found
        return null;            
    }

    public int findLocation(K key, String value)
    {
        if (sortType == ASCENDING_KEYS){
            int k =0;
            while(k<keys.size() && keys.get(k).compareTo(key)<0) //as long as index has smaller value than k
                k++;
            return k;
        }

        else return keys.size(); // fix for descending value
    }

    /** Retrieves from this dictionary the value associated with a given
    search key.
    @param key an object search key of the entry to be retrieved
    @return either the value that is associated with the search key
    or null if no such object exists */
    public String getValue(K key)
    {
        if (keys.contains(key)){
            int location = keys.indexOf(key);
            String temp = values.get(location);
            return temp; }

        return null;
    }
    
    

    public K getKey(String value)
    {
        if (values.contains(value)){
            int location = values.indexOf(value);
            K temp = keys.get(location);
            return temp; }

        return null;
    }

    /** Sees whether a specific entry is in this dictionary.
    @param key an object search key of the desired entry
    @return true if key is associated with an entry in the
    dictionary */
    public boolean contains(K key)
    {
        if (keys.contains(key))
            return true; 
        else
            return false;
    }

    public boolean containsV(String value)
    {
        if (values.contains(value))
            return true; 
        else
            return false;
    }

    public int frequency(String value)
    {
        int size=0;
        for (int k=0; k<getSize(); k++)
        {
            if (values.get(k).equals(value))
                size++;
        }
        return size;
    }

    //returns the mostFrequent "family"
    public String mostFrequent()
    {
        int mostNum = 0;
        String mostNumValue = "";
        for (int k=0;k<getSize();k++)
        {
            String curValue = values.get(k);
            int curNum = frequency(curValue);
            if (mostNum < curNum)
            {
                mostNumValue = curValue;
                mostNum = curNum;
            }
        }
        return mostNumValue;
    }

    /** Creates an iterator that traverses all search keys in this
    dictionary.
    @return an iterator that provides sequential access to the search
    keys in the dictionary */
    public Iterator<K> getKeyIterator()
    {
        return keys.iterator();            
    }

    /** Creates an iterator that traverses all values in this dictionary.
    @return an iterator that provides sequential access to the values
    in this dictionary */
    public Iterator<String> getValueIterator()
    {
        return values.iterator();               
    }

    /** Sees whether this dictionary is empty.
    @return true if the dictionary is empty */
    public boolean isEmpty()
    {
        return keys.isEmpty();            
    }

    /** Gets the size of this dictionary.
    @return the number of entries (key-value pairs) currently
    in the dictionary */
    public int getSize()
    {
        return keys.size();            
    }

    /** Removes all entries from this dictionary. */
    public void clear()
    {
        keys.clear();
        values.clear();
    }
} // end DictionaryInterface