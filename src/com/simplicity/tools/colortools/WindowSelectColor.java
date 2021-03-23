package com.simplicity.tools.colortools;

import javax.print.attribute.IntegerSyntax;
import javax.swing.*;
import javax.swing.border.EtchedBorder;
import com.simplicity.client.cache.definitions.ItemDefinition;
import com.simplicity.tools.ModelViewer;
import com.sun.org.apache.xerces.internal.impl.dv.xs.IntegerDV;

import net.runelite.api.IntegerNode;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.image.BufferedImage;

/** the Main class for the Window selector*/
public class WindowSelectColor extends JFrame {

	private static final long serialVersionUID = 1L;

	/** robot for handle the mouse dragged*/
    private Robot robot;

    /** get the current location of the mouse*/
    private TriggerClass triggerMouse = TriggerClass.OUT;

    /** the height of the JFrame */
    public static final int HEIGHT = 600;

    /** the width of the JFrame*/
    public static final int WIDTH = 680;

    /** JPanel with all the color*/
    private ArrayColorSelector arrayColor;

    /** line of color for edit the arrayColor*/
    private LineSelectColor lineColor;

    /** the contentPane*/
    private final JPanel contentPane;

    /** show the color in big*/
    private JLabel colorSelect;

    /** show the value rgb of the color*/
    private JLabel valueRGB;

    /** show the value rgb and be a input*/
    private JTextField valueRGBdisplay;

    /** show the value hex of the color*/
    private JLabel valueHex;

    /** show the value hexa and can be a input*/
    private JTextField valueHexDisplay;

    /** the cursor for select color*/
    public static final Cursor cursorSelect = new Cursor(Cursor.CROSSHAIR_CURSOR);

    /** the cursor normal*/
    public static Cursor cursorDefault = new Cursor(Cursor.DEFAULT_CURSOR);


    public WindowSelectColor() throws HeadlessException {
        super("RS2 Color Picker");

        try {
            robot = new Robot();
        } catch (AWTException e) {
            e.printStackTrace();
        }

        //set
        setSize(WIDTH, HEIGHT );
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setResizable(false);
        setVisible(true);

        this.contentPane =  (JPanel) getContentPane();
        contentPane.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));
        contentPane.setLayout(new BorderLayout(3,5));

        createComponent();

        initMouseListenners();

        setInfoColor(new Color(180,80,20));
        setVisible(true);
    }

    /** init mouselistenners*/
    private void initMouseListenners(){

        arrayColor.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                mouseOnMove(e, TriggerClass.ARRAYCOLORSELECTOR);

            }

            @Override
            public void mouseEntered(MouseEvent e) {
                super.mouseEntered(e);
                triggerMouse = TriggerClass.ARRAYCOLORSELECTOR;
                setCursor(cursorSelect);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                super.mouseExited(e);
                triggerMouse = TriggerClass.OUT;
                setCursor(cursorDefault);
            }
        });

        arrayColor.addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                super.mouseDragged(e);
                if (triggerMouse != TriggerClass.ARRAYCOLORSELECTOR) {
                    //math

                    //location absolute from the screen of the array color
                    int locationScreenX = (int) arrayColor.getLocationOnScreen().getX();
                    int locationScreenY = (int) arrayColor.getLocationOnScreen().getY();

                    // max value of the array color from the screen
                    // -2 is the border
                    int xMax = arrayColor.getWidth() + locationScreenX -2;
                    int yMax = arrayColor.getHeight() + locationScreenY -2;

                    // value of the mouse from the screen
                    replaceOfMouse(e, locationScreenX, locationScreenY, xMax, yMax);

                }
                mouseOnMove(e, TriggerClass.ARRAYCOLORSELECTOR);
            }
        });

        lineColor.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                mouseOnMove(e, TriggerClass.LINESELECTOR);
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                super.mouseEntered(e);
                triggerMouse = TriggerClass.LINESELECTOR;
                setCursor(cursorSelect);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                super.mouseExited(e);
                triggerMouse = TriggerClass.OUT;
                setCursor(cursorDefault);
            }
        });

        lineColor.addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                super.mouseDragged(e);
                if (triggerMouse != TriggerClass.LINESELECTOR) {
                    //math

                    //location absolute from the screen of the line selector
                    int locationScreenX = (int) lineColor.getLocationOnScreen().getX();
                    int locationScreenY = (int) lineColor.getLocationOnScreen().getY();

                    // max value of the array color from the screen
                    int xMax = lineColor.getWidth() + locationScreenX -2;
                    int yMax = lineColor.getHeight() + locationScreenY -2;

                    // value of the mouse from the screen
                    replaceOfMouse(e, locationScreenX, locationScreenY, xMax, yMax);

                }
                mouseOnMove(e, TriggerClass.LINESELECTOR);
            }
        });

    }

    /** replace the mouse*/
    private void replaceOfMouse(MouseEvent e, int locationScreenX, int locationScreenY, int xMax, int yMax) {
        int mouseX = (int) e.getLocationOnScreen().getX();
        int mouseY = (int) e.getLocationOnScreen().getY();

        if (mouseX < locationScreenX) robot.mouseMove(locationScreenX, mouseY);
        else if (mouseX > xMax) robot.mouseMove(xMax, mouseY);

        if (mouseY < locationScreenY) robot.mouseMove(mouseX, locationScreenY);
        else if (mouseY > yMax) robot.mouseMove(mouseX, yMax);
    }

    /** action when the mouse move*/
    private void mouseOnMove(MouseEvent e, TriggerClass trigger){
        int x = e.getX();
        int y = e.getY();
        Rectangle rectangle = trigger == TriggerClass.LINESELECTOR ? lineColor.getBounds(): arrayColor.getBounds();

        BufferedImage pixel = new BufferedImage(rectangle.width,rectangle.height, BufferedImage.TYPE_INT_ARGB);
        if ( trigger == TriggerClass.LINESELECTOR) lineColor.paintAll(pixel.createGraphics());
        else arrayColor.paintAll(pixel.createGraphics());
        Color color;
        //make sur the dragged doesn't create an error if the mouse is out of the JFrame
        try {
            color =new Color(pixel.getRGB(x,y), true);
        } catch (Exception error){
            return;
        }

        if (trigger == TriggerClass.LINESELECTOR) actionInLine(color);
        else actionInArray(color);
    }

    /** do the action in the lineSelect*/
    private void actionInLine(Color color){
        setInfoColor(color);
        arrayColor.setValueOfColor(color);
        arrayColor.paint(arrayColor.getGraphics());
    }

    /** do the action on the array*/
    private void actionInArray(Color color){
        setInfoColor(color);
    }

    /**do the action when  hex is input*/
    private void actionInInputHex(ActionEvent event){
        String value = valueHexDisplay.getText().replace(" ", "");
        Color color;
        if ('#' != value.charAt(0)) value = '#' + value;
        if (value.length() == 4) value = "#" + value.charAt(1) + value.charAt(1) + value.charAt(2) + value.charAt(2) + value.charAt(3) + value.charAt(3);
        try {
            color = Color.decode(value);
        } catch (Exception e){
            valueHexDisplay.setForeground(Color.decode("#ff001b"));
            return;
        }
        setInfoColor(color);
        arrayColor.setValueOfColor(color);
        arrayColor.paint(arrayColor.getGraphics());
    }

    /** do the action when rgb is input*/
    private void actionInInputRGB(ActionEvent event){
        String value = valueRGBdisplay.getText();
       Color color;
       try {
    	   color = new Color(ItemDefinition.RS2HSB_to_RGB(Integer.parseInt(value)));
           //color = new Color(Integer.parseInt(eachData[0]), Integer.parseInt(eachData[1]), Integer.parseInt(eachData[2]));
       } catch (Exception error){
           valueRGBdisplay.setForeground(Color.decode("#ff001b"));
           return;
       }
       setInfoColor(color);
       arrayColor.setValueOfColor(color);
       arrayColor.paint(arrayColor.getGraphics());
    }

    /** create all the JPanel and add them*/
    private void createComponent(){
        arrayColor = new ArrayColorSelector();
        lineColor = new LineSelectColor();
        //the JPanel where all the date of the color will be
        JPanel infoColor = createInfoColor();

        contentPane.add(arrayColor, BorderLayout.CENTER);
        contentPane.add(lineColor, BorderLayout.SOUTH);
        contentPane.add(infoColor, BorderLayout.NORTH);
    }


    /** create the JPanel who contains all the data of the color pick*/
    private JPanel createInfoColor(){
        JPanel pane = new JPanel();
        pane.setMinimumSize(new Dimension( 60, 60));
        pane.setMaximumSize(new Dimension( 60, 60));
        pane.setLayout(new FlowLayout());

        colorSelect = new JLabel("     ");
        colorSelect.setOpaque(true);

        valueRGB = new JLabel("RS2 ");
        valueHex = new JLabel("HEX ");
        valueHexDisplay = new JTextField("       ");
        valueRGBdisplay = new JTextField("             ");
        valueRGBdisplay.setPreferredSize(new Dimension(52, 25));
        valueRGBdisplay.addActionListener(this::actionInInputRGB);
        valueHexDisplay.addActionListener(this::actionInInputHex);

        pane.add(colorSelect);
        pane.add(valueRGB);
        pane.add(valueRGBdisplay);
        pane.add(valueHex);
        pane.add(valueHexDisplay);

        return pane;
    }

    /** actualise all the date of the new pixel chose*/
    public void setInfoColor(Color color){
        colorSelect.setBackground(color);
        valueRGB.setText("RS2 :");
        valueRGBdisplay.setForeground(Color.WHITE);
        int rs2Color = ItemDefinition.RGB_to_RS2HSB(color.getRed(), color.getGreen(), color.getBlue());
        valueRGBdisplay.setText(String.valueOf(rs2Color));
        valueHex.setText("HEX :");
        valueHexDisplay.setForeground(Color.WHITE);
        valueHexDisplay.setText("#"+Integer.toHexString(color.getRGB()).substring(2));
        
        if (viewer != null) {
        	viewer.setColor(rs2Color);
        }
    }
    
    public void setSelectedColor(int rs2hsb) {
    	int rgb = ItemDefinition.RS2HSB_to_RGB(rs2hsb);
    	Color color = new Color(rgb);
    	colorSelect.setBackground(color);
    	valueRGB.setText("RS2 :");
    	valueRGBdisplay.setForeground(Color.WHITE);
    	valueRGBdisplay.setText(Integer.toString(rs2hsb));
    	valueHex.setText("HEX :");
    	valueHexDisplay.setForeground(Color.WHITE);
        valueHexDisplay.setText("#"+Integer.toHexString(color.getRGB()).substring(2));
    }
    
    private ModelViewer viewer;
    
    public void setModelViewer(ModelViewer viewer) {
    	this.viewer = viewer;
    }
}
