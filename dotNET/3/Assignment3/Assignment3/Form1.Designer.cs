namespace Assignment3
{
    partial class Form1
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
            this.treeViewXml = new System.Windows.Forms.TreeView();
            this.btnBrowse = new System.Windows.Forms.Button();
            this.tbFilePath = new System.Windows.Forms.TextBox();
            this.btnSave = new System.Windows.Forms.Button();
            this.ctxmNodeOp = new System.Windows.Forms.ContextMenuStrip(this.components);
            this.ctxmItemAdd = new System.Windows.Forms.ToolStripMenuItem();
            this.ctxmItemModify = new System.Windows.Forms.ToolStripMenuItem();
            this.ctxmItemDelete = new System.Windows.Forms.ToolStripMenuItem();
            this.label1 = new System.Windows.Forms.Label();
            this.tbAddRoot = new System.Windows.Forms.Button();
            this.ctxmItemAddElement = new System.Windows.Forms.ToolStripMenuItem();
            this.ctxmItemAddText = new System.Windows.Forms.ToolStripMenuItem();
            this.ctxmItemType = new System.Windows.Forms.ToolStripTextBox();
            this.toolStripSeparator1 = new System.Windows.Forms.ToolStripSeparator();
            this.ctxmNodeOp.SuspendLayout();
            this.SuspendLayout();
            // 
            // treeViewXml
            // 
            this.treeViewXml.Location = new System.Drawing.Point(12, 65);
            this.treeViewXml.Margin = new System.Windows.Forms.Padding(3, 4, 3, 4);
            this.treeViewXml.Name = "treeViewXml";
            this.treeViewXml.Size = new System.Drawing.Size(876, 518);
            this.treeViewXml.TabIndex = 0;
            // 
            // btnBrowse
            // 
            this.btnBrowse.Location = new System.Drawing.Point(698, 13);
            this.btnBrowse.Margin = new System.Windows.Forms.Padding(3, 4, 3, 4);
            this.btnBrowse.Name = "btnBrowse";
            this.btnBrowse.Size = new System.Drawing.Size(92, 44);
            this.btnBrowse.TabIndex = 1;
            this.btnBrowse.Text = "浏览";
            this.btnBrowse.UseVisualStyleBackColor = true;
            this.btnBrowse.Click += new System.EventHandler(this.btnBrowse_Click);
            // 
            // tbFilePath
            // 
            this.tbFilePath.Location = new System.Drawing.Point(12, 24);
            this.tbFilePath.Name = "tbFilePath";
            this.tbFilePath.ReadOnly = true;
            this.tbFilePath.Size = new System.Drawing.Size(680, 23);
            this.tbFilePath.TabIndex = 3;
            // 
            // btnSave
            // 
            this.btnSave.Location = new System.Drawing.Point(796, 13);
            this.btnSave.Margin = new System.Windows.Forms.Padding(3, 4, 3, 4);
            this.btnSave.Name = "btnSave";
            this.btnSave.Size = new System.Drawing.Size(92, 44);
            this.btnSave.TabIndex = 4;
            this.btnSave.Text = "保存";
            this.btnSave.UseVisualStyleBackColor = true;
            this.btnSave.Click += new System.EventHandler(this.btnSave_Click);
            // 
            // ctxmNodeOp
            // 
            this.ctxmNodeOp.Items.AddRange(new System.Windows.Forms.ToolStripItem[] {
            this.ctxmItemType,
            this.toolStripSeparator1,
            this.ctxmItemAdd,
            this.ctxmItemModify,
            this.ctxmItemDelete});
            this.ctxmNodeOp.Name = "ctxmNodeOp";
            this.ctxmNodeOp.Size = new System.Drawing.Size(185, 101);
            // 
            // ctxmItemAdd
            // 
            this.ctxmItemAdd.DropDownItems.AddRange(new System.Windows.Forms.ToolStripItem[] {
            this.ctxmItemAddElement,
            this.ctxmItemAddText});
            this.ctxmItemAdd.Name = "ctxmItemAdd";
            this.ctxmItemAdd.Size = new System.Drawing.Size(184, 22);
            this.ctxmItemAdd.Text = "增加此节点的子节点";
            // 
            // ctxmItemModify
            // 
            this.ctxmItemModify.Name = "ctxmItemModify";
            this.ctxmItemModify.Size = new System.Drawing.Size(184, 22);
            this.ctxmItemModify.Text = "修改此节点";
            // 
            // ctxmItemDelete
            // 
            this.ctxmItemDelete.Name = "ctxmItemDelete";
            this.ctxmItemDelete.Size = new System.Drawing.Size(184, 22);
            this.ctxmItemDelete.Text = "删除此节点";
            // 
            // label1
            // 
            this.label1.AutoSize = true;
            this.label1.Location = new System.Drawing.Point(12, 596);
            this.label1.Name = "label1";
            this.label1.Size = new System.Drawing.Size(238, 17);
            this.label1.TabIndex = 6;
            this.label1.Text = "加载XML后，右键点击某个节点进行操作。";
            this.label1.Click += new System.EventHandler(this.label1_Click);
            // 
            // tbAddRoot
            // 
            this.tbAddRoot.Enabled = false;
            this.tbAddRoot.Location = new System.Drawing.Point(789, 589);
            this.tbAddRoot.Name = "tbAddRoot";
            this.tbAddRoot.Size = new System.Drawing.Size(99, 30);
            this.tbAddRoot.TabIndex = 7;
            this.tbAddRoot.Text = "增加根节点";
            this.tbAddRoot.UseVisualStyleBackColor = true;
            this.tbAddRoot.Click += new System.EventHandler(this.tbAddRoot_Click);
            // 
            // ctxmItemAddElement
            // 
            this.ctxmItemAddElement.Name = "ctxmItemAddElement";
            this.ctxmItemAddElement.Size = new System.Drawing.Size(180, 22);
            this.ctxmItemAddElement.Text = "普通节点";
            // 
            // ctxmItemAddText
            // 
            this.ctxmItemAddText.Name = "ctxmItemAddText";
            this.ctxmItemAddText.Size = new System.Drawing.Size(180, 22);
            this.ctxmItemAddText.Text = "纯文本节点";
            // 
            // ctxmItemType
            // 
            this.ctxmItemType.Name = "ctxmItemType";
            this.ctxmItemType.ReadOnly = true;
            this.ctxmItemType.Size = new System.Drawing.Size(100, 23);
            // 
            // toolStripSeparator1
            // 
            this.toolStripSeparator1.Name = "toolStripSeparator1";
            this.toolStripSeparator1.Size = new System.Drawing.Size(181, 6);
            // 
            // Form1
            // 
            this.AutoScaleDimensions = new System.Drawing.SizeF(7F, 17F);
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
            this.ClientSize = new System.Drawing.Size(893, 624);
            this.Controls.Add(this.tbAddRoot);
            this.Controls.Add(this.label1);
            this.Controls.Add(this.btnSave);
            this.Controls.Add(this.tbFilePath);
            this.Controls.Add(this.btnBrowse);
            this.Controls.Add(this.treeViewXml);
            this.Font = new System.Drawing.Font("Microsoft YaHei", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.Margin = new System.Windows.Forms.Padding(3, 4, 3, 4);
            this.MaximizeBox = false;
            this.Name = "Form1";
            this.Text = "Assignment3";
            this.Load += new System.EventHandler(this.Form1_Load);
            this.ctxmNodeOp.ResumeLayout(false);
            this.ctxmNodeOp.PerformLayout();
            this.ResumeLayout(false);
            this.PerformLayout();

        }

        #endregion
        private System.Windows.Forms.TreeView treeViewXml;
        private System.Windows.Forms.Button btnBrowse;
        private System.Windows.Forms.TextBox tbFilePath;
        private System.Windows.Forms.Button btnSave;
        private System.Windows.Forms.ContextMenuStrip ctxmNodeOp;
        private System.Windows.Forms.ToolStripMenuItem ctxmItemAdd;
        private System.Windows.Forms.ToolStripMenuItem ctxmItemModify;
        private System.Windows.Forms.ToolStripMenuItem ctxmItemDelete;
        private System.Windows.Forms.Label label1;
        private System.Windows.Forms.Button tbAddRoot;
        private System.Windows.Forms.ToolStripMenuItem ctxmItemAddElement;
        private System.Windows.Forms.ToolStripMenuItem ctxmItemAddText;
        private System.Windows.Forms.ToolStripTextBox ctxmItemType;
        private System.Windows.Forms.ToolStripSeparator toolStripSeparator1;
    }
}

