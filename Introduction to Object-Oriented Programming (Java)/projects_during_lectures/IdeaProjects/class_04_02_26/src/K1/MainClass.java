package K1;

import java.util.Scanner;

public class MainClass {

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        //Size size;  // 0 1 2 3

        //size = Size.SMALL;
        //System.out.println(size.getAbbreviation());

        Scanner in = new Scanner(System.in);
        System.out.println("Enter a size:[SMALL,MEDIUM,LARGE,EXTRA_LARGE]");

        String input = in.next().toUpperCase();
        Size size = Enum.valueOf(Size.class, input);
        System.out.println("size="+size);
        System.out.println("enum ordinal: "+size.ordinal());
        System.out.println("abbreviation="+size.getAbbreviation());
        if(size == Size.EXTRA_LARGE) {
            System.out.println("Good job!");

        }

    }

}