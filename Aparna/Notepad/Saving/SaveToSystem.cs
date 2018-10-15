

namespace Notepad.Saving
{
    public abstract class SaveToSystem : ISaver
    {
        public SaveToSystem(string FileName, string Text)
        {
            this.FileName = FileName;
            this.Text = Text;
        }

        public string FileName { get; set; }
        public string Text { get ; set; }

        public abstract void Save();
    }
}
