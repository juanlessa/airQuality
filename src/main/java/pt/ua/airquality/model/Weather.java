package pt.ua.airquality.model;

import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@ToString
public class Weather {
    private String cityName;
    private BigDecimal aqi;
    private BigDecimal pm10;
    private BigDecimal co;
    private BigDecimal o3;
    private BigDecimal so2;
    private BigDecimal no2;
    private BigDecimal pm25;
}
