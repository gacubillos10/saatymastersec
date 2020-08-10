using System;
using System.Collections.Generic;
using System.IO;
using System.Linq;
using System.Net;
using System.Net.Http;
using System.Threading.Tasks;
using System.Web;
using System.Web.Http;

using Microsoft.Azure;
using Microsoft.WindowsAzure.Storage;
using Microsoft.WindowsAzure.Storage.Blob;
using Azure.Storage;
using Azure.Storage.Blobs;
using System.Text;
using SaatyWeb.Models;

namespace SaatyWeb.Controllers
{
    public class CoreController : ApiController
    {
        [HttpPost]
        public async Task<IHttpActionResult> Core()
        {

            SubirDocumentoDto SDC = new SubirDocumentoDto();

            string filePath="";
            var ctx = HttpContext.Current;
            var root = ctx.Server.MapPath("~/App_Data");
            var provider = new MultipartFormDataStreamProvider(root);

            filePath = await createLocalFileAsync(ctx, root, provider);//Crea archivo local

            
            encode64(filePath);


            //SDC.storage = "Azure";
            //SDC.urlLocal = filePath;

            /*string urlAPISubir = @"http://saaty.azurewebsites.net//api/SubirDocumento";*/
            string urlAPISubir = "http://localhost:62276//api/SubirDocumento";



            WebClient client = new WebClient();
            string Json = Newtonsoft.Json.JsonConvert.SerializeObject(SDC);
            client.Headers[HttpRequestHeader.ContentType] = "application/json";

            //string response = client.UploadString(urlAPISubir, Json);
            var bytes = Convert.FromBase64String(@"iVBORw0KGgoAAAANSUhEUgAAAAUAAAAFCAYAAACNbyblAAAAHElEQVQI12P4//8/w38GIAXDIBKE0DHxgljNBAAO9TXL0Y4OHwAAAABJRU5ErkJggg==");// without data:image/jpeg;base64 prefix, just base64 string

            client.UploadData(urlAPISubir, bytes);


            return Ok("OK");
        }



        public void encode64(string urlFile)
        {
            Byte[] bytes = File.ReadAllBytes(urlFile);
            string file = Convert.ToBase64String(bytes);
            //urlFile = @"C:\Users\wolfa\source\repos\SaatyWeb\SaatyWeb\App_Data\base64.txt";

            try
            {
                // Check if file already exists. If yes, delete it.     
                if (File.Exists(urlFile))
                {
                    File.Delete(urlFile);
                }

                // Create a new file     
                using (FileStream fs = File.Create(urlFile))
                {
                    // Add some text to file    
                    Byte[] title = new UTF8Encoding(true).GetBytes(file);
                    fs.Write(title, 0, title.Length);
                }

            }
            catch (Exception Ex)
            {
                Console.WriteLine(Ex.ToString());
            }



        }

        

        public async Task<string> createLocalFileAsync(HttpContext ctx, string root, MultipartFormDataStreamProvider provider)
        {

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
                    var localFileName = file.LocalFileName;
                    var filePath = Path.Combine(root, name);
                    File.Move(localFileName, filePath);
                    return filePath;
                }

            }
            catch (Exception e)
            {
                return "Fallo";
            }
            return "OK";
        }


    }
}
