package com.alexandria_library.tests.util;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;

import com.alexandria_library.application.Main;

public class TestUtils {
    private static final File DB_SRC = new File("src/main/assets/db/LibraryDB.script");

    public static File copyDB() throws IOException {
        final File target = File.createTempFile("temp-db", ".script");
        Files.copy(DB_SRC.toPath(), target.toPath(), StandardCopyOption.REPLACE_EXISTING);
        Main.setDBPathName(target.getAbsolutePath().replace(".script", ""));
        return target;
    }
}