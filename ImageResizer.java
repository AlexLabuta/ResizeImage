import org.imgscalr.Scalr;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.stream.Collectors;

class ImageResizer implements Runnable {
    private final String srcFolder;
    private final String dstFolder;
    private final int size;
    private final Queue<Path> queue = new ConcurrentLinkedQueue<>();

    public ImageResizer(String srcFolder, String dstFolder, int size) throws IOException {
        this.srcFolder = srcFolder;
        this.dstFolder = dstFolder;
        this.size = size;
        queue.addAll(Files.list(Paths.get(srcFolder)).filter(file -> !Files.isDirectory(file)).collect(Collectors.toList()));
    }

    @Override
    public void run() {
        for (Path currentFile : queue) {
            currentFile = queue.poll();
            if (currentFile == null) break;
            try {
                long start = System.currentTimeMillis();
                BufferedImage image = ImageIO.read(currentFile.toFile());
                BufferedImage resizeImage = Scalr.resize(image, size);
                File newFile = new File(String.valueOf(Path.of(dstFolder).resolve(Path.of(srcFolder).relativize(currentFile))));
                ImageIO.write(resizeImage, "jpg", newFile);
                System.out.println("Image " + currentFile.getFileName() + " resized successfully in " + (System.currentTimeMillis() - start) + " ms.");
            } catch (IOException ex) {
                System.out.println("Can't resize file: " + currentFile.getFileName());
            }
        }
    }
}




