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
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import javax.ws.rs.Consumes;
import javax.ws.rs.Path;
import javax.ws.rs.GET;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

/**
 *
 * @author cuent
 */
@Path("/keyservice")
@Component
public class KeyFileControllers {
    
    private static final Logger logger = LoggerFactory.getLogger(KeyFileControllers.class);

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
    @POST
    @Path("/uploadKeyText")
    @Produces(MediaType.APPLICATION_JSON_VALUE)
    @Consumes(MediaType.TEXT_PLAIN_VALUE)
    public UploadFileResponse uploadKeyText(@FormParam("keyData") String keyData, @FormParam("keyTipo") String keyTipo, @FormParam("keySalt") String keySalt, @FormParam("keyIV") String keyIV) {
        KeyFile dbFile = dbFileStorageService.storeKey(keyData, keyTipo,keySalt, keyIV);
        return new UploadFileResponse(keyData, keyTipo,keySalt, keyIV);
    }
    
    /**
     *
     * @param file
     * @param keyTipo
     * @return
     */
    @POST
    @Path("/uploadKey")
    @Produces(MediaType.APPLICATION_JSON_VALUE)
    @Consumes(MediaType.MULTIPART_FORM_DATA_VALUE)
    public UploadFileResponse uploadKey(@FormParam("file") MultipartFile file,@FormParam("keyTipo") String keyTipo) {
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
    @POST
    @Path("/uploadMultipleKeyFiles")
    @Produces(MediaType.TEXT_PLAIN_VALUE)
    @Consumes(MediaType.MULTIPART_FORM_DATA_VALUE)
    public List<UploadFileResponse> uploadMultipleKeyFiles(@FormParam("files") MultipartFile[] files, @FormParam("keyTipo") String keyTipo) {
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
     @GET
    @Path("/downloadKeyFile/{fileId}")
     @Produces(MediaType.APPLICATION_OCTET_STREAM_VALUE)
     @Consumes(MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public ResponseEntity<Resource> downloadKeyFile(@PathParam("fileId") String fileId) {
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
    @GET
    @Path("/downloadKeyFileData/{fileId}")
     @Produces(MediaType.APPLICATION_JSON_VALUE)
     @Consumes(MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public ResponseEntity<DataKey>  downloadKeyFileData(@PathParam("fileId") String fileId) {
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
