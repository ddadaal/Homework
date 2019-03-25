using System;
using System.Windows.Forms;

namespace Assignment4
{
    public partial class Main
    {
        public void BindTableContextMenuEvents()
        {
            cmTable.Opening += CmTable_Opening;
            cmiSelectTable.Click += CmiSelectTable_Click;
            cmiAddTable.Click += CmiAddTable_Click;
            cmiRenameTable.Click += CmiRenameTable_Click;
            cmiDeleteTable.Click += CmiDeleteTable_Click;
            cmiUpdateTable.Click += CmiUpdateTable_Click;
            lbTables.MouseDoubleClick += LbTables_MouseDoubleClick;
        }

        private void LbTables_MouseDoubleClick(object sender, MouseEventArgs e)
        {
            if (SelectedTable == null) { return; }
            ShowTableForm(SelectedDatabase, SelectedTable);
        }

        private void CmiSelectTable_Click(object sender, EventArgs e)
        {
            ShowTableForm(SelectedDatabase, SelectedTable);
        }

        private void ShowTableForm(string database, string table)
        {
            var form = new TableForm(databaseService, database, table);
            form.Show();
        }

        private void CmTable_Opening(object sender, System.ComponentModel.CancelEventArgs e)
        {
            bool databaseSelected = SelectedDatabase != null;
            bool tableSelected = SelectedTable != null;

            cmiSelectTable.Enabled = databaseSelected && tableSelected;
            cmiAddTable.Enabled = databaseSelected;
            cmiUpdateTable.Enabled = databaseSelected;
            cmiRenameTable.Enabled = databaseSelected && tableSelected;
            cmiDeleteTable.Enabled = databaseSelected && tableSelected;
        }

        private void CmiDeleteTable_Click(object sender, EventArgs e)
        {
            if (MessageBox.Show($"真的要删除{SelectedDatabase}数据库的{SelectedTable}表吗？", "提示",
                    MessageBoxButtons.YesNo, MessageBoxIcon.Exclamation) == DialogResult.Yes)
            {
                try
                {
                    databaseService.DeleteTable(SelectedDatabase, SelectedTable);
                    lbTables.SelectedItem = null;
                    UpdateTables();
                    MessageBoxEx.Show("删除成功！");


                }
                catch (Exception ex)
                {
                    MessageBoxEx.Error($"发生了错误：{ex.Message}");
                }

            }
        }

        private void CmiUpdateTable_Click(object sender, EventArgs e)
        {
            UpdateTables();
        }

        private void CmiRenameTable_Click(object sender, EventArgs e)
        {
            var newName = MessageBoxEx.Input($"请输入新表名，原名：{SelectedTable}");
            if (string.IsNullOrEmpty(newName)) { return; }

            try
            {
                databaseService.RenameTable(SelectedDatabase, SelectedTable, newName);
                UpdateTables();
                MessageBoxEx.Show("重命名成功！");
            }
            catch (Exception ex)
            {
                MessageBoxEx.Error($"发生了错误：{ex.Message}");
            }
        }

        private void CmiAddTable_Click(object sender, EventArgs e)
        {
            var form = new AddTableForm(SelectedDatabase, databaseService);
            form.Confirmed += () =>
            {
                UpdateTables();
            };
            form.Show();


        }
    }
}
