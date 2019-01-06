namespace Assignment3
{
    partial class NodeForm
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
            this.label1 = new System.Windows.Forms.Label();
            this.tbTagName = new System.Windows.Forms.TextBox();
            this.label2 = new System.Windows.Forms.Label();
            this.btnConfirm = new System.Windows.Forms.Button();
            this.btnCancel = new System.Windows.Forms.Button();
            this.lvAttributes = new System.Windows.Forms.ListView();
            this.colKey = ((System.Windows.Forms.ColumnHeader)(new System.Windows.Forms.ColumnHeader()));
            this.colValue = ((System.Windows.Forms.ColumnHeader)(new System.Windows.Forms.ColumnHeader()));
            this.tbType = new System.Windows.Forms.TextBox();
            this.lbType = new System.Windows.Forms.Label();
            this.btnAdd = new System.Windows.Forms.Button();
            this.btnDelete = new System.Windows.Forms.Button();
            this.btnChangeKey = new System.Windows.Forms.Button();
            this.btnChangeValue = new System.Windows.Forms.Button();
            this.SuspendLayout();
            // 
            // label1
            // 
            this.label1.AutoSize = true;
            this.label1.Location = new System.Drawing.Point(12, 68);
            this.label1.Name = "label1";
            this.label1.Size = new System.Drawing.Size(44, 17);
            this.label1.TabIndex = 0;
            this.label1.Text = "标签名";
            // 
            // tbTagName
            // 
            this.tbTagName.Location = new System.Drawing.Point(15, 88);
            this.tbTagName.Name = "tbTagName";
            this.tbTagName.Size = new System.Drawing.Size(326, 23);
            this.tbTagName.TabIndex = 1;
            this.tbTagName.TextChanged += new System.EventHandler(this.tbTagName_TextChanged);
            // 
            // label2
            // 
            this.label2.AutoSize = true;
            this.label2.Location = new System.Drawing.Point(12, 131);
            this.label2.Name = "label2";
            this.label2.Size = new System.Drawing.Size(56, 17);
            this.label2.TabIndex = 2;
            this.label2.Text = "属性列表";
            // 
            // btnConfirm
            // 
            this.btnConfirm.Location = new System.Drawing.Point(12, 393);
            this.btnConfirm.Name = "btnConfirm";
            this.btnConfirm.Size = new System.Drawing.Size(155, 37);
            this.btnConfirm.TabIndex = 4;
            this.btnConfirm.Text = "保存";
            this.btnConfirm.UseVisualStyleBackColor = true;
            this.btnConfirm.Click += new System.EventHandler(this.btnConfirm_Click);
            // 
            // btnCancel
            // 
            this.btnCancel.Location = new System.Drawing.Point(186, 393);
            this.btnCancel.Name = "btnCancel";
            this.btnCancel.Size = new System.Drawing.Size(155, 37);
            this.btnCancel.TabIndex = 5;
            this.btnCancel.Text = "取消";
            this.btnCancel.UseVisualStyleBackColor = true;
            this.btnCancel.Click += new System.EventHandler(this.btnCancel_Click);
            // 
            // lvAttributes
            // 
            this.lvAttributes.Columns.AddRange(new System.Windows.Forms.ColumnHeader[] {
            this.colKey,
            this.colValue});
            this.lvAttributes.Location = new System.Drawing.Point(12, 151);
            this.lvAttributes.MultiSelect = false;
            this.lvAttributes.Name = "lvAttributes";
            this.lvAttributes.Size = new System.Drawing.Size(326, 188);
            this.lvAttributes.TabIndex = 6;
            this.lvAttributes.UseCompatibleStateImageBehavior = false;
            this.lvAttributes.View = System.Windows.Forms.View.Details;
            // 
            // colKey
            // 
            this.colKey.Text = "属性名";
            this.colKey.Width = 150;
            // 
            // colValue
            // 
            this.colValue.Text = "属性值";
            this.colValue.Width = 150;
            // 
            // tbType
            // 
            this.tbType.Location = new System.Drawing.Point(15, 29);
            this.tbType.Name = "tbType";
            this.tbType.ReadOnly = true;
            this.tbType.Size = new System.Drawing.Size(326, 23);
            this.tbType.TabIndex = 8;
            // 
            // lbType
            // 
            this.lbType.AutoSize = true;
            this.lbType.Location = new System.Drawing.Point(12, 9);
            this.lbType.Name = "lbType";
            this.lbType.Size = new System.Drawing.Size(56, 17);
            this.lbType.TabIndex = 7;
            this.lbType.Text = "节点类型";
            // 
            // btnAdd
            // 
            this.btnAdd.Location = new System.Drawing.Point(12, 354);
            this.btnAdd.Name = "btnAdd";
            this.btnAdd.Size = new System.Drawing.Size(75, 23);
            this.btnAdd.TabIndex = 9;
            this.btnAdd.Text = "新增属性";
            this.btnAdd.UseVisualStyleBackColor = true;
            this.btnAdd.Click += new System.EventHandler(this.btnAdd_Click);
            // 
            // btnDelete
            // 
            this.btnDelete.Location = new System.Drawing.Point(93, 354);
            this.btnDelete.Name = "btnDelete";
            this.btnDelete.Size = new System.Drawing.Size(75, 23);
            this.btnDelete.TabIndex = 10;
            this.btnDelete.Text = "删除选中属性";
            this.btnDelete.UseVisualStyleBackColor = true;
            this.btnDelete.Click += new System.EventHandler(this.btnDelete_Click);
            // 
            // btnChangeKey
            // 
            this.btnChangeKey.Location = new System.Drawing.Point(186, 354);
            this.btnChangeKey.Name = "btnChangeKey";
            this.btnChangeKey.Size = new System.Drawing.Size(75, 23);
            this.btnChangeKey.TabIndex = 11;
            this.btnChangeKey.Text = "修改名";
            this.btnChangeKey.UseVisualStyleBackColor = true;
            this.btnChangeKey.Click += new System.EventHandler(this.btnChangeKey_Click);
            // 
            // btnChangeValue
            // 
            this.btnChangeValue.Location = new System.Drawing.Point(263, 354);
            this.btnChangeValue.Name = "btnChangeValue";
            this.btnChangeValue.Size = new System.Drawing.Size(75, 23);
            this.btnChangeValue.TabIndex = 12;
            this.btnChangeValue.Text = "修改值";
            this.btnChangeValue.UseVisualStyleBackColor = true;
            this.btnChangeValue.Click += new System.EventHandler(this.btnChangeValue_Click);
            // 
            // NodeForm
            // 
            this.AutoScaleDimensions = new System.Drawing.SizeF(7F, 17F);
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
            this.ClientSize = new System.Drawing.Size(350, 442);
            this.Controls.Add(this.btnChangeValue);
            this.Controls.Add(this.btnChangeKey);
            this.Controls.Add(this.btnDelete);
            this.Controls.Add(this.btnAdd);
            this.Controls.Add(this.tbType);
            this.Controls.Add(this.lbType);
            this.Controls.Add(this.lvAttributes);
            this.Controls.Add(this.btnCancel);
            this.Controls.Add(this.btnConfirm);
            this.Controls.Add(this.label2);
            this.Controls.Add(this.tbTagName);
            this.Controls.Add(this.label1);
            this.Font = new System.Drawing.Font("Microsoft YaHei", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.Margin = new System.Windows.Forms.Padding(3, 4, 3, 4);
            this.MaximizeBox = false;
            this.Name = "NodeForm";
            this.Text = "节点信息";
            this.Load += new System.EventHandler(this.NodeForm_Load);
            this.ResumeLayout(false);
            this.PerformLayout();

        }

        #endregion

        private System.Windows.Forms.Label label1;
        private System.Windows.Forms.TextBox tbTagName;
        private System.Windows.Forms.Label label2;
        private System.Windows.Forms.Button btnConfirm;
        private System.Windows.Forms.Button btnCancel;
        private System.Windows.Forms.ListView lvAttributes;
        private System.Windows.Forms.ColumnHeader colKey;
        private System.Windows.Forms.ColumnHeader colValue;
        private System.Windows.Forms.TextBox tbType;
        private System.Windows.Forms.Label lbType;
        private System.Windows.Forms.Button btnAdd;
        private System.Windows.Forms.Button btnDelete;
        private System.Windows.Forms.Button btnChangeKey;
        private System.Windows.Forms.Button btnChangeValue;
    }
}