package rule_examples;
//MET55-J & METT54-J & EXP52-J Jamie Morrone
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

class EmptyListTest
{
    private List<String> list;

    /**
     * Initializing the list
     */
    public EmptyListTest()
    {
        list = new ArrayList();
    }

    /**
     * Returns list or null if it is empty
     * @return list
     */
    public List returnListBad()
    {
        //if and else contain no brackets
        if(list.isEmpty())
            return null;
        else
            return list;
    }

    /**
     * Returns list or and empty list the list is empty
     * @return list
     */
    public List returnListGood()
    {
        //Even with one statement, brackets included with if and else
        if(list.isEmpty())
        {
            //Instead of null return an empty list
            return Collections.EMPTY_LIST;
        }
        else
        {
            return list;
        }
    }

    /**
     * Adds item to list but does not provide and feedback if item was added succesfully or not
     * @param item
     */
    public void addBad(String item)
    {
        if(item != null)
            list.add(item);
    }

    /**
     * Adds item and if the item is not added returns false, otherwise true
     * @param item
     * @return boolean
     */
    public boolean addGood(String item)
    {
        if(item == null)
        {
            //Check if the item is empty and return false to show item was not added
            return false;
        }
        list.add(item);
        return true;
    }

    public static void main(String[] args) 
    {
        EmptyListTest test = new EmptyListTest();
        //Prints null
        System.out.println(test.returnListBad());
        //Prints empty list
        System.out.println(test.returnListGood());
    }
}
