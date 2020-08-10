using SaatyWeb.Models;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Net;
using System.Net.Http;
using System.Security.Cryptography;
using System.Threading.Tasks;
using System.Web.Http;

using Model;
using Service.Auth;
using System.Web;

namespace SaatyWeb.Controllers
{
    public class LoginController : ApiController
    {
        private ApplicationSignInManager _signInManager;
        private ApplicationUserManager _userManager;

    




        [HttpGet]
        public async Task<IHttpActionResult> login(LoginDtocs LoginDtocs)
        {
            string usr = LoginDtocs.user;
            string pass = LoginDtocs.pass;

            pass = HashPassword(pass);

           // var result = await PasswordSignInAsync("", "", false, shouldLockout: false);
            return Ok(true);
        }

        public static string HashPassword(string password)
        {
            byte[] salt;
            byte[] buffer2;
            if (password == null)
            {
                throw new ArgumentNullException("password");
            }
            using (Rfc2898DeriveBytes bytes = new Rfc2898DeriveBytes(password, 0x10, 0x3e8))
            {
                salt = bytes.Salt;
                buffer2 = bytes.GetBytes(0x20);
            }
            byte[] dst = new byte[0x31];
            Buffer.BlockCopy(salt, 0, dst, 1, 0x10);
            Buffer.BlockCopy(buffer2, 0, dst, 0x11, 0x20);
            return Convert.ToBase64String(dst);
        }
    }
}
