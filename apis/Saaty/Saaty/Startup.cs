using Microsoft.Owin;
using Owin;

[assembly: OwinStartupAttribute(typeof(Saaty.Startup))]
namespace Saaty
{
    public partial class Startup
    {
        public void Configuration(IAppBuilder app)
        {
            ConfigureAuth(app);
        }
    }
}
