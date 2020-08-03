package com.uniandes.keymanager.payload;

/**
 *
 * @author cuent
 */
public class UploadFileResponse implements java.io.Serializable {
    private String fileName;
    private String fileDownloadUri;
    private String fileType;
    private long size;
    private String keyData;
    private String keyTipo;
    private String keySalt;
    private String keyIV;

    /**
     *
     * @param fileName
     * @param fileDownloadUri
     * @param fileType
     * @param size
     */
    public UploadFileResponse(String fileName, String fileDownloadUri, String fileType, long size) {
        this.fileName = fileName;
        this.fileDownloadUri = fileDownloadUri;
        this.fileType = fileType;
        this.size = size;
    }

    /**
     *
     * @param fileName
     * @param fileDownloadUri
     * @param fileType
     * @param size
     * @param keyData
     * @param keyTipo
     */
    public UploadFileResponse(String fileName, String fileDownloadUri, String fileType, long size, String keyData, String keyTipo) {
        this.fileName = fileName;
        this.fileDownloadUri = fileDownloadUri;
        this.fileType = fileType;
        this.size = size;
	this.keyTipo = keyTipo;
	this.keyData = keyData;
        
    }

    /**
     *
     * @param keyData
     * @param keyTipo
     */
    public UploadFileResponse(String keyData, String keyTipo) {
       this.keyTipo = keyTipo;
       this.keyData = keyData;
    }
    
    /**
     *
     * @param keyData
     * @param keyTipo
     * @param keySalt
     * @param keyIV
     */
    public UploadFileResponse(String keyData, String keyTipo, String keySalt, String keyIV) {
       this.keyTipo = keyTipo;
       this.keyData = keyData;
       this.keyIV = keyIV;
       this.keySalt = keySalt;
    }

    /**
     *
     * @param fileName
     * @param fileDownloadUri
     * @param fileType
     * @param size
     * @param keyTipo
     */
    public UploadFileResponse(String fileName, String fileDownloadUri, String fileType, long size, String keyTipo) {
        this.fileName = fileName;
        this.fileDownloadUri = fileDownloadUri;
        this.fileType = fileType;
        this.size = size;
        this.keyTipo = keyTipo;
    }

    /**
     *
     * @return
     */
    public String getFileName() {
        return fileName;
    }

    /**
     *
     * @param fileName
     */
    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    /**
     *
     * @return
     */
    public String getFileDownloadUri() {
        return fileDownloadUri;
    }

    /**
     *
     * @param fileDownloadUri
     */
    public void setFileDownloadUri(String fileDownloadUri) {
        this.fileDownloadUri = fileDownloadUri;
    }

    /**
     *
     * @return
     */
    public String getFileType() {
        return fileType;
    }

    /**
     *
     * @param fileType
     */
    public void setFileType(String fileType) {
        this.fileType = fileType;
    }

    /**
     *
     * @return
     */
    public long getSize() {
        return size;
    }

    /**
     *
     * @param size
     */
    public void setSize(long size) {
        this.size = size;
    }
}
