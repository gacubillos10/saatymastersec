//------------------------------------------------------------------------------
// <auto-generated>
//     Este código se generó a partir de una plantilla.
//
//     Los cambios manuales en este archivo pueden causar un comportamiento inesperado de la aplicación.
//     Los cambios manuales en este archivo se sobrescribirán si se regenera el código.
// </auto-generated>
//------------------------------------------------------------------------------

namespace Model.ModelsEntity
{
    using System;
    using System.Collections.Generic;
    
    public partial class Segmentacion
    {
        public int Id_Segmentacion { get; set; }
        public int Id_Storage { get; set; }
        public int Id_Documento { get; set; }
        public Nullable<int> Posicion { get; set; }
        public string UrlSegmento { get; set; }
        public string HashSegmento { get; set; }
        public string HashAnt { get; set; }
        public string HashPos { get; set; }
    
        public virtual Documentos Documentos { get; set; }
        public virtual Storage Storage { get; set; }
    }
}
