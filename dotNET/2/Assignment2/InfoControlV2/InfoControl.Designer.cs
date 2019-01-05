namespace InfoControl
{
    partial class InfoControl
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

        #region Component Designer generated code

        /// <summary>
        /// Required method for Designer support - do not modify 
        /// the contents of this method with the code editor.
        /// </summary>
        private void InitializeComponent()
        {
            this.treeViewDirectory = new System.Windows.Forms.TreeView();
            this.btnChoosePath = new System.Windows.Forms.Button();
            this.listViewDetail = new System.Windows.Forms.ListView();
            this.colKey = ((System.Windows.Forms.ColumnHeader)(new System.Windows.Forms.ColumnHeader()));
            this.colValue = ((System.Windows.Forms.ColumnHeader)(new System.Windows.Forms.ColumnHeader()));
            this.SuspendLayout();
            // 
            // treeViewDirectory
            // 
            this.treeViewDirectory.Location = new System.Drawing.Point(0, 0);
            this.treeViewDirectory.Name = "treeViewDirectory";
            this.treeViewDirectory.Size = new System.Drawing.Size(350, 250);
            this.treeViewDirectory.TabIndex = 7;
            this.treeViewDirectory.NodeMouseDoubleClick += TreeViewDirectory_NodeMouseDoubleClick;
            // 
            // btnChoosePath
            // 
            this.btnChoosePath.Location = new System.Drawing.Point(0, 513);
            this.btnChoosePath.Name = "btnChoosePath";
            this.btnChoosePath.Size = new System.Drawing.Size(350, 37);
            this.btnChoosePath.TabIndex = 8;
            this.btnChoosePath.Text = "选择目录";
            this.btnChoosePath.UseVisualStyleBackColor = true;
            this.btnChoosePath.Click += button1_Click;
            // 
            // listViewDetail
            // 
            this.listViewDetail.Columns.AddRange(new System.Windows.Forms.ColumnHeader[] {
            this.colKey,
            this.colValue});
            this.listViewDetail.Location = new System.Drawing.Point(0, 277);
            this.listViewDetail.Name = "listViewDetail";
            this.listViewDetail.Size = new System.Drawing.Size(350, 221);
            this.listViewDetail.TabIndex = 9;
            this.listViewDetail.UseCompatibleStateImageBehavior = false;
            this.listViewDetail.View = System.Windows.Forms.View.Details;
            // 
            // colKey
            // 
            this.colKey.Text = "项";
            // 
            // colValue
            // 
            this.colValue.Text = "值";
            // 
            // InfoControl
            // 
            this.AutoScaleDimensions = new System.Drawing.SizeF(6F, 12F);
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
            this.Controls.Add(this.listViewDetail);
            this.Controls.Add(this.btnChoosePath);
            this.Controls.Add(this.treeViewDirectory);
            this.Name = "InfoControl";
            this.Size = new System.Drawing.Size(350, 550);
            this.Load += new System.EventHandler(this.InfoControl_Load);
            this.ResumeLayout(false);

        }

        #endregion
        private System.Windows.Forms.TreeView treeViewDirectory;
        private System.Windows.Forms.Button btnChoosePath;
        private System.Windows.Forms.ListView listViewDetail;
        private System.Windows.Forms.ColumnHeader colKey;
        private System.Windows.Forms.ColumnHeader colValue;
    }
}
