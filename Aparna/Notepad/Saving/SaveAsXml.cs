using System;
using System.IO;
using System.Xml;

namespace Notepad.Saving
{
    public class SaveAsXml:SaveToSystem
    {
        public SaveAsXml(string fileName, string text) : base(fileName, text) { }

        public override void Save()
        {
            try
            {
                if (!String.IsNullOrEmpty(Text))
                {
                    XmlDocument xmlDocument = new XmlDocument();

                    xmlDocument.Load(Text);
                    xmlDocument.Save(Path.GetFullPath(FileName));

                }
            }
            catch
            {
                throw;
            }
        }
    }
}
