package com.example.runeshop_ecommerce.utils;

import com.example.runeshop_ecommerce.entities.enums.Marca;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class MarcaConverter implements Converter<String, Marca> {
    @Override
    public Marca convert(String source) {
        try {
            return Marca.valueOf(source.toUpperCase());
        } catch (IllegalArgumentException e) {
            return null;
        }
    }
}
