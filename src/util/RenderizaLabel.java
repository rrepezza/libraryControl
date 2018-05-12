/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import java.awt.Component;
import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;

/**
 *
 * @author repez
 */
public class RenderizaLabel implements TableCellRenderer{

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        TableColumn coluna = table.getColumn("FOTO");
        coluna.setMinWidth(50);
        coluna.setMaxWidth(50);
        table.setRowHeight(50);
        return (Component) value;
    }
    
}
