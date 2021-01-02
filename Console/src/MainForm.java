import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class MainForm
{
      JPanel mainPanel;
    private JButton Enter;
    private JTextPane Console;
    private JTextPane PATH;


    public MainForm()
    {
        Enter.addActionListener(new Action() {
            @Override
            public Object getValue(String key) {
                return null;
            }

            @Override
            public void putValue(String key, Object value) {

            }

            @Override
            public void setEnabled(boolean b) {

            }

            @Override
            public boolean isEnabled() {
                return false;
            }

            @Override
            public void addPropertyChangeListener(PropertyChangeListener listener) {

            }

            @Override
            public void removePropertyChangeListener(PropertyChangeListener listener) {

            }

            @Override
            public void actionPerformed(ActionEvent e)
            {

                String commands=Console.getText();
//            System.out.println("Choose actions  and enter one of the commands: ADD,  LIST, EXIT ");
//
//            JOptionPane.showMessageDialog("l","Choose actions  and enter one of the commands: ADD,  LIST, EXIT ",
//                    "Ошибка!",JOptionPane.PLAIN_MESSAGE);
//                    BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
                    switch (commands)
                    {
                        case ("sizeFile"): {
                            //System.out.println("Enter email");

                            String path = PATH.getText();
                            File file = new File(path);
                            //File file = new File("src/test.txt");

                            if(file.exists()){
                                JOptionPane.showMessageDialog(mainPanel,ConsoleCommand.getFileSizeBytes(file),
                                        "sizeFile",JOptionPane.PLAIN_MESSAGE);
                                JOptionPane.showMessageDialog(mainPanel,ConsoleCommand.getFileSizeKiloBytes(file),
                                        "sizeFile",JOptionPane.PLAIN_MESSAGE);
                                JOptionPane.showMessageDialog(mainPanel,ConsoleCommand.getFileSizeMegaBytes(file),
                                        "sizeFile",JOptionPane.PLAIN_MESSAGE);
                            }else
                            JOptionPane.showMessageDialog(mainPanel,"Файла нет!",
                                    "sizeFile",JOptionPane.PLAIN_MESSAGE);

                            //File[] files = file2.listFiles();

                          //  System.out.println("=========================");
                            break;
                        }
                        case ("sizeFolder"):
                        {
                            String path = PATH.getText();
                            File file = new File(path);

                            double sizeFolder= ConsoleCommand.getFolderSize(file);

                            if(sizeFolder>(1024*1024*1024))
                                JOptionPane.showMessageDialog(mainPanel, sizeFolder/(1024*1024*1024)+" Gb",
                                        "sizeFolder",JOptionPane.PLAIN_MESSAGE);
                            else if(sizeFolder>1024*1024)

                                JOptionPane.showMessageDialog(mainPanel, sizeFolder/(1024*1024)+" Mb",
                                        "sizeFolder",JOptionPane.PLAIN_MESSAGE);
                            else if(sizeFolder>1024)
                                JOptionPane.showMessageDialog(mainPanel, sizeFolder/(1024)+" kb",
                                        "sizeFolder",JOptionPane.PLAIN_MESSAGE);

                            break;
                        }

                        case ("help"):
                            {
                           // System.out.println("ADD"+"\n"+"LIST"+"\n"+"EXIT");
                            JOptionPane.showMessageDialog(mainPanel,"data"+"\n"+"help"+"\n"+"list"+"\n"+"sizeFile"+"\n"+"sizeFolder",
                    "help",JOptionPane.PLAIN_MESSAGE);
                            break;
                        }
                        case ("list"):
                        {
                            String path = PATH.getText();
                            File file = new File(path);
                            ConsoleCommand.Process(file);
                            break;
                        }
                        case ("data"):
                        {
                            Locale rus = new  Locale("ru");
                            DateFormat dateFormat = new SimpleDateFormat("HH:mm - dd.MM.yyyy - EEEEE", rus);
                            Calendar calendar = Calendar.getInstance();
                            Date your = calendar.getTime();
                            JOptionPane.showMessageDialog(mainPanel,"Today "+dateFormat.format(your),
                                    "Data",JOptionPane.PLAIN_MESSAGE);

                            break;
                        }

                        default: {
                         //  System.out.println("SORRY! Try again.");
                            JOptionPane.showMessageDialog(mainPanel,"SORRY! Try again.",
                                    "Ошибка!",JOptionPane.PLAIN_MESSAGE);
                        }
                    }


            }
        });
    }

    public JPanel getMainPanel()
    {
        return mainPanel;
    }
}
