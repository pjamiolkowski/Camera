package Interface;


import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.text.DecimalFormat;

public class Window extends JFrame implements KeyListener {
    private PanelFigure panelFigure;
    private JPanel windowForm;
    private JPanel panelFigureForm;
    private JLabel zoomText;
    private static final double displacement = 1;
    private static final double angle = 1;
    private static final int zoomIncrease = 2;
    private static final double angleViewing = 70;
    private int zoom;
    private double d;


    public Window() {
        panelFigure = new PanelFigure();
        panelFigure.setBackground(Color.BLACK);
        panelFigureForm.add(panelFigure);
        this.setContentPane(windowForm);
        setTitle("Kamera");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.addKeyListener(this);
        this.setFocusable(true);
        pack();
        setVisible(true);
        initMethod();
    }


    private void initMethod() {      //metod inicjująca ustawienia podstawowe wyświetlanych figur
        zoom = 1;
        d = (((double) panelFigure.getHeight() / 2) / (Math.tan(Math.toRadians(angleViewing / 2)))) * zoom;
        panelFigure.getClcFigure().setPosition(0, 0, 40, 0, 0, 0, d);
        panelFigure.getClcFigure().setPosition(0, 0, 0, 0, 0, 180, d);
        viewCameraPosition(zoom);
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {    //metoda sprawdzająca naciśnięty klawisz
        double x = 0;
        double y = 0;
        double z = 0;
        double angleX = 0;
        double angleY = 0;
        double angleZ = 0;

        if (e.getKeyCode() == 74) {
            x = displacement;
        }
        if (e.getKeyCode() == 76) {
            x = -displacement;
        }
        if (e.getKeyCode() == 73) {
            y = displacement;
        }
        if (e.getKeyCode() == 75) {
            y = -displacement;
        }
        if (e.getKeyCode() == 85) {
            z = displacement;
        }
        if (e.getKeyCode() == 79) {
            z = -displacement;
        }
        if (e.getKeyCode() == 83) {
            angleX = angle;
        }
        if (e.getKeyCode() == 87) {
            angleX = -angle;
        }
        if (e.getKeyCode() == 68) {
           angleY = angle;
        }
        if (e.getKeyCode() == 65){
            angleY = -angle;
        }
        if (e.getKeyCode() == 69) {
            angleZ = angle;
        }
        if (e.getKeyCode() == 81) {
            angleZ = -angle;
        }
        if (e.getKeyCode() == 84) {
            if (zoom * zoomIncrease <= 32) {
                zoom *= zoomIncrease;
                d *= zoomIncrease;
            }
        }
        if (e.getKeyCode() == 71) {
            if (zoom > 1) {
                zoom /= zoomIncrease;
                d /= zoomIncrease;
            }
        }

        panelFigure.getClcFigure().setPosition(x, y, z, angleX, angleY, angleZ, d);     //wywołanie metody wyliczającej położenie figur
        viewCameraPosition(zoom); //wywołanie metody ustawiającej wyświetlane parametry na ekranie
        panelFigure.repaint();      //odświeżenie obrazu
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }

    private void viewCameraPosition(double zoom) {   // metody ustawiająca wyświetlane parametry na ekranie
        DecimalFormat decimalFormat = new DecimalFormat();
        decimalFormat.setMaximumFractionDigits(0);
        zoomText.setText(decimalFormat.format(zoom));
    }


    public static void main(String[] args) {

        EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Window();
            }
        });
    }
}
