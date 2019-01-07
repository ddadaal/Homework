using System;
using System.IO;
using System.Windows.Forms;

namespace InfoControl
{
    public partial class InfoControl : UserControl
    {

        public InfoControl()
        {
            InitializeComponent();
        }

        public void SetFilePath(string path)
        {
            ListDirectory(path);
            UpdateFileDetail(path);
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
            var dir = Path.GetDirectoryName(path);
            treeViewDirectory.Nodes.Clear();
            var rootDirectoryInfo = new DirectoryInfo(dir);
            treeViewDirectory.Nodes.Add(CreateDirectoryNode(rootDirectoryInfo));
            treeViewDirectory.ExpandAll();
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
