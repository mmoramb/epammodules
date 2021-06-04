package module2.maintask;

import java.util.Arrays;

public class InsertionsSort {
    public int[] useInsertionSort(int[] values) {
        if (values.length == 1){
            return values;
        }else if (values.length>1){
            int sorter = 0;
            for (int i=0; i<values.length -1; i++){
                if (values[i+1] < values[i]){
                    int aux = values[i];
                    values[i] = values[i+1];
                    values[i+1] = aux;
                    sorter = i;
                    if(sorter > 0){
                        for (int j=sorter; j>0; j--){
                            if (values[j] < values[j-1]){
                                aux = values[j];
                                values[j] = values[j-1];
                                values[j-1] = aux;
                            }
                        }
                    }
                }else {
                    sorter++;
                }
            }
        }
        return values;
    }
}
