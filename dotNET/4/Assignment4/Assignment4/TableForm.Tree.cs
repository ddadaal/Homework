using System;
using System.Data;
using System.IO;
using System.Text;
using System.Windows.Forms;
using System.Xml;

namespace Assignment4
{
    public partial class TableForm : Form
    {

        private bool loadedFile = false;

        private XmlDocument document = new XmlDocument();
        private XmlNode selectedNode = null;

        private void BindTreeEvents()
        {
            tvData.NodeMouseClick += TvData_NodeMouseClick;
        }

        private void TvData_NodeMouseClick(object sender, TreeNodeMouseClickEventArgs e)
        {
            selectedNode = e.Node.Tag as XmlNode;
        }

        private string XmlNodeToString(XmlNode node)
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

        private void SetTreeViewData(DataSet dataSet)
        {
            if (loadedFile) { return; }
            string xml = dataSet.GetXml();
            document.LoadXml(xml);
            UpdateList();
        }

        private void UpdateList()
        {
            tvData.Nodes.Clear();

            var rootNode = document.DocumentElement;

            if (rootNode == null) { return; }

            var tNode = CreateTreeNodeFromXmlNode(rootNode);

            tvData.Nodes.Add(tNode);
            AddNode(rootNode, tNode);
            tvData.ExpandAll();
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

                nodeInfo.OriginalNode.ParentNode.ReplaceChild(newNode, nodeInfo.OriginalNode);
            }

            UpdateList();


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
            }
            catch (InvalidOperationException ex)
            {
                MessageBox.Show("发生错误：" + ex.Message);
            }

        }

        private TreeNode CreateTreeNodeFromXmlNode(XmlNode node)
        {
            return new TreeNode()
            {
                Tag = node,
                Text = XmlNodeToString(node)
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


        private void btnAddButton_Click(object sender, EventArgs e)
        {
            var form = new AddNodeForm(database, table ,databaseService);
            form.Show();

            form.Confirmed += () =>
            {
                UpdateViews();
            };
        }


        private void btnDeleteNode_Click(object sender, EventArgs e)
        {
            if (selectedNode == null) { return; }


            if (selectedNode.ParentNode != document.DocumentElement)
            {
                MessageBoxEx.Error("只能删除数据库行节点");
                return;
            }

            // Delete the entry at once
            selectedNode.ParentNode.RemoveChild(selectedNode);

            SaveTreeChanges();

            UpdateViews();
        }

        public void SaveTreeChanges()
        {
            var dataSet = dgData.DataSource as DataSet;

            var xmlReader = new XmlNodeReader(document);

            dataSet.ReadXml(xmlReader);



            UpdateDatabase(dataSet, true);
        }

        private void btnSaveTree_Click(object sender, EventArgs e)
        {
            var data = new SerializableXmlDocument(document);

            var dialog = new SaveFileDialog()
            {
                DefaultExt = ".tree",
                FileName = $"Tree"
            };

            if (dialog.ShowDialog() == DialogResult.OK)
            {
                var fileName = dialog.FileName;
                storageService.SerializeToFile(data, fileName);
            }
        }

        private void btnReadTree_Click(object sender, EventArgs e)
        {
            var dialog = new OpenFileDialog()
            {
                DefaultExt = ".tree"
            };

            if (dialog.ShowDialog() == DialogResult.OK)
            {
                var fileName = dialog.FileName;
                var data = storageService.ReadObjectFromFile(fileName) as SerializableXmlDocument;

                document = data.Document;
                UpdateList();

                loadedFile = true;

                // disable buttons
                btnAddButton.Enabled = false;
                btnDeleteNode.Enabled = false;
            }
        }

    }
}
