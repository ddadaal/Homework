using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using Microsoft.VisualBasic;

using System.Windows.Forms;

namespace Assignment4
{
    public static class MessageBoxEx
    {
        public static DialogResult Show(string text)
        {
            return MessageBox.Show(text);
        }

        public static DialogResult Error(string text)
        {
            return MessageBox.Show(text, "出错了！", MessageBoxButtons.OK, MessageBoxIcon.Error);
        }

        public static string Input(string prompt, string defaultResponse = null)
        {
            return Interaction.InputBox(prompt, "输入", defaultResponse);
        }
    }
}
