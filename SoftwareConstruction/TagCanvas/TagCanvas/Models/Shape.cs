using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace TagCanvas.Models
{
    [Serializable]
    public class Shape
    {
        public string Name { get; set; }

        public Shape(string name)
        {
            Name = name;
        }

        #region Equals and ToString
        public override bool Equals(object obj)
        {
            return obj is Shape shape &&
                   Name == shape.Name;
        }

        public override int GetHashCode()
        {
            return 539060726 + EqualityComparer<string>.Default.GetHashCode(Name);
        }

        public override string ToString()
        {
            return Name;
        }
        #endregion

        #region Predefined Shapes (Rectangle, Triangle and Circle)
        public readonly static Shape Rectangle = new Shape(nameof(Rectangle));

        public readonly static Shape Triangle = new Shape(nameof(Triangle));

        public readonly static Shape Circle = new Shape(nameof(Circle));
        #endregion



    }

}
