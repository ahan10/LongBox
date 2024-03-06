package org.longbox.businesslogic.utils;

import org.longbox.domainobjects.dto.CommentDTO;
import org.longbox.persistence.entity.Comment;

import javax.swing.*;
import java.awt.*;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MultiLineCellRenderer extends JLabel implements ListCellRenderer<CommentDTO> {
    private static final int LINE_HEIGHT = 18; // Adjust this value as needed

    public MultiLineCellRenderer() {
        setOpaque(true);
    }

    @Override
    public Component getListCellRendererComponent(
            JList<? extends CommentDTO> list, CommentDTO value, int index,
            boolean isSelected, boolean cellHasFocus) {

        // Calculate the height based on the number of lines (word wrap)
        int numLines = calculateNumLines(value.getMessage());
        int height = (numLines + 1) * LINE_HEIGHT;

        // Set the text and preferred size
        setText("<html><b>" + value.getUserName() + "</b>: " + value.getMessage() +
                " <font color='gray'><i>" + formatDate(value.getCommentDate()) + "</i></font></html>");

        setPreferredSize(new Dimension(495, height));
        setBackground(isSelected ? list.getSelectionBackground() : list.getBackground());
        setForeground(isSelected ? list.getSelectionForeground() : list.getForeground());
        setVerticalAlignment(SwingConstants.TOP);

        return this;
    }

    private int calculateNumLines(String text) {
        int lineWidth = 495; // Adjust this value based on your component's width
        FontMetrics fm = getFontMetrics(getFont());

        int numLines = 0;
        int textWidth = 0;

        for (String word : text.split("\\s")) {
            int wordWidth = fm.stringWidth(word);

            if (textWidth + wordWidth > lineWidth) {
                numLines++;
                textWidth = wordWidth;
            } else {
                textWidth += wordWidth;
            }
        }

        // Increment for the last line
        if (textWidth > 0) {
            numLines++;
        }

        return numLines;
    }

    private String formatDate(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); // Adjust the format as needed
        return sdf.format(date);
    }
}