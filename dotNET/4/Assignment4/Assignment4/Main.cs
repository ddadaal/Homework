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
    public partial class Main : Form
    {
        private DatabaseService databaseService;

        public Main()
        {
            InitializeComponent();

            BindDatabaseContextMenuEvents();
            BindTableContextMenuEvents();
        }

        public string SelectedDatabase => lbDatabases.SelectedItem as string;

        public string SelectedTable => lbTables.SelectedItem as string;
        


        private void lbTables_SelectedIndexChanged(object sender, EventArgs e)
        {

        }

        private void Main_Load(object sender, EventArgs e)
        {

            try
            {
                databaseService = new DatabaseService();

            } catch (Exception ex)
            {
                MessageBoxEx.Error($"建立数据库连接失败！原因：{ex.Message}");
            }

            UpdateDatabases();

        }

        private void UpdateTables()
        {
            if (SelectedDatabase == null) { return; }

            lbTables.BeginUpdate();
            lbTables.Items.Clear();
            foreach (var table in databaseService.GetTables(SelectedDatabase))
            {
                lbTables.Items.Add(table);
            }
            lbTables.EndUpdate();
        }

        private void UpdateDatabases()
        {
            lbDatabases.BeginUpdate();
            lbDatabases.Items.Clear();
            foreach (var database in databaseService.GetDatabases())
            {
                lbDatabases.Items.Add(database);
            }
            lbDatabases.EndUpdate();
        }

        private void lbDatabases_SelectedIndexChanged(object sender, EventArgs e)
        {
            UpdateTables();
        }
    }
}
