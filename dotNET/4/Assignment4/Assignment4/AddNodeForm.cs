using Assignment4.Services;
using System;
using System.Collections.Generic;
using System.Windows.Forms;

namespace Assignment4
{
    public delegate void ConfirmHandler();
    public partial class AddNodeForm : Form
    {
        public event ConfirmHandler Confirmed;

        public string database;
        public string table;
        public DatabaseService databaseService;

        private Dictionary<string, string> columns;

        public AddNodeForm(string database, string table, DatabaseService databaseService)
        {
            this.database = database;
            this.table = table;
            this.databaseService = databaseService;


            InitializeComponent();

            SetColumns();

        }

        private void SetColumns()
        {
            columns = databaseService.GetTableColumnDefs(database, table);

            foreach (var column in columns) {
                lvAttributes.Items.Add(new ListViewItem(new string[] { column.Key, "" }));
            }
        }

        private void btnCancel_Click(object sender, EventArgs e)
        {
            Close();
        }

        private void AddNodeForm_Load(object sender, EventArgs e)
        {

        }

        private void btnChangeValue_Click(object sender, EventArgs e)
        {
            if (lvAttributes.SelectedIndices.Count == 0)
            {
                return;
            }

            var selected = lvAttributes.SelectedItems[0];

            var key = selected.SubItems[0].Text;

            string input = MessageBoxEx.Input($"修改列：{key}，类型应该为：{columns[key]}。原值：{selected.SubItems[1].Text}");

            selected.SubItems[1].Text = input;

        }

        private void btnConfirm_Click(object sender, EventArgs e)
        {

            // collect values
            var dict = new Dictionary<string, string>();
            foreach (ListViewItem pair in lvAttributes.Items)
            {
                dict.Add(pair.SubItems[0].Text, pair.SubItems[1].Text);
            }

            try
            {
                databaseService.AddRow(database, table, dict);
                Confirmed?.Invoke();
                Close();
            } catch (Exception ex)
            {
                MessageBoxEx.Error(ex.Message);
            }
        }
    }
}
