using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;

namespace Assignment5.Sorting
{
    public class QuickSort: Sort
    {
        public QuickSort(List<Button> buttons) : base(buttons)
        {
        }

        int Partition(List<int> numbers, int left, int right, Swap swap)
        {
            int pivot = numbers[left];
            while (true)
            {
                while (numbers[left] < pivot)
                {
                    left++;
                }
                while (numbers[right] > pivot)
                {
                    right--;
                }
                if (left < right)
                {
                    swap(left, right);
                }
                else
                {
                    return right;
                }

            }

        }

        public void SortQuick(List<int> arr, int left, int right, Swap swap)
        {
            // For Recusrion  

            if (left < right)
            {    
                int pivot = Partition(arr, left, right, swap);
                if (pivot > 1)
                {
                    SortQuick(arr, left, pivot - 1, swap);
                }
                if (pivot + 1 < right)
                {
                    SortQuick(arr, pivot + 1, right, swap);
                }
            }
        }

        protected override void DoSort(List<int> widths, Swap swap)
        {
            SortQuick(widths, 0, widths.Count - 1, swap);
        }
    }
}
