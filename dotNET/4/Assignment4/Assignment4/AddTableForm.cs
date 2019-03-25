using Assignment4.Services;
using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;

namespace Assignment4
{


    public partial class AddTableForm : Form
    {

        public event ConfirmHandler Confirmed;

        private string database;
        private DatabaseService databaseService;

        public AddTableForm(string database, DatabaseService databaseService)
        {
            InitializeComponent();

            this.database = database;
            this.databaseService = databaseService;
        }

        private void AddTableForm_Load(object sender, EventArgs e)
        {

        }

        private void btnConfirm_Click(object sender, EventArgs e)
        {
            var dict = new Dictionary<string, string>();

            foreach (ListViewItem pair in lvAttributes.Items)
            {
                dict.Add(pair.SubItems[0].Text, pair.SubItems[1].Text);
            }

            try
            {
                databaseService.AddTable(database, tbTableName.Text, dict);
                Confirmed?.Invoke();
                Close();
            } catch (Exception ex)
            {
                MessageBoxEx.Error(ex.Message);
            }
        }

        private void btnCancel_Click(object sender, EventArgs e)
        {
            Close();
        }

        private void btnChangeCol_Click(object sender, EventArgs e)
        {
            if (lvAttributes.SelectedIndices.Count == 0)
            {
                return;
            }

            var selected = lvAttributes.SelectedItems[0];

            string input = MessageBoxEx.Input($"原：{selected.SubItems[0].Text}");

            selected.SubItems[0].Text = input;
            
        }

        private void btnChangeType_Click(object sender, EventArgs e)
        {
            if (lvAttributes.SelectedIndices.Count == 0)
            {
                return;
            }

            var selected = lvAttributes.SelectedItems[0];

            string input = MessageBoxEx.Input($"原：{selected.SubItems[1].Text}");

            selected.SubItems[1].Text = input;
        }

        private void btnDelete_Click(object sender, EventArgs e)
        {
            if (lvAttributes.SelectedIndices.Count == 0)
            {
                return;
            }

            lvAttributes.Items.RemoveAt(lvAttributes.SelectedIndices[0]);
        }

        private void btnAdd_Click(object sender, EventArgs e)
        {
            lvAttributes.BeginUpdate();
            AddKeyValue("列名", "类型");
            lvAttributes.EndUpdate();
        }

        public void AddKeyValue(string key, string value)
        {
            lvAttributes.Items.Add(new ListViewItem(new string[] { key, value }));
        }

        private void tbTagName_TextChanged(object sender, EventArgs e)
        {

        }
    }
}
