package utils.for_db_data;

import java.io.File;
import java.io.FileFilter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by Dmitriy Yurkin on 11.01.2018.
 */
public class BooksParser {
    private static List<File> getFilesInFolder(String folderPath) {
        File directory = new File(folderPath);
        if (!directory.exists() || !directory.isDirectory()) throw new IllegalArgumentException();
        ArrayList<File> book_refs = new ArrayList<>();
        //File[] files = directory.listFiles(pathname -> pathname.getName().endsWith(".txt"));
        File[] files = directory.listFiles(new FileFilter() {
            @Override
            public boolean accept(File pathname) {
                return pathname.getName().endsWith(".txt");
            }
        });
        Collections.addAll(book_refs, files);

        //Получаем список всех названий книг
        File[] subDirrectories = directory.listFiles(File::isDirectory);
        for (File folder: subDirrectories){
            addFiles(book_refs, folder);
        }
        ArrayList<String> books = new ArrayList<>();
        book_refs.forEach(book -> books.add(book.getName().
                                            substring(0, book.getName().indexOf(".txt"))));
        System.out.println(books);

        //Получаем список всех ссылок
        System.out.println(book_refs);

        return book_refs;
    }

    private static void addFiles(List<File> collection, File folder){
        File[] subFiles = folder.listFiles(pathname -> pathname.getName().endsWith(".txt"));
        Collections.addAll(collection, subFiles);
        File[] subDirrectories = folder.listFiles(pathname -> pathname.isDirectory());
        for (File dir : subDirrectories){
            addFiles(collection, dir);
        }
    }

    public static void main(String[] args) {
        getFilesInFolder("C:\\books\\Юмор");
    }
}