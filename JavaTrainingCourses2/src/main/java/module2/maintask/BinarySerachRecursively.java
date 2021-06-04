package module2.maintask;

import java.util.Arrays;

public class BinarySerachRecursively {
    public int recursiveBinarySearch(int[] values,int low, int higher, int searchVal) {
        if(higher >= low){
            int middle = low + (higher - low) / 2;
            if (searchVal == values[middle]){
                return middle;
            }

            if (searchVal < values[middle])
                return recursiveBinarySearch(values,low,middle-1, searchVal);

            return  recursiveBinarySearch(values,middle+1,higher, searchVal);
        }

        return -1;
    }

    public int binarySearch(int[] values, int searchVal) {
        int low = 0;
        int high = values.length - 1;
        int index = -1;
        boolean isFound = true;
        int mid;
        while (isFound){
            if (high < low){
                return -1;
            }

            mid = low + (high - low) / 2;

            if (searchVal > values[mid]){
                low = mid + 1;
            }
            if (searchVal < values[mid])
            {
                high = mid-1;
            }

            if (searchVal == values[mid]){
                isFound = false;
                return mid;
            }
        }

        return index;
    }


}
