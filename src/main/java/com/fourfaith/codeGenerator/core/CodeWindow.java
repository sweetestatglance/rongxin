//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.fourfaith.codeGenerator.core;

//import com.fourfaith.codeGenerator.core.CodeWindow.1;
//import com.fourfaith.codeGenerator.core.CodeWindow.2;
import java.awt.Dimension;
import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class CodeWindow extends JFrame {
    private static final long serialVersionUID = -5324160085184088010L;
    public static String entityPackage = "";
    private static String entityName = "TestEntity";
    private static String tableName = "t00_company";
    private static String ftlDescription = "分公司";
    private static int fieldRowNum = 1;
    private static String primaryKeyPolicy = "uuid";
    private static String sequenceCode = "";
    String[] planets = new String[]{"uuid", "identity", "sequence"};

    public CodeWindow() {
        JPanel jp = new JPanel();
        this.setContentPane(jp);
        jp.setLayout(new GridLayout(10, 2));
        JLabel infolbl = new JLabel("提示:");
        JLabel showlbl = new JLabel();
        JLabel packagebl = new JLabel("jsp文件名（小写）：");
        JTextField packagefld = new JTextField();
        JLabel entitylbl = new JLabel("实体类名（首字母大写）：");
        JTextField entityfld = new JTextField();
        JLabel tablejbl = new JLabel("表名：");
        JTextField tablefld = new JTextField(20);
        JLabel titlelbl = new JLabel("功能描述：");
        JTextField titlefld = new JTextField();
        JCheckBox actionButton = new JCheckBox("Controller");
        actionButton.setSelected(true);
        JCheckBox daoImplButton = new JCheckBox("Mapper.xml");
        daoImplButton.setSelected(false);
        JCheckBox serviceIButton = new JCheckBox("Service");
        serviceIButton.setSelected(true);
        JCheckBox serviceImplButton = new JCheckBox("ServiceImpl");
        serviceImplButton.setSelected(true);
        JCheckBox daoButton = new JCheckBox("Mapper");
        daoButton.setSelected(true);
        JCheckBox entityButton = new JCheckBox("Entity");
        entityButton.setSelected(true);
        jp.add(infolbl);
        jp.add(showlbl);
        jp.add(packagebl);
        jp.add(packagefld);
        jp.add(entitylbl);
        jp.add(entityfld);
        jp.add(tablejbl);
        jp.add(tablefld);
        jp.add(titlelbl);
        jp.add(titlefld);
        jp.add(actionButton);
        jp.add(daoImplButton);
        jp.add(serviceIButton);
        jp.add(serviceImplButton);
        jp.add(daoButton);
        jp.add(entityButton);
        JButton confirmbtn = new JButton("生成");
        //confirmbtn.addActionListener(new 1(this, confirmbtn, packagefld, showlbl, entityfld, titlefld, tablefld, actionButton, daoButton, daoImplButton, serviceIButton, serviceImplButton, entityButton));
        JButton extbtn = new JButton("退出");
        //extbtn.addActionListener(new 2(this));
        jp.add(confirmbtn);
        jp.add(extbtn);
        this.setTitle("四信代码生成器");
        this.setVisible(true);
        this.setDefaultCloseOperation(3);
        this.setSize(new Dimension(400, 200));
        this.setResizable(false);
        this.setLocationRelativeTo(this.getOwner());
    }

    public static void main(String[] args) {
        try {
            (new CodeWindow()).pack();
        } catch (Exception var2) {
            System.out.println(var2.getMessage());
        }

    }
}
