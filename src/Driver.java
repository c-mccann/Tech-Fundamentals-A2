import java.util.Scanner;

/**
 * Created by carlmccann2 on 06/12/2016.
 */
public class Driver {

    public static void main(String[] args){
        DirectMappedCache directMappedCache = new DirectMappedCache(64,16);
        FullyAssociativeCache fullyAssociativeCache = new FullyAssociativeCache(64,16);
        Processor processor = new Processor();

        int directMiss =0, directHit = 0, assocMiss = 0, assocHit = 0;


        System.out.println("Direct Mapped Cache\n");
        for (Integer i : processor.getAddresses()) {
            System.out.print(i + ":\t");
            if(directMappedCache.applyAddress(i)){
                directHit++;
                System.out.println("hit");

            }
            else{
                directMiss++;
                System.out.println("miss");
            }
        }

        System.out.println("Hits:\t" + directHit);
        System.out.println("Misses:\t" + directMiss);
        System.out.println("ratio:\t" + (double)directHit/(directHit + directMiss));

        Scanner scanner = new Scanner(System.in);
        System.out.println("\nEnter Y to see cache lines:\t");
        if(scanner.nextLine().toUpperCase().equals("Y")) directMappedCache.printCacheLines();

        System.out.println("\n\nPress Return to see the Fully Associative Cache's output\n");
        scanner.nextLine();

        System.out.println("-----------------------------------------------------------------------------------------");
        System.out.println("-----------------------------------------------------------------------------------------");

        System.out.println("\nFully Associative Cache\n");

        for (Integer i : processor.getAddresses()) {
            System.out.print(i + ":\t");
            if(fullyAssociativeCache.applyAddress(i)){
                assocHit++;
                System.out.println("hit");
            }
            else{
                assocMiss++;
                System.out.println("miss");
            }
        }

        System.out.println("Hits:\t" + assocHit);
        System.out.println("Misses:\t" + assocMiss);
        System.out.println("ratio:\t" + (double)assocHit/(assocHit + assocMiss));

        System.out.println("\nEnter Y to see cache lines:\t");
        if(scanner.nextLine().toUpperCase().equals("Y")) fullyAssociativeCache.printCacheLines();
    }

}
