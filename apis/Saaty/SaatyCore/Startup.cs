using Microsoft.Owin;
using Owin;

[assembly: OwinStartupAttribute(typeof(SaatyCore.Startup))]
namespace SaatyCore
{
    public partial class Startup
    {
        public void Configuration(IAppBuilder app)
        {
            ConfigureAuth(app);
        }
    }
}
