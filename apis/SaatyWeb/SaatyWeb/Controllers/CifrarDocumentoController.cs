using System;
using System.IO;
using System.Net;
using System.Net.Http;
using System.Threading.Tasks;
using System.Web;
using System.Web.Http;
using Newtonsoft.Json;

using Microsoft.Azure;
using Microsoft.WindowsAzure.Storage;
using Microsoft.WindowsAzure.Storage.Blob;
using Azure.Storage;
using Azure.Storage.Blobs;

namespace SaatyWeb.Controllers
{
    public class CifrarDocumentoController : ApiController
    {

        [HttpPost]
        public async Task<IHttpActionResult> Cifrar()
        {

            string url = "http://ec2-54-85-108-117.compute-1.amazonaws.com:8080/downloadKeyFileData/2d643c87-1c46-4453-9b8a-e4cff92fab55";
            string usr = "userUniandes";
            string pass = "holamundo123.";

            Stream fileStream = null;


                WebClient client = new WebClient();

                NetworkCredential myCreds = new NetworkCredential(usr, pass);
                client.Credentials = myCreds;
                var json = client.DownloadString(url);


            string fileName = "C:/Users/wolfa/Downloads/51710796_10205823876152016_4831820693123366912_o.jpg";






            var ctx = HttpContext.Current;
            var root = ctx.Server.MapPath("~/App_Data");
            var provider =
                new MultipartFormDataStreamProvider(root);

            try
            {
                await Request.Content
                    .ReadAsMultipartAsync(provider);

                foreach (var file in provider.FileData)
                {
                    var name = file.Headers
                        .ContentDisposition
                        .FileName;

                    // remove double quotes from string.
                    name = name.Trim('"');

                    fileName = "C:/Users/wolfa/Downloads/51710796_10205823876152016_4831820693123366912_o.jpg";




                    fileStream = new FileStream(fileName, FileMode.Open, FileAccess.Read);
                    //StreamReader reader = new StreamReader(stream);

                    ///fileStream = File;
                    
                    
                    
                    
                    var localFileName = file.LocalFileName;
                    var filePath = Path.Combine(root, name);

                    File.Move(localFileName, filePath);
                }
            }
            catch (Exception e)
            {
                return Ok($"Error: {e.Message}");
            }

            dynamic m = JsonConvert.DeserializeObject(json);






            Uri blobUri = new Uri("https://saaty.blob.core.windows.net/documentos/" + fileName);

            // Create StorageSharedKeyCredentials object by reading
            // the values from the configuration (appsettings.json)
            StorageSharedKeyCredential storageCredentials =
                new StorageSharedKeyCredential("saaty", "Bo2msi0DOXRHhflJgkxQafPu+6A9xl0zcdDS6ka9o+kC3UZvhe0vAUrK5c23beHTLPvMEOkfHALPgmr/LdRD7g==");

            // Create the blob client.
            BlobClient blobClient = new BlobClient(blobUri, storageCredentials);

            // Upload the file
            await blobClient.UploadAsync(fileStream);









            return Ok(json);
        }
    }



}
