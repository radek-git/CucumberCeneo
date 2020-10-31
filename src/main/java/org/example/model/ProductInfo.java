package org.example.model;

import com.opencsv.bean.CsvBindByName;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;

@Getter
@Setter
//@AllArgsConstructor
@NoArgsConstructor
public class ProductInfo extends CsvBean {
    @CsvBindByName private String shop;
    @CsvBindByName private String name;
    @CsvBindByName private BigDecimal price;
    @CsvBindByName private LocalDate date;
    @CsvBindByName private MyTime time;

    public ProductInfo(String shop, String name, BigDecimal price, LocalDate date, MyTime time) {
        this.shop = shop;
        this.name = name;
        this.price = price;
        this.date = date;
        this.time = time;
    }

    @Override
    public String toString() {
        return "ProductInfo{" +
                "shop='" + shop + '\'' +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", date=" + date +
                ", time=" + time +
                '}';
    }
}
