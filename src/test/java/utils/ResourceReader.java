package utils;

import java.io.InputStream;
import java.nio.charset.StandardCharsets;

public class ResourceReader {

    private ResourceReader() { }
    // tratamento de leitura de arquivos
    public static String read(String resourcePath) {
        try (InputStream inputStream =
                     ResourceReader.class.getClassLoader().getResourceAsStream(resourcePath)) {

            if (inputStream == null) {
                throw new RuntimeException("Arquivo não encontrado: " + resourcePath);
            }

            return new String(inputStream.readAllBytes(), StandardCharsets.UTF_8);

        } catch (Exception e) {
            throw new RuntimeException("Falha ao ler o recurso: " + resourcePath, e);
        }
    }
}
