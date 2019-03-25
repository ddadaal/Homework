namespace Assignment4
{
    partial class AddTableForm
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
            this.btnChangeType = new System.Windows.Forms.Button();
            this.btnChangeCol = new System.Windows.Forms.Button();
            this.btnDelete = new System.Windows.Forms.Button();
            this.btnAdd = new System.Windows.Forms.Button();
            this.lvAttributes = new System.Windows.Forms.ListView();
            this.colColumn = ((System.Windows.Forms.ColumnHeader)(new System.Windows.Forms.ColumnHeader()));
            this.colType = ((System.Windows.Forms.ColumnHeader)(new System.Windows.Forms.ColumnHeader()));
            this.btnCancel = new System.Windows.Forms.Button();
            this.btnConfirm = new System.Windows.Forms.Button();
            this.label2 = new System.Windows.Forms.Label();
            this.tbTableName = new System.Windows.Forms.TextBox();
            this.label1 = new System.Windows.Forms.Label();
            this.SuspendLayout();
            // 
            // btnChangeType
            // 
            this.btnChangeType.Location = new System.Drawing.Point(314, 421);
            this.btnChangeType.Margin = new System.Windows.Forms.Padding(3, 4, 3, 4);
            this.btnChangeType.Name = "btnChangeType";
            this.btnChangeType.Size = new System.Drawing.Size(87, 33);
            this.btnChangeType.TabIndex = 22;
            this.btnChangeType.Text = "修改类型";
            this.btnChangeType.UseVisualStyleBackColor = true;
            this.btnChangeType.Click += new System.EventHandler(this.btnChangeType_Click);
            // 
            // btnChangeCol
            // 
            this.btnChangeCol.Location = new System.Drawing.Point(224, 421);
            this.btnChangeCol.Margin = new System.Windows.Forms.Padding(3, 4, 3, 4);
            this.btnChangeCol.Name = "btnChangeCol";
            this.btnChangeCol.Size = new System.Drawing.Size(87, 33);
            this.btnChangeCol.TabIndex = 21;
            this.btnChangeCol.Text = "修改列";
            this.btnChangeCol.UseVisualStyleBackColor = true;
            this.btnChangeCol.Click += new System.EventHandler(this.btnChangeCol_Click);
            // 
            // btnDelete
            // 
            this.btnDelete.Location = new System.Drawing.Point(116, 421);
            this.btnDelete.Margin = new System.Windows.Forms.Padding(3, 4, 3, 4);
            this.btnDelete.Name = "btnDelete";
            this.btnDelete.Size = new System.Drawing.Size(87, 33);
            this.btnDelete.TabIndex = 20;
            this.btnDelete.Text = "删除选中列";
            this.btnDelete.UseVisualStyleBackColor = true;
            this.btnDelete.Click += new System.EventHandler(this.btnDelete_Click);
            // 
            // btnAdd
            // 
            this.btnAdd.Location = new System.Drawing.Point(21, 421);
            this.btnAdd.Margin = new System.Windows.Forms.Padding(3, 4, 3, 4);
            this.btnAdd.Name = "btnAdd";
            this.btnAdd.Size = new System.Drawing.Size(87, 33);
            this.btnAdd.TabIndex = 19;
            this.btnAdd.Text = "新增列";
            this.btnAdd.UseVisualStyleBackColor = true;
            this.btnAdd.Click += new System.EventHandler(this.btnAdd_Click);
            // 
            // lvAttributes
            // 
            this.lvAttributes.Columns.AddRange(new System.Windows.Forms.ColumnHeader[] {
            this.colColumn,
            this.colType});
            this.lvAttributes.Location = new System.Drawing.Point(21, 108);
            this.lvAttributes.Margin = new System.Windows.Forms.Padding(3, 4, 3, 4);
            this.lvAttributes.MultiSelect = false;
            this.lvAttributes.Name = "lvAttributes";
            this.lvAttributes.Size = new System.Drawing.Size(380, 265);
            this.lvAttributes.TabIndex = 18;
            this.lvAttributes.UseCompatibleStateImageBehavior = false;
            this.lvAttributes.View = System.Windows.Forms.View.Details;
            // 
            // colColumn
            // 
            this.colColumn.Text = "列";
            this.colColumn.Width = 150;
            // 
            // colType
            // 
            this.colType.Text = "类型";
            this.colType.Width = 150;
            // 
            // btnCancel
            // 
            this.btnCancel.Location = new System.Drawing.Point(224, 477);
            this.btnCancel.Margin = new System.Windows.Forms.Padding(3, 4, 3, 4);
            this.btnCancel.Name = "btnCancel";
            this.btnCancel.Size = new System.Drawing.Size(181, 52);
            this.btnCancel.TabIndex = 17;
            this.btnCancel.Text = "取消";
            this.btnCancel.UseVisualStyleBackColor = true;
            this.btnCancel.Click += new System.EventHandler(this.btnCancel_Click);
            // 
            // btnConfirm
            // 
            this.btnConfirm.Location = new System.Drawing.Point(21, 477);
            this.btnConfirm.Margin = new System.Windows.Forms.Padding(3, 4, 3, 4);
            this.btnConfirm.Name = "btnConfirm";
            this.btnConfirm.Size = new System.Drawing.Size(181, 52);
            this.btnConfirm.TabIndex = 16;
            this.btnConfirm.Text = "保存";
            this.btnConfirm.UseVisualStyleBackColor = true;
            this.btnConfirm.Click += new System.EventHandler(this.btnConfirm_Click);
            // 
            // label2
            // 
            this.label2.AutoSize = true;
            this.label2.Location = new System.Drawing.Point(22, 87);
            this.label2.Name = "label2";
            this.label2.Size = new System.Drawing.Size(20, 17);
            this.label2.TabIndex = 15;
            this.label2.Text = "列";
            // 
            // tbTableName
            // 
            this.tbTableName.Location = new System.Drawing.Point(25, 45);
            this.tbTableName.Margin = new System.Windows.Forms.Padding(3, 4, 3, 4);
            this.tbTableName.Name = "tbTableName";
            this.tbTableName.Size = new System.Drawing.Size(380, 23);
            this.tbTableName.TabIndex = 14;
            this.tbTableName.TextChanged += new System.EventHandler(this.tbTagName_TextChanged);
            // 
            // label1
            // 
            this.label1.AutoSize = true;
            this.label1.Location = new System.Drawing.Point(21, 16);
            this.label1.Name = "label1";
            this.label1.Size = new System.Drawing.Size(32, 17);
            this.label1.TabIndex = 13;
            this.label1.Text = "表名";
            // 
            // AddTableForm
            // 
            this.AutoScaleDimensions = new System.Drawing.SizeF(7F, 17F);
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
            this.ClientSize = new System.Drawing.Size(417, 555);
            this.Controls.Add(this.btnChangeType);
            this.Controls.Add(this.btnChangeCol);
            this.Controls.Add(this.btnDelete);
            this.Controls.Add(this.btnAdd);
            this.Controls.Add(this.lvAttributes);
            this.Controls.Add(this.btnCancel);
            this.Controls.Add(this.btnConfirm);
            this.Controls.Add(this.label2);
            this.Controls.Add(this.tbTableName);
            this.Controls.Add(this.label1);
            this.Font = new System.Drawing.Font("Microsoft YaHei", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.Margin = new System.Windows.Forms.Padding(3, 4, 3, 4);
            this.Name = "AddTableForm";
            this.Text = "AddTableForm";
            this.Load += new System.EventHandler(this.AddTableForm_Load);
            this.ResumeLayout(false);
            this.PerformLayout();

        }

        #endregion

        private System.Windows.Forms.Button btnChangeType;
        private System.Windows.Forms.Button btnChangeCol;
        private System.Windows.Forms.Button btnDelete;
        private System.Windows.Forms.Button btnAdd;
        private System.Windows.Forms.ListView lvAttributes;
        private System.Windows.Forms.ColumnHeader colColumn;
        private System.Windows.Forms.ColumnHeader colType;
        private System.Windows.Forms.Button btnCancel;
        private System.Windows.Forms.Button btnConfirm;
        private System.Windows.Forms.Label label2;
        private System.Windows.Forms.TextBox tbTableName;
        private System.Windows.Forms.Label label1;
    }
}