package module2.maintask.binarysearchpattern;

public interface MainClass {
    default int binarySeacrh(int[] values,int low, int higher, int searchVal){
        if(higher >= low){
            int middle = low + (higher - low) / 2;
            if (searchVal == values[middle]){
                return middle;
            }

            if (searchVal < values[middle])
                return binarySeacrh(values,low,middle-1, searchVal);

            return  binarySeacrh(values,middle+1,higher, searchVal);
        }
        return -1;
    }
    //implementar el strategy pattern usando una clase para cada tipo de sort y despues de haber
    //ordenado los elementos usar un binary search para encontrar in elemento
    public int chooseSort(int[] values, int searchVal);
}
