using System;
using System.IO;
using System.Windows.Forms;

namespace InfoControl
{
    public delegate void FileChangedHandler(string path);
    public partial class InfoControl : UserControl
    {
        public event FileChangedHandler FileChanged;

        public InfoControl()
        {
            InitializeComponent();
        }

        private void button1_Click(object sender, EventArgs e)
        {
            FolderBrowserDialog dialog = new FolderBrowserDialog();
            if (dialog.ShowDialog() == DialogResult.OK)
            {
                var path = dialog.SelectedPath;
                ListDirectory(path);
            }

        }


        private void TreeViewDirectory_NodeMouseDoubleClick(object sender, TreeNodeMouseClickEventArgs e)
        {
            var file = e.Node.Name;

            if (Directory.Exists(file))
            {
                MessageBox.Show("请选择文件！");
                return;
            }

            if (UpdateFileDetail(file))
            {
                FileChanged(file);
            }

        }

        public bool UpdateFileDetail(string path)
        {
            var file = new FileInfo(path);

            listViewDetail.BeginUpdate();
            listViewDetail.Items.Clear();
            AddInfoItem("文件名", file.Name);
            AddInfoItem("文件格式", file.Extension);
            AddInfoItem("文件大小", file.Length / 1024.0 / 1024.0 + " MB");
            AddInfoItem("创建日期", file.CreationTime.ToString());
            AddInfoItem("修改日期", file.LastWriteTime.ToString());
            listViewDetail.EndUpdate();
            return true;
        }

        private void AddInfoItem(string key, string value)
        {
            listViewDetail.Items.Add(new ListViewItem(new string[] { key, value }));
        }



        private void ListDirectory(string path)
        {
            treeViewDirectory.Nodes.Clear();
            var rootDirectoryInfo = new DirectoryInfo(path);
            treeViewDirectory.Nodes.Add(CreateDirectoryNode(rootDirectoryInfo));
        }

        private TreeNode CreateDirectoryNode(DirectoryInfo directoryInfo)
        {
            var directoryNode = new TreeNode(directoryInfo.Name)
            {
                Name = directoryInfo.FullName
            };
            foreach (var directory in directoryInfo.GetDirectories())
            {
                directoryNode.Nodes.Add(CreateDirectoryNode(directory));
            }
            foreach (var file in directoryInfo.GetFiles())
            {

                directoryNode.Nodes.Add(new TreeNode(file.Name)
                {
                    Name = file.FullName
                });
            }

            return directoryNode;
        }

        private void InfoControl_Load(object sender, EventArgs e)
        {

        }
    }
}
