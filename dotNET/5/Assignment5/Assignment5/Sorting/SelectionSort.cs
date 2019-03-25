using System.Collections.Generic;
using System.Windows.Forms;

namespace Assignment5.Sorting
{
    public class SelectionSort : Sort
    {
        public SelectionSort(List<Button> buttons) : base(buttons)
        {
        }

        protected override void DoSort(List<int> widths, Swap swap)
        {
            int len = widths.Count;
            for (int i = 0; i < len - 1; i++)
            {
                int min = i;
                for (int j = i + 1; j < len; j++)
                {
                    if (widths[min] > widths[j])
                    {
                        min = j;
                    }

                }
                swap(min, i);
            }
        }
    }
}
