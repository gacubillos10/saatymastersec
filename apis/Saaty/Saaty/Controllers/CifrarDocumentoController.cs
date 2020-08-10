using System;
using System.Collections.Generic;
using System.Linq;
using System.Net;
using System.Net.Http;
using System.Web.Http;

namespace Saaty.Controllers
{

    public class CifrarDocumentoController : ApiController
    {
        [HttpPost]
        public IHttpActionResult Cifrar()
        {
            return Ok("Archivo cifrado OK");
        }
    }
}
