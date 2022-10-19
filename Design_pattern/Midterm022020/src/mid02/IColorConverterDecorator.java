package mid02;

import java.awt.*;

public abstract class IColorConverterDecorator implements IColorConverter {
    IColorConverter converter;

    public IColorConverterDecorator(IColorConverter converter) {
        this.converter = converter;
    }

    @Override
    public Color getColor(Color color) {
        return converter.getColor(color);
    }
    public String getExtension() {
        return converter.getExtension();
    }
}
