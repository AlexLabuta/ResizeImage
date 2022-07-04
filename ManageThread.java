import java.io.IOException;

public abstract class ManageThread {
    private static final int CORE = Runtime.getRuntime().availableProcessors();

    public static void startThread(String srcFolder, String dstFolder, int size) throws IOException {
        ImageResizer resizer = new ImageResizer(srcFolder, dstFolder, size);

        for (int i = 0; i < CORE; i++) {
            new Thread(resizer).start();
        }
    }
}
