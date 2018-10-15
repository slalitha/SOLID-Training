using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;

namespace WFTextEditor
{
    class Dialogs
    {
        private static OpenFileDialog ofd;
        public Dialogs()
        {
        }
        public static string OpenFile(RichTextBox richTextBox,Form form)
        {
            ofd = new OpenFileDialog();
            ofd.Title = "Open Document";
            ofd.Filter = "Text Files|*.txt";
            ofd.FileName = string.Empty;
            if (ofd.ShowDialog() == DialogResult.OK)
            {
                if (ofd.FileName == String.Empty)
                {
                    return null;
                }
                else
                {
                    string str = ofd.FileName;
                    richTextBox.LoadFile(str, RichTextBoxStreamType.PlainText);
                    form.Text = ofd.FileName;
                    return str;
                }
            }
            return null;
        }

        public static string SearchDialog(string text, string caption)
        {
            Form prompt = new Form()
            {
                Width = 500,
                Height = 150,
                FormBorderStyle = FormBorderStyle.FixedDialog,
                Text = caption,
                StartPosition = FormStartPosition.CenterScreen
            };
            Label textLabel = new Label() { Left = 50, Top = 20, Text = text, Width = 400 };
            TextBox textBox = new TextBox() { Left = 50, Top = 50, Width = 400 };
            Button confirmation = new Button() { Text = "Ok", Left = 350, Width = 100, Top = 70, DialogResult = DialogResult.OK };
            confirmation.Click += (sender, e) => { prompt.Close(); };
            prompt.Controls.Add(textBox);
            prompt.Controls.Add(confirmation);
            prompt.Controls.Add(textLabel);
            prompt.AcceptButton = confirmation;
            return prompt.ShowDialog() == DialogResult.OK ? textBox.Text : "";
        }
        public static string[] ReplaceDialog(string caption, string text, string text2)
        {
            Form prompt = new Form()
            {
                Width = 500,
                Height = 250,
                FormBorderStyle = FormBorderStyle.FixedDialog,
                Text = caption,
                StartPosition = FormStartPosition.CenterScreen
            };
            Label textLabel = new Label() { Left = 50, Top = 20, Text = text, Width = 400 };
            TextBox textBox = new TextBox() { Left = 50, Top = 50, Width = 400 };
            Label textLabel2 = new Label() { Left = 50, Top = 80, Text = text2, Width = 400 };
            TextBox textBox2 = new TextBox() { Left = 50, Top = 110, Width = 400 };
            Button confirmation = new Button() { Text = "Ok", Left = 350, Width = 100, Top = 140, DialogResult = DialogResult.OK };
            confirmation.Click += (sender, e) => { prompt.Close(); };
            prompt.Controls.Add(textBox);
            prompt.Controls.Add(textBox2);
            prompt.Controls.Add(confirmation);
            prompt.Controls.Add(textLabel);
            prompt.Controls.Add(textLabel2);
            prompt.AcceptButton = confirmation;
            DialogResult dialogResult = prompt.ShowDialog();
            string[] replaceTexts = { textBox.Text, textBox2.Text };
            return dialogResult == DialogResult.OK ? replaceTexts : null;
        }
    }
}
