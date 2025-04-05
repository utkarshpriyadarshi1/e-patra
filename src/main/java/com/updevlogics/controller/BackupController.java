package com.updevlogics.controller;

@RestController
@RequestMapping("/api/backup")
public class BackupController {
    private final BackupService backupService;

    @PostMapping("/create")
    public String createBackup() {
        return backupService.createBackup();
    }

}
