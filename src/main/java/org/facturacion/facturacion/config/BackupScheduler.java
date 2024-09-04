package org.facturacion.facturacion.config;

import lombok.AllArgsConstructor;
import org.facturacion.facturacion.services.implementation.DatabaseBackupService;
import org.facturacion.facturacion.services.implementation.GithubUploadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.time.LocalDate;

@Service
@AllArgsConstructor
public class BackupScheduler {

    private DatabaseBackupService databaseBackupService;

    private GithubUploadService githubUploadService;

    @Scheduled(fixedRate = 120000)  // Cada 3 horas
    public void backupAndUpload() throws IOException {
        String directoryPath = "";

        // Exportar todas las tablas
        databaseBackupService.exportAllTablesToCsv(directoryPath);

        // Subir a GitHub
        String repoName = "AariazP/facturacion-backup";
        String commitMessage = "Backup de todas las tablas" + LocalDate.now();
        githubUploadService.uploadFilesToRepo(repoName, directoryPath, commitMessage);
    }
}
