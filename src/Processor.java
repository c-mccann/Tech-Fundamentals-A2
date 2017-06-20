import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by carlmccann2 on 05/12/2016.
 */
public class Processor {
    List<Integer> addresses;

    Processor(){
        String fileToOpen = "src/res/addresses.csv";
        addresses = csvReader(fileToOpen);
    }

    private List<Integer> csvReader(String fileToOpen){
        System.out.println("Reading file \"" + fileToOpen + "\"...");
        List<Integer> nums = new ArrayList<>();
        try{
            BufferedReader br = new BufferedReader(new FileReader(fileToOpen));
            String currentLine;
            while((currentLine = br.readLine()) != null){
                    nums.add(Integer.parseInt(currentLine));
                }
            System.out.println("CSV file read succesfully!\n");
            return nums;

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("CSV file not found.\n");
        return null;
    }

    public List<Integer> getAddresses() {
        return addresses;
    }
}