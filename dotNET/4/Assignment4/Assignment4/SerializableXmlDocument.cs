using System;
using System.Collections.Generic;
using System.IO;
using System.Linq;
using System.Runtime.Serialization;
using System.Text;
using System.Threading.Tasks;
using System.Xml;

namespace Assignment4
{
    [Serializable]
    public class SerializableXmlDocument : ISerializable
    {
        private const string KEY = "xml";
        public XmlDocument Document { get; set; }

        internal SerializableXmlDocument(SerializationInfo si, StreamingContext context)
        {
            var xmlString = si.GetString(KEY) as string;
            Document = new XmlDocument();
            Document.LoadXml(xmlString);
        }

        public SerializableXmlDocument(XmlDocument document)
        {
            Document = document;
        }

        public void GetObjectData(SerializationInfo info, StreamingContext context)
        {
            using (StringWriter sw = new StringWriter())
            {
                using (XmlTextWriter tx = new XmlTextWriter(sw))
                {
                    Document.WriteTo(tx);
                    string strXmlText = sw.ToString();
                    info.AddValue(KEY, strXmlText);
                }
            }
        }
    }
}
