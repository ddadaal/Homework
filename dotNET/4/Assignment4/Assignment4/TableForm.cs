using Assignment4.Services;
using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Data.SqlClient;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;

namespace Assignment4
{
    public partial class TableForm : Form
    {

        private DatabaseService databaseService;
        private StorageService storageService = new StorageService();

        private string table;
        private string database;

        SqlDataAdapter dataAdapter;

        public TableForm(DatabaseService databaseService, string database, string table)
        {
            InitializeComponent();


            this.databaseService = databaseService;
            this.database = database;
            this.table = table;

        }

        private void Table_Load(object sender, EventArgs e)
        {
            BindTreeEvents();

            UpdateViews();
        }

        private void UpdateViews()
        {

            UpdateGridData();

            UpdateTreeViewData();
        }

        private void UpdateTreeViewData()
        {
            
            SetTreeViewData(databaseService.GetDataSet(database, table));

        }

        private void UpdateGridData()
        {
            dataAdapter = databaseService.GetDataAdapter(database, table);


            var dataSet = databaseService.FillDataSet(dataAdapter);

            dgData.DataSource = dataSet;
            dgData.DataMember = "Table";

        }


        private void btnSaveToXml_Click(object sender, EventArgs e)
        {
            var dataSet = dgData.DataSource as DataSet;

            var dialog = new SaveFileDialog
            {
                DefaultExt = "xml",
                Filter = "XML (*.xml)|*.xml"
            };
            if (dialog.ShowDialog() == DialogResult.OK)
            {
                var path = dialog.FileName;
                storageService.SaveToXml(dataSet, path);
                MessageBox.Show("保存成功！");
            }
        }

        private void btnSaveGridToDb_Click(object sender, EventArgs e)
        {
            if (dataAdapter == null)
            {
                return;
            }

            dgData.EndEdit();

            UpdateDatabase(dgData.DataSource as DataSet, false);

            UpdateViews();
        }

        private void UpdateDatabase(DataSet dataSet, bool deletedAll)
        {
            if (deletedAll)
            {
                databaseService.DeleteAll(database, table);

            }
            dataAdapter.Update(dataSet);
            
        }

        private void tvData_AfterSelect(object sender, TreeViewEventArgs e)
        {

        }
    }
}
