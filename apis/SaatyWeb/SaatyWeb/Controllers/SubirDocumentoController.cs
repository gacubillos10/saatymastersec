using SaatyWeb.Models;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Net;
using System.Net.Http;
using System.Threading.Tasks;
using System.Web.Http;


using Microsoft.Azure;
using Microsoft.WindowsAzure.Storage;
using Microsoft.WindowsAzure.Storage.Blob;
using Azure.Storage;
using Azure.Storage.Blobs;
using System.IO;
using AmazonS3Demo;
using Cifrado;

namespace SaatyWeb.Controllers
{
    public class SubirDocumentoController : ApiController
    {

        [HttpPost]
        public async Task<IHttpActionResult> Core(SubirDocumentoDto Documento)
        {
            Stream fileStream = null;
            //Cifrado objCifrar = new Cifrado();

            string text = Documento.base64;
            int mitad = Convert.ToInt32(Math.Ceiling(Convert.ToDouble(text.Length / 2)));
            string mitad1 = text.Substring(0, mitad);
            string mitad2 = text.Substring(mitad, text.Length - mitad);



            string abc = mitad1;



            Uri blobUri = new Uri("https://saaty.blob.core.windows.net/documentos/" + Documento.fileName);

            StorageSharedKeyCredential storageCredentials =
                new StorageSharedKeyCredential("saaty", "Bo2msi0DOXRHhflJgkxQafPu+6A9xl0zcdDS6ka9o+kC3UZvhe0vAUrK5c23beHTLPvMEOkfHALPgmr/LdRD7g==");

            // Create the blob client.
            BlobClient blobClient = new BlobClient(blobUri, storageCredentials);


            using (MemoryStream ms = new MemoryStream(System.Text.Encoding.ASCII.GetBytes(abc)))
            {
                await blobClient.UploadAsync(ms);
            }

            AmazonS3Uploader amazonS3 = new AmazonS3Uploader();

            amazonS3.UploadFile(mitad2, Documento.fileName);



            return Ok("Exito");
        }

    }
}
