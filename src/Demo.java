import java.util.Scanner;

class Demo {
    public static long reverse(long num) {
        long rv = 0L;

        while (num != 0) {
            rv = (rv * 10) + (num % 10);
            num /= 10;
        }
        return rv;
    }
        public static void main (String[]args){
            Scanner input = new Scanner(System.in);
            if (input.hasNextLong()) {
                long num = input.nextLong();
                System.out.println(Demo.reverse(num));
            }
            input.close();
        }
    }
