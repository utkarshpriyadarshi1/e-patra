package com.updevlogics.service;

@Service
public class BackupProgressHandler extends TextWebSocketHandler {

    private WebSocketSession session;

    @Override
    public void afterConnectionEstablished(WebSocketSession session) {
        this.session = session;
    }

    public void sendProgress(String message) {
        if (session != null && session.isOpen()) {
            try {
                session.sendMessage(new TextMessage(message));
            } catch (IOException ignored) {}
        }
    }

    public String createBackup(String sourceFolder) {
        try {
            progressHandler.sendProgress("Backup started...");

            String backupPath = backupFolder + "backup_" + System.currentTimeMillis();
            Path source = Paths.get(sourceFolder);
            Path destination = Paths.get(backupPath);

            Files.createDirectories(destination);
            copyFolder(source, destination);

            BackupRecord record = new BackupRecord(null, backupPath, LocalDateTime.now(), "SUCCESS");
            backupRepository.save(record);

            progressHandler.sendProgress("Backup completed successfully.");
            return "Backup created at: " + backupPath;
        } catch (Exception e) {
            progressHandler.sendProgress("Backup failed: " + e.getMessage());
            return "Backup failed: " + e.getMessage();
        }
    }

}
@PostMapping("/create")
public String createBackup(@RequestBody Map<String, String> request) {
    String folderPath = request.get("folderPath");
    return backupService.createBackup(folderPath);
}
