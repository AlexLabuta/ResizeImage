import java.io.IOException;

public class Main {
    private static final int SIZE = 300;

    public static void main(String[] args) throws IOException {
        String srcFolder = "C:\\Users\\Aleksandr\\Desktop\\image";
        String dstFolder = "C:\\Users\\Aleksandr\\Desktop\\image1";
        ManageThread.startThread(srcFolder, dstFolder, SIZE);
    }
}




