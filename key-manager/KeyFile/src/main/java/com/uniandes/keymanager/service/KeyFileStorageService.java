package com.uniandes.keymanager.service;

import com.uniandes.keymanager.exception.FileStorageException;
import com.uniandes.keymanager.exception.MyFileNotFoundException;
import com.uniandes.keymanager.model.KeyFile;
import com.uniandes.keymanager.repository.KeyFileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;

/**
 *
 * @author cuent
 */
@Service
public class KeyFileStorageService {

    @Autowired
    private KeyFileRepository KeyFileRepository;

    /**
     *
     * @param file
     * @param keyTipo
     * @return
     */
    public KeyFile storeFile(MultipartFile file, String keyTipo) {
        // Normalize file name
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());

        try {
            // Check if the file's name contains invalid characters
            if(fileName.contains("..")) {
                throw new FileStorageException("¡Lo siento! El nombre del archivo contiene una secuencia de ruta incorrecta " + fileName);
            }

            KeyFile KeyFile = new KeyFile(fileName, file.getContentType(), file.getBytes(),keyTipo);

            return KeyFileRepository.save(KeyFile);
        } catch (IOException ex) {
            throw new FileStorageException("No se pudo almacenar el archivo " + fileName + ". Intenta de nuevo!", ex);
        }
    }
    
    /**
     *
     * @param keyData
     * @param keyTipo
     * @param keySalt
     * @param keyIV
     * @return
     */
    public KeyFile storeKey( String keyData, String keyTipo, String keySalt, String keyIV) {
          try {
         
            KeyFile KeyFile = new KeyFile(keyData,keyTipo,keySalt, keyIV);

            return KeyFileRepository.save(KeyFile);
        } catch (Exception ex) {
            throw new FileStorageException("No se pudo almacenar la llave " + keyData + ". Intenta de nuevo!", ex);
        }
    }

    /**
     *
     * @param fileId
     * @return
     */
    public KeyFile getFile(String fileId) {
        return KeyFileRepository.findById(fileId)
                .orElseThrow(() -> new MyFileNotFoundException("Archivo no encontrado con id " + fileId));
    }
}
