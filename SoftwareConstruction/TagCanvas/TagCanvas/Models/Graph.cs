using System;
using System.Collections.Generic;
using System.Linq;
using System.IO;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Ink;
using System.Windows.Media;
using System.Runtime.Serialization;
using System.Runtime.Serialization.Formatters.Binary;

namespace TagCanvas.Models
{
    [Serializable]
    public class Graph : ISerializable
    {
        private static int latestId = 0;

        public int Id { get; set; }
        public StrokeCollection Strokes { get; set; }
        public Shape Shape { get; set; }
        public string Name { get; set; }
        public string Content { get; set; }

        public Graph(int id, StrokeCollection strokes, Shape shape, string name = null, string content = "")
        {
            Id = id;
            Strokes = strokes;
            Shape = shape;
            Name = name ?? $"{Shape} {Id}";
            Content = content;
        }

        public Graph(StrokeCollection strokes, Shape shape, string name = null, string content = "")
            : this(latestId++, strokes, shape, name, content)
        {
    
        }

        public override bool Equals(object obj)
        {
            return obj is Graph graph &&
                   Id == graph.Id;
        }

        public override int GetHashCode()
        {
            var hashCode = -1810666432;
            hashCode = hashCode * -1521134295 + Id.GetHashCode();
            hashCode = hashCode * -1521134295 + EqualityComparer<StrokeCollection>.Default.GetHashCode(Strokes);
            hashCode = hashCode * -1521134295 + EqualityComparer<Shape>.Default.GetHashCode(Shape);
            hashCode = hashCode * -1521134295 + EqualityComparer<string>.Default.GetHashCode(Name);
            hashCode = hashCode * -1521134295 + EqualityComparer<string>.Default.GetHashCode(Content);
            return hashCode;
        }

        public Graph(SerializationInfo info, StreamingContext context)
        {
            Id = (int)info.GetValue(nameof(Id), typeof(int));
            Shape = (Shape)info.GetValue(nameof(Shape), typeof(Shape));
            Name = (string)info.GetValue(nameof(Name), typeof(string));
            Content = (string)info.GetValue(nameof(Content), typeof(string));

            // deserialize strokes
            var bitContent = (byte[])info.GetValue(nameof(Strokes), typeof(byte[]));
            using (var stream = new MemoryStream(bitContent))
            {
                Strokes = new StrokeCollection(stream);
            }
        }

        public void GetObjectData(SerializationInfo info, StreamingContext context)
        {
            info.AddValue(nameof(Id), Id);
            info.AddValue(nameof(Shape), Shape);
            info.AddValue(nameof(Name), Name);
            info.AddValue(nameof(Content), Content);

            // serialze strokes
            using (var stream = new MemoryStream())
            {
                Strokes.Save(stream);
                var bytes = stream.ToArray();
                info.AddValue(nameof(Strokes), bytes);
            }


            

        }
    }
}
