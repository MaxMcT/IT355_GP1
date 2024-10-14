package rule_examples;
// Evan Rogers
/**
 * DCL59-J. Do not apply public final to constants whose value might change in later releases
 */
public class DCL59 {
    private static int version = 1; // version will change over time, don't make it final
    public static final int getVersion() {
    return version;
    }
    /**
     * Example test case of getting the public static final version data
     * @param args None
     */
    public static void main(String[] args) {
        System.out.println(getVersion());
    }
}
