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
    
    public partial class Nivel_Privacidad
    {
        [System.Diagnostics.CodeAnalysis.SuppressMessage("Microsoft.Usage", "CA2214:DoNotCallOverridableMethodsInConstructors")]
        public Nivel_Privacidad()
        {
            this.Documentos = new HashSet<Documentos>();
        }
    
        public int Id_NivelPrivacidad { get; set; }
        public int Id_Algoritmo { get; set; }
        public string NivelPrivacidad { get; set; }
        public string DescripcionPrivacidad { get; set; }
    
        public virtual Algoritmos_Cifrado Algoritmos_Cifrado { get; set; }
        [System.Diagnostics.CodeAnalysis.SuppressMessage("Microsoft.Usage", "CA2227:CollectionPropertiesShouldBeReadOnly")]
        public virtual ICollection<Documentos> Documentos { get; set; }
    }
}
