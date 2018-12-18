package me.towdium.jecalculation.gui.widgets;

import mcp.MethodsReturnNonnullByDefault;
import me.towdium.jecalculation.data.label.ILabel;
import me.towdium.jecalculation.gui.JecaGui;
import me.towdium.jecalculation.utils.Utilities;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nullable;
import javax.annotation.ParametersAreNonnullByDefault;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Author: towdium
 * Date:   17-9-14.
 */
@ParametersAreNonnullByDefault
@MethodsReturnNonnullByDefault
@SideOnly(Side.CLIENT)
public class WContainer implements IContainer {
    protected List<IWidget> widgets = new ArrayList<>();

    public void add(IWidget w) {
        widgets.add(w);
    }

    public void addAll(IWidget... w) {
        for (IWidget aw : w) add(aw);
    }

    public void remove(IWidget w) {
        widgets.remove(w);
    }

    public void removeAll(IWidget... w) {
        for (IWidget aw : w) remove(aw);
    }

    public void clear() {
        widgets.clear();
    }

    @Override
    public void onDraw(JecaGui gui, int mouseX, int mouseY) {
        widgets.forEach(widget -> widget.onDraw(gui, mouseX, mouseY));
    }

    @Override
    public boolean onClicked(JecaGui gui, int xMouse, int yMouse, int button) {
        return new Utilities.ReversedIterator<>(widgets).stream()
                .anyMatch(i -> i.onClicked(gui, xMouse, yMouse, button));
    }

    @Override
    public boolean onKey(JecaGui gui, char ch, int code) {
        return new Utilities.ReversedIterator<>(widgets).stream()
                .anyMatch(i -> i.onKey(gui, ch, code));
    }

    @Override
    public boolean onScroll(JecaGui gui, int xMouse, int yMouse, int diff) {
        return new Utilities.ReversedIterator<>(widgets).stream()
                .anyMatch(i -> i.onScroll(gui, xMouse, yMouse, diff));
    }

    @Override
    public boolean onTooltip(JecaGui gui, int xMouse, int yMouse, List<String> tooltip) {
        return new Utilities.ReversedIterator<>(widgets).stream()
                .anyMatch(i -> i.onTooltip(gui, xMouse, yMouse, tooltip));
    }

    @Nullable
    @Override
    public ILabel getLabelUnderMouse(int xMouse, int yMouse) {
        return new Utilities.ReversedIterator<>(widgets).stream()
                .map(i -> i.getLabelUnderMouse(xMouse, yMouse))
                .filter(Objects::nonNull)
                .findFirst().orElse(null);
    }
}