package utils;

import java.util.UUID;

public class PayloadLoader {
    // diretorio base onde carrega o payload

    private static final String PAYLOADS_DIR = "payloads/";

    private PayloadLoader() { }

    public static String load(String fileName) {
        return ResourceReader.read(PAYLOADS_DIR + fileName);
    }


}
