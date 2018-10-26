using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Media;
using TagCanvas.Models;

namespace TagCanvas.ViewModels
{

    public class ShapeSelection
    {
        public Shape Shape { get; set; }
        public Color Color { get; set; }

        public Brush Fill => new SolidColorBrush(Color);
        public string Name => Shape.ToString();

        public override bool Equals(object obj)
        {
            return obj is ShapeSelection map &&
                   EqualityComparer<Shape>.Default.Equals(Shape, map.Shape) &&
                   Color.Equals(map.Color);
        }

        public override int GetHashCode()
        {
            var hashCode = -1078427730;
            hashCode = hashCode * -1521134295 + EqualityComparer<Shape>.Default.GetHashCode(Shape);
            hashCode = hashCode * -1521134295 + EqualityComparer<Color>.Default.GetHashCode(Color);
            return hashCode;
        }

        public override string ToString()
        {
            return Name;
        }
    }

    public class GraphEditDialogViewModel : BaseViewModel
    {

        private ColorProfile colorProfile;

        public GraphEditDialogViewModel(ColorProfile colorProfile)
        {
            this.colorProfile = colorProfile;
        }

        private string name;
        public string Name
        {
            get => name;
            set
            {
                SetProperty(ref name, value, nameof(Name), nameof(BtnConfirmAvailable));
            }
        }

        private string content;
        public string Content
        {
            get => content;
            set
            {
                SetProperty(ref content, value);
            }
        }

        private ShapeSelection shape;
        public ShapeSelection Shape
        {
            get => shape;
            set
            {
                SetProperty(ref shape, value, nameof(Shape), nameof(ShapeName));
            }
        }
    
        public string ShapeName
        {
            get => shape.ToString();
            set
            {
                var shape = new Shape(value);
                Shape = new ShapeSelection()
                {
                    Shape = shape,
                    Color = colorProfile.GetColorFromShape(shape)
                };
            }
        }

        public bool BtnConfirmAvailable => !string.IsNullOrEmpty(name);

        public List<ShapeSelection> ShapeSelections
        {
            get
            {
                return colorProfile.ColorDictionary.Select(x => new ShapeSelection()
                {
                    Shape = x.Key,
                    Color = x.Value
                }).ToList();
            }
        }
    }
}
