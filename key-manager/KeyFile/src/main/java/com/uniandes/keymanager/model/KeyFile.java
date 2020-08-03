package com.uniandes.keymanager.model;

import java.io.Serializable;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

/**
 *
 * @author cuent
 */
@Entity
@Table(name = "keyFile")
public class KeyFile implements Serializable {
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    @Column(name="id")
    private String id;

    @Column(name="key_data")
    private String keyData;
    @Column(name="key_salt")
    private String keySalt;
    @Column(name="key_iv")
    private String keyIV;
    @Column(name="key_tipo")
    private String keyTipo;
    @Column(name="file_name")
    private String fileName;
    @Column(name="file_type")
    private String fileType;

    @Lob
    @Column(name="data")
    private byte[] data;

    /**
     *
     */
    public KeyFile() {
      super();
    }
    
    /**
     *
     * @param fileName
     * @param fileType
     * @param data
     */
    public KeyFile(String fileName, String fileType, byte[] data) {
        this.fileName = fileName;
        this.fileType = fileType;
        this.data = data;
    }

    /**
     *
     * @param fileName
     * @param fileType
     * @param data
     * @param keyData
     * @param keyTipo
     * @param keySalt
     * @param keyIV
     */
    public KeyFile(String fileName, String fileType, byte[] data, String keyData, String keyTipo, String keySalt, String keyIV) {
        this.fileName = fileName;
        this.fileType = fileType;
	this.keyTipo = keyTipo;
	this.keyData = keyData;
        this.keyIV = keyIV;
        this.keySalt = keySalt;
        this.data = data;
    } 

    /**
     *
     * @param fileName
     * @param fileType
     * @param data
     * @param keyData
     * @param keyTipo
     * @param keySalt
     */
    public KeyFile(String fileName, String fileType, byte[] data, String keyData, String keyTipo, String keySalt) {
        this.fileName = fileName;
        this.fileType = fileType;
	this.keyTipo = keyTipo;
	this.keyData = keyData;
        this.keySalt = keySalt;
        this.data = data;
    } 

    /**
     *
     * @param fileName
     * @param fileType
     * @param data
     * @param keyData
     * @param keyTipo
     */
    public KeyFile(String fileName, String fileType, byte[] data, String keyData, String keyTipo) {
        this.fileName = fileName;
        this.fileType = fileType;
	this.keyTipo = keyTipo;
	this.keyData = keyData;
        this.data = data;
    }

    /**
     *
     * @param keyData
     * @param keyTipo
     */
    public KeyFile(String keyData, String keyTipo) {
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
    public KeyFile(String keyData, String keyTipo, String keySalt, String keyIV) {
        this.keyTipo = keyTipo;
	this.keyData = keyData;
        this.keySalt = keySalt;
        this.keyIV = keyIV;
    }
    
    /**
     *
     * @param fileName
     * @param fileType
     * @param data
     * @param keyTipo
     */
    public KeyFile(String fileName, String fileType, byte[] data, String keyTipo) {
        this.fileName = fileName;
        this.fileType = fileType;
        this.keyTipo = keyTipo;
        this.data = data;
    }

    /**
     *
     * @return
     */
    public String getId() {
        return id;
    }

    /**
     *
     * @param id
     */
    public void setId(String id) {
        this.id = id;
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
    public byte[] getData() {
        return data;
    }

    /**
     *
     * @param data
     */
    public void setData(byte[] data) {
        this.data = data;
    }
	
    /**
     *
     * @return
     */
    public String getKeyTipo() {
        return keyTipo;
    }

    /**
     *
     * @param keyTipo
     */
    public void setKeyTipo(String keyTipo) {
        this.keyTipo = keyTipo;
    }
	
    /**
     *
     * @return
     */
    public String getKeyData() {
        return keyData;
    }

    /**
     *
     * @param keyData
     */
    public void setKeyData(String keyData) {
        this.keyData = keyData;
    }

    /**
     * @return the keySalt
     */
    public String getKeySalt() {
        return keySalt;
    }

    /**
     * @param keySalt the keySalt to set
     */
    public void setKeySalt(String keySalt) {
        this.keySalt = keySalt;
    }

    /**
     * @return the keyIV
     */
    public String getKeyIV() {
        return keyIV;
    }

    /**
     * @param keyIV the keyIV to set
     */
    public void setKeyIV(String keyIV) {
        this.keyIV = keyIV;
    }
    
	
}
