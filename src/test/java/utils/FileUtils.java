package utils;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.assertNotNull;

public class FileUtils {

    public static byte[] readBytesFromFile(String filePath) {
        File file = new File(filePath);
        try {
            return Files.readAllBytes(Paths.get(file.getAbsolutePath()));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new byte[]{};
    }

    public String readStringFromFile(String filePath) {
        return new String(readBytesFromFile(filePath));
    }

    public void saveFile(String content, String filePath)  {
        File file = new File(filePath);
        try {
            file.getParentFile().mkdirs();
            file.createNewFile();
            PrintWriter writer = new PrintWriter(file, "UTF-8");
            writer.write(content);
            writer.flush();
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void savePng(BufferedImage image, String imagePath) {
        try {
            File file = new File(imagePath);
            file.mkdirs();

            ImageIO.write(image, "PNG", file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static BufferedImage getImage(String imagePath) {
        try {
            return ImageIO.read(new File(imagePath));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static InputStream getInputStream(String filePath) {
        Path path = Paths.get(filePath);
        try {
            return  Files.newInputStream(path);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private String resourcePath(String file) {
        URL resource = Thread.currentThread().getContextClassLoader().getResource(file);
        assertNotNull(resource, "Resource not found in classpath: " + file);
        return resource.getFile();
    }

//    public static void getFileFromContainer(String exportFileName) {
//
//        String session = ((RemoteWebDriver) getWebDriver()).getSessionId().toString();
//        String path = SELENOID_URL + "download/" + session + "/" + exportFileName;
//
//        int retryCount = 1;
//        while (retryCount <= 10) {
//            try {
//                copyURLToFile(new URL(path), new File("build/downloads/" + exportFileName));
//                break;
//            } catch (IOException e) {
//                logger.error("Файл не найден. Попытка " + retryCount + " из 10");
//                sleep(1000);
//                retryCount++;
//            }
//        }
//    }
}
