using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;

namespace SaatyWeb.Models
{
    public class SubirDocumentoDto
    {
        public string base64 { get; set; }
        public string usr { get; set; }
        public string acceso { get; set; }
        public string integridad { get; set; }
        public string disponibilidad { get; set; }
        public string fileName { get; set; }
    }
}