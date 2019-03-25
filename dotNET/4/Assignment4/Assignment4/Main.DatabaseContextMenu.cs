using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;

namespace Assignment4
{
    public partial class Main
    {
        public void BindDatabaseContextMenuEvents()
        {
            cmDatabase.Opening += CmDatabase_Opening;
            cmiAddDatabase.Click += CmiAddDatabase_Click;
            cmiRenameDatabase.Click += CmiRenameDatabase_Click;
            cmiDeleteDatabase.Click += CmiDeleteDatabase_Click;
            cmiUpdateDatabase.Click += CmiUpdateDatabase_Click;
        }

        private void CmiUpdateDatabase_Click(object sender, EventArgs e)
        {
            UpdateDatabases();
        }

        private void CmDatabase_Opening(object sender, System.ComponentModel.CancelEventArgs e)
        {
            bool databaseSelected = SelectedDatabase != null;
            cmiDeleteDatabase.Enabled = databaseSelected;
            cmiRenameDatabase.Enabled = databaseSelected;
        }

        private void CmiDeleteDatabase_Click(object sender, EventArgs e)
        {
            var database = SelectedDatabase;
            if (database == "master")
            {
                MessageBoxEx.Error("不能删除master数据库！");
                return;
            }

            if (MessageBox.Show($"真的要删除{database}数据库吗？", "提示", 
                MessageBoxButtons.YesNo, MessageBoxIcon.Exclamation) == DialogResult.Yes)
            {
                try
                {
                    databaseService.DeleteDatabase(database);
                    lbDatabases.SelectedItem = null;
                    UpdateDatabases();
                    MessageBoxEx.Show("删除成功！");
 

                } catch (Exception ex)
                {
                    MessageBoxEx.Error($"发生了错误：{ex.Message}");
                }

            }

        }

        private void CmiRenameDatabase_Click(object sender, EventArgs e)
        {
            var newName = MessageBoxEx.Input($"请输入新数据库名，原名：{SelectedDatabase}");
            if (string.IsNullOrEmpty(newName)) { return; }

            try
            {
                databaseService.RenameDatabase(SelectedDatabase, newName);
                UpdateDatabases();
                MessageBoxEx.Show("重命名成功！");
            } catch (Exception ex)
            {
                MessageBoxEx.Error($"发生了错误：{ex.Message}");
            }
        }

        private void CmiAddDatabase_Click(object sender, EventArgs e)
        {
            var dbName = MessageBoxEx.Input("请输入新数据库名");
            if (string.IsNullOrEmpty(dbName)) { return; }

            try
            {
                databaseService.CreateDatabase(dbName);
                UpdateDatabases();

            }
            catch (Exception ex)
            {
                MessageBoxEx.Error($"发生了错误：{ex.Message}");
            }
        }

    }
}
