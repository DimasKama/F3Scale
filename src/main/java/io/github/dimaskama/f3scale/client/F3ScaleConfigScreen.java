package io.github.dimaskama.f3scale.client;

import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.client.gui.widget.SliderWidget;
import net.minecraft.screen.ScreenTexts;
import net.minecraft.text.Text;

public class F3ScaleConfigScreen extends Screen {
    private final Screen parent;

    public F3ScaleConfigScreen(Screen parent) {
        super(Text.translatable("f3scale"));
        this.parent = parent;
    }

    @Override
    protected void init() {
        float currentScale = F3Scale.CONFIG.scale;
        addDrawableChild(new SliderWidget(
                (width - 200) >> 1, 50,
                200, 20,
                Text.translatable("f3Scale.scale", String.format("%.2f", currentScale)),
                (currentScale - 0.01F) / 1.49F
        ) {
            @Override
            protected void updateMessage() {
                setMessage(Text.translatable("f3Scale.scale", String.format("%.2f", getExactValue())));
            }
            @Override
            protected void applyValue() {
                F3Scale.CONFIG.scale = getExactValue();
                F3Scale.CONFIG.dirty = true;
            }
            private float getExactValue() {
                return 0.01F + ((float) value * 1.49F);
            }
        });
        addDrawableChild(ButtonWidget.builder(Text.translatable("controls.reset"), button -> reset())
                .dimensions((width - 300) >> 1, height - 35, 140, 20)
                .build());
        addDrawableChild(ButtonWidget.builder(ScreenTexts.BACK, button -> close())
                .dimensions((width + 10) >> 1, height - 35, 140, 20)
                .build());
    }

    private void reset() {
        F3Scale.CONFIG.reset();
        clearAndInit();
    }

    @Override
    public void render(DrawContext context, int mouseX, int mouseY, float delta) {
        if (client.world == null)  {
            renderBackgroundTexture(context);
        }
        context.drawCenteredTextWithShadow(textRenderer, title, width >>> 1, 12, 0xFFFFFFFF);
        super.render(context, mouseX, mouseY, delta);
    }

    @Override
    public void close() {
        client.setScreen(parent);
    }
}
