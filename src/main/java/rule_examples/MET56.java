package rule_examples;

/**
 * Do not use Object.equals to compare cryptographic keys
 * add key specific implementations
 */
public class MET56 {

    myKey key1 = new myKey(123);
    myKey key2 = new myKey(123);

    private static boolean equalKeys(myKey key1, myKey key2){

        //Do not do if(key.equals(key2), it wont work
        //instead
        if (key1.hash == key2.hash){
            return true;
        }
        return false;
    }

}

class myKey{
    int hash;

    public myKey(int hash){
        this.hash = hash;
    }
}


