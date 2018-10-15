using Notepad.ViewModel;

namespace Notepad.Saving
{
    public static class SaveFactory
    {
        public static ISaver GetSaveProvider(int fileType,string FileName,string text)
        {
            switch (fileType)
            {
                case FileTypeEnum.PDF:
                     return new SaveAsPDF(FileName,text);
                case FileTypeEnum.XML:
                    return new SaveAsXml(FileName, text);
                default:return null;
            }
        }
    }
}
