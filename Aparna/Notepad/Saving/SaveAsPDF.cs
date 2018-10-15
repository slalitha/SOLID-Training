using iTextSharp.text;
using iTextSharp.text.pdf;
using System;
using System.IO;


namespace Notepad.Saving
{
    public class SaveAsPDF : SaveToSystem
    {
        public SaveAsPDF(string fileName,string text) : base(fileName, text) { }   
        public override void Save()
        {
            try
            {
                if (!String.IsNullOrEmpty(Text))
                {
                    Document document = new Document();
                    PdfWriter.GetInstance(document, new FileStream(FileName, FileMode.Create));
                    document.Open();
                    document.Add(new Paragraph(Text));
                    document.Close();
                }
            }
            catch
            {
                throw;
            }
        }
    }
}
