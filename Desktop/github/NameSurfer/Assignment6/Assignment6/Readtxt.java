
import java.awt.*;

import java.awt.font.*;
import java.awt.geom.*;
import javax.swing.*;
 
public class Readtxt extends JPanel {
    int[] data = {
        21, 14, 18, 03, 86, 88, 74, 87, 54, 77,
        61, 55, 48, 60, 49, 36, 38, 27, 20, 18
    };
    final int GRAPH_MARGIN_SIZE = 20;
 
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D)g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                            RenderingHints.VALUE_ANTIALIAS_ON);
        int width = getWidth();
        int height = getHeight();
        // Draw ordinate.
        g2.draw(new Line2D.Double(GRAPH_MARGIN_SIZE, GRAPH_MARGIN_SIZE, GRAPH_MARGIN_SIZE, height-GRAPH_MARGIN_SIZE));
        // Draw abcissa.
        g2.draw(new Line2D.Double(GRAPH_MARGIN_SIZE, height-GRAPH_MARGIN_SIZE, width-GRAPH_MARGIN_SIZE, height-GRAPH_MARGIN_SIZE));
        // Draw labels.
        Font font = g2.getFont();
        FontRenderContext frc = g2.getFontRenderContext();
        LineMetrics lm = font.getLineMetrics("0", frc);
        float sh = lm.getAscent() + lm.getDescent();
        // Ordinate label.
        String s = "data";
        float sy = GRAPH_MARGIN_SIZE + ((height - 2*GRAPH_MARGIN_SIZE) - s.length()*sh)/2 + lm.getAscent();
        for(int i = 0; i < s.length(); i++) {
            String letter = String.valueOf(s.charAt(i));
            float sw = (float)font.getStringBounds(letter, frc).getWidth();
            float sx = (GRAPH_MARGIN_SIZE - sw)/2;
            g2.drawString(letter, sx, sy);
            sy += sh;
        }
        // Abcissa label.
        s = "x axis";
        sy = height - GRAPH_MARGIN_SIZE + (GRAPH_MARGIN_SIZE - sh)/2 + lm.getAscent();
        float sw = (float)font.getStringBounds(s, frc).getWidth();
        float sx = (width - sw)/2;
        g2.drawString(s, sx, sy);
        // Draw lines.
        double xInc = (double)(width - 2*GRAPH_MARGIN_SIZE)/(data.length-1);
        double scale = (double)(height - 2*GRAPH_MARGIN_SIZE)/getMax();
        g2.setPaint(Color.green.darker());
        for(int i = 0; i < data.length-1; i++) {
            double x1 = GRAPH_MARGIN_SIZE + i*xInc;
            double y1 = height - GRAPH_MARGIN_SIZE - scale*data[i];
            double x2 = GRAPH_MARGIN_SIZE + (i+1)*xInc;
            double y2 = height - GRAPH_MARGIN_SIZE - scale*data[i+1];
            g2.draw(new Line2D.Double(x1, y1, x2, y2));
        }
        // Mark data points.
        g2.setPaint(Color.red);
        for(int i = 0; i < data.length; i++) {
            double x = GRAPH_MARGIN_SIZE + i*xInc;
            double y = height - GRAPH_MARGIN_SIZE - scale*data[i];
            g2.fill(new Ellipse2D.Double(x-2, y-2, 4, 4));
        }
    }
 
    private int getMax() {
        int max = -Integer.MAX_VALUE;
        for(int i = 0; i < data.length; i++) {
            if(data[i] > max)
                max = data[i];
        }
        return max;
    }
 
    public static void main(String[] args) {
        JFrame f = new JFrame();
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.add(new Readtxt());
        f.setSize(400,400);
        f.setLocation(200,200);
        f.setVisible(true);
    }
}