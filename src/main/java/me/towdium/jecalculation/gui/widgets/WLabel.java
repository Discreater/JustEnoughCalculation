package me.towdium.jecalculation.gui.widgets;

import codechicken.lib.gui.GuiDraw;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import me.towdium.jecalculation.data.label.ILabel;
import me.towdium.jecalculation.gui.JecaGui;
import me.towdium.jecalculation.polyfill.MethodsReturnNonnullByDefault;
import me.towdium.jecalculation.utils.Utilities.Timer;

import javax.annotation.Nullable;
import javax.annotation.ParametersAreNonnullByDefault;
import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.Function;

import static me.towdium.jecalculation.gui.JecaGui.Font.HALF;
import static me.towdium.jecalculation.gui.Resource.WGT_SLOT;

/**
 * Author: towdium
 * Date:   17-8-17.
 */
@ParametersAreNonnullByDefault
@MethodsReturnNonnullByDefault
@SideOnly(Side.CLIENT)
public class WLabel implements IWidget {
    public int xPos, yPos, xSize, ySize;
    ILabel label = ILabel.EMPTY;
    public boolean accept;
    public ListenerValue<? super WLabel, ILabel> update;
    public ListenerAction<? super WLabel> leftClick, rightClick;
    Function<ILabel, String> fmtAmount = i -> "";
    BiConsumer<ILabel, List<String>> fmtTooltip = (i, j) -> i.getToolTip(j, false);
    protected Timer timer = new Timer();

    public WLabel(int xPos, int yPos, int xSize, int ySize, boolean accept) {
        this.xPos = xPos;
        this.yPos = yPos;
        this.xSize = xSize;
        this.ySize = ySize;
        this.accept = accept;
    }

    public ILabel getLabel() {
        return label;
    }

    public WLabel setLabel(ILabel label) {
        return setLabel(label, false);
    }

    public WLabel setLabel(ILabel label, boolean notify) {
        this.label = label;
        if (notify)
            notifyUpdate();
        return this;
    }

    @Override
    public boolean onDraw(JecaGui gui, int xMouse, int yMouse) {
        gui.drawResourceContinuous(WGT_SLOT, xPos, yPos, xSize, ySize, 3, 3, 3, 3);
        label.drawLabel(gui, xPos + xSize / 2, yPos + ySize / 2, true);
        String s = fmtAmount.apply(label);
        gui.drawText(xPos + xSize / 2.0f + 8 - HALF.getTextWidth(s), yPos + ySize / 2.0f + 8.5f - HALF.getTextHeight(),
                     HALF, s);
        if (accept) {
            timer.setState(gui.hand != ILabel.EMPTY);
            int color = 0xFFFFFF + (int) ((-Math.cos(timer.getTime() * Math.PI / 1500) + 1) * 0x40) * 0x1000000;
            GuiDraw.drawRect(xPos + 1, yPos + 1, xSize - 2, ySize - 2, color);
        }
        if (mouseIn(xMouse, yMouse))
            GuiDraw.drawRect(xPos + 1, yPos + 1, xSize - 2, ySize - 2, 0x80FFFFFF);
        return false;
    }

    @Override
    public boolean onTooltip(JecaGui gui, int xMouse, int yMouse, List<String> tooltip) {
        if (!mouseIn(xMouse, yMouse))
            return false;
        if (label != ILabel.EMPTY) {
            tooltip.add(label.getDisplayName());
            tooltip.add(JecaGui.SEPARATOR);
            fmtTooltip.accept(label, tooltip);
        }
        return false;
    }

    @Nullable
    @Override
    public WLabel getLabelUnderMouse(int xMouse, int yMouse) {
        return mouseIn(xMouse, yMouse) ? this : null;
    }

    @Override
    public boolean onMouseClicked(JecaGui gui, int xMouse, int yMouse, int button) {
        if (!mouseIn(xMouse, yMouse))
            return false;
        if(button == 1) {
            notifyRightClick();
            return true;
        }
        if (accept) {
            if (gui.hand == ILabel.EMPTY && leftClick != null)
                notifyLeftClick();
            else {
                label = gui.hand;
                gui.hand = label.EMPTY;
                notifyUpdate();
            }
        } else
            notifyLeftClick();
        return true;
    }

    public WLabel setLsnrUpdate(ListenerValue<? super WLabel, ILabel> listener) {
        update = listener;
        return this;
    }

    public WLabel setLsnrLeftClick(ListenerAction<? super WLabel> listener) {
        leftClick = listener;
        return this;
    }

    public WLabel setLsnrRightClick(ListenerAction<? super WLabel> listener) {
        rightClick = listener;
        return this;
    }

    public WLabel setFmtAmount(Function<ILabel, String> f) {
        fmtAmount = f;
        return this;
    }

    public WLabel setFmtTooltip(BiConsumer<ILabel, List<String>> f) {
        fmtTooltip = f;
        return this;
    }

    public boolean mouseIn(int x, int y) {
        int xx = x - xPos;
        int yy = y - yPos;
        return xx >= 0 && xx < xSize && yy >= 0 && yy < ySize;
    }

    private void notifyLeftClick() {
        if (leftClick != null)
            leftClick.invoke(this);
    }

    private void notifyRightClick() {
        if (rightClick != null)
            rightClick.invoke(this);
    }

    private void notifyUpdate() {
        if (update != null)
            update.invoke(this, label);
    }
}
