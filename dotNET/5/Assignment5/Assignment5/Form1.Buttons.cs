using System;
using System.Collections.Generic;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;

namespace Assignment5
{
    public partial class Form1
    {
        private const int HEIGHT = 40;

		private void SwapButtons(Button btn1, Button btn2)
        {
            var temp = btn1.Location;
            btn1.Location = btn2.Location;
            btn2.Location = temp;
            Update();
        }

        private void RemoveAllButtons()
        {
            foreach (var btns in buttons)
            {
                foreach (var btn in btns)
                {
                    Controls.Remove(btn);
                }
            }
            buttons.Clear();
            Update();

        }

        private void LocateButtons(List<Button> buttons, int column)
        {
            var anchor = labelAnchors[column].Location;

            for (int i=0;i<buttons.Count;i++)
            {
                buttons[i].Location = new Point(anchor.X, anchor.Y + 20 + HEIGHT * i);
            }

        }

        private Button CreateButton(int width, int column, int index)
        {

            var button = new Button
            {
                Size = new Size(width, HEIGHT),
                Text = $"{index}: {width}",
                TextAlign = ContentAlignment.MiddleLeft
	        };


            return button;
        }

        private List<int> GetRandomWidths(int num)
        {
            int min = 50, max = 300;
            return Enumerable.Range(0, num).Select(x => random.Next(min, max)).ToList();
        }
    }
}
