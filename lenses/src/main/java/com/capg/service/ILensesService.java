package com.capg.service;

import com.capg.dto.LensesDTO;

import com.capg.exception.LensNotFoundException;


import java.util.List;

public interface ILensesService {

    List<LensesDTO> getAllLenses();

    LensesDTO getLensById(String lensId) throws LensNotFoundException;

    LensesDTO addLens(LensesDTO lensDTO) throws LensNotFoundException;

    boolean deleteLens(String lensId) throws LensNotFoundException;

    LensesDTO updateLens(LensesDTO lensDTO) throws LensNotFoundException;
   
    void reduceStock(String lensId, int quantity);
    void increaseStock(String lensId, int quantity);
}
