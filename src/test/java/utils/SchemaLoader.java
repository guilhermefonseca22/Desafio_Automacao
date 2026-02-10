package utils;

public class SchemaLoader {
    // Configura base onde esta os arquivos de schema JSON

    private static final String SCHEMAS_DIR = "schemas/";

    private SchemaLoader() { }

    public static String load(String fileName) {
        return ResourceReader.read(SCHEMAS_DIR + fileName);
    }
}
