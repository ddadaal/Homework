using System;
using System.Text;
using System.Windows.Forms;
using System.Xml;

namespace Assignment3
{
    public partial class Form1 : Form
    {
        public string path = "";
        public XmlDocument document = new XmlDocument();
        private XmlNode selectedNode = null;

        public Form1()
        {
            InitializeComponent();

            treeViewXml.NodeMouseClick += TreeViewXml_NodeMouseClick;
            ctxmItemModify.Click += CtxmItemModify_Click;
            ctxmItemAddText.Click += CtxmItemAddText_Click;
            ctxmItemAddElement.Click += CtxmItemAddElement_Click;
            ctxmItemDelete.Click += CtxmItemDelete_Click;
        }

        private void CtxmItemAddElement_Click(object sender, EventArgs e)
        {
            AddElement(XmlNodeType.Element, selectedNode);
        }

        private void CtxmItemDelete_Click(object sender, EventArgs e)
        {
            if (selectedNode.ParentNode == null)
            {
                // the node is a root node
                document.RemoveChild(selectedNode);
            }
            else
            {
                selectedNode.ParentNode.RemoveChild(selectedNode);

            }
            UpdateList();
        }

        private void CtxmItemAddText_Click(object sender, EventArgs e)
        {
            AddElement(XmlNodeType.Text, selectedNode);
        }

        private void CtxmItemModify_Click(object sender, EventArgs e)
        {
            OpenNodeForm(selectedNode);
        }

        private void btnBrowse_Click(object sender, EventArgs e)
        {
            OpenFileDialog dialog = new OpenFileDialog();

            if (dialog.ShowDialog() == DialogResult.OK)
            {
                try
                {
                    path = dialog.FileName;
                    OnSelectFile();
                }
                catch (Exception ex)
                {
                    //throw ex;
                    MessageBox.Show($"发生了错误。{ex.Message}");
                }

            }

        }

        private void TreeViewXml_NodeMouseClick(object sender, TreeNodeMouseClickEventArgs e)
        {
            selectedNode = e.Node.Tag as XmlNode;

            if (e.Button == MouseButtons.Right)
            {

                ctxmItemType.Text = selectedNode.NodeType.ToString();

                ctxmItemAdd.Enabled = selectedNode.NodeType != XmlNodeType.Text;

                ctxmNodeOp.Show(treeViewXml, e.Location);
            }
        }

        private void OpenNodeForm(XmlNode node)
        {

            var infoForm = new NodeForm(node);

            infoForm.Confirmed += InfoForm_Confirmed;
            infoForm.Show();
        }

        private void InfoForm_Confirmed(NodeInfo nodeInfo)
        {

            if (nodeInfo.OriginalNode.NodeType == XmlNodeType.Text)
            {
                nodeInfo.OriginalNode.InnerText = nodeInfo.TagName;
            }
            else
            {
                var newNode = document.CreateElement(nodeInfo.TagName);

                foreach (var attr in nodeInfo.Attributes)
                {
                    newNode.SetAttribute(attr.Key, attr.Value);
                }

                // Every access to the ChildNodes will remove the accessed item from the original list
                // so this is the only way to transfer the children into the new one
                // Is this to save memory by avoiding multiple copies of the same data?
                while (nodeInfo.OriginalNode.HasChildNodes)
                {
                    newNode.AppendChild(nodeInfo.OriginalNode.ChildNodes[0]);
                }


                //foreach (XmlNode child in nodeInfo.OriginalNode.ChildNodes)
                //{
                //    newNode.AppendChild(child);
                //}

                nodeInfo.OriginalNode.ParentNode.ReplaceChild(newNode, nodeInfo.OriginalNode);
            }

            UpdateList();


        }

        public void OnSelectFile()
        {
            // set the filepath
            tbFilePath.Text = path;

            // load the document
            document.Load(path);
            UpdateList();
        }

        public void UpdateList()
        {
            selectedNode = null;
            treeViewXml.Nodes.Clear();

            tbAddRoot.Enabled = document.DocumentElement == null;
            if (document.DocumentElement == null)
            {
                return;
            }

            TreeNode tNode = CreateTreeNodeFromXmlNode(document.DocumentElement);

            treeViewXml.Nodes.Add(tNode);

            AddNode(document.DocumentElement, tNode);
            treeViewXml.ExpandAll();
        }

        public static string XmlNodeToString(XmlNode node)
        {
            if (node.NodeType == XmlNodeType.Text)
            {
                return node.InnerText.Trim();
            }
            var sb = new StringBuilder();
            sb.Append(node.Name);
            if (node.Attributes != null && node.Attributes.Count > 0)
            {
                sb.Append("[");

                foreach (XmlAttribute attr in node.Attributes)
                {
                    sb.Append(attr.Name).Append("=").Append(attr.Value).Append(", ");
                }

                sb.Remove(sb.Length - 2, 2);
                sb.Append("]");
            }

            return sb.ToString();
        }

        private TreeNode CreateTreeNodeFromXmlNode(XmlNode node)
        {
            return new TreeNode()
            {
                Tag = node,
                Text = XmlNodeToString(node),
            };
        }

        private void AddNode(XmlNode inXmlNode, TreeNode inTreeNode)
        {

            if (inXmlNode.HasChildNodes)
            {
                XmlNodeList nodeList = inXmlNode.ChildNodes;
                for (int i = 0; i < nodeList.Count; i++)
                {
                    XmlNode xNode = inXmlNode.ChildNodes[i];

                    var newTreeNode = CreateTreeNodeFromXmlNode(xNode);

                    inTreeNode.Nodes.Add(newTreeNode);

                    AddNode(xNode, newTreeNode);
                }
            }
        }

        private void Form1_Load(object sender, EventArgs e)
        {

        }

        private void label1_Click(object sender, EventArgs e)
        {

        }

        private void AddElement(XmlNodeType type, XmlNode parent)
        {
            try
            {
                var name = "NEW";

                XmlNode element;

                switch (type)
                {
                    case XmlNodeType.Element:
                        element = document.CreateElement(name);
                        break;
                    case XmlNodeType.Text:
                        element = document.CreateTextNode(name);
                        break;
                    default:
                        throw new ArgumentException("Only accepts Element or Text.");
                }

                parent.AppendChild(element);
                UpdateList();
                OpenNodeForm(element);
            }
            catch (InvalidOperationException ex)
            {
                MessageBox.Show("发生错误：" + ex.Message);
            }

        }

        private void tbAddRoot_Click(object sender, EventArgs e)
        {
            AddElement(XmlNodeType.Element, document);


        }

        private void btnSave_Click(object sender, EventArgs e)
        {
            var dialog = new SaveFileDialog
            {
                DefaultExt = "xml",
                Filter = "XML (*.xml)|*.xml"
            };
            if (dialog.ShowDialog() == DialogResult.OK)
            {
                var path = dialog.FileName;
                document.Save(path);
                MessageBox.Show("保存成功！");
            }
        }
    }
}
