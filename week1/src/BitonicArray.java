public class BitonicArray {
    public int findBitonicPointIndex(int [] array){
        int n = array.length;
        int low = 0;
        int high = n-1;
        while(low <= high){
            int mid = (high - low)/2 + low;
            if (array[mid] - array[mid-1] > 0 && array[mid] - array[mid+1] > 0){
                return mid;
            } else if (array[mid] - array[mid-1] > 0 && array[mid] - array[mid+1] < 0) {
                low = mid;
            } else if (array[mid] - array[mid-1] < 0 && array[mid] - array[mid+1] > 0) {
                high = mid;
            }
        }
        return -1;
    }
    public int binarySearchBitonic(int [] array, int bitonicPointIndex, int value){
        int low = 0;
        int high = bitonicPointIndex;
        while(low <= high){
            int mid = (high - low)/2 + low;
            if(array[mid] < value){
                low = mid + 1;
            } else if (array[mid] > value) {
                high = mid - 1;
            } else {
                return mid;
            }
        }
        return -1;
    }
    public int reverseBinarySearchBitonic(int [] array, int bitonicPointIndex, int value){
        int n = array.length;
        int high = bitonicPointIndex;
        int low = n - 1;
        while(low >= high){
            int mid = (low - high)/2 + high;
            if(array[mid] < value){
                low = mid - 1;
            } else if (array[mid] > value) {
                high = mid + 1;
            } else {
                return mid;
            }
        }
        return -1;
    }
    public static void main(String [] args){
        int [] a = {-3,-1, 9, 15, 18, 20, 17, 5, 1, -2};
        int value = 5;
        BitonicArray bitonicArray = new BitonicArray();
        int bitonicIndex = bitonicArray.findBitonicPointIndex(a);
        if (a[bitonicIndex] < value)
            System.out.println("The value is not in array");
        int binarySearchIndex = bitonicArray.binarySearchBitonic(a, bitonicIndex, value);
        if (binarySearchIndex != -1){
            System.out.println("Value is in index " + binarySearchIndex);
        }
        else {
            int reverseBinarySearchIndex = bitonicArray.reverseBinarySearchBitonic(a, bitonicIndex, value);
            if (reverseBinarySearchIndex != -1){
                System.out.println("Value is in index " + reverseBinarySearchIndex);
            }
            else {
                System.out.println("The value is not in array");
            }
        }
    }
}
