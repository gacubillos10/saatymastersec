using Microsoft.Owin;
using Owin;

[assembly: OwinStartupAttribute(typeof(SaatyWeb.Startup))]
namespace SaatyWeb
{
    public partial class Startup
    {
        public void Configuration(IAppBuilder app)
        {
            ConfigureAuth(app);
        }
    }
}
