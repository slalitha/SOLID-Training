

namespace Notepad.Saving
{
    abstract class SaveToCloud : ISaver
    {
        public SaveToCloud(string FilePath)
        {
            this.FilePath = FilePath;
        }

        public string FilePath { get; }

        public abstract void Save();
    }
}
