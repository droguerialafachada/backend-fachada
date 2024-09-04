package org.facturacion.facturacion.services.implementation;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class DatabaseBackupService {

    private JdbcTemplate jdbcTemplate;

    public void exportAllTablesToCsv(String directoryPath) throws IOException {
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

        try (BufferedWriter writer = Files.newBufferedWriter(Paths.get(filePath))) {
            if (!rows.isEmpty()) {
                String headers = String.join(",", rows.get(0).keySet());
                writer.write(headers);
                writer.newLine();
            }

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
