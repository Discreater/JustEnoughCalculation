package me.towdium.jecalculation.client.widget.widgets;

import mcp.MethodsReturnNonnullByDefault;
import me.towdium.jecalculation.client.gui.JecGui;
import me.towdium.jecalculation.client.resource.Resource;
import me.towdium.jecalculation.client.widget.Widget;

import javax.annotation.ParametersAreNonnullByDefault;

/**
 * Author: towdium
 * Date:   17-8-18.
 */
@ParametersAreNonnullByDefault
@MethodsReturnNonnullByDefault
public class WLine extends Widget {
    public int y;

    public WLine(int y) {
        this.y = y;
    }

    @Override
    public void onDraw(JecGui gui, int xMouse, int yMouse) {
        gui.drawResource(Resource.WGT_LINE, 6 + gui.getGuiLeft(), y + gui.getGuiTop() - 1);
    }
}