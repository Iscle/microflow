package org.daniel.microflow.model;

import java.awt.*;
import java.awt.geom.QuadCurve2D;

public class Node extends Element {

    protected Point center;
    protected NodeType type;
    private transient Graph graph;

    private static final String TAD = "TAD";

    public Node(NodeType type, String name, Point center, Graph g) {
        super(name);
        this.type = type;
        this.center = center;
        setBounds();
        setName(name);
    }

    public Node(NodeType type, Point center, Graph g) {
        this(type, String.valueOf(g.getStateCount()), center, g);
        g.incStateCount();
    }

    @Override
    protected void setBounds() {
        bounds.setBounds(
                center.x - (type.getWidth() / 2), center.y - (type.getHeight() / 2),
                type.getWidth(), type.getHeight()
        );
    }

    public NodeType getType() {
        return type;
    }

    public Point getCenter() {
        return center;
    }

    public void setCenter(Point center) {
        this.center = center;
        setBounds();
        setName(name);
    }

    public boolean circleContains(Point p) {
        //radio^2 -> 45^2 = 2025 para un TAD
        //           22.5^2 = 506.25 para un estado
        //verificar si es circulo dado por esto tiene un punto p
        return Math.pow(p.x - center.x, 2) + Math.pow(p.y - center.y, 2) <= (type.equals(NodeType.TAD) ? 3600 : 506.25);
    }

    @Override
    public void setName(String name) {
        Canvas c = new Canvas();
        if (type.equals(NodeType.VARIABLE) || type.equals(NodeType.PERIPHERAL)) {
            int width = c.getFontMetrics(FONT_MED).stringWidth(name);
            bounds.setBounds(
                    center.x - (width / 2) - 5, center.y - (type.getHeight() / 2),
                    width + 10, type.getHeight()
            );
        }

        if (type.equals(NodeType.TEXT)) {
            String[] lines = name.replace("\t", "    ").split("\n");
            int longest = 0;

            for (String l : lines) {
                int lineWidth = c.getFontMetrics(FONT_MED).stringWidth(l);
                if (lineWidth > longest) longest = lineWidth;
            }

            if (lines.length > 1) {
                FontMetrics metrics = c.getFontMetrics(FONT_MED);
                int height = lines.length * metrics.getAscent() + 11;

                bounds.setBounds(
                        center.x - (longest / 2) - 5, center.y - (height / 2),
                        longest + 10, height
                );
            } else {
                bounds.setBounds(
                        center.x - (longest / 2) - 5, center.y - (type.getHeight() / 2),
                        longest + 10, type.getHeight()
                );
            }
        }

        super.setName(name);
    }

    @Override
    public void draw(Graphics2D g) {
        if (selected) {
            drawOutline(g);
        }

        int x = bounds.x;
        int y = bounds.y;
        int width = bounds.width;
        int height = bounds.height;
        g.setColor(type.getFill());

        switch (type) {
            case TAD:
            case STATE:
                g.fillOval(x, y, width, height);

                setStrokeAndColor(g);
                g.setStroke(type.getOuter());
                g.setColor(type.getOutline());
                g.drawOval(x, y, width, height);

                if (type.equals(NodeType.TAD)) {
                    Point bezOrigin = new Point(center.x, center.y - height / 2);
                    Point bezDest = new Point(center.x + width / 2, center.y);
                    Point bezControl = new Point(center.x + width / 2 - 5, center.y - height / 2 + 5);
                    Point moonOrigin = bezierQuadratic(0.15, bezOrigin, bezControl, bezDest);
                    Point moonDest = bezierQuadratic(0.85, bezOrigin, bezControl, bezDest);

                    g.draw(new QuadCurve2D.Float(
                            moonOrigin.x, moonOrigin.y, center.x + width / 5, center.y - height / 5,
                            moonDest.x, moonDest.y
                    ));

                    g.setFont(FONT_SMALL);
                    g.drawString(TAD, center.x - 20, center.y - 20);
                }

                break;
            case VARIABLE:
                g.setColor(type.getFill());
                g.fill(bounds);
                setStrokeAndColor(g);
                g.drawLine(x, y, x + width, y);
                g.drawLine(x, y + height, x + width, y + height);
                break;
            case PERIPHERAL:
                g.fill(bounds);

                if (selected) {
                    g.setStroke(type.getOuter());
                    g.setColor(Color.LIGHT_GRAY);
                } else {
                    setStrokeAndColor(g);
                }
                g.draw(bounds);
                break;
        }

        if (type.equals(NodeType.TEXT)) {
            String[] lines = name.replace("\t", "    ").split("\n");
            int lineHeight = g.getFontMetrics(FONT_MED).getAscent();
            g.setFont(FONT_MED);
            for (int i = 0; i < lines.length; i++) {
                g.setColor(Color.BLACK);
                String line = lines[i];
                g.drawString(line, bounds.x + 5, bounds.y + (lineHeight * (i + 1)) + 4);
            }
        } else {
            Font f;
            if (type.equals(NodeType.STATE)) {
                f = FONT_LARGE;
            } else if (type.equals(NodeType.TAD)) {
                f = FONT_MED_SMALL;
            } else {
                f = FONT_MED;
            }
            g.setFont(f);
            g.setColor(Color.BLACK);
            int nameWidth = g.getFontMetrics().stringWidth(name);
            g.drawString(name, center.x - nameWidth / 2,
                    center.y + f.getSize() / 3);
        }
    }

    private void setStrokeAndColor(Graphics2D g) {
        g.setStroke(type.getOuter());
        g.setColor(type.getOutline());
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Node)) return false;

        Node other = (Node) o;
        if (!other.center.equals(center)) return false;
        if (!other.type.equals(type)) return false;
        if (!other.name.equals(name)) return false;
        return other.bounds.equals(bounds);
    }
}
