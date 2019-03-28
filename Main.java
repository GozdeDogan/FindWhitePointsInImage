import java.io.IOException;

public class Main {

    public static void main(String[] args) {
        System.out.println("Test Part1(Question1):");
        String path = "test_file_1.txt";

        FindWhitePointsInImage test_q1 = new FindWhitePointsInImage(path);
        try {
            test_q1.doOperation();
        } catch (IOException e) {
            e.printStackTrace();
        }

        path = "test_file_2.txt";

        test_q1 = new FindWhitePointsInImage(path);
        try {
            test_q1.doOperation();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
