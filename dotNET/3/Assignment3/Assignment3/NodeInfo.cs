using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Xml;

namespace Assignment3
{
    public class NodeInfo
    {
        public XmlNodeType NodeType => OriginalNode.NodeType;
        public string TagName { get; set; }
        public Dictionary<string, string> Attributes { get; set; } = new Dictionary<string, string>();

        public XmlNode OriginalNode { get; set; }

        public NodeInfo() { }
        public NodeInfo(XmlNode node)
        {
            TagName = node.Name;
            if (node.Attributes != null)
            {
                foreach (XmlAttribute attr in node.Attributes)
                {
                    Attributes[attr.Name] = attr.Value;
                }
            }
        }

        public override string ToString()
        {
            var sb = new StringBuilder();
            sb.Append(TagName);
            if (Attributes.Count > 0)
            {
                sb.Append("[");
                foreach (var attr in Attributes)
                {
                    sb.Append(attr.Key).Append("=").Append(attr.Value).Append(", ");
                }
                sb.Append("]");
            }

            return sb.ToString();
        }
    }
}
