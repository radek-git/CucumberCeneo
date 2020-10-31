package org.example.model;

import com.opencsv.bean.CsvBindByName;
import com.opencsv.bean.CsvCustomBindByName;
import lombok.*;
import org.example.fileUtils.CsvUtils;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Offer extends CsvBean {

    @CsvBindByName private String shopName;
    @CsvBindByName private String productName;
    @CsvBindByName private BigDecimal price;

    @CsvCustomBindByName(converter = LocalDateConverter.class)
    private LocalDate date;

    public Offer removePolish() {
        this.shopName = CsvUtils.removePolish(this.shopName);
        this.productName = CsvUtils.removePolish(this.productName);

        System.out.println(this.shopName);
        System.out.println(this.productName);
        return this;
    }
}
