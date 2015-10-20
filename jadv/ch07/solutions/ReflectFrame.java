package solutions;

import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;

public class ReflectFrame extends JFrame {
  private static final long serialVersionUID = 1L;
  private JList<String> lstClasses;
  private JList<Constructor<?>> lstCtors;
  private JList<Object> lstObjects;
  private JList<Method> lstMethods;
  private List<Object> objects;
  private List<String> classes;
  private IReflect engine;

  public ReflectFrame(IReflect engine) {
    super("Reflection Test Bed");
    this.engine = engine;
    initWindow();
  }
  private void initWindow() {
    int col1 = 150;
    int col2 = 600;
    int col3 = 250;
    int col4 = 500;

    setBounds(50,50,800,410);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    JMenuBar mb = new JMenuBar();
    JMenu mnuFile = new JMenu("File");
    mnuFile.setMnemonic(KeyEvent.VK_F);
    mb.add(mnuFile);
    JMenuItem mniExit = new JMenuItem("Exit");
    mniExit.setMnemonic(KeyEvent.VK_X);
    mniExit.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent ae) {
        ReflectFrame.this.dispose();
      }
    });
    mnuFile.add(mniExit);
    JMenu mnuClasses = new JMenu("Classes");
    mnuClasses.setMnemonic(KeyEvent.VK_C);
    mb.add(mnuClasses);
    JMenuItem mniAddClass = new JMenuItem("Add Class ...");
    mniAddClass.setMnemonic(KeyEvent.VK_A);
    mniAddClass.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent ae) {
        String className = JOptionPane.showInputDialog(
          ReflectFrame.this, "Enter the fully qualified class name");
        if (className != null && !className.equals("")) {
          try {
            classes.add(className);
            lstClasses.setListData(new Vector<String>(classes));
          }
          catch (Exception e) {
            JOptionPane.showMessageDialog(ReflectFrame.this,
              className + " is not a valid class.", "Add Class Error",
              JOptionPane.ERROR_MESSAGE);
          }
        }
      }
    });
    mnuClasses.add(mniAddClass);
    setJMenuBar(mb);

    Container content = getContentPane();
    content.setLayout(null);

    // Build classes components
    JLabel lbl = new JLabel("Classes");
    lbl.setBounds(10,10,col1,12);
    lbl.setHorizontalAlignment(JLabel.CENTER);
    content.add(lbl);
    lstClasses = new JList<>();
    classes = engine.getClasses();
    lstClasses.setListData(new Vector<String>(classes));
    JScrollPane scrollPane = new JScrollPane(lstClasses);
    scrollPane.setBounds(10,30,col1,100);
    content.add(scrollPane);
    JButton button = new JButton("Show Constructors");
    button.setBounds(10,150,col1,20);
    button.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent ae) {
        String className = (String) lstClasses.getSelectedValue();
        if (className != null)
          lstCtors.setListData(new Vector<Constructor<?>>(engine.getConstructors(className)));
      }
    });
    content.add(button);

    // Build constructors components
    lbl = new JLabel("Constructors");
    lbl.setBounds(col1+30, 10, col2, 12);
    lbl.setHorizontalAlignment(JLabel.CENTER);
    content.add(lbl);
    lstCtors = new JList<>();
    scrollPane = new JScrollPane(lstCtors);
    scrollPane.setBounds(col1+30, 30, col2, 100);
    content.add(scrollPane);
    button = new JButton("Instantiate");
    button.setBounds(col1+30,150,col2,20);
    button.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent ae) {
        Constructor<?> ctor = (Constructor<?>) lstCtors.getSelectedValue();
        if (ctor != null) {
          objects.add(engine.instantiate(ctor, getArgs(ctor)));
          lstObjects.setListData(new Vector<Object>(objects));
        }
        else {
          JOptionPane.showMessageDialog(ReflectFrame.this,
            "Please select a class.", "Instantiate Error",
            JOptionPane.ERROR_MESSAGE);
        }
      }
    });
    content.add(button);

    // Build object components
    objects = new ArrayList<Object>();
    lbl = new JLabel("Objects");
    lbl.setBounds(10, 190, col3, 12);
    lbl.setHorizontalAlignment(JLabel.CENTER);
    content.add(lbl);
    lstObjects = new JList<>();
    scrollPane = new JScrollPane(lstObjects);
    scrollPane.setBounds(10, 210, col3, 100);
    content.add(scrollPane);
    button = new JButton("Show Methods");
    button.setBounds(10,330,col3,20);
    button.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent ae) {
        Object o = lstObjects.getSelectedValue();
        if (o != null) {
          lstMethods.setListData(new Vector<Method>(engine.getMethods(o)));
        }
        else {
          JOptionPane.showMessageDialog(ReflectFrame.this,
            "Please select an object.", "Show Methods Error",
            JOptionPane.ERROR_MESSAGE);
        }

      }
    });
    content.add(button);

    // Build method components
    lbl = new JLabel("Methods");
    lbl.setBounds(col3+30, 190, col4, 12);
    lbl.setHorizontalAlignment(JLabel.CENTER);
    content.add(lbl);
    lstMethods = new JList<>();
    scrollPane = new JScrollPane(lstMethods);
    scrollPane.setBounds(col3+30, 210, col4, 100);
    content.add(scrollPane);
    button = new JButton("Invoke");
    button.setBounds(col3+30,330,col4,20);
    button.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent ae) {
        Method method = (Method) lstMethods.getSelectedValue();
        Object target = lstObjects.getSelectedValue();
        if (method != null && target != null) {
          Object retValue = engine.invoke(method, target,
            getArgs(method));
          // show the return value
          JOptionPane.showMessageDialog(ReflectFrame.this,
            "Result: " + retValue, "Method Result",
            JOptionPane.INFORMATION_MESSAGE);
        }
        else {
          JOptionPane.showMessageDialog(ReflectFrame.this,
            "Please select an object and a method.", "Invoke Error",
            JOptionPane.ERROR_MESSAGE);
        }
      }
    });
    content.add(button);
  }
  private Object[] getArgs(Object o) {
    Object[] args = null;
    Class<?>[] paramTypes = null;
    if (o instanceof Constructor) {
      paramTypes = ((Constructor<?>) o).getParameterTypes();
    }
    else if (o instanceof Method) {
      paramTypes = ((Method) o).getParameterTypes();
    }

    if (paramTypes.length == 0) {
      args = new Object[0];
    }
    else {
      // display a dialog box get arguments
      ArgsDialog dialog = new ArgsDialog(this, paramTypes);
      dialog.setVisible(true);
      args = dialog.getArgs();
    }
    return args;
  }
}
