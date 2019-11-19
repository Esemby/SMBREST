public class ServerTest {
    public static void main(String[] args) throws InterruptedException {
        SMBRESTServer server1 = new SMBRESTServer();
        server1.start();
        int counter = 0;
        while (true) {
            Thread.sleep(1000);
            counter++;
            if (counter%50 == 0) {
                System.out.println();
            }
            System.out.print(".");
        }
    }
}
