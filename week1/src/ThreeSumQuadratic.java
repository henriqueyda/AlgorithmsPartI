import java.util.Arrays;

public class ThreeSumQuadratic {
    public int threeSum(int [] a){
        Arrays.sort(a);
        int count = 0;
        int n = a.length;
        for(int i = 0; i < n-1; i++){
            int l = i + 1;
            int r = n - 1;
            while(l != r) {
                if(-a[l] - a[r] > a[i]){
                    l++;
                } else if (-a[l] - a[r] < a[i]) {
                    r--;
                }
                else {
                    System.out.println("(" + a[i] + ", " + a[l] + ", " + a[r] + ")");
                    count++;
                    r--;
                }
            }
        }
        return count;
    }
    public static void main(String [] args){
        int [] a = {-2, 6, 1, 2, -1, -4, 0};
        ThreeSumQuadratic threeSumQuadratic = new ThreeSumQuadratic();
        System.out.println(threeSumQuadratic.threeSum(a));
    }
}
