
import java.util.Arrays;
import java.util.Collections;

public class descendarrynos {
    public static void main(String[] args) {
        


         Integer[] array= {5, 2, 8, 7, 1,5,9,40,90,66,45,43,23,67};


         System.out.println("original Array :"  +  Arrays.toString(array));
         Arrays.sort(array, Collections.reverseOrder());

        
         System.out.println("Array sorted in descending order: " + Arrays.toString(array));
       


    }
    
}
