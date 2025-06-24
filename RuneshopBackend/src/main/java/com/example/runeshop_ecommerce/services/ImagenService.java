package com.example.runeshop_ecommerce.services;

import com.example.runeshop_ecommerce.entities.Imagen;
import com.example.runeshop_ecommerce.repositories.ImagenRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;
import java.io.IOException;
import java.util.Map;

@Service
public class ImagenService extends BaseService<Imagen, Long> {

    private final CloudinaryService cloudinaryService;
    private final ImagenRepository imagenRepository;

    public ImagenService(ImagenRepository imagenRepository, CloudinaryService cloudinaryService) {
        super(imagenRepository);
        this.imagenRepository = imagenRepository;
        this.cloudinaryService = cloudinaryService;
    }

    @Transactional
    public List<Imagen> subirImagen(List<MultipartFile> files) throws IOException {
        List<Imagen> imagenes = new ArrayList<>();
        for (MultipartFile file : files) {
            Map<String, Object> resultado = cloudinaryService.upload(file);
            String url = resultado.get("url").toString();

            Imagen imagen = Imagen.builder()
                    .nombre(file.getOriginalFilename())
                    .imagenUrl(url)
                    .build();
            imagenRepository.save(imagen);
            imagenes.add(imagen);
        }
        return imagenes;
    }

    @Transactional
    public Imagen actualizarImagen(MultipartFile file) throws IOException {
        Map<String, Object> resultado = cloudinaryService.upload(file);
        String url = resultado.get("url").toString();

        Imagen imagen = Imagen.builder()
                .nombre(file.getOriginalFilename())
                .imagenUrl(url)
                .build();

        return imagenRepository.save(imagen);
    }

    @Transactional
    public void borrarImagen(Long imagenId) throws Exception {
        Imagen imagen = imagenRepository.findById(imagenId)
                .orElseThrow(() -> new Exception("Imagen no encontrada"));

        cloudinaryService.delete(imagen.getImagenUrl());
        imagenRepository.deleteById(imagenId);
    }

}
