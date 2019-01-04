using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;
using System.Xml;

namespace Assignment3
{
    public partial class Form1 : Form
    {
        public XmlDocument document = new XmlDocument();

        public Form1()
        {
            InitializeComponent();
        }

        private void btnBrowse_Click(object sender, EventArgs e)
        {
            OpenFileDialog dialog = new OpenFileDialog();

            if (dialog.ShowDialog() == DialogResult.OK)
            {
                try
                {
                    document.Load(dialog.FileName);
                } catch (Exception ex)
                {
                    HandleException(ex);
                }
                UpdateList();
            }
            
        }

        public void UpdateList()
        {
            treeViewXml.Nodes.Clear();
            treeViewXml.Nodes.Add(new TreeNode(document.DocumentElement.Name));
            TreeNode tNode = new TreeNode();
            tNode = treeViewXml.Nodes[0];

            AddNode(document.DocumentElement, tNode);
            treeViewXml.ExpandAll();
        }

        private void AddNode(XmlNode inXmlNode, TreeNode inTreeNode)
        {
            XmlNode xNode;
            TreeNode tNode;
            XmlNodeList nodeList;
            int i;

            // Loop through the XML nodes until the leaf is reached.
            // Add the nodes to the TreeView during the looping process.
            if (inXmlNode.HasChildNodes)
            {
                nodeList = inXmlNode.ChildNodes;
                for (i = 0; i <= nodeList.Count - 1; i++)
                {
                    xNode = inXmlNode.ChildNodes[i];
                    inTreeNode.Nodes.Add(new TreeNode(xNode.Name));
                    tNode = inTreeNode.Nodes[i];
                    AddNode(xNode, tNode);
                }
            }
            else
            {
                // Here you need to pull the data from the XmlNode based on the
                // type of node, whether attribute values are required, and so forth.
                inTreeNode.Text = (inXmlNode.OuterXml).Trim();
            }
        }

        public void HandleException(Exception e)
        {
            MessageBox.Show($"发生了错误。{e.Message}");
        }

    }
}
