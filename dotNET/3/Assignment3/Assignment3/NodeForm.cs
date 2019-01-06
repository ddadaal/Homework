using System;
using System.Windows.Forms;
using System.Xml;

namespace Assignment3
{

    public delegate void InfoConfirmHandler(NodeInfo node);

    public partial class NodeForm : Form
    {

        public event InfoConfirmHandler Confirmed;

        private XmlNode node;

        public NodeForm(XmlNode node)
        {

            InitializeComponent();
            this.node = node;
            tbType.Text = node.NodeType.ToString();

            UpdateInfo();
        }


        private void UpdateInfo()
        {
            if (node.NodeType == XmlNodeType.Text)
            {
                tbTagName.Text = node.InnerText;
                lvAttributes.Enabled = false;
                return;
            }

            tbTagName.Text = node.Name;

            lvAttributes.BeginUpdate();

            lvAttributes.Items.Clear();
            if (node.Attributes != null)
            {
                foreach (XmlAttribute attr in node.Attributes)
                {
                    AddKeyValue(attr.Name, attr.Value);
                }
            }


            lvAttributes.EndUpdate();
        }

        public void AddKeyValue(string key, string value)
        {
            lvAttributes.Items.Add(new ListViewItem(new string[] { key, value }));
        }


        private void NodeForm_Load(object sender, EventArgs e)
        {

        }

        private void btnConfirm_Click(object sender, EventArgs e)
        {
            var info = new NodeInfo()
            {
                TagName = tbTagName.Text,
                OriginalNode = node,
            };

            foreach (ListViewItem item in lvAttributes.Items)
            {
                info.Attributes[item.SubItems[0].Text] = item.SubItems[1].Text;
            }

            Confirmed?.Invoke(info);

            Close();
        }

        private void btnCancel_Click(object sender, EventArgs e)
        {
            Close();
        }

        private void listBox1_SelectedIndexChanged(object sender, EventArgs e)
        {

        }

        private void tbTagName_TextChanged(object sender, EventArgs e)
        {
        }

        private void btnAdd_Click(object sender, EventArgs e)
        {
            lvAttributes.BeginUpdate();
            AddKeyValue("Key", "Value");
            lvAttributes.EndUpdate();
        }


        private void btnChangeKey_Click(object sender, EventArgs e)
        {
            if (lvAttributes.SelectedIndices.Count == 0)
            {
                return;
            }

            var selected = lvAttributes.SelectedItems[0];

            if (DialogUtil.ShowDialog($"原：{selected.SubItems[0].Text}", "修改键", out string input))
            {
                selected.SubItems[0].Text = input;
            }
        }

        private void btnDelete_Click(object sender, EventArgs e)
        {
            if (lvAttributes.SelectedIndices.Count == 0)
            {
                return;
            }

            lvAttributes.Items.RemoveAt(lvAttributes.SelectedIndices[0]);
        }

        private void btnChangeValue_Click(object sender, EventArgs e)
        {
            if (lvAttributes.SelectedIndices.Count == 0)
            {
                return;
            }

            var selected = lvAttributes.SelectedItems[0];

            if (DialogUtil.ShowDialog($"原：{selected.SubItems[1].Text}", "修改值", out string input))
            {
                selected.SubItems[1].Text = input;
            }

        }
    }
}
