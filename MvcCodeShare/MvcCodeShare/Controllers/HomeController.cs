using System;
using System.Collections.Generic;
using System.Data;
using System.Data.Entity;
using System.Linq;
using System.Net;
using System.Web;
using System.Web.Mvc;
using MvcMovie.Models;

namespace MvcMovie.Controllers
{
    public class HomeController : Controller
    {
        private CodeDBContext db = new CodeDBContext();

        // GET: Home/List
        public ActionResult List()
        {
            return View(db.Codes.ToList());
        }

        public ActionResult About()
        {
            return View();
        }
        // GET: Home/Details/1
        public ActionResult Details(int? id)
        {
            if (id == null)
            {
                return new HttpStatusCodeResult(HttpStatusCode.BadRequest);
            }
            Code code = db.Codes.Find(id);
            if (code == null)
            {
                return HttpNotFound();
            }
            return View(code);
        }

        // GET: Home/ 
        public ActionResult Index()
        {
            List<SelectListItem> list = new List<SelectListItem>();    // Add a dropDownList
            list.Add(new SelectListItem { Text = "Text", Value = "text" });
            list.Add(new SelectListItem { Text = "C/C++", Value = "c_cpp" });
            list.Add(new SelectListItem { Text = "C#", Value = "csharp" });
            list.Add(new SelectListItem { Text = "Java", Value = "java" });
            list.Add(new SelectListItem { Text = "Go", Value = "fsharp" });
            list.Add(new SelectListItem { Text = "Javascript", Value = "javacript" });
            ViewBag.list = list;   

            return View();
        }

        // POST: Home/
        [HttpPost]
        [ValidateAntiForgeryToken]
        public ActionResult Index([Bind(Include = "ID,Title,Author,Language,Addcode")] Code code)
        {
            if (ModelState.IsValid)
            {
                db.Codes.Add(code);
                db.SaveChanges();
                return RedirectToAction("List");
            }
            
            return View(code);
        }


        // GET: Home/Delete/1
        public ActionResult Delete(int? id)
        {
            if (id == null)
            {
                return new HttpStatusCodeResult(HttpStatusCode.BadRequest);
            }
            Code code = db.Codes.Find(id);
            if (code == null)
            {
                return HttpNotFound();
            }
            return View(code);
        }

        // POST: Home/Delete/1
        [HttpPost, ActionName("Delete")]
        [ValidateAntiForgeryToken]
        public ActionResult DeleteConfirmed(int id)
        {
            Code code = db.Codes.Find(id);
            db.Codes.Remove(code);
            db.SaveChanges();
            return RedirectToAction("Index");
        }

        protected override void Dispose(bool disposing)
        {
            if (disposing)
            {
                db.Dispose();
            }
            base.Dispose(disposing);
        }
    }
}
