package lab;

import java.awt.Container;
import java.awt.Dimension;
import java.io.File;
import java.net.InetAddress;
import java.net.UnknownHostException;

import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTree;
import javax.swing.UIManager;
import javax.swing.event.TreeExpansionEvent;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.event.TreeWillExpandListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.ExpandVetoException;
import javax.swing.tree.TreeNode;

/**
 * 
 */
/**
 * @author Jerric
 * @created Jan 27, 2006
 */
public class DiskExplorer extends JFrame implements TreeSelectionListener,
		TreeWillExpandListener {

	/**
	 * The MyFile is a decorator to File, and to display file name with full
	 * path in the toString()
	 * 
	 * @author Jerric
	 * @created Jan 27, 2006
	 */
	private class MyFile {

		private File file;

		public MyFile(File file) {
			this.file = file;
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see java.lang.Object#toString()
		 */
		@Override
		public String toString() {
			String name = file.getName().trim();
			if (name.length() == 0) {
				name = file.getAbsolutePath().trim();
			}
			return name;
		}
	}

	/**
	 * @param args
	 * @throws UnknownHostException
	 */
	public static void main(String[] args) throws UnknownHostException {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception e) {
			System.err.println("Couldn't use system look and feel.");
		}
		JFrame.setDefaultLookAndFeelDecorated(true);

		DiskExplorer explorer = new DiskExplorer();
		explorer.setExtendedState(MAXIMIZED_BOTH);
		explorer.setVisible(true);
	}

	private TreeNode dirRoot;
	@SuppressWarnings("rawtypes")
	private DefaultListModel fileList;
	/**
     * 
     */
	private static final long serialVersionUID = 40432233890429375L;

	public DiskExplorer() throws UnknownHostException {
		super("Disk Explorer");
		this.setPreferredSize(new Dimension(800, 600));
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// load directory list
		dirRoot = iterateDisk();
		this.setContentPane(createContent());
		this.pack();
	}

	@SuppressWarnings("rawtypes")
	private Container createContent() {
		// create navigation tree
		JTree dirTree = new JTree(dirRoot);
		dirTree.addTreeSelectionListener(this);
		dirTree.addTreeWillExpandListener(this);
		JScrollPane pnlTree = new JScrollPane(dirTree);
		pnlTree.setPreferredSize(new Dimension(300, 500));

		// create file list
		fileList = new DefaultListModel();
		@SuppressWarnings("unchecked")
		JList lstFile = new JList(fileList);
		JScrollPane pnlFile = new JScrollPane(lstFile);

		// create the content panel as a split panel
		JSplitPane pnlContent = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,
				pnlTree, pnlFile);
		return pnlContent;
	}

	private TreeNode iterateDisk() throws UnknownHostException {
		// get machine name
		InetAddress local = InetAddress.getLocalHost();
		// create root
		DefaultMutableTreeNode root = new DefaultMutableTreeNode(
				local.getHostName());

		// first try all drives - windows only
		for (char label = 'A'; label < 'Z'; label++) {
			File driveFile = new File(label + ":\\");
			if (driveFile.exists()) {
				DefaultMutableTreeNode driveNode = new DefaultMutableTreeNode(
						new MyFile(driveFile));
				iterateDir(driveFile, driveNode);
				root.add(driveNode);
			}
		}
		return root;
	}

	private void iterateDir(File parentDir, DefaultMutableTreeNode parentNode) {
		File[] files = parentDir.listFiles();
		if (files == null) {
			return;
		}
		for (File file : files) {
			if (file.exists() && file.isDirectory()) {
				DefaultMutableTreeNode dirNode = new DefaultMutableTreeNode(
						new MyFile(file));
				// create a child stub
				dirNode.add(new DefaultMutableTreeNode("Stub"));
				parentNode.add(dirNode);
			}
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * javax.swing.event.TreeSelectionListener#valueChanged(javax.swing.event
	 * .TreeSelectionEvent)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public void valueChanged(TreeSelectionEvent e) {
		// get the list of files in the current directory
		DefaultMutableTreeNode node = (DefaultMutableTreeNode) e.getPath()
				.getLastPathComponent();
		if (node == null) {
			return;
		}

		Object obj = node.getUserObject();
		if (!(obj instanceof MyFile)) {
			return; // selected the very root
		}
		File dir = ((MyFile) obj).file;
		File[] files = dir.listFiles();
		fileList.removeAllElements();
		for (File file : files) {
			if (file.exists() && file.isFile()) {
				fileList.addElement(new MyFile(file));
			}
		}
	}

	@Override
	public void treeWillExpand(TreeExpansionEvent e) throws ExpandVetoException {
		DefaultMutableTreeNode node = (DefaultMutableTreeNode) e.getPath()
				.getLastPathComponent();
		if (node == null) {
			return;
		}

		// get the children of the node
		int count = node.getChildCount();
		for (int i = 0; i < count; i++) {
			DefaultMutableTreeNode child = (DefaultMutableTreeNode) node
					.getChildAt(i);
			// check if the child has a stub
			if (child.getChildCount() != 1) {
				continue;
			}

			Object obj = ((DefaultMutableTreeNode) child.getChildAt(0))
					.getUserObject();
			if (!(obj instanceof MyFile)) { // a stub found
				child.removeAllChildren();
				File childFile = ((MyFile) child.getUserObject()).file;
				iterateDir(childFile, child);
			}
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * javax.swing.event.TreeWillExpandListener#treeWillCollapse(javax.swing
	 * .event.TreeExpansionEvent)
	 */
	@Override
	public void treeWillCollapse(TreeExpansionEvent event)
			throws ExpandVetoException {
	}
}