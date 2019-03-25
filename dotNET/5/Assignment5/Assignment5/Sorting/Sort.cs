using System;
using System.Collections.Generic;
using System.Linq;
using System.Windows.Forms;

namespace Assignment5.Sorting
{

    public delegate void Swap(int i, int j);

    public abstract class Sort
    {
        public Swap Swap { get; set; }
        public List<Button> Buttons { get; private set; }

        public List<int> GetWidthsList()
        {
            return Buttons.Select(btn => btn.Width).ToList();
        }

        public int StepCount
        {
            get
            {
                int count = 0;

                List<int> widths = GetWidthsList();
                DoSort(widths, (i, j) =>
                {
                    count++;
                    widths.Swap(i, j);
                });

                return count;
            }
        }

        public Sort(List<Button> buttons)
        {
            Buttons = buttons;
        }

        public void SortButtons()
        {
            var widths = GetWidthsList();
            DoSort(widths, (i, j) =>
            {
                Swap(i, j);
                Buttons.Swap(i, j);
                widths.Swap(i, j);
            });
        }

        protected abstract void DoSort(List<int> widths, Swap swap);

    }
}
