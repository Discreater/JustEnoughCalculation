package me.towdium.jecalculation.gui.widgets;

import me.towdium.jecalculation.gui.JecaGui;
import me.towdium.jecalculation.polyfill.MethodsReturnNonnullByDefault;

import javax.annotation.Nullable;
import javax.annotation.ParametersAreNonnullByDefault;
import java.util.Collections;
import java.util.List;

import static me.towdium.jecalculation.gui.Resource.*;

/**
 * Author: Towdium
 * Date: 18-12-7
 */
@ParametersAreNonnullByDefault
@MethodsReturnNonnullByDefault
public class WTick extends WContainer {
    WButton button;
    int xPos, yPos, xSize, ySize;
    String name;
    boolean disabled;
    ListenerAction<? super WTick> listener;


    public WTick(int xPos, int yPos, int xSize, int ySize, @Nullable String name) {
        this.xPos = xPos;
        this.yPos = yPos;
        this.xSize = xSize;
        this.ySize = ySize;
        this.name = name;
        setSelected(false);
    }

    public WTick setDisabled(boolean disabled) {
        this.disabled = disabled;
        button.setDisabled(disabled);
        return this;
    }

    public WTick setListener(ListenerAction<? super WTick> listener) {
        this.listener = listener;
        return this;
    }

    public boolean selected() {
        return !(button instanceof Normal);
    }

    public WTick setSelected(boolean selected) {
        if (selected == selected())
            return this;
        remove(button);
        if (selected)
            button = new Selected(xPos, yPos, xSize, ySize, name);
        else
            button = new Normal(xPos, yPos, xSize, ySize, name);
        add(button);
        return this;
    }

    private class Normal extends WButton {
        public Normal(int xPos, int yPos, int xSize, int ySize, @Nullable String name) {
            super(xPos, yPos, xSize, ySize, name);
            listener = i -> {
                setSelected(true);
                WTick.this.listener.invoke(WTick.this);
            };
            disabled = WTick.this.disabled;
        }

        @Override
        protected List<String> getSuffix() {
            return Collections.singletonList("unselected");
        }
    }

    private class Selected extends WButton {
        public Selected(int xPos, int yPos, int xSize, int ySize, @Nullable String name) {
            super(xPos, yPos, xSize, ySize, name);
            listener = i -> {
                setSelected(false);
                WTick.this.listener.invoke(WTick.this);
            };
            disabled = WTick.this.disabled;
        }

        @Override
        protected List<String> getSuffix() {
            return Collections.singletonList("selected");
        }

        @Override
        public boolean onDraw(JecaGui gui, int xMouse, int yMouse) {
            boolean ret = super.onDraw(gui, xMouse, yMouse);
            boolean hovered = JecaGui.mouseIn(xPos + 1, yPos + 1, xSize - 2, ySize - 2, xMouse, yMouse);
            gui.drawResourceContinuous(disabled ? WGT_BUTTON_S_D : (hovered ? WGT_BUTTON_S_F : WGT_BUTTON_S_N), xPos,
                                       yPos, xSize, ySize, 5, 5, 5, 5);

            return ret;
        }
    }
}
