package com.updevlogics.service;

import com.updevlogics.model.FileInfo;
import com.updevlogics.repository.FileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;

@Service
public class FileService {
    @Autowired
    private FileRepository fileRepository;
    private final FileMetadataRepository fileMetadataRepository;

    public void saveFile(MultipartFile multipartFile, String description, String category, String subCategory) throws IOException {
        String fileName = multipartFile.getOriginalFilename();
        String filePath = "uploads/" + fileName;
        File file = new File(filePath);
        multipartFile.transferTo(file);

        FileInfo fileInfo = new FileInfo();
        fileInfo.setFileName(fileName);
        fileInfo.setFilePath(filePath);
        fileInfo.setFileType(multipartFile.getContentType());
        fileInfo.setFileSize(multipartFile.getSize());
        fileInfo.setDescription(description);
        fileInfo.setCategory(category);
        fileInfo.setSubCategory(subCategory);
        fileInfo.setUploadDate(new Date());

        fileRepository.saveFile(fileInfo);
    }

    public List<FileInfo> getAllFiles() {
        return fileRepository.getAllFiles();
    }

    public List<FileInfo> searchFiles(String query, String fileType, String role, Date dateFrom, Date dateTo, String category, String subCategory) {
        return fileRepository.searchFiles(query, fileType, role, dateFrom, dateTo, category, subCategory);
    }

    public List<FileInfo> getLast10Files() {
        return fileRepository.getLast10Files();
    }

    public String storeFile(String filePath) throws Exception {
        Path source = Paths.get(filePath);
        String fileType = Files.probeContentType(source);
        String year = String.valueOf(Files.getLastModifiedTime(source).toInstant().atZone(java.time.ZoneId.systemDefault()).getYear());
        String month = String.format("%02d", Files.getLastModifiedTime(source).toInstant().atZone(java.time.ZoneId.systemDefault()).getMonthValue());

        String newPath = "organized/" + fileType + "/" + year + "/" + month + "/" + source.getFileName();
        Path destination = Paths.get(newPath);

        Files.createDirectories(destination.getParent());
        Files.copy(source, destination, StandardCopyOption.REPLACE_EXISTING);

        String hash = generateFileHash(source);
        Optional<FileMetadata> existingFile = fileMetadataRepository.findByHash(hash);
        if (existingFile.isPresent()) {
            Files.delete(destination); // Remove duplicate
            return "Duplicate file detected and removed.";
        }

        FileMetadata metadata = new FileMetadata(null, filePath, newPath, fileType, year, month, Files.size(source), hash);
        fileMetadataRepository.save(metadata);

        return "File stored at: " + newPath;
    }

    private String generateFileHash(Path path) throws Exception {
        MessageDigest digest = MessageDigest.getInstance("SHA-256");
        byte[] fileBytes = Files.readAllBytes(path);
        byte[] hash = digest.digest(fileBytes);
        StringBuilder hexString = new StringBuilder();
        for (byte b : hash) {
            hexString.append(String.format("%02x", b));
        }
        return hexString.toString();
    }

}