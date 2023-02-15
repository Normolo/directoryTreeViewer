import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class DirectoryTreeGenerator extends JFrame {

    private static final long serialVersionUID = 1L;
    private JTextField folderPath;
    private JTextField filePath;
    
    public DirectoryTreeGenerator() {
        // Set up frame
        setTitle("Directory Tree Generator");
        setSize(500, 150);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        // Add panel for folder path
        JPanel folderPanel = new JPanel(new BorderLayout());
        JLabel folderLabel = new JLabel("Folder:");
        folderPath = new JTextField(30);
        JButton folderButton = new JButton("Select...");
        folderButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser chooser = new JFileChooser();
                chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
                int result = chooser.showOpenDialog(DirectoryTreeGenerator.this);
                if (result == JFileChooser.APPROVE_OPTION) {
                    File selectedFolder = chooser.getSelectedFile();
                    folderPath.setText(selectedFolder.getAbsolutePath());
                }
            }
        });
        folderPanel.add(folderLabel, BorderLayout.WEST);
        folderPanel.add(folderPath, BorderLayout.CENTER);
        folderPanel.add(folderButton, BorderLayout.EAST);
        
        // Add panel for file path
        JPanel filePanel = new JPanel(new BorderLayout());
        JLabel fileLabel = new JLabel("File:");
        filePath = new JTextField(30);
        filePath.setText("dir_tree.txt");
        filePanel.add(fileLabel, BorderLayout.WEST);
        filePanel.add(filePath, BorderLayout.CENTER);
        
        // Add generate button
        JButton generateButton = new JButton("Generate");
        generateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String folder = folderPath.getText();
                String file = filePath.getText();
                try {
                    // Check if folder exists
                    File folderFile = new File(folder);
                    if (!folderFile.exists() || !folderFile.isDirectory()) {
                        JOptionPane.showMessageDialog(DirectoryTreeGenerator.this, "Selected folder does not exist.");
                        return;
                    }
                    // Check if file exists and prompt to overwrite if necessary
                    File outputFile = new File(file);
                    if (outputFile.exists()) {
                        int choice = JOptionPane.showConfirmDialog(DirectoryTreeGenerator.this, "Output file already exists. Overwrite?", "Confirm Overwrite", JOptionPane.YES_NO_OPTION);
                        if (choice != JOptionPane.YES_OPTION) {
                            return;
                        }
                    }
                    // Generate directory tree and write to file
                    PrintWriter writer = new PrintWriter(new FileWriter(outputFile));
                    Runtime runtime = Runtime.getRuntime();
                    Process process = runtime.exec("cmd /c tree /a /f \"" + folder + "\"");
                    process.getOutputStream().close();
                    java.util.Scanner scanner = new java.util.Scanner(process.getInputStream());
                    while (scanner.hasNextLine()) {
                        writer.println(scanner.nextLine());
                    }
                    scanner.close();
                    writer.close();
                    // Open output file
                    runtime.exec("notepad \"" + file + "\"");
                } catch (IOException ex) {
                    JOptionPane.showMessageDialog(DirectoryTreeGenerator.this, "Error generating directory tree: " + ex.getMessage());
                }
            }
        });
        
        // Add panels and button to frame
        JPanel mainPanel = new JPanel(new GridLayout(3, 1));
        mainPanel.add(folderPanel);
        mainPanel.add(filePanel);
        mainPanel.add(generateButton);
        setContentPane(mainPanel);
    }
    
    public static void main(String[] args) {
        DirectoryTreeGenerator frame = new DirectoryTreeGenerator();
        frame.setVisible(true);
    }
}    
