package org.example.fileUtils;

import com.opencsv.CSVWriter;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import com.opencsv.bean.StatefulBeanToCsv;
import com.opencsv.bean.StatefulBeanToCsvBuilder;
import com.opencsv.exceptions.CsvDataTypeMismatchException;
import com.opencsv.exceptions.CsvRequiredFieldEmptyException;
import org.example.model.CsvBean;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class CsvUtils {

    public static <T extends CsvBean> void saveToFile(String filename, Class<T> clazz, List<T> list) {
        List<T> previouslySaved = readFromFile(filename, clazz);
        list.addAll(0, previouslySaved);

        try (Writer writer = new FileWriter(filename)) {
            StatefulBeanToCsv<T> sbc = new StatefulBeanToCsvBuilder<T>(writer)
                    .withSeparator(CSVWriter.DEFAULT_SEPARATOR)
                    .build();

            sbc.write(list);

        } catch (IOException | CsvRequiredFieldEmptyException | CsvDataTypeMismatchException e) {
            e.printStackTrace();
        }
    }

    public static <T extends CsvBean> List<T> readFromFile(String fileName, Class<T> clazz) {
        try (Reader reader = Files.newBufferedReader(Paths.get(fileName))) {
            CsvToBean<T> cb = new CsvToBeanBuilder(reader)
                    .withType(clazz)
                    .build();

            return cb.parse();

        } catch (NoSuchFileException e) {
            return new ArrayList<>();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    private static final Map<String, String> polishCharacters = Map.of(
            "Ą", "A",
            "ą", "a",
            "Ó", "O",
            "ó", "o"
    );

    public static String removePolish(String str) {
        return str.replaceAll("[^a-zA-Z0-9]", "");
    }

}
