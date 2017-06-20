import java.util.Arrays;

/**
 * Created by carlmccann2 on 07/12/2016.
 */
public class FullyAssociativeCache {
    private int totalSizeInKB,bytesPerCacheLine, lruCount = 0,currentLowestLRUValue = 0, currentLowestLRUIndex = 0;
    private int[][] cache;

    FullyAssociativeCache(int totalSizeInKB, int bytesPerCacheLine){
        this.totalSizeInKB = totalSizeInKB;
        this.bytesPerCacheLine = bytesPerCacheLine;
        //                                                      0 = tag, 1 = lru val, 2+ offsets
        cache = new int[(totalSizeInKB*1024)/bytesPerCacheLine][2 + bytesPerCacheLine]; // third field is an age field
        for (int i = 0; i < cache.length ; i++) {
            Arrays.fill(cache[i],-1);
        }
    }

    public boolean applyAddress(int address){
        String addressString = Conversion.integerToPaddedHex(address);
        int tag = Integer.parseInt(addressString.substring(0,7),16);
        int offset = Integer.parseInt(addressString.substring(7),16);
        int firstAvailableCacheLine = -1;

        for (int i = 0; i < cache.length; i++) {
            if(cache[i][0] == tag){
                // return cache[i][2+offset] in actual cache
                //check valid bit here

                // update lru
                cache[i][1] = lruCount;
                lruCount++;
                return true;
            }
            else{
                // find available slot, if needed
                if(firstAvailableCacheLine == -1){
                    if(cache[i][0] == -1){
                        firstAvailableCacheLine = i;
                    }
                }
                // no available slot found yet, so monitor LRU cacheLine
                if(firstAvailableCacheLine == -1){
                    if(cache[i][1] == currentLowestLRUValue){
                        currentLowestLRUIndex = i;
                    }
                }
            }
        }
        // there is an available space
        if(firstAvailableCacheLine != -1){
            cache[firstAvailableCacheLine][0] = tag;
            cache[firstAvailableCacheLine][1] = lruCount;  // insert lru val
            lruCount++;         // increase

            // this for loop simulates fetching from main memory with regard to spatial and temporal locality, although
            // i-1 is inserted this would ideally be what is actually stored at this address in main memory
            for (int i = 2; i < cache[firstAvailableCacheLine].length; i++) {
                cache[firstAvailableCacheLine][i] = i-2;
            }
        }
        // there isn't any space therefore an eviction occurs
        else {
            cache[currentLowestLRUIndex][0] = tag;
            cache[currentLowestLRUIndex][1] = lruCount;  // insert lru val
            // this for loop simulates fetching from main memory with regard to spatial and temporal locality, although
            // i-1 is inserted this would ideally be what is actually stored at this address in main memory
            for (int i = 2; i < cache[firstAvailableCacheLine].length; i++) {
                cache[firstAvailableCacheLine][i] = i-2;
            }            lruCount++;         // increase
            currentLowestLRUValue++;

        }
        return false;
    }

    public void printCacheLines(){
        System.out.println("\nCache Lines");
        for (int i = 0; i < cache.length ; i++) {
            System.out.println((i + 1) + ":\t" + Arrays.toString(cache[i]));
        }
    }

}
