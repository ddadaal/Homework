using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Media;

namespace TagCanvas.Models
{
    public class ColorProfile
    {
        public Dictionary<Shape, Color> ColorDictionary { get; set; } = new Dictionary<Shape, Color>()
        {
            { Shape.Circle, Colors.Red },
            { Shape.Triangle, Colors.Yellow },
            { Shape.Rectangle, Colors.Blue }
        };

        public Color FallbackColor { get; set; } = Colors.Gray;

        public Color GetColorFromShape(Shape shape)
        {

            bool result = ColorDictionary.TryGetValue(shape, out Color color);
            if (result)
            {
                return color;
            }
            else
            {
                return FallbackColor;
            }
        }
    }
}
