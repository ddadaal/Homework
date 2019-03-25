namespace Assignment4
{
    partial class TableForm
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
            this.tvData = new System.Windows.Forms.TreeView();
            this.btnAddButton = new System.Windows.Forms.Button();
            this.dgData = new System.Windows.Forms.DataGridView();
            this.btnDeleteNode = new System.Windows.Forms.Button();
            this.btnSaveTree = new System.Windows.Forms.Button();
            this.btnReadTree = new System.Windows.Forms.Button();
            this.btnSaveToXml = new System.Windows.Forms.Button();
            this.btnSaveGridToDb = new System.Windows.Forms.Button();
            ((System.ComponentModel.ISupportInitialize)(this.dgData)).BeginInit();
            this.SuspendLayout();
            // 
            // tvData
            // 
            this.tvData.Location = new System.Drawing.Point(12, 13);
            this.tvData.Margin = new System.Windows.Forms.Padding(3, 4, 3, 4);
            this.tvData.Name = "tvData";
            this.tvData.Size = new System.Drawing.Size(428, 471);
            this.tvData.TabIndex = 0;
            this.tvData.AfterSelect += new System.Windows.Forms.TreeViewEventHandler(this.tvData_AfterSelect);
            // 
            // btnAddButton
            // 
            this.btnAddButton.Location = new System.Drawing.Point(12, 504);
            this.btnAddButton.Name = "btnAddButton";
            this.btnAddButton.Size = new System.Drawing.Size(118, 35);
            this.btnAddButton.TabIndex = 1;
            this.btnAddButton.Text = "增加节点";
            this.btnAddButton.UseVisualStyleBackColor = true;
            this.btnAddButton.Click += new System.EventHandler(this.btnAddButton_Click);
            // 
            // dgData
            // 
            this.dgData.ColumnHeadersHeightSizeMode = System.Windows.Forms.DataGridViewColumnHeadersHeightSizeMode.AutoSize;
            this.dgData.Location = new System.Drawing.Point(463, 13);
            this.dgData.Name = "dgData";
            this.dgData.RowTemplate.Height = 27;
            this.dgData.Size = new System.Drawing.Size(425, 471);
            this.dgData.TabIndex = 2;
            // 
            // btnDeleteNode
            // 
            this.btnDeleteNode.Location = new System.Drawing.Point(136, 505);
            this.btnDeleteNode.Name = "btnDeleteNode";
            this.btnDeleteNode.Size = new System.Drawing.Size(98, 34);
            this.btnDeleteNode.TabIndex = 3;
            this.btnDeleteNode.Text = "删除节点";
            this.btnDeleteNode.UseVisualStyleBackColor = true;
            this.btnDeleteNode.Click += new System.EventHandler(this.btnDeleteNode_Click);
            // 
            // btnSaveTree
            // 
            this.btnSaveTree.Location = new System.Drawing.Point(240, 505);
            this.btnSaveTree.Name = "btnSaveTree";
            this.btnSaveTree.Size = new System.Drawing.Size(98, 34);
            this.btnSaveTree.TabIndex = 4;
            this.btnSaveTree.Text = "保存树";
            this.btnSaveTree.UseVisualStyleBackColor = true;
            this.btnSaveTree.Click += new System.EventHandler(this.btnSaveTree_Click);
            // 
            // btnReadTree
            // 
            this.btnReadTree.Location = new System.Drawing.Point(344, 504);
            this.btnReadTree.Name = "btnReadTree";
            this.btnReadTree.Size = new System.Drawing.Size(96, 38);
            this.btnReadTree.TabIndex = 5;
            this.btnReadTree.Text = "读入树";
            this.btnReadTree.UseVisualStyleBackColor = true;
            this.btnReadTree.Click += new System.EventHandler(this.btnReadTree_Click);
            // 
            // btnSaveToXml
            // 
            this.btnSaveToXml.Location = new System.Drawing.Point(620, 505);
            this.btnSaveToXml.Name = "btnSaveToXml";
            this.btnSaveToXml.Size = new System.Drawing.Size(96, 38);
            this.btnSaveToXml.TabIndex = 6;
            this.btnSaveToXml.Text = "保存为XML";
            this.btnSaveToXml.UseVisualStyleBackColor = true;
            this.btnSaveToXml.Click += new System.EventHandler(this.btnSaveToXml_Click);
            // 
            // btnSaveGridToDb
            // 
            this.btnSaveGridToDb.Location = new System.Drawing.Point(489, 504);
            this.btnSaveGridToDb.Name = "btnSaveGridToDb";
            this.btnSaveGridToDb.Size = new System.Drawing.Size(113, 38);
            this.btnSaveGridToDb.TabIndex = 7;
            this.btnSaveGridToDb.Text = "保存表格到数据库";
            this.btnSaveGridToDb.UseVisualStyleBackColor = true;
            this.btnSaveGridToDb.Click += new System.EventHandler(this.btnSaveGridToDb_Click);
            // 
            // TableForm
            // 
            this.AutoScaleDimensions = new System.Drawing.SizeF(7F, 17F);
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
            this.ClientSize = new System.Drawing.Size(905, 556);
            this.Controls.Add(this.btnSaveGridToDb);
            this.Controls.Add(this.btnSaveToXml);
            this.Controls.Add(this.btnReadTree);
            this.Controls.Add(this.btnSaveTree);
            this.Controls.Add(this.btnDeleteNode);
            this.Controls.Add(this.dgData);
            this.Controls.Add(this.btnAddButton);
            this.Controls.Add(this.tvData);
            this.Font = new System.Drawing.Font("Microsoft YaHei", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.Margin = new System.Windows.Forms.Padding(3, 4, 3, 4);
            this.Name = "TableForm";
            this.Text = "Table";
            this.Load += new System.EventHandler(this.Table_Load);
            ((System.ComponentModel.ISupportInitialize)(this.dgData)).EndInit();
            this.ResumeLayout(false);

        }

        #endregion

        private System.Windows.Forms.TreeView tvData;
        private System.Windows.Forms.Button btnAddButton;
        private System.Windows.Forms.DataGridView dgData;
        private System.Windows.Forms.Button btnDeleteNode;
        private System.Windows.Forms.Button btnSaveTree;
        private System.Windows.Forms.Button btnReadTree;
        private System.Windows.Forms.Button btnSaveToXml;
        private System.Windows.Forms.Button btnSaveGridToDb;
    }
}