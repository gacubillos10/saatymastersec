'use strict';

var singleUploadForm = document.querySelector('#singleUploadForm');
var singleKeyForm = document.querySelector('#singleKeyForm');
var singleFileUploadInput = document.querySelector('#singleFileUploadInput');
var singlekeyTipoInput = document.querySelector('#singlekeyTipo');
var skeyTipo = document.querySelector('#skeyTipo');
var skeyData = document.querySelector('#skeyData');
var skeySalt = document.querySelector('#skeySalt');
var skeyIV = document.querySelector('#skeyIV');
var singleFileUploadError = document.querySelector('#singleFileUploadError');
var singleFileUploadSuccess = document.querySelector('#singleFileUploadSuccess');
var singleKeyUploadError = document.querySelector('#singleKeyUploadError');
var singleKeyUploadSuccess = document.querySelector('#singleKeyUploadSuccess');

var multipleUploadForm = document.querySelector('#multipleUploadForm');
var multipleFilekeyTipoInput = document.querySelector('#multipleFilekeyTipo1');
var multipleFileUploadInput = document.querySelector('#multipleFileUploadInput');
var multipleFileUploadError = document.querySelector('#multipleFileUploadError');
var multipleFileUploadSuccess = document.querySelector('#multipleFileUploadSuccess');


function uploadSingleKey(keyData,keyTipo, keySalt, keyIV) {
    var formData = new FormData();
    formData.append("keyData", keyData);
    formData.append("keyTipo", keyTipo);
    formData.append("keySalt", keySalt);
    formData.append("keyIV", keyIV);

    var xhr = new XMLHttpRequest();
    xhr.open("POST", "/uploadKeyText");

    xhr.onload = function() {
        console.log(xhr.responseText);
        var response = JSON.parse(xhr.responseText);
        if(xhr.status == 200) {
            singleKeyUploadError.style.display = "none";
            singleKeyUploadSuccess.innerHTML = "<p>Clave cargada exitosamente.</p>";
            singleKeyUploadSuccess.style.display = "block";
        } else {
            singleKeyUploadSuccess.style.display = "none";
            singleKeyUploadError.innerHTML = (response && response.message) || "Se produjo un error";
        }
    }

    xhr.send(formData);
}

function uploadSingleFile(file,keyTipo) {
    var formData = new FormData();
    formData.append("file", file);
    formData.append("keyTipo", keyTipo);
     

    var xhr = new XMLHttpRequest();
    xhr.open("POST", "/uploadKey");

    xhr.onload = function() {
        console.log(xhr.responseText);
        var response = JSON.parse(xhr.responseText);
        if(xhr.status == 200) {
            singleFileUploadError.style.display = "none";
            singleFileUploadSuccess.innerHTML = "<p>Archivo cargado exitosamente.</p><p>DownloadUrl : <a href='" + response.fileDownloadUri + "' target='_blank'>" + response.fileDownloadUri + "</a></p>";
            singleFileUploadSuccess.style.display = "block";
        } else {
            singleFileUploadSuccess.style.display = "none";
            singleFileUploadError.innerHTML = (response && response.message) || "Se produjo un error";
        }
    }

    xhr.send(formData);
}

function uploadMultipleFiles(files,keyTipo1) {
    var formData = new FormData();
    for(var index = 0; index < files.length; index++) {
        formData.append("files", files[index]);
    }
    
    formData.append("keyTipo", keyTipo1);


    var xhr = new XMLHttpRequest();
    xhr.open("POST", "/uploadMultipleKeyFiles");

    xhr.onload = function() {
        console.log(xhr.responseText);
        var response = JSON.parse(xhr.responseText);
        if(xhr.status == 200) {
            multipleFileUploadError.style.display = "none";
            var content = "<p>Todos los archivos cargados correctamente</p>";
            for(var i = 0; i < response.length; i++) {
                content += "<p>DownloadUrl : <a href='" + response[i].fileDownloadUri + "' target='_blank'>" + response[i].fileDownloadUri + "</a></p>";
            }
            multipleFileUploadSuccess.innerHTML = content;
            multipleFileUploadSuccess.style.display = "block";
        } else {
            multipleFileUploadSuccess.style.display = "none";
            multipleFileUploadError.innerHTML = (response && response.message) || "Se produjo un error";
        }
    }

    xhr.send(formData);
}


singleKeyForm.addEventListener('submit', function(event){
    var keydata = skeyData.value;
    var keytipo = skeyTipo.value;
    var keySalt = skeySalt.value;
    var keyIV = skeyIV.value;
    
    if(keydata === null || keytipo=== null || keySalt=== null || keyIV=== null) {
        singleKeyUploadError.innerHTML = "Por favor llene el formulario";
        singleKeyUploadError.style.display = "block";
    }
    
    uploadSingleKey(keydata.replace(/(\r\n|\n|\r)/gm,""),keytipo,keySalt.replace(/(\r\n|\n|\r)/gm,""),keyIV.replace(/(\r\n|\n|\r)/gm,""));
    event.preventDefault();
}, true);


singleUploadForm.addEventListener('submit', function(event){
    var files = singleFileUploadInput.files;
    var tipo = singlekeyTipoInput.value;
    if(files.length === 0) {
        singleFileUploadError.innerHTML = "Por favor seleccione un archivo";
        singleFileUploadError.style.display = "block";
    }
    
    if(tipo=== null) {
        singleFileUploadError.innerHTML = "Por favor llene el formulario";
        singleFileUploadError.style.display = "block";
    }
    
    uploadSingleFile(files[0],tipo);
    event.preventDefault();
}, true);


multipleUploadForm.addEventListener('submit', function(event){
    var files = multipleFileUploadInput.files;
     var tipo = multipleFilekeyTipoInput.value;
    if(files.length === 0) {
        multipleFileUploadError.innerHTML = "Seleccione al menos un archivo";
        multipleFileUploadError.style.display = "block";
    }
    
     if(tipo=== null) {
        multipleFileUploadError.innerHTML = "Por favor llene el formulario";
        multipleFileUploadError.style.display = "block";
    }
    
    uploadMultipleFiles(files,tipo);
    event.preventDefault();
}, true);

