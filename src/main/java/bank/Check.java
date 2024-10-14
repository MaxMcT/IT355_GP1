package bank;

import org.w3c.dom.Document;
import org.xml.sax.SAXException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.FileReader;
import java.io.IOException;

/**
 * This class represents a check that can be read in from a xml file
 */
public class Check {
    private String to, from, amount;

    /**
     *
     * @param filename
     * @return
     */
    public static String readFile(String filename){
        StringBuilder stringBuilder = new StringBuilder();
        try (FileReader reader = new FileReader(filename)) {
            int character;

            while ((character = reader.read()) != -1) {
                  if ((character < Integer.MIN_VALUE) || (character > Integer.MAX_VALUE)) {
                    throw new ArithmeticException("Integer overflow");
                }
                stringBuilder.append((char)character); // Processing the char and printing the output of the file
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        System.out.println(stringBuilder.toString());
        return stringBuilder.toString();
    }

    /**
     * This method reads a check in from a xml file
     * @param filename name of the file
     * @throws SAXException There was an error parsing your check
     * @throws IOException There was an error reading in the check
     * @throws ParserConfigurationException There was an error parsing your check
     */
    public void readCheck(String filename) throws SAXException, IOException, ParserConfigurationException {
        String fileContents = readFile(filename);
        XMLVerifier xmlVerifier = new XMLVerifier();
        boolean parser = xmlVerifier.validateXML(fileContents);
        DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
        Document document = documentBuilder.parse(filename);
        this.to = document.getElementsByTagName("to").item(0).getTextContent();
        this.from = document.getElementsByTagName("from").item(0).getTextContent();
        this.amount = document.getElementsByTagName("amount").item(0).getTextContent();
    }

    /**
     * gets the amount on the check
     * @return amount
     */
    public String getAmount() {
        return amount;
    }

    /**
     * gets the person sending the check
     * @return from
     */
    public String getFrom() {
        return from;
    }

    /**
     * gets the person recieving the check
     * @return to
     */
    public String getTo() {
        return to;
    }
}
