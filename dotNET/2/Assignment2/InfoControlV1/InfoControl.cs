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
