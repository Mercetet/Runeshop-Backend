package com.example.runeshop_ecommerce.utils;


import com.example.runeshop_ecommerce.entities.enums.TipoProducto;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class TipoProductoConverter implements Converter<String, TipoProducto> {
    @Override
    public TipoProducto convert(String source) {
        try {
            return TipoProducto.valueOf(source.toUpperCase());
        } catch (IllegalArgumentException e) {
            return null;
        }
    }
}
