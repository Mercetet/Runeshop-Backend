package com.example.runeshop_ecommerce.services;

import com.example.runeshop_ecommerce.entities.Base;
import com.example.runeshop_ecommerce.exception.NotFoundException;
import com.example.runeshop_ecommerce.repositories.BaseRepository;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.List;

public abstract class BaseService<E extends Base, ID extends Serializable> {

    protected BaseRepository<E, ID> baseRepository;

    public BaseService(BaseRepository<E, ID> baseRepository) {
        this.baseRepository = baseRepository;
    }

    @Transactional
    public List<E> findAll() throws Exception {
        try {
            return baseRepository.findAll();
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    @Transactional
    public E findByID(ID id) {
        return baseRepository.findById(id).orElseThrow((() -> new NotFoundException("Id no encontrado")));
    }

    @Transactional
    public E create(E entity) throws Exception {
        try {
            return baseRepository.save(entity);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    @Transactional
    public E update(E data) throws Exception {
        try {
            return baseRepository.save(data);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    @Transactional
    public void logicDeletion(ID id) throws Exception{
        try {
            E entity = baseRepository.findById(id)
                    .orElseThrow(() -> new NotFoundException("Entidad no encontrada"));
            entity.setEstado(false);
            baseRepository.save(entity);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }
}
