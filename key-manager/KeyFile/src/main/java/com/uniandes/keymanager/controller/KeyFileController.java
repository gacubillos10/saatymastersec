package com.uniandes.keymanager.controller;

import com.uniandes.keymanager.model.DataKey;
import com.uniandes.keymanager.model.KeyFile;
import com.uniandes.keymanager.payload.UploadFileResponse;
import com.uniandes.keymanager.service.KeyFileStorageService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 *
 * @author cuent
 */
@RestController()
@Configuration
@EnableSwagger2
public class KeyFileController {
    
    /**
     *
     * @return
     */
    @Bean
    public Docket api() { 
        return new Docket(DocumentationType.SWAGGER_2)  
          .select()                                  
          .apis(RequestHandlerSelectors.basePackage("com.uniandes.keymanager.controller"))              
          .paths(PathSelectors.any())                          
          .build();                                           
    }

    private static final Logger logger = LoggerFactory.getLogger(KeyFileController.class);

    @Autowired
    private KeyFileStorageService dbFileStorageService;
    
    
    /**
     *
     * @param keyData
     * @param keyTipo
     * @param keySalt
     * @param keyIV
     * @return
     */
    @PostMapping(name="/uploadKeyText", consumes = MediaType.TEXT_PLAIN_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public UploadFileResponse uploadKeyText(@RequestParam("keyData") String keyData, @RequestParam("keyTipo") String keyTipo, @RequestParam("keySalt") String keySalt, @RequestParam("keyIV") String keyIV) {
        KeyFile dbFile = dbFileStorageService.storeKey(keyData, keyTipo,keySalt, keyIV);
        return new UploadFileResponse(keyData, keyTipo,keySalt, keyIV);
    }
    
    /**
     *
     * @param file
     * @param keyTipo
     * @return
     */
    @PostMapping(name="/uploadKey", consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public UploadFileResponse uploadKey(@RequestParam("file") MultipartFile file,@RequestParam("keyTipo") String keyTipo) {
        KeyFile dbFile = dbFileStorageService.storeFile(file,keyTipo);

        String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/downloadKeyFile/")
                .path(dbFile.getId())
                .toUriString();

        UploadFileResponse up= new UploadFileResponse(dbFile.getFileName(), fileDownloadUri,
                file.getContentType(), file.getSize(), keyTipo);
        
        return up;
    }

    /**
     *
     * @param files
     * @param keyTipo
     * @return
     */
    @PostMapping(name="/uploadMultipleKeyFiles", consumes = MediaType.MULTIPART_FORM_DATA_VALUE , produces = MediaType.TEXT_PLAIN_VALUE)
    public List<UploadFileResponse> uploadMultipleKeyFiles(@RequestParam("files") MultipartFile[] files, @RequestParam("keyTipo") String keyTipo) {
        List<UploadFileResponse> list = Arrays.asList(files)
                .stream()
                .map(file -> uploadKey(file,keyTipo))
                .collect(Collectors.toList());
        
        return list;
    }

    /**
     *
     * @param fileId
     * @return
     */
    @GetMapping(name="/downloadKeyFile/{fileId}" ,consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE, produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
    public ResponseEntity<Resource> downloadKeyFile(@PathVariable String fileId) {
        // Load file from database
        KeyFile dbFile = dbFileStorageService.getFile(fileId);

        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(dbFile.getFileType()))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + dbFile.getFileName() + "\"")
                .body(new ByteArrayResource(dbFile.getData()));
    }
    
    /**
     *
     * @param fileId
     * @return
     */
    @GetMapping(name="/downloadKeyFileData/{fileId}" ,consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<DataKey>  downloadKeyFileData(@PathVariable String fileId) {
        // Load file from database 
        KeyFile dbFile = dbFileStorageService.getFile(fileId);
        DataKey data = new DataKey();
        if(dbFile!=null){
        data.setKeyTipo(dbFile.getKeyTipo());
        data.setKeyData(dbFile.getKeyData());
        data.setKeyIV(dbFile.getKeyIV());
        data.setKeySalt(dbFile.getKeySalt());
        }
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.setContentType(MediaType.APPLICATION_JSON);
        return new ResponseEntity<DataKey>(data, responseHeaders, HttpStatus.CREATED);
                
    }

}
