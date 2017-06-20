import java.util.Arrays;

/**
 * Created by C12508463 on 10/11/2016.
 */

/* Notes from class

   BG
   32 bit long mem address. cache is 64kb, 16 bytes per line

   low order bits:

   64kb = 2^16
   64 = 2^6
   32 = 2^5
   16 = 2^4


   As part of your submission you should include a sample list of memory addresses,
   explaining why your program reported a cache hit or miss in each case.
*/


public class DirectMappedCache {
    private int totalSizeInKB, bytesPerCacheLine, noOfIndexDigitsHex;
    private int[][] cache;

    public DirectMappedCache(int totalSizeInKB, int bytesPerCacheLine){
        // your implementation here
        this.totalSizeInKB = totalSizeInKB;
        this.bytesPerCacheLine = bytesPerCacheLine;

        // total size in kb = 64 and bytes per cache line = 16 i.e 4096 cache lines
        cache = new int[(totalSizeInKB*1024) / bytesPerCacheLine][bytesPerCacheLine+1];
        for (int i = 0; i < cache.length; i++) {
            Arrays.fill(cache[i],-1);
        }

        // index bits = log_base_2(number_of_cache_lines)
        // Max value of one hex digit is 4 binary: F = 1111
        // so div by 4 to get hex value

        noOfIndexDigitsHex = (int)Math.round(Math.log(cache.length) / Math.log(2)) / 4;
    }

    public boolean applyAddress(int address){
        String addressString = Conversion.integerToPaddedHex(address);

        int tag = Integer.parseInt(addressString.substring(0,7- noOfIndexDigitsHex),16);
        int index = Integer.parseInt(addressString.substring(7- noOfIndexDigitsHex,7),16);
        int offset = Integer.parseInt(addressString.substring(7),16);

        // index is cache line
        // tag is used to verify correct information
        //if it is the correct information, get the offset

        if(cache[index][0] == tag){
            // return cache[index][1+offset] in actual cache
            // [*][] is the cache line
            // [][*] 0 = tag, the rest equals offsets
            return true;
        }
        else{
            cache[index][0] = tag;

            // this for loop simulates fetching from main memory with regard to spatial and temporal locality, although
            // i-1 is inserted this would ideally be what is actually stored at this address in main memory
            for (int i = 1; i < cache[index].length; i++) {
                cache[index][i] = i-1;
            }
            return false;
        }
    }

    public void printCacheLines(){
        System.out.println("\nCache Lines");
        for (int i = 0; i < cache.length ; i++) {
            System.out.println((i + 1) + ":\t" + Arrays.toString(cache[i]));
        }
    }

    public int[][] getCache() {
        return cache;
    }

}
