package com.project1.service;

import java.util.List;

import com.project1.dto.LensesDTO;
import com.project1.exception.LensNotFoundException;

public interface ILensesService {

    List<LensesDTO> getAllLenses();

    LensesDTO getLensById(String lensId) throws LensNotFoundException;

    LensesDTO addLens(LensesDTO lensDTO) throws LensNotFoundException;

    boolean deleteLens(String lensId) throws LensNotFoundException;

    LensesDTO updateLens(LensesDTO lensDTO) throws LensNotFoundException;
   
    void reduceStock(String lensId, int quantity);
    void increaseStock(String lensId, int quantity);
}
