package rule_examples;

/**
 * ONJ01-J Limit accessibility of fields
 * The field is declared private and is only accessed by an accessor method
 */
public class OBJ01 {

    private int field;

    public int getField(){
        return field;
    }

    private void setField(int var){ //direct manipulations are done within the class with private methods
        field = var;
    }
}
