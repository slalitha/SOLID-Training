
using System.IO;

namespace Notepad.Saving
{
    class SaveToGoogleDrive : SaveToCloud
    {
        public SaveToGoogleDrive(string filePath) : base(filePath) { }

        public override void Save()
        {
            Drive d = new Drive();
            d.UploadFile(FilePath, Path.GetExtension(FilePath));
        }
    }
}
