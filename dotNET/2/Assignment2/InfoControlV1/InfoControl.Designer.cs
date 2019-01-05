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
            this.SuspendLayout();
            // 
            // treeViewDirectory
            // 
            this.treeViewDirectory.Location = new System.Drawing.Point(0, 0);
            this.treeViewDirectory.Margin = new System.Windows.Forms.Padding(3, 4, 3, 4);
            this.treeViewDirectory.Name = "treeViewDirectory";
            this.treeViewDirectory.Size = new System.Drawing.Size(408, 352);
            this.treeViewDirectory.TabIndex = 2;
            this.treeViewDirectory.NodeMouseDoubleClick += TreeViewDirectory_NodeMouseDoubleClick;
            // 
            // btnChoosePath
            // 
            this.btnChoosePath.Location = new System.Drawing.Point(0, 727);
            this.btnChoosePath.Margin = new System.Windows.Forms.Padding(3, 4, 3, 4);
            this.btnChoosePath.Name = "btnChoosePath";
            this.btnChoosePath.Size = new System.Drawing.Size(408, 52);
            this.btnChoosePath.TabIndex = 3;
            this.btnChoosePath.Text = "选择目录";
            this.btnChoosePath.UseVisualStyleBackColor = true;
            this.btnChoosePath.Click += new System.EventHandler(this.button1_Click);
            // 
            // InfoControl
            // 
            this.AutoScaleDimensions = new System.Drawing.SizeF(7F, 17F);
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
            this.Controls.Add(this.btnChoosePath);
            this.Controls.Add(this.treeViewDirectory);
            this.Margin = new System.Windows.Forms.Padding(3, 4, 3, 4);
            this.Name = "InfoControl";
            this.Size = new System.Drawing.Size(408, 779);
            this.Load += new System.EventHandler(this.InfoControl_Load);
            this.ResumeLayout(false);

        }

        #endregion

        private System.Windows.Forms.TreeView treeViewDirectory;
        private System.Windows.Forms.Button btnChoosePath;
    }
}
