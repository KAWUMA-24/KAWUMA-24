import java.util.Scanner;
public class example2 {
  public static void main(String[] args) {
    
   Scanner dk= new Scanner(System.in);

        System.out.println("Enter your name");
        String name= dk.nextLine();

        System.out.println("Enter your campus");
        String campus = dk.nextLine();

       

        System.out.println("Enter the your  course ");
        String course = dk.nextLine();

           System.out.println("Are a day, evening or weekend student ?");
            String time = dk.nextLine();




        System.out.println("name=" + name); 

        System.out.println("campus="+ campus);

         System.out.println("course=" + course); 

         System.out.println("time=" + time);

System.out.println(name  +" your a student at "+ campus +  " offering " +  course  + " during " +  time + "  please be a harding student ");

   dk.close();
  }  
}
