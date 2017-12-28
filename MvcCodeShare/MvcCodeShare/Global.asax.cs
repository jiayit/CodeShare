using System;
using System.Collections.Generic;
using System.Data.Entity;
using System.Linq;
using System.Web;
using System.Web.Mvc;
using System.Web.Optimization;
using System.Web.Routing;

namespace MvcMovie
{
    public class MvcApplication : System.Web.HttpApplication
    {
        protected void Application_Start()
        {
            AreaRegistration.RegisterAllAreas();
            FilterConfig.RegisterGlobalFilters(GlobalFilters.Filters);
            RouteConfig.RegisterRoutes(RouteTable.Routes);
            BundleConfig.RegisterBundles(BundleTable.Bundles);
           
            // initializing in model does not work, so initialize it in the Glabal file
            Database.SetInitializer(new DropCreateDatabaseAlways<Models.CodeDBContext>());   
            using (var context = new Models.CodeDBContext())
            {
                //context.Database.Initialize(force: true);
            }

        }
    }
}
