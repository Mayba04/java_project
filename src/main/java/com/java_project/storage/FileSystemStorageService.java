package com.java_project.storage;

import net.coobird.thumbnailator.Thumbnails;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Base64;
import java.util.UUID;

@Service
public class FileSystemStorageService implements StorageService {
    private final Path rootLocation;
     private final RestTemplate restTemplate;

    public FileSystemStorageService(StorageProperties properties, RestTemplate restTemplate) {
        this.rootLocation = Paths.get(properties.getLocation());
        this.restTemplate = restTemplate;
    }

    @Override
    public void init() throws IOException {
        if(!Files.exists(rootLocation))
            Files.createDirectory(rootLocation);
    }


    @Override
    public String SaveImage(MultipartFile file, FileSaveFormat format) throws IOException {
        String ext = format.name().toLowerCase();
        String randomFileName = UUID.randomUUID().toString()+"."+ext;
        int [] sizes = {32,150,300,600,1200};
        var bufferedImage = ImageIO.read(new ByteArrayInputStream(file.getBytes()));
        for (var size : sizes) {
            String fileSave = rootLocation.toString()+"/"+size+"_"+randomFileName;
            Thumbnails.of(bufferedImage).size(size, size).outputFormat(ext).toFile(fileSave);
        }
        return randomFileName;
    }

    @Override
    public void delete(String filepath) throws IOException {
        int[] sizes = {32, 150, 300, 600, 1200};
        for (int size : sizes) {
            Path filePath = Paths.get(rootLocation.toString(), size + "_" + filepath);
            Files.deleteIfExists(filePath);
        }
    }

    
    @Override
    public void deleteImage(String fileName) throws IOException {
        Path filePath = rootLocation.resolve(fileName);
        int[] sizes = {32, 150, 300, 600, 1200};
        for (var size : sizes) {
            Path fileToDelete = filePath.resolveSibling(size + "_" + fileName);
            Files.deleteIfExists(fileToDelete);
        }
    }

    @Override
    public String saveImageFromUrl(String imageUrl, FileSaveFormat format) {
        try {
            byte[] originalImageBytes = restTemplate.getForObject(imageUrl, byte[].class);
    
            if (originalImageBytes == null || originalImageBytes.length == 0) {
                // Обробка помилки, якщо не вдалося отримати зображення
                return "Failed to fetch original image from URL";
            }
    
            String ext = format.name().toLowerCase();
            String randomFileName = UUID.randomUUID().toString() + "." + ext;
            int[] sizes = {32, 150, 300, 600, 1200};
    
            ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(originalImageBytes);
            var bufferedImage = ImageIO.read(byteArrayInputStream);
    
            for (var size : sizes) {
                String fileSave = rootLocation.toString() + "/" + size + "_" + randomFileName;
                Thumbnails.of(bufferedImage).size(size, size).outputFormat(ext).toFile(fileSave);
            }
    
            return randomFileName;
        } catch (IOException e) {
            return "Failed to store image: " + e.getMessage();
        }
    }

    @Override
    public String SaveImageBase64(String base64, FileSaveFormat format) {
        try {
            String ext = format.name().toLowerCase();
            String randomFileName = UUID.randomUUID().toString() + "."+ext;
            int [] sizes = {32,150,300,600,1200};

            var bytes = Base64.getDecoder().decode(base64);

            BufferedImage bufferedImage = ImageIO.read(new ByteArrayInputStream(bytes));
            for(int size: sizes) {
                String fileSave = rootLocation.toString()+"/"+size+"_"+randomFileName;
                Thumbnails.of(bufferedImage)
                        .size(size, size)
                        .outputFormat(ext)
                        .toFile(fileSave);
            }
            return randomFileName;
        } catch (IOException ex) {
            System.out.println("Помилка кодування файлу "+ ex.getMessage());
            return null;
        }
    }


}