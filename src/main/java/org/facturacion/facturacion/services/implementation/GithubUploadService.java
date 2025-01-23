package org.facturacion.facturacion.services.implementation;
import org.kohsuke.github.GHContent;
import org.kohsuke.github.GHRepository;
import org.kohsuke.github.GitHub;
import org.kohsuke.github.GitHubBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
public class GithubUploadService {

    private final GitHub github;

    @Autowired
    public GithubUploadService(@Value("${GITHUB-TOKEN}") String githubToken) throws IOException {
        this.github = new GitHubBuilder().withOAuthToken(githubToken).build();
    }

    public void uploadFilesToRepo(String repoName, String directoryPath, String commitMessage) throws IOException {
        GHRepository repository = github.getRepository(repoName);
        Path dirPath = Paths.get(directoryPath);

        Files.walk(dirPath)
                .filter(Files::isRegularFile)
                .forEach(file -> {
                    try {
                        String content = Files.readString(file);
                        String relativePath = dirPath.relativize(file).toString();

                        GHContent existingContent = null;
                        try {
                            existingContent = repository.getFileContent(relativePath);
                        } catch (IOException e) {
                            // El archivo no existe, por lo que será creado
                        }

                        if (existingContent != null) {
                            // Actualizar archivo existente
                            existingContent.update(content, commitMessage);
                        } else {
                            // Crear un nuevo archivo
                            repository.createContent()
                                    .path(relativePath)
                                    .content(content)
                                    .message(commitMessage)
                                    .commit();
                        }
                    } catch (IOException e) {
                        e.printStackTrace(); // Manejo de errores
                    }
                });
    }
}