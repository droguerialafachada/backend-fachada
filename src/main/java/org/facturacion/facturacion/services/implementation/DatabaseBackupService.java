package org.facturacion.facturacion.services.implementation;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class DatabaseBackupService {

    private final JdbcTemplate jdbcTemplate;

    public void exportAllTablesToCsv(String directoryPath) throws IOException {
        // Crear el directorio si no existe
        Path dirPath = Paths.get(directoryPath);
        Files.createDirectories(dirPath);

        List<String> tableNames = getTableNames();

        for (String tableName : tableNames) {
            String filePath = directoryPath + "/" + tableName + ".csv";
            exportTableToCsv(tableName, filePath);
        }
    }

    private List<String> getTableNames() {
        return jdbcTemplate.queryForList("SHOW TABLES", String.class);
    }

    private void exportTableToCsv(String tableName, String filePath) throws IOException {
        String query = "SELECT * FROM " + tableName;

        List<Map<String, Object>> rows = jdbcTemplate.queryForList(query);

        Path path = Paths.get(filePath);
        Files.createDirectories(path.getParent()); // Crear el directorio si no existe

        try (BufferedWriter writer = Files.newBufferedWriter(path)) {
            if (rows.isEmpty()) {
                System.out.println("La tabla " + tableName + " está vacía, no se creará archivo.");
                return;
            }

            String headers = String.join(",", rows.get(0).keySet());
            writer.write(headers);
            writer.newLine();

            for (Map<String, Object> row : rows) {
                String rowData = row.values().stream()
                        .map(String::valueOf)
                        .collect(Collectors.joining(","));
                writer.write(rowData);
                writer.newLine();
            }
        }
    }
}
