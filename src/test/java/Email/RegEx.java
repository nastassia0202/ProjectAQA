package Email;

import java.util.Scanner;
//1   --  "(^#[A-F0-9]{6})$"
//2   --
//3
//4
//5
//6


public class RegEx {

    public static void main(String[] args) {
        String email = null;
        Boolean email_valid = false;
        String password = null;
        Boolean password_valid = false;

        Scanner input = new Scanner(System.in);
        do {
            System.out.println("Enter your email: ");
            email = input.nextLine();

            System.out.println("Enter your password: ");
            password = input.nextLine();

            if (email.matches("^(?=.{1,64}@)[A-z0-9_-]+(\\.[A-z0-9_-]+)*@" +"[^-][A-z0-9-]+(\\.[A-z0-9-]+)*(\\.[A-z]{2,3})$"))
                email_valid = true;
            else
                email_valid = false;


            if ((password.matches(".*[^\\w\\s].*")) && (password.matches(".*[a-zA-Z].*")) &&
                    (password.matches(".*[0-9].*")) && (password.length() >= 8))
                password_valid = true;
            else
                password_valid = false;

            if (password_valid && email_valid)
                System.out.println("Welcome User!!");
            else {
                if (!email_valid)
                    System.out.println("Re-enter your email: ");
                if (!password_valid)
                    System.out.println("Re-enter your password: ");
            }

        } while (!email_valid || !password_valid);

        input.close();
    }
}
