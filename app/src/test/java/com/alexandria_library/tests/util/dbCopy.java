package com.alexandria_library.tests.util;

import com.alexandria_library.application.Main;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

public class dbCopy {

    private File DB_SRC = new File("src/main/assets/db/LibraryDB.script");

    public File dbCopy() throws IOException {
        final File target = File.createTempFile("temp-db", ".script");
        Files.copy(DB_SRC.toPath(), target);
        Main.setDBPathName(target.getAbsolutePath().replace(".script", ""));
        return target;
    }
}
