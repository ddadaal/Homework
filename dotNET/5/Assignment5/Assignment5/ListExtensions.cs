using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Assignment5
{
    public static class ListExtensions
    {
        public static void Swap<T>(this List<T> list, int i, int j) {
            T temp = list[i];
            list[i] = list[j];
            list[j] = temp;
        }
    }
}
