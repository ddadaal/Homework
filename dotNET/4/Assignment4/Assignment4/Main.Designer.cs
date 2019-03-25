namespace Assignment4
{
    partial class Main
    {
        /// <summary>
        /// Required designer variable.
        /// </summary>
        private System.ComponentModel.IContainer components = null;

        /// <summary>
        /// Clean up any resources being used.
        /// </summary>
        /// <param name="disposing">true if managed resources should be disposed; otherwise, false.</param>
        protected override void Dispose(bool disposing)
        {
            if (disposing && (components != null))
            {
                components.Dispose();
            }
            base.Dispose(disposing);
        }

        #region Windows Form Designer generated code

        /// <summary>
        /// Required method for Designer support - do not modify
        /// the contents of this method with the code editor.
        /// </summary>
        private void InitializeComponent()
        {
            this.components = new System.ComponentModel.Container();
            this.label1 = new System.Windows.Forms.Label();
            this.label2 = new System.Windows.Forms.Label();
            this.lbTables = new System.Windows.Forms.ListBox();
            this.lbDatabases = new System.Windows.Forms.ListBox();
            this.cmDatabase = new System.Windows.Forms.ContextMenuStrip(this.components);
            this.cmiAddDatabase = new System.Windows.Forms.ToolStripMenuItem();
            this.cmiRenameDatabase = new System.Windows.Forms.ToolStripMenuItem();
            this.cmiDeleteDatabase = new System.Windows.Forms.ToolStripMenuItem();
            this.cmiUpdateDatabase = new System.Windows.Forms.ToolStripMenuItem();
            this.label3 = new System.Windows.Forms.Label();
            this.cmTable = new System.Windows.Forms.ContextMenuStrip(this.components);
            this.cmiAddTable = new System.Windows.Forms.ToolStripMenuItem();
            this.cmiRenameTable = new System.Windows.Forms.ToolStripMenuItem();
            this.cmiDeleteTable = new System.Windows.Forms.ToolStripMenuItem();
            this.cmiUpdateTable = new System.Windows.Forms.ToolStripMenuItem();
            this.cmiSelectTable = new System.Windows.Forms.ToolStripMenuItem();
            this.cmDatabase.SuspendLayout();
            this.cmTable.SuspendLayout();
            this.SuspendLayout();
            // 
            // label1
            // 
            this.label1.AutoSize = true;
            this.label1.Location = new System.Drawing.Point(12, 8);
            this.label1.Name = "label1";
            this.label1.Size = new System.Drawing.Size(54, 20);
            this.label1.TabIndex = 2;
            this.label1.Text = "数据库";
            // 
            // label2
            // 
            this.label2.AutoSize = true;
            this.label2.Location = new System.Drawing.Point(325, 8);
            this.label2.Name = "label2";
            this.label2.Size = new System.Drawing.Size(24, 20);
            this.label2.TabIndex = 3;
            this.label2.Text = "表";
            // 
            // lbTables
            // 
            this.lbTables.ContextMenuStrip = this.cmTable;
            this.lbTables.FormattingEnabled = true;
            this.lbTables.ItemHeight = 20;
            this.lbTables.Location = new System.Drawing.Point(329, 32);
            this.lbTables.Name = "lbTables";
            this.lbTables.Size = new System.Drawing.Size(548, 544);
            this.lbTables.TabIndex = 1;
            this.lbTables.SelectedIndexChanged += new System.EventHandler(this.lbTables_SelectedIndexChanged);
            // 
            // lbDatabases
            // 
            this.lbDatabases.ContextMenuStrip = this.cmDatabase;
            this.lbDatabases.FormattingEnabled = true;
            this.lbDatabases.ItemHeight = 20;
            this.lbDatabases.Location = new System.Drawing.Point(12, 32);
            this.lbDatabases.Margin = new System.Windows.Forms.Padding(3, 4, 3, 4);
            this.lbDatabases.Name = "lbDatabases";
            this.lbDatabases.Size = new System.Drawing.Size(311, 544);
            this.lbDatabases.TabIndex = 0;
            this.lbDatabases.SelectedIndexChanged += new System.EventHandler(this.lbDatabases_SelectedIndexChanged);
            // 
            // cmDatabase
            // 
            this.cmDatabase.ImageScalingSize = new System.Drawing.Size(20, 20);
            this.cmDatabase.Items.AddRange(new System.Windows.Forms.ToolStripItem[] {
            this.cmiAddDatabase,
            this.cmiRenameDatabase,
            this.cmiDeleteDatabase,
            this.cmiUpdateDatabase});
            this.cmDatabase.Name = "cmDatabase";
            this.cmDatabase.Size = new System.Drawing.Size(169, 100);
            // 
            // cmiAddDatabase
            // 
            this.cmiAddDatabase.Name = "cmiAddDatabase";
            this.cmiAddDatabase.Size = new System.Drawing.Size(168, 24);
            this.cmiAddDatabase.Text = "创建数据库";
            // 
            // cmiRenameDatabase
            // 
            this.cmiRenameDatabase.Name = "cmiRenameDatabase";
            this.cmiRenameDatabase.Size = new System.Drawing.Size(168, 24);
            this.cmiRenameDatabase.Text = "重命名数据库";
            // 
            // cmiDeleteDatabase
            // 
            this.cmiDeleteDatabase.Name = "cmiDeleteDatabase";
            this.cmiDeleteDatabase.Size = new System.Drawing.Size(168, 24);
            this.cmiDeleteDatabase.Text = "删除数据库";
            // 
            // cmiUpdateDatabase
            // 
            this.cmiUpdateDatabase.Name = "cmiUpdateDatabase";
            this.cmiUpdateDatabase.Size = new System.Drawing.Size(168, 24);
            this.cmiUpdateDatabase.Text = "刷新数据库";
            // 
            // label3
            // 
            this.label3.AutoSize = true;
            this.label3.Location = new System.Drawing.Point(12, 589);
            this.label3.Name = "label3";
            this.label3.Size = new System.Drawing.Size(249, 20);
            this.label3.TabIndex = 4;
            this.label3.Text = "右击列表或者列表项以执行更多活动";
            // 
            // cmTable
            // 
            this.cmTable.ImageScalingSize = new System.Drawing.Size(20, 20);
            this.cmTable.Items.AddRange(new System.Windows.Forms.ToolStripItem[] {
            this.cmiSelectTable,
            this.cmiAddTable,
            this.cmiRenameTable,
            this.cmiDeleteTable,
            this.cmiUpdateTable});
            this.cmTable.Name = "cmTable";
            this.cmTable.Size = new System.Drawing.Size(139, 124);
            // 
            // cmiAddTable
            // 
            this.cmiAddTable.Name = "cmiAddTable";
            this.cmiAddTable.Size = new System.Drawing.Size(138, 24);
            this.cmiAddTable.Text = "增加表";
            // 
            // cmiRenameTable
            // 
            this.cmiRenameTable.Name = "cmiRenameTable";
            this.cmiRenameTable.Size = new System.Drawing.Size(138, 24);
            this.cmiRenameTable.Text = "重命名表";
            // 
            // cmiDeleteTable
            // 
            this.cmiDeleteTable.Name = "cmiDeleteTable";
            this.cmiDeleteTable.Size = new System.Drawing.Size(138, 24);
            this.cmiDeleteTable.Text = "删除表";
            // 
            // cmiUpdateTable
            // 
            this.cmiUpdateTable.Name = "cmiUpdateTable";
            this.cmiUpdateTable.Size = new System.Drawing.Size(138, 24);
            this.cmiUpdateTable.Text = "刷新表";
            // 
            // cmiSelectTable
            // 
            this.cmiSelectTable.Name = "cmiSelectTable";
            this.cmiSelectTable.Size = new System.Drawing.Size(138, 24);
            this.cmiSelectTable.Text = "选择表";
            // 
            // Main
            // 
            this.AutoScaleDimensions = new System.Drawing.SizeF(9F, 20F);
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
            this.ClientSize = new System.Drawing.Size(885, 617);
            this.Controls.Add(this.label3);
            this.Controls.Add(this.label2);
            this.Controls.Add(this.label1);
            this.Controls.Add(this.lbTables);
            this.Controls.Add(this.lbDatabases);
            this.Font = new System.Drawing.Font("Microsoft YaHei", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.Margin = new System.Windows.Forms.Padding(3, 4, 3, 4);
            this.Name = "Main";
            this.Text = "Assignment4";
            this.Load += new System.EventHandler(this.Main_Load);
            this.cmDatabase.ResumeLayout(false);
            this.cmTable.ResumeLayout(false);
            this.ResumeLayout(false);
            this.PerformLayout();

        }

        #endregion
        private System.Windows.Forms.Label label1;
        private System.Windows.Forms.Label label2;
        private System.Windows.Forms.ListBox lbTables;
        private System.Windows.Forms.ListBox lbDatabases;
        private System.Windows.Forms.Label label3;
        private System.Windows.Forms.ContextMenuStrip cmDatabase;
        private System.Windows.Forms.ToolStripMenuItem cmiAddDatabase;
        private System.Windows.Forms.ToolStripMenuItem cmiRenameDatabase;
        private System.Windows.Forms.ToolStripMenuItem cmiDeleteDatabase;
        private System.Windows.Forms.ToolStripMenuItem cmiUpdateDatabase;
        private System.Windows.Forms.ContextMenuStrip cmTable;
        private System.Windows.Forms.ToolStripMenuItem cmiAddTable;
        private System.Windows.Forms.ToolStripMenuItem cmiRenameTable;
        private System.Windows.Forms.ToolStripMenuItem cmiDeleteTable;
        private System.Windows.Forms.ToolStripMenuItem cmiUpdateTable;
        private System.Windows.Forms.ToolStripMenuItem cmiSelectTable;
    }
}

