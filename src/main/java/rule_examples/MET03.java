package rule_examples;

/**
 * Methods that perform a security check must be declared private or final
 * the method doing the sensitive operation is made final, it could also be made private instead
 */
public class MET03 {
    public final void sensitveOperation(){
        //do security operations
    }
}
