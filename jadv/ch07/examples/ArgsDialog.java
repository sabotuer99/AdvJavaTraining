package examples;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.Constructor;
import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableModel;

public class ArgsDialog extends JDialog {
  private static final long serialVersionUID = 1L;
  private JTable tblArgs;
  private JButton btnOk;
  private JButton btnCancel;
  private Class<?>[] paramTypes;
  private Object[] args;

  public ArgsDialog(Frame parent, Class<?>[] pTypes) {
    super(parent, "Enter Arguments", true);

    paramTypes = pTypes;
    args = new Object[paramTypes.length];
    initArgs();

    setBounds(100, 100, 400, 300);

    Container content = getContentPane();
    content.setLayout(new BorderLayout());

    JPanel pnlButtons = new JPanel();

    btnOk = new JButton("Ok");
    btnOk.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent ae) {
        dispose();
      }
    });
    pnlButtons.add(btnOk);

    btnCancel = new JButton("Cancel");
    btnCancel.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent ae) {
        initArgs();
        dispose();
      }
    });
    pnlButtons.add(btnCancel);

    content.add(pnlButtons, BorderLayout.SOUTH);

    TableModel model = new AbstractTableModel() {
      private static final long serialVersionUID = 1L;

      public int getColumnCount() {
        return 2;
      }

      public int getRowCount() {
        return args.length;
      }

      public Object getValueAt(int row, int col) {
        Object value = null;
        switch (col) {
        case 0:
          value = paramTypes[row].getName();
          break;
        case 1:
          value = args[row];
          break;
        }
        return value;
      }

      public String getColumnName(int col) {
        String name = null;
        switch (col) {
        case 0:
          name = "Type";
          break;
        case 1:
          name = "Value";
          break;
        }
        return name;
      }

      public boolean isCellEditable(int row, int col) {
        return (col == 1);
      }

      public void setValueAt(Object value, int row, int col) {
        String sValue = value.toString();
        if (paramTypes[row] == String.class)
          args[row] = sValue;
        else if (paramTypes[row] == boolean.class)
          args[row] = new Boolean(sValue);
        else if (paramTypes[row] == byte.class)
          args[row] = new Byte(sValue);
        else if (paramTypes[row] == char.class)
          args[row] = sValue.charAt(0);
        else if (paramTypes[row] == double.class)
          args[row] = new Double(sValue);
        else if (paramTypes[row] == float.class)
          args[row] = new Float(sValue);
        else if (paramTypes[row] == int.class)
          args[row] = new Integer(sValue);
        else if (paramTypes[row] == long.class)
          args[row] = new Long(sValue);
        else if (paramTypes[row] == short.class)
          args[row] = new Short(sValue);
        else {
          try {
            Object[] iargs = new Object[] { value };
            Constructor<?> ct = paramTypes[row]
                .getConstructor(new Class[] { String.class });
            args[row] = ct.newInstance(iargs);
          }
          catch (Exception e) {
            args[row] = value;
          }
        }
      }
    };
    tblArgs = new JTable(model);
    JScrollPane scrollPane = new JScrollPane(tblArgs);
    content.add(scrollPane, BorderLayout.CENTER);
  }

  public Object[] getArgs() {
    return args;
  }

  private void initArgs() {
    for (int i = 0; i < args.length; i++) {
      try {
        args[i] = paramTypes[i].newInstance();
      }
      catch (Exception e) {
        args[i] = null;
      }
    }
  }
}
