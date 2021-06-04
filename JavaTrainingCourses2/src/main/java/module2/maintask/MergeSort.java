package module2.maintask;

import java.util.Arrays;
import java.util.Collections;

public class MergeSort {

    public static int[] margeSort(int arr[]){
        if (arr.length == 1)
            return arr;

        int mid = (int)(arr.length) / 2;
        int[] arrAux1 = Arrays.copyOfRange(arr,0,mid);
        int[] arrAuc2 = Arrays.copyOfRange(arr,mid,arr.length);
        arrAux1 = MergeSort.margeSort(arrAux1);
        arrAuc2 = MergeSort.margeSort(arrAuc2);

        return merge(arrAux1,arrAuc2);
    }

    private static int[] merge(int[] arrAux1, int[] arrAuc2) {
        int j = 0;
        int i = 0;
        int k =0;
        int[] arrAux3 = new int[arrAux1.length + arrAuc2.length];
        while (j < arrAux1.length && i < arrAuc2.length){
            if(arrAux1[j] < arrAuc2[i]){
                arrAux3[k] = arrAux1[j];
                j++;
            }else {
                arrAux3[k] = arrAuc2[i];
                i++;
            }
            k++;
        }

        while (j<arrAux1.length){
            arrAux3[k] = arrAux1[j];
            j++;
            k++;
        }

        while (i < arrAuc2.length){
            arrAux3[k] = arrAuc2[i];
            i++;
            k++;
        }

        return arrAux3;
    }

}
