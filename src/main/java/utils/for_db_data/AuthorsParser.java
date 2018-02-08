package utils.for_db_data;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;

/**
 * Created by Dmitriy Yurkin on 10.01.2018.
 */
public class AuthorsParser {
    /**
     * Получаем список всех авторов книг в папке
     * @param folderPath
     */
    private static void getAuthorsInFolder(String folderPath) {
        File directory = new File(folderPath);
        if (!directory.exists() || !directory.isDirectory()) throw new IllegalArgumentException();
        ArrayList<File> list = new ArrayList<>();
        File[] files = directory.listFiles(pathname -> pathname.getName().endsWith(""));
        Collections.addAll(list, files);
        ArrayList<String> authors = new ArrayList<>();
        list.forEach(file -> authors.add(file.getName()));
    }
}