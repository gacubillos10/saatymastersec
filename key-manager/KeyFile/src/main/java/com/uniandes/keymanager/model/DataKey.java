/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.uniandes.keymanager.model;

import java.io.Serializable;

/**
 *
 * @author cuent
 */
public class DataKey implements Serializable {
    private String keyData;
    private  String keyTipo;
    private String keySalt;
    private String keyIV;
    
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
