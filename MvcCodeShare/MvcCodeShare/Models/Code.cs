using System;
using System.Collections.Generic;
using System.ComponentModel.DataAnnotations;
using System.Data.Entity;
using System.Linq;
using System.Web;

namespace MvcMovie.Models
{
    public class Code
    {
        public int ID { get; set; }
        public string Title { get; set; }
        public string Author { get; set; }
        public string Language { get; set; }
        [DataType(DataType.MultilineText)]
        public string Addcode { get; set; }
    }


    public class CodeDBContext : DbContext
    {
        // constructor
        public CodeDBContext() : base("CodeDBContext")
        {
            // Create a database every time the application is started 
            Database.SetInitializer(new DropCreateDatabaseAlways<CodeDBContext>());  
        }
        public DbSet<Code> Codes { get; set; }
    }

}
