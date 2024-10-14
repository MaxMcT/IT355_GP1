package rule_examples;

/**
 * ENV05-J: Do not deploy an application that can be remotely monitored
 * 
 * Author: Zach Contreras
 *
 * This class ensures that remote monitoring is disabled for security.
 */
public class ENV05 {
    public static void disableRemoteMonitoring() {
        // Code to disable remote monitoring features
        System.setProperty("com.sun.management.jmxremote", "false");
        System.setProperty("java.security.debug", "none");
    }
}
