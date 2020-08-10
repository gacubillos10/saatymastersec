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


namespace SaatyWeb.Models
{
    public class DescargarDocumentoController : ApiController
    {

        [HttpPost]
        public async Task<IHttpActionResult> Descargar()
        {
            string azure_ContainerName = "documentos";
            string filetoDownload = "text2.txt";
            Console.WriteLine("Inside downloadfromBlob()");

            string storageAccount_connectionString = "DefaultEndpointsProtocol=https;AccountName=saaty;AccountKey=DcLy046L4noFRbsMt6qDkgQ6YekrAr8qF5ANZVRRhkkGt0TyO2jeMOBMiHfqAnW0c6AVoA+eO45lS+rdWKbpeg==;EndpointSuffix=core.windows.net";

            CloudStorageAccount mycloudStorageAccount = CloudStorageAccount.Parse(storageAccount_connectionString);
            CloudBlobClient blobClient = mycloudStorageAccount.CreateCloudBlobClient();

            CloudBlobContainer container = blobClient.GetContainerReference(azure_ContainerName);
            CloudBlockBlob cloudBlockBlob = container.GetBlockBlobReference(filetoDownload);

            // provide the file download location below            
            string url = @"C:\Users\wolfa\source\repos\SaatyWeb\SaatyWeb\App_Data\" + filetoDownload;
            Stream file = File.OpenWrite(url);    
          

             cloudBlockBlob.DownloadToStream(file);

            file.Close();
            decode64(url);

            return Ok("Download completed!");
        }
        public void decode64(string pathOrg)
        {
            string contenido = "";

            string text = System.IO.File.ReadAllText(pathOrg);

            string[] lines = System.IO.File.ReadAllLines(pathOrg);


            foreach (string line in lines)
            {
                contenido = contenido + line;
            }


            Byte[] bytes = Convert.FromBase64String(text);
            File.WriteAllBytes(@"C:\Users\wolfa\source\repos\SaatyWeb\SaatyWeb\App_Data\DecodeKey2.png", bytes);
        }

    }
}
