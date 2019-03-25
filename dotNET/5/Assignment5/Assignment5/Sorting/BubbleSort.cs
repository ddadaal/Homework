using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;

namespace Assignment5.Sorting
{
    public class BubbleSort : Sort
    {
        public BubbleSort(List<Button> buttons): base(buttons)
        {
        }

        protected override void DoSort(List<int> widths, Swap swap)
        {
            bool swapped;
            for (int i = 0; i < widths.Count; i++)
            {
                swapped = false;
                for (int j = 0; j < widths.Count - 1 - i; j++)
                    if (widths[j] > widths[j + 1])
                    {
                        swap(j, j+1);
                        swapped = true;

                    }
                if (!swapped)
                    return;
            }
        }
    }
}
