package org.example;

import com.opencsv.CSVWriter;
import com.opencsv.bean.StatefulBeanToCsv;
import com.opencsv.bean.StatefulBeanToCsvBuilder;
import com.opencsv.exceptions.CsvDataTypeMismatchException;
import com.opencsv.exceptions.CsvRequiredFieldEmptyException;
import org.example.model.MyTime;
import org.example.model.ProductInfo;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.math.BigDecimal;
import java.time.LocalDate;

public class Main {
    public static void main(String[] args) throws IOException, CsvDataTypeMismatchException, CsvRequiredFieldEmptyException {
        ProductInfo productInfo = new ProductInfo("MediaMarkt", "Kabel HDMI 2.0 1234", new BigDecimal("10.50"), LocalDate.now(), MyTime.now());

        Writer writer = new FileWriter("dupa.csv");
        StatefulBeanToCsv<ProductInfo> sbc = new StatefulBeanToCsvBuilder<ProductInfo>(writer)
                .withSeparator(CSVWriter.DEFAULT_SEPARATOR)
                .build();

        sbc.write(productInfo);
        writer.close();
    }
}
