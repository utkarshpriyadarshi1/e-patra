package com.updevlogics.service;

@Service
@RequiredArgsConstructor
public class BackupService {


    private final BackupRepository backupRepository;

    @Value("${app.backup-folder}")
    private String backupFolder;

    public String createBackup() {
        try {
            String backupPath = backupFolder + "backup_" + System.currentTimeMillis();
            Path source = Paths.get("organized/");
            Path destination = Paths.get(backupPath);

            Files.createDirectories(destination);
            copyFolder(source, destination);

            BackupRecord record = new BackupRecord(null, backupPath, LocalDateTime.now(), "SUCCESS");
            backupRepository.save(record);

            return "Backup created at: " + backupPath;
        } catch (Exception e) {
            return "Backup failed: " + e.getMessage();
        }
    }

    private void copyFolder(Path source, Path destination) throws Exception {
        Files.walk(source).forEach(file -> {
            try {
                Path target = destination.resolve(source.relativize(file));
                if (Files.isDirectory(file)) {
                    Files.createDirectories(target);
                } else {
                    Files.copy(file, target, StandardCopyOption.REPLACE_EXISTING);
                }
            } catch (Exception ignored) {}
        });
    }

}
